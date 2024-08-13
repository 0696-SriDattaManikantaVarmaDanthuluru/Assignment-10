class MatrixMultiplication {
    private static final int MATRIX_SIZE = 3;

        public static void main(String[] args)throws InterruptedException {
            int[][] matrixA = {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9}
            };

            int[][] matrixB = {
                    {9, 8, 7},
                    {6, 5, 4},
                    {3, 2, 1}
            };

            int[][] result = new int[MATRIX_SIZE][MATRIX_SIZE];
            Thread[] threads = new Thread[MATRIX_SIZE * MATRIX_SIZE];
            int threadCount = 0;

            for (int i = 0; i < MATRIX_SIZE; i++) {
                for (int j = 0; j < MATRIX_SIZE; j++){
                    threads[threadCount] = new Thread(new Worker(matrixA, matrixB, result, i, j));
                    threads[threadCount].start();
                    threadCount++;
            }
        }


            for (int i = 0; i < threadCount; i++) {

                    threads[i].join();

            }

            System.out.println("Result matrix:");
            for (int i = 0; i < MATRIX_SIZE; i++) {
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    class Worker implements Runnable {
        private int[][] matrixA;
        private int[][] matrixB;
        private int[][] result;
        private int row;
        private int col;

        public Worker(int[][] matrixA, int[][] matrixB, int[][] result, int row, int col) {
            this.matrixA = matrixA;
            this.matrixB = matrixB;
            this.result = result;
            this.row = row;
            this.col = col;
        }

        @Override
        public synchronized void run(){
            result[row][col] = 0;
            for (int k = 0; k < matrixA[0].length; k++) {
                result[row][col] += matrixA[row][k] * matrixB[k][col];
            }
        }
    }

