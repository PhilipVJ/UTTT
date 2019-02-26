/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttt.field;

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


    public Field()
    {
        board = new String[9][9];
        macroBoard = new String[3][3];
        activeMicroboard = 10;
        clearBoard();
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
        //Virker kun 1%
        if (activeMicroboard == 10 && board[x][y] == AVAILABLE_FIELD)
        {
            return true;
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

}
