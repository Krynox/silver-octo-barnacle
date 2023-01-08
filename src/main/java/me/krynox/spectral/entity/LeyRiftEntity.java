package me.krynox.spectral.entity;


import me.krynox.spectral.setup.Registration;
import me.krynox.spectral.spell.MagicType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;

public class LeyRiftEntity extends Entity {

    private MagicType type;
    private RandomSource randomSource = RandomSource.create();

    private final BaseSpawner spawner = new BaseSpawner() {
        @Override
        public void broadcastEvent(Level pLevel, BlockPos pPos, int pEventId) {
            //SpawnerBlockEntity fires a pLevel.blockEvent here, we can probably just do nothing?
        }
    };

    public LeyRiftEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        type = MagicType.FIRE;
        spawner.setEntityId(Registration.WIND_SPIRIT_ENTITY.get(), pLevel, randomSource, getOnPos().offset(0d,0.5d,0d));
    }


    @Override
    public void tick() {
        if(level.isClientSide) {
            spawner.clientTick(level, getOnPos().offset(0, 0.5d, 0));
        } else {
            spawner.serverTick((ServerLevel) level, getOnPos().offset(0, 0.5d, 0));
        }

        super.tick();
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        this.spawner.load(level, getOnPos(), tag);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        this.spawner.save(tag);

    }
}
