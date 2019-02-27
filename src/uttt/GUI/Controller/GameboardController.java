/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttt.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import uttt.field.Field;
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

    private GameManager gManager;

    private int currentPlayer = 0;
    @FXML
    private Button btnClear;

    @FXML
    private Label winnerIs;
    private int gMode;

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

        if (gManager.updateGame(toDo) == true)
        {
            setWinner();
            clearLight();
            boardLight(gManager.getCurrentState().getField().getActiveMicroboard());
            if (currentPlayer == 0)
            {

                btn.setText("0");
                currentPlayer = 1;
                return;
            }

            if (currentPlayer == 1)
            {

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
    }

    public void boardLight(int activeMicroboard)
    {
        /* case 10 er hele gridpane som lyser op og ellers så hver case passer til
         hvert sit grid "nummer" */
        System.out.println("UDPRINT AF ACTIVEMB " + activeMicroboard);
        DropShadow grid = new DropShadow();
        grid.setColor(Color.BLUE);
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
            System.out.println("Happsn");
            if (winner.equals("" + 0))
            {
                winnerIs.setText("Vinderen er spiller: O");
            }
            if (winner.equals("" + 1))
            {
                winnerIs.setText("Vinderen er spiller: X");
            }

        }
        if (winner.equals("" + 2))
        {
            winnerIs.setText("Uafgjort");
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

            }

        }
    }

}
