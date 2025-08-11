For a newspaper word search puzzle, you’re given a grid of letters and a list of words. Your goal
is to find all of the given words within the grid. The challenge is that the words do not
necessarily appear in left-to-right order. Instead, the words can go in any of 8 directions:
left-to-right, right-to-left, vertically down, vertically up, or any of 4 directions diagonally (up-left,
up-right, down-left, down-right).
For this assignment, in addition to implementing a separate chaining hash table, you will be
completing an implementation of a word search solver. For this word search solver, we use two
text files. One containing our grid of letters, the other containing a list of potential words. This
solver will use these slightly differently from the puzzles you might find in the newspaper. In the
newspaper it is guaranteed that all words in the list appear somewhere in the grid. For this
application our task will be to determine which of the words appear. Additionally, we’re
implementing the algorithm using generics, so rather than a grid of letters we could instead do a
wordsearch on a grid of integers, or doubles, or booleans, or pixels, or any other object we
could dream of!
You’ll see in the starter code and in the list of provided files below that we give you two pairs of
puzzles. One that is a small grid paired with a relatively short list of words (this is actually the list
of words used by xkcd author Randall Munroe for his Thing Explainer book, but with the swear
words removed). The other is a larger grid paired with a large list of words (this list of words is
the official Scrabble dictionary).
