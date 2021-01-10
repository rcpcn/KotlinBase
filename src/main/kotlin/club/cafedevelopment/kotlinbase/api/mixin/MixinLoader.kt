package club.cafedevelopment.kotlinbase.api.mixin

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
import org.spongepowered.asm.launch.MixinBootstrap
import org.spongepowered.asm.mixin.Mixins

/**
 * @author Reap
 */
class MixinLoader : IFMLLoadingPlugin {
    init {
        MixinBootstrap.init()
        Mixins.addConfiguration("mixins.kotlinbase.json")
    }

    override fun injectData(map: MutableMap<String, Any>?) {}
    override fun getASMTransformerClass(): Array<String>? = null
    override fun getModContainerClass(): String? = null
    override fun getSetupClass(): String? = null
    override fun getAccessTransformerClass(): String? = null
}