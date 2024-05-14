package org.example;

public class Player {
    private final int[] position;
    private final char[][] matrix;

    public Player(char[][]matrix, int row, int col) {
        this.position = new int[]{row, col};
        this.matrix = matrix;
    }

    public int[] getPosition() {
        return position;
    }



    public void up(){
        while(position[0] > 0 && matrix[position[0] - 1][position[1]] != '0') {
            if (matrix[position[0]][position[1]] != 'F') {
                position[0]--;
            }else{
                break;
            }
        }
    }

    public void down(){
        while (position[0] < matrix.length - 1 && matrix[position[0] + 1][position[1]] != '0') {
            if (matrix[position[0]][position[1]] != 'F'){
                    position[0]++;
            }else{
                break;
            }
        }
    }

    public void left(){
        while (position[1] > 0 && matrix[position[0]][position[1] - 1] != '0') {
            if (matrix[position[0]][position[1]] != 'F') {
                position[1]--;
            }else{
                break;
            }
        }
    }

    public void right(){
        while (position[1] < matrix[0].length - 1 && matrix[position[0]][position[1] + 1] != '0') {
            if (matrix[position[0]][position[1]] != 'F') {
                position[1]++;
            }else{
                break;
            }
        }
    }
}
