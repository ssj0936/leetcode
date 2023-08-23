import java.util.LinkedList

class Solution {
    fun sortItems(n: Int, m: Int, group: IntArray, beforeItems: List<List<Int>>): IntArray {
        var count = m
        //group# -> group member
        val groupDetail = Array(m+group.count { it==-1 }){ mutableListOf<Int>()}
        for(i in group.indices){
            if(group[i]==-1) {
                group[i] = count
                groupDetail[group[i]].add(i)
                ++count
            }else{
                groupDetail[group[i]].add(i)
            }
        }

        val tableBig = Array(count){ Array(2){ hashSetOf<Int>()}}
        for(i in beforeItems.indices){
            for(before in beforeItems[i]){
                if(group[before]!=group[i]) {
                    tableBig[group[i]][0].add(group[before])
                    tableBig[group[before]][1].add(group[i])
                }
            }
        }
        val inDegreeBig = tableBig.map { it[0].size }.toMutableList()
        val result = mutableListOf<Int>()

        //insert in degree
        val queueBig = LinkedList<Int>().apply {
            for(i in inDegreeBig.indices){
                if(inDegreeBig[i]==0)
                    this.offer(i)
            }
        }

        while (queueBig.isNotEmpty()){
            val g = queueBig.poll()

            val tableSmall = HashMap<Int, MutableList<Int>>()
            val inDegreeSmall = HashMap<Int,Int>()

            //checking dependency of member inside group
            for(i in 0 until groupDetail[g].size){
                //pre class
                val obj = groupDetail[g][i]

                //obj are who's pre class
                val before = beforeItems[obj]
                var indegreeAcc = 0
                for(b in before){
                    if(group[b]!=group[obj]) continue
                    tableSmall.getOrPut(b){ mutableListOf() }.add(obj)
                    ++indegreeAcc
                }
                inDegreeSmall.put(obj, indegreeAcc)
            }

            val queueSmall = LinkedList<Int>().apply {
                for(entry in inDegreeSmall){
                    if(entry.value==0)
                        this.offer(entry.key)
                }
            }

            while (queueSmall.isNotEmpty()){
                val pop = queueSmall.poll()

                result.add(pop)
                if(tableSmall.get(pop)==null)
                    continue
                for(nextPoint in tableSmall.get(pop)!!){
                    inDegreeSmall.put(nextPoint, inDegreeSmall.get(nextPoint)!!-1)
                    if(inDegreeSmall.get(nextPoint)==0)
                        queueSmall.offer(nextPoint)
                }
            }

            for(nextGroup in tableBig[g][1]){
                --inDegreeBig[nextGroup]
                if(inDegreeBig[nextGroup]==0)
                    queueBig.offer(nextGroup)
            }
        }

        return if(result.size == n)
            result.toIntArray()
        else
            intArrayOf()
    }
}