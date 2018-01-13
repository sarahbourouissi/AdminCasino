/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXToolbar;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HomeController implements Initializable {


    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane dynamicPane;
    @FXML
    AnchorPane repPane;
    @FXML
    Label label;
    AnchorPane players, satistics, mainApp,games,round ,chips;
    VBox sidePane;
    
    @FXML
    JFXToolbar toolbar;
    @FXML
    ImageView image , im ;
       private static final int            LIME = DotMatrix.convertToInt(Color.LIME);
    private static final int            RED  = DotMatrix.convertToInt(Color.RED);
    private              int            x;
    private              DotMatrix      matrix;
    private              String         text;
    private              int            textLength;
    private              int            textLengthInPixel;
    private              int            offset;
    private              long           lastTimerCall;
    private              AnimationTimer timer;


    @FXML
    public void showDrawer(MouseEvent me) {
        if (!drawer.isShown()) {
            drawer.open();
        }

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
         mainApp = FXMLLoader.load(HomeController.class.getResource("/GUI/main.fxml"));
            sidePane = FXMLLoader.load(getClass().getResource("/GUI/adminDrawer.fxml"));
           players = FXMLLoader.load(getClass().getResource("/GUI/players.fxml"));
          satistics = FXMLLoader.load(getClass().getResource("/GUI/Round.fxml"));
           games = FXMLLoader.load(getClass().getResource("/GUI/games.fxml"));
           round = FXMLLoader.load(getClass().getResource("/GUI/statistics.fxml"));
           chips = FXMLLoader.load(getClass().getResource("/GUI/chips.fxml"));
            drawer.setSidePane(sidePane);
            drawer.open();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         repPane.getChildren().add(mainApp);
            for (Node node : sidePane.getChildren()) {
                if (node.getAccessibleText() != null) {

                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            System.out.println(node.getAccessibleText());

                            switch (node.getAccessibleText()) {
                                case ("palyers"):
                                    label.setText("PLAYERS");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add((Node) players);
                                    break;
                                case ("statistic"):
                                    label.setText("ACCOUNTS");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add(satistics);
                                    break;
                                       case ("CHIPS"):
                                    label.setText("CHIPS");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add(chips);
                                    break;
                                    
                                    
                                    case ("rounds"):
                                    label.setText("ROUNDS");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add(round);
                                    break;
                                    
                                    case ("games"):
                                    label.setText("Games");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add(games);
                                    break;
                                    
                                case ("mainApp"):
                                     label.setText("Home");
                                    repPane.getChildren().clear();
                                    repPane.getChildren().add(mainApp);
                                    break;
                                case ("exit"):
                                    System.exit(0);
                                    break;

                            }
                        }
                    });
                }
            }
    
    }    
    
}
