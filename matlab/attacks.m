function [battleground, enemyIndex, enemyIndexBattleground] = attacks(userInput, battleground, enemyIndex, playerX, playerY, boardX, boardY, enemyNumber, attackPower, enemyIndexBattleground)
 
	%% function manages user attack input.
		
switch userInput
 
    case '1'

for iY = -1:1
    for iX = -1:1

    [battleground, enemyIndexBattleground, enemyIndex] = enemyAttackUpdater(battleground, enemyIndexBattleground, enemyIndex, playerX, playerY, iX, iY, enemyNumber, attackPower, boardX, boardY);

    end
end

    case '2'

iY = 0;
for iX = -boardX:boardX
    
    [battleground, enemyIndexBattleground, enemyIndex] = enemyAttackUpdater(battleground, enemyIndexBattleground, enemyIndex, playerX, playerY, iX, iY, enemyNumber, attackPower, boardX, boardY);

end


    case '3'

iX = 0;
for iY = -boardY:boardY

    [battleground, enemyIndexBattleground, enemyIndex] = enemyAttackUpdater(battleground, enemyIndexBattleground, enemyIndex, playerX, playerY, iX, iY, enemyNumber, attackPower, boardX, boardY);

end



otherwise
    disp("Invalid input. Please try again.");

end
end
