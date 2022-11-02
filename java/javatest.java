public class javatest {
    public static void arrayMod(int[][] input) {
        int randY = (int) Math.floor(4 * Math.random());
        input[1][0] = randY;
    }

    public static void main(String[] args) {
        // int[] boardDim = { 6, 7 };
        // int[][] board = { { 1, 20, 1, 3 }, { 2, 3, 40, 60 } };
        int[][] array = { { 1, -100, 1, 100 }, { 10, 20, 10, 20 }, { 3, 4, 5, 6 } };

        // Functions.printArray(array);
        int F = 0;
        for (int i = 0; i < 100000; i++) {
            boolean enemyMoveDir = (boolean) (.5 < Math.random());
            if (enemyMoveDir == false) {
                F++;
            }
        }
        StdOut.println(F);
    }

}