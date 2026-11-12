package etheryfarlightning;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import java.util.Random;

@Mod.EventBusSubscriber(modid = EtheryFarlightningMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {

    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (ThunderFlashPacket.hasPending) {
            ThunderFlashPacket.hasPending = false;

            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null) return;

            double dx = ThunderFlashPacket.pendingX - mc.player.getX();
            double dy = ThunderFlashPacket.pendingY - mc.player.getY();
            double dz = ThunderFlashPacket.pendingZ - mc.player.getZ();
            double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);

            ThunderFlashPacket.flashTicks = ThunderFlashPacket.FLASH_DURATION;

            float volume = Math.max(0.1f, 1.0f - (float) (dist / 4096.0));
            float pitch = 0.8f + RANDOM.nextFloat() * 0.4f;
            mc.player.level().playSound(
                    mc.player,
                    mc.player.getX(), mc.player.getY(), mc.player.getZ(),
                    SoundEvents.LIGHTNING_BOLT_THUNDER,
                    SoundSource.NEUTRAL,
                    volume,
                    pitch
            );
        }

        ThunderFlashPacket.tickFlash();
    }
}   