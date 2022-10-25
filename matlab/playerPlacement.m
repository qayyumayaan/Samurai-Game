function [battleground,playerX,playerY] = playerPlacement(playerNumber,Y,X,battleground,playerHealth)
    for en = (1:playerNumber)
        playerY = (randi(Y));
        playerX = (randi(X)); 
        if battleground(playerY,playerX) ~= 0
            playerY = (randi(Y));
            playerX = (randi(X));
        end
        battleground(playerY,playerX) = playerHealth;
    end
end