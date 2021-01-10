package club.cafedevelopment.kotlinbase.util

import com.mojang.realmsclient.gui.ChatFormatting
import net.minecraft.client.Minecraft
import net.minecraft.util.text.TextComponentString

/**
 * @author Reap
 */
object ChatUtil {
    private val mc = Minecraft.getMinecraft()

    val prefix = "${ChatFormatting.BLUE}Kotlin Base by CafeDevelopment >${ChatFormatting.WHITE}"

    fun sendInfoMessage(text: String) = mc.player.sendMessage(TextComponentString("$prefix $text"))
}