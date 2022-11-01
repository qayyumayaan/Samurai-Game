public class game {
    public static void main(String[] args) {

        int[] boardDim = { 7, 6 }; // y by x

        int[][] battleground = new int[boardDim[0]][boardDim[1]];

        int enemyNum = 10; // formerly enemyNumber
        int[] enemyHealthRange = { 40, 100 }; // min, max
        int damage = 15; // enemy attack power

        // int playerNum = 1;
        int[] playerPos = { 0, 0, 150 }; // playerPos[2] = health
        int attackPower = 25;

        int[][] enemyIndexBattleground = battleground;
        int[][] enemyIndex = new int[4][enemyNum];
        int[] validAttacks = { 1, 2, 3 };
        // int[] result = { 0, 0 };

        // routine that places enemies, makes enemyIndexBattleground, battleground, and
        Functions.enemyPlacement(enemyNum, boardDim, enemyIndex, enemyIndexBattleground, enemyIndexBattleground,
                enemyHealthRange);
        // routine that places player
        Functions.playerPlacement(boardDim, enemyIndexBattleground, playerPos);
        battleground[playerPos[0]][playerPos[1]] = playerPos[2];

        /*
         * 
         */
        // GAMEPLAY

        // while (playerPos[2] > 0 || enemyNum > 0) {
        Functions.printArray(battleground);

        // // parseInput
        // int[] resultParseInput = { 0, 0 };
        // boolean runRestFlag = true;
        // boolean continueFlag = true;

        // while (continueFlag == true) {
        // StdOut.println("What will you do? ");

        // // Functions.parseInput();

        // if (resultParseInput[0] == -2) {
        // continueFlag = false;
        // runRestFlag = false;
        // break;
        // } else if (resultParseInput[0] == -1) {
        // StdOut.println("You can't move here!");
        // } else {
        // continueFlag = false;
        // battleground[resultParseInput[0]][resultParseInput[1]] =
        // battleground[playerPos[0]][playerPos[1]];
        // battleground[playerPos[0]][playerPos[1]] = 0;
        // playerPos[0] = resultParseInput[0];
        // playerPos[1] = resultParseInput[1];

        // // code for attacking, iterating through attacked enemies
        // }
        // }

        // if (runRestFlag == false) {
        // break;
        // } else if (runRestFlag == true) {
        // Functions.printArray(battleground);

        // Functions.enemyAI(battleground, enemyIndexBattleground, enemyIndex,
        // playerPos, enemyNum, damage, boardDim);

        // boolean win = Functions.winGame(battleground, playerPos[2]);
        // if (win == true) {
        // StdOut.println("All enemies defeated. You win!");
        // break;
        // }
        // if (playerPos[2] <= 0) {
        // StdOut.println("Game over!");
        // break;
        // }

        // }
        // }

        // Functions.printArray(battleground);
        // StdOut.println("Thank you so much for playing my game!");

    }
}
