package com.github.busiedpandora.mymusic.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Language {

    ENGLISH("en"), ITALIAN("it");

    private final String abbreviation;

    public static List<String> getAbbreviations() {
        return Arrays.stream(Language.values()).map(Language::getAbbreviation).collect(Collectors.toList());
    }

    public static Language getDefaultLanguage() {
        return ENGLISH;
    }

    Language(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

}
