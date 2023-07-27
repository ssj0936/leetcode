import java.util.LinkedList
import java.util.Stack

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

class Solution {

    var r:IntArray? = null
    fun smallestSufficientTeam(req_skills: Array<String>, people: List<List<String>>): IntArray {
        val indexMappingTable = HashMap<String, Int>().apply { req_skills.forEachIndexed { index, s -> this.put(s, index)} }

        // convert people skill into bits
        val peopleSkillStatus = IntArray(people.size)
        for((i,skills) in people.withIndex()){
            var bits = 0
            for(skill in skills){
                val skillIndex = indexMappingTable.get(skill)!!
                bits = bits or (1 shl skillIndex)
            }
            peopleSkillStatus[i] = bits
        }

        //key: skillIndex, value:peopleIds
        val skillMap = HashMap<Int, MutableList<Int>>().apply {
            people.forEachIndexed { index, skills ->
                skills.forEach {skill ->
                    this.getOrPut(indexMappingTable.get(skill)!!){ mutableListOf()}.add(index)
                }
            }

            this.forEach { entry ->
                entry.value.sortBy { people[it].size }
            }
        }

        val result = LinkedList<Int>()
        val visited = BooleanArray(60)
        val memo = HashSet<Int>()
        var target = Math.pow(2.0, req_skills.size.toDouble()).toInt()-1
        helper(0, target, skillMap, peopleSkillStatus, visited, result)
        return r!!

    }
    private fun helper(currentSkills: Int, target:Int, skillMap:HashMap<Int, MutableList<Int>>, peopleSkillStatus:IntArray, visited:BooleanArray, result:LinkedList<Int>){
        if(currentSkills == target) {
            if(result.size < (r?.size ?: Int.MAX_VALUE))
                r = result.toIntArray()
            println(r.contentToString())
            return
        }

        var origin = currentSkills
        var targetSkill:Int = run {
            var tmp = currentSkills
            var count = 0
            println("$tmp and 1=${tmp and 1}")
            while (tmp and 1 !=0) {
                ++count
                tmp shr 1
                println("tmp:$tmp")
            }
            count
        }
        //目前要找的skill
        for(peopleId in skillMap.get(targetSkill)!!){
            if(!visited[peopleId]) {
                visited[peopleId] = true
                result.push(peopleId)
                helper(currentSkills or peopleSkillStatus[peopleId], target, skillMap, peopleSkillStatus, visited, result)
                result.pop()
                visited[peopleId] = false
            }
        }

    }
}