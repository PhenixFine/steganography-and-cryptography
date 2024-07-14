import org.apache.commons.io.FilenameUtils
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.File
import javax.imageio.ImageIO

public class ImageRelay(byteArray: ByteArray) {
    private val bytes: ByteArray = byteArray
    private val image: BufferedImage
    private val _height: Int
    val height:Int
        get() = _height
    private val _width: Int
    val width:Int
        get() = _width

    init {
        image = ImageIO.read(ByteArrayInputStream(bytes))
        _width = image.width
        _height = image.height
    }

    public operator fun get(i: Int, j: Int): Pixel {
        require(i in 0 until _width && j in 0 until _height)
        return Pixel(image.getRGB(i, j))
    }

    public operator fun set(i: Int, j: Int, p: Pixel): Unit {
        require(i in 0 until _width && j in 0 until _height)
        image.setRGB(i, j, p.getColorCode())
    }

    public fun export(imagePath: String = "image") {
        val imageType = FilenameUtils.getExtension(imagePath)
        require(imageType in listOf("tiff", "bmp", "gif", "wbmp", "png"))
        val outputfile = File("$imagePath")
        ImageIO.write(image, imageType, outputfile)
    }

    public fun copy(): ImageRelay = ImageRelay(bytes)
}