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
import javafx.scene.layout.GridPane;
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

    private GameState gState;

    private int currentPlayer = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
//        gStage = new GameState();
    }

    @FXML
    private void buttonPressed(ActionEvent event)
    {
        Button btn = (Button) event.getSource();
        Integer[] coordinates = makeCoordinates(btn);

        if (gManager.updateGame(new Move(coordinates[0], coordinates[1])) == true)
        {
            if (currentPlayer == 0)
            {

                btn.setText("0");
                currentPlayer = 1;
            }

            if (currentPlayer == 1)
            {

                btn.setText("X");
                currentPlayer = 0;
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
            case 2:
            case 3:
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
       
       coordinates[0]=r+(r1*3);
       coordinates[1]=c+(c1*3);
       
       
        return coordinates;
    }

}
