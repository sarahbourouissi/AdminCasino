/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DialogController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private StackPane root;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         JFXDialogLayout content = new JFXDialogLayout();
         JFXDialog dialog = new JFXDialog(root,new Label("hello"), JFXDialog.DialogTransition.CENTER);
  
   dialog.show();
    }    
    
}
