package com.onebeyond.kata2022.solutions;

import com.onebeyond.kata2022.MorseDecoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class VippDaavMorseDecoder implements MorseDecoder {

    String dictionaryString = ".-     => a\n" +
            "-...   => b\n" +
            "-.-.   => c\n" +
            "-..    => d\n" +
            ".      => e\n" +
            "..-.   => f\n" +
            "--.    => g\n" +
            "....   => h\n" +
            "..     => i\n" +
            ".---   => j\n" +
            "-.-    => k\n" +
            ".-..   => l\n" +
            "--     => m\n" +
            "-.     => n\n" +
            "---    => o\n" +
            ".--.   => p\n" +
            "--.-   => q\n" +
            ".-.    => r\n" +
            "...    => s\n" +
            "-      => t\n" +
            "..-    => u\n" +
            "...-   => v\n" +
            ".--    => w\n" +
            "-..-   => x\n" +
            "-.--   => y\n" +
            "--..   => z\n" +
            ".----  => 1\n" +
            "..---  => 2\n" +
            "...--  => 3\n" +
            "....-  => 4\n" +
            ".....  => 5\n" +
            "-....  => 6\n" +
            "--...  => 7\n" +
            "---..  => 8\n" +
            "----.  => 9\n" +
            "-----  => 0";
    private Map<String, String> dictionary;
    public VippDaavMorseDecoder() {
        this.dictionary = new HashMap<>();
        Arrays.stream(this.dictionaryString.split("\n")).forEach((value) -> {
            String[] values = value.split("  => ");
            this.dictionary.put(values[0].trim(), values[1]);
        });
    }

    @Override
    public String decodeMisteryMessage(String inputMessage) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(inputMessage.split("   ")).forEach((wordEncoded) -> {
            Arrays.stream(wordEncoded.split(" ")).forEach((charEncoded) -> {
                sb.append(dictionary.get(charEncoded));
            });
            sb.append(" ");
        });
        return sb.toString().trim();
    }
}
