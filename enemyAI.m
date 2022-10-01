function [battleground,enemyIndex,playerHealth] = enemyAI(battleground,enemyIndex,playerX,playerY,boardX,boardY,enemyNumber,playerHealth,damage)


for enc = 1:enemyNumber
    enemyHealth = enemyIndex(1,enc);
    enemyY = enemyIndex(2,enc);
    enemyX = enemyIndex(3,enc);

    if enemyHealth >= 0
        return
    else

    enemyPlayerDistanceY = enemyY - playerY;
    enemyPlayerDistanceX = enemyX - playerX;
    
%     enemyPlayerDistance = sqrt(enemyPlayerDistanceY^2 + enemyPlayerDistanceX^2);
%     boardHypotenuse = sqrt(boardX^2 + boardY^2);

    enemyMovement = randi([-1 1]); % decide how much to move, forward or back

%% deciding enemy movement direction
    if enemyMovement == 0 % enemy will not move this turn
        return
    else
        enemyMoveDir = logical(randi([0 1])); % true for X, false for Y

        if enemyMoveDir == true
            if enemyPlayerDistanceX > 0 % enemy is to right of player
                enemyMovement = -1;
            elseif enemyPlayerDistanceX < 0 % enemy is to left of player
                enemyMovement = 1;
            else
                enemyMoveDir = ~enemyMoveDir;
            end
        elseif enemyMoveDir == false
            if enemyPlayerDistanceY > 0 % enemy is above player
                enemyMovement = -1;
            elseif enemyPlayerDistanceY < 0 % enemy is below player
                enemyMovement = 1;
            else
                enemyMoveDir = ~enemyMoveDir;
            end
        end

    % determining if enemy can move
        [validSpot, newEnemyX, newEnemyY] = validSpotChecker(battleground, enemyMoveDir, enemyMovement, boardX, boardY, enemyX, enemyY);

        if validSpot == true

            enemyIndex(1,enc) = enemyHealth;
            enemyIndex(2,enc) = newEnemyY;
            enemyIndex(3,enc) = newEnemyX;

            battleground(newEnemyY,newEnemyX) = enemyHealth;
            battleground(enemyY,enemyX) = 0;
            fprintf("Enemy with health %f moved one space! \n",enemyHealth);

        elseif validSpot == false
            return
        end

    end
        
    end
end


end
