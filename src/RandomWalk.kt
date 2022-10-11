import kotlin.random.Random

fun getNeighbour(node: Int, edges: MutableList<Pair<Int,Int>>):Set<Int>{
    val neighbors = mutableSetOf<Int>()
    edges.forEach {
        if(node == it.first){
            neighbors.add(it.second)
        } else if(node == it.second){
            neighbors.add(it.first)
        }
    }
    return neighbors.toSet()
}

fun walkInit(depthOrWalk: Int, visitedTime: Int, edges: MutableList<Pair<Int, Int>> ):ArrayList<MutableSet<Int>>{
    val neighbourList = ArrayList<MutableSet<Int>>()
    repeat(visitedTime-1){
        val neighbour = mutableSetOf<Int>()
        edges[(0 until edges.size).random()].toList().forEach {
            neighbour.addAll(getNeighbour(it,edges))
            if(neighbour.isNotEmpty()){
                neighbour.addAll(walk(depthOrWalk-1,neighbour.random(),edges,neighbour))
            }
        }
        neighbourList.add(neighbour)
    }
    return neighbourList
}
fun walk(depthOrWalk: Int, node: Int, edges: MutableList<Pair<Int, Int>>,neighbour : MutableSet<Int> ):MutableSet<Int>{
    val currentNeighbour = mutableSetOf<Int>()
    currentNeighbour.addAll(getNeighbour(node,edges))
    if(currentNeighbour.isNotEmpty() && depthOrWalk -1 >0 ){
        walk(depthOrWalk-1,currentNeighbour.random(),edges,currentNeighbour)
    }
    neighbour.addAll(currentNeighbour)
    return neighbour
}
fun main(){
    val edges = mutableListOf(Pair(1,2), Pair(3,4), Pair(4,5), Pair(7,8), Pair(5,7), Pair(1,7))
    print(walkInit(1, edges.size,edges))
}