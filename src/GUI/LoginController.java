/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Game;
import Entity.Round;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessException;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXToolbar;

import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import static java.lang.Math.round;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javax.annotation.PostConstruct;
import jdk.nashorn.internal.ir.RuntimeNode.Request;
import weborb.util.log.Log;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginController implements Initializable {
 Stage stage;

    /**
     * Initializes the controller class.
     */
 Region content;
 @FXMLViewFlowContext
	private ViewFlowContext context;
 private FlowHandler flowHandler;
@FXML private StackPane root;
    @FXML
    private JFXTextField authField;

    @FXML
    private JFXPasswordField pwdField;
@FXML
ImageView image ;
	
    @FXML
    private JFXButton loginBtn;
    @FXML
    private AnchorPane anchorPane;
@FXMLViewFlowContext

	private ViewFlowContext context1;
    //For test purposes 
    @FXML
    public void loginCheck(ActionEvent ae) {
       
        try {
  BackendlessUser user =            Backendless.UserService.login(authField.getText(),pwdField.getText());
             
             if ( Integer.parseInt(user.getProperty("admin").toString())== 1 )  {
             
                Parent root = FXMLLoader.load(LoginController.class.getResource("/GUI/home.fxml"));
                stage = new Stage();
                JFXToolbar jfxToolbar = new JFXToolbar();
			jfxToolbar.setLeftItems(new Label("Left"));
			jfxToolbar.setRightItems(new Label("Right"));
			
             Image icon = new Image(LoginController.class.getResourceAsStream("/image/smartcasino.jpg"));
               stage.getIcons().add(icon);
               stage.setTitle("Smart Casino");
                Scene scene = new Scene(root);
               
              stage.setScene(scene);
              
              loginBtn.getScene().getWindow().hide();
           //   stage.initModality(Modality.);
        //  stage.initStyle(StageStyle.UNDECORATED);
           stage.show();
                authField.setText(null);
                pwdField.setText(null);
             }
             else {
         createPopup ("you have not the permission to login as an administrator");
             }
        } catch (BackendlessException e) {
            try {
                createPopup ("wrong password ! Try again");
            } catch (FlowException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(e.getMessage());
        } catch (IOException ex) {
       
         Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (FlowException ex) {
         Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
     }
            
//
//                System.out.println("setting user" + "success");
//                  try {

//            } catch (IOException ex) {
//                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//                System.out.println("Error loading the admin stage !");
//            }




            }

      
            
            
            
            
            
                  

    
    
    private void createPopup (String msg) throws FlowException{
        JFXDialogLayout content = new JFXDialogLayout();
         JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);
        dialog.setOverlayClose(false);
        content.setHeading(new Text(msg));
        GridPane  oo = new GridPane ();
     
       
       oo.setId("gridPopup");
        oo.setPrefHeight(100);
        oo.setPrefWidth(300);
        oo.setVgap(20);
        oo.setHgap(14);



        VBox vbox = new VBox();
        vbox.setPrefWidth(400);
        vbox.setSpacing(7);

       
    

      

        HBox actionParent = new HBox();
        actionParent.setId("actionParent");
        actionParent.setAlignment(Pos.CENTER);

        HBox okParent = new HBox();
        okParent.setId("okParent");
        okParent.setSpacing(10);
        okParent.setAlignment(Pos.CENTER);


    
        JFXButton buttonNoPopupRename = new JFXButton("OK");
        buttonNoPopupRename.setPrefHeight(30);
        buttonNoPopupRename.setPrefWidth(70);
        buttonNoPopupRename.setId("buttonNoPopupRename");
        buttonNoPopupRename.setButtonType(ButtonType.RAISED);
        buttonNoPopupRename.setOnAction(e -> dialog.close());
        buttonNoPopupRename.setStyle("-fx-text-fill:WHITE;-fx-background-color: #b81d1d;-fx-font-size:14px;");


   


        okParent.getChildren().addAll( buttonNoPopupRename);

        GridPane.setColumnIndex(okParent, 1);
        GridPane.setRowIndex(okParent, 1);
        GridPane.setColumnIndex(vbox, 1);
        GridPane.setRowIndex(vbox, 0);
        oo.getChildren().addAll(vbox, okParent);
        content.setBody(oo);
        dialog.show();
  }
    
	@PostConstruct
	public void init() throws FlowException, VetoException {

        
        }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         String appVersion = "v1";
    Backendless.initApp( this, "90AAA3BC-7A12-C560-FF51-67F0FF7CDF00", "0D351D4D-340C-4495-FFC5-8D5C28B29A00", appVersion );
  
// JFXDialogLayout content = new JFXDialogLayout();
//         JFXDialog dialog = new JFXDialog(root,new Label("hello"), JFXDialog.DialogTransition.CENTER);
//  
//   dialog.show();

//  Round round = new Round();
//     String DATE_FORMAT = "yyyy-MM-dd";
//    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
//    System.out.println("Formated Date " + sdf.format(new Date()));
// round.setDates(sdf.format(new Date())+"");
//  round.setResult(1.00);
// Backendless.Persistence.save( round );
// 
  // save object asynchronously



    
     


 
// ***********************************************************
// Synchronous API:
// ***********************************************************

    //****************************************
    
         
          
          // Asynchronous API:
// ***********************************************************
// create the inner flow and content
		context = new ViewFlowContext();
		// set the default controller 
		Flow innerFlow = new Flow(ButtonController.class);

		flowHandler = innerFlow.createHandler(context);
		context.register("ContentFlowHandler", flowHandler);
		context.register("ContentFlow", innerFlow);
          
    
    }   
    
       
}
