package org.lmdeveloper.arduino

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.lmdeveloper.arduino.listeners.ButtonPressEvent
import org.lmdeveloper.arduino.modules.ArduinoModule

class Arduino: JavaPlugin() {

    companion object {
        private const val port = "/dev/ttyACM0"
        val arduinoController = ArduinoModule(port, 9600)
    }

    override fun onEnable() {
        arduinoController.init()
        Bukkit.getConsoleSender().sendMessage("§aArduino plugin Started. (Running in $port)")
        Bukkit.getPluginManager().registerEvents(ButtonPressEvent(), this)
    }

    override fun onDisable() {
        arduinoController.close()
    }
}