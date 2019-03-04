/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttt.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    @FXML
    private ChoiceBox<String> botVsBotChoice;
    @FXML
    private ChoiceBox<String> humanVsBotChoice;
    @FXML
    private ChoiceBox<String> botVsBotChoice2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> botNames = FXCollections.observableArrayList();
        botNames.add("Please choose a bot");
        botNames.add("RandomBot");
        botNames.add("SmartBot");
        botNames.add("SmarterBot");
        botNames.add("SmartestBot");
        botVsBotChoice.setItems(botNames);
        humanVsBotChoice.setItems(botNames);
        botVsBotChoice2.setItems(botNames);

        botVsBotChoice.getSelectionModel().selectFirst();
        humanVsBotChoice.getSelectionModel().selectFirst();
        botVsBotChoice2.getSelectionModel().selectFirst();
    }

    @FXML
    private void gameAgainstHuman(ActionEvent event) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uttt/GUI/View/Gameboard.fxml"));
        Parent root = loader.load();
        GameboardController con = loader.getController();

        con.setGameManager(1, 0, 0, "", "");

        stage = (Stage) anchorPane.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gameAgainstBot(ActionEvent event) throws IOException, InterruptedException {
        String botChoice = humanVsBotChoice.getSelectionModel().getSelectedItem();
        if (botChoice.equals("Please choose a bot")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Choose a bot");
            alert.show();
            return;
        }

//        if(botChoice.equals("RandomBot" || botChoice))
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uttt/GUI/View/Gameboard.fxml"));
        Parent root = loader.load();
        GameboardController con = loader.getController();

        con.setGameManager(2, 0, 0, botChoice, "");

        stage = (Stage) anchorPane.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void botVsBot(ActionEvent event) throws IOException, InterruptedException {
        String botChoice = botVsBotChoice.getSelectionModel().getSelectedItem();
        String botChoice2 = botVsBotChoice2.getSelectionModel().getSelectedItem();
        System.out.println("" + botChoice);
        if (botChoice.equals("Please choose a bot") || botChoice2.equals("Please choose a bot")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Choose a bot");
            alert.show();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uttt/GUI/View/Gameboard.fxml"));
        Parent root = loader.load();
        GameboardController con = loader.getController();
        String numberOfFights = fights.getText();
        try {
            int number = Integer.parseInt(numberOfFights);
            System.out.println("botchoice1: " + botChoice);
            System.out.println("botchoice2: " + botChoice2);

            con.setGameManager(3, 0, number, botChoice, botChoice2);
            stage = (Stage) anchorPane.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (NumberFormatException e) {
            fights.setText("Type a number here");
        }

    }

}
