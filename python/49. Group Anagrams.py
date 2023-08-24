from typing import List


class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        map = {}
        for s in strs:
            sorted_s = ''.join(sorted(s))
            # r = ",".join([str(el) for el in table])
            map.setdefault(sorted_s,[]).append(s)
        # print(map)

        return list(map.values())

input = ["","b"]
solution = Solution()
print(solution.groupAnagrams(input))