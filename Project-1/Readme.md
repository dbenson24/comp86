# Derek Benson - Project 1
## Comp 86 - 9/24/2015

### Running and Compiling

To build, execute: javac Main.java

To run, execute: java Main

### This project contains 3 source files

#### Main.java
- Initializes the main loop of the program.
- Builds the swing window and JFrame.
- Uses a BorderLayout
- Attaches the 2 buttons and the text input field.
- Attaches a second text input field that has input disabled.
This field displays the Comp 86 Course Homepage from the website.

#### CustomJButton.java
- Extends the swing JButton class
- Customizes the JButton to alternate the text shown on the button between
clicks.
- The number of total times the button has been click will be printed to stdout.

#### LoggingJEditorPane.java
- Extends the swing JEditorPane class
- This class will log its contents after each keystroke to stdout.
