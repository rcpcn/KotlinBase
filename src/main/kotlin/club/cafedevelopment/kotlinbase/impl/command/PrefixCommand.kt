package club.cafedevelopment.kotlinbase.impl.command

import club.cafedevelopment.kotlinbase.KotlinBase
import club.cafedevelopment.kotlinbase.util.ChatUtil
import me.yagel15637.venture.command.AbstractCommand

/**
 * @author Reap
 */
object PrefixCommand : AbstractCommand("Changes the client's prefix.", "prefix <key>", "prefix") {
    override fun execute(args: Array<out String>?) {
        if (args == null || args.isEmpty()) {
            ChatUtil.sendInfoMessage("Not enough arguments for Command Prefix.")
            return
        }

        KotlinBase.commandPrefix = args[0]
    }
}