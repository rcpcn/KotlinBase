package club.cafedevelopment.kotlinbase.api.event.events

import me.yagel15637.blitz.event.Event
import me.yagel15637.blitz.modifiers.EventEra
import net.minecraft.network.Packet

/**
 * @author Reap
 */
abstract class PacketEvent(val packet: Packet<*>, era: EventEra) : Event(era) {
    class Write(packet: Packet<*>, era: EventEra) : PacketEvent(packet, era)
    class Read(packet: Packet<*>, era: EventEra) : PacketEvent(packet, era)
}