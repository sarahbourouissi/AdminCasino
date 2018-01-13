/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admincasino;

import GUI.HomeController;
import GUI.LoginController;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyphLoader;
import insidefx.undecorator.UndecoratorScene;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class admincasino1 extends Application {
    
    @Override
    public void start(Stage stage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
            stage.setFullScreen(true);
            stage.setMinWidth(700);
            stage.setMinHeight(800);
             // The Undecorator as a Scene
 // UndecoratorScene undecoratorScene = new UndecoratorScene(stage, root);
            //   Image icon = new Image(admincasino.class.getResourceAsStream("Images/company2.png"));
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            //   stage.getIcons().add(icon);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(admincasino1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
