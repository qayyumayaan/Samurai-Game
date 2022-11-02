public class Functions {
    static boolean winGame(int[][] battleground, int playerHealth) {
        int sum = 0;
        for (int i = 0; i < battleground.length; i++) {
            for (int j = 0; j < battleground[i].length; j++) {
                sum += battleground[i][j];
            }
        }
        sum -= playerHealth;
        return (sum >= 0);
    }

    static void printArray(int[][] array) {

        StdOut.println();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                StdOut.print(array[i][j] + "\t");
            }
            StdOut.println();
        }
        StdOut.println();

    }

    static void printArray(int[] array) {

        StdOut.println();
        for (int i = 0; i < array.length; i++) {
            StdOut.print(array[i] + "\t");
        }
        StdOut.println();

    }

    static void printArray(String[] array) {

        StdOut.println();
        for (int i = 0; i < array.length; i++) {
            StdOut.print(array[i] + " ");
        }
        StdOut.println();

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
                break;
            }
        }
        return win;
    }

    static void enemyPlacement(int enemyNum, int[] boardDim, int[][] enemyIndex,
            int[][] enemyIndexBattleground, int[][] battleground, int[] enemyHealthRange) {

        for (int i = 0; i < enemyNum; i++) {
            int randY = (int) Math.floor((boardDim[0] * Math.random()));
            int randX = (int) Math.floor((boardDim[1] * Math.random()));

            while (battleground[randY][randX] != 0) {
                randY = (int) Math.floor(boardDim[0] * Math.random());
                randX = (int) Math.floor(boardDim[1] * Math.random());
            }
            enemyIndexBattleground[randY][randX] = i + 1; // important!

            int health = (int) Math.round(
                    (enemyHealthRange[1] - enemyHealthRange[0])
                            * Math.random() + enemyHealthRange[0]);

            enemyIndex[0][i] = -1 * health;
            enemyIndex[1][i] = randY;
            enemyIndex[2][i] = randX;

            battleground[randY][randX] = -1 * health;

        }

    }

    static void playerPlacement(int[] boardDim, int[][] battleground, int[] playerPos) {
        int randY = (int) Math.floor((boardDim[0] * Math.random()));
        int randX = (int) Math.floor((boardDim[1] * Math.random()));
        playerPos[0] = randY;
        playerPos[1] = randX;
        if (battleground[playerPos[0]][playerPos[1]] == 0) {
            battleground[playerPos[0]][playerPos[1]] = playerPos[2];
        } else {
            playerPlacement(boardDim, battleground, playerPos);
        }
    }

    static boolean validEnemyMovementSpot(int[][] battleground, boolean enemyMoveDir, int enemyMoveDist,
            int[] boardDim, int enemyX, int enemyY) {

        boolean valid = false;
        if (enemyMoveDir == true) {
            if ((enemyX + enemyMoveDist < boardDim[1]) && (enemyX + enemyMoveDist >= 0)) {
                // problematic code
                if (battleground[enemyY][enemyX + enemyMoveDist] == 0) {
                    valid = true;
                }
            }
        } else if (enemyMoveDir == false) {
            if ((enemyY + enemyMoveDist < boardDim[0]) && (enemyY + enemyMoveDist >= 0)) {

                if (battleground[enemyY + enemyMoveDist][enemyX] == 0) {
                    valid = true;
                }
            }
        }
        return valid;
    }

    static void enemyAI(int[][] battleground, int[][] enemyIndexBattleground, int[][] enemyIndex,
            int[] playerPos, int enemyNum, int damage, int[] boardDim, int maxSpacesMoved) {

        for (int i = 0; i < enemyNum; i++) {

            int enemyHealth = enemyIndex[0][i];

            if (enemyHealth < 0) {
                int newEnemyY = enemyIndex[1][i];
                int newEnemyX = enemyIndex[2][i];
                int enemyPDisY = enemyIndex[1][i] - playerPos[0];
                int enemyPDisX = enemyIndex[2][i] - playerPos[1];

                boolean enemyMoveProb = (boolean) (.75 < Math.random()); // will enemy move? x% < random() == x% yes

                if (enemyMoveProb == true) {

                    if ((enemyPDisY == 0 && Math.abs(enemyPDisX) == 1)
                            || (enemyPDisX == 0 && Math.abs(enemyPDisX) == 1)) { // enemy -> player damage potential
                        battleground[playerPos[0]][playerPos[1]] = battleground[playerPos[0]][playerPos[1]] - damage;
                        playerPos[2] -= damage;
                        StdOut.println(damage + " damage to you by enemy with health " + enemyHealth + "! ");

                    } else { // deciding movement dir
                        boolean valid = false;
                        boolean enemyMoveDir = false;
                        int enemyMoveDist = 0;

                        while (valid == false) { // fix this loop!!!
                            double rand2 = Math.random();
                            if (maxSpacesMoved == 1) {
                                enemyMoveDist = 1;
                            } else {
                                double rand = Math.random();
                                enemyMoveDist = (int) Math.ceil(maxSpacesMoved * rand); // distance to travel
                            }
                            enemyMoveDir = (boolean) (.5 < rand2); // direction: T for x, F for y
                            // int[] boardDim = { 10, 10 }; // temp

                            valid = Functions.validEnemyMovementSpot(battleground, enemyMoveDir, enemyMoveDist,
                                    boardDim, enemyIndex[2][i], enemyIndex[1][i]);

                        }

                        if (valid == true) { // updating movement
                            if (enemyMoveDir == false) { // move in Y dir
                                newEnemyY = enemyIndex[1][i] + enemyMoveDist;
                            } else if (enemyMoveDir == true) { // move in X dir
                                newEnemyX = enemyIndex[2][i] + enemyMoveDist;
                            }
                            // updating enemyIndex, battleground, EIB
                            enemyIndexBattleground[newEnemyY][newEnemyX] = i + 1;
                            enemyIndexBattleground[enemyIndex[1][i]][enemyIndex[2][i]] = 0;

                            battleground[newEnemyY][newEnemyX] = enemyIndex[0][i];
                            battleground[enemyIndex[1][i]][enemyIndex[2][i]] = 0;

                            int[] enemyPosHolder = { newEnemyY, newEnemyX };
                            enemyIndex[1][i] = enemyPosHolder[0];
                            enemyIndex[2][i] = enemyPosHolder[1];

                            if (enemyMoveDir == true) { // movement in x dir
                                if (enemyMoveDist < 0) {
                                    StdOut.println(
                                            "Enemy with health " + enemyHealth + " moved " + Math.abs(enemyMoveDist)
                                                    + " space to the left! ");
                                } else if (enemyMoveDist > 0) {
                                    StdOut.println(
                                            "Enemy with health " + enemyHealth + " moved " + Math.abs(enemyMoveDist)
                                                    + " space to the right! ");
                                }
                            } else if (enemyMoveDir == false) { // move in y dir
                                if (enemyMoveDist < 0) {
                                    StdOut.println("Enemy with health " + enemyHealth + " moved "
                                            + Math.abs(enemyMoveDist) + " space up! ");
                                } else if (enemyMoveDist > 0) {
                                    StdOut.println("Enemy with health " + enemyHealth + " moved "
                                            + Math.abs(enemyMoveDist) + " space down! ");
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
            int[][] enemyIndexBattleground, int enemyNum, int attackPower, int[] resultParseInput, int[] validAttacks,
            String[] playerInputs, int[] count) {

        String uinput = StdIn.readString();
        playerInputs[count[0]] = uinput;
        count[0]++;

        resultParseInput[0] = -4;
        int pY = playerPos[0];
        int pX = playerPos[1];
        int bY = boardDim[0];
        int bX = boardDim[1];

        int pYtest = pY;
        int pXtest = pX;

        // resultParseInput:
        // -4: default
        // -3: error
        // -2: break
        // -1: redo
        // 0: continue

        if (uinput.equals("0")) {
            resultParseInput[0] = -2;
            return;

        } else if (uinput.equals("w")) {
            pYtest = pY - 1;
            pXtest = pX;

            if (pYtest < 0) {
                resultParseInput[0] = -1;
            } else if (battleground[pYtest][pXtest] != 0) {
                resultParseInput[0] = -1;
            } else {
                resultParseInput[0] = 0;
            }

        } else if (uinput.equals("a")) {
            pYtest = pY;
            pXtest = pX - 1;

            if (pXtest < 0) {
                resultParseInput[0] = -1;
            } else if (battleground[pYtest][pXtest] != 0) {
                resultParseInput[0] = -1;
            } else {
                resultParseInput[0] = 0;
            }
        } else if (uinput.equals("s")) {
            pYtest = pY + 1;
            pXtest = pX;

            if (pYtest >= bY) {
                resultParseInput[0] = -1;
            } else if (battleground[pYtest][pXtest] != 0) {
                resultParseInput[0] = -1;
            } else {
                resultParseInput[0] = 0;
            }

        } else if (uinput.equals("d")) {
            pYtest = pY;
            pXtest = pX + 1;
            if (pXtest >= bX) {
                resultParseInput[0] = -1;
            } else if (battleground[pYtest][pXtest] != 0) {
                resultParseInput[0] = -1;
            } else {
                resultParseInput[0] = 0;
            }

        }

        if (resultParseInput[0] == 0) { // moving player routine
            enemyIndexBattleground[pYtest][pXtest] = playerPos[2];
            enemyIndexBattleground[pY][pX] = 0;
            battleground[pYtest][pXtest] = playerPos[2];
            battleground[pY][pX] = 0;
            playerPos[0] = pYtest;
            playerPos[1] = pXtest;
        } else if (resultParseInput[0] == -4) {
            int uinputInt = 0;
            try {
                uinputInt = Integer.parseInt(uinput);
            } catch (NumberFormatException e) {
                resultParseInput[0] = -3;
                return;
            }
            if (-1 != arrayComparer(validAttacks, uinputInt)) { // is input an attack?
                if (uinput.equals("1")) {
                    for (int iY = -1; iY <= 1; iY++) {
                        for (int iX = -1; iX <= 1; iX++) { // loop checks each position to see if it is an enemy.
                            if (inValidSpot(boardDim, playerPos, iY, iX) == true) {
                                playerAttackedEnemy(battleground, enemyIndex, enemyIndexBattleground, playerPos, iX, iY,
                                        enemyNum, attackPower, boardDim);
                            }
                        }
                    }
                } else if (uinput.equals("2")) {
                    int iY = 0;
                    for (int iX = -boardDim[1]; iX < boardDim[1]; iX++) {
                        if (inValidSpot(boardDim, playerPos, iY, iX) == true) {
                            playerAttackedEnemy(battleground, enemyIndex, enemyIndexBattleground, playerPos, iX, iY,
                                    enemyNum, attackPower, boardDim);
                        }
                    }
                } else if (uinput.equals("3")) {
                    int iX = 0;
                    for (int iY = -boardDim[0]; iY < boardDim[0]; iY++) {
                        if (inValidSpot(boardDim, playerPos, iY, iX) == true) {
                            playerAttackedEnemy(battleground, enemyIndex, enemyIndexBattleground, playerPos, iX, iY,
                                    enemyNum, attackPower, boardDim);
                        }
                    }
                }
            } else {
                resultParseInput[0] = -3;
                return;
            }
        }
    }

    static boolean inValidSpot(int[] boardDim, int[] playerPos, int iY, int iX) {
        boolean validSpot = true;
        if (iY == 0 && iX == 0) {
            validSpot = false;
        } else if (playerPos[0] + iY >= boardDim[0] || playerPos[0] + iY < 0) {
            validSpot = false;
        } else if (playerPos[1] + iX >= boardDim[1] || playerPos[1] + iX < 0) {
            validSpot = false;
        }
        return validSpot;
    }

    static void playerAttackedEnemy(int[][] battleground, int[][] enemyIndex, int[][] enemyIndexBattleground,
            int[] playerPos, int iX, int iY, int enemyNum, int attackPower, int[] boardDim) {

        int pYtemp = playerPos[0] + iY;
        int pXtemp = playerPos[1] + iX;
        int[] attackPowerHolder = { attackPower };

        for (int i = 0; i < enemyNum; i++) {
            if (enemyIndex[0][i] != 0 && battleground[pYtemp][pXtemp] != 0
                    && enemyIndex[0][i] == battleground[pYtemp][pXtemp]
                    && pYtemp == enemyIndex[1][i] && pXtemp == enemyIndex[2][i]) {
                if (battleground[pYtemp][pXtemp] + attackPower >= 0) {
                    StdOut.println("Enemy with " + battleground[pYtemp][pXtemp] + " health has been eliminated! ");
                    battleground[pYtemp][pXtemp] = 0;
                    enemyIndexBattleground[pYtemp][pXtemp] = 0;
                    for (int j = 0; j < enemyIndex.length; j++) {
                        enemyIndex[j][i] = 0;
                    }
                } else {
                    battleground[pYtemp][pXtemp] += attackPowerHolder[0];
                    enemyIndex[0][i] += attackPowerHolder[0];
                    // EIB stays the same bc enemy didn't move
                    StdOut.println(attackPower + " damage to enemy with health " + enemyIndex[0][i] + "! ");
                }
            }
        }

    }
}