# Derek Benson - Project 3
## Comp 86 - 10/23/2015

## Running and Compiling

To build, execute: javac Main.java

To run, execute: java Main

## This project contains 6 source files

#### Main.java
- Initializes the main loop of the program.
- Builds the swing window and JFrame.
- Uses a BorderLayout
- Attaches the Canvas to the center of the JFrame.
- Attaches the 4 buttons grouped together in a JPanel below the canvas.

#### Plane.java
- Defines the abstract class to represent a plane
- Provides get and set methods for the protected variables
altitude, id, speed, direction, and the plane's active state.
- Plane also requires that any implementation define the draw method 
so that the plane can be displayed, it also requires a contains method
to make sure that other modules will be able to determine if a 2d point 
is inside of the plane.

#### UAV.java
- Extends the Plane class
- Initializes a Polygon that is in the shape of a UAV. It uses this polygon
for translations and to display the current location of the plane.

#### Map.java
- Extends the swing JPanel class
- This class overrides the paintComponent method to draw 4 clouds onto itself
and then draw 8 randomly positioned UAVs onto the screen.
- This class also provides a refresh method so that another class can force a
redraw of just the canvas, or if the canvas is contained inside a JFrame it can
redraw the entire JFrame.

#### DirectionalButton.java
- Extends the JButton class
- This button class allows the button to translate the position of the UAV on
the target canvas.
- When the button is clicked, it prints the translation for that
button to stdout and translates the UAV.

#### AttributeController.java
- Extends the swing JPanel class.
- This class overrides the paintComponent to keep the slider's selected value
up to date with the currently selected plane.
- The combobox allows a user to select which attribute the slider is currently
affecting and the slider allows a user to adjust the currently selected value
for the currently selected plane.

## Inheritance Hierarchy

#### Main
- Main has no inheritance

#### Map
- Map inherits from javax.swing.JPanel

#### Plane
- Plane has no inheritance

#### UAV
- UAV inherits from Plane

#### DirectionalButton
- DirectionalButton inherits from javax.swing.JButton

#### AttributeController
- AttributeController inherits from javax.swing.JPanel

## Aggregation Hierarchy
### Each of these classes reference:

#### Main
- JFrame
- JPanel
- Map
- DirectionalButton
- AttributeController

#### Map
- Plane
- UAV
- JFrame
- Graphics
- Point

#### Plane
- Graphics
- Point

#### UAV
- Polygon
- Point

#### DirectionalButton
- Map
- Point

#### AttributeController
- JComboBox
- JSlider
- Map
