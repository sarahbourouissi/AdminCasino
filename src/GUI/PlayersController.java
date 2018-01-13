/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Game;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PlayersController implements Initializable {
 @FXML

    private TableView<BackendlessUser> tableview;
    @FXML
    // private ObservableList<Certification> data;
    private TableColumn<BackendlessUser, String> id;
    @FXML
    private TableColumn<BackendlessUser, String> name;
    @FXML
    private TableColumn<BackendlessUser, String> email;
     ObservableList<BackendlessUser> users = FXCollections.observableArrayList();
     @FXML
     ImageView image ;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
 QueryOptions queryOptions = new QueryOptions();
 String whereClause = "status = '1'";
dataQuery.setWhereClause(whereClause );
        queryOptions.setPageSize( 100 );
        dataQuery.setQueryOptions( queryOptions );
 
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
     
       name.setCellValueFactory(new PropertyValueFactory<BackendlessUser, String>("name"));
            email.setCellValueFactory(new PropertyValueFactory<BackendlessUser, String>("email"));
       
      tableview.setItems(users);
        // TODO
    }    
    
}
