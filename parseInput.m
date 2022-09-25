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