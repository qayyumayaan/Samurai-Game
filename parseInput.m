function [battleground,playerX,playerY,userInput,enemyIndex,enemyNumber] = parseInput(battleground,playerX,playerY,X,Y,enemyIndex,enemyNumber)

    userInput = input(compose(splitlines("What will you do?" + "\n" + "0: quit. w:up. a: left. s:down. d: right. 5: list attacks. attack1. 7: attack2." + "\n")),"s");
    switch userInput

        case "0"
            disp("Thank you for playing my game!");

        case 'w'
            if (playerY-1 == 0) || (battleground(playerY-1,playerX) ~= 0)
                disp("You can't move here!");
            else
                battleground(playerY-1,playerX) = battleground(playerY,playerX);
                battleground(playerY,playerX) = 0;
                playerY = playerY-1;
            end  

        case 'a'
            if (playerX-1 == 0) || (battleground(playerY,playerX-1) ~= 0)
                disp("You can't move here!");
            else
                battleground(playerY,playerX-1) = battleground(playerY,playerX);
                battleground(playerY,playerX) = 0;
                playerX = playerX-1;
            end

        case 's'
            if (playerY+1 > Y) || (battleground(playerY+1,playerX) ~= 0)
                disp("You can't move here!");
            else
                battleground(playerY+1,playerX) = battleground(playerY,playerX);
                battleground(playerY,playerX) = 0;
                playerY = playerY+1;
            end        

        case 'd'
            if (playerX+1 > X) || (battleground(playerY,playerX+1) ~= 0)
                disp("You can't move here!");
            else
                battleground(playerY,playerX+1) = battleground(playerY,playerX);
                battleground(playerY,playerX) = 0;
                playerX = playerX+1;
            end

        case "attack1"
            attackPower = 15;
            for iY = -1:1
                for iX = -1:1
                    if playerY+iY > Y || playerY+iY <= 0 || playerX+iX > X  || playerX+iX <= 0
                    else
                        if battleground(playerY+iY,playerX+iX) < 0
                            if battleground(playerY+iY,playerX+iX) + attackPower >= 0
                                battleground(playerY+iY,playerX+iX) = 0;
                                disp(" ");
                                disp("Enemy eliminated!");
                            else
                                for iEnemy = 1:enemyNumber
                                    if enemyIndex(1,iEnemy) == battleground(playerY+iY,playerX+iX)
                                        enemyIndex(1,iEnemy) = battleground(playerY+iY,playerX+iX);
                                        battleground(playerY+iY,playerX+iX) = battleground(playerY+iY,playerX+iX) + attackPower; 
                                        disp("15 damage dealt!");  
                                    end
                                end

                            end
                        end
                    end
                end
            end

        case "attack2"
            attackPower = 50;
            for iY = 1:1
                for iX = -X:X
                    if playerY+iY > Y || playerY+iY <= 0 || playerX+iX > X  || playerX+iX <= 0
                        
                    else
                        if battleground(playerY+iY,playerX+iX) < 0
                            if battleground(playerY+iY,playerX+iX) + attackPower > 0
                                battleground(playerY+iY,playerX+iX) = 0;
                                disp(" ");
                                disp("Enemy eliminated!");
                            else
                                battleground(playerY+iY,playerX+iX) = battleground(playerY+iY,playerX+iX) + attackPower; 
                            end
                        end
                    end
                end
            end

        otherwise
            disp("Invalid input. Please try again.");
    end
end