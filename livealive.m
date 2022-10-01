% Live a Live Battle System written in the only language I know. 

clc
clear

boardY = 7; % board height
boardX = 6; % board width
battleground = zeros([boardY boardX]);

enemyNumber = 10;

playerNumber = 1;
playerHealth = 150;
enemyHealthMin = 40;
enemyHealthMax = 100;
damage = 15;
attackPower = 25; % user

% battleground(Y,X)

% player and enemy need: location, health, attacks. 

[battleground,enemyIndex] = enemyPlacement(enemyNumber,boardY,boardX,battleground,enemyHealthMin,enemyHealthMax);
[battleground,playerX,playerY] = playerPlacement(playerNumber,boardY,boardX,battleground,playerHealth);

% playerX = X;
% playerY = 1;
% battleground(playerY,playerX) = playerHealth;
while playerHealth > 0 || enemyNumber > 0
    disp(battleground)
    [battleground,playerX,playerY,userInput,enemyIndex,enemyNumber] = parseInput(battleground,playerX,playerY,boardX,boardY,enemyIndex,enemyNumber,attackPower);
    if userInput == "0"
        break
    end

    if sum(sum(sum(battleground)) - playerHealth) >= 0
        disp("All enemies eliminated! You win!");
        break
    else
        [battleground,enemyIndex,playerHealth] = enemyAI(battleground,enemyIndex,playerX,playerY,boardX,boardY,enemyNumber,playerHealth,damage);
    end

    if playerHealth <= 0
        disp("Game over!")
        disp(battleground)
        break
    end
end

disp("Thank you for playing my game!");
%enemyIndex
% battleground(enemyIndex(2,1),enemyIndex(3,1))
% battleground(enemyIndex(2,2),enemyIndex(3,2))
% battleground(enemyIndex(2,3),enemyIndex(3,3))
% battleground(enemyIndex(2,4),enemyIndex(3,4))
