/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttt.GUI.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import static uttt.field.IField.AVAILABLE_FIELD;
import uttt.game.GameManager;
import uttt.game.GameState;
import uttt.move.Move;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class GameboardController implements Initializable {

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
    private Button btnClear;

    private String[][] mBoard;

    @FXML
    private Label winnerIs;

    private GameManager gManager;
    private HighScore hScore;

    private int currentPlayer = 0;
    private int gMode;

    private boolean gameOver = false;

    @FXML
    private Label lblXWins;
    @FXML
    private Label lblOWins;
    @FXML
    private Label lblDraw;
    private boolean grid1isDone = false;
    private boolean grid9isDone = false;
    private boolean grid8isDone = false;
    private boolean grid7isDone = false;
    private boolean grid6isDone = false;
    private boolean grid5isDone = false;
    private boolean grid4isDone = false;
    private boolean grid3isDone = false;
    private boolean grid2isDone = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startLight();
        hScore = new HighScore();
        getScores();
    }

    @FXML
    private void buttonPressed(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Integer[] coordinates = makeCoordinates(btn);
        Move toDo = new Move(coordinates[0], coordinates[1]);
        if (gameOver == true) {
            int winner = gManager.getWinnerIs();
            hScore.incrementScore(winner);
            getScores();
            return;
        }

        if (gManager.updateGame(toDo) == true) {
            setWinner();
            mBoard = gManager.getCurrentState().getField().getMacroboard();
            clearLight();
            boardLight(gManager.getCurrentState().getField().getActiveMicroboard());
            if (currentPlayer == 0) {
                InnerShadow bolle = new InnerShadow(25, Color.BLUE);
                btn.setEffect(bolle);
                btn.setStyle("-fx-font-size: 34px;");

                btn.setText("O");
                currentPlayer = 1;
                fixBigMarkings();
                return;
            }

            if (currentPlayer == 1) {
                InnerShadow kryds = new InnerShadow(20, Color.RED);
                btn.setEffect(kryds);
                btn.setStyle("-fx-font-size: 34px;");

                btn.setText("X");

                currentPlayer = 0;
                fixBigMarkings();
                return;
            }

        }

    }

    public void setGameManager(int gMode) {
        this.gMode = gMode;
        // Mangler kode i 2 og 3
        switch (gMode) {
            case 1:
                gManager = new GameManager(new GameState());
                System.out.println("New gManager");
                break;
//            case 2:
//                gManager = new GameManager(new GameState(), bot);
//                break;
//            case 3:
//                gManager = new GameManager(new GameState(), bot, bot2);
//                break;
        }
    }

    private Integer[] makeCoordinates(Button btn) {
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
    private void clearTheBoard(ActionEvent event) {
        clearLight();
        startLight();
        clearButtons();
        setGameManager(gMode);
        gameOver = false;
        winnerIs.setText("");

        grid1isDone = false;
        grid9isDone = false;
        grid8isDone = false;
        grid7isDone = false;
        grid6isDone = false;
        grid5isDone = false;
        grid4isDone = false;
        grid3isDone = false;
        grid2isDone = false;
        currentPlayer=0;

    }

    public void boardLight(int activeMicroboard) {
        /* case 10 er hele gridpane som lyser op og ellers så hver case passer til
         hvert sit grid "nummer" */

        DropShadow grid = new DropShadow();
        grid.setColor(Color.BLACK);
        switch (activeMicroboard) {

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

    private void setWinner() {

        String winner = "" + gManager.getWinnerIs();

        if (winner.equals("" + 0) || winner.equals("" + 1)) {
            if (winner.equals("" + 0)) {
                winnerIs.setText("Vinderen er spiller: O");
                lblOWins.setText("Player O wins:" + 1);
                gameOver = true;

                if (winner.equals("" + 0) || winner.equals("" + 1)) {
                    if (winner.equals("" + 0)) {
                        winnerIs.setText("Vinderen er spiller: O");

                        gameOver = true;

                    }
                    if (winner.equals("" + 1)) {
                        winnerIs.setText("Vinderen er spiller: X");

                        lblXWins.setText("Player X wins:" + 1);
                        gameOver = true;

                    }
                    if (winner.equals("" + 2)) {
                        winnerIs.setText("Uafgjort");

                        lblDraw.setText("Draw:" + 1);
                        gameOver = true;

                    }

                }
            }
        }
    }

    private void getScores(){

        lblOWins.setText(String.valueOf(hScore.getScoreOne()));
        lblXWins.setText(String.valueOf(hScore.getScoreTwo()));
        lblDraw.setText(String.valueOf(hScore.getScoreDraw()));

    }

    private void clearLight() {

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

    private void clearButtons() {
        removeAllBigWinButtons();
        System.out.println("STOERRELSE " + macroBoard.getChildren().size());

        for (Node n : macroBoard.getChildren()) {
            GridPane pane = (GridPane) n;
            for (Node k : pane.getChildren()) {
                Button btn = (Button) k;
                btn.setText("");
                btn.setEffect(null);
                gameOver = false;
            }

        }
    }

    private void setBigLight(DropShadow grid) {

        ArrayList<Integer> availableGrids = new ArrayList<>();
        String playerX = "" + 1;
        String playerO = "" + 0;
        String draw = ".";
        Integer boardNumber = 0;
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                boardNumber++;
                String mMarking = mBoard[i][k];
                if (!mMarking.equals(playerX) && !mMarking.equals(playerO) && !mMarking.equals(draw)) {
                    availableGrids.add(boardNumber);
                }
            }
        }
        for (Integer x : availableGrids) {
            boardLight(x);
        }

    }

    private void startLight() {

        DropShadow grid = new DropShadow();
        grid.setColor(Color.BLACK);
        macroBoard.setEffect(grid);
    }

    public void fixBigMarkings() {

        int boardNumber = 0;
        String playerX = "" + 1;
        String playerO = "" + 0;
        String draw = ".";

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                boardNumber++;
                String mMarking = mBoard[i][k];

                if (mMarking.equals(playerX)) {
                    fixBigGrids(boardNumber, playerX);
                }
                if (mMarking.equals(playerO)) {
                    fixBigGrids(boardNumber, playerO);
                }
                if (mMarking.equals(draw)) {
                    fixBigGrids(boardNumber, draw);
                }
            }
        }

    }

    public void fixBigGrids(int grid, String player) {
        Button newB = new Button();
        newB.setStyle("-fx-font-size: 34px;");
        newB.setPrefSize(224, 245);

        switch (grid) {

            case 1:
                if (!grid1isDone) {
                    if (player.equals("" + 1)) {
                        makeBigXButton(newB);
                    }
                    if (player.equals("" + 0)) {
                        makeBigOButton(newB);
                    }
                    if (player.equals(".")) {
                        makeBigDrawButton(newB);
                    }
                    grid1isDone = true;
                    macroBoard.add(newB, 0, 0);
                    System.out.println("GRID 1");
                }
                break;

            case 2:
                if (!grid2isDone) {
                    if (player.equals("" + 1)) {
                        makeBigXButton(newB);
                    }
                    if (player.equals("" + 0)) {
                        makeBigOButton(newB);
                    }
                    if (player.equals(".")) {
                        makeBigDrawButton(newB);
                    }
                    grid2isDone = true;
                    macroBoard.add(newB, 1, 0);
                    System.out.println("GRID 2");
                }
                break;
            case 3:
                if (!grid3isDone) {
                    if (player.equals("" + 1)) {
                        makeBigXButton(newB);
                    }
                    if (player.equals("" + 0)) {
                        makeBigOButton(newB);
                    }
                    if (player.equals(".")) {
                        makeBigDrawButton(newB);
                    }
                    grid3isDone = true;
                    macroBoard.add(newB, 2, 0);
                }
                break;
            case 4:
                if (!grid4isDone) {
                    if (player.equals("" + 1)) {
                        makeBigXButton(newB);
                    }
                    if (player.equals("" + 0)) {
                        makeBigOButton(newB);
                    }
                    if (player.equals(".")) {
                        makeBigDrawButton(newB);
                    }
                    grid4isDone = true;
                    macroBoard.add(newB, 0, 1);
                }
                break;
            case 5:
                if (!grid5isDone) {
                    if (player.equals("" + 1)) {
                        makeBigXButton(newB);

                    }
                    if (player.equals("" + 0)) {
                        makeBigOButton(newB);
                    }
                    if (player.equals(".")) {
                        makeBigDrawButton(newB);
                    }
                    grid5isDone = true;
                    macroBoard.add(newB, 1, 1);
                }
                break;

            case 6:
                if (!grid6isDone) {
                    if (player.equals("" + 1)) {
                        makeBigXButton(newB);

                    }
                    if (player.equals("" + 0)) {
                        makeBigOButton(newB);
                    }
                    if (player.equals(".")) {
                        makeBigDrawButton(newB);
                    }
                    grid6isDone = true;
                    macroBoard.add(newB, 2, 1);
                }
                break;
            case 7:
                if (!grid7isDone) {
                    if (player.equals("" + 1)) {
                        makeBigXButton(newB);
                    }
                    if (player.equals("" + 0)) {
                        makeBigOButton(newB);
                    }
                    if (player.equals(".")) {
                        makeBigDrawButton(newB);
                    }
                    grid7isDone = true;
                    macroBoard.add(newB, 0, 2);
                }
                break;
            case 8:
                if (!grid8isDone) {
                    if (player.equals("" + 1)) {
                        makeBigXButton(newB);
                    }
                    if (player.equals("" + 0)) {
                        makeBigOButton(newB);
                    }
                    if (player.equals(".")) {
                        makeBigDrawButton(newB);
                    }
                    grid8isDone = true;
                    macroBoard.add(newB, 1, 2);
                }
                break;
            case 9:
                if (!grid9isDone) {
                    if (player.equals("" + 1)) {
                        makeBigXButton(newB);
                    }
                    if (player.equals("" + 0)) {
                        makeBigOButton(newB);
                    }
                    if (player.equals(".")) {
                        makeBigDrawButton(newB);
                    }
                    grid9isDone = true;
                    macroBoard.add(newB, 2, 2);
                }
                break;

        }
    }

    private void makeBigOButton(Button newB) {
        newB.setText("O");
        InnerShadow bolle = new InnerShadow(25, Color.BLUE);
        newB.setEffect(bolle);
        newB.setStyle("-fx-font-size: 34px;");
    }

    private void makeBigDrawButton(Button newB) {
        newB.setText("DRAW");
        InnerShadow draw = new InnerShadow(25, Color.YELLOW);
        newB.setEffect(draw);
        newB.setStyle("-fx-font-size: 34px;");
    }

    private void makeBigXButton(Button newB) {
        newB.setText("X");
        InnerShadow kryds = new InnerShadow(20, Color.RED);
        newB.setEffect(kryds);
        newB.setStyle("-fx-font-size: 34px;");
    }

    private void removeAllBigWinButtons() {
        ObservableList<Node> allnodes = macroBoard.getChildren();

        ArrayList<Node> toDelList = new ArrayList<>();

        for (Node x : allnodes) {

            if (x instanceof Button) {
                toDelList.add(x);
            }

        }

        int sizeOfList = toDelList.size();
        System.out.println("HERE: " + sizeOfList);

        for (int i = sizeOfList - 1; i > -1; i--) {

            macroBoard.getChildren().remove(toDelList.get(i));
        }

    }
}
