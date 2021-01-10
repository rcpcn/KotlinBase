package club.cafedevelopment.kotlinbase.util

import club.cafedevelopment.kotlinbase.KotlinBase
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.stream.JsonReader
import net.minecraft.client.Minecraft
import java.io.File
import java.io.FileReader
import java.util.*

object FileUtil {
    val basePath = "${Minecraft.getMinecraft().gameDir.absolutePath}\\${KotlinBase.MOD_NAME}\\"

    fun getTxtFileContent(path: String): String {
        val file = File("$basePath$path")
        if (!file.exists()) return ""

        var text = ""
        with(Scanner(file)) { while (this.hasNext()) text += this.nextLine() }
        return text
    }

    fun <T : JsonElement> getJsonElement(path: String): T? {
        val file = File("$basePath$path")
        if (!file.exists()) return null

        return JsonParser().parse(JsonReader(FileReader(file))) as T
    }
}