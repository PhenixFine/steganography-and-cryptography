

fun main() {
    var command = getCommand()

    while (command != "exit") {
        runCommand(command)
        command = getCommand()
    }
    println("Bye!")
}
