import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // key: Word; value: least number transformations from begin to Word
        Map<String, Integer> words = new HashMap<>();
        for (String word: wordList) {
            words.put(word, 0);
        }
        if (!words.containsKey(endWord)) return 0;
        Queue<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        int step = 0;
        while (!q.isEmpty()) {
            step++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                char[] word = q.poll().toCharArray();
                for (int j = 0; j < word.length; j++) {
                    char ori = word[j];
                    for (char k = 'a'; k <= 'z'; k++) {
                        word[j] = k;
                        String nextWord = new String(word);
                        if (words.containsKey(nextWord) && words.get(nextWord) == 0) {
                            words.put(nextWord, step);
                            q.offer(nextWord);
                            if (nextWord.equals(endWord)) return step;
                        }
                    }
                    word[j] = ori;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        WordLadder o = new WordLadder();
        List<String> list  = Arrays.asList("bd","dc","dd","ca");
        o.ladderLength("bd", "dc", list);
    }
}