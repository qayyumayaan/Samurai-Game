public class game {
    public static void main(String[] args) {

        int[] boardDim = { 7, 6 }; // y by x

        int[][] battleground = new int[boardDim[0]][boardDim[1]];

        int enemyNum = 10; // formerly enemyNumber
        int[] enemyHealthRange = { 40, 100 }; // min, max
        int damage = 15; // enemy attack power

        // int playerNum = 1;
        int playerHealth = 150;
        int attackPower = 25;

        int[][] enemyIndexBattleground = battleground;
        int[][] enemyIndex = new int[4][enemyNum];
        // int[] result = { 0, 0 };

        // routine that places enemies, makes enemyIndexBattleground, battleground, and
        // enemyIndex
        for (int i = 0; i < enemyNum; i++) {
            int randY = (int) Math.floor((boardDim[0] * Math.random()));
            int randX = (int) Math.floor((boardDim[1] * Math.random()));

            if (battleground[randY][randX] != 0) {
                randY = (int) Math.floor(boardDim[0] * Math.random());
                randX = (int) Math.floor(boardDim[1] * Math.random());
            }

            int health = (int) Math.round(
                    (enemyHealthRange[1] - enemyHealthRange[0])
                            * Math.random() + enemyHealthRange[0]);

            enemyIndex[0][i] = -1 * health;
            enemyIndex[1][i] = randY;
            enemyIndex[2][i] = randX;

            // Functions.printArray(enemyIndex);

            enemyIndexBattleground[randY][randX] = i;
            battleground[randY][randX] = enemyIndex[0][i];

        }

        // routine that places player
        int randY = (int) Math.floor((boardDim[0] * Math.random()));
        int randX = (int) Math.floor((boardDim[1] * Math.random()));
        int[] playerPos = { randY, randX };
        if (battleground[playerPos[0]][playerPos[1]] != 0) {
            randY = (int) Math.floor((boardDim[0] * Math.random()));
            randX = (int) Math.floor((boardDim[1] * Math.random()));
            playerPos[0] = randY;
            playerPos[1] = randX;
        }
        battleground[randY][randX] = playerHealth;

        /*
         * 
         */
        // GAMEPLAY

        while (playerHealth > 0 || enemyNum > 0) {
            Functions.printArray(battleground);

            // parseInput
            int[] resultParseInput = { -1, 2 };
            boolean runRestFlag = true;
            boolean continueFlag = true;

            while (continueFlag == true) {
                StdOut.println("What will you do? ");

                resultParseInput = Functions.parseInput(battleground, playerPos, boardDim, enemyIndex, enemyNum,
                        attackPower);

                if (resultParseInput[0] == -2) {
                    continueFlag = false;
                    runRestFlag = false;
                    break;
                } else if (resultParseInput[0] == -1) {
                    StdOut.println("You can't move here!");
                } else {
                    continueFlag = false;
                    battleground[resultParseInput[0]][resultParseInput[1]] = battleground[playerPos[0]][playerPos[1]];
                    battleground[playerPos[0]][playerPos[1]] = 0;
                    playerPos[0] = resultParseInput[0];
                    playerPos[1] = resultParseInput[1];

                    // code for attacking, iterating through attacked enemies
                }
            }

            if (runRestFlag == false) {
                break;
            } else if (runRestFlag == true) {
                Functions.printArray(battleground);

                // enemy AI
                int[][] enemyAIreturn = Functions.enemyAI(battleground, enemyIndexBattleground, enemyIndex,
                        playerPos, enemyNum, playerHealth, damage, boardDim);

                playerHealth = enemyAIreturn[0][0];
                for (int i = 0; i < enemyNum; i++) {

                    int enemyHealth = enemyAIreturn[1][i];
                    if (enemyHealth < 0) {
                        int newEnemyY = enemyAIreturn[2][i];
                        int newEnemyX = enemyAIreturn[3][i];
                        int enemyY = enemyAIreturn[4][i];
                        int enemyX = enemyAIreturn[5][i];

                        battleground[newEnemyY][newEnemyX] = enemyHealth;
                        battleground[enemyY][enemyX] = 0;

                        enemyIndexBattleground[newEnemyY][newEnemyX] = i;
                        enemyIndexBattleground[enemyY][enemyX] = 0;
                        enemyIndex[0][i] = enemyHealth;
                        enemyIndex[1][i] = newEnemyY;
                        enemyIndex[2][i] = newEnemyX;
                    }
                }

                boolean win = Functions.winGame(battleground, playerHealth);
                if (win == true) {
                    StdOut.println("All enemies defeated. You win!");
                    break;
                }
                if (playerHealth <= 0) {
                    StdOut.println("Game over!");
                    break;
                }

            }
        }

        Functions.printArray(battleground);
        StdOut.println("Thank you so much for playing my game!");

    }
}
