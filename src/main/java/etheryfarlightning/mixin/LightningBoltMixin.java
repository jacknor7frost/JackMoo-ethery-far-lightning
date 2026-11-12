package etheryfarlightning.mixin;

import etheryfarlightning.EtheryConfig;
import net.minecraft.world.entity.LightningBolt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightningBolt.class)
public class LightningBoltMixin {

    @Inject(method = "shouldRenderAtSqrDistance", at = @At("HEAD"), cancellable = true)
    private void alwaysRenderFromFar(double distance, CallbackInfoReturnable<Boolean> cir) {
        if (!EtheryConfig.ENABLED.get()) {
            cir.setReturnValue(false);
            return;
        }
        double maxDist = EtheryConfig.MAX_DISTANCE.get();
        if (maxDist <= 0.0) {
            cir.setReturnValue(true);
        } else {
            cir.setReturnValue(distance < maxDist * maxDist);
        }
    }
}   