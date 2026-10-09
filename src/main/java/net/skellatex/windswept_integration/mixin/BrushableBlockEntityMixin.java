package net.skellatex.windswept_integration.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.skellatex.windswept_integration.registry.WIItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.ThreadLocalRandom;

@Mixin(BrushableBlockEntity.class)
public abstract class BrushableBlockEntityMixin extends BlockEntity {

    protected BrushableBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Inject(method = "brush", at = @At("RETURN"))
    private void onBrushComplete(long worldTime, Player player, Direction direction,
                                 CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValueZ()) return;

        ItemStack hands = player.getUseItem();
        if (hands.getItem() != WIItems.ELDER_BRUSH.get()) return;

        ServerLevel level = (ServerLevel) player.level();
        BlockPos pos = this.getBlockPos();

        int xp = ThreadLocalRandom.current().nextInt(1, 3);

        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        level.addFreshEntity(new ExperienceOrb(level, x, y, z, xp));
    }
}
