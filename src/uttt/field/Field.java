/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttt.field;

import java.util.ArrayList;
import java.util.List;
import uttt.GUI.Controller.GameboardController;
import uttt.move.IMove;

/**
 *
 * @author Philip
 */
public class Field implements IField
{

    GameboardController gbModel;

    private String[][] board;
    private String[][] macroBoard;

    private Integer[] lastMove;
    //The current - to be played in - microboard (3x3)
    private int activeMicroboard;
    //Contains an list of 9 elements, representing each microboard. Each element contains 9 different coordinates
    private ArrayList<String[]> microBoards;

    private boolean micro1Done = false;
    private boolean micro2Done = false;
    private boolean micro3Done = false;
    private boolean micro4Done = false;
    private boolean micro5Done = false;
    private boolean micro6Done = false;
    private boolean micro7Done = false;
    private boolean micro8Done = false;
    private boolean micro9Done = false;

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
            System.out.println("THIS ONE");
            activeMicroboard = (findMicroBoard(x, y));
            isItNewGame();
            if (checkForWonMicroBoard() == true)
            {
                System.out.println("SETTING UP TO 10");
                activeMicroboard = 10;
            }
            return true;
        }

        if (activeMicroboard != 10)
        {
            String[] curBoard = microBoards.get(activeMicroboard - 1);
            System.out.println("THAT ONE");
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
                System.out.println("IS IN BOARD");
                activeMicroboard = indexOfCoordinateInMicroBoard + 1;
                if (checkForFullMicroBoard() == true)
                {
                    System.out.println("ARRIVED AT FULL MICROBOARD");
                    arrivedAtFullMicroBoard();
                }
                if (checkForWonMicroBoard() == true)
                {
                    System.out.println("SETTING UP TO 10");
                    activeMicroboard = 10;
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
                        newBoard[i][k] = AVAILABLE_FIELD;
                    }
                }
            }

        }

        if (activeMicroboard != 10)
        {
            makePlayableFieldsToMinusOne(newBoard);
        }
        checkForWinInMicro(newBoard);
        this.board = newBoard;
        System.out.println("" + activeMicroboard);
        printBoard();

    }

    /**
     * Vi laver alle punktummer i nuværende microboard om til -1'ere
     *
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

    /**
     * Checks for a full microboard (a draw)
     *
     * @return
     */
    private boolean checkForFullMicroBoard()
    {
        String[] curBoard = microBoards.get(activeMicroboard - 1);
        int foundPlayerMarks = 0;
        boolean foundO = false;
        boolean foundX = false;

        for (int i = 0; i < 9; i++)
        {
            String coordinate = curBoard[i];

            char xCor = coordinate.charAt(0);
            char yCor = coordinate.charAt(2);
            int x = Character.getNumericValue(xCor);
            int y = Character.getNumericValue(yCor);

            if (board[x][y].equals("0"))
            {
                foundO = true;
                foundPlayerMarks++;
            }

            if (board[x][y].equals("1"))
            {
                foundX = true;
                foundPlayerMarks++;
            }
        }

        if (foundPlayerMarks == 9 && foundO == true && foundX == true)
        {
            return true;
        }

        return false;

    }

    private void checkForWinInMicro(String[][] newBoard)
    {
//        checker for en vandret  sejr 

        for (int w = 0; w < 2; w++)
        {
            String player = "" + w;
            for (int i = 0; i < 3; i++)
            {
                // microboard 1
                if (newBoard[i][0].equals(player) && newBoard[i][1].equals(player) && newBoard[i][2].equals(player) && !micro1Done)
                {
                    makeAllFieldsToOneOrZero(w, 1, newBoard);
                    macroBoard[0][0] = player;
                    micro1Done = true;
                }
                // microboard 2
                if (newBoard[i][3].equals(player) && newBoard[i][4].equals(player) && newBoard[i][5].equals(player) && !micro2Done)
                {
                    makeAllFieldsToOneOrZero(w, 2, newBoard);
                    macroBoard[0][1] = player;
                    micro2Done = true;

                }
                // microboard 3
                if (newBoard[i][6].equals(player) && newBoard[i][7].equals(player) && newBoard[i][8].equals(player) && !micro3Done)
                {
                    makeAllFieldsToOneOrZero(w, 4, newBoard);
                    macroBoard[0][2] = player;
                    micro3Done = true;
                }

                // microboard 4
                if (newBoard[i + 3][0].equals(player) && newBoard[i + 3][1].equals(player) && newBoard[i + 3][2].equals(player) && !micro4Done)
                {
                    makeAllFieldsToOneOrZero(w, 4, newBoard);
                    macroBoard[1][0] = player;
                    micro4Done = true;
                }
                // microboard 5
                if (newBoard[i + 3][3].equals(player) && newBoard[i + 3][4].equals(player) && newBoard[i + 3][5].equals(player) && !micro5Done)
                {
                    makeAllFieldsToOneOrZero(w, 5, newBoard);
                    macroBoard[1][1] = player;
                    micro5Done = true;
                }
                // microboard 6
                if (newBoard[i + 3][6].equals(player) && newBoard[i + 3][7].equals(player) && newBoard[i + 3][8].equals(player) && !micro6Done)
                {
                    makeAllFieldsToOneOrZero(w, 6, newBoard);
                    macroBoard[1][2] = player;
                    micro6Done = true;
                }

                // microboard 7
                if (newBoard[i + 6][0].equals(player) && newBoard[i + 6][1].equals(player) && newBoard[i + 6][2].equals(player) && !micro7Done)
                {
                    makeAllFieldsToOneOrZero(w, 7, newBoard);
                    macroBoard[2][0] = player;
                    micro7Done = true;
                }
                // microboard 8
                if (newBoard[i + 6][3].equals(player) && newBoard[i + 6][4].equals(player) && newBoard[i + 6][5].equals(player) && !micro8Done)
                {
                    makeAllFieldsToOneOrZero(w, 8, newBoard);
                    macroBoard[2][1] = player;
                    micro8Done = true;
                }
                // microboard 9
                if (newBoard[i + 6][6].equals(player) && newBoard[i + 6][7].equals(player) && newBoard[i + 6][8].equals(player) && !micro9Done)
                {
                    makeAllFieldsToOneOrZero(w, 9, newBoard);
                    macroBoard[2][2] = player;
                    micro9Done = true;
                }

            }
        }
        // checker for en lodret sejr
        for (int k = 0; k < 2; k++)
        {
            String player = "" + k;
            for (int i = 0; i < 3; i++)
            {
                // microboard 1
                if (newBoard[0][i].equals(player) && newBoard[1][i].equals(player) && newBoard[2][i].equals(player) && !micro1Done)
                {
                    makeAllFieldsToOneOrZero(k, 1, newBoard);
                    macroBoard[0][0] = player;
                    micro1Done = true;
                }
                // microboard 2
                if (newBoard[0][i + 3].equals(player) && newBoard[1][i + 3].equals(player) && newBoard[2][i + 3].equals(player) && !micro2Done)
                {
                    makeAllFieldsToOneOrZero(k, 2, newBoard);
                    macroBoard[0][1] = player;
                    micro2Done = true;
                }
                // microboard 3
                if (newBoard[0][i + 6].equals(player) && newBoard[1][i + 6].equals(player) && newBoard[2][i + 6].equals(player) && !micro3Done)
                {
                    makeAllFieldsToOneOrZero(k, 3, newBoard);
                    macroBoard[0][2] = player;
                    micro3Done = true;
                }
                // microboard 4
                if (newBoard[3][i].equals(player) && newBoard[4][i].equals(player) && newBoard[5][i].equals(player) && !micro4Done)
                {
                    makeAllFieldsToOneOrZero(k, 4, newBoard);
                    macroBoard[1][0] = player;
                    micro4Done = true;
                }
                // microboard 5
                if (newBoard[3][i + 3].equals(player) && newBoard[4][i + 3].equals(player) && newBoard[5][i + 3].equals(player) && !micro5Done)
                {
                    makeAllFieldsToOneOrZero(k, 5, newBoard);
                    macroBoard[1][1] = player;
                    micro5Done = true;
                }
                // microboard 6
                if (newBoard[3][i + 6].equals(player) && newBoard[4][i + 6].equals(player) && newBoard[5][i + 6].equals(player) && !micro6Done)
                {
                    makeAllFieldsToOneOrZero(k, 6, newBoard);
                    macroBoard[1][2] = player;
                    micro6Done = true;
                }
                // microboard 7
                if (newBoard[6][i].equals(player) && newBoard[7][i].equals(player) && newBoard[8][i].equals(player) && !micro7Done)
                {
                    makeAllFieldsToOneOrZero(k, 7, newBoard);
                    macroBoard[2][0] = player;
                    micro7Done = true;
                }
                // microboard 8
                if (newBoard[6][i + 3].equals(player) && newBoard[7][i + 3].equals(player) && newBoard[8][i + 3].equals(player) && !micro8Done)
                {
                    makeAllFieldsToOneOrZero(k, 8, newBoard);
                    macroBoard[2][1] = player;
                    micro8Done = true;
                }
                // microboard 9
                if (newBoard[6][i + 6].equals(player) && newBoard[7][i + 6].equals(player) && newBoard[8][i + 6].equals(player) && !micro9Done)
                {
                    makeAllFieldsToOneOrZero(k, 9, newBoard);
                    macroBoard[2][2] = player;
                    micro9Done = true;
                }

            }
        }

        // checker for en diagonal sejr
        for (int w = 0; w < 2; w++)
        {
            String player = "" + w;
            // microboard 1
            if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player) && !micro1Done)
            {
                makeAllFieldsToOneOrZero(w, 1, newBoard);
                macroBoard[0][0] = player;
                micro1Done = true;
            }

            if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player) && !micro1Done)
            {
                makeAllFieldsToOneOrZero(w, 1, newBoard);
                macroBoard[0][0] = player;
                micro1Done = true;
            }
            // microboard 2
            if (board[0][3].equals(player) && board[1][4].equals(player) && board[2][5].equals(player) && !micro2Done)
            {
                makeAllFieldsToOneOrZero(w, 2, newBoard);
                macroBoard[0][1] = player;
                micro2Done = true;
            }

            if (board[0][5].equals(player) && board[1][4].equals(player) && board[2][3].equals(player) && !micro2Done)
            {
                makeAllFieldsToOneOrZero(w, 2, newBoard);
                macroBoard[0][1] = player;
                micro2Done = true;
            }
            // microboard 3
            if (board[0][6].equals(player) && board[1][7].equals(player) && board[2][8].equals(player) && !micro3Done)
            {
                makeAllFieldsToOneOrZero(w, 3, newBoard);
                macroBoard[0][2] = player;
                micro3Done = true;
            }

            if (board[0][8].equals(player) && board[1][7].equals(player) && board[2][6].equals(player) && !micro3Done)
            {
                makeAllFieldsToOneOrZero(w, 3, newBoard);
                macroBoard[0][2] = player;
                micro3Done = true;
            }
            // microboard 4
            if (board[3][0].equals(player) && board[4][1].equals(player) && board[5][2].equals(player) && !micro4Done)
            {
                makeAllFieldsToOneOrZero(w, 4, newBoard);
                macroBoard[1][0] = player;
                micro4Done = true;
            }

            if (board[3][2].equals(player) && board[4][1].equals(player) && board[5][0].equals(player) && !micro4Done)
            {
                makeAllFieldsToOneOrZero(w, 4, newBoard);
                macroBoard[1][0] = player;
                micro4Done = true;
            }
            // microboard 5
            if (board[3][3].equals(player) && board[4][4].equals(player) && board[5][5].equals(player) && !micro5Done)
            {
                makeAllFieldsToOneOrZero(w, 5, newBoard);
                macroBoard[1][1] = player;
                micro5Done = true;
            }

            if (board[3][5].equals(player) && board[4][4].equals(player) && board[5][3].equals(player) && !micro5Done)
            {
                makeAllFieldsToOneOrZero(w, 5, newBoard);
                macroBoard[1][1] = player;
                micro5Done = true;
            }
            // microboard 6
            if (board[3][6].equals(player) && board[4][7].equals(player) && board[5][8].equals(player) && !micro6Done)
            {
                makeAllFieldsToOneOrZero(w, 6, newBoard);
                macroBoard[1][2] = player;
                micro6Done = true;
            }

            if (board[3][8].equals(player) && board[4][7].equals(player) && board[5][6].equals(player) && !micro6Done)
            {
                makeAllFieldsToOneOrZero(w, 6, newBoard);
                macroBoard[1][2] = player;
                micro6Done = true;
            }
            // microboard 7
            if (board[6][0].equals(player) && board[7][1].equals(player) && board[8][2].equals(player) && !micro7Done)
            {
                makeAllFieldsToOneOrZero(w, 7, newBoard);
                macroBoard[2][0] = player;
                micro7Done = true;
            }

            if (board[6][2].equals(player) && board[7][1].equals(player) && board[8][0].equals(player) && !micro7Done)
            {
                makeAllFieldsToOneOrZero(w, 7, newBoard);
                macroBoard[2][0] = player;
                micro7Done = true;
            }
            // microboard 8
            if (board[6][3].equals(player) && board[7][4].equals(player) && board[8][5].equals(player) && !micro8Done)
            {
                makeAllFieldsToOneOrZero(w, 8, newBoard);
                macroBoard[2][1] = player;
                micro8Done = true;
            }

            if (board[6][5].equals(player) && board[7][4].equals(player) && board[8][3].equals(player) && !micro8Done)
            {
                makeAllFieldsToOneOrZero(w, 8, newBoard);
                macroBoard[2][1] = player;
                micro8Done = true;
            }
            // microboard 9
            if (board[6][6].equals(player) && board[7][7].equals(player) && board[8][8].equals(player) && !micro9Done)
            {
                makeAllFieldsToOneOrZero(w, 9, newBoard);
                macroBoard[2][2] = player;
                micro9Done = true;
            }

            if (board[6][8].equals(player) && board[7][7].equals(player) && board[8][6].equals(player) && !micro9Done)
            {
                makeAllFieldsToOneOrZero(w, 9, newBoard);
                macroBoard[2][2] = player;
                micro9Done = true;
            }
        }
    }

    public boolean isItNewGame()
    {
        if (activeMicroboard == 10)
        {
            gbModel.boardLigth(10);
            System.out.println("lyset skulle virke");
            return true;
        }
        return false;
    }

    private void makeAllFieldsToOneOrZero(int player, int microboard, String[][] newBoard)
    {
        String[] currentMicroBoard = microBoards.get(microboard - 1);

        for (int i = 0; i < 9; i++)
        {
            String coordinate = currentMicroBoard[i];
            char xCor = coordinate.charAt(0);
            char yCor = coordinate.charAt(2);
            int x = Character.getNumericValue(xCor);
            int y = Character.getNumericValue(yCor);

            newBoard[x][y] = "" + player;
            arrivedAtFullMicroBoard();
        }
    }

    private void printBoard()
    {
//        for (int i = 0; i < 9; i++)
//        {
//            for (int k = 0; k < 9; k++)
//            {
//                System.out.println("" + board[i][k]);
//            }
//        }
//System.out.println("Printing macro");
//        for (int i = 0; i < 3; i++)
//        {
//            for (int k = 0; k < 3; k++)
//            {
//                System.out.println("" + macroBoard[i][k]);
//            }
//        }
    }

    private boolean checkForWonMicroBoard()
    {
        int aM = activeMicroboard;
        System.out.println("CHECKING");

        switch (activeMicroboard)
        {
            case 1:
                if (micro1Done)
                {
                    System.out.println("FOUND");
                    return true;

                }

        }
        return false;
    }
}
