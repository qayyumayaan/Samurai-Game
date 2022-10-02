function [battleground, enemyIndexBattleground, enemyIndex] = enemyAttackUpdater(battleground, enemyIndexBattleground, enemyIndex, playerX, playerY, iX, iY, enemyNumber, attackPower, boardX, boardY)

if playerY+iY > boardY || playerY+iY <= 0 || playerX+iX > boardX  || playerX+iX <= 0 
else

for en = 1:enemyNumber
    if enemyIndex(1,en) == battleground(playerY+iY,playerX+iX) && battleground(playerY+iY,playerX+iX) ~= 0
        if battleground(playerY+iY,playerX+iX) + attackPower >= 0
            fprintf("\n Enemy with health %d eliminated! \n", battleground(playerY+iY,playerX+iX));
            battleground(playerY+iY,playerX+iX) = 0;
            enemyIndexBattleground(playerY+iY,playerX+iX) = 0;
            enemyIndex(:,en) = 0;

        else 
            battleground(playerY+iY,playerX+iX) = battleground(playerY+iY,playerX+iX) + attackPower; 
%             enemyIndex(1,en) = battleground(playerY+iY,playerX+iX);
            enemyIndex(1,en) = enemyIndex(1,en) + attackPower;
            fprintf("%d damage to enemy with health %d! \n",attackPower,battleground(playerY+iY,playerX+iX))
       
        end
    end
end
    
end




end

