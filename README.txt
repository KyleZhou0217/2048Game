=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: kylezhou
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. We use a 2D array to represent the 2048 game board and do manipulations of Square objects within the
     array in order to implement the addition/value changes within the game board.

  2. We use collections (LinkedList) to store states of previous game boards in order for the user to able to undo a
     move and go back to previous game states within a playing session. A LinkedList is also used for storing scores
     of previous boards so that the score updates correctly when using the undo functionality.

  3. We use File I/O to write to a text file in order to store a game (the game board and score) so that the user
     can load a previous game after opening up 2048 again and continue playing from saved state.

  4. We use JUnit to test the Game2048 class in order to ensure that the methods of the game are implemented properly.
     We test specific functionality of Game2048 such as merging cells (Square), shifting the board, resetting the board,
     checking if we have reached a game win or lost state, and spawning new Squares as part of the game.

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named 
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  Game2048 Class: Contains all the functionality and logic of the 2048 game such as merging cells (Square),
  shifting the board, resetting the board, checking if we have reached a game win or lost state, and spawning new
  Squares as part of the game.

  GameBoard Class: Handles drawing of the 2048 game board as well as handles keyboard inputs like arrow keys
  and space bar for undo. Also updates the displaying of the status such as score or if we have reached a LOSS/WIN.

  GameSaverLoader Class: Writes and reads to/from a text file in order to store the current game board and score
  or to return the game board and score from a previous save when the user reopens that game.

  RunGame2048 Class: Handles/draws the GUI interface of the game such as the main menu and game window along with the
  buttons like saves, reset, undo, new game, etc.

  Square Class: Represents each square/tile in the 2048 game and stores/updates the value of the square and contains the
  drawing method of that square depending on its value and position.

  Tuple Class: Helper class in order to group two coupled information. An example usage is group the hex of the number
  color and background color of a square and corresponding that to the value in a map.

  Direction Enum: Used for differentiation direction of board movement based on user's arrow key click.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  Implementing the logic of sliding and merging Squares was a challenging since there were many different cases to
  consider and many times my implementation wouldn't merge or slide the Squares correctly for a specific edge case.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  I think overall for my design there is a good separation of functionality between the actual game logic and
  drawing/updating the board in the GUI. My private states are encapsulated within each class so that other classes
  cannot break invariants I've decided within each class. Something I would refactor would be my changeBoard method
  in Game2048 which handles the merging of the cells since I think there is a lot of copy-pasted code and overall
  the method could be implemented better if we could add a viewing perspective on the board so the changeBoard method
  wouldn't have to consider all directions of movement with slight variations in for loop logic to iterate through the
  board.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

  Got hex color codes of the squares corresponding the different values (2,4,8,16, etc) from online.
