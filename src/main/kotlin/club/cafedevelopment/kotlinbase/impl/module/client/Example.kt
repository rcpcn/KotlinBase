package club.cafedevelopment.kotlinbase.impl.module.client

import club.cafedevelopment.kotlinbase.api.module.Module
import club.cafedevelopment.kotlinbase.api.module.ModuleCategory
import club.cafedevelopment.kotlinbase.api.event.events.SafeUpdateEvent
import club.cafedevelopment.kotlinbase.util.ChatUtil
import me.yagel15637.blitz.dispatcher.DispatcherEntry

/**
 * @author Reap
 */
object Example : Module("An example.", ModuleCategory.CLIENT) {
    override fun onEnable() =
            ChatUtil.sendInfoMessage("This happens when enabling the module!")

    override fun onDisable() =
            ChatUtil.sendInfoMessage("This happens when disabling the module!")

    @DispatcherEntry
    fun onSafeUpdate(event: SafeUpdateEvent) {
        mc.player.sendChatMessage("HI EVERYONE I AM DUMB AND HAVE AN EXAMPLE MODULE ENABLED.")
        disable()
    }
}