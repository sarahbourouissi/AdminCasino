 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Game;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GamesController implements Initializable {
@FXML
PieChart piechart ;
  @FXML
    ImageView image ;  
@FXML
 private JFXSpinner spinner;
   @FXML

    private TableView<Game> ctableview;
    @FXML
    // private ObservableList<Certification> data;
    private TableColumn<Game, String> cgame;
    @FXML
    private TableColumn<Game, String> cdatedebut;
    @FXML
    private TableColumn<Game, String> cdatefin;
     ObservableList<Game> Games = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          
 
        
      
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
 QueryOptions queryOptions = new QueryOptions();
        queryOptions.setPageSize( 100 );
        dataQuery.setQueryOptions( queryOptions );
 
// ***********************************************************
// Synchronous API:
// ***********************************************************
BackendlessCollection<Game> result = Backendless.Persistence.of( Game.class ).find( dataQuery );
      System.out.println(result.getData()+"ddd");
    
  Iterator<Game> iterator=result.getData().iterator();
 
            while( iterator.hasNext() )
            {
               Game game=iterator.next();
               Games.add(game);
                System.out.println( "Restaurant name = " + game.getDateFin() );
            }
    //****************************************
    cgame.setCellValueFactory(new PropertyValueFactory<Game, String>("objectId"));
     
       cdatefin.setCellValueFactory(new PropertyValueFactory<Game, String>("datefin"));
       
      ctableview.setItems(Games);
        // TODO*
    int i , roulette = 0 , blackjack = 0 , slot = 0;
        
        for(i = 0 ; i< Games.size(); i++){
             System.out.println(Games.get(i).getTypegame());
            if( null != Games.get(i).getTypegame() )switch (Games.get(i).getTypegame()) {
                case "roulette":
                    System.out.println(Games.get(i).getTypegame());
                    roulette ++;
                    break;
                case "blackjak":
                    blackjack ++ ;
                    break;
                case "slot":
                    slot ++ ;
                    break;
                default:
                    break;
            }
        }
             PieChart.Data slice1 = new PieChart.Data("Roulette", roulette);
        PieChart.Data slice2 = new PieChart.Data("Blackjack"  , blackjack);
        PieChart.Data slice3 = new PieChart.Data("Slot Machine"  , slot);
        piechart.getData().add(slice1);
        piechart.getData().add(slice2);
          piechart.getData().add(slice3);
    }    
    
}
