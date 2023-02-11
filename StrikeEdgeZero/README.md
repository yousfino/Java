# Description

StrikeEdgeZero is a 1-player game consisting of a sequence of integers.

The rules of the game are simple. The circle on the initial square is an initial marker that can move 
to other positions along the sequence. At each step in the game, you may move the marker the number 
of positions indicated by the integer in the square it currently occupies. The marker may move either 
left or right along the row but may not move past either end. For example, in the above array, the only 
legal first move is to move the marker four squares to the right because there is no room to move four
spaces to the left.

The goal of the game is to move the marker to the far-right index (the Edge), which always contains 
“0”.

## Input

The input file has on its first line one integer M that specifies the number of datasets in the file. 
The following M lines each specifies M inputs (one per line) as space separated integers. The first 
one is N, the size of the sequence including the final 0. This is followed by an integer that denotes 
the position of the initial marker between 0 and N-2 (inclusive). They are followed by N-1 integer 
values denoting the values of the sequence excluding the last 0 entry.

## Output

The output file contains a line for each input with only one integer: 1 (if a solution exists), or 0 
otherwise. No end of line after the last line. Your solution should work for any size of the game’s 
row (i.e. any array size).
