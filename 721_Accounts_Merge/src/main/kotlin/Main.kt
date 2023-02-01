/*
721. Accounts Merge
Medium
Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.


Example 1:
Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

Example 2:
Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]

Constraints:
1 <= accounts.length <= 1000
2 <= accounts[i].length <= 10
1 <= accounts[i][j].length <= 30
accounts[i][0] consists of English letters.
accounts[i][j] (for j > 0) is a valid email.
 */
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
        val hashMap = HashMap<String, MutableList<MutableSet<String>>>()
        for(account in accounts){
            val name = account[0]
            val list = hashMap.getOrPut(name, ::mutableListOf)
            var foundGroup = false
            for(emailIndex in 1..account.lastIndex) {
                if(foundGroup)
                    break

                val email =account[emailIndex]
                for (set in list) {
                    if (set.contains(email)){
                        set.addAll(account.subList(1,account.size))
                        foundGroup = true
                        break
                    }
                }
            }

            if(!foundGroup) {
                val set = mutableSetOf<String>().apply {
                    addAll(account.subList(1,account.size))
                }
                list.add(set)
            }
        }

        val result = mutableListOf<List<String>>()
        for((name, listOfEmail) in hashMap){
            for(list in listOfEmail){
                val subResult = mutableListOf<String>().apply { add(name) }
                subResult.addAll(list.sorted())
                result.add(subResult)
            }
        }

        return result
    }
}

class Solution02 {
    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
        //key值為name，value是另一個hashMap，記錄每一個email的parent是誰
        //藉由這個parentMap 來推出每一個點的root是誰
        val accountMap = HashMap<String, HashMap<String, String>>()
        for (account in accounts) {
            val name = account[0]
            val parentMap = accountMap.getOrPut(name){HashMap()}
            //first insert of this Name
            if(parentMap.size==0){
                //parentMap塞入每一個email parent都是這個account的第一個email(以第一個email當root)
                for(i in 1 .. account.lastIndex){
                    parentMap.put(account[i],account[1])
                }
            }
            //find overlapped and union
            //這個account的email們分兩部分：
            // 1.有出現在其他account裡：把這些重疊到的group記錄下來
            // 2.沒有出現在其他account裡：這部分的email自成一group
            //把這些group全部union起來
            else{
                val overlapped = mutableSetOf<String>()
                val theRest = mutableSetOf<String>()
                for(i in 1 .. account.lastIndex){
                    val root = findRoot(parentMap, account[i])
                    if(root != null){
                        overlapped.add(root)
                    }else{
                        theRest.add(account[i])
                    }
                }

                //(2)的部份，自己union起來成一個group
                union(parentMap, theRest)?.let {
                    overlapped.add(it)
                }

                //(1)和(2)全部union起來
                union(parentMap,overlapped)
            }
        }

        //整理output
        val r = mutableListOf<List<String>>()
        for((name, hashmap) in accountMap){
            val hash = HashMap<String, MutableList<String>>()
            for((email,parent) in hashmap){
                val root = findRoot(hashmap, email)
                hash.getOrPut(root!!,::mutableListOf).add(email)
            }

            for((_, value) in hash){
                val subResult = mutableListOf(name)
                subResult.addAll(value.sorted())
                r.add(subResult)
            }
        }
        return r
    }

    //固定把第一個點當作新的root，其他的點設定自己的parent為newRoot
    private fun union(map:HashMap<String, String>, setOfRoot:Set<String>):String?{
        if(setOfRoot.isEmpty())
            return null

        val newRoot = setOfRoot.elementAt(0)
        for(root in setOfRoot){
            map.put(root, newRoot)
        }
        return newRoot
    }

    //從一個點開始根本溯源把root找出來
    private fun findRoot(map:HashMap<String, String>, node:String):String?{
        if(map.get(node) == null) return null
        if(map.get(node) == node) return node

        //這裡用一個trick，因為這個map的最終用途是要找root，所以中間他的parent都不是重點所以在recursive的過程中，也同時去改寫map中的parent值
        val parent:String = map.get(node)!!
        map.put(node, findRoot(map, parent)!!)
        return map.get(node)
    }
}