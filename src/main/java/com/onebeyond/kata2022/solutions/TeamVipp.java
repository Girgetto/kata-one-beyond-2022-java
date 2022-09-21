package com.onebeyond.kata2022.solutions;

import com.onebeyond.kata2022.MorseDecoder;

public class TeamVipp implements MorseDecoder {

    @Override
    public String decodeMisteryMessage(String inputMessage) {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "expected output for " + inputMessage;
    }
}
