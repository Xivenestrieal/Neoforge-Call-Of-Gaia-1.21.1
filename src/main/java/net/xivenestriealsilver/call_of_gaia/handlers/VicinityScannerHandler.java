package net.xivenestriealsilver.call_of_gaia.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.function.Predicate;

public class VicinityScannerHandler {

    public static class Request {
        private final Level level;
        private final BlockPos center;
        private int radius = 5;
        
        // Block scanning settings
        private Predicate<BlockState> blockFilter = state -> false;
        private int blockCountCap = Integer.MAX_VALUE;
        
        // Entity scanning settings
        private Predicate<Entity> entityFilter = entity -> false;

        public Request(Level level, BlockPos center) {
            this.level = level;
            this.center = center;
        }

        public Request withRadius(int radius) {
            this.radius = radius;
            return this;
        }

        public Request filterBlocks(Predicate<BlockState> filter, int cap) {
            this.blockFilter = filter;
            this.blockCountCap = cap;
            return this;
        }

        public Request filterEntities(Predicate<Entity> filter) {
            this.entityFilter = filter;
            return this;
        }

        //  Executes the scan and returns the combined energy score.

        public ScanResult execute() {
            int totalBlockEnergy = 0;
            int countedBlocks = 0;

            BlockPos minCorner = center.offset(-radius, -radius, -radius);
            BlockPos maxCorner = center.offset(radius, radius, radius);

            // 1. Scan Blocks with Cap
            for (BlockPos currentPos : BlockPos.betweenClosed(minCorner, maxCorner)) {
                BlockState state = level.getBlockState(currentPos);
                if (blockFilter.test(state)) {
                    countedBlocks++;
                    if (totalBlockEnergy < blockCountCap) {
                        totalBlockEnergy++;
                    }
                }
            }

            // 2. Scan Entities within the exact same bounding box area
            AABB boundingBox = AABB.encapsulatingFullBlocks(minCorner, maxCorner);
            List<Entity> nearbyEntities = level.getEntitiesOfClass(Entity.class, boundingBox, entityFilter);
            int entityEnergy = nearbyEntities.size();

            return new ScanResult(totalBlockEnergy, countedBlocks, entityEnergy);
        }
    }

    // A structured return object so your item knows exactly where the power came from
    public record ScanResult(int cappedBlockEnergy, int totalBlocksFound, int entityEnergy) {
        public int getTotalPower() {
            return cappedBlockEnergy + entityEnergy;
        }
    }
}
