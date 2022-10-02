function [battleground,enemyIndex,playerHealth, enemyIndexBattleground] = enemyAI(battleground,enemyIndex,playerX,playerY,boardX,boardY,enemyNumber,playerHealth,damage, enemyIndexBattleground)


for enc = 1:enemyNumber
    enemyHealth = enemyIndex(1,enc);
    if enemyHealth >= 0
    else
    
        enemyY = enemyIndex(2,enc);
        enemyX = enemyIndex(3,enc);
    
    
    
        enemyPlayerDistanceY = enemyY - playerY;
        enemyPlayerDistanceX = enemyX - playerX;
        
        %% enemy attack
        if (enemyPlayerDistanceY == 0 && abs(enemyPlayerDistanceX) == 1) || (enemyPlayerDistanceX == 0) && (abs(enemyPlayerDistanceY) == 1)
            battleground(playerY,playerX) = battleground(playerY,playerX) - damage;
            playerHealth = playerHealth - damage;
            fprintf("%d damage to you by enemy with health %d! \n",damage,enemyHealth)
        else
        %% deciding enemy movement direction

        %     enemyPlayerDistance = sqrt(enemyPlayerDistanceY^2 + enemyPlayerDistanceX^2);
        %     boardHypotenuse = sqrt(boardX^2 + boardY^2);
        
            enemyMovement = randi(2)-1; % decide how much to move, forward or back
            enemyMovementProb = randi([0 3]); % 3/4ths probability of movement
        
            if enemyMovementProb == 0 % enemy will not move this turn
            else
                enemyMoveDir = logical(randi([0 1])); % true for X, false for Y
        
                if enemyMoveDir == true
                    if enemyPlayerDistanceX > 0 % enemy is to right of player
                        enemyMovement = -abs(enemyMovement);
                    elseif enemyPlayerDistanceX < 0 % enemy is to left of player
                        enemyMovement = abs(enemyMovement);
                    else
                        enemyMoveDir = ~enemyMoveDir;
                    end
                elseif enemyMoveDir == false
                    if enemyPlayerDistanceY > 0 % enemy is above player
                        enemyMovement = abs(enemyMovement);
                    elseif enemyPlayerDistanceY < 0 % enemy is below player
                        enemyMovement = -abs(enemyMovement);
                    else
                        enemyMoveDir = ~enemyMoveDir;
                    end
                end
        
            %% determining if enemy can move
                [validSpot, newEnemyX, newEnemyY] = validSpotChecker(battleground, enemyMoveDir, enemyMovement, boardX, boardY, enemyX, enemyY);
        
                if validSpot == true
        
                    enemyIndex(1,enc) = enemyHealth;
                    enemyIndex(2,enc) = newEnemyY;
                    enemyIndex(3,enc) = newEnemyX;
        
                    enemyIndexBattleground(newEnemyY,newEnemyX) = enemyIndexBattleground(enemyY,enemyX);
                    enemyIndexBattleground(enemyY,enemyX) = 0; 
        
                    battleground(newEnemyY,newEnemyX) = enemyHealth;
                    battleground(enemyY,enemyX) = 0;
    
                    if enemyMoveDir == true
                        if enemyMovement == -1
                            fprintf("Enemy with health %d moved one space to the left! \n",enemyHealth);
                        elseif enemyMovement == 1
                            fprintf("Enemy with health %d moved one space to the right! \n",enemyHealth);
                        end
                    elseif enemyMoveDir == false
                        if enemyMovement == -1
                            fprintf("Enemy with health %d moved one space up! \n",enemyHealth);
                        elseif enemyMovement == 1
                            fprintf("Enemy with health %d moved one space down! \n",enemyHealth);
                        end
                    end
        
                elseif validSpot == false
                end
        
            end
        end        
    end
end


end
