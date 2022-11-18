# Sheet 4 - Task 3

This repository corresponds to: `Theoretische Informatik 1, Ulrike von Luxburg, 2022/2023`

Handin of:`Marcel Heine, Edwin Hoffmann`

Tutor: `Long Nguyen` 

## Algorithm description

### Main

`createMatrix`: Responsible for loading the graph.csv into a two dimensional Array, with which then can be worked with. Does not work with an empty graph.

`printMatrix`: Prints an imput matrix into the console. Mainly used for debugging purposes and has no use beyond that.

`printSCCs`: Prints an imput of List of Lists of Integers into the console. Used to visualise the end resultes of findSCCs. Due to the implementation, the sink component(s) are represented first and source component(s) last.

`Main`: Loading of the _garph.csv-files into usable values and executing of both print-functions from above.

### Algorithm

The implemented algorithm is based of Skript Pages ~309 (version 14.11.2022)
Contains an algorithm to determin the strongly connected components within a directed graph, which has to be represented as an adjacency matrix.
It follows the steps given in the Skript:
 - List the running time of DFS for all vertecies in the graph
 - transpose the graph
 - Run DFS again on the transposed Graph with the vertecies with the highest running time in decreasing order
 - making sure that no element is saved twice will result in a list of Srongly Connected Components for the imput graph.

`findSCCs` is the highest function in the hirarchy and followes above recipe:
 
 - `dfsCCSize`: Run on the adjacency matrix for each vertex in the graph and save the number of reachable vertecies 
 
 - `reverseGraph`: Call to taranspose the adjacency matrix and therefore reverse all directed edges of the original graph
 
 - `sortByLargesV`: To reorder the results of dfsCCSize, in oder to obtain an array, that contains the vertecies with the largest running time in decreasing fashon
 
 - `dfsCCList`: Is the same as dfsCCSize, but has a List of reachable vertecies from a vertex as an output. There exist two different DFS functions in order to avoid output type ambiguity.
 
 - `addSCC`: Checks the output of dfsCCList and checks, if the contained vertecies are already part of other existing and delet the from the dfsCCList output. The remaining Elements (if any) can then be added as a new SCC to the List.

`getAdjacencies`: Is a helpter function for both DFS-Functions, and finds all adjacent vertecies in a input matrix for a vertex

Why this algorithm works, is explaned in the Skript on pages: 296 - 307

## Additional Context

All written code for Task 3 is located is `\src\main\scala`.
All (example-) graphs are located in `\src\main\resources`.

## Usage (auto generated)

This is a normal sbt project. You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL.

