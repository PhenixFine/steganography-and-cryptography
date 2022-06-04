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
        "hide" -> "Hiding message in image."
        "show" -> "Obtaining message from image."
        else -> "Wrong task: $command"
    }.let { println(it) }
}

private fun getString(message: String): String {
    println(message)
    return readln()
}