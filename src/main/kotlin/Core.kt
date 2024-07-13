import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.experimental.xor

public  fun transformBits(message: List<Byte>, password: String) = getBits(password).let { keyBits ->
    message.mapIndexed { index, byte -> byte xor keyBits[index % keyBits.size] }
}

public  fun getBufferedImage(file: File): BufferedImage? {
    return try {
        ImageIO.read(file)
    } catch (e: Exception) {
        null
    }
}