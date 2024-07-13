public fun getCommand() = getString("Task (hide, show, exit):")
public fun runCommand(command: String) {
    when (command) {
        "hide" -> hide()
        "show" -> show()
        else -> "Wrong task: $command"
    }.let { println(it) }
}