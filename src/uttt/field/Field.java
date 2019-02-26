/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttt.field;

import java.util.ArrayList;
import uttt.field.IField;
import java.util.List;
import uttt.move.IMove;

/**
 *
 * @author Philip
 */
public class Field implements IField
{

    private String[][] board;
    private String[][] macroBoard;
    private Integer[] lastMove;
    private int activeMicroboard;
    private ArrayList<String[]> microBoards;

    public Field()
    {
        board = new String[9][9];
        macroBoard = new String[3][3];
        // 10 means the current Microboard is the entire arena (all are available)
        activeMicroboard = 10;
        clearBoard();
        microBoards = new ArrayList();

    }

    @Override
    public void clearBoard()
    {
        for (int i = 0; i < 9; i++)
        {
            for (int k = 0; k < 9; k++)
            {
                board[i][k] = AVAILABLE_FIELD;
            }
        }

        for (int l = 0; l < 3; l++)
        {
            for (int m = 0; m < 3; m++)
            {
                macroBoard[l][m] = AVAILABLE_FIELD;
            }
        }
    }

    @Override
    public List<IMove> getAvailableMoves()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPlayerId(int column, int row)
    {
        return board[row][column];
    }

    @Override
    public boolean isEmpty()
    {
        for (int i = 0; i < 9; i++)
        {
            for (int k = 0; k < 9; k++)
            {
                if (board[i][k] != AVAILABLE_FIELD)
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isFull()
    {
        for (int i = 0; i < 9; i++)
        {
            for (int k = 0; k < 9; k++)
            {
                if (board[i][k] == AVAILABLE_FIELD || board[i][k] == EMPTY_FIELD)
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Boolean isInActiveMicroboard(int x, int y)
    {
        // This only happens the first move in the game
        if (activeMicroboard == 10 && board[x][y] == AVAILABLE_FIELD)
        {
            activeMicroboard=(findMicroBoard(x, y));
            return true;
        }
        
        
        if(activeMicroboard!=10)
        {
        String[] curBoard = microBoards.get(activeMicroboard-1);
     
        String coordinate = ""+x+"."+y;
        boolean isInBoard=false;
        int indexOfCoordinateInMicroBoard=100;
        
        for(int i=0;i<9;i++)
        {
            if(curBoard[i].equals(coordinate))
                isInBoard=true;
                indexOfCoordinateInMicroBoard=i;
        }
        
        if(isInBoard==true && board[x][y] == AVAILABLE_FIELD)
        {
            activeMicroboard=indexOfCoordinateInMicroBoard+1;
            return true;
            
        }
        }
 
        return false;
    }

    @Override
    public String[][] getBoard()
    {
        return board;
    }

    @Override
    public String[][] getMacroboard()
    {
        return macroBoard;
    }

    @Override
    public void setBoard(String[][] board)
    {
        this.board = board;
    }

    @Override
    public void setMacroboard(String[][] macroboard)
    {
        this.macroBoard = macroboard;
    }


    public void setMicroboardCoordinates()
    {
        String[] m1 = {"0.0", "0.1", "0.2", "1.0", "1.1", "1.2", "2.0","2.1","2.2"};
        String[] m2 = new String[9];
        String[] m3 = new String[9];
        String[] m4 = new String[9];
        String[] m5 = new String[9];
        String[] m6 = new String[9];
        String[] m7 = new String[9];
        String[] m8 = new String[9];
        String[] m9 = new String[9];
        
        
//        microBoards.add(e)
    }
    
    public int findMicroBoard(int x, int y)
    {
        for(String[] k : microBoards)
        {
            for(int i=0;i<9;i++)
            {
                if(k[i].equals(""+x+"."+y))
                {
                    return i;
                }
            
        }
            
        }
        return 100;
    }

}
