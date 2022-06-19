import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.experimental.xor

private const val INPUT_ERROR = "Can't read input file!"
private const val PASSWORD_ERROR = "Password can't be empty!"
private val END = getBits("\u0000\u0000\u0003")

fun main() {
    var command = getCommand()

    while (command != "exit") {
        runCommand(command)
        command = getCommand()
    }
    println("Bye!")
}

private fun getCommand() = getString("Task (hide, show, exit):")

private fun runCommand(command: String) {
    when (command) {
        "hide" -> hide()
        "show" -> show()
        else -> "Wrong task: $command"
    }.let { println(it) }
}

private fun hide(): String {
    val input = getFile("Input")
    val output = getFile("Output")
    val userMessage = getString("Message to hide:").let { it.ifEmpty { null } } ?: return "Message can't be empty!"
    val password = getPassword() ?: return PASSWORD_ERROR
    val image = getBufferedImage(input) ?: return INPUT_ERROR
    val enoughSpace = image.width * image.height >= userMessage.length * 8 + END.size
    val notLargeEnough = "The input image is not large enough to hold this message."
    val message = if (enoughSpace) "Message saved in ${output.name} image." else return notLargeEnough
    val bits = transformBits(getBits(userMessage), password) + END
    var index = 0

    start@ for (y in 0 until image.height) {
        for (x in 0 until image.width) {
            if (index == bits.size) break@start
            val bit = bits[index++].toInt()
            val color = Color(image.getRGB(x, y)).let { Color(it.red, it.green, it.blue.and(254).or(bit)) }
            image.setRGB(x, y, color.rgb)
        }
    }
    return if (saveImage(image, output)) message else "Can't write to output file!"
}

private fun show(): String {
    val input = getFile("Input")
    val password = getPassword() ?: return PASSWORD_ERROR
    val image = getBufferedImage(input) ?: return INPUT_ERROR
    val message = mutableListOf<Byte>()

    for (y in 0 until image.height) {
        for (x in 0 until image.width) {
            message.add((Color(image.getRGB(x, y)).blue % 2).toByte())
            if (message.size >= END.size && message.size % 8 == 0 && message.takeLast(END.size) == END) {
                return "Message:\n" + decodeMessage(message.dropLast(END.size), password)
            }
        }
    }
    return "No message was hidden!"
}

private fun transformBits(message: List<Byte>, password: String) = getBits(password).let { keyBits ->
    message.mapIndexed { index, byte -> byte xor keyBits[index % keyBits.size] }
}

private fun getBits(message: String) = message.map { it.code }.map {
    it.toString(2).padStart(8, '0').map { char -> char.digitToInt().toByte() }
}.flatten()

private fun decodeMessage(message: List<Byte>, password: String) =
    transformBits(message, password).joinToString("").chunked(8).map { it.toInt(2).toChar() }.joinToString("")

private fun saveImage(image: BufferedImage, file: File): Boolean {
    return try {
        ImageIO.write(image, "png", file)
    } catch (e: Exception) {
        false
    }
}

private fun getBufferedImage(file: File): BufferedImage? {
    return try {
        ImageIO.read(file)
    } catch (e: Exception) {
        null
    }
}

private fun getPassword() = getString("Password:").let { it.ifEmpty { null } }

private fun getFile(type: String) = File(getString("$type image file:"))

private fun getString(message: String): String {
    println(message)
    return readln()
}