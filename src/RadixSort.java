import java.util.*;

class RadixSortMSD {

    /**
     * Sorts a list of words using MSD radix sort.
     *
     * @param words
     *     The list of words to sort.
     * @return The sorted list of words.
     * @throws NullPointerException
     *     If `words` equals `null`.
     */
    static List<String> sort(List<String> words) {
        if (words == null) throw new NullPointerException();
        return sortByChar(words, 0);
    }

    private static List<String> sortByChar(List<String> words, int pos) {
        if (words.size() < 2) return words;

        // initialize the buckets for 26 letters
        List<List<String>> buckets = new ArrayList<>(26);
        for (int i = 0; i < 26; i++)
            buckets.add(new ArrayList<>());

        // puts the word into its corresponding bucket
        List<String> result = new ArrayList<>(words.size());
        
        for (String word : words) {
            if (pos < word.length()) {
                int bucket = word.charAt(pos) - 'a';
                buckets.get(bucket).add(word);
            } else {
                result.add(word);
            }
        }

        for (List<String> bucket : buckets)
            result.addAll(sortByChar(bucket, pos + 1));

        return result;
    }
}

