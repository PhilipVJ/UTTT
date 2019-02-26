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
    private Field fieldModel;

    private GameState gState;

    private int currentPlayer = 0;
    @FXML
    private Button btnClear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
//        gStage = new GameState();
        boardLigth(5);
    }

    @FXML
    private void buttonPressed(ActionEvent event)
    {
        Button btn = (Button) event.getSource();
        Integer[] coordinates = makeCoordinates(btn);
        Move toDo = new Move(coordinates[0], coordinates[1]);


        if (gManager.updateGame(toDo) == true)
        {
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
            
    }
          
    public void boardLigth(int grid)
    {
        /* case 10 er hele gridpane som lyser op og ellers så hver case passer til 
         hvert sit grid "nummer" */
        switch (grid)
        {
            case 1:
                DropShadow grid1 = new DropShadow();
                grid1.setColor(Color.BLUE);
                gridPane1.setEffect(grid1);
                break;
            case 2:
                DropShadow grid2 = new DropShadow();
                grid2.setColor(Color.BLUE);
                gridPane2.setEffect(grid2);
                break;
            case 3:
                DropShadow grid3 = new DropShadow();
                grid3.setColor(Color.BLUE);
                gridPane3.setEffect(grid3);
                break;
            case 4:
                DropShadow grid4 = new DropShadow();
                grid4.setColor(Color.BLUE);
                gridPane4.setEffect(grid4);
                break;
            case 5:
                DropShadow grid5 = new DropShadow();
                grid5.setColor(Color.BLUE);
                gridPane5.setEffect(grid5);
                break;
            case 6:
                DropShadow grid6 = new DropShadow();
                grid6.setColor(Color.BLUE);
                gridPane6.setEffect(grid6);
                break;
            case 7:
                DropShadow grid7 = new DropShadow();
                grid7.setColor(Color.BLUE);
                gridPane7.setEffect(grid7);
                break;
            case 8:
                DropShadow grid8 = new DropShadow();
                grid8.setColor(Color.BLUE);
                gridPane8.setEffect(grid8);
                break;
            case 9:
                DropShadow grid9 = new DropShadow();
                grid9.setColor(Color.BLUE);
                gridPane9.setEffect(grid9);
                break; 
            case 10:
                DropShadow ds = new DropShadow();
                ds.setColor(Color.BLUE);
                macroBoard.setEffect(ds);
                break;    
        } 
    }  
    
}
