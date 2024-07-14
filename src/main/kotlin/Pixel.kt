public class Pixel(colorCode: Int, private val numberOfChannels: Int = 3) {
   private var alpha: Byte
    private var r: Byte;
    private var g: Byte;
    private var b: Byte

    init {
        b = (colorCode and 0xff).toByte()
        g = (colorCode and 0xff00 shr 8).toByte()
        r = (colorCode and 0xff0000 shr 16).toByte()

        alpha = (colorCode and 0xff000000.toInt() ushr 32).toByte()
    }

    var Alpha: Byte
        get() = alpha
        set(value: Byte) {
            require(value.isByte())
            alpha = value
        }

    var R: Byte
        get() = r
        set(value: Byte) {
            require(value.isByte())
            r = value
        }


    var G: Byte
        get() = g
        set(value: Byte) {
            require(value.isByte())
            g = value
        }


    var B: Byte
        get() = b
        set(value: Byte) {
            require(value.isByte())
            b = value
        }


    operator fun set(index: Int, value: Byte) {
        require(index in 0 until numberOfChannels)
        require(value.isByte())

        when (index) {
            0 -> R = value
            1 -> G = value
            2 -> B = value
            3 -> Alpha = value
        }
    }

    operator fun get(index: Int): Byte {
        require(index in 0 until numberOfChannels)

        when (index) {
            0 -> return R
            1 -> return G
            2 -> return B
            3 -> return Alpha
        }

        throw IllegalArgumentException()
    }

    fun Byte.isByte() = this in Byte.MIN_VALUE..Byte.MAX_VALUE

//    fun getColorCode(): Int {
//        val hex = String.format("%02x%02x%02x%02x", alpha, r, g, b)
//        return hex.toLong(16).toInt()
//    }
}

//TODO TEST for KMP
fun Pixel.getColorCode(): Int {
    val hex = String.format("%02x%02x%02x%02x", Alpha, R, G, B)
    return hex.toLong(16).toInt()
}