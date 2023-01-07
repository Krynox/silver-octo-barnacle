package me.krynox.spectral.block.entity;

import me.krynox.spectral.capability.EctoHandlerImpl;
import me.krynox.spectral.capability.IEctoHandler;
import me.krynox.spectral.capability.SpectralCapabilities;
import me.krynox.spectral.crafting.EctoInvRecipeWrapper;
import me.krynox.spectral.crafting.SpectralForgeRecipe;
import me.krynox.spectral.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SpectralForgeBE extends BlockEntity {
    private final ItemStackHandler inventory;
    private final IEctoHandler ectoStorage;
    //cached maybes to return for the getCapability calls
    private final LazyOptional<IItemHandler> maybeInventory;
    private final LazyOptional<IEctoHandler> maybeEctoStorage;
    //also cache the recipe wrapper instance
    private final EctoInvRecipeWrapper recipeWrapper;
    private final RecipeManager.CachedCheck<EctoInvRecipeWrapper, SpectralForgeRecipe> recipeChecker;

    public SpectralForgeBE(BlockPos pPos, BlockState pBlockState) {
        super(Registration.SPECTRAL_FORGE_BE.get(), pPos, pBlockState);

        this.inventory = new ItemStackHandler(21); // first slot is the central item, the others are the infusion items
        this.ectoStorage = new EctoHandlerImpl();
        this.maybeInventory = LazyOptional.of(() -> inventory);
        this.maybeEctoStorage = LazyOptional.of(() -> ectoStorage);
        this.recipeChecker = RecipeManager.createCheck(Registration.SPECTRAL_FORGE_RECIPETYPE.get());
        this.recipeWrapper = new EctoInvRecipeWrapper(inventory, ectoStorage);
    }

    /**
     * Attempt to craft an item using the current state of the forge.
     */
    public Optional<ItemStack> doCraft(Level level) {
        return recipeChecker
                .getRecipeFor(recipeWrapper, level)
                .map((recipe) -> recipe.assemble(recipeWrapper));
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
        super.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inv", inventory.serializeNBT());
        pTag.put("ecto", ectoStorage.serialize());
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

        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.inventory.deserializeNBT(tag.getCompound("inv"));
        this.ectoStorage.deserialize(tag.getCompound("ecto"));
    }
}
