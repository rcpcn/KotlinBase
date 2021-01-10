package club.cafedevelopment.kotlinbase.api.module

import club.cafedevelopment.kotlinbase.api.setting.Setting
import club.cafedevelopment.kotlinbase.api.setting.Setting.Companion.setting
import club.cafedevelopment.kotlinbase.util.ChatUtil
import com.mojang.realmsclient.gui.ChatFormatting
import net.minecraft.client.Minecraft

/**
 * @author Reap
 */
abstract class Module(val description: String, val category: ModuleCategory) {
    val settings = ArrayList<Setting<*>>()

    fun getSettingByName(name: String, ignoreCase: Boolean = true): Setting<*>? {
        settings.forEach { if (it.name.equals(name, ignoreCase)) return it }
        return null
    }

    val id = this.javaClass.simpleName
    var enabled by setting("Enabled", "Decides whether the module is active or not", false)
    var displayName by setting("DisplayName", "The name that will appear in the chat notifications and the array list", id)
    var toggleMessages by setting("ToggleMessage", "Sends a message in chat when toggling the module", false)

    open fun setup() {}
    open fun onEnable() {}
    open fun onDisable() {}

    fun toggle(): Boolean { if (enabled) disable() else enable(); return enabled }

    fun enable() {
        if (enabled) return
        enabled = true
        onEnable()
        if (toggleMessages) ChatUtil.sendInfoMessage("${ChatFormatting.GREEN} Enabled ${ChatFormatting.DARK_PURPLE}$displayName")
    }

    fun disable() {
        if (!enabled) return
        enabled = false
        onDisable()
        if (toggleMessages) ChatUtil.sendInfoMessage("${ChatFormatting.RED} Disabled ${ChatFormatting.DARK_PURPLE}$displayName")
    }

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.CLASS)
    annotation class Disabled

    companion object {
        val mc = Minecraft.getMinecraft()
    }
}