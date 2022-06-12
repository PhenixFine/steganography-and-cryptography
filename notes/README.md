### Main.kt
* main() - makes use of functions getCommand() and runCommand() to interact with the user. It loops asking the user for next command until the user types `exit`.    
* getCommand() - asks the user for next command using function getString()    
* runCommand(String) - receives a command, if the command is `hide` it calls the hide() function, for command `show` it calls the show() function, anything else it prints that the command is a wrong task.     
* hide() -
    * uses helper functions - getFile(), getBufferedImage() and getBits()
    * hides the user message within the output image if the input image is large enough to store the message.
        * hides message by looping through each pixel of image and saves the message bit by bit within the least significant bit of the color blue.
        * once the full message has been saved in the buffered image the image is saved into the output file and the user is informed of the successful save.
    * If a problem arises during the process an appropriate error message is printed. 
* show() -
    * uses helper functions - getFile() and getBufferedImage()
    * loops through the image saving the least significant bit of the color blue, and converting each eight bits encountered to a char to save within the String message. 
    * If the end of the image is reached without encountering the END marker that indicates the end of a message, the user is informed that no message was hidden. 
    * If the END marker is encountered the saved message is presented to the user.
* getBits() - 
    * using getString() it requests the message from the user. 
    * The message is then mapped to a list with each character converted to a Char code and combined with the END list
    * each number code is then converted to a binary String padded with leading zeroes to make each one of length eight. 
    * All binary strings are then combined as one String and returned.
* getFile(type: String) - gets the type of file needed from the user using the function getString()    
* getBufferedImage(File) - creates a buffered image from the passed file, if an issue arises from the creation null is returned.    
* saveImage(BufferedImage, File) - if the saving of the BufferedImage is successful true is returned otherwise false is returned.    
* getString(String) - receives a message to print to the user and returns the user's response.