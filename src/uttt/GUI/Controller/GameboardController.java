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
        Integer row = macroBoard.getRowIndex(macroBoard);
        Integer col = macroBoard.getColumnIndex(macroBoard);
        System.out.println(""+row);
        System.out.println(""+col);

        if (gManager.updateGame(new Move(row,col)) == true)
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

}
