package rusty_badlands.blocks;

//public class RustyCoreBlockEntity extends BlockEntity {
//    public RustyCoreBlockEntity(BlockPos pos, BlockState state) {
//        super(ModBlockEntities.RUSTY_CORE_BLOCK_ENTITY, pos, state);
//    }
//    public static void tick(Level level, BlockPos blockPos, BlockState blockState, RustyCoreBlockEntity entity) {
//        if (level.getGameTime() % 30 == 0 && level instanceof ServerLevel serverLevel) {
//            if (level.getBlockState(blockPos.above()).getBlock() == Blocks.WATER) {
//                Vec3 startPos = Vec3.atCenterOf(blockPos.above());
//                TaskScheduler.schedule(run -> {
//                    Vec3 pos = startPos.add(0, 0.2 * run, 0);
//                    serverLevel.sendParticles(
//                        new DustParticleOptions(0xaa3a14, level.getRandom().nextFloat() * 2 + 2.0f),
//                        pos.x, pos.y, pos.z, 0.0, 2.0, 0.0);
//                }, 2, 20, true, null);
//            }
//        }
//    }
//}
