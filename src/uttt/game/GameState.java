/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttt.game;

import uttt.field.Field;
import uttt.field.IField;
import uttt.game.IGameState;

/**
 *
 * @author Philip
 */
public class GameState implements IGameState
{
    
    private int roundNumber;
    private int moveNumber;
    private Field gField;
    
    public GameState()
    {
        gField=new Field();
        roundNumber=0;
        moveNumber=0;
    }

    @Override
    public IField getField()
    {
      return gField;
    }

    @Override
    public int getMoveNumber()
    {
        return moveNumber;
    }

    @Override
    public void setMoveNumber(int moveNumber)
    {
       this.moveNumber = moveNumber;
    }

    @Override
    public int getRoundNumber()
    {
       return roundNumber;
    }

    @Override
    public void setRoundNumber(int roundNumber)
    {
       this.roundNumber=roundNumber;
    }
    
}
