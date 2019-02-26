/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttt.field;

import java.util.ArrayList;
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
    //The current - to be played in - microboard (3x3)
    private int activeMicroboard;
    //Contains an list of 9 elements, representing each microboard. Each element contains 9 different coordinates
    private ArrayList<String[]> microBoards;

    public Field()
    {
        board = new String[9][9];
        macroBoard = new String[3][3];
        // 10 means the current Microboard is the entire arena (all are available)
        activeMicroboard = 10;
        clearBoard();
        microBoards = new ArrayList();
        setMicroboardCoordinates();

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
        if (activeMicroboard == 10 && board[x][y] == AVAILABLE_FIELD)
        {
            activeMicroboard = (findMicroBoard(x, y));
            return true;
        }

        if (activeMicroboard != 10)
        {
            String[] curBoard = microBoards.get(activeMicroboard - 1);

            String coordinate = "" + x + "." + y;

            boolean isInBoard = false;
            int indexOfCoordinateInMicroBoard = 100;

            for (int i = 0; i < 9; i++)
            {
                if (curBoard[i].equals(coordinate))
                {
                    isInBoard = true;
                    indexOfCoordinateInMicroBoard = i;
                }
            }

            if (isInBoard == true && board[x][y] == AVAILABLE_FIELD)
            {
                activeMicroboard = indexOfCoordinateInMicroBoard + 1;
                if (checkForFullMicroBoard() == true)
                {
                    arrivedAtFullMicroBoard();
                }

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
        String[][] newBoard = board;
        // Hvis activeMicroboard er 10 (der er frit valg) så skal alle felter tomme felter laves available
        if (activeMicroboard == 10)
        {
            for (int i = 0; i < 9; i++)
            {
                for (int k = 0; k < 9; k++)
                {
                    if (newBoard[i][k].equals(EMPTY_FIELD))
                    {
                        newBoard[i][k]=AVAILABLE_FIELD;
                    }
                }
            }
            
        }

        if (activeMicroboard != 10)
        {
            makePlayableFieldsToMinusOne(newBoard);
        }

        this.board = newBoard;

    }
/**
 * Vi laver alle punktummer i nuværende microboard om til -1'ere
 * @param newBoard 
 */
    private void makePlayableFieldsToMinusOne(String[][] newBoard)
    {
        String[] currentMicroBoard = microBoards.get(activeMicroboard - 1);
        
       
        for (int i = 0; i < 9; i++)
        {
            String coordinate = currentMicroBoard[i];
            
            char xCor = coordinate.charAt(0);
            char yCor = coordinate.charAt(2);
            int x = Character.getNumericValue(xCor);
            int y = Character.getNumericValue(yCor);
            
            String mBoard = newBoard[x][y];
            
            if (mBoard.equals("."))
            {
                
                newBoard[x][y] = AVAILABLE_FIELD;
            }
        }
    }

    @Override
    public void setMacroboard(String[][] macroboard)
    {
        this.macroBoard = macroboard;
    }

    public void setMicroboardCoordinates()
    {
        String[] m1 =
        {
            "0.0", "0.1", "0.2", "1.0", "1.1", "1.2", "2.0", "2.1", "2.2"
        };
        String[] m2 =
        {
            "0.3", "0.4", "0.5", "1.3", "1.4", "1.5", "2.3", "2.4", "2.5"
        };
        String[] m3 =
        {
            "0.6", "0.7", "0.8", "1.6", "1.7", "1.8", "2.6", "2.7", "2.8"
        };
        String[] m4 =
        {
            "3.0", "3.1", "3.2", "4.0", "4.1", "4.2", "5.0", "5.1", "5.2"
        };
        String[] m5 =
        {
            "3.3", "3.4", "3.5", "4.3", "4.4", "4.5", "5.3", "5.4", "5.5"
        };
        String[] m6 =
        {
            "3.6", "3.7", "3.8", "4.6", "4.7", "4.8", "5.6", "5.7", "5.8"
        };
        String[] m7 =
        {
            "6.0", "6.1", "6.2", "7.0", "7.1", "7.2", "8.0", "8.1", "8.2"
        };
        String[] m8 =
        {
            "6.3", "6.4", "6.5", "7.3", "7.4", "7.5", "8.3", "8.4", "8.5"
        };
        String[] m9 =
        {
            "6.6", "6.7", "6.8", "7.6", "7.7", "7.8", "8.6", "8.7", "8.8"
        };

        microBoards.add(m1);
        microBoards.add(m2);
        microBoards.add(m3);
        microBoards.add(m4);
        microBoards.add(m5);
        microBoards.add(m6);
        microBoards.add(m7);
        microBoards.add(m8);
        microBoards.add(m9);
    }

    public int findMicroBoard(int x, int y)
    {
        String coordinate = "" + x + "." + y;
        for (String[] k : microBoards)
        {
            for (int i = 0; i < 9; i++)
            {
                if (k[i].equals(coordinate))
                {
                    return i + 1;
                }

            }

        }
        //Couldn't find coordinate
        return 100;
    }

    private void arrivedAtFullMicroBoard()
    {
        activeMicroboard = 10;
        
        //Makes all empty spaces an available field
        for (int i = 0; i < 9; i++)
        {
            for (int k = 0; k < 9; k++)
            {
                if (board[i][k] == ".")
                {
                    board[i][k] = AVAILABLE_FIELD;
                }
            }
        }

    }

    private boolean checkForFullMicroBoard()
    {
        String[] curBoard = microBoards.get(activeMicroboard - 1);
        int foundPlayerMarks = 0;

        for (int i = 0; i < 9; i++)
        {
            String coordinate = curBoard[i];

            char xCor = coordinate.charAt(0);
            char yCor = coordinate.charAt(2);
            int x = Character.getNumericValue(xCor);
            int y = Character.getNumericValue(yCor);

            if (board[x][y].equals("X") || board[x][y].equals("O"))
            {
                foundPlayerMarks++;
            }
        }

        if (foundPlayerMarks == 9)
        {
            return true;
        }

        return false;

    }

}
