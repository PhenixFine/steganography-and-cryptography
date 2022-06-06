import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

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
        "show" -> "Obtaining message from image."
        else -> "Wrong task: $command"
    }.let { println(it) }
}

private fun hide(): String {
    val input = getFile("Input")
    val output = getFile("Output")
    val image = getBufferedImage(input) ?: return "Can't read input file!"
    val inPath = "Input image: ${input.invariantSeparatorsPath}\n"
    val outPath = "Output image: ${output.invariantSeparatorsPath}\n"
    val message = inPath + outPath + "Image ${output.name} is saved."

    for (i in 0 until image.width) {
        for (j in 0 until image.height) {
            image.setRGB(i, j, image.getRGB(i, j) or 0x010101)
        }
    }
    return if (saveImage(image, output)) message else "Can't write to output file!"
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