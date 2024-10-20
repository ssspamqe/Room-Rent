package com.ssspamqe.roomrent.configuration.liquibase;

import java.util.Comparator;

public class ScriptComparator implements Comparator<String> {

    @Override
    public int compare(String file1, String file2) {
        return file1.compareTo(file2);
    }

    private int parseNumber(String fileName) {
        String[] parts = fileName.split("_");
        return Integer.parseInt(parts[0]);
    }
}
