function [battleground,playerX,playerY,userInput,enemyIndex,enemyNumber] = parseInput(battleground,playerX,playerY,X,Y,enemyIndex,enemyNumber,attackPower, damage)

    userInput = input(compose(splitlines("What will you do?" + "\n" + "0: quit. w:up. a: left. s:down. d: right. attack1. " + "\n")),"s");
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

[battleground, enemyIndex] = attacks(userInput, battleground, enemyIndex, playerX, playerY, X, Y, enemyNumber, attackPower, damage, attackPower);

%         case "attack2"
% 
%             [battleground, enemyIndex] = attacks(userInput, battleground, enemyIndex, playerX, playerY, X, Y, enemyNumber, attackPower, damage);

        otherwise
            disp("Invalid input. Please try again.");
    end
end