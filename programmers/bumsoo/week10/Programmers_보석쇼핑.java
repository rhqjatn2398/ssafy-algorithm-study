import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {

    public static void main(String[] args) {
        new Solution().solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
    }

    class Candidate implements Comparable<Candidate> {
        int startIdx;
        int length;

        public Candidate(int startIdx, int length) {
            this.startIdx = startIdx;
            this.length = length;
        }

        @Override
        public int compareTo(Candidate o) {
            return this.length == o.length ? this.startIdx - o.startIdx : this.length - o.length;
        }
    }

    Map<String, Integer> gemCount;
    Set<String> set;
    PriorityQueue<Candidate> pq;

    public int[] solution(String[] gems) {
        int[] answer = null;

        gemCount = new HashMap<>();
        set = new HashSet<>();
        pq = new PriorityQueue<>();
        Collections.addAll(set, gems);

        int targetCnt = set.size();

        int left = 0, right = 0, cnt = 0; // right는 현재 고려중인 곳
        while (true) {
            if (right >= gems.length) {
                pq.add(new Candidate(left + 1, right - (left + 1)));
                break;
            }

            if (gemCount.containsKey(gems[right])) {

                if (gems[left].equals(gems[right])) {
                    left++;

                    while (gemCount.get(gems[left]) > 1) {
                        gemCount.put(gems[left], gemCount.getOrDefault(gems[left], 0) - 1);
                        left++;
                    }
                } else {
                    gemCount.put(gems[right], gemCount.getOrDefault(gems[right], 0) + 1);
                }

                right++;

            } else {
                gemCount.put(gems[right], gemCount.getOrDefault(gems[right], 0) + 1);
                right++;
                cnt++;
            }

            if (cnt == targetCnt) {
                pq.add(new Candidate(left + 1, right - (left + 1)));
            }
        }

        Candidate candidate = pq.poll();
        return new int[]{candidate.startIdx, candidate.startIdx + candidate.length};
    }
}