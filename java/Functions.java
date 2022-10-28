public class Functions {
    static boolean winGame(int[][] battleground, int playerHealth) {
        int sum = 0;
        for (int i = 0; i < battleground.length; i++) {
            for (int j = 0; j < battleground[i].length; j++) {
                sum += battleground[i][j];
            }
        }
        return (sum >= 0);
    }

    static void enemyPlacement(int enemyNum, int[] boardDim, int[][] enemyIndex,
            int[][] enemyIndexBattleground, int[][] battleground, int[] enemyHealthRange) {

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

            enemyIndexBattleground[randY][randX] = i;
            battleground[randY][randX] = enemyIndex[0][i];

        }

    }

    static void playerPlacement(int[] boardDim, int[][] battleground, int[] playerPos) {
        int randY = (int) Math.floor((boardDim[0] * Math.random()));
        int randX = (int) Math.floor((boardDim[1] * Math.random()));
        playerPos[0] = randY;
        playerPos[1] = randX;
        while (battleground[playerPos[0]][playerPos[1]] != 0) {
            playerPlacement(boardDim, battleground, playerPos);
            playerPos[0] = randY;
            playerPos[1] = randX;
        }
    }

    static void printArray(int[][] array) {

        StdOut.println();
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array[i].length; j++) {
                StdOut.print(array[i][j] + " ");
            }
            StdOut.println();
        }
        StdOut.println();

    }

    static boolean validSpot(int[][] battleground, boolean enemyMoveDir, int enemyMoveDist,
            int[] boardDim, int enemyX, int enemyY) {

        boolean valid = false;
        if (enemyMoveDir == true) {
            if ((enemyX + enemyMoveDist < boardDim[1]) || (enemyX + enemyMoveDist >= 0)) {

                if (battleground[enemyY][enemyX + enemyMoveDist] == 0) {
                    valid = true;
                }
            }
        } else if (enemyMoveDir == false) {
            if (enemyY + enemyMoveDist < boardDim[0] && enemyY + enemyMoveDist >= 0) {

                if (battleground[enemyY + enemyMoveDist][enemyX] == 0) {
                    valid = true;
                }
            }
        }
        return valid;
    }

    static void enemyAI(int[][] battleground, int[][] enemyIndexBattleground, int[][] enemyIndex,
            int[] playerPos, int enemyNum, int playerHealth, int damage, int[] boardDim) {

        for (int i = 0; i < enemyNum; i++) {

            int enemyHealth = enemyIndex[0][i];

            if (enemyHealth < 0) {

                int enemyY = enemyIndex[1][i];
                int enemyX = enemyIndex[2][i];
                int newEnemyY = 0;
                int newEnemyX = 0;

                int enemyPDisY = enemyY - playerPos[0];
                int enemyPDisX = enemyX - playerPos[1];

                if ((enemyPDisY == 0 && Math.abs(enemyPDisX) == 1)
                        || (enemyPDisX == 0 && Math.abs(enemyPDisX) == 1)) { // enemy damage potential
                    battleground[playerPos[0]][playerPos[1]] = battleground[playerPos[0]][playerPos[1]] - damage;
                    playerPos[2] -= damage;
                    StdOut.println(damage + " damage to you by enemy with health " + enemyHealth + "! ");

                } else { // deciding movement dir
                    int enemyMoveDist = (int) Math.ceil(3 * Math.random()); // distance to travel
                    boolean enemyMoveDir = (boolean) (.5 < Math.random()); // direction: T for x, F for y
                    boolean enemyMoveProb = (boolean) (.25 < Math.random()); // will enemy move?
                    // int[] boardDim = { 10, 10 }; // temp

                    if (enemyMoveProb == true) {
                        if (enemyMoveDir == true) {
                            if (enemyPDisX > 0) { // enemy is to right of P1
                                enemyMoveDist *= -1;
                            } else if (enemyMoveDir == false) {
                                if (enemyPDisY < 0) { // enemy is below player
                                    enemyMoveDist *= -1;
                                }
                            }
                        }

                        boolean valid = Functions.validSpot(battleground, enemyMoveDir, enemyMoveDist, boardDim, enemyX,
                                enemyY);
                        if (valid == true) { // updating move dir
                            if (enemyMoveDir == false) { // move in Y dir
                                // enemyIndexBattleground[enemyY][enemyX] = 0;
                                newEnemyY = enemyY + enemyMoveDist;

                            } else if (enemyMoveDir == true) { // move in X dir
                                newEnemyX = enemyX + enemyMoveDist;
                            }

                            // updating enemyIndex, batlteground, EIB
                            battleground[newEnemyY][newEnemyX] = enemyHealth;
                            battleground[enemyY][enemyX] = 0;
                            enemyIndexBattleground[newEnemyY][newEnemyX] = i;
                            enemyIndexBattleground[enemyY][enemyX] = 0;
                            enemyIndex[0][i] = enemyHealth;
                            enemyIndex[1][i] = newEnemyY;
                            enemyIndex[2][i] = newEnemyX;

                            // update battleground, enemyIndex, playerHealth, and enemyIndexBattleground

                            if (enemyMoveDir == true) { // movement in x dir
                                if (enemyMoveDist < 0) {
                                    StdOut.println(
                                            "Enemy with health " + enemyHealth + " moved one space to the left!");
                                } else if (enemyMoveDist > 0) {
                                    StdOut.println(
                                            "Enemy with health " + enemyHealth + " moved one space to the right!");
                                }

                            } else if (enemyMoveDir == false) {
                                if (enemyMoveDist < 0) {
                                    StdOut.println("Enemy with health " + enemyHealth + " moved one space up!");
                                } else if (enemyMoveDist > 0) {
                                    StdOut.println("Enemy with health " + enemyHealth + " moved one space down!");
                                }
                            }
                        }

                    }

                }
            }
        }
    }

    // static int[] enemyAttackChecker(int[][] battleground, int[][] enemyIndex,
    // int[] playerPos,
    // int enemyNum, int[] attackRangeDim, int attackPower) {

    // int pY = playerPos[0];
    // int pX = playerPos[1];
    // int atkRngY = attackRangeDim[0];
    // int atkRngX = attackRangeDim[1];
    // int[] result = { -1 }; // gives enemyIndex# of attacked or defeated enemy.
    // 2nd num is enemy's remaining
    // // health
    // int curBtg = battleground[atkRngY + pY][atkRngX + pX];
    // for (int i = 0; i < enemyNum; i++) {

    // if (curBtg != 0) {
    // if (enemyIndex[0][i] == curBtg) {
    // if (curBtg + attackPower >= 0) {
    // result[1] = 0;
    // } else {
    // result[1] = curBtg + attackPower;
    // }
    // result[0] = i;
    // }
    // }
    // }

    // return result;

    // }

    static void parseInput(int[][] battleground, int[] playerPos, int[] boardDim, int[][] enemyIndex,
            int[][] enemyIndexBattleground, int enemyNum, int attackPower, int[] resultParseInput, int[] validAttacks) {

        String uinput = StdIn.readString();

        int pY = playerPos[0];
        int pX = playerPos[1];
        int bY = boardDim[0];
        int bX = boardDim[1];

        int pYtest = pY;
        int pXtest = pX;

        // resultParseInput:
        // -2: break
        // -1: redo
        // 0: continue

        if (uinput.equals("0")) {
            resultParseInput[0] = -2;

        } else if (uinput.equals("w")) {
            pYtest = pY - 1;
            pXtest = pX;

            if (pYtest - 1 == 0) {
                resultParseInput[0] = -1;
            }

        } else if (uinput.equals("a")) {
            pYtest = pY;
            pXtest = pX - 1;

            if (pXtest - 1 == 0) {
                resultParseInput[0] = -1;
            }
        } else if (uinput.equals("s")) {
            pYtest = pY + 1;
            pXtest = pX;

            if (pYtest > bY) {
                resultParseInput[0] = -1;
            }

        } else if (uinput.equals("d")) {
            pYtest = pY;
            pXtest = pX + 1;
            if (pXtest > bX) {
                resultParseInput[0] = -1;
            }
        } else if (-1 != arrayComparer(validAttacks, Integer.parseInt(uinput))) { // checks if input is an attack

            if (uinput.equals("1")) {
                for (int iY = -1; iY <= 1; iY++) {
                    for (int iX = -1; iX <= 1; iX++) { // loop checks each position to see if it is an enemy.

                        playerOnEnemyAttackUpdater(battleground, enemyIndex, enemyIndexBattleground, playerPos, iX, iY,
                                enemyNum, attackPower, boardDim);

                    }
                }
            } else if (uinput.equals("2")) {

            }
        }

        if (resultParseInput[0] == -1) {

        } else if (resultParseInput[0] == -2) {

        }

    }

    static void playerOnEnemyAttackUpdater(int[][] battleground, int[][] enemyIndex, int[][] enemyIndexBattleground,
            int[] playerPos, int iX, int iY, int enemyNum, int attackPower, int[] boardDim) {

        int playerY = playerPos[0];
        int playerX = playerPos[1];
        int pYtemp = playerY + iY;
        int pXtemp = playerX + iX;
        int battgndRef = battleground[pYtemp][pXtemp];

        if (pYtemp > boardDim[0] || pYtemp < 0 ||
                pXtemp > boardDim[1] || pXtemp <= 0) {
        } else {
            for (int i = 0; i < enemyNum; i++) {
                if (enemyIndex[0][i] == battgndRef
                        && battgndRef != 0) {
                    if (battgndRef + attackPower >= 0) {
                        StdOut.println("Enemy with " + battgndRef + " health has been eliminated!");
                        battleground[pYtemp][pXtemp] = 0;
                        enemyIndexBattleground[pYtemp][pXtemp] = 0;
                        for (int j = 0; j < enemyIndex[i].length; j++) {
                            enemyIndex[j][i] = 0;
                        }
                    } else {
                        battleground[pYtemp][pXtemp] += attackPower;
                        enemyIndex[0][i] = battleground[pYtemp][pXtemp];
                        enemyIndex[0][i] += attackPower;
                        StdOut.println(attackPower + " damage to enemy with health " + enemyIndex[0][i]);
                    }
                }
            }
        }

    }

    static int[] intArrayMerger(int[] array1, int[] array2) {
        int array1length = array1.length;
        int array2length = array2.length;
        int[] bigArray = new int[array1length + array2length];

        for (int i = 0; i < array2.length; i++) {
            bigArray[i + array1length] = array2[i];
        }
        return bigArray;
    }

    static int arrayComparer(int[] input, int flag) {
        // function checks if any int flag matches any index in input array
        int win = -1;
        for (int i = 0; i < input.length; i++) {
            if (flag == input[i]) {
                win = i;
            }
        }
        return win;
    }
}

// static void printArray(int[][] array) {

// int[] maxIntLength = new int[array[1].length];
// int[] minIntLength = new int[array[1].length];
// String stringSpace = " ";
// String copy = stringSpace;

// for (int i = 0; i < array.length; i++) {
// for (int j = 0; j < array[0].length; j++) {
// maxIntLength[j] = Math.max(maxIntLength[j], array[i][j]);
// minIntLength[j] = Math.min(minIntLength[j], array[i][j]);
// }
// }

// for (int i = 0; i < array.length -1; i++) {
// if (Math.abs(maxIntLength[i]) - Math.abs(minIntLength[i]) > 10) {
// for (int k = 1; k < Math.log10(maxIntLength[i]) / 10; k += 1) {
// stringSpace += " ";
// }
// }
// for (int j = 0; j < array[i].length; j++) {
// System.out.print(array[i][j] + stringSpace);
// stringSpace = copy;
// }
// System.out.println();
// }
// System.out.println();

// }