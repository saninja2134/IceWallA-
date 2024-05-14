package org.example;

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime(); // Start measuring time

        // Grabbing all useful data from the file
        ReadMap readMap = new ReadMap("src/main/java/org/example/benchmark_series/puzzle_2560.txt");
        char[][] matrix = readMap.getData();
        int[] startCoords = readMap.getStart();
        int[] targetCoords = readMap.getTarget();

        int startRow = startCoords[0];
        int startCol = startCoords[1];
        int targetRow = targetCoords[0];
        int targetCol = targetCoords[1];

        // Using the player as the ruleset for the pathfinding algorithm
        Player player = new Player(matrix, startRow, startCol);
        Solution solution = new Solution(matrix, player, targetRow, targetCol);
        System.out.println(solution.shortestPath());

        // Runtime to check how long it takes to run the program - for benchmarking purposes
        long endTime = System.nanoTime(); // Stop measuring time
        long duration = (endTime - startTime) / 1_000_000; // Calculate duration in milliseconds
        System.out.println("Runtime: " + duration + " milliseconds");
    }
}
