# csc413-tankgame

## Student Name  : Megan (Em) Powers
## Student ID    : 927304539
## IDE: IntelliJ iDEA 2019.3.2 Ultimate Edition
## Java Version: 11.0.5
## Working Directory: MainRoom.java 

## src folder is to be used for source code only.

## resources folder is to be used to store the resources for your project only. This includes images, sounds, map text files, etc.

## jar folder is used to store the built jar of your term-project. NO SOURCE CODE SHOULD BE IN THIS FOLDER. DOING SO WILL CAUSE POINTS TO BE DEDUCTED

##Instructions:
This game is a one-person puzzle game built using JavaFX and pure Java. 

To import:

- Navigate into the project directory
- Click on the "Clone or Download" button and copy the link
- Open your terminal
- Run the command git clone [link copied]

To run in a JDK:

- Open the repository in your JDK of choice, right click
on MainRoom.java, and click "run"

To run the jar file:

- To run the jar file, open up the folder with the jar inside 
- Copy the file path. On a mac, this is done by hitting the 
gear icon and clicking "copy file path"
- Open up your terminal of choice 
- Run cd [link copied]
- Run the command java -jar [name of jar].jar in your terminal
- NOTE: Opening up the jar directly will not work. It MUST be opened
using the terminal 

To play the game:

You are an intrepid explorer who's gotten lost inside a pyramid. Use the arrow keys to move up, down, left, and right. 

There are powerups scattered around the map: 

- Treasure: Ankhs give you +15, statues give you +30 to your total score. 

- Scarabs: Can be collected to make enemy mummies vulnerable by pressing shift. They also add +50 to your total score. 

- Gun: Can be used to fire at scorpions and beetles. There's only one, so you'll have to venture out to find it. Press enter to fire.

- Papyrus: A part of the Book of Lives, that grants you an extra life.

- Potion: Grants you full health. You'll need it.

- Sword: The Sword of Ra and your target. Acquiring it plunges the pyramid into darkness. If you have treasure, you can press the space key to reactivate its glow, at the cost of draining your riches. This also causes mummies to become                vulnerable.

There are enemies that live within. All will pursue you if you get too close:

- Beetles: Can move up and down. They have 10 HP, and you can shoot them with a gun. They are invulnerable to scarabs.
- Scorpions: Can move left and right. They have 10 HP, and you can shoot them with a gun. They are invulnerable to scarabs.
- Mummies: Can move in any direction. The only way you can kill them is by hitting them while they're weakest - if you         activate a scarab or the sword.

There are walls:

- Pyramid walls: You cannot pass through these.

- Hieroglyph door: What trapped you inside. You need to return to this point while bearing the Sword of Ra to leave.

- Movable walls: Walls you can push based on the direction indicated. They can slide into other walls to aid your progress.

- Breakable walls: Walls that you can shoot, indicated by their cracked exterior

The explorer has 3 lives and 100 HP. To win, you must collect the Sword of Ra and return to the pyramid door without losing all 3 of your lives.

Note: The Scoreboard and movement are extra, not-fully-implemented features done for experience. They are there as placeholders right now. For movement, it was not implemented because of timeout/framerate issues, and for scoreboard, it was not fully implemented because of time issues. They are, to my knowledge, not requirements of the project.
