/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttt.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uttt.bot.IBot;
import uttt.bot.RandomBot;
import uttt.bot.SmartBot;
import uttt.bot.SmarterBot;
import uttt.bot.SmartestBot;
import uttt.game.GameManager;
import uttt.game.GameState;
import uttt.move.Move;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class GameboardController implements Initializable
{

    @FXML
    private GridPane macroBoard;
    @FXML
    private GridPane gridPane1;
    @FXML
    private GridPane gridPane2;
    @FXML
    private GridPane gridPane3;
    @FXML
    private GridPane gridPane4;
    @FXML
    private GridPane gridPane5;
    @FXML
    private GridPane gridPane6;
    @FXML
    private GridPane gridPane7;
    @FXML
    private GridPane gridPane8;
    @FXML
    private GridPane gridPane9;
    @FXML
    private Label lblXWins;
    @FXML
    private Label lblOWins;
    @FXML
    private Label lblDraw;
    @FXML
    private Label winnerIs;
    @FXML
    private Label lblPlayerTurn;
    @FXML
    private Button btnBackToMainScr;

    private Stage stage;
    private GameManager gManager;

    private boolean gameOver = false;
    private boolean grid1isDone = false;
    private boolean grid9isDone = false;
    private boolean grid8isDone = false;
    private boolean grid7isDone = false;
    private boolean grid6isDone = false;
    private boolean grid5isDone = false;
    private boolean grid4isDone = false;
    private boolean grid3isDone = false;
    private boolean grid2isDone = false;

    private int numberOfXWins = 0;
    private int numberOfOWins = 0;
    private int numberOfDraws = 0;
    private int currentPlayer = 0;
    private int gMode;

    private String[][] mBoard;

    private GameMode gameMode = null;
    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private Button btn00;
    @FXML
    private Button btn01;
    @FXML
    private Button btn02;
    @FXML
    private Button btn10;
    @FXML
    private Button btn20;
    @FXML
    private Button btn11;
    @FXML
    private Button btn21;
    @FXML
    private Button btn12;
    @FXML
    private Button btn22;
    @FXML
    private Button btn03;
    @FXML
    private Button btn04;
    @FXML
    private Button btn05;
    @FXML
    private Button btn13;
    @FXML
    private Button btn14;
    @FXML
    private Button btn15;
    @FXML
    private Button btn23;
    @FXML
    private Button btn24;
    @FXML
    private Button btn25;
    @FXML
    private Button btn07;
    @FXML
    private Button btn06;
    @FXML
    private Button btn08;
    @FXML
    private Button btn16;
    @FXML
    private Button btn17;
    @FXML
    private Button btn18;
    @FXML
    private Button btn26;
    @FXML
    private Button btn27;
    @FXML
    private Button btn28;
    @FXML
    private Button btn30;
    @FXML
    private Button btn31;
    @FXML
    private Button btn40;
    @FXML
    private Button btn41;
    @FXML
    private Button btn50;
    @FXML
    private Button btn51;
    @FXML
    private Button btn52;
    @FXML
    private Button btn42;
    @FXML
    private Button btn32;
    @FXML
    private Button btn34;
    @FXML
    private Button btn33;
    @FXML
    private Button btn43;
    @FXML
    private Button btn44;
    @FXML
    private Button btn53;
    @FXML
    private Button btn54;
    @FXML
    private Button btn55;
    @FXML
    private Button btn45;
    @FXML
    private Button btn35;
    @FXML
    private Button btn36;
    @FXML
    private Button btn37;
    @FXML
    private Button btn46;
    @FXML
    private Button btn47;
    @FXML
    private Button btn38;
    @FXML
    private Button btn48;
    @FXML
    private Button btn56;
    @FXML
    private Button btn57;
    @FXML
    private Button btn58;
    @FXML
    private Button btn60;
    @FXML
    private Button btn62;
    @FXML
    private Button btn61;
    @FXML
    private Button btn70;
    @FXML
    private Button btn71;
    @FXML
    private Button btn72;
    @FXML
    private Button btn80;
    @FXML
    private Button btn81;
    @FXML
    private Button btn82;
    @FXML
    private Button btn63;
    @FXML
    private Button btn64;
    @FXML
    private Button btn73;
    @FXML
    private Button btn74;
    @FXML
    private Button btn65;
    @FXML
    private Button btn75;
    @FXML
    private Button btn83;
    @FXML
    private Button btn84;
    @FXML
    private Button btn85;
    @FXML
    private Button btn66;
    @FXML
    private Button btn67;
    @FXML
    private Button btn68;
    @FXML
    private Button btn76;
    @FXML
    private Button btn77;
    @FXML
    private Button btn78;
    @FXML
    private Button btn86;
    @FXML
    private Button btn87;
    @FXML
    private Button btn88;
    @FXML
    private Button btnClear;

    private HashMap buttonHashMap;
    private int numberOfBotPlays;

    private String bot1;
    private String bot2;

    private IBot AIBot1;
    private IBot AIBot2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        startLight();
        playerTurn();
        buttonHashMap = new HashMap();
        makeButtonHashMap();

    }

    private void setBotMark()
    {
        String lastMove = gManager.getLastBotMove();

        Button btn = (Button) buttonHashMap.get(lastMove);
        InnerShadow kryds = new InnerShadow(20, Color.RED);
        btn.setEffect(kryds);
        btn.setStyle("-fx-font-size: 34px;");
        btn.setText("X");

    }

    private void setBotVsBotMark(int player)
    {
        String lastMove = gManager.getLastBotMove();

        Button btn = (Button) buttonHashMap.get(lastMove);
        ;
        btn.setStyle("-fx-font-size: 34px;");

        if (player == 1)
        {
            InnerShadow kryds = new InnerShadow(20, Color.RED);
            btn.setEffect(kryds);
            btn.setText("X");
        }
        if (player == 0)
        {
            InnerShadow bolle = new InnerShadow(25, Color.BLUE);
            btn.setEffect(bolle);
            btn.setText("O");
        }

    }

    private void startBotFight() throws InterruptedException
    {

        if (gManager.updateGame())
        {
            mBoard = gManager.getCurrentState().getField().getMacroboard();
            setBotVsBotMark(currentPlayer);

            setWinner();

            currentPlayer = (currentPlayer + 1) % 2;

            if (gameOver)
            {
                DropShadow h = new DropShadow();
                h.setColor(Color.GREEN);
                macroBoard.setEffect(h);
                return;

            }
            // These alerts are used for testing, when you want to see a bots move step by step. Disable initBotFight in setGameManager also
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setX(0);
//            alert.showAndWait();
            
            
            startBotFight();

        }

    }

    private void initBotFight() throws InterruptedException
    {
        for (int i = numberOfBotPlays; i > 0; i--)
        {
            startBotFight();
            gManager = new GameManager(new GameState(), AIBot1, AIBot2);
            if (i > 1)
            {
                cleanUpStage();
            }
        }
        fixBigMarkings();
    }

    private void cleanUpStage()
    {
        clearLight();
        startLight();
        clearButtons();

        currentPlayer = 0;

        gameOver = false;
        winnerIs.setVisible(false);

        grid1isDone = false;
        grid9isDone = false;
        grid8isDone = false;
        grid7isDone = false;
        grid6isDone = false;
        grid5isDone = false;
        grid4isDone = false;
        grid3isDone = false;
        grid2isDone = false;
    }

    @FXML
    private void start(MouseEvent event) throws InterruptedException
    {
        initBotFight();
    }

    private void createBots(String botToPlay, String botToPlay2)
    {
        System.out.println("Bot to play.: "+botToPlay);
        System.out.println("Bot to play 2: "+botToPlay2);
        switch (botToPlay)
        {
            case "RandomBot":
                AIBot1 = new RandomBot();
                System.out.println("Creating random bot");
                break;
            case "SmartBot":
                AIBot1 = new SmartBot();
                break;
            case "SmarterBot":
                AIBot1 = new SmarterBot();
                break;
            case "SmartestBot":
                AIBot1 = new SmartestBot();
                break;

        }
        System.out.println("DOING THIS");
        switch (botToPlay2)
        {
            case "RandomBot":
                AIBot2 = new RandomBot();
                break;
            case "SmartBot":
                AIBot2 = new SmartBot();
                break;
            case "SmarterBot":
                AIBot2 = new SmarterBot();
                break;
            case "SmartestBot":
                AIBot2 = new SmartestBot();
                
                break;

        }
    }

    public enum GameMode
    {
        HumanVsHuman,
        HumanVsBot,
        BotVsBot
    }

    @FXML
    private void buttonPressed(ActionEvent event)
    {
        if (gameMode == GameMode.BotVsBot)
        {
            return;
        }
        Button btn = (Button) event.getSource();
        Integer[] coordinates = makeCoordinates(btn);
        Move toDo = new Move(coordinates[0], coordinates[1]);
        if (gameOver == true)
        {
            return;
        }

        if (gManager.updateGame(toDo) == true)
        {
            setWinner();
            mBoard = gManager.getCurrentState().getField().getMacroboard();
            if (!gameOver)
            {
                clearLight();
                boardLight(gManager.getCurrentState().getField().getActiveMicroboard());
            }
            if (gameOver)
            {
                DropShadow h = new DropShadow();
                h.setColor(Color.GREEN);
                macroBoard.setEffect(h);
                playerTurnDis();
            }
            if (currentPlayer == 0)
            {
                InnerShadow bolle = new InnerShadow(25, Color.BLUE);
                btn.setEffect(bolle);
                btn.setStyle("-fx-font-size: 34px;");

                btn.setText("O");
                currentPlayer = 1;
                fixBigMarkings();
                playerTurn();
                if (gameMode == GameMode.HumanVsBot && gameOver == false)
                {
                    if (gManager.updateGame())
                    {
                        setBotMark();

                        fixBigMarkings();
                        clearLight();
                        boardLight(gManager.getCurrentState().getField().getActiveMicroboard());
                        setWinner();
                        currentPlayer = 0;
                        playerTurn();

                        if (!gameOver)
                        {
                            clearLight();
                            boardLight(gManager.getCurrentState().getField().getActiveMicroboard());
                            return;
                        }

                        if (gameOver)
                        {
                            DropShadow h = new DropShadow();
                            h.setColor(Color.GREEN);
                            macroBoard.setEffect(h);
                            playerTurnDis();
                        }

                    }

                }

                return;
            }

            if (currentPlayer == 1)
            {
                InnerShadow kryds = new InnerShadow(20, Color.RED);
                btn.setEffect(kryds);
                btn.setStyle("-fx-font-size: 34px;");

                btn.setText("X");
                currentPlayer = 0;
                fixBigMarkings();
                playerTurn();

                return;
            }

        }

    }

    public void playerTurn()
    {
        if (currentPlayer == 0)
        {
            lblPlayerTurn.setText("Player O turn");
        }
        if (currentPlayer == 1)
        {
            lblPlayerTurn.setText("Player X turn");
        }
    }

    public void playerTurnDis()
    {
        if (gameOver == true)
        {
            lblPlayerTurn.setVisible(false);
        }
    }

    public void setGameManager(int gMode, int playerToStart, int bPlays, String botToPlay, String botToPlay2) throws InterruptedException
    {
        this.gMode = gMode;
        bot1 = botToPlay;
        bot2 = botToPlay2;
        createBots(botToPlay, botToPlay2);

                

        switch (gMode)
        {
            case 1:
                gManager = new GameManager(new GameState(), playerToStart);
                gameMode = GameMode.HumanVsHuman;
                break;
            case 2:
                gManager = new GameManager(new GameState(), AIBot1);
                gameMode = GameMode.HumanVsBot;
                // Sets the starting player to be human
                currentPlayer = 0;
                playerTurn();
                break;
            case 3:

                gManager = new GameManager(new GameState(), AIBot1, AIBot2);
                gameMode = GameMode.BotVsBot;

                this.numberOfBotPlays = bPlays;
                initBotFight();

                lblPlayerTurn.setVisible(false);
                btnClear.setVisible(false);

                break;
        }
    }

    private Integer[] makeCoordinates(Button btn)
    {
        Integer row = GridPane.getRowIndex((Node) btn);
        Integer col = GridPane.getColumnIndex((Node) btn);

        GridPane current = (GridPane) btn.getParent();
        Integer row2 = GridPane.getRowIndex((Node) current);
        Integer col2 = GridPane.getColumnIndex((Node) current);

        // local 3x3
        int r = (row == null) ? 0 : row;
        int c = (col == null) ? 0 : col;
        // macro
        int r1 = (row2 == null) ? 0 : row2;
        int c1 = (col2 == null) ? 0 : col2;

        Integer[] coordinates = new Integer[2];

        coordinates[0] = r + (r1 * 3);
        coordinates[1] = c + (c1 * 3);

        return coordinates;
    }

    @FXML
    private void clearTheBoard(ActionEvent event) throws InterruptedException
    {
        clearLight();
        startLight();
        clearButtons();
        if (gameMode == GameMode.BotVsBot)
        {
            currentPlayer = 0;
        }
        if (gameMode != GameMode.BotVsBot)
        {

            setGameManager(gMode, currentPlayer, 0, bot1, bot2);
        }
        if (gameMode == GameMode.BotVsBot)
        {
            setGameManager(gMode, currentPlayer, numberOfBotPlays, bot1, bot2);
        }
        gameOver = false;
        winnerIs.setVisible(false);
        if (gameMode != GameMode.BotVsBot)
        {
            lblPlayerTurn.setVisible(true);
        }

        grid1isDone = false;
        grid9isDone = false;
        grid8isDone = false;
        grid7isDone = false;
        grid6isDone = false;
        grid5isDone = false;
        grid4isDone = false;
        grid3isDone = false;
        grid2isDone = false;

    }

    @FXML
    void backToMainScr(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uttt/GUI/View/MainView.fxml"));
        Parent root = loader.load();

        stage = (Stage) btnBackToMainScr.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void boardLight(int activeMicroboard)
    {
        /* case 10 er hele gridpane som lyser op og ellers så hver case passer til
         hvert sit grid "nummer" */

        DropShadow grid = new DropShadow();
        grid.setColor(Color.GREEN);
        switch (activeMicroboard)
        {

            case 1:

                gridPane1.setEffect(grid);
                break;
            case 2:

                gridPane2.setEffect(grid);
                break;
            case 3:

                gridPane3.setEffect(grid);
                break;
            case 4:

                gridPane4.setEffect(grid);
                break;
            case 5:

                gridPane5.setEffect(grid);
                break;
            case 6:

                gridPane6.setEffect(grid);
                break;
            case 7:

                gridPane7.setEffect(grid);
                break;
            case 8:

                gridPane8.setEffect(grid);
                break;
            case 9:

                gridPane9.setEffect(grid);
                break;
            case 10:

                setBigLight(grid);
                break;
        }
    }

    private void setWinner()
    {

        String winner = "" + gManager.getWinnerIs();

        if (winner.equals("" + 0))
        {
            winnerIs.setVisible(true);
            winnerIs.setText("Vinderen er spiller: O");
            numberOfOWins++;
            lblOWins.setText("" + numberOfOWins);
            gameOver = true;

        }
        if (winner.equals("" + 1))
        {
            winnerIs.setVisible(true);
            winnerIs.setText("Vinderen er spiller: X");
            numberOfXWins++;
            lblXWins.setText("" + numberOfXWins);
            gameOver = true;

        }
        if (winner.equals("" + 2))
        {
            winnerIs.setVisible(true);
            winnerIs.setText("Uafgjort");
            numberOfDraws++;
            lblDraw.setText("" + numberOfDraws);
            gameOver = true;

        }

    }

    private void clearLight()
    {

        DropShadow grid = new DropShadow();
        grid.setColor(Color.WHITE);
        gridPane1.setEffect(grid);

        gridPane2.setEffect(grid);

        gridPane3.setEffect(grid);

        gridPane4.setEffect(grid);

        gridPane5.setEffect(grid);

        gridPane6.setEffect(grid);

        gridPane7.setEffect(grid);

        gridPane8.setEffect(grid);

        gridPane9.setEffect(grid);

        macroBoard.setEffect(grid);

    }

    private void clearButtons()
    {
        removeAllBigWinButtons();

        for (Node n : macroBoard.getChildren())
        {
            GridPane pane = (GridPane) n;
            for (Node k : pane.getChildren())
            {
                Button btn = (Button) k;
                btn.setText("");
                btn.setEffect(null);
                gameOver = false;
            }

        }
    }

    private void setBigLight(DropShadow grid)
    {

        ArrayList<Integer> availableGrids = new ArrayList<>();
        String playerX = "" + 1;
        String playerO = "" + 0;
        String draw = ".";
        Integer boardNumber = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int k = 0; k < 3; k++)
            {
                boardNumber++;
                String mMarking = mBoard[i][k];
                if (!mMarking.equals(playerX) && !mMarking.equals(playerO) && !mMarking.equals(draw))
                {
                    availableGrids.add(boardNumber);
                }
            }
        }
        for (Integer x : availableGrids)
        {
            boardLight(x);
        }

    }

    private void startLight()
    {

        DropShadow grid = new DropShadow();
        grid.setColor(Color.GREEN);
        macroBoard.setEffect(grid);
    }

    public void fixBigMarkings()
    {

        int boardNumber = 0;
        String playerX = "" + 1;
        String playerO = "" + 0;
        String draw = ".";

        for (int i = 0; i < 3; i++)
        {
            for (int k = 0; k < 3; k++)
            {
                boardNumber++;
                String mMarking = mBoard[i][k];

                if (mMarking.equals(playerX))
                {
                    fixBigGrids(boardNumber, playerX);
                }
                if (mMarking.equals(playerO))
                {
                    fixBigGrids(boardNumber, playerO);
                }
                if (mMarking.equals(draw))
                {
                    fixBigGrids(boardNumber, draw);
                }
            }
        }

    }

    public void fixBigGrids(int grid, String player)
    {
        Button newB = new Button();
        newB.setStyle("-fx-font-size: 34px;");
        newB.setPrefSize(224, 245);

        switch (grid)
        {

            case 1:
                if (!grid1isDone)
                {
                    if (player.equals("" + 1))
                    {
                        makeBigXButton(newB);
                    }
                    if (player.equals("" + 0))
                    {
                        makeBigOButton(newB);
                    }
                    if (player.equals("."))
                    {
                        makeBigDrawButton(newB);
                    }
                    grid1isDone = true;
                    macroBoard.add(newB, 0, 0);

                }
                break;

            case 2:
                if (!grid2isDone)
                {
                    if (player.equals("" + 1))
                    {
                        makeBigXButton(newB);
                    }
                    if (player.equals("" + 0))
                    {
                        makeBigOButton(newB);
                    }
                    if (player.equals("."))
                    {
                        makeBigDrawButton(newB);
                    }
                    grid2isDone = true;
                    macroBoard.add(newB, 1, 0);
                }
                break;
            case 3:
                if (!grid3isDone)
                {
                    if (player.equals("" + 1))
                    {
                        makeBigXButton(newB);
                    }
                    if (player.equals("" + 0))
                    {
                        makeBigOButton(newB);
                    }
                    if (player.equals("."))
                    {
                        makeBigDrawButton(newB);
                    }
                    grid3isDone = true;
                    macroBoard.add(newB, 2, 0);
                }
                break;
            case 4:
                if (!grid4isDone)
                {
                    if (player.equals("" + 1))
                    {
                        makeBigXButton(newB);
                    }
                    if (player.equals("" + 0))
                    {
                        makeBigOButton(newB);
                    }
                    if (player.equals("."))
                    {
                        makeBigDrawButton(newB);
                    }
                    grid4isDone = true;
                    macroBoard.add(newB, 0, 1);
                }
                break;
            case 5:
                if (!grid5isDone)
                {
                    if (player.equals("" + 1))
                    {
                        makeBigXButton(newB);

                    }
                    if (player.equals("" + 0))
                    {
                        makeBigOButton(newB);
                    }
                    if (player.equals("."))
                    {
                        makeBigDrawButton(newB);
                    }
                    grid5isDone = true;
                    macroBoard.add(newB, 1, 1);
                }
                break;

            case 6:
                if (!grid6isDone)
                {
                    if (player.equals("" + 1))
                    {
                        makeBigXButton(newB);

                    }
                    if (player.equals("" + 0))
                    {
                        makeBigOButton(newB);
                    }
                    if (player.equals("."))
                    {
                        makeBigDrawButton(newB);
                    }
                    grid6isDone = true;
                    macroBoard.add(newB, 2, 1);
                }
                break;
            case 7:
                if (!grid7isDone)
                {
                    if (player.equals("" + 1))
                    {
                        makeBigXButton(newB);
                    }
                    if (player.equals("" + 0))
                    {
                        makeBigOButton(newB);
                    }
                    if (player.equals("."))
                    {
                        makeBigDrawButton(newB);
                    }
                    grid7isDone = true;
                    macroBoard.add(newB, 0, 2);
                }
                break;
            case 8:
                if (!grid8isDone)
                {
                    if (player.equals("" + 1))
                    {
                        makeBigXButton(newB);
                    }
                    if (player.equals("" + 0))
                    {
                        makeBigOButton(newB);
                    }
                    if (player.equals("."))
                    {
                        makeBigDrawButton(newB);
                    }
                    grid8isDone = true;
                    macroBoard.add(newB, 1, 2);
                }
                break;
            case 9:
                if (!grid9isDone)
                {
                    if (player.equals("" + 1))
                    {
                        makeBigXButton(newB);
                    }
                    if (player.equals("" + 0))
                    {
                        makeBigOButton(newB);
                    }
                    if (player.equals("."))
                    {
                        makeBigDrawButton(newB);
                    }
                    grid9isDone = true;
                    macroBoard.add(newB, 2, 2);
                }
                break;

        }
    }

    private void makeBigOButton(Button newB)
    {
        newB.setText("O");
        InnerShadow bolle = new InnerShadow(25, Color.BLUE);
        newB.setEffect(bolle);
        newB.setStyle("-fx-font-size: 34px;");
    }

    private void makeBigDrawButton(Button newB)
    {
        newB.setText("DRAW");
        InnerShadow draw = new InnerShadow(25, Color.YELLOW);
        newB.setEffect(draw);
        newB.setStyle("-fx-font-size: 34px;");
    }

    private void makeBigXButton(Button newB)
    {
        newB.setText("X");
        InnerShadow kryds = new InnerShadow(20, Color.RED);
        newB.setEffect(kryds);
        newB.setStyle("-fx-font-size: 34px;");
    }

    private void removeAllBigWinButtons()
    {
        ObservableList<Node> allnodes = macroBoard.getChildren();

        ArrayList<Node> toDelList = new ArrayList<>();

        for (Node x : allnodes)
        {

            if (x instanceof Button)
            {
                toDelList.add(x);
            }

        }

        int sizeOfList = toDelList.size();

        for (int i = sizeOfList - 1; i > -1; i--)
        {

            macroBoard.getChildren().remove(toDelList.get(i));
        }

    }

    private void makeButtonHashMap()
    {
        buttonHashMap.put("0.0", btn00);
        buttonHashMap.put("0.1", btn01);
        buttonHashMap.put("0.2", btn02);
        buttonHashMap.put("0.3", btn03);
        buttonHashMap.put("0.4", btn04);
        buttonHashMap.put("0.5", btn05);
        buttonHashMap.put("0.6", btn06);
        buttonHashMap.put("0.7", btn07);
        buttonHashMap.put("0.8", btn08);
        buttonHashMap.put("1.0", btn10);
        buttonHashMap.put("1.1", btn11);
        buttonHashMap.put("1.2", btn12);
        buttonHashMap.put("1.3", btn13);
        buttonHashMap.put("1.4", btn14);
        buttonHashMap.put("1.5", btn15);
        buttonHashMap.put("1.6", btn16);
        buttonHashMap.put("1.7", btn17);
        buttonHashMap.put("1.8", btn18);
        buttonHashMap.put("2.0", btn20);
        buttonHashMap.put("2.1", btn21);
        buttonHashMap.put("2.2", btn22);
        buttonHashMap.put("2.3", btn23);
        buttonHashMap.put("2.4", btn24);
        buttonHashMap.put("2.5", btn25);
        buttonHashMap.put("2.6", btn26);
        buttonHashMap.put("2.7", btn27);
        buttonHashMap.put("2.8", btn28);
        buttonHashMap.put("3.0", btn30);
        buttonHashMap.put("3.1", btn31);
        buttonHashMap.put("3.2", btn32);
        buttonHashMap.put("3.3", btn33);
        buttonHashMap.put("3.4", btn34);
        buttonHashMap.put("3.5", btn35);
        buttonHashMap.put("3.6", btn36);
        buttonHashMap.put("3.7", btn37);
        buttonHashMap.put("3.8", btn38);
        buttonHashMap.put("3.8", btn38);
        buttonHashMap.put("4.0", btn40);
        buttonHashMap.put("4.1", btn41);
        buttonHashMap.put("4.2", btn42);
        buttonHashMap.put("4.3", btn43);
        buttonHashMap.put("4.4", btn44);
        buttonHashMap.put("4.5", btn45);
        buttonHashMap.put("4.6", btn46);
        buttonHashMap.put("4.7", btn47);
        buttonHashMap.put("4.8", btn48);
        buttonHashMap.put("5.0", btn50);
        buttonHashMap.put("5.1", btn51);
        buttonHashMap.put("5.2", btn52);
        buttonHashMap.put("5.3", btn53);
        buttonHashMap.put("5.4", btn54);
        buttonHashMap.put("5.5", btn55);
        buttonHashMap.put("5.6", btn56);
        buttonHashMap.put("5.7", btn57);
        buttonHashMap.put("5.8", btn58);
        buttonHashMap.put("6.0", btn60);
        buttonHashMap.put("6.1", btn61);
        buttonHashMap.put("6.2", btn62);
        buttonHashMap.put("6.3", btn63);
        buttonHashMap.put("6.4", btn64);
        buttonHashMap.put("6.5", btn65);
        buttonHashMap.put("6.6", btn66);
        buttonHashMap.put("6.7", btn67);
        buttonHashMap.put("6.8", btn68);
        buttonHashMap.put("7.0", btn70);
        buttonHashMap.put("7.1", btn71);
        buttonHashMap.put("7.2", btn72);
        buttonHashMap.put("7.3", btn73);
        buttonHashMap.put("7.4", btn74);
        buttonHashMap.put("7.5", btn75);
        buttonHashMap.put("7.6", btn76);
        buttonHashMap.put("7.7", btn77);
        buttonHashMap.put("7.8", btn78);
        buttonHashMap.put("8.0", btn80);
        buttonHashMap.put("8.1", btn81);
        buttonHashMap.put("8.2", btn82);
        buttonHashMap.put("8.3", btn83);
        buttonHashMap.put("8.4", btn84);
        buttonHashMap.put("8.5", btn85);
        buttonHashMap.put("8.6", btn86);
        buttonHashMap.put("8.7", btn87);
        buttonHashMap.put("8.8", btn88);
    }
}
