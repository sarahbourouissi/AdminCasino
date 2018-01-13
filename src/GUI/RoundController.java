/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Game;
import Entity.Round;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.skins.JFXTimePickerContent;
import com.jfoenix.svg.SVGGlyph;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RoundController implements Initializable {

 @FXML StackPane stack ;
      @FXML
    JFXSpinner spinner;
    @FXML
    BarChart barchart;
    @FXML
    JFXDatePicker datePickerFX;
    @FXML private JFXScrollPane scroll;
    @FXML 
    PieChart pieChart ;
    @FXML
    ImageView image ;
@FXML
    JFXListView<String> listview ;
 private List<String> listName = new ArrayList<String>() ;;
  ObservableList<BackendlessUser> users = FXCollections.observableArrayList();
    ObservableList<String> listusers = FXCollections.observableArrayList();
       ObservableList<Round> rounds = FXCollections.observableArrayList();
       private int i ,wins  , loses  ;
           @FXML
     TableView<Round> tableview;
    @FXML
     TableColumn<Round,String> idround;
   @FXML
   private JFXButton validate;
    @FXML
          private  AnchorPane satistics;
    @FXML

   TableColumn<Round,Date> date;
    @FXML
     TableColumn<Round,String> resultat;
    @FXML
    ScrollBar  sB;
    @FXML
    private TableColumn<Round,Double> somme;
   @FXML
   private StackPane stackpane;
@FXML private JFXButton backButton;
  int counter = 1 ;
  private static String idd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spinner.setVisible(false);
     listview.depthProperty().set(1); 
     listview.setExpanded(true);
   

      
     
     //***********************
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();

// listview.getStyleClass().add("mylistview");
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
               listusers.add(user.getProperty("name")+"");
              
             
            }
      listview.setItems(listusers);
      
      listview.setOnMouseClicked((event) -> {
          
           idd=users.get(listview.getSelectionModel().getSelectedIndex()).getEmail();
          afficherRounds(idd);
          
      });
      
      
        
       
    }  
    @FXML
    public void barchart(ActionEvent ae){
        
        barchart.getData().clear();
        String firstDate = datePickerFX.getValue().toString();
        System.out.println(firstDate+"saraaaaaaaaaaaaaah");
        //*****************************
        
            
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();

 
// ***********************************************************
// Synchronous API:
// ***********************************************************
System.out.println("iddd" + idd);
String whereClause = "useremail = '"+idd + "'";
dataQuery.setWhereClause(whereClause );
  QueryOptions queryOptions = new QueryOptions();
        queryOptions.setPageSize( 100 );
        dataQuery.setQueryOptions( queryOptions );
BackendlessCollection<Round> result = Backendless.Persistence.of( Round.class ).find( dataQuery );
      System.out.println(result.getData()+"ddd");
    
  Iterator<Round> iterator=result.getData().iterator();
 
            while( iterator.hasNext() )
            {
               Round round=iterator.next();
               rounds.add(round);
                
            }
                 idround.setCellValueFactory(new PropertyValueFactory<Round, String>("objectId"));
     
       date.setCellValueFactory(new PropertyValueFactory<Round, Date>("created"));
      resultat.setCellValueFactory(new PropertyValueFactory<Round, String>("result"));
       somme.setCellValueFactory(new PropertyValueFactory<Round, Double>("resultfloat"));
       wins = 0;
       loses = 0 ;
       for ( i=0 ; i < rounds.size(); i++ ){
           System.out.println("test"+firstDate);
           System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(rounds.get(i).getCreated()) + "test");
           
           
       if(firstDate.equals( new SimpleDateFormat("yyyy-MM-dd").format(rounds.get(i).getCreated()))){
           if (rounds.get(i).getResult() == 0){
                  System.out.println("cc");
       loses ++;
               
       }
           else if(rounds.get(i).getResult() == 1)
           {     
       wins++;
               System.out.println(",nnnn");
           }
       }
       }
    
        
        System.out.println( loses +  wins + "dffffffffffffff" );
        
        //*********************************
        
       
    
        //mois jour anne
        //anné mois jour
         //2017-05-11
     CategoryAxis xAxis = new CategoryAxis();
       xAxis.setLabel("Statistics per day");

       NumberAxis yAxis = new NumberAxis();
       yAxis.setLabel("Statistics per day");
       
//          String[] date = seance.getStardate().split("-");
//        String[] heure = date[2].split(" ");
//        String[] min = heure[1].split(":");
//        String[] dateF = seance.getEnddate().split("-");
//        String[] heureF = dateF[2].split(" ");
//        String[] minF = heureF[1].split(":");
       
     XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
      XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<String, Number>();
       dataSeries1.setName("WINS");
        dataSeries1.setName("LOSES");
     dataSeries1.getData().add(new XYChart.Data<String, Number>("WINS", wins));
       dataSeries2.getData().add(new XYChart.Data<String, Number>("LOSES", loses));
      
       barchart.getData().add(dataSeries1);
        barchart.getData().add(dataSeries2);
    }
     public void afficherRounds(String id){
         rounds.clear();
         tableview.refresh();
        longStart();
         
         System.out.println("coucou");
         
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();

  QueryOptions queryOptions = new QueryOptions();
        queryOptions.setPageSize( 100 );
        dataQuery.setQueryOptions( queryOptions );
// ***********************************************************
// Synchronous API:
// ***********************************************************
String whereClause = "useremail = '"+id + "'";
dataQuery.setWhereClause(whereClause );
 
BackendlessCollection<Round> result = Backendless.Persistence.of( Round.class ).find( dataQuery );
      System.out.println(result.getData()+"ddd");
    
  Iterator<Round> iterator=result.getData().iterator();
 
            while( iterator.hasNext() )
            {
               Round round=iterator.next();
               rounds.add(round);
                
            }
                 idround.setCellValueFactory(new PropertyValueFactory<Round, String>("objectId"));
     
       date.setCellValueFactory(new PropertyValueFactory<Round, Date>("created"));
      resultat.setCellValueFactory(new PropertyValueFactory<Round, String>("result"));
       somme.setCellValueFactory(new PropertyValueFactory<Round, Double>("resultfloat"));
       wins = 0;
       loses = 0 ;
       for ( i=0 ; i < rounds.size(); i++ ){
       
           if (rounds.get(i).getResult() == 0){
       loses ++;
       }
           else if(rounds.get(i).getResult() == 1)
       wins++;
       
       
       }
    
       tableview.setItems(rounds);
pieChart.getData().clear();

  barchart.getData().clear();
        PieChart.Data slice1 = new PieChart.Data("WINS", wins);
        PieChart.Data slice2 = new PieChart.Data("LOSES"  , loses);
      

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
      

         }  
      private void longStart() {
             BooleanProperty ready = new SimpleBooleanProperty(false);
        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int max = 100;
                spinner.setVisible(true);
                // enviar notidicacion de progreso cada 50 ms
                for (int i = 1; i <= max; i++) {
                    Thread.sleep(50);
                  //  notifyPreloader(new ProgressNotification(i));
                }

                Thread.sleep(200);
                // indicar que la carga ha terminado, 100% completa
                ready.setValue(Boolean.TRUE);
            //    notifyPreloader(new StateChangeNotification(StateChangeNotification.Type.BEFORE_START));
spinner.setVisible(false);
                return null;
            }
        };
        new Thread(task).start();
    }
}
