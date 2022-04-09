The projects has the classes Bag, Board, Game, Player and Tile.

Tile represents a letter and a Bag contains tiles.

A player extracts tiles and creates a word. After that the player submits the word to the Board.

The Game contains a Bag, a Board and Players.

Program output:

Player 1: qfmstwt

Player 3: snwkvwx

Player 2: vwpiwsb

Player 2: jbyppot 

...

Compulsory (1p)

Write a program that simulates a word game between a given number of players.

At the beginning of the game there is a bag containing a number of tiles. Each tile represents a letter and has a number of points. The application will also use a dictionary that contains a list of acceptable words. Each player extracts 7 tiles from the bag and must create a word from the dictionary with them. When a player creates a word, it submits it to the board and receives a number of points (the value of tile points multiplied by the length of the word). After submitting a word of length k, the player will immediately request other tiles k from the bag(if this is possible). If the player cannot create a word, it will discard all the tiles and extract others (and passes its turn). The game ends when the bag becomes empty. The winner is the player with the highest score.
The players might take turns (or not...) and a time limit might be imposed (or not...).

The main specifications of the application are:

- [x] Create an object oriented model of the problem. You may assume that there are 10 tiles for each letter in the alphabet and each letter is worth a random number of points (between 1 and 10).
- [x] Each player will have a name and they must perform in a concurrent manner, extracting repeatedly tokens from the board.
- [x] After each extraction, the player will submit to the board all the letters.
- [x] Simulate the game using a thread for each player.
- [x] Pay attention to the synchronization of the threads when extracting tokens from the bag and when putting words on the board.







