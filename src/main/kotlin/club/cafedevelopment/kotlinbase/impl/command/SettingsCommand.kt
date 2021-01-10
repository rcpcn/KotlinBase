package club.cafedevelopment.kotlinbase.impl.command

import club.cafedevelopment.kotlinbase.api.module.ModuleManager
import club.cafedevelopment.kotlinbase.util.ChatUtil
import me.yagel15637.venture.command.AbstractCommand

/**
 * @author Reap
 */
object SettingsCommand : AbstractCommand("Shows all the settings for a module", "settings <module>", "settings") {
    override fun execute(args: Array<out String>?) {
        if (args == null || args.isEmpty()) {
            ChatUtil.sendInfoMessage("Not enough arguments for command settings.")
            return
        }

        if (!ModuleManager.moduleExists(args[0])) {
            ChatUtil.sendInfoMessage("Could not find Module: ${args[0]}")
            return
        }

        var message = "All settings for module ${args[0]}"

        ModuleManager.getModuleById(args[0])?.settings?.forEach {
            message += "\n${it.name} : ${it.description} : ${it.value}"
        }

        ChatUtil.sendInfoMessage(message)
    }
}