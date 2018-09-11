package design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WordFilter {
    String[] words;
    HashMap<String, List<Integer>> prefixIndex = new HashMap<>();
    HashMap<String, List<Integer>> suffixIndex = new HashMap<>();

    public WordFilter(String[] words) {
        this.words = words;

        for (String word : words) {
            if (word == null) {
                continue;
            }

            int l = word.length();
            for (int i = 1; i < l; ++i) {
                String prefix = word.substring(0, i);
                if (!prefixIndex.containsKey(prefix)) {
                    prefixIndex.put(prefix, new LinkedList<Integer>());
                }
                prefixIndex.get(prefix).add(i);

                String suffix = word.substring(l - i - 1);
                if (!suffixIndex.containsKey(suffix)) {
                    suffixIndex.put(suffix, new LinkedList<Integer>());
                }
                suffixIndex.get(suffix).add(i);
            }
        }
    }

    public int f(String prefix, String suffix) {
        List<Integer> preffixed = prefixIndex.get(prefix);
        List<Integer> suffixed = prefixIndex.get(suffix);

        if (preffixed == null || suffixed == null) {
            return -1;
        }

        for (int i : preffixed) {
            if (suffixed.contains(i)) {
                return i;
            }
        }

        return -1;
    }
}
