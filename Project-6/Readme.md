# Derek Benson - Project 5
## Comp 86 - 11/12/2015

## Running and Compiling

To build, execute: ```javac *.java```

To run, execute: ```java Main```

## Playing the game

The game is very simple. When the application is run, 10 planes and 15 obstacles
will randomly spawn onto the map. You start with 1,000 points and every tick
you lose 20 points. For every plane you crash you gain points. It doesn't matter
how you crash them, just make sure you crash each and every plane. Once you blow up
all 10 planes another 10 will spawn in and the game continues.

A crash occurs when two planes collide or when a plane collides into an obstacle.

You can quickly test the respawn mechanism by just selecting plans and removing
them via the edit menu.

## UI Design

I chose to use a standard menu bar that any user will be familiar with.

It has 3 tabs:
- File
- Edit
- View
That group settings associated with each type of option.

The add a plane feature creates a popup dialog so that the controls are not cluttered.

All other controls are spread out so that the only manipulation of state that is
required from the user is adjusting which attribute the user would like to change
using the slider.

The slider is also used in combination with the combobox to make sure that the user
doesn't have to worry about minimum or maximum values and/or spelling the
variable correctly.

The score is written unobtrusively in the top left of the display, similar to
many "scoreboard" experiences a user is likely to come across.

All of the user inputs not related to the menus are grouped together at the
bottom so that there is minimal searching.

## This project contains 8 source files

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
- The ComboBox allows a user to select which attribute the JSlider is currently
affecting and the JSlider allows a user to adjust the currently selected value
for the currently selected plane.

#### AddPlaneDialog.java
- Extends the swing JDialog class.
- This class creates a small dialog that allows a user to specify parameters
in order to create a new plane.

#### MapMenuBar.java
- Extends the swing MenuBar class.
- This class creates a menu bar at the top to give the user access to some
extra functionality. Included in it is the File menu to close the program,
the Edit menu with the option to Add a Plane, Remove Current Plane, Pause, or Play,
and the view menu which manages scrolling.
- The Play and Pause menu items are enabled or disabled depending on whether or
not the animation is running.

#### Obstacle.java
- This class defines the abstract Obstacle for use in building obstacles
for planes to hit.

#### Mountain.java
- Extends the Obstacle class.
- This class creates Mountains and draws them on the map.
- This class also enables the map to determine whether a pair of coordinates
and an altitude are in collision with the mountain.

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

#### AddPlaneDialog
- AddPlaneDialog inherits from javax.swing.JDialog

#### MapMenuBar
- MapMenuBar inherits from javax.swing.MenuBar

#### Obstacle
- Obstacle has no inheritance

#### Mountain
- Mountain inherits from Obstacle

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
- Color

#### DirectionalButton
- Map
- Point

#### AttributeController
- JComboBox
- JSlider
- Map

#### MapMenuBar
- JMenu
- JMenuItem
- Map
- ActionListener

#### AddPlaneDialog
- JLabel
- JTextField
- JButton
- Map

#### Obstacle
- Graphics
- Point

#### Mountain
- Polygon
- Color

## Uses

- UAV.java relies on Polygon to keep track of all of the points
needed to draw the UAV, as well as for handling drawing of the points,
and translation.
- DirectionalButton.java uses a Point to keep track of how a target
should be translated when one of the button is pressed.
- DirectionalButton.java also relies on Map to determine which plane
the button is supposed to be affecting when it is pushed.
- AttributeController uses a ComboBox and a Slider to handle visually
presenting the user the controls.
- AttributeController also relies on Map to get the currently selected
plane so that the controls can affect it.
- All classes that interact with the Map rely on storing the Map in a variable
and calling methods on that Map instance.

## Secret Hiding
#### Main
- JFrame frame

#### Map
- Plane current
- ArrayList<Plane> planes
- JFrame parent
- boolean hasParent
- Timer clock
- TimerTask animate
- double scaleFactor
- Point center
- Clip explosion
- int score
- File explosionInputFile


#### Plane
- int x
- int y
- int direction
- int speed
- int altitude
- int id
- boolean active

#### UAV
- Polygon shape

#### DirectionalButton
- Map target
- Point change

#### AttributeController
- JComboBox<String> comboBox
- JSlider slider
- Map target

#### MapMenuBar
- Map target

#### AddPlaneDialog
- Map target
- JPanel contentPanel
- JTextField AltitudeField
- JTextField SpeedField
- JTextField MaxSpeedField
- JTextField DirectionField
- JTextField XField
- JTextField YField

#### Obstacle
- int x
- int y
- double scaleFactor

#### Mountain
- Polygon shape
- int height
