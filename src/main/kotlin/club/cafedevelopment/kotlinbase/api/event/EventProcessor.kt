package club.cafedevelopment.kotlinbase.api.event

import club.cafedevelopment.kotlinbase.KotlinBase
import club.cafedevelopment.kotlinbase.api.event.events.*
import me.yagel15637.blitz.modifiers.EventEra
import me.yagel15637.venture.manager.CommandManager
import net.minecraft.client.Minecraft
import net.minecraftforge.client.event.ClientChatEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

/**
 * @author Reap
 */
object EventProcessor {
    private val mc = Minecraft.getMinecraft()

    fun setup() {
        MinecraftForge.EVENT_BUS.register(this)
        KotlinBase.EVENT_BUS.register(this)
    }

    @SubscribeEvent
    fun onPlayerTick(event: TickEvent.PlayerTickEvent) {
        KotlinBase.EVENT_BUS.dispatch(UpdateEvent(EventEra.PRE))

        if (mc.world != null && mc.player != null)
            KotlinBase.EVENT_BUS.dispatch(SafeUpdateEvent(EventEra.PRE))
    }

    @SubscribeEvent
    fun onChatMessage(event: ClientChatEvent) {
        if (event.message.startsWith(KotlinBase.commandPrefix)) {
            CommandManager.parseCommand(event.message.replaceFirst(KotlinBase.commandPrefix, ""))
            event.isCanceled = true
            mc.ingameGUI.chatGUI.addToSentMessages(event.message)
        } else {

        }
    }
}