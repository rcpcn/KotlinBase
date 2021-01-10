package club.cafedevelopment.kotlinbase.api.module

import club.cafedevelopment.kotlinbase.KotlinBase

/**
 * @author Reap
 */
enum class ModuleCategory(var displayName: String) {
    CHAT("Chat")         ,
    CLIENT(KotlinBase.MOD_NAME)     ,
    COMBAT("Combat")     ,
    EXPLOIT("Exploit")   ,
    HUD("HUD")           ,
    MISC("Misc")         ,
    MOVEMENT("Movement") ,
    PLAYER("Player")     ,
    RENDER("Render")     ,
}
