package me.krynox.spectral.block.entity;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.capability.SpectralCapabilities;
import me.krynox.spectral.capability.ectohandler.EctoHandlerImpl;
import me.krynox.spectral.capability.ectohandler.IEctoHandler;
import me.krynox.spectral.crafting.EctoInvRecipeWrapper;
import me.krynox.spectral.crafting.SpectralForgeRecipe;
import me.krynox.spectral.setup.Registration;
import me.krynox.spectral.spell.MagicType;
import me.krynox.spectral.util.SpectralDamageSources;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;

public class SpectralForgeBE extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private final ItemStackHandler inventory;
    private final IEctoHandler ectoStorage;
    //cached maybes to return for the getCapability calls
    private final LazyOptional<IItemHandler> maybeInventory;
    private final LazyOptional<IEctoHandler> maybeEctoStorage;
    //also cache the recipe wrapper instance
    private final EctoInvRecipeWrapper recipeWrapper;
    private final RecipeManager.CachedCheck<EctoInvRecipeWrapper, SpectralForgeRecipe> recipeChecker;

    private final int tickInterval = 5;
    private int tickTimer = 0;

    private boolean isActive;
    private int portalLayer; //invalid if !isActive
    @Nullable
    private AABB portalBB; //invalid and nullable if !isActive. must be nonnull if isActive.

    public SpectralForgeBE(BlockPos pPos, BlockState pBlockState) {
        super(Registration.SPECTRAL_FORGE_BE.get(), pPos, pBlockState);

        this.inventory = new ItemStackHandler(21); // first slot is the input, the others are the outputs
        this.ectoStorage = new EctoHandlerImpl();
        this.maybeInventory = LazyOptional.of(() -> inventory);
        this.maybeEctoStorage = LazyOptional.of(() -> ectoStorage);
        this.recipeChecker = RecipeManager.createCheck(Registration.SPECTRAL_FORGE_RECIPETYPE.get());
        this.recipeWrapper = new EctoInvRecipeWrapper(inventory, ectoStorage);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SpectralForgeBE blockEntity) {
        if(!level.isClientSide) {
            if(blockEntity.tickTimer <= 0 && blockEntity.portalBB != null && blockEntity.isActive) {
                for(Entity e : level.getEntities(null, blockEntity.portalBB)) {
                    if(e instanceof ItemEntity) {
                        boolean craftSucceeded = blockEntity.tryCraft(((ItemEntity) e).getItem());
                        if(craftSucceeded) {
                            e.remove(Entity.RemovalReason.KILLED);
                        } else {
                            blockEntity.yeet(e, false);
                        }

                    } else if(e instanceof LivingEntity) {
                        LivingEntity le = (LivingEntity) e;
                        le.hurt(SpectralDamageSources.SPECTRAL_FORGE, le.getMaxHealth() / 3);
                        blockEntity.yeet(le, true);
                    } else {
                        blockEntity.yeet(e, false);
                    }
                }
                blockEntity.tickTimer = blockEntity.tickInterval;
            } else {
                blockEntity.tickTimer -= 1;
            }
        }
    }

    public void tryInitialiseMultiblock() {
        int above = 5;
        int below = 10;
        Block portalFrame = Blocks.CRYING_OBSIDIAN;

        Optional<Integer> cagesLevel = getCagesLevel(above);
        Optional<Integer> portalsLevel = getPortalLevel(below, portalFrame);

        if(cagesLevel.isPresent() && portalsLevel.isPresent()) {
            this.isActive = true;
            this.portalLayer = portalsLevel.get();
            updatePortalBB();
            Spectral.LOGGER.info("Initialised forge, portal at " + portalBB);
        }
    }

    public void withdrawItems(ServerPlayer player) {
        for(int i = 1; i < this.inventory.getSlots(); i++) {
            player.getInventory().placeItemBackInInventory(this.inventory.getStackInSlot(i));
        }
    }

    private void updatePortalBB() {
        if(this.isActive) {
            double x = getBlockPos().getX();
            double y = getBlockPos().getY() - portalLayer;
            double z = getBlockPos().getZ();
            this.portalBB = new AABB(x + 3, y + 1, z + 3, x - 2, y, z - 2);
        }
    }

    //////////////////////////
    //// Capability stuff ////
    //////////////////////////

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return this.maybeInventory.cast();
        } else if(cap == SpectralCapabilities.ECTO_HANDLER) {
            return this.maybeEctoStorage.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.maybeInventory.invalidate();
        this.maybeEctoStorage.invalidate();
    }

    /////////////////////////////
    //// Saving data to disk ////
    /////////////////////////////

    @Override
    public void load(CompoundTag tag) {
        this.inventory.deserializeNBT(tag.getCompound("inv"));
        this.ectoStorage.deserialize(tag.getCompound("ecto"));
        this.isActive = tag.getBoolean("isActive");
        this.portalLayer = tag.getInt("portalLayer");
        updatePortalBB();
        super.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inv", inventory.serializeNBT());
        pTag.put("ecto", ectoStorage.serialize());
        pTag.putBoolean("isActive", isActive);
        pTag.putInt("portalLayer", portalLayer);
        super.saveAdditional(pTag);
    }

    ////////////////////////////////////////////////
    //// Syncing data between client and server ////
    ////////////////////////////////////////////////

    //TODO - This is currently probably more heavyweight than needed, syncing everything. Come back later and make in leaner.

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        tag.put("inv", inventory.serializeNBT());
        tag.put("ecto", ectoStorage.serialize());
        tag.putBoolean("isActive", isActive);
        tag.putInt("portalLayer", portalLayer);

        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.inventory.deserializeNBT(tag.getCompound("inv"));
        this.ectoStorage.deserialize(tag.getCompound("ecto"));
        this.isActive = tag.getBoolean("isActive");
        this.portalLayer = tag.getInt("portalLayer");
        updatePortalBB();
    }

    ///////////////////////////////////////
    //// Helpers for isValidMultiblock ////
    ///////////////////////////////////////

    private Optional<Integer> getCagesLevel(int maxHeight) {
        for(int i = 1; i <= maxHeight; i++) {
            if(isValidMultiblockCagesLayer(i)) return Optional.of(i);
        }
        return Optional.empty();
    }

    private Optional<Integer> getPortalLevel(int maxDepth, Block portalFrame) {
        for(int i = 1; i <= maxDepth; i++) {
            if(!isValidMultiblockPrePortalLayer(i)) return Optional.empty(); // NB: this works because isValidPortalLayer implies isValidPrePortalLayer
            if(isValidMultiblockPortalLayer(i, portalFrame)) return Optional.of(i);
        }

        return Optional.empty();

    }

    private boolean isValidMultiblockCagesLayer(int distAbove) {
        Block cage = Registration.SPIRIT_CAGE_BLOCK.get();
        BlockPos center = getBlockPos().above(distAbove);

        // inner 5x5 must be all air
        if(!isFilledHorizontalSquare(level, center, 2, Blocks.AIR)) return false;

        // edges (corners excluded) must be air with one cage in the middle
        // north and south edges:
        for(int z = -2; z <= 2; z++) {
            BlockPos north = center.north(3).east(z);
            BlockPos south = center.south(3).east(z);

            if(z == 0 &&
                    (!level.getBlockState(north).is(cage)
                            || !level.getBlockState(south).is(cage))) {
                return false;

            } else if(z != 0 &&
                    (!level.getBlockState(north).is(Blocks.AIR)
                            || !level.getBlockState(south).is(Blocks.AIR))) {
                return false;
            }
        }

        // east and west edges:
        for(int z = -2; z <= 2; z++) {
            BlockPos east = center.east(3).north(z);
            BlockPos west = center.west(3).north(z);

            if(z == 0 &&
                    (!level.getBlockState(east).is(cage)
                            || !level.getBlockState(west).is(cage))) {
                return false;

            } else if(z != 0 &&
                    (!level.getBlockState(east).is(Blocks.AIR)
                            || !level.getBlockState(west).is(Blocks.AIR))) {
                return false;
            }
        }

        //corners must be cages
        return level.getBlockState    (center.north(3).east(3)).is(cage)
                && level.getBlockState(center.north(3).west(3)).is(cage)
                && level.getBlockState(center.south(3).east(3)).is(cage)
                && level.getBlockState(center.south(3).west(3)).is(cage);

    }

    private boolean isValidMultiblockPrePortalLayer(int distBelow) {
        //must be a 3x3 of air
        return isFilledHorizontalSquare(level, worldPosition.below(distBelow), 1, Blocks.AIR);
    }

    private boolean isValidMultiblockPortalLayer(int distBelow, Block portalFrame) {
        // inner 5x5 must be all air
        if(!isFilledHorizontalSquare(level, worldPosition.below(distBelow), 2, Blocks.AIR)) return false;

        // north+south borders must be the frame material
        for(int z = -2; z <= 2; z++) {
            BlockPos north = this.getBlockPos().north(3).east(z).below(distBelow);
            BlockPos south = this.getBlockPos().south(3).east(z).below(distBelow);
            if(!level.getBlockState(north).is(portalFrame) || !level.getBlockState(south).is(portalFrame)) return false;
        }

        // east+west borders must be the frame material
        for(int x = -2; x <= 2; x++) {
            BlockPos east = this.getBlockPos().north(x).east(3).below(distBelow);
            BlockPos west = this.getBlockPos().north(x).west(3).below(distBelow);
            if(!level.getBlockState(east).is(portalFrame) || !level.getBlockState(west).is(portalFrame)) return false;
        }

        return true;
    }

    private static boolean isFilledHorizontalSquare(Level level, BlockPos center, int radius, Block fill) {
        for(int x = -radius; x <= radius; x++) {
            for(int z = -radius; z <= radius; z++) {
                BlockPos pos = center.north(x).east(z);
                if(!level.getBlockState(pos).is(fill)) return false;
            }
        }
        return true;
    }

    //////////////////////////
    //// Crafting Helpers ////
    //////////////////////////

    private boolean tryCraft(ItemStack item) {
        // If it's an ecto, put it right into storage
        for(MagicType t : MagicType.values()) {
            if(item.is(Registration.ECTO_ITEMS(t).get())) {
                ectoStorage.add(t, 1);
                return true;
            }
        }

        // If not, then put it in the input slot and try a recipe.
        // Return true iff a recipe was sucessfully found and applied.
        inventory.setStackInSlot(0, item);
        return recipeChecker.getRecipeFor(recipeWrapper, level)
                .map((recipe) -> recipe.assemble(recipeWrapper))
                .isPresent();
    }

    private void yeet(Entity e, boolean shouldOmegaYeet) {
        if(shouldOmegaYeet) {
            e.addDeltaMovement(e.getPosition(0).subtract(getBlockPos().getCenter()).normalize().add(0,2,0).scale(4));
        } else {
            e.addDeltaMovement(e.getPosition(0).subtract(getBlockPos().getCenter()).normalize().add(0,1.5,0).scale(2));
        }
    }

    /////////////////////////////
    //// Getters and Setters ////
    /////////////////////////////

    public boolean isActive() {
        return isActive;
    }

    ///////////////////
    //// Animation ////
    ///////////////////

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericIdleController(this));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
