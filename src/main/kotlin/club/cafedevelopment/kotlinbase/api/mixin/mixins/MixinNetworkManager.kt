package club.cafedevelopment.kotlinbase.api.mixin.mixins

import club.cafedevelopment.kotlinbase.KotlinBase
import club.cafedevelopment.kotlinbase.api.event.events.PacketEvent
import io.netty.channel.ChannelHandlerContext
import me.yagel15637.blitz.modifiers.EventEra
import net.minecraft.network.NetworkManager
import net.minecraft.network.Packet
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

/**
 * @author Reap
 */
@Mixin(NetworkManager::class)
object MixinNetworkManager {
    @Inject(method = ["channelRead0"], at = [At("HEAD")], cancellable = true)
    fun onReadPacket(chc: ChannelHandlerContext, packet: Packet<*>, ci: CallbackInfo) =
            with(PacketEvent.Read(packet, EventEra.PRE)) {
                KotlinBase.EVENT_BUS.dispatch(this)
                if (this.isCancelled) ci.cancel()
            }

    @Inject(method = ["sendPacket(Lnet/minecraft/network/Packet;)V"], at = [At("HEAD")], cancellable = true)
    fun onSendPacket(packet: Packet<*>, ci: CallbackInfo) {
        with (PacketEvent.Write(packet, EventEra.PRE)) {
            KotlinBase.EVENT_BUS.dispatch(this)
            if (this.isCancelled) ci.cancel()
        }
    }
}