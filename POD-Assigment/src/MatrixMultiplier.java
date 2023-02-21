import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrixMultiplier {
    private final int[][] A;
    private final int[][] B;
    private final int[][] C;

    private final int n;
    private final int numThreads;

    public MatrixMultiplier(int[][] A, int[][] B, int numThreads) {
        this.A = A;
        this.B = B;
        this.n = A.length;
        this.C = new int[n][n];
        this.numThreads = numThreads;
    }

    public int[][] multiply() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        // Create tasks for each row of the output matrix
        for (int i = 0; i < n; i++) {
            final int row = i;
            executor.execute(() -> multiplyRow(row));
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        return C;
    }

    private void multiplyRow(int i) {
        for (int j = 0; j < n; j++) {
            int sum = 0;
            for (int k = 0; k < n; k++) {
                sum += A[i][k] * B[k][j];
            }
            C[i][j] = sum;
        }
    }
    public static class Main {
        public static void main(String[] args) throws InterruptedException {
            int[][] A = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            int[][] B = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
            int numThreads = 2;

            MatrixMultiplier multiplier = new MatrixMultiplier(A, B, numThreads);
            int[][] C = multiplier.multiply();

            // Print the result matrix
            for (int[] row : C) {
                for (int element : row) {
                    System.out.print(element + " ");
                }
                System.out.println();
            }
        }
    }

}
