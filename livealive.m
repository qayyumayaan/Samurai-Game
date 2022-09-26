% Live a Live Battle System written in the only language I know. 

clc
clear

Y = 6; % board length
X = 6; % board width
battleground = zeros([Y X]);

enemyNumber = 1;

playerNumber = 1;
playerHealth = 100;
enemHealthMin = 40;
enemHealthMax = 100;
damage = 15;

% battleground(Y,X)

% player and enemy need: location, health, attacks. 

[battleground,enemyIndex] = enemyPlacement(enemyNumber,Y,X,battleground,enemHealthMin,enemHealthMax);
[battleground,playerX,playerY] = playerPlacement(playerNumber,Y,X,battleground,playerHealth);

% playerX = X;
% playerY = 1;
% battleground(playerY,playerX) = playerHealth;
while playerHealth > 0 || enemyNumber > 0
    disp(battleground)
    [battleground,playerX,playerY,userInput,enemyIndex,enemyNumber] = parseInput(battleground,playerX,playerY,X,Y,enemyIndex,enemyNumber);
    if userInput == "0"
        break
    end

    if sum(sum(sum(battleground)) - playerHealth) >= 0
        disp("All enemies eliminated! You win!");
        break
    end
%     randVar4Enemies = randi(10);
%     if randVar4Enemies < 9
        [battleground,enemyIndex,playerHealth] = enemyAI(battleground,enemyIndex,playerX,playerY,X,Y,enemyNumber,playerHealth,damage);
%     end
    if playerHealth < 0
        disp("Game over!")
        break
    end
end


%enemyIndex
% battleground(enemyIndex(2,1),enemyIndex(3,1))
% battleground(enemyIndex(2,2),enemyIndex(3,2))
% battleground(enemyIndex(2,3),enemyIndex(3,3))
% battleground(enemyIndex(2,4),enemyIndex(3,4))
