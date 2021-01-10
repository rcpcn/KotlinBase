package club.cafedevelopment.kotlinbase.api.module

import club.cafedevelopment.kotlinbase.api.setting.Setting
import org.reflections.Reflections
import kotlin.streams.toList

/**
 * @author Reap
 */
object ModuleManager {
    val modules = ArrayList<Module>()

    fun setup() {
        modules.addAll(Reflections("club.cafedevelopment.kotlinbase.impl.module").getSubTypesOf(Module::class.java) // TODO change Reflections prefix to your own package if you change it.
                .stream()
                .filter { !it.isAnnotationPresent(Module.Disabled::class.java) }
                .map { it.getField("INSTANCE").get(this) as Module }
                .toList())

        modules.forEach {
            it.settings.addAll(it.javaClass.declaredFields
                    .filter { it1 ->
                        it1.type.isAssignableFrom(Setting::class.java)
                    }.map { it1 ->
                        val wasAccessible = it1.isAccessible
                        it1.isAccessible = true
                        val returning = it1.get(it) as Setting<*>
                        it1.isAccessible = wasAccessible
                        returning
                    }.toList())
            println("Module: ${it.id}")
            it.setup()
        }
    }

    fun getEnabledModules(): List<Module> =
            modules.stream().filter { it.enabled }.toList()

    fun getModulesByCategory(category: ModuleCategory): List<Module> =
            modules.stream().filter { it.category == category }.toList()

    fun getModuleById(id: String, ignoreCase: Boolean = true): Module? {
        modules.forEach { if (it.id.equals(id, ignoreCase)) return it }
        return null
    }

    fun moduleExists(id: String, ignoreCase: Boolean = true): Boolean {
        modules.forEach { if (it.id.equals(id, ignoreCase)) return true }
        return false
    }

    fun toggleModule(id: String, ignoreCase: Boolean = true): Boolean {
        modules.forEach { if (it.id.equals(id, ignoreCase)) return it.toggle() }
        return false
    }
}
