# Indigo Card Game
Stage 1 of 4 for JetBrains Academy - Kotlin - [Steganography and Cryptography project](https://hyperskill.org/projects/160/stages/830/implement).   
This stage has us practice getting the commands from the user.
### Description
A steganography/cryptography program is versatile: it can perform a variety of tasks based on the user's wishes. You may want to hide a message within an image, encrypt the text message for extra security, or decipher a hidden message you got from someone else.

In this stage, you will create the user interface for your program. The user will be able to choose a task by giving commands via standard input.
### Objectives
The program should read input strings (commands) in a loop.

Your program should:
   * Print the message `Task (hide, show, exit):` and read standard input in a loop.
   * If the input is `exit`, print `Bye!` and exit.
   * If the input is `hide`, print `Hiding message in image.` and return to the input loop.
   * If the input is `show`, print `Obtaining message from image.` and return to the input loop.
   * If any other string is input, then print `Wrong task: [input String]` and return to the input loop.

### Example
The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.
#### Example: How the user interface should work.
```text
Task (hide, show, exit):
> hide
Hiding message in image.
Task (hide, show, exit):
> show
Obtaining message from image.
Task (hide, show, exit):
> task
Wrong task: task
Task (hide, show, exit):
> exit
Bye!
```