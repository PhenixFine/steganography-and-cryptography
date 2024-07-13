import java.io.File

public fun getPassword() = getString("Password:").let { it.ifEmpty { null } }
public fun getFile(type: String) = File(getString("$type image file:"))
public fun getString(message: String): String {
    println(message)
    return readln()
}
public  fun getBits(message: String) = message.map { it.code }.map {
    it.toString(2).padStart(8, '0').map { char -> char.digitToInt().toByte() }
}.flatten()