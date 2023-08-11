fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")


}

interface Sol{
    fun numSimilarGroups(strs: Array<String>): Int
}

class Solution:Sol {
    override fun numSimilarGroups(strs: Array<String>): Int {
        val connection = mutableMapOf<String, List<String>>()
        for(str1 in strs){
            connection[str1] = strs.filter { str1!=it && isSimilar(str1, it) }
        }

        var cnt = 0
        while (connection.isNotEmpty()){
            val queue = ArrayDeque<String>()
            val randomStartKey = connection.keys.toList()[0]
            connection.get(randomStartKey)!!.forEach {
                queue.addLast(it)
            }
            connection.remove(randomStartKey)

            while (queue.isNotEmpty()){
                val pop = queue.removeFirst()
                connection.get(pop)?.forEach { queue.addLast(it) }
                connection.remove(pop)
            }
            ++cnt
        }
        return cnt
    }

    private fun isSimilar(s1:String, s2:String):Boolean{
        var diffCnt = 0
        for(i in s1.indices){
            if(s1[i]!=s2[i]) {
                ++diffCnt
                if(diffCnt>2) return false
            }
        }
        return true
    }
}

class SolutionDFS:Sol {
    override fun numSimilarGroups(strs: Array<String>): Int {
        val visited = BooleanArray(strs.size)
        var cnt = 0

        for(i in strs.indices){
            if(!visited[i]){
                visited[i] = true
                val queue = ArrayDeque<Int>()

                for(j in strs.indices){
                    if(!visited[j] && isSimilar(strs[i], strs[j]))
                        queue.addLast(j)
                }

                while (queue.isNotEmpty()){
                    val pop = queue.removeFirst()
                    visited[pop] = true
                    for(j in strs.indices){
                        if(!visited[j] && isSimilar(strs[pop], strs[j]))
                            queue.addLast(j)
                    }
                }
                ++cnt
            }
        }
        return cnt
    }

    private fun isSimilar(s1:String, s2:String):Boolean{
        var diffCnt = 0
        for(i in s1.indices){
            if(s1[i]!=s2[i]) {
                ++diffCnt
                if(diffCnt>2) return false
            }
        }
        return true
    }
}

class SolutionDAndF:Sol {
    override fun numSimilarGroups(strs: Array<String>): Int {
        //node -> parent
        val map = hashMapOf<String, String>()
        for(i in strs.indices){
            for(j in i .. strs.lastIndex) {
                if(i == j) {
                    if(!map.containsKey(strs[i]))
                        map[strs[i]] = strs[i]
                }else{
                    if(isSimilar(strs[i], strs[j])){
                        map[strs[j]] = map[strs[i]]!!
                    }
                }
            }
        }
//        println(map)

        var rootSet = hashSetOf<String>()
        for(entry in map){
            rootSet.add(findRoot(map, entry.key))
        }
        return rootSet.size
    }

    private fun findRoot(map: HashMap<String, String>,node:String):String{
        var target = node
        while (map[target] != target){
            target = map[target]!!
        }
        return target
    }

    private fun isSimilar(s1:String, s2:String):Boolean{
        var diffCnt = 0
        for(i in s1.indices){
            if(s1[i]!=s2[i]) {
                ++diffCnt
                if(diffCnt>2) return false
            }
        }
        return true
    }
}