import javax.swing.JFrame
import javax.swing.JOptionPane

public fun getCommand() = getString("Task (hide, show, exit):")
public fun runCommand(command: String) {
    when (command) {
        "hide" -> hide()
        "show" -> show()
        else -> "Wrong task: $command"
    }.let { println(it) }
}

fun runCommandMessageDialog(command: String, frame: JFrame) {
    val result = when (command) {
        "hide" ->hide()
        "show" -> show()
        else -> "Wrong task: $command"
    }
    JOptionPane.showMessageDialog(frame, result)
}