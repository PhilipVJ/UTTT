/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttt.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
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
    private Button btnClear;
    @FXML
    private Label winnerIs;
    
    private GameManager gManager;

    private int currentPlayer = 0;
    private int gMode;
     
    private boolean gameOver=false;
    @FXML
    private Button btn11;
    @FXML
    private Button btn12;
    @FXML
    private Button btn13;
    @FXML
    private Button btn14;
    @FXML
    private Button btn17;
    @FXML
    private Button btn15;
    @FXML
    private Button btn18;
    @FXML
    private Button btn16;
    @FXML
    private Button btn19;
    @FXML
    private Button btn21;
    @FXML
    private Button btn22;
    @FXML
    private Button btn23;
    @FXML
    private Button btn24;
    @FXML
    private Button btn25;
    @FXML
    private Button btn26;
    @FXML
    private Button btn27;
    @FXML
    private Button btn28;
    @FXML
    private Button btn29;
    @FXML
    private Button btn32;
    @FXML
    private Button btn31;
    @FXML
    private Button btn33;
    @FXML
    private Button btn34;
    @FXML
    private Button btn35;
    @FXML
    private Button btn36;
    @FXML
    private Button btn37;
    @FXML
    private Button btn38;
    @FXML
    private Button btn39;
    @FXML
    private Button btn41;
    @FXML
    private Button btn42;
    @FXML
    private Button btn44;
    @FXML
    private Button btn45;
    @FXML
    private Button btn47;
    @FXML
    private Button btn48;
    @FXML
    private Button btn49;
    @FXML
    private Button btn46;
    @FXML
    private Button btn43;
    @FXML
    private Button btn52;
    @FXML
    private Button btn51;
    @FXML
    private Button btn54;
    @FXML
    private Button btn55;
    @FXML
    private Button btn57;
    @FXML
    private Button btn58;
    @FXML
    private Button btn59;
    @FXML
    private Button btn56;
    @FXML
    private Button btn53;
    @FXML
    private Button btn61;
    @FXML
    private Button btn62;
    @FXML
    private Button btn64;
    @FXML
    private Button btn65;
    @FXML
    private Button btn63;
    @FXML
    private Button btn66;
    @FXML
    private Button btn67;
    @FXML
    private Button btn68;
    @FXML
    private Button btn69;
    @FXML
    private Button btn71;
    @FXML
    private Button btn73;
    @FXML
    private Button btn72;
    @FXML
    private Button btn74;
    @FXML
    private Button btn75;
    @FXML
    private Button btn76;
    @FXML
    private Button btn77;
    @FXML
    private Button btn78;
    @FXML
    private Button btn79;
    @FXML
    private Button btn81;
    @FXML
    private Button btn82;
    @FXML
    private Button btn84;
    @FXML
    private Button btn85;
    @FXML
    private Button btn83;
    @FXML
    private Button btn86;
    @FXML
    private Button btn87;
    @FXML
    private Button btn88;
    @FXML
    private Button btn89;
    @FXML
    private Button btn91;
    @FXML
    private Button btn92;
    @FXML
    private Button btn93;
    @FXML
    private Button btn94;
    @FXML
    private Button btn95;
    @FXML
    private Button btn96;
    @FXML
    private Button btn97;
    @FXML
    private Button btn98;
    @FXML
    private Button btn99;
    @FXML
    private Label lblOWins;
    @FXML
    private Label lblDraw;
    @FXML
    private Label lblXWins;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        boardLight(10);
    }

    @FXML
    private void buttonPressed(ActionEvent event)
    {
        Button btn = (Button) event.getSource();
        Integer[] coordinates = makeCoordinates(btn);
        Move toDo = new Move(coordinates[0], coordinates[1]);
        if(gameOver==true)
        {
            return;
        }

        if (gManager.updateGame(toDo) == true)
        {
            setWinner();
            clearLight();
            boardLight(gManager.getCurrentState().getField().getActiveMicroboard());
            if (currentPlayer == 0)
            {
                InnerShadow bolle = new InnerShadow(25, Color.BLUE);
                btn.setEffect(bolle);
                btn.setStyle("-fx-font-size: 34px;");
                
                btn.setText("O");
                currentPlayer = 1;
                return;
            }

            if (currentPlayer == 1)
            {
                InnerShadow kryds = new InnerShadow(20, Color.RED);
                btn.setEffect(kryds);
                btn.setStyle("-fx-font-size: 34px;");
                
                btn.setText("X");
                
                currentPlayer = 0;
                return;
            }

        }

    }

    public void setGameManager(int gMode)
    {
        this.gMode = gMode;
        // Mangler kode i 2 og 3
        switch (gMode)
        {
            case 1:
                gManager = new GameManager(new GameState());
                break;
//            case 2:
//                gManager = new GameManager(new GameState(), bot);
//                break;
//            case 3:
//                gManager = new GameManager(new GameState(), bot, bot2);
//                break;
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
    private void clearTheBoard(ActionEvent event)
    {
        clearLight();
        boardLight(10);
        clearButtons();
        setGameManager(gMode);
        winnerIs.setText("");
    }

    public void boardLight(int activeMicroboard)
    {
        /* case 10 er hele gridpane som lyser op og ellers så hver case passer til
         hvert sit grid "nummer" */
       
        DropShadow grid = new DropShadow();
        grid.setColor(Color.BLACK);
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

                macroBoard.setEffect(grid);
                break;
        }
    }

    private void setWinner()
    {

        String winner = "" + gManager.getWinnerIs();

        if (winner.equals("" + 0) || winner.equals("" + 1))
        {
            if (winner.equals("" + 0))
            {
                winnerIs.setText("Vinderen er spiller: O");
                lblOWins.setText("Player O wins:" +1);
                gameOver=true;
            }
            if (winner.equals("" + 1))
            {
                winnerIs.setText("Vinderen er spiller: X");
                lblXWins.setText("Player X wins:" +1);
                gameOver=true;
            }

        }
        if (winner.equals("" + 2))
        {
            winnerIs.setText("Uafgjort");
            lblDraw.setText("Draw:" +1);
            gameOver=true;
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
        for (Node n : macroBoard.getChildren())
        {
            GridPane pane = (GridPane) n;
            for (Node k : pane.getChildren())
            {
                Button btn = (Button) k;
                btn.setText("");
                btn.setEffect(null);
            }

        }
    }
    


}
