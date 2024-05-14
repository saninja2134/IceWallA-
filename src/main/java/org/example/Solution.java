package org.example;

import java.util.*;

public class Solution {
    private final Set<String> visited = new HashSet<>();
    private final int targetRow;
    private final int targetCol;
    private final char[][] matrix;
    private final Player player;
    private final String[] MOVE_SET = {"up", "down", "left", "right"};

    public Solution(char[][] matrix, Player player, int targetRow, int targetCol) {
        this.targetRow = targetRow;
        this.targetCol = targetCol;
        this.matrix = matrix;
        this.player = player;
    }

    public List<String> shortestPath() {
        // Initialize a priority queue to store the nodes to be explored
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::cost));

        // Initialize the starting node
        Node start = new Node(player.getPosition()[0], player.getPosition()[1], 0, null);
        pq.offer(start);
        visited.add(start.toString());

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // Check if we've reached the target
            if (current.row() == targetRow && current.col() == targetCol) {
                System.out.println("Steps Taken: " + (current.cost() + 1)); // Add 1 to account for the starting position
                System.out.println("Shortest path:");
                return reconstructPath(current);
            }

            // Explore the neighboring nodes
            for (String move : MOVE_SET) {
                int[] nextPos = getNextPosition(current.row(), current.col(), move);
                int nextRow = nextPos[0];
                int nextCol = nextPos[1];

                // Skip if the next position is out of bounds or is a wall
                if (nextRow < 0 || nextRow >= matrix.length || nextCol < 0 || nextCol >= matrix[0].length || matrix[nextRow][nextCol] == '0') {
                    continue;
                }

                // Create the next node and add it to the priority queue if it hasn't been visited
                Node next = new Node(nextRow, nextCol, current.cost() + 1, current);
                String nextNodeStr = next.toString();
                if (!visited.contains(nextNodeStr)) {
                    pq.offer(next);
                    visited.add(nextNodeStr);
                }
            }
        }

        // If we reach this point, the target is unreachable
        return Collections.emptyList();
    }

    // Helper method to get the next position based on the move
    private int[] getNextPosition(int row, int col, String move) {
        Player tempPlayer = new Player(matrix, row, col);
        return switch (move) {
            case "up" -> {
                tempPlayer.up();
                yield tempPlayer.getPosition();
            }
            case "down" -> {
                tempPlayer.down();
                yield tempPlayer.getPosition();
            }
            case "left" -> {
                tempPlayer.left();
                yield tempPlayer.getPosition();
            }
            case "right" -> {
                tempPlayer.right();
                yield tempPlayer.getPosition();
            }
            default -> throw new IllegalArgumentException("Invalid move: " + move);
        };
    }

    // Helper method to reconstruct the shortest path
    private List<String> reconstructPath(Node node) {
        List<String> path = new ArrayList<>();
        Node current = node;
        while (current.parent() != null) {
            // adding 1 to row and col to convert from 0-based index to 1-based index as it's a matrix
            path.add(0, "(" + (current.row()+1) + "," + (current.col()+1) + ")");
            current = current.parent();
        }
        path.add(0, "(" + (current.row()+1) + "," + (current.col()+1) + ")"); // Add the starting position
        return path;
    }

    // Helper class to represent a node in the search
    private record Node(int row, int col, int cost, Solution.Node parent) {
        @Override
        public String toString() {
            return row + "," + col;
        }
    }
}