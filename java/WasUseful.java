public class WasUseful {
    static int[][] minMaxFinder(int[][] array) {
        int[][] minMax = new int[2][array[0].length];
        // minMax[0] = max, minMax[0] = min
        for (int X = 0; X < array[0].length; X++) {
            minMax[0][X] = array[0][X];
            minMax[1][X] = array[0][X];

            for (int Y = 0; Y < array.length; Y++) {
                if (array[Y][X] > minMax[0][X]) {
                    minMax[0][X] = array[Y][X];

                } else if (array[Y][X] < minMax[1][X]) {
                    minMax[1][X] = array[Y][X];
                }
            }
        }
        return minMax;
    }

    static int[][] numLength(int[][] array) {
        // identifies the length of each value of a 2d array.
        // Adds 1 extra char if negative

        int[][] result = new int[array.length][array[0].length];

        for (int Y = 0; Y < array.length; Y++) {
            for (int X = 0; X < array[0].length; X++) {
                int value = array[Y][X];
                int charCount = 0;
                if (value < 0) {
                    charCount++;
                }
                int valueMod = Math.abs(value);
                while (valueMod > 0) {
                    charCount++;
                    valueMod = valueMod / 10;
                }
                result[Y][X] = charCount;
            }
        }

        return result;

    }

    static void printArray(int[][] array) {
        int[][] lengthArray = numLength(array);
        int[][] minMax = minMaxFinder(lengthArray);
        int[][] invLengthArray = lengthArray;

        for (int X = 0; X < lengthArray[0].length; X++) {
            int maX = minMax[0][X];
            for (int Y = 0; Y < lengthArray.length; Y++) {
                invLengthArray[Y][X] = maX - invLengthArray[Y][X];
            }
        }

        String[][] whiteSpace = new String[array.length][array[0].length];
        for (int X = 0; X < array[0].length; X++) {
            for (int Y = 0; Y < array.length; Y++) { // for every x and y val in table
                whiteSpace[Y][X] = "  ";
                for (int Z = 0; Z < invLengthArray[Y][X]; Z++) {
                    whiteSpace[Y][X] += " ";
                }
            }
        }

        StdOut.println();
        for (int Y = 0; Y < array.length; Y++) {
            for (int X = 0; X < array[Y].length; X++) {
                StdOut.print(array[Y][X] + whiteSpace[Y][X]);
            }
            StdOut.println();
        }
        StdOut.println();

    }
}
