package org.lmdeveloper.arduino.listeners

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.data.FaceAttachable
import org.bukkit.block.data.type.Switch
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.lmdeveloper.arduino.Arduino.Companion.arduinoController

class ButtonPressEvent: Listener {

    @EventHandler
    fun onButtonPress(event: PlayerInteractEvent) {
        if (event.hand != EquipmentSlot.HAND) return

        if (event.action == Action.RIGHT_CLICK_BLOCK && event.clickedBlock?.type == Material.STONE_BUTTON) {
            val button = event.clickedBlock!!
            val attachedBlock = getAttachedButton(button)

            when (attachedBlock.type) {
                Material.RED_WOOL -> {
                    sendSignal("LED_RED", 4, event.player)
                }
                Material.YELLOW_WOOL -> {
                    sendSignal("LED_YELLOW", 3, event.player)
                }
                Material.LIME_WOOL -> {
                    sendSignal("LED_GREEN", 2, event.player)
                }
                else -> {}
            }
        }
    }

    private fun sendSignal(value: String, pin: Int, player: Player) {
        arduinoController.write(value)
        player.sendMessage("§aSignal has been sent to PIN $pin")
    }

    private fun getAttachedButton(button: Block): Block {
        val buttonData: Switch = button.blockData as Switch
        val facing = when (buttonData.attachedFace) {
            FaceAttachable.AttachedFace.FLOOR -> BlockFace.DOWN
            FaceAttachable.AttachedFace.CEILING -> BlockFace.UP
            FaceAttachable.AttachedFace.WALL -> buttonData.facing.oppositeFace
        }

        return button.getRelative(facing)
    }
}