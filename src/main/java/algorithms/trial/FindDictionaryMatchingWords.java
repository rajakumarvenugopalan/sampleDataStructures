package algorithms.trial;

import java.util.HashSet;
import java.util.Set;

public class FindDictionaryMatchingWords {

    private Set<String> dictionaryInput = new HashSet<>();

    {
        dictionaryInput.add("i");
        dictionaryInput.add("am");
        dictionaryInput.add("a");
        dictionaryInput.add("ace");
        dictionaryInput.add("sample");
        dictionaryInput.add("the");
    }

    public String findMatchingSplitOfDictionaryWords(String input) {
        input = input.toLowerCase();
        int[][] splitPositions = new int[input.length()][input.length()];
        for(int curLength = 1; curLength <= input.length(); curLength++) {
            for(int curStart = 0; curStart + curLength <= input.length(); curStart++) {
                findMatchingLimits(input, splitPositions, curStart, curLength);
            }
        }
        if(splitPositions[0][input.length() - 1] != 0) {
            String output = "";
            int totalCharsCopied = 0;
            while(totalCharsCopied < input.length()) {
                int curWordLength = splitPositions[totalCharsCopied][input.length() -1];
                String currentWord = input.substring(totalCharsCopied, totalCharsCopied + curWordLength);
                totalCharsCopied += currentWord.length();
                output += currentWord + " ";
            }
            return output;
        }
        else {
            return "Not a proper Dictionary Sequence";
        }
    }

    private void findMatchingLimits(String input, int[][] splitPositions, int startIndex, int lengthOfCurWord) {
        String curWord = input.substring(startIndex, startIndex + lengthOfCurWord);
        if(dictionaryInput.contains(curWord)) {
            splitPositions[startIndex][startIndex + lengthOfCurWord - 1] = curWord.length();
        }else {
            if(lengthOfCurWord > 1) {
                if(checkIfSplitWordsInDictionary(curWord, startIndex, splitPositions)) {
                    return;
                }
            }
        }
    }

    private boolean checkIfSplitWordsInDictionary(String curWord, int originalStartIndex, int[][] splitPositions) {
        for(int length = 1; length <= curWord.length() -1; length++) {
            if(splitPositions[originalStartIndex][originalStartIndex + length-1] != 0
                    && splitPositions[originalStartIndex+length][originalStartIndex + curWord.length()-1] != 0) {
                splitPositions[originalStartIndex][originalStartIndex + curWord.length()-1] = length;
                return true;
            }
        }
        return false;
    }

    public static void main(String... args) {
        FindDictionaryMatchingWords findDictionaryMatchingWords = new FindDictionaryMatchingWords();
        System.out.println("Iamace --> " + findDictionaryMatchingWords.findMatchingSplitOfDictionaryWords("Iamace"));
        System.out.println("Iamtheace --> " + findDictionaryMatchingWords.findMatchingSplitOfDictionaryWords("Iamtheace"));
        System.out.println("Iamsampleace123 --> " + findDictionaryMatchingWords.findMatchingSplitOfDictionaryWords("Iamsampleace123"));
        System.out.println("aaaaaaiaam --> " + findDictionaryMatchingWords.findMatchingSplitOfDictionaryWords("aamaaamaiaam"));
    }
}
