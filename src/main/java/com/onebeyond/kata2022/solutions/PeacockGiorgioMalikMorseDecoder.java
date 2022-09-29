package com.onebeyond.kata2022.solutions;

import com.onebeyond.kata2022.MorseDecoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PeacockGiorgioMalikMorseDecoder implements MorseDecoder {
    static Map<String, String> morse = new HashMap<>();

    static {
        morse.put(".-", "a");
        morse.put("-...", "b");
        morse.put("-.-.", "c");
        morse.put("-..", "d");
        morse.put(".", "e");
        morse.put("..-.", "f");
        morse.put("--.", "g");
        morse.put("....", "h");
        morse.put("..", "i");
        morse.put(".---", "j");
        morse.put("-.-", "k");
        morse.put(".-..", "l");
        morse.put("--", "m");
        morse.put("-.", "n");
        morse.put("---", "o");
        morse.put(".--.", "p");
        morse.put("--.-", "q");
        morse.put(".-.", "r");
        morse.put("...", "s");
        morse.put("-", "t");
        morse.put("..-", "u");
        morse.put("...-", "v");
        morse.put(".--", "w");
        morse.put("-..-", "x");
        morse.put("-.--", "y");
        morse.put("--..", "z");
        morse.put("-----", "0");
        morse.put(".----", "1");
        morse.put("..---", "2");
        morse.put("...--", "3");
        morse.put("....-", "4");
        morse.put(".....", "5");
        morse.put("-....", "6");
        morse.put("--...", "7");
        morse.put("---..", "8");
        morse.put("----.", "9");
    }

    @Override
    public String decodeMisteryMessage(String inputMessage) {
        return Arrays.stream(inputMessage.split(Pattern.quote("   ")))
                .map(w -> Arrays.stream(w.split(Pattern.quote(" ")))
                .map(letter -> morse.getOrDefault(letter, ""))
                .collect(Collectors.joining()))
                .collect(Collectors.joining(" "));
    }


}