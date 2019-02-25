/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttt.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bruger
 */
public class MainViewController implements Initializable
{
    private Stage stage;
    
    
    @FXML
    private Button btnHvH;
    @FXML
    private Button btnHvB;
    @FXML
    private Button btnBvB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void gameAgainstHuman(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/uttt/GUI/View/Gameboard.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gameAgainstBot(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/uttt/GUI/View/Gameboard.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void botVsBot(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/uttt/GUI/View/Gameboard.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();

    }
    
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
    
}
