/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttt.bot;

import java.util.ArrayList;
import java.util.List;
import static uttt.field.IField.AVAILABLE_FIELD;
import uttt.game.IGameState;
import uttt.move.IMove;
import uttt.move.Move;

/**
 * This bot first tries to get a line. If that isn't possible it will try to
 * block the opponent. If that doesn't work it will do a random move.
 *
 * @author Philip
 */
public class SmartestBot implements IBot {

    private String botName = "SmartestBot";
    private String[][] board;
    private String[][] macroBoard;
    private List<IMove> availableMoves;
    private IMove moveToDo;
    private int playerId;
    boolean setPlayerId = false;
    private String[][] copyBoard = new String[9][9];
    private String[][] copyMacro = new String[3][3];
    private String[][] copyCopyBoard = new String[9][9];
    private String[][] copyCopyMacro = new String[3][3];

    private IGameState currentState;
    private ArrayList<String[]> microBoards = new ArrayList<>();
    private int activeMicroBoard = 0;

    @Override
    public IMove doMove(IGameState state) {
        setMicroboardCoordinates();
        System.out.println("SMARTEST BOT");
        board = state.getField().getBoard();
        currentState = state;
        macroBoard = state.getField().getMacroboard();
        availableMoves = state.getField().getAvailableMoves();
        setPlayerId();

        calculateMove();

        return moveToDo;

    }

    private void setPlayerId() {
        if (setPlayerId == true) {
            return;
        }

        int numberOfEmptyFields = 0;

        //Checks if the board is empty or not
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                if (board[i][k] == AVAILABLE_FIELD) {
                    numberOfEmptyFields++;
                }
            }
        }

        if (numberOfEmptyFields == 81) {
            playerId = 0;
            System.out.println("PlayerId is 0");

        } else {
            playerId = 1;
            System.out.println("PlayerId is 1");
        }
        setPlayerId = true;
    }

    private void calculateMove() {
        // Always goes for a move which gives a line
        boolean foundValidMove = false;
        // Attack move
        System.out.println("Looking for ATTACK MOVE");
        for (IMove x : availableMoves) {
            copyBoards();

            if (checkMove(x, playerId)) {
                foundValidMove = true;
                moveToDo = x;
                System.out.println("SETTING ATTACK MOVE");
                break;
            }
        }
        // Defense move
        if (foundValidMove == false) {
            int otherPlayer;
            if (playerId == 1) {
                otherPlayer = 0;
            } else {
                otherPlayer = 1;
            }
            System.out.println("Looking for DEFENSE MOVE");
            for (IMove x : availableMoves) {
                copyBoards();

                if (checkMove(x, otherPlayer)) {
                    foundValidMove = true;
                    moveToDo = x;
                    System.out.println("SETTING DEFENCE MOVE");
                    break;
                }
            }

        }
        // Make sure the next move doesn't give the opponent a free line
        if (foundValidMove == false) {
            System.out.println("LOOKING FOR GOOD MOVE");
            if (findGoodMove() == true) {
                foundValidMove = true;
            }
        }

        // Random move
        if (foundValidMove == false) {
            setRandomMove();
        }

    }

    /**
     * Returns true if the given move gives a line to the specified player
     *
     * @param move
     * @param player
     * @return
     */
    private boolean checkMove(IMove move, int player) {
        int x = move.getX();
        int y = move.getY();

        boolean givesLine = false;

        copyBoard[x][y] = "" + player;

        checkForWinInMicro(player);
        // Checks if the move gives a new line

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {

                if (copyMacro[i][k].equals(macroBoard[i][k])) {
                    // do nothings
                } else {
                    givesLine = true;

                }
            }
        }
        if (givesLine) {
            return true;
        }
        return false;

    }

    private void setRandomMove() {
        System.out.println("Doing random move");
        int moveSize = availableMoves.size();
        Double randomNumber = Math.random() * moveSize;
        int rNumber = randomNumber.intValue();
        moveToDo = availableMoves.get(rNumber);
    }

    private void copyBoards() {
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                copyBoard[i][k] = board[i][k];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                copyMacro[i][k] = macroBoard[i][k];
            }
        }
    }

    private void checkForWinInMicro(int player) {
        checkForHorizontalWin(player);
        checkForVerticalWin(player);
        checkForDiagonalWin(player);

    }

    private boolean findGoodMove() {
        boolean foundMove = false;
        int otherPlayer;
        if (playerId == 1) {
            otherPlayer = 0;
        } else {
            otherPlayer = 1;
        }
        // Tager hver move objekt som er tilgængeligt
        List<IMove> allGoodMoves = new ArrayList<>();

        for (IMove x : availableMoves) {
            // laver en kopi af banen (både micro og macro)
            copyBoards();
            // Laver en kopi af kopien
            copyCopyBoard();
            // Sætter det pågældende move på microboardKopien
            copyBoard[x.getX()][x.getY()] = "" + playerId;

            // Udregn de næste gyldige træk
            List<IMove> newAvailableMoves = new ArrayList<>();
            //ActiveMicroBoard in the moment
            activeMicroBoard = findMicroBoard(x.getX(), x.getY());

            String[] curBoard = microBoards.get(activeMicroBoard - 1);

            String coordinate = "" + x.getX() + "." + x.getY();

            int indexOfCoordinateInMicroBoard = 100;

            for (int i = 0; i < 9; i++) {

                if (curBoard[i].equals(coordinate)) {
                    indexOfCoordinateInMicroBoard = i;
                }
            }
            // ActiveMicroBoard after a specific move
            activeMicroBoard = indexOfCoordinateInMicroBoard + 1;
            //Check if activeMicroBoard should be the entire field
            System.out.println("ACTIVE MICRO HERE: "+activeMicroBoard);
            if(checkForWinOrDrawOnMicro(activeMicroBoard))
            {
                System.out.println("CONTINUE");
                continue;
            }
            String[] currentMicroBoard = microBoards.get(activeMicroBoard - 1);
            // Laver alle moves til det nye activeMicroBoard
            for (int i = 0; i < 9; i++) {
                String coor = currentMicroBoard[i];

                char xCor = coor.charAt(0);
                char yCor = coor.charAt(2);

                int xCoord = Character.getNumericValue(xCor);
                int yCoord = Character.getNumericValue(yCor);

                Move move = new Move(xCoord, yCoord);
                if (copyBoard[xCoord][yCoord] == "-1" || copyBoard[xCoord][yCoord] == ".") {
                    newAvailableMoves.add(move);
                }

            }

            // Tjek om nogle af de nye træk vil kunne give 3 på stribe for modstanderen senere
            boolean opponentGotLine = false;
            for (IMove y : newAvailableMoves) {
                makeCopyOriginalAgain();
                if (checkMove(y, otherPlayer)) {
                    opponentGotLine = true;
                    break;
                }

            }

            if (opponentGotLine == false) {
                allGoodMoves.add(x);
                foundMove = true;

            }

        }
        System.out.println("Fundet gyldigt træk:" + foundMove);
        System.out.println("ANTAL GODE TRÆK: " + allGoodMoves.size());
        Double rNum = Math.random() * allGoodMoves.size();
        int rNumInt = rNum.intValue();
        if (allGoodMoves.size() > 0) {
            moveToDo = allGoodMoves.get(rNumInt);
        }
        return foundMove;
    }

    private void checkForDiagonalWin(int playerId) {

        // checker for en diagonal sejr
        String player = "" + playerId;
        // microboard 1
        if (copyBoard[0][0].equals(player) && copyBoard[1][1].equals(player) && copyBoard[2][2].equals(player)) {

            copyMacro[0][0] = player;

        }

        if (copyBoard[0][2].equals(player) && copyBoard[1][1].equals(player) && copyBoard[2][0].equals(player)) {

            copyMacro[0][0] = player;

        }
        // microboard 2
        if (copyBoard[0][3].equals(player) && copyBoard[1][4].equals(player) && copyBoard[2][5].equals(player)) {

            copyMacro[0][1] = player;

        }

        if (copyBoard[0][5].equals(player) && copyBoard[1][4].equals(player) && copyBoard[2][3].equals(player)) {

            copyMacro[0][1] = player;
            ;

        }
        // microboard 3
        if (copyBoard[0][6].equals(player) && copyBoard[1][7].equals(player) && copyBoard[2][8].equals(player)) {

            copyMacro[0][2] = player;

        }

        if (copyBoard[0][8].equals(player) && copyBoard[1][7].equals(player) && copyBoard[2][6].equals(player)) {

            copyMacro[0][2] = player;

        }
        // microboard 4
        if (copyBoard[3][0].equals(player) && copyBoard[4][1].equals(player) && copyBoard[5][2].equals(player)) {

            copyMacro[1][0] = player;

        }

        if (copyBoard[3][2].equals(player) && copyBoard[4][1].equals(player) && copyBoard[5][0].equals(player)) {

            copyMacro[1][0] = player;

        }
        // microboard 5
        if (copyBoard[3][3].equals(player) && copyBoard[4][4].equals(player) && copyBoard[5][5].equals(player)) {

            copyMacro[1][1] = player;

        }

        if (copyBoard[3][5].equals(player) && copyBoard[4][4].equals(player) && copyBoard[5][3].equals(player)) {

            copyMacro[1][1] = player;

        }
        // microboard 6
        if (copyBoard[3][6].equals(player) && copyBoard[4][7].equals(player) && copyBoard[5][8].equals(player)) {

            copyMacro[1][2] = player;

        }

        if (copyBoard[3][8].equals(player) && copyBoard[4][7].equals(player) && copyBoard[5][6].equals(player)) {

            copyMacro[1][2] = player;

        }
        // microboard 7
        if (copyBoard[6][0].equals(player) && copyBoard[7][1].equals(player) && copyBoard[8][2].equals(player)) {

            copyMacro[2][0] = player;

        }

        if (copyBoard[6][2].equals(player) && copyBoard[7][1].equals(player) && copyBoard[8][0].equals(player)) {

            copyMacro[2][0] = player;

        }
        // microboard 8
        if (copyBoard[6][3].equals(player) && copyBoard[7][4].equals(player) && copyBoard[8][5].equals(player)) {

            copyMacro[2][1] = player;

        }

        if (copyBoard[6][5].equals(player) && copyBoard[7][4].equals(player) && copyBoard[8][3].equals(player)) {

            copyMacro[2][1] = player;

        }
        // microboard 9
        if (copyBoard[6][6].equals(player) && copyBoard[7][7].equals(player) && copyBoard[8][8].equals(player)) {

            copyMacro[2][2] = player;

        }

        if (copyBoard[6][8].equals(player) && copyBoard[7][7].equals(player) && copyBoard[8][6].equals(player)) {

            copyMacro[2][2] = player;

        }

    }

    private void checkForVerticalWin(int playerId) {

        // checker for en lodret sejr
        String player = "" + playerId;
        for (int i = 0; i < 3; i++) {
            // microboard 1
            if (copyBoard[0][i].equals(player) && copyBoard[1][i].equals(player) && copyBoard[2][i].equals(player)) {

                copyMacro[0][0] = player;

            }
            // microboard 2
            if (copyBoard[0][i + 3].equals(player) && copyBoard[1][i + 3].equals(player) && copyBoard[2][i + 3].equals(player)) {

                copyMacro[0][1] = player;

            }
            // microboard 3
            if (copyBoard[0][i + 6].equals(player) && copyBoard[1][i + 6].equals(player) && copyBoard[2][i + 6].equals(player)) {

                copyMacro[0][2] = player;

            }
            // microboard 4
            if (copyBoard[3][i].equals(player) && copyBoard[4][i].equals(player) && copyBoard[5][i].equals(player)) {

                copyMacro[1][0] = player;

            }
            // microboard 5
            if (copyBoard[3][i + 3].equals(player) && copyBoard[4][i + 3].equals(player) && copyBoard[5][i + 3].equals(player)) {

                copyMacro[1][1] = player;

            }
            // microboard 6
            if (copyBoard[3][i + 6].equals(player) && copyBoard[4][i + 6].equals(player) && copyBoard[5][i + 6].equals(player)) {

                copyMacro[1][2] = player;

            }
            // microboard 7
            if (copyBoard[6][i].equals(player) && copyBoard[7][i].equals(player) && copyBoard[8][i].equals(player)) {

                copyMacro[2][0] = player;

            }
            // microboard 8
            if (copyBoard[6][i + 3].equals(player) && copyBoard[7][i + 3].equals(player) && copyBoard[8][i + 3].equals(player)) {

                copyMacro[2][1] = player;

            }
            // microboard 9
            if (copyBoard[6][i + 6].equals(player) && copyBoard[7][i + 6].equals(player) && copyBoard[8][i + 6].equals(player)) {

                copyMacro[2][2] = player;

            }

        }

    }

    private void checkForHorizontalWin(int playerId) {
        //        checker for en vandret  sejr

        String player = "" + playerId;
        for (int i = 0; i < 3; i++) {
            // microboard 1
            if (copyBoard[i][0].equals(player) && copyBoard[i][1].equals(player) && copyBoard[i][2].equals(player)) {

                copyMacro[0][0] = player;

            }
            // microboard 2
            if (copyBoard[i][3].equals(player) && copyBoard[i][4].equals(player) && copyBoard[i][5].equals(player)) {

                copyMacro[0][1] = player;

            }
            // microboard 3
            if (copyBoard[i][6].equals(player) && copyBoard[i][7].equals(player) && copyBoard[i][8].equals(player)) {

                copyMacro[0][2] = player;

            }

            // microboard 4
            if (copyBoard[i + 3][0].equals(player) && copyBoard[i + 3][1].equals(player) && copyBoard[i + 3][2].equals(player)) {

                copyMacro[1][0] = player;

            }
            // microboard 5
            if (copyBoard[i + 3][3].equals(player) && copyBoard[i + 3][4].equals(player) && copyBoard[i + 3][5].equals(player)) {

                copyMacro[1][1] = player;

            }
            // microboard 6
            if (copyBoard[i + 3][6].equals(player) && copyBoard[i + 3][7].equals(player) && copyBoard[i + 3][8].equals(player)) {

                copyMacro[1][2] = player;

            }

            // microboard 7
            if (copyBoard[i + 6][0].equals(player) && copyBoard[i + 6][1].equals(player) && copyBoard[i + 6][2].equals(player)) {

                copyMacro[2][0] = player;

            }
            // microboard 8
            if (copyBoard[i + 6][3].equals(player) && copyBoard[i + 6][4].equals(player) && copyBoard[i + 6][5].equals(player)) {

                copyMacro[2][1] = player;

            }
            // microboard 9
            if (copyBoard[i + 6][6].equals(player) && copyBoard[i + 6][7].equals(player) && copyBoard[i + 6][8].equals(player)) {

                copyMacro[2][2] = player;

            }

        }
    }

    @Override
    public String getBotName() {
        return botName;
    }

    public int findMicroBoard(int x, int y) {
        String coordinate = "" + x + "." + y;
        int counter = 0;
        for (String[] k : microBoards) {
            counter++;
            for (int i = 0; i < 9; i++) {
                if (k[i].equals(coordinate)) {
                    return counter;
                }

            }

        }
        //Couldn't find coordinate - returns 100 - shouldn't happen
        return 100;
    }

    public void setMicroboardCoordinates() {
        String[] m1
                = {
                    "0.0", "0.1", "0.2", "1.0", "1.1", "1.2", "2.0", "2.1", "2.2"
                };
        String[] m2
                = {
                    "0.3", "0.4", "0.5", "1.3", "1.4", "1.5", "2.3", "2.4", "2.5"
                };
        String[] m3
                = {
                    "0.6", "0.7", "0.8", "1.6", "1.7", "1.8", "2.6", "2.7", "2.8"
                };
        String[] m4
                = {
                    "3.0", "3.1", "3.2", "4.0", "4.1", "4.2", "5.0", "5.1", "5.2"
                };
        String[] m5
                = {
                    "3.3", "3.4", "3.5", "4.3", "4.4", "4.5", "5.3", "5.4", "5.5"
                };
        String[] m6
                = {
                    "3.6", "3.7", "3.8", "4.6", "4.7", "4.8", "5.6", "5.7", "5.8"
                };
        String[] m7
                = {
                    "6.0", "6.1", "6.2", "7.0", "7.1", "7.2", "8.0", "8.1", "8.2"
                };
        String[] m8
                = {
                    "6.3", "6.4", "6.5", "7.3", "7.4", "7.5", "8.3", "8.4", "8.5"
                };
        String[] m9
                = {
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

    private void copyCopyBoard() {
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                copyCopyBoard[i][k] = copyBoard[i][k];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                copyCopyMacro[i][k] = copyMacro[i][k];
            }
        }
    }

    private void makeCopyOriginalAgain() {
        {
            for (int i = 0; i < 9; i++) {
                for (int k = 0; k < 9; k++) {
                    copyBoard[i][k] = copyCopyBoard[i][k];
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int k = 0; k < 3; k++) {
                    copyMacro[i][k] = copyCopyMacro[i][k];
                }
            }
        }
    }

    private boolean checkForWinOrDrawOnMicro(int activeMicroBoard) {
        boolean foundWinOrDraw=false;
   
        
        
        switch (activeMicroBoard){
            case 1: if(!copyMacro[0][0].equals("-1"))
            {
                System.out.println("case 1 true");
                System.out.println("PMacro"+copyMacro[0][0]);
                foundWinOrDraw=true;              
            }
            break;
            
            case 2: if(!copyMacro[0][1].equals("-1"))
            {
                 System.out.println("case 2 true");
                 System.out.println("PMacro"+copyMacro[0][1]);
                foundWinOrDraw=true;              
            }
            break;
            
            case 3: if(!copyMacro[0][2].equals("-1"))
            {
                 System.out.println("case 3 true");
                 System.out.println("PMacro"+copyMacro[0][2]);
                foundWinOrDraw=true;              
            }
            break;
            
            case 4: if(!copyMacro[1][0].equals("-1"))
            {
                 System.out.println("case 4 true");
                 System.out.println("PMacro"+copyMacro[1][0]);
                foundWinOrDraw=true;              
            }
            break;
            
            case 5: 
                if(!copyMacro[1][1].equals("-1"))
            {
                 System.out.println("case 5 true");
                 System.out.println("PMacro"+copyMacro[1][1]);
                foundWinOrDraw=true;              
            }
            break;
            
            case 6: if(!copyMacro[1][2].equals("-1"))
            {
             System.out.println("case 6 true");
             System.out.println("PMacro"+copyMacro[1][2]);
                foundWinOrDraw=true;              
            }           
            break;
            
            case 7: if(!copyMacro[2][0].equals("-1"))
            {
                 System.out.println("case 7 true");
                 System.out.println("PMacro"+copyMacro[2][0]);
                foundWinOrDraw=true;              
            }
            break;
            
            case 8: if(!copyMacro[2][1].equals("-1"))
            {
                 System.out.println("case 8 true");
                 System.out.println("PMacro"+copyMacro[2][1]);
                foundWinOrDraw=true;              
            }
            break;
            
            case 9: if(!copyMacro[2][2].equals("-1"))
            {
                 System.out.println("case 9 true");
                 System.out.println("PMacro"+copyMacro[2][2]);
                foundWinOrDraw=true;              
            }
            break;
    }
     return foundWinOrDraw;  
    }
}
