package etheryfarlightning;

import net.minecraftforge.network.NetworkEvent;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Supplier;

public class ThunderFlashPacket {
    public final double x, y, z;

    public static final Queue<ThunderFlashPacket> pendingStrikes = new ConcurrentLinkedQueue<>();

    public static int flashTicks = 0;
    public static final int FLASH_DURATION = 10;

    public ThunderFlashPacket(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void encode(ThunderFlashPacket pkt, FriendlyByteBuf buf) {
        buf.writeDouble(pkt.x);
        buf.writeDouble(pkt.y);
        buf.writeDouble(pkt.z);
    }

    public static ThunderFlashPacket decode(FriendlyByteBuf buf) {
        return new ThunderFlashPacket(buf.readDouble(), buf.readDouble(), buf.readDouble());
    }

    public static void handle(ThunderFlashPacket pkt, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            pendingStrikes.add(pkt);
        });
        ctx.get().setPacketHandled(true);
    }

    public static void tickFlash() {
        if (flashTicks > 0) {
            flashTicks--;
        }
    }

    public static float getFlashIntensity() {
        if (flashTicks <= 0)
            return 0.0f;
        return flashTicks / (float) FLASH_DURATION;
    }
}
