def reverseGraph(matrix: Array[Array[Int]]) : Array[Array[Int]] = {
    val len = matrix.length
    val reversedM = Array[Array[Int]](len)

    for (i <- 0 to len) {
        for (j <- to len) {
            reversedM(j)(i) = matrix(i)(j)
        }
    }

    return reversedM
}

// returns the finishing time
def dfs(matrix: Array[Array[Int]], vertex: Int) : Int = {

} 

def findSCC (matrix: Array[Array[Int]]): List[Int] = {
    // Skript ~ 308
    // run DFS(G, u) for all u vertices -> list storage of finishing times
    // transponiere G => G^t
    // run DFS(G^t, u), callint u by decrasing f(u) (List)
    // output/ save subsets ?!

}