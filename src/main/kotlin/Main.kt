import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

private const val INPUT_ERROR = "Can't read input file!"
private val END = listOf(0, 0, 3)

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
    val image = getBufferedImage(input) ?: return INPUT_ERROR
    val userMessage = getBits()
    val enoughSpace = image.width * image.height >= userMessage.length
    val notLargeEnough = "The input image is not large enough to hold this message."
    val message = if (enoughSpace) "Message saved in ${output.name} image." else return notLargeEnough
    val save = { if (saveImage(image, output)) message else "Can't write to output file!" }
    var index = 0

    for (y in 0 until image.height) {
        for (x in 0 until image.width) {
            if (index == userMessage.length) return save()
            val bit = userMessage[index++].digitToInt()
            val color = Color(image.getRGB(x, y)).let { Color(it.red, it.green, it.blue.and(254).or(bit)) }
            image.setRGB(x, y, color.rgb)
        }
    }
    return save()
}

private fun show(): String {
    val input = getFile("Input")
    val image = getBufferedImage(input) ?: return INPUT_ERROR
    var lastThree = listOf(1, 1, 1)
    var lastEight = ""
    var message = ""

    for (y in 0 until image.height) {
        for (x in 0 until image.width) {
            lastEight += Color(image.getRGB(x, y)).blue % 2
            if (lastEight.length == 8) {
                val code = lastEight.toInt(2)
                message += code.toChar()
                lastThree = lastThree.drop(1) + code
                if (lastThree == END) return "Message:\n" + message.dropLast(3)
                lastEight = ""
            }
        }
    }
    return "No message was hidden!"
}

private fun getBits() = (getString("Message to hide:").map { it.code } + END).joinToString("") {
    it.toString(2).padStart(8, '0')
}

private fun getFile(type: String) = File(getString("$type image file:"))

private fun getBufferedImage(file: File): BufferedImage? {
    return try {
        ImageIO.read(file)
    } catch (e: Exception) {
        null
    }
}

private fun saveImage(image: BufferedImage, file: File): Boolean {
    return try {
        ImageIO.write(image, "png", file)
    } catch (e: Exception) {
        false
    }
}

private fun getString(message: String): String {
    println(message)
    return readln()
}