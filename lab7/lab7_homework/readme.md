The projects has the classes Bag, Board, Game, Player and Tile.

Tile represents a letter and a Bag contains tiles.

A player extracts tiles and creates a word. After that the player submits the word to the Board.

The Game contains a Bag, a Board and Players.

Program output:

Player 1: hui

Player 2: hts

Player 3: url

Player 1: road

...

Player 1 has score: 226

Player 2 has score: 213

Player 3 has score: 221

The winner is: Player 1

Homework (2p)
- [x] Use the following number of tiles for each letter: A-9, B-2, C-2, D-4, E-12, F-2, G-3, H-2, I-9, J-1, K-1, L-4, M-2, N-6, O-8, P-2, Q-1, R-6, S-4, T-6, U-4, V-2, W-2, X-1, Y-2, Z-1
- [x] Use the following points for the letters:
- (1 point)-A, E, I, O, U, L, N, S, T, R
- (2 points)-D, G
- (3 points)-B, C, M, P
- (4 points)-F, H, V, W, Y
- (5 points)-K
- (8 points)- J, X
- (10 points)-Q, Z
- [x] Create an implementation of a dictionary, using some collection of words. Use an appropriate collection to represent the dictionary. This collection should be large enough; you may use aspell to generate a text file containing English words, or anything similar: WordNet, dexonline, etc.
- [x] Implement the scoring and determine who the winner is at the end of the game.
- [ ] Make sure that players wait their turns, using a wait-notify approach.
- [ ] Implement a timekeeper thread that runs concurrently with the player threads, as a daemon. This thread will display the running time of the game and it will stop the game if it exceeds a certain time limit.







