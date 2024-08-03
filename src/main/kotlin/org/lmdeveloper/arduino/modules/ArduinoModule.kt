package org.lmdeveloper.arduino.modules

import com.fazecast.jSerialComm.SerialPort
import java.io.OutputStream


class ArduinoModule(private val port: String, private val taxTransfer: Int) {
    private lateinit var serialPort: SerialPort
    private lateinit var serialOut: OutputStream

    fun init() {
        serialPort = SerialPort.getCommPort(port)
        serialPort.openPort()
        serialPort.baudRate = taxTransfer
        serialOut = serialPort.outputStream
    }

    fun write(char: String) {
        serialOut.write(char.toByteArray())
    }

    fun close() {
        serialPort.closePort()
    }
}
