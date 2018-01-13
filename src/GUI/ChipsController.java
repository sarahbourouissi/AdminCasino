/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.persistence.BackendlessDataQuery;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ChipsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField nbrchips;

	
    @FXML
    private JFXButton addBtn;
    
    @FXML
    ImageView image ;
      @FXML
    public void Addchips(ActionEvent ae) {
    BackendlessUser user = Backendless.Persistence.of( BackendlessUser.class ).findById( username.getText() );
   
          System.out.println(user.getEmail());
          
          
    user.setProperty( "balance", Integer.parseInt(nbrchips.getText()) );
 Backendless.Persistence.of(BackendlessUser.class).save( user );

// ***********************************************************
// Synchronous API:
// ***********************************************************



    
    
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        // TODO
    }    
    
}
