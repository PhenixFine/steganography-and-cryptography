import javax.swing.JFrame
import javax.swing.JOptionPane

fun main() {
    val frame = JFrame()
    frame.isVisible = true
    var command:String?

    do {
        command = JOptionPane.showInputDialog(
            frame,
            "Task (hide, show, exit):",
            "Steganography",
            JOptionPane.QUESTION_MESSAGE
        )

        if (command != null) {
            runCommandMessageDialog(command, frame)
        }
    } while (command != "exit")

    println("Bye!")
    frame.dispose()
}