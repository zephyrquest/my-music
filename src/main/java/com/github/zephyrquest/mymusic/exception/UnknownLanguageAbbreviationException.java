package com.github.zephyrquest.mymusic.exception;

public class UnknownLanguageAbbreviationException extends Exception {
    public UnknownLanguageAbbreviationException(String langAbbr) {
        super("Unknown language abbreviation for: " + langAbbr);
    }
}
