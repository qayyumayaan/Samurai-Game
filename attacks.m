function [battleground, enemyIndex, enemyIndexBattleground] = attacks(userInput, battleground, enemyIndex, playerX, playerY, boardX, boardY, enemyNumber, attackPower, damage, enemyIndexBattleground)
 
switch userInput
 
    case 'a1'

for iY = -1:1
    for iX = -1:1
        if playerY+iY > boardY || playerY+iY <= 0 || playerX+iX > boardX  || playerX+iX <= 0
        else
            
            if  battleground(playerY+iY,playerX+iX) < 0
                if battleground(playerY+iY,playerX+iX) + attackPower >= 0
                    battleground(playerY+iY,playerX+iX) = 0;
                    fprintf("\n Enemy with health %d eliminated! \n", enemyIndex(1,enemyIndexBattleground(playerY+iY,playerX+iX)));
                    enemyIndex(1,enemyIndexBattleground(playerY+iY,playerX+iX)) = 0;
                    enemyIndexBattleground(playerY+iY,playerX+iX) = 0;
                    
                else 
                    battleground(playerY+iY,playerX+iX) = battleground(playerY+iY,playerX+iX) + attackPower; 
                    fprintf("%d damage to enemy with health %d! \n",attackPower,enemyIndex(1,enemyIndexBattleground(playerY+iY,playerX+iX)))
                    enemyIndex(1,enemyIndexBattleground(playerY+iY,playerX+iX)) = battleground(playerY+iY,playerX+iX);
               
                end
            end
            
        end
    end
end

    case "attack2"

for iY = -1:1
    for iX = -1:1
        if playerY+iY > boardY || playerY+iY <= 0 || playerX+iX > boardX  || playerX+iX <= 0
            return
        else

        for en = 1:enemyNumber
            if enemyIndex(1,en) == battleground(playerY+iY,playerX+iX)
                if battleground(playerY+iY,playerX+iX) + attackPower >= 0
                    battleground(playerY+iY,playerX+iX) = 0;
                    enemyIndex(1,en) = battleground(playerY+iY,playerX+iX);
                    fprintf("\n Enemy eliminated! \n");
                    
                else 
                    battleground(playerY+iY,playerX+iX) = battleground(playerY+iY,playerX+iX) + attackPower; 
                    enemyIndex(1,en) = battleground(playerY+iY,playerX+iX);
                    fprintf("%f damage dealt! \n",attackPower);  
               
                end
            end
        end
            
        end
    end
end






end
end
