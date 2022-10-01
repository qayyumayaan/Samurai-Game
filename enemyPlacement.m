function [battleground,enemyIndex,enemyNumber, enemyIndexBattleground] = enemyPlacement(enemyNumber,boardY,boardX,battleground,enemyHealthMin,enemyHealthMax)

enemyIndexBattleground = battleground;  
enemyIndex = zeros([1 enemyNumber]);

    for en = 1:enemyNumber
        randY = randi(boardY);
        randX = randi(boardX); 
        if battleground(randY,randX) ~= 0
            randY = randi(boardY);
            randX = randi(boardX);
        end

        enemyIndex(1,en) = -randi([enemyHealthMin enemyHealthMax]);
        enemyIndex(2,en) = randY;
        enemyIndex(3,en) = randX;

        enemyIndexBattleground(randY,randX) = en;
        battleground(randY,randX) = enemyIndex(1,en);
    end

end