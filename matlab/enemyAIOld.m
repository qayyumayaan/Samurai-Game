function [battleground,enemyIndex,playerHealth] = enemyAIOld(battleground,enemyIndex,playerX,playerY,X,Y,enemyNumber,playerHealth,damage)

    for en = 1:enemyNumber % for each enemy
       enemyX = enemyIndex(3,en); % enemy X
       enemyY = enemyIndex(2,en); % enemy Y
       diffY = enemyY - playerY;
       diffX = enemyX - playerX;
       randVar4Enemies = randi(10);
       
       if randVar4Enemies < 5

        if (diffY == 0 || abs(diffY) == 1) && (diffX == 0 || abs(diffX) == 1)
            battleground(playerY,playerX) = battleground(playerY,playerX) - damage;
            playerHealth = playerHealth - damage;
            fprintf("%f damage to you by enemy with health %f! \n",damage,enemyIndex(1,en))
    
        elseif randi(2) == 1 % enemies have an equal chance of going left or right. 
    
                if enemyY - 1 >= 0 || enemyY + 1 <= Y
                    if diffY > 0
                        newEnemyY = enemyY-1;
                        if battleground(newEnemyY,enemyX) == playerHealth
    
                            playerHealth = playerHealth - damage;
                            if playerHealth < 0
                                disp("Game over!")
                                break
                            end
    
                            playerHealth = battleground(newEnemyY,enemyX);
                            fprintf("%f damage to you by enemy with health %f! \n",damage,enemyIndex(1,en))
                            return
    
                        elseif battleground(newEnemyY,enemyX) ~= 0
                            return
    
                        else % all attack checks are passed, can move one tile
    
                            battleground(newEnemyY,enemyX) = battleground(enemyY,enemyX); battleground(enemyY,enemyX) = 0;
                            fprintf("Enemy with health %f moved one space! \n",enemyIndex(1,en));
                            enemyIndex(2,en) = newEnemyY;
                            enemyY = newEnemyY;
                        end
                    elseif diffY < 0
    
                        newEnemyY = enemyY+1;
                        if battleground(newEnemyY,enemyX) ~= 0
                            return
                        else
                            battleground(newEnemyY,enemyX) = battleground(enemyY,enemyX); battleground(enemyY,enemyX) = 0;
                            fprintf("Enemy with health %f moved one space! \n",enemyIndex(1,en));
                            enemyIndex(2,en) = newEnemyY;
                            enemyY = newEnemyY;
                        end
                    end
    
           else
    
                if enemyX - 1 >= 0 || enemyX + 1 <= X
                    
                    if diffX > 0
                        newEnemyX = enemyX-1;
                        if battleground(enemyY,newEnemyX) == playerHealth % simulates running into you for damage
                            playerHealth = playerHealth - damage;
                            battleground(enemyY,newEnemyX) = playerHealth;
                            fprintf("%f damage to you by enemy with health %f! \n",damage,enemyIndex(1,en))
                            return
                        elseif battleground(enemyY,newEnemyX) ~= 0
                            return
                        else
                        battleground(enemyY,newEnemyX) = battleground(enemyY,enemyX); battleground(enemyY,enemyX) = 0;
                        fprintf("Enemy with health %f moved one space! \n",enemyIndex(1,en));
                        enemyIndex(3,en) = newEnemyX;
                        enemyX = newEnemyX;
                        end
                    elseif diffX < 0
                        newEnemyX = enemyX+1;
                        if battleground(enemyY,newEnemyX) ~= 0
                            return
                        else
                            battleground(enemyY,newEnemyX) = battleground(enemyY,enemyX); battleground(enemyY,enemyX) = 0;
                            fprintf("Enemy with health %f moved one space! \n",enemyIndex(1,en));
                            enemyIndex(3,en) = newEnemyX;
                            enemyX = newEnemyX;
                        end
                    elseif diffX == 0
                        return
                    end
                end
           end
       end
       end


%         if enemyY - playerY < 0
%             bruh
%         end
        
%         tempX = enemyIndex(2,en) + randi([-X X]);
%         if ((tempX > X) || (tempX < 0) || (battleground(enemyIndex(3,en),tempX) ~= 0))
%         else
%             battleground(enemyIndex(3,en),tempX) = battleground(enemyIndex(2,en),enemyIndex(3,en)); % enemy movement
%             battleground(enemyIndex(2,en),enemyIndex(3,en)) = 0; % enemy # health value
%         end
% 
%         tempY = enemyIndex(2,en) + randi([-Y Y]);
%         if ((tempY > Y) || (tempX < 0) || (battleground(tempY,tempX) ~= 0))
%         else
%             battleground(tempY,tempX) = battleground(enemyIndex(2,en),enemyIndex(3,en)); % enemy movement
%             battleground(enemyIndex(2,en),enemyIndex(3,en)) = 0; % enemy # health value
%         end
%         enemyIndex(2,en); % enemy X
%         enemyIndex(3,en); % enemy Y
    end

end