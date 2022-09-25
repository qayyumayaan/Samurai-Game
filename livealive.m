% Live a Live Battle System written in the only language I know. 

clc
clear

Y = uint8(7); % board length
X = uint8(5); % board width
battleground = int32(zeros([Y X]));

enemyNumber = uint8(4);

playerNumber = uint8(1);
playerHealth = uint8(100);
enemHealthMin = uint8(40);
enemHealthMax = uint8(100);


% player and enemy need: location, health, attacks. 

[battleground,enemyIndex] = enemyPlacement(enemyNumber,Y,X,battleground,enemHealthMin,enemHealthMax);
[battleground,playerX,playerY] = playerPlacement(playerNumber,Y,X,battleground,playerHealth);

% playerX = X;
% playerY = 1;
% battleground(playerY,playerX) = playerHealth;

while playerHealth > 0 || enemyNumber > 0
    battleground
    [battleground,playerX,playerY,userInput] = parseInput(battleground,playerX,playerY,X,Y);
    if userInput == "0"
        break
    end
    if sum(sum(sum(battleground)) - int32(playerHealth)) >= 0
        disp("All enemies eliminated! You win!");
        break
    end
    [battleground,enemyIndex] = enemyAI(battleground,enemyIndex,playerX,playerY,X,Y,enemyNumber);
end

%enemyIndex
% battleground(enemyIndex(2,1),enemyIndex(3,1))
% battleground(enemyIndex(2,2),enemyIndex(3,2))
% battleground(enemyIndex(2,3),enemyIndex(3,3))
% battleground(enemyIndex(2,4),enemyIndex(3,4))

function [battleground,enemyIndex] = enemyAI(battleground,enemyIndex,playerX,playerY,X,Y,enemyNumber);


end


