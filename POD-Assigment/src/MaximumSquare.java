import java.util.Stack;

public class MaximumSquare {
    private static final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int rows;
    private int cols;
    private int[][] matrix;

    public MaximumSquare(int[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
    }

    public int findMaximumSquare() {
        int[][] distance = new int[rows][cols];
        int maxSquare = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    distance[i][j] = getMinimumDistance(distance, i, j);
                    maxSquare = Math.max(maxSquare, distance[i][j] * distance[i][j]);
                }
            }
        }

        return maxSquare;
    }

    private int getMinimumDistance(int[][] distance, int i, int j) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{i, j});

        while (!stack.isEmpty()) {
            int[] point = stack.pop();
            int row = point[0];
            int col = point[1];

            for (int[] dir : DIRS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && distance[newRow][newCol] > distance[row][col] + 1) {
                    distance[newRow][newCol] = distance[row][col] + 1;
                    stack.push(new int[]{newRow, newCol});
                }
            }
        }

        return distance[i][j];
    }
}

