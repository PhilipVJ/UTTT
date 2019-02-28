/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttt.GUI.Controller;

import uttt.game.GameManager;

/**
 *
 * @author Anders
 */
public class HighScore {
    private GameManager gManager;
    public int score; 
    private int Player1 = 0;
    private int Player2 = 0;
    private int draw = 0;
     
    
    public int getScoreOne()
    {
        return Player1;
    }
   
    public int getScoreTwo()
    {
        return Player2;
    }
    
    public int getScoreDraw()
    {
        return draw;
    }
    
    /**
     *
     * @param playerno
     */
    public void incrementScore(int playerno)
    {   
        
        switch (gManager.getWinnerIs())
        {
            case 1:
                Player1 ++;
                break;
            case 2:
                Player2 ++;
                break;
            case -1:
                draw++;
                break;
        }
          
    }
}
