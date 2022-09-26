function [battleground,enemyIndex,enemyNumber] = enemyPlacement(enemyNumber,Y,X,battleground,enemHealthMin,enemHealthMax)
enemyIndex = zeros([1 enemyNumber]);
    for en = 1:enemyNumber
        randY = randi(Y);
        randX = randi(X); 
        if battleground(randY,randX) ~= 0
            randY = randi(Y);
            randX = randi(X);
        end
% no row / column repeats
%         if (sum(battleground(l,:)) < 0 || sum(battleground(w,:)) < 0) 
%             randL = randi(l);
%             randW = randi(w); 
%             if (sum(battleground(l,:)) < 0 || sum(battleground(w,:)) < 0)
%                 randL = randi(l);
%                 randW = randi(w); 
%                 if (sum(battleground(l,:)) < 0 || sum(battleground(w,:)) < 0)
%                     randL = randi(l);
%                     randW = randi(w); 
%                 end
%             end
%         end
        
        enemyIndex(1,en) = -randi([enemHealthMin enemHealthMax]);
        enemyIndex(2,en) = randY;
        enemyIndex(3,en) = randX;
        enemyIndex(4,en) = en;

        battleground(randY,randX) = enemyIndex(1,en);
    end
end