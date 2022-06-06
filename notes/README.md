### Main.kt
* main() - makes use of functions getCommand() and runCommand() to interact with the user. It loops asking the user for next command until the user types `exit`.    
* getCommand() - asks the user for next command using function getString()    
* runCommand(String) - receives a command, if the command is `hide` it calls the hide() function, for command `show` it prints the info for the command, anything else it prints that the command is a wrong task.     
* hide() - using helper functions getFile(), getBufferedImage(), and saveImage(), it gets the input and output image file names, updates the image with the least significant bit for each color is set to 1, if a problem arises during the process an appropriate error message is printed.    
* getFile(type: String) - gets the type of file needed from the user using the function getString()    
* getBufferedImage(File) - creates a buffered image from the passed file, if an issue arises from the creation null is returned.    
* saveImage(BufferedImage, File) - if the saving of the BufferedImage is successful true is returned otherwise false is returned.    
* getString(String) - receives a message to print to the user and returns the user's response.
