Tetris Game (JavaFX)

Overview: This project is a simple implementation of the classic Tetris game using JavaFX. The game features standard Tetris gameplay, including randomly generated Tetriminos, player-controlled movement, and a score system.

Features: The game includes randomly generated pieces from a set of standard Tetriminos, and players can move pieces left, right, and down using the arrow keys. Additionally, players can rotate pieces clockwise or counterclockwise. The game has collision detection that stops pieces when they hit the bottom or another piece, and it ends when there’s no room left to spawn a new piece. The score increases as players complete rows.

Technologies Used: The game was built using JavaFX for graphical user interface and animations, with Java for object-oriented programming and game logic.

How to Run:

    Ensure you have Java JDK 8 or later installed on your machine.

    Download or clone this repository.

    Open the project in your preferred Java IDE (Eclipse, IntelliJ, etc.).

    Run the tetris.java file to start the game.

    Also, a "Broken Console Bold" font is required to run the application. It is included with the code under "Java Projects/Tetris/src".

Controls: Arrow Left: Move piece left. Arrow Right: Move piece right. Arrow Down: Move piece down faster. Space: Rotate the piece clockwise. Z: Rotate the piece counterclockwise. Escape: Pause the game.

Game Flow: The game begins with a random Tetrimino piece at the top of the screen. Players can move the piece left or right, and pressing down moves the piece faster. Players can rotate the piece using the Space or Z keys. When a piece reaches the bottom of the screen or hits another piece, it lands and becomes part of the game grid. Completed rows are cleared, and the score increases. The game ends if a new piece cannot be placed because the screen is full. There are various buttons in the UI that serve to reset, pause, or teach the user how to use the controls in the game.

Code Structure: The Piece.java file represents a Tetris piece and manages its movement, rotation, and collision detection. The Block.java file represents a single block in a Tetris piece, responsible for drawing and interacting with the grid. Game.java contains the core game logic, such as piece generation, collision detection, and row clearing. PaneOrganizer.java handles the UI elements, including the game grid and score display, and manages key event handling for user input. The Tetris.java file is the main entry point for the application, where the JavaFX window is set up and the game is initialized.
