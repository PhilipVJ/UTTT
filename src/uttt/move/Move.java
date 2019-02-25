/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttt.move;

import uttt.move.IMove;

/**
 *
 * @author Philip
 */
public class Move implements IMove
{
    private int x;
    private int y;
    
    public Move(int x, int y)
    {
        this.x = y;
        this.y=y;
    }
    
    
    @Override
    public int getX()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getY()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
