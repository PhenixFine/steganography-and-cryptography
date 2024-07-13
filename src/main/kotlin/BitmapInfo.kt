public data class BitmapInfo(val width: Int, val height: Int, private val rgbValues: IntArray) {
//FIXME  rgbValues: IntArray not full support !! because BufferedImage use  "ColorModel" for RGB information
//    fun getRGB(startX: Int, startY: Int, w: Int, h: Int, rgbArray: IntArray, offset: Int, scansize: Int):Int {
//        if (rgbArray == null) {
//            throw NullPointerException("rgbArray cannot be null")
//        }
//        if (startX < 0 || startY < 0 || w < 0 || h < 0 ||
//            (startX + w) > width || (startY + h) > height ||
//            offset < 0 || (offset + w) > rgbArray.size || scansize < 0
//        ) {
//            throw IllegalArgumentException("Invalid arguments")
//        }
//
//        var off = offset
//        var y = startY
//        while (y < startY + h) {
//            var x = startX
//            while (x < startX + w) {
//                rgbArray[off++] = rgbValues[y * width + x]
//                x++
//            }
//            off += scansize - w
//            y++
//        }
//        if (x < 0 || y < 0 || x >= width || y >= height) {
//            throwIllegalArgumentException("Invalid coordinates")
//        }
//        return rgbValues[y * width + x]
//    }

    fun setRGB(x: Int, y: Int, rgb: Int) {
        // 檢查參數的有效性，例如邊界檢查
        // ...
        // 將 rgb 值設定到 rgbValues 中對應的位置
        val index = y * width + x
        rgbValues[index] = rgb
    }
}