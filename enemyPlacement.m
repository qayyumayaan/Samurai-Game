function [battleground,enemyIndex,enemyNumber] = enemyPlacement(enemyNumber,Y,X,battleground,enemHealthMin,enemHealthMax)
enemyIndex = int32(zeros([1 enemyNumber]));
    for en = uint8(1:enemyNumber)
        randL = randi(Y);
        randW = randi(X); 
        if battleground(randL,randW) ~= 0
            randL = randi(Y);
            randW = randi(X);
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
        
        enemyIndex(1,en) = int16(-randi([enemHealthMin enemHealthMax]));
        enemyIndex(2,en) = randL;
        enemyIndex(3,en) = randW;
        enemyIndex(4,en) = en;

        battleground(randL,randW) = enemyIndex(1,en);
    end
end