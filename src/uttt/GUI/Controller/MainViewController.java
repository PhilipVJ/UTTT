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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bruger
 */
public class MainViewController implements Initializable {

    private Stage stage;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnHvH;
    @FXML
    private Button btnHvB;
    @FXML
    private Button btnBvB;
    @FXML
    private TextField fights;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void gameAgainstHuman(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uttt/GUI/View/Gameboard.fxml"));
        Parent root = loader.load();
        GameboardController con = loader.getController();

        con.setGameManager(1, 0, 0);

        stage = (Stage) anchorPane.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gameAgainstBot(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uttt/GUI/View/Gameboard.fxml"));
        Parent root = loader.load();
        GameboardController con = loader.getController();

        con.setGameManager(2, 0, 0);

        stage = (Stage) anchorPane.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void botVsBot(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uttt/GUI/View/Gameboard.fxml"));
        Parent root = loader.load();
        GameboardController con = loader.getController();
        String numberOfFights = fights.getText();
        try {
            int number = Integer.parseInt(numberOfFights);

            con.setGameManager(3, 0, number);
            stage = (Stage) anchorPane.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (NumberFormatException e) {
            fights.setText("Type a number here");
        }

    }

}
