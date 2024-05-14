package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadMap {
    private final char[][] data;
    private int startRow;
    private int startCol;
    private int targetRow;
    private int targetCol;

    public ReadMap(String filename) {
        // First pass to get dimensions
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int numRows = 0;
            int maxLength = 0; // Track the maximum length of lines
            while ((line = reader.readLine()) != null) {
                numRows++;
                maxLength = Math.max(maxLength, line.length());
            }
            reader.close();

            // Initialize the char[][] with dimensions
            data = new char[numRows][maxLength];

            // Second pass to fill data and find start/target
            reader = new BufferedReader(new FileReader(filename));
            int rowNum = 0;
            while ((line = reader.readLine()) != null) {
                if (line.contains("S")) {
                    startRow = rowNum;
                    startCol = line.indexOf('S');
                }
                if (line.contains("F")) {
                    targetRow = rowNum;
                    targetCol = line.indexOf('F');
                }
                data[rowNum] = line.toCharArray();
                rowNum++;
            }
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int[] getStart() {
        return new int[]{startRow, startCol};
    }

    public int[] getTarget() {
        return new int[]{targetRow, targetCol};
    }

    public char[][] getData() {
        return data;
    }
}
