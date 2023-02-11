# Description

In this programming assignment, you will design an algorithm (in pseudo code), and implement 
(in Java), two recursive versions of an Oddonacci calculator (similar to the ones of Fibonacci) and 
experimentally compare their runtime performances. Oddonacci numbers are inspired by Fibonacci 
numbers but start with three predetermined values, each value afterwards being the sum of the 
preceding three values. The first few Oddonacci numbers are: 

1, 1, 1, 3, 5, 9, 17, 31, 57, 105, 193, 355, 653, 1201, 2209, 4063, 7473, 13745, 25281, 46499, ...

For that, with each implemented version you will calculate Oddonacci(5), Oddonacci (10), etc. in 
increments of 5 up to Oddonacci(100) (or higher value if required for your timing measurement) and 
measure the corresponding run times. For instance, Oddonacci(10) returns 105 as value. You can use 
Java’s built-in time function for this purpose. You should redirect the output of each program to an
OddoOut.txt file. You should write about your observations on the timing measurements in a separate text 
file. You are required to submit the two fully commented Java source files, the compiled files, and the text 
files.

## Solution Details

This was solved using two algorithms.

The first algorithm uses tail recursion. Tail recursion is a special type of linear recursion and has 
a time complexity that grows proportional to the size of inputs, which makes it run in O(n) complexity.

The second algorithm uses trinary/ternary recursion. It was not a good idea to use ternary recursion 
because it is of exponential time complexity since each time the method is called, it calls itself 
three times. So, compared to the linear recursion, the ternary recursion runs in O(n^3).

The text file "OddoOut.txt" contains a sample output that shows the performance for each algorithm 
in terms of time and the result is arranged in a nicely-arranged table so that the comparison 
between both algorithms is clear.
