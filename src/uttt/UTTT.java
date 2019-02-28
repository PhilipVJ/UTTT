/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uttt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uttt.GUI.Controller.MainViewController;

/**
 *
 * @author Philip
 */
public class UTTT extends Application
{
    
    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/uttt/GUI/View/MainView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
