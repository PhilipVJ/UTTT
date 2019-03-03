package uttt.game;

import uttt.bot.IBot;
import static uttt.field.IField.AVAILABLE_FIELD;
import uttt.move.IMove;

/**
 * This is a proposed GameManager for Ultimate Tic-Tac-Toe, the implementation
 * of which is up to whoever uses this interface. Note that initializing a game
 * through the constructors means that you have to create a new instance of the
 * game manager for every new game of a different type (e.g. Human vs Human,
 * Human vs Bot or Bot vs Bot), which may not be ideal for your solution, so you
 * could consider refactoring that into an (re-)initialize method instead.
 *
 * @author mjl
 */
public class GameManager {

    private int moveCounter;
    private String lastBotMove;

    /**
     * Three different game modes.
     */
    public enum GameMode {
        HumanVsHuman,
        HumanVsBot,
        BotVsBot
    }

    private final IGameState currentState;
    private int currentPlayer = 0; //player0 == 0 && player1 == 1
    private GameMode mode = GameMode.HumanVsHuman;
    private IBot bot = null;
    private IBot bot2 = null;
    private int winnerIs = 100;

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Human vs Human
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     */
    public GameManager(IGameState currentState, int playerToStart) {
        this.currentState = currentState;

        currentPlayer = playerToStart;

        mode = GameMode.HumanVsHuman;
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Human vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     * @param bot The bot to play against in vsBot mode.
     */
    public GameManager(IGameState currentState, IBot bot) {
        this.currentState = currentState;
        mode = GameMode.HumanVsBot;
        this.bot = bot;
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Bot vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     * @param bot The first bot to play.
     * @param bot2 The second bot to play.
     */
    public GameManager(IGameState currentState, IBot bot, IBot bot2) {
        this.currentState = currentState;
        mode = GameMode.BotVsBot;
        this.bot = bot;
        this.bot2 = bot2;
    }

    /**
     * User input driven Update
     *
     * @param move The next user move
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean updateGame(IMove move) {
        //Verify the new move
        if (!verifyMoveLegality(move)) {
            return false;
        }
        //Update the currentState
        updateBoard(move);
        updateMacroboard(move);

        boolean winChecker = checkForWin();

        if (winChecker == true) {
            System.out.println("VINDER FUNDET");
        }

        if (currentState.getField().isFull() == true && winChecker == false) {
            System.out.println("UAFGJORT");
            winnerIs = 2;
        }

        //Update currentPlayer
        currentPlayer = (currentPlayer + 1) % 2;
        moveCounter++;
        currentState.setMoveNumber(moveCounter);

        return true;
    }

    /**
     * Non-User driven input, e.g. an update for playing a bot move.
     *
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean updateGame() {
        //Check game mode is set to one of the bot modes.
        assert (mode != GameMode.HumanVsHuman);

        //Check if player is bot, if so, get bot input and update the state based on that.
        if (mode == GameMode.HumanVsBot && currentPlayer == 1) {
            //Check bot is not equal to null, and throw an exception if it is.
            assert (bot != null);

            IMove botMove = bot.doMove(currentState);

            //Be aware that your bots might perform illegal moves.
            lastBotMove = "" + botMove.getX() + "." + botMove.getY();
            boolean done = updateGame(botMove);
            return done;
        }

        //Check bot is not equal to null, and throw an exception if it is.
        assert (bot != null);
        assert (bot2 != null);

        //TODO: Implement a bot vs bot Update.
        if (mode == GameMode.BotVsBot) {
            if(currentPlayer==0){
            IMove botMove = bot.doMove(currentState);
            lastBotMove = "" + botMove.getX() + "." + botMove.getY(); 
            boolean done = updateGame(botMove);
            return done;
            }
            if(currentPlayer==1){
              IMove botMove = bot2.doMove(currentState);
            lastBotMove = "" + botMove.getX() + "." + botMove.getY(); 
            boolean done = updateGame(botMove);
            return done;
              
            }
        }

        return false;
    }

    private Boolean verifyMoveLegality(IMove move) {
        //Test if the move is legal   
        //NOTE: should also check whether the move is placed on an occupied spot.
//        System.out.println("Checking move validity against macroboard available field");
//        System.out.println("Not currently checking move validity actual board");
        return currentState.getField().isInActiveMicroboard(move.getX(), move.getY());
    }

    private void updateBoard(IMove move) {
        String[][] currentBoard = currentState.getField().getBoard();

        if (currentPlayer == 0) {
            currentBoard[move.getX()][move.getY()] = "0";
        } else if (currentPlayer == 1) {
            currentBoard[move.getX()][move.getY()] = "1";
        }

        makeAllEmptyFieldsToDot(currentBoard);

        currentState.getField().setBoard(currentBoard);

    }

    /**
     * Makes all empty fields to ".". In setBoard we will make available fields
     * "-1"
     *
     * @param currentBoard
     */
    private void makeAllEmptyFieldsToDot(String[][] currentBoard) {

        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                if (currentBoard[i][k] == AVAILABLE_FIELD) {
                    currentBoard[i][k] = ".";
                }
            }
        }
    }

    /**
     * We don't use this method. Update microboard is connected to the
     * macroboard and it all happens at once
     *
     * @param move
     */
    private void updateMacroboard(IMove move) {

    }

    /**
     * Checks if the overall game is won (macro)
     *
     * @return
     */
    private boolean checkForWin() {

        String[][] grid = currentState.getField().getMacroboard();
        // checker for en vandret sejr
        for (int w = 0; w < 2; w++) {
            String player = "" + w;
            for (int i = 0; i < 3; i++) {
                if (grid[i][0].equals(player) && grid[i][1].equals(player) && grid[i][2].equals(player)) {
                    winnerIs = w;
                    return true;
                }
            }
        }

        // checker for en lodret sejr
        for (int w = 0; w < 2; w++) {
            String player = "" + w;
            for (int i = 0; i < grid.length; i++) {
                if (grid[0][i].equals(player) && grid[1][i].equals(player) && grid[2][i].equals(player)) {
                    winnerIs = w;
                    return true;
                }
            }
        }
        // checker for en diagonal sejr
        for (int w = 0; w < 2; w++) {
            String player = "" + w;
            if (grid[0][0].equals(player) && grid[1][1].equals(player) && grid[2][2].equals(player)) {
                winnerIs = w;
                return true;
            }
        }
        for (int w = 0; w < 2; w++) {
            String player = "" + w;
            if (grid[0][2].equals(player) && grid[1][1].equals(player) && grid[2][0].equals(player)) {
                winnerIs = w;
                return true;
            }
        }

        return false;
    }

    public int getWinnerIs() {
        return winnerIs;
    }

    public IGameState getCurrentState() {
        return currentState;
    }

    public String getLastBotMove() {
        return lastBotMove;
    }

}
