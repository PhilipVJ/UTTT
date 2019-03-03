/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttt.bot;

import java.util.List;
import uttt.bot.IBot;
import uttt.game.IGameState;
import uttt.move.IMove;

/**
 * All this does is throwing random moves.
 * @author Philip
 */
public class RandomBot implements IBot
{

    @Override
    public IMove doMove(IGameState state)
    {
        System.out.println("RANDOM BOT");
        List<IMove> moves = state.getField().getAvailableMoves();
       int moveSize = moves.size();
       Double randomNumber = Math.random()*moveSize;
       int rNumber = randomNumber.intValue();
       return moves.get(rNumber);
       
    }


}
