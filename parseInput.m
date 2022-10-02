function [battleground,playerX,playerY,userInput,enemyIndex,enemyNumber, enemyIndexBattleground] = parseInput(battleground,playerX,playerY,enemyIndex,enemyNumber,attackPower, damage, boardX, boardY, enemyIndexBattleground)

    userInput = input(compose(splitlines("What will you do?" + "\n" + "0: quit. w:up. a: left. s:down. d: right. attacks: 1, 2, 3" + "\n")),"s");
    switch userInput

        case "0"
            return

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
            if (playerY+1 > boardY) || (battleground(playerY+1,playerX) ~= 0)
                disp("You can't move here!");
            else
                battleground(playerY+1,playerX) = battleground(playerY,playerX);
                battleground(playerY,playerX) = 0;
                playerY = playerY+1;
            end        

        case 'd'
            if (playerX+1 > boardX) || (battleground(playerY,playerX+1) ~= 0)
                disp("You can't move here!");
            else
                battleground(playerY,playerX+1) = battleground(playerY,playerX);
                battleground(playerY,playerX) = 0;
                playerX = playerX+1;
            end

        otherwise

            [battleground, enemyIndex, enemyIndexBattleground] = attacks(userInput, battleground, enemyIndex, playerX, playerY, boardX, boardY, enemyNumber, attackPower, enemyIndexBattleground);

    end
end