package com.github.busiedpandora.mymusic.exception;

public class UnknownLanguageAbbreviationException extends Exception {
    public UnknownLanguageAbbreviationException(String langAbbr) {
        super("Unknown language abbreviation for: " + langAbbr);
    }
}
