import scala.io.Source


// converts the matrix represented in a `.csv-file` into a `v x v-Matrix`
def createMatrix(v: Int, path: String): Array[Array[Int]] = {
    val matrix = Array.ofDim[Int](v,v)
    
    val buffer = scala.io.Source.fromFile(path)
    var count = 0
    for (line <- buffer.getLines) {
        matrix(count) = line.split(",").map(_.trim.toInt).toArray 
        count += 1
    }
    buffer.close

    return matrix
}

// prints the input matrix in the console
def printMatrix(matrix: Array[Array[Int]]) = {
    for (i <- 0 to matrix.length-1) {
        for (j <- 0 to matrix.length-1) {
            print(matrix(i)(j) + " ")       
        }
        println("")
    }
}

// display a list of SCCs in the console
// each vertex has been assighed a number >= 1, increasing
def printSCCs(matrix: Array[Array[Int]], name: String) : Unit = {
    val newSCCs = findSCCs(matrix)
    
    println("")
    println("SCCs of graph: " + name)
    
    for (i <- 0 to newSCCs.length - 1)
        print(" ( ")
        
        for (j <- 0 to newSCCs(i).length - 1) {
            // start a new paragraph after 20 entries
            if (j % 20 == 19 && j != (newSCCs(i).length - 1)) {
                println(newSCCs(i)(j) + 1 + " ")
                print("   ")
            } else {
                print(newSCCs(i)(j) + 1 + " ")
            }
        }
        println(")")
}

def main(args: Array[String]): Unit = {
    
    // load test templates 
    val big_graph = createMatrix(1000,"src/main/resources/big_graph.csv")
    val small_graph = createMatrix(7,"src/main/resources/small_graph.csv")
    val fullyConnected_graph = createMatrix(5,"src/main/resources/fullyConnected_graph.csv")
    val simpleCycle_graph = createMatrix(3,"src/main/resources/simpleCycle_graph.csv")
    val twoCycle_graph = createMatrix(6,"src/main/resources/twoCycle_graph.csv")

    printSCCs(big_graph, "big_graph")    
}   