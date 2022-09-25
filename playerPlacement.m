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