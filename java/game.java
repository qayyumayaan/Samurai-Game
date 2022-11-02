public class game {
    public static void main(String[] args) {

        int[] boardDim = { 7, 6 }; // y by x

        int[][] battleground = new int[boardDim[0]][boardDim[1]];
        int[][] enemyIndexBattleground = new int[boardDim[0]][boardDim[1]];

        int enemyNum = 10; // formerly enemyNumber
        int[] enemyHealthRange = { 40, 100 }; // min, max
        int damage = 15; // enemy attack power
        int maxSpacesMoved = 1;

        // int playerNum = 1;
        int[] playerPos = { 0, 0, 150 }; // playerPos[2] = health
        int attackPower = 25;

        int[][] enemyIndex = new int[3][enemyNum];
        int[] validAttacks = { 1, 2, 3 };

        // routine that places enemies, makes enemyIndexBattleground, battleground, and
        Functions.enemyPlacement(enemyNum, boardDim, enemyIndex, enemyIndexBattleground, battleground,
                enemyHealthRange);

        // routine that places player
        Functions.playerPlacement(boardDim, enemyIndexBattleground, playerPos);
        battleground[playerPos[0]][playerPos[1]] = playerPos[2];

        /*
         * 
         */
        // GAMEPLAY
        int playerInputsLength = 2000;
        String[] playerInputs = new String[playerInputsLength];
        int[] count = new int[1];

        while (playerPos[2] > 0 || enemyNum > 0) {
            // parseInput
            int[] resultParseInput = { 0, 0 };
            boolean runRestFlag = true;
            boolean continueParseInputLoop = true;

            while (continueParseInputLoop == true) {
                Functions.printArray(enemyIndex);
                Functions.printArray(enemyIndexBattleground);
                Functions.printArray(battleground);


                StdOut.print("What will you do? ");

                Functions.parseInput(battleground, playerPos, boardDim, enemyIndex, enemyIndexBattleground, enemyNum,
                        attackPower, resultParseInput, validAttacks, playerInputs, count);

                if (resultParseInput[0] == -2) {
                    continueParseInputLoop = false;
                    runRestFlag = false;
                    break;
                } else if (resultParseInput[0] == -1) {
                    StdOut.println("You can't move here!");
                } else if (resultParseInput[0] == -3) {
                    StdOut.println("Unrecognized input. Please try again.");
                } else {
                    continueParseInputLoop = false;
                    // code for attacking, iterating through attacked enemies
                }
            }

            if (runRestFlag == false) {
                break;
            } else if (runRestFlag == true) {

                Functions.enemyAI(battleground, enemyIndexBattleground, enemyIndex,
                        playerPos, enemyNum, damage, boardDim, maxSpacesMoved);

                // Functions.printArray(enemyIndex);

                boolean win = Functions.winGame(battleground, playerPos[2]);
                if (win == true) {
                    StdOut.println("All enemies defeated. You win!");
                    break;
                }
                if (playerPos[2] <= 0) {
                    StdOut.println("Game over!");
                    break;
                }

            }
        }

        String[] finalPlayerInputs = new String[count[0]];
        for (int i = 0; i < count[0]; i++) {
            finalPlayerInputs[i] = playerInputs[i];
        }
        Functions.printArray(battleground);
        StdOut.println("Thank you so much for playing my game!");
        Functions.printArray(finalPlayerInputs);
        // Functions.printArray(enemyIndex);

    }
}
