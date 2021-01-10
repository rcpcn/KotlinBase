package club.cafedevelopment.kotlinbase.util

import net.minecraft.entity.player.EntityPlayer
import java.util.*
import kotlin.collections.ArrayList

object RelationUtil {
    val friends = ArrayList<UUID>()
    val enemies = ArrayList<UUID>()

    fun setup() { /* TODO loading friends/enemies */ }
    fun save() { /* TODO saving friends/enemies */ }

    fun addFriend(player: EntityPlayer, override: Boolean = true) {
        val uuid = player.entityUniqueID
        if (friends.contains(uuid)) return

        if (override) {
            friends.add(uuid)
            enemies.remove(uuid)
        } else {
            if (!enemies.contains(uuid))
                friends.add(uuid)
        }
    }

    fun addEnemy(player: EntityPlayer, override: Boolean = false) {
        val uuid = player.entityUniqueID
        if (enemies.contains(uuid)) return

        if (override) {
            enemies.add(uuid)
            friends.remove(uuid)
        } else {
            if (!friends.contains(uuid))
                enemies.add(uuid)
        }
    }

    fun isFriend(player: EntityPlayer) = friends.contains(player.entityUniqueID)
    fun isEnemy(player: EntityPlayer) = enemies.contains(player.entityUniqueID)
}