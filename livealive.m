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

function [battleground,playerX,playerY,userInput] = parseInput(battleground,playerX,playerY,X,Y)

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
            for iY = int16(-1:1)
                for iX = int16(-1:1)
                    if playerY+iY > Y || playerY+iY <= 0 || playerX+iX > X  || playerX+iX <= 0
                        disp("Out of bounds, for now")
                    else
                        if battleground(playerY+iY,playerX+iX) < 0
                            if battleground(playerY+iY,playerX+iX) + 50 > 0
                                battleground(playerY+iY,playerX+iX) = 0;
                                disp(" ");
                                disp("Enemy eliminated!");
                            else
                                battleground(playerY+iY,playerX+iX) = battleground(playerY+iY,playerX+iX) + 15; 
                            end
                        end
                    end
                end
            end

        case "attack2"
            for iY = int16(1:1)
                for iX = int16(-X:X)
                    if playerY+iY > Y || playerY+iY <= 0 || playerX+iX > X  || playerX+iX <= 0
                        disp("Out of bounds, for now")
                    else
                        if battleground(playerY+iY,playerX+iX) < 0
                            if battleground(playerY+iY,playerX+iX) + 50 > 0
                                battleground(playerY+iY,playerX+iX) = 0;
                                disp(" ");
                                disp("Enemy eliminated!");
                            else
                                battleground(playerY+iY,playerX+iX) = battleground(playerY+iY,playerX+iX) + 15; 
                            end
                        end
                    end
                end
            end

        otherwise
            disp("Invalid input. Please try again.");
    end
end

function [battleground,enemyIndex] = enemyAI(battleground,enemyIndex,playerX,playerY,X,Y,enemyNumber);


end

function [battleground,enemyIndex,enemyNumber] = enemyPlacement(enemyNumber,Y,X,battleground,enemHealthMin,enemHealthMax)
enemyIndex = int32(zeros([1 enemyNumber]));
    for en = uint8(1:enemyNumber)
        randL = randi(Y);
        randW = randi(X); 
        if battleground(randL,randW) ~= 0
            randL = randi(Y);
            randW = randi(X);
        end
% no row / column repeats
%         if (sum(battleground(l,:)) < 0 || sum(battleground(w,:)) < 0) 
%             randL = randi(l);
%             randW = randi(w); 
%             if (sum(battleground(l,:)) < 0 || sum(battleground(w,:)) < 0)
%                 randL = randi(l);
%                 randW = randi(w); 
%                 if (sum(battleground(l,:)) < 0 || sum(battleground(w,:)) < 0)
%                     randL = randi(l);
%                     randW = randi(w); 
%                 end
%             end
%         end
        
        enemyIndex(1,en) = int16(-randi([enemHealthMin enemHealthMax]));
        enemyIndex(2,en) = randL;
        enemyIndex(3,en) = randW;
        enemyIndex(4,en) = en;

        battleground(randL,randW) = enemyIndex(1,en);
    end
end

function [battleground,playerX,playerY] = playerPlacement(playerNumber,Y,X,battleground,playerHealth)
    for en = uint8(1:playerNumber)
        playerY = int16(randi(Y));
        playerX = int16(randi(X)); 
        if battleground(playerY,playerX) ~= 0
            playerY = int16(randi(Y));
            playerX = int16(randi(X));
        end
        battleground(playerY,playerX) = playerHealth;
    end
end