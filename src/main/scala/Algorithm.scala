/** transpose the input matirx (graph)
  *
  * @param matrix
  * @return matrix
  */
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

/** adds adycent vertecies of first vertex of the stack
  *
  * @param matrix
  * @param vertex
  * @return list of Int
  */
def getAdjacencies (matrix: Array[Array[Int]], vertex: Int) : List[Int] = {
    var newStack = List[Int]()
    for (i <- 0 to matrix.length - 1) {
        if (matrix(vertex)(i) == 1) {
            newStack = i :: newStack
        }
    }
    return newStack
}

/** pahts a graph in DFS style
  *
  * @param matrix
  * @param vertex
  * @return the number of reachable vertecies
  */
def dfsCCSize(matrix: Array[Array[Int]], vertex: Int) : Int = {
    
    var vStatus = new Array[vColour](matrix.length)
    var dfsStack = List[Int](vertex)

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

/** pahts a graph in DFS style
  *
  * @param matrix
  * @param vertex
  * @return list of reachable vertecies
  */
def dfsCCList(matrix: Array[Array[Int]], vertex: Int) : List[Int] = {
    
    var vStatus = new Array[vColour](matrix.length)
    var dfsStack = List[Int](vertex)

    for (i <- 0 to matrix.length - 1) {
        vStatus(i) = vColour.White
    }

    var ccList = List[Int]()
    while (!dfsStack.isEmpty) {
        val head = dfsStack.head      
        dfsStack = dfsStack.drop(1)     // remove the head

        if (vStatus(head) == vColour.White) {
            dfsStack = getAdjacencies(matrix, head) ::: dfsStack
            ccList = head :: ccList
        } 
        vStatus(head) = vColour.Grey    
    }

    return ccList
} 

/** findSCCs helper function
  * removes all elements from toAddL-List which are already contained within a List of sccList
  *
  * @param toAddL
  * @param sccList
  * @return list of list of Int
  */
def addSCC (toAddL: List[Int], sccList: List[List[Int]]) : List[List[Int]] = {
    var newToAddL = toAddL
    var toDelete = List[Int]()

    for (i <- 0 to sccList.length - 1) {                // for each List in sccList
        for (j <- 0 to newToAddL.length - 1) {          // for each element of newToAddL
            if (sccList(i).contains(newToAddL(j))) {
                toDelete = newToAddL(j) :: toDelete     // collect elemnts to be deleted later
            }
        }

        for (k <- 0 to toDelete.length - 1) {           // delete vertecies         
            newToAddL = newToAddL.filterNot(_ == toDelete(k))     
        }
    }

    if (!newToAddL.isEmpty) {
        val newSCCList = newToAddL :: sccList 

        return newSCCList
    } else {
        return sccList
    }
}


/** sorts the indices of input array by decreasing value
  *
  * @param array
  * @return array of indeces of highest value, derecasing
  */
def sortByLargesV (array: Array[Int]) : Array[Int] = {
    var maxSortedArray = new Array[Int](array.length)
    var unsortedList = array
    var indexOfMax = 0

    for (i <- 0 to array.length - 1) {
        indexOfMax = array.indexOf(array.max)   // find index of largest Integer
        maxSortedArray(i) = indexOfMax          // write index 
        array(indexOfMax) = 0                   // set lagest integer to 0
    }

    return maxSortedArray
}

/** Determins the Strongly Connected Components (SCCs) of a graph
  *
  * @param matrix
  * @return list of list of integers
  */
def findSCCs (matrix: Array[Array[Int]]): List[List[Int]] = {
    
    // run dfs for all vertecies and strore their cc sizes
    val ccSizes = new Array[Int](matrix.length)
    for (i <- 0 to matrix.length - 1) {
        ccSizes(i) = dfsCCSize(matrix, i)
    }

    // transposes the adjacency matrix
    val tMatrix = reverseGraph(matrix)

    // sort ccSizes-indexes by hightes value
    val sortedCCSizes = sortByLargesV(ccSizes)
    
    // add collect all SCCs    
    var sccList = List[List[Int]]()
    var tempCC = List[Int]()

    // check if vertecies are already part of other SCCs and remove accordingly
    for (i <- 0 to sortedCCSizes.length - 1) {
        // starting with the vertex, which had the hightes ccSize
        tempCC = dfsCCList(tMatrix, sortedCCSizes(i))     
        
        if (sccList.isEmpty) {
            sccList = tempCC :: sccList
        } else {
            sccList = addSCC(tempCC, sccList)
        } 
    }

    return sccList
}