import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

public fun hide(): String {
    val input = getFile("Input")
    val output = getFile("Output")
    val userMessage = getString("Message to hide:").let { it.ifEmpty { null } } ?: return "Message can't be empty!"
    val password = getPassword() ?: return PASSWORD_ERROR
    val image = getBufferedImage(input) ?: return INPUT_ERROR
    val imageinfo = input.readBytes().toBitmapInfo() ?: return INPUT_ERROR
    val enoughSpace = image.width * image.height >= userMessage.length * 8 + END.size
    val enoughSpace_info = imageinfo.width * imageinfo.height >= userMessage.length * 8 + END.size
    val notLargeEnough = "The input image is not large enough to hold this message."
    val message = if (enoughSpace) "Message saved in ${output.name} image." else return notLargeEnough
    val message_info = if (enoughSpace_info) "Message saved in ${output.name} image." else return notLargeEnough
    val bits = transformBits(getBits(userMessage), password) + END
    var index = 0

    start@ for (y in 0 until image.height) {
        for (x in 0 until image.width) {
            if (index == bits.size) break@start
            val bit = bits[index++].toInt()
            val color = Color(image.getRGB(x, y)).let {
                Color(
                    it.red,
                    it.green,
                    it.blue.and(254).or(bit)
                )
            }
            image.setRGB(x, y, color.rgb)
        }
    }

//    start@ for (y in 0 until imageinfo.height) {
//        for (x in 0 until imageinfo.width) {
//            if (index == bits.size) break@start
//            val bit = bits[index++].toInt()
//            val color = Color(imageinfo.getRGB(x, y)).let {
//                Color(
//                    it.red,
//                    it.green,
//                    it.blue.and(254).or(bit)
//                )
//            }
//            imageinfo.setRGB(x, y, color.rgb)
//        }
//    }
    return if (saveImage(image, output)) message else "Can't write to output file!"
}

public  fun saveImage(image: BufferedImage, file: File): Boolean {
    return try {
        ImageIO.write(image, "png", file)
    } catch (e: Exception) {
        false
    }
}

public  fun show(): String {
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

public  fun decodeMessage(message: List<Byte>, password: String) =
    transformBits(message, password).joinToString("").chunked(8).map { it.toInt(2).toChar() }.joinToString("")

