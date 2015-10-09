# Derek Benson - Project 2
## Comp 86 - 10/9/2015

## Running and Compiling

To build, execute: javac Main.java

To run, execute: java Main

## This project contains 4 source files

#### Main.java
- Initializes the main loop of the program.
- Builds the swing window and JFrame.
- Uses a BorderLayout
- Attaches the Canvas to the center of the JFrame.
- Attaches the 4 buttons grouped together in a JPanel below the canvas.

#### UAV.java
- Extends the Polygon class
- Initializes a Polygon that is in the shape of a UAV.

#### Canvas.java
- Extends the swing JPanel class
- This class overrides the paintComponent method to draw 4 clouds onto itself
and then draw a UAV onto the screen.
- This class also provides a refresh method so that another class can force a
redraw of just the canvas, or if the canvas is contained inside a JFrame it can
redraw the entire JFrame.

#### DirectionalButton.java
- Extends the JButton class
- This button class allows the button to translate the position of the UAV on
the target canvas.
- When the button is clicked, it prints the translation for that
button to stdout and translates the UAV.

## Inheritance Hierarchy

#### Main
- Main has no inheritance

#### Canvas
- Canvas inherits from javax.swing.JPanel

#### UAV
- UAV inherits from java.awt.Polygon

#### DirectionalButton
- DirectionalButton inherits from javax.swing.JButton

## Aggregation Hierarchy
### Each of these classes references:

#### Main
- JFrame
- JPanel
- Canvas
- DirectionalButton

#### Canvas
- UAV
- JFrame

#### UAV
- No Aggregation

#### DirectionalButton
- Canvas
- Point
