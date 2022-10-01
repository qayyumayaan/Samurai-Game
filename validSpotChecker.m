function [validSpot, newEnemyX, newEnemyY] = validSpotChecker(battleground, enemyMoveDir, enemyMovement, boardX, boardY, enemyX, enemyY)

if enemyMoveDir == true % for X movement
    if enemyX + enemyMovement > boardX || enemyX + enemyMovement <= 0
         validSpot = false;
         newEnemyX = enemyX;
         newEnemyY = enemyY;

    else % spot filled?
        newEnemyX = enemyX + enemyMovement;
        newEnemyY = enemyY;

        if battleground(enemyY,newEnemyX) ~= 0
            validSpot = false;
        else 
            validSpot = true;
        end

    end


elseif enemyMoveDir == false % for Y movement
    if enemyY + enemyMovement > boardY || enemyY + enemyMovement <= 0
         validSpot = false;
         newEnemyX = enemyX;
         newEnemyY = enemyY;

    else % spot filled?
        newEnemyY = enemyY + enemyMovement;
        newEnemyX = enemyX;

        if battleground(newEnemyY,enemyX) ~= 0
            validSpot = false;
        else 
            validSpot = true;
        end
        
    end

else
    validSpot = false;
    newEnemyX = enemyX;
    newEnemyY = enemyY;
    error("enemyMoveDir cannot be understood as a logical variable.")

end


end