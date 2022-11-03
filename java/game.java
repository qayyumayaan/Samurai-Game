public class game {
    public static void runtime(int[] boardDim, int enemyNum, int[] enemyHealthRange, int damage, int maxSpacesMoved,
            int playerHealth, int attackPower, int[] validAttacks, boolean simulationMode, int playerInputsLength,
            int[][] debugData, int[] runTimes, boolean suppressAllOutput) {

        int[] playerPos = { 0, 0, playerHealth };
        int[][] enemyIndex = new int[3][enemyNum];
        int[][] battleground = new int[boardDim[0]][boardDim[1]];
        int[][] enemyIndexBattleground = new int[boardDim[0]][boardDim[1]];

        // routine that places enemies, makes enemyIndexBattleground, battleground, and
        Functions.enemyPlacement(enemyNum, boardDim, enemyIndex, enemyIndexBattleground, battleground,
                enemyHealthRange);

        // routine that places player
        Functions.playerPlacement(boardDim, enemyIndexBattleground, playerPos);
        battleground[playerPos[0]][playerPos[1]] = playerPos[2];

        boolean win = false;

        /*
         * 
         */
        // GAMEPLAY
        String[][] newBoard = new String[boardDim[0]][boardDim[1]];
        if (simulationMode == true && suppressAllOutput == false) {
            newBoard = Functions.battlegroundModifier(battleground);
            Functions.printArray(newBoard);
        }
        String[] playerInputs = new String[playerInputsLength];
        int[] count = new int[1];

        while (playerPos[2] > 0 || enemyNum > 0) {
            // parseInput
            int[] resultParseInput = { 0, 0 };
            boolean runRestFlag = true;
            boolean continueParseInputLoop = true;

            while (continueParseInputLoop == true) {
                String uinput = "";
                if (simulationMode == true) {
                    String[] inputs = { "w", "a", "s", "d", "1", "2", "3" };
                    uinput = Functions.randomPlayerInput(inputs);
                    continueParseInputLoop = false;
                    Functions.parseInput(battleground, playerPos, boardDim, enemyIndex, enemyIndexBattleground,
                            enemyNum,
                            attackPower, resultParseInput, validAttacks, playerInputs, count, uinput, simulationMode,
                            playerInputsLength);
                } else {
                    newBoard = Functions.battlegroundModifier(battleground);
                    Functions.printArray(newBoard);
                    StdOut.print("What will you do? ");
                    uinput = StdIn.readString();

                    Functions.parseInput(battleground, playerPos, boardDim, enemyIndex, enemyIndexBattleground,
                            enemyNum,
                            attackPower, resultParseInput, validAttacks, playerInputs, count, uinput, simulationMode,
                            playerInputsLength);

                    if (resultParseInput[0] == -2) {
                        continueParseInputLoop = false;
                        runRestFlag = false;
                        break;
                    } else if (simulationMode == false) {
                        if (resultParseInput[0] == -1) {
                            StdOut.println("You can't move here!");
                        } else if (resultParseInput[0] == -3) {
                            StdOut.println("Unrecognized input. Please try again.");
                        } else {
                            continueParseInputLoop = false;
                        }
                    }
                }
            }

            if (runRestFlag == false) {
                break;
            } else if (runRestFlag == true) {

                Functions.enemyAI(battleground, enemyIndexBattleground, enemyIndex,
                        playerPos, enemyNum, damage, boardDim, maxSpacesMoved, simulationMode, suppressAllOutput);

                win = Functions.winGame(battleground, playerPos[2]);
                if (win == true) {
                    if (suppressAllOutput == false) {
                        StdOut.println("All enemies defeated. You win!");
                    }
                    break;
                }
                if (playerPos[2] <= 0) {
                    if (suppressAllOutput == false) {
                        StdOut.println("Game over!");
                    }
                    break;
                }

            }
        }

        String[] finalPlayerInputs = new String[count[0]];
        for (int i = 0; i < count[0]; i++) {
            finalPlayerInputs[i] = playerInputs[i];
        }
        if (suppressAllOutput == false) {
            newBoard = Functions.battlegroundModifier(battleground);
            Functions.printArray(newBoard);
            StdOut.println("Thank you so much for playing my game!");
        }
        if (simulationMode == true) {
            if (suppressAllOutput == false) {
                StdOut.println(count[0] + " inputs to randomly clear the game.");
            }
            debugData[0][runTimes[0]] = count[0];
            debugData[1][runTimes[0]] = win ? 2 : 1;
            runTimes[0]++;
            // count[0] = 0;
            // Functions.printArray(finalPlayerInputs);
        }

    }

    public static void main(String[] args) {
        int[] boardDim = { 7, 7 }; // y by x
        int enemyNum = 10; // formerly enemyNumber
        int[] enemyHealthRange = { 40, 100 }; // min, max
        int damage = 15; // enemy attack power
        int maxSpacesMoved = 1;
        int playerHealth = 150;
        int attackPower = 15; // player attack power
        int[] validAttacks = { 1, 2, 3 };

        // debug
        boolean simulationMode = false;
        boolean suppressAllOutput = false;
        int loopIterations = 50000;
        int playerInputsLength = 50000;
        int[][] debugData = new int[2][playerInputsLength];
        int[] runTimes = { 0 };

        if (simulationMode == false) {
            runtime(boardDim, enemyNum, enemyHealthRange, damage, maxSpacesMoved,
                    playerHealth, attackPower, validAttacks, simulationMode, playerInputsLength, debugData, runTimes,
                    suppressAllOutput);
        } else {
            for (int i = 0; i < loopIterations; i++) {
                runtime(boardDim, enemyNum, enemyHealthRange, damage, maxSpacesMoved,
                        playerHealth, attackPower, validAttacks, simulationMode, playerInputsLength, debugData,
                        runTimes,
                        suppressAllOutput);
            }

            int sumMoves = 0;
            double sumWins = 0;
            int[][] debugDataFinal = new int[2][runTimes[0]];
            for (int i = 0; i < runTimes[0]; i++) {
                debugDataFinal[0][i] = debugData[0][i];
                debugDataFinal[1][i] = debugData[1][i];
                sumMoves += debugData[0][i];
                sumWins += debugData[1][i];
            }
            int avgMoves = sumMoves / runTimes[0];
            double avgWins = sumWins / runTimes[0] - 1;

            StdOut.println("Avg # of moves over " + loopIterations + " games: " + avgMoves);
            StdOut.println("Avg % of wins over " + loopIterations + " games: " + avgWins);
            // Functions.printArray(debugDataFinal);
        }
    }
}
