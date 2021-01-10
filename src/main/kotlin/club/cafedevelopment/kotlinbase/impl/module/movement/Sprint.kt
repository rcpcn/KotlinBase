package club.cafedevelopment.kotlinbase.impl.module.movement

import club.cafedevelopment.kotlinbase.api.event.events.SafeUpdateEvent
import club.cafedevelopment.kotlinbase.api.module.Module
import club.cafedevelopment.kotlinbase.api.module.ModuleCategory
import club.cafedevelopment.kotlinbase.api.setting.Setting.Companion.setting
import me.yagel15637.blitz.dispatcher.DispatcherEntry

/**
 * @author Reap
 */
object Sprint : Module("", ModuleCategory.MOVEMENT) {
    var mode by setting("Mode", "What mode to run in.", SprintMode.Rage)

    @DispatcherEntry
    fun onSafeTick(event: SafeUpdateEvent) { mc.player.isSprinting = mode.shouldSprint() }

    enum class SprintMode(val shouldSprint: () -> Boolean) {
        Rage({
            mc.gameSettings.keyBindForward.isKeyDown ||
                    mc.gameSettings.keyBindBack.isKeyDown ||
                    mc.gameSettings.keyBindLeft.isKeyDown ||
                    mc.gameSettings.keyBindRight.isKeyDown
        }),
        Legit({ mc.gameSettings.keyBindForward.isKeyDown }),
        Continuous({ true });
    }
}
