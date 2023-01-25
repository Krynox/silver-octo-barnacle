package me.krynox.spectral.entity;


import me.krynox.spectral.setup.Registration;
import me.krynox.spectral.magic.MagicType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

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
        spawner.setEntityId(Registration.SPIRIT_ENTITIES(MagicType.WIND).get(), pLevel, randomSource, getOnPos().offset(0d,0.5d,0d));
    }


    @Override
    public void tick() {
        spawn();

        level.getEntities(this, this.getBoundingBox().inflate(6),
                        (e) -> !(e instanceof LeyRiftEntity || e instanceof SpiritEntity) && e.distanceToSqr(this) < 8)
                .forEach(e -> repelEntity(this, e));

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

    private void spawn() {
        if(!level.isClientSide
            && level.getEntities(this, this.getBoundingBox().inflate(16), (e) -> (e instanceof SpiritEntity))
                    .size() < 5) {
            //spawner.serverTick((ServerLevel) level, getOnPos().offset(0, 0.5d, 0));
        }
        //cant do this any more because we aren't firing server tick every time?
        //spawner.clientTick(level, getOnPos().offset(0, 0.5d, 0));
    }


    private static void repelEntity(Entity rift, Entity entity) {
        Vec3 toRift = entity.position().subtract(rift.position());
        double dist = toRift.length();

        entity.setDeltaMovement(entity.getDeltaMovement().add(toRift.scale(0.09 / dist)));
    }
}
