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


def main(args: Array[String]): Unit = {
    
    // testsamples 
    val big_graph = createMatrix(1000,"src/main/resources/big_graph.csv")
    val small_graph = createMatrix(7,"src/main/resources/small_graph.csv")
    
    println("A runing time of: " + dfs(small_graph, 1))
    printMatrix(reverseGraph(small_graph))

}   