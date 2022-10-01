function [battleground, enemyIndex] = attacks(userInput, battleground, enemyIndex, playerX, playerY, X, Y, enemyNumber, attackPower, damage)
 
switch userInput
 
    case "attack1"

for iY = -1:1
    for iX = -1:1
        if playerY+iY > Y || playerY+iY <= 0 || playerX+iX > X  || playerX+iX <= 0
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
                    fprintf("%f damage to you by enemy with health %f! \n",damage,enemyIndex(1,en))
               
                end
            end
        end
            
        end
    end
end


% switch userAttackInput
% 
%     case "attack2"
% 
% for iY = -1:1
%     for iX = -1:1
%         if playerY+iY > Y || playerY+iY <= 0 || playerX+iX > X  || playerX+iX <= 0
%             return
%         else
% 
%         for en = 1:enemyNumber
%             if enemyIndex(1,en) == battleground(playerY+iY,playerX+iX)
%                 if battleground(playerY+iY,playerX+iX) + attackPower >= 0
%                     battleground(playerY+iY,playerX+iX) = 0;
%                     enemyIndex(1,en) = battleground(playerY+iY,playerX+iX);
%                     fprintf("\n Enemy eliminated! \n");
%                     
%                 else 
%                     battleground(playerY+iY,playerX+iX) = battleground(playerY+iY,playerX+iX) + attackPower; 
%                     enemyIndex(1,en) = battleground(playerY+iY,playerX+iX);
%                     fprintf("%f damage dealt! \n",attackPower);  
%                
%                 end
%             end
%         end
%             
%         end
%     end
% end






end
end