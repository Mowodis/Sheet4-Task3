// transpose the input graph
def reverseGraph(matrix: Array[Array[Int]]) : Array[Array[Int]] = {
    val len = matrix.length
    val reversedM = Array.ofDim[Int](len, len)

    for (i <- 0 to len - 1) {
        for (j <- 0 to len - 1) {
            reversedM(j)(i) = matrix(i)(j)
        }
    }

    return reversedM
}

// marking a vertex as: White-unvisited, Grey-visited
enum vColour {
    case White, Grey
}

// adds adycent vertecies of first vertex of the stack
def getAdjacencies (matrix: Array[Array[Int]], vertex: Int) : List[Int] = {
    var newStack = List[Int]()
    for (i <- 0 to matrix.length - 1) {
        if (matrix(vertex)(i) == 1) {
            newStack = i :: newStack
        }
    }
    return newStack
}

// pahts a graph in DFS style
// @param adjacency matrix, vertex of graph
// @returns number of vertecies reachable from imput vertex (itselfe included)
def dfs(matrix: Array[Array[Int]], vertex: Int) : Int = {
    
    var vStatus = Array.ofDim[vColour](matrix.length)
    var dfsStack = List[Int](vertex - 1)

    for (i <- 0 to matrix.length - 1) {
        vStatus(i) = vColour.White
    }

    var cc = 0
    while (!dfsStack.isEmpty) {
        val head = dfsStack.head      
        dfsStack = dfsStack.drop(1)     // remove the head

        if (vStatus(head) == vColour.White) {
            dfsStack = getAdjacencies(matrix, head) ::: dfsStack
            cc += 1
        } 
        vStatus(head) = vColour.Grey    
    }

    return cc
} 

def findSCC (matrix: Array[Array[Int]]): List[Int] = {
    // Skript ~ 308
    // run DFS(G, u) for all u vertices -> list storage of finishing times
    // transpose G => G^t
    // run DFS(G^t, u), callint u by decrasing f(u) (List)
    // output/ save SCCs ?!
    val temp = List[Int](1)
    return temp
}