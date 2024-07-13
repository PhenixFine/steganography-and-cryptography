import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.File
import javax.imageio.ImageIO

public fun getPassword() = getString("Password:").let { it.ifEmpty { null } }
public fun getFile(type: String) = File(getString("$type image file:"))
public fun getString(message: String): String {
    println(message)
    return readln()
}
public  fun getBits(message: String) = message.map { it.code }.map {
    it.toString(2).padStart(8, '0').map { char -> char.digitToInt().toByte() }
}.flatten()

public fun fileToBufferedImage(file: File): BufferedImage? {
    return try {
        val byteArray = file.readBytes()
        val inputStream = ByteArrayInputStream(byteArray)
        ImageIO.read(inputStream)
    } catch (e: Exception) {
        null // 處理可能的例外，例如檔案不存在或無法讀取
    }
}

public fun fileByteArrayToBufferedImage(byteArray: ByteArray): BufferedImage? {
    return try {
        val inputStream = ByteArrayInputStream(byteArray)
        ImageIO.read(inputStream)
    } catch (e: Exception) {
        null
    }
}

public fun bufferedImageToBitmapInfo(bufferedImage: BufferedImage): BitmapInfo {
    val width = bufferedImage.width
    val height = bufferedImage.height
    val rgbValues = IntArray(width * height)
    bufferedImage.getRGB(0, 0, width, height, rgbValues, 0, width)
    return BitmapInfo(width, height, rgbValues)
}

public fun BufferedImage.toBitmapInfo(): BitmapInfo {
    val rgbValues = IntArray(width * height)
    getRGB(0, 0, width, height, rgbValues, 0, width)
    return BitmapInfo(width, height, rgbValues)
}

public fun ByteArray.toBufferedImage(): BufferedImage? {
    return try {
        val inputStream = ByteArrayInputStream(this)
        ImageIO.read(inputStream)
//        ImageIO.read(ByteArrayInputStream(this))
    } catch (e: Exception) {
        null // 處理可能的例外，例如格式不支援
    }
}

public fun ByteArray.toBitmapInfo(): BitmapInfo? {
    return try {
        ImageIO.read(ByteArrayInputStream(this))?.let {
            val rgbValues = IntArray(it.width * it.height)
            it.getRGB(0, 0, it.width, it.height, rgbValues, 0, it.width)
            BitmapInfo(it.width, it.height, rgbValues)
        }
    } catch (e: Exception) {
        null // 處理可能的例外，例如格式不支援
    }
}