fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol {
    fun maxNumEdgesToRemove(n: Int, edges: Array<IntArray>): Int
}

class SolutionBetter:Sol {
    override fun maxNumEdgesToRemove(n: Int, edges: Array<IntArray>): Int {
        val common = UnionFind(n)

        var type3EdgesRemoveCnt = 0
        var type3EdgesCnt = 0
        for(edge in edges){
            if(edge[0]!=3) continue
            ++type3EdgesCnt
            if(!common.union(edge[1], edge[2]))
                ++type3EdgesRemoveCnt
        }

        if(common.getGroupCnt() == 1){
            return edges.size - type3EdgesCnt + type3EdgesRemoveCnt
        }

        val alice = UnionFind(common)
        val bob = UnionFind(common)

        var type1EdgesRemoveCnt = 0
        var type2EdgesRemoveCnt = 0

        for(edge in edges) {
            if (edge[0] ==1 && !alice.union(edge[1], edge[2])){
                ++type1EdgesRemoveCnt
            }else if(edge[0] ==2 && !bob.union(edge[1], edge[2])) {
                ++type2EdgesRemoveCnt
            }
        }

        return if(alice.getGroupCnt()>1 || bob.getGroupCnt()>1) -1 else (type3EdgesRemoveCnt + type1EdgesRemoveCnt + type2EdgesRemoveCnt)

    }

    class UnionFind(n:Int){
        private var father = IntArray(n+1){it}
        private var group = n

        constructor(u:UnionFind) : this(0) {
            this.father = u.father.clone()
            this.group = u.group
        }

        private fun findRoot(node:Int):Int{
            if(father[node]== node)
                return node

            father[node] = findRoot(father[node])
            return father[node]
        }

        fun union(node1:Int, node2:Int):Boolean{
            val root1 = findRoot(node1)
            val root2 = findRoot(node2)
            return if(root1 == root2)
                false
            else {
                --group
                father[root1] = root2
                true
            }
        }

        fun getGroupCnt() = group
    }
}

class Solution:Sol {
    override fun maxNumEdgesToRemove(n: Int, edges: Array<IntArray>): Int {
        val father = IntArray(n+1){it}
        var cnt = 0
        fun findRoot(node:Int, father:IntArray):Int{
            if(father[node]== node)
                return node

            father[node] = findRoot(father[node], father)
            return father[node]
        }

        fun union(node1:Int, node2:Int, father:IntArray){
            val root1 = findRoot(node1, father)
            val root2 = findRoot(node2, father)
            if(root1 != root2)
                father[root1] = root2
        }

        for(type3Edge in edges.filter { it[0]==3 }){
            val node1 = type3Edge[1]
            val node2 = type3Edge[2]
            if(findRoot(node1, father) != findRoot(node2, father)){
                union(node1, node2, father)
            }else
                ++cnt
        }

        val rootSet = HashSet<Int>()
        for(i in 1 .. n)
            rootSet.add(findRoot(i, father))

//        println("root size=${rootSet.size}")
        if(rootSet.size==1){
            return edges.filter { it[0]==1 || it[0]==2 }.size + cnt
        }else{
            var result = cnt
            val type1Edges = edges.filter { it[0]==1 }
            val type2Edges = edges.filter { it[0]==2 }

            //type1
            val father1 = father.clone()
            var cntType1 = 0
            var currentGroupCnt1 = rootSet.size
            for(type1EdgeIndex in type1Edges.indices){
                val node1 = type1Edges[type1EdgeIndex][1]
                val node2 = type1Edges[type1EdgeIndex][2]
                if(findRoot(node1, father1) != findRoot(node2, father1)){
                    union(node1, node2, father1)
                    --currentGroupCnt1

                    if(currentGroupCnt1 == 1) {
                        result += ((type1Edges.lastIndex-type1EdgeIndex) + cntType1)
                        break
                    }
                }else {
                    ++cntType1
                }
            }
            if(currentGroupCnt1>1) return -1

            //type2
            val father2 = father.clone()
            var cntType2 = 0
            var currentGroupCnt2 = rootSet.size
            for(type2EdgeIndex in type2Edges.indices){
                val node1 = type2Edges[type2EdgeIndex][1]
                val node2 = type2Edges[type2EdgeIndex][2]
                if(findRoot(node1, father2) != findRoot(node2, father2)){
                    union(node1, node2, father2)

                    --currentGroupCnt2
                    if(currentGroupCnt2 == 1) {
                        result += ((type2Edges.lastIndex-type2EdgeIndex)+ cntType2)
                        break
                    }
                }else {
                    ++cntType2
                }
            }
            if(currentGroupCnt2>1) return -1

            return result
        }
    }
}