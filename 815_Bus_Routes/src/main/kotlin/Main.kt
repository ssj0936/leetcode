import java.util.LinkedList

class SolutionOri {
    fun numBusesToDestination(routes: Array<IntArray>, source: Int, target: Int): Int {
        if(source == target) return 0
        //stop -> bus#
        val table = hashMapOf<Int, MutableList<Int>>().apply {
            for(route in routes.indices){
                for(stop in routes[route]){
                    this.getOrPut(stop){ mutableListOf()}.add(route)
                }
            }
        }

        if(!table.containsKey(target)) return -1

        val visited = hashSetOf<Int>()
        val queue = LinkedList<Int>().apply {
            table.get(source)?.forEach {
                offer(it)
                visited.add(it)
            }
        }


        var path = 0
        while (queue.isNotEmpty()){
            val size = queue.size
            ++path

            repeat(size){
                val bus = queue.poll()

                if(table.get(target)!!.contains(bus)) return path

                for(stop in routes[bus]){
                    for(nextBus in table.get(stop)!!){
                        if(!visited.contains(nextBus)) {
                            queue.offer(nextBus)
                            visited.add(nextBus)
                        }
                    }
                }
            }
        }

        return -1
    }
}

class Solution {
    fun numBusesToDestination(routes: Array<IntArray>, source: Int, target: Int): Int {
        if(source == target) return 0
        //stop -> bus#
        val table = hashMapOf<Int, MutableList<Int>>().apply {
            for(route in routes.indices){
                for(stop in routes[route]){
                    this.getOrPut(stop){ mutableListOf()}.add(route)
                }
            }
        }

        if(!table.containsKey(target)) return -1

        val visitedStop = hashSetOf<Int>()
        val visitedBus = hashSetOf<Int>()
        val queue = LinkedList<Int>().apply {offer(source)}


        var path = 0
        while (queue.isNotEmpty()){
            val size = queue.size
            ++path

            repeat(size){
                val stop = queue.poll()
                val buses = table.get(stop)!!
                for(bus in buses){
                    if(bus in visitedBus) continue
                    visitedBus.add(bus)

                    for(nextStop in routes[bus]){
                        if(nextStop in visitedStop) continue
                        if(nextStop == target) return path
                        queue.offer(nextStop)
                        visitedStop.add(nextStop)
                    }
                }
//
//                if(table.get(target)!!.contains(bus)) return path
//
//                for(stop in routes[bus]){
//                    for(nextBus in table.get(stop)!!){
//                        if(!visited.contains(nextBus)) {
//                            queue.offer(nextBus)
//                            visited.add(nextBus)
//                        }
//                    }
//                }
            }
        }

        return -1
    }
}