package me.krynox.spectral.magic;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

// Class containing utility functions for building spells compositionally.
public class SpellUtils {

    ///////////////
    // Targeting //
    ///////////////

    /**
     * Perform a raycast and return a list of target entities.
     * This is desgined for ease of use only, and is not optimised.
     * As such, it is intended for use with lightweight filters and reasonably short lengths.
     *
     * @param caster The entity casting the spell. Excluded from the output. The ray originates from this entity's eye position.
     * @param dir A unit direction vector.
     * @param len The length of the ray.
     * @param filter A predicate on entities which evaluates to true for valid targets, and false for entities which should be ignored.
     * @return A list of all valid entites which intersect the ray, from nearest to furthest.
     */
    public static List<Entity> entitiesInRay(Level level, LivingEntity caster, float partialTicks, Vec3 dir, float len, Predicate<Entity> filter) {
        Vec3 origin = caster.getEyePosition(partialTicks);
        Vec3 endPoint = origin.add(dir.scale(len));

        AABB aabb = new AABB(origin, endPoint);

        // initially look at everything in the bounding box which satisfies the filter.
        List<Entity> output = level.getEntities(caster, aabb, filter);

        // cull everything that doesn't intersect with the ray.
        output.removeIf((e) -> e.getBoundingBox().intersects(origin, endPoint));

        return output;
    }

    // raycast to blockpos

    // cleave/cone/PBAoE

    // projectile

}
