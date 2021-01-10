package club.cafedevelopment.kotlinbase

import club.cafedevelopment.kotlinbase.api.mixin.MixinLoader
import club.cafedevelopment.kotlinbase.api.module.ModuleManager
import club.cafedevelopment.kotlinbase.api.event.EventProcessor
import club.cafedevelopment.kotlinbase.util.FileUtil
import me.yagel15637.blitz.dispatcher.EventDispatcher
import me.yagel15637.venture.command.ICommand
import me.yagel15637.venture.manager.CommandManager
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.reflections.Reflections
import java.lang.Exception
import kotlin.streams.toList

/**
 * @author Reap
 */
@Mod(name = KotlinBase.MOD_NAME, modid = KotlinBase.MOD_ID, version = KotlinBase.MOD_VERSION)
class KotlinBase {
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        MixinLoader()
        EventProcessor.setup()
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        ModuleManager.setup()
        commandSetup()
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {

    }

    fun commandSetup() {
        Reflections("club.cafedevelopment.kotlinbase.impl.command").getSubTypesOf(ICommand::class.java)
                .stream()
                .map {
                    try {
                        it.getDeclaredField("INSTANCE").get(it) as ICommand
                    } catch (e: Exception) {
                        null
                    }
                }
                .filter { it != null }
                .map { it!! }
                .toList()
                .forEach { CommandManager.addCommands(it) }

        with(FileUtil.getTxtFileContent("prefix.txt")) {
            if (isBlank() || isEmpty()) return@with
            commandPrefix = this[0].toString()
        }
    }

    companion object {
        const val MOD_NAME = "KotlinBase"
        const val MOD_ID = "kotlinbase"
        const val MOD_VERSION = "1.0.0"

        val EVENT_BUS = EventDispatcher()

        var commandPrefix = ";"
    }
}