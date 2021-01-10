package club.cafedevelopment.kotlinbase.api.setting

import club.cafedevelopment.kotlinbase.api.module.Module
import kotlin.reflect.KProperty

/**
 * @author Reap
 */
class Setting<T> private constructor(
        val name: String,
        val description: String = "",
        var value: T,
        var visible: () -> Boolean = { true }
) {
    /**
     * Both #getValue and #setValue are used for delegation in setting declarations:
     *
     * val/var varName by Setting(...)
     */
    operator fun getValue(any: Any, property: KProperty<*>): T = value
    operator fun setValue(any: Any, property: KProperty<*>, newVal: T) { value = newVal }

    companion object {
        fun <T> Module.setting(
                name: String,
                description: String = "",
                value: T,
                visible: () -> Boolean = { true }
        ): Setting<T> =
                Setting(name, description, value, visible).also { settings.add(it) }
    }
}

