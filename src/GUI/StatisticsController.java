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
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXSpinner;
import com.sun.prism.impl.Disposer.Record;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.management.Notification;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author user
 */
public class StatisticsController implements Initializable {
    public static BackendlessUser user ;
    public static int index;
  @FXML static StackPane root;
  @FXML

    private TableView<BackendlessUser> tableview;
    @FXML
    // private ObservableList<Certification> data;
    private TableColumn<BackendlessUser, String> id;
    @FXML
    private TableColumn<BackendlessUser, String> name;
    @FXML
    private TableColumn<BackendlessUser, String> email;
    @FXML
    ImageView image ;
   @FXML
    private TableColumn<Record, Boolean>  confirmation;
     ObservableList<BackendlessUser> users = FXCollections.observableArrayList();
     ObservableList<String> etat = FXCollections.observableArrayList("Submit","Reject");
    /**
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
             System.out.println("coucou");
      BackendlessDataQuery dataQuery = new BackendlessDataQuery();

 String whereClause = "status = '0'";
dataQuery.setWhereClause(whereClause );
// ***********************************************************
// Synchronous API:
// ***********************************************************
BackendlessCollection<BackendlessUser> result = Backendless.Persistence.of( BackendlessUser.class ).find( dataQuery );
      System.out.println(result.getData()+"ddd");
    
  Iterator<BackendlessUser> iterator=result.getData().iterator();
 
            while( iterator.hasNext() )
            {
               BackendlessUser user =iterator.next();
               users.add(user);
               
            }
    //****************************************
    id.setCellValueFactory(new PropertyValueFactory<BackendlessUser, String>("objectId"));
        System.out.println("coucou");
             System.out.println(users);
       name.setCellValueFactory(new PropertyValueFactory<BackendlessUser, String>("name"));
            email.setCellValueFactory(new PropertyValueFactory<BackendlessUser, String>("email"));
       
        confirmation.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
           //Adding the Button to the cell
        confirmation.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                 System.out.println("ss");
                return new ButtonCell();
               
            }
        
        });
        
      tableview.setItems(users);   
        // TODO
         tableview.setOnMouseClicked((event) -> {
         
          index = tableview.getSelectionModel().getSelectedIndex();
          user = Backendless.Persistence.of( BackendlessUser.class ).findById( tableview.getSelectionModel().getSelectedItem().getObjectId() );
        
      });
        
    }   

    private static class ButtonCell extends TableCell<Record, Boolean> {
        
       final JFXComboBox cellButton = new JFXComboBox();
        
            int chips ;
        public ButtonCell() {
            cellButton.getItems().addAll("Submit","Reject");
            cellButton.setVisible(true);
           
          
            //Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                  //  System.out.println(user.getObjectId());
                    System.out.println(cellButton.getSelectionModel().getSelectedItem());
                  
                
                //    System.out.println(chips);
//   
//          System.out.println(user.getEmail());
//    user.setProperty( "balance", Integer.parseInt(nbrchips.getText()) );
// Backendless.Persistence.of(BackendlessUser.class).save( user );
if(user != null) {
      chips = (int) user.getProperty("balance");
                    if (cellButton.getSelectionModel().getSelectedItem()== "Submit"){
                    
                        user.setProperty( "balance", chips+20 );
                        user.setProperty( "status", 1);
   Backendless.Persistence.of(BackendlessUser.class).save( user ); 
   String title = "SUCCESS";
        String message = "Player Submited";
                        Image img = new Image("/image/smartcasino.jpg");
    //   NotificationType notification = NotificationType.SUCCESS;
        
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setRectangleFill(Paint.valueOf("#3B0405"));
        tray.setImage(img);
      //  tray.setNotificationType(notification);
        tray.showAndDismiss(Duration.seconds(3));
                        
                    }
                    
                    
                    else if(cellButton.getSelectionModel().getSelectedItem()== "Reject"){
                    
                    
                    Backendless.Persistence.of( BackendlessUser.class ).remove( user );
                    
                    }
                    
} else {
  JFXDialogLayout content = new JFXDialogLayout();
         JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);
        dialog.setOverlayClose(false);
        content.setHeading(new Text("select a row please ! "));
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
        buttonNoPopupRename.setButtonType(JFXButton.ButtonType.RAISED);
        buttonNoPopupRename.setOnAction(e -> dialog.close());
        buttonNoPopupRename.setStyle("-fx-text-fill:WHITE;-fx-background-color: #b81d1d;-fx-font-size:14px;");


   


        okParent.getChildren().addAll( buttonNoPopupRename);

        GridPane.setColumnIndex(okParent, 1);
        GridPane.setRowIndex(okParent, 1);
    
     
        oo.getChildren().addAll( okParent);
        content.setBody(oo);
        dialog.show();
    
    
    
    System.out.println("coucou");
}
                    // get Selected Item
                    
                	//Person currentPerson = (Person) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                	//data.remove(currentPerson);
                }
            });
        }
              @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }
    }
    
}
