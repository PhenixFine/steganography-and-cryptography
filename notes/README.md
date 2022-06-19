### Main.kt
* main() - makes use of functions getCommand() and runCommand() to interact with the user. It loops asking the user for next command until the user types `exit`.    
* getCommand() - asks the user for next command using function getString()    
* runCommand(command: String) - receives a command, if the command is `hide` it calls the hide() function, for command `show` it calls the show() function, anything else it prints that the command is a wrong task.     
* hide() -
    * uses helper functions - getFile(), getString(), getPassword(), getBufferedImage(), transformBits(), getBits(),  and saveImage()
    * hides and encrypts the user message within the output image if the input image is large enough to store the message.
        * hides message by looping through each pixel of image and saves the message bit by bit within the least significant bit of the color blue.
        * once the full message has been saved in the buffered image the image is saved into the output file and the user is informed of the successful save.
    * If a problem arises during the process an appropriate error message is printed. 
* show() -
    * uses helper functions - getFile(), getPassword(), getBufferedImage() and decodeMessage()
    * loops through the image saving the least significant bit of the color blue, until the END marker is encountered or the end of the image. 
    * If the end of the image is reached without encountering the END marker that indicates the end of a message, the user is informed that no message was hidden. 
    * If the END marker is encountered the saved message is presented to the user using decodeMessage() to decode the bits.
* transformBits(message: List<Byte>, password: String) -
    * converts the password into bits using getBits()
    * it then maps the given message to convert each bit using the appropriate bit from the converted password by doing bit xor keyBit, where bit is from message and keyBit is from the password
* getBits(message: String) - 
    * The message is mapped to a list with each character converted to a Char code
    * each number code is then converted to a binary String padded with leading zeroes to make each one of length eight. 
    * each character of each string is then mapped as a byte into a list, and then each list of bytes is flattened to one list
* decodeMessage(message: List<Byte>, password: String) - 
  * it calls transformBits() on message and password and then breaks it up to convert into the stored message
* saveImage(image: BufferedImage, file: File) - if the saving of the BufferedImage is successful true is returned otherwise false is returned.
* getBufferedImage(file: File) - creates a buffered image from the passed file, if an issue arises from the creation null is returned.
* getPassword() - using getString() it returns a password or null depending on if the user typed anything
* getFile(type: String) - gets the type of file needed from the user using the function getString()     
* getString(message: String) - receives a message to print to the user and returns the user's response.