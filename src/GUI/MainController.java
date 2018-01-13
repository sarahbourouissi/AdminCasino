/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Game;
import Entity.Round;
import GUI.DotMatrix.DotShape;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import eu.hansolo.tilesfx.Country;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.TimeSection;
import eu.hansolo.tilesfx.TimeSectionBuilder;
import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.skins.LeaderBoardItem;
import eu.hansolo.tilesfx.tools.ChartData;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import eu.hansolo.tilesfx.tools.Location;
import eu.hansolo.tilesfx.weather.DarkSky;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MainController implements Initializable {
   
   @FXML 
   AnchorPane anchorpane , tiles ;
   
   @FXML 
   StackPane stack;
   //*** casino matrix declaration
    private static final int            LIME = DotMatrix.convertToInt(Color.WHITE);
    private static final int            RED  = DotMatrix.convertToInt(Color.YELLOW);
    private              int            x;
    private              DotMatrix      matrix;
    private              String         text;
    private              int            textLength;
    private              int            textLengthInPixel;
    private              int            offset;
    private              long           lastTimerCall;
    private              AnimationTimer timer;
    /**
     * Initializes the controller class.
     */
    
      //*** tiles declaration
      private static final    Random RND = new Random();
    private static final    double TILE_WIDTH  = 250;
    private static final    double TILE_HEIGHT = 250;
    private BarChartItem    barChartItem1;
    private BarChartItem    barChartItem2;
    private BarChartItem    barChartItem3;
    
    private LeaderBoardItem leaderBoardItem1;
    private LeaderBoardItem leaderBoardItem2;
    private LeaderBoardItem leaderBoardItem3;
    private LeaderBoardItem leaderBoardItem4;
    private ChartData       chartData1;
    private ChartData       chartData2;
     private ChartData       daychartData1;
    private ChartData       daychartData2;
   
    private Tile            percentageTile;
    private Tile            clockTile;
    private Tile            gaugeTile;
    private Tile            sparkLineTile;
    private Tile            areaChartTile;
    private Tile            lineChartTile;
    private Tile            highLowTile;
    private Tile            dayhighLowTile;
    private Tile            timerControlTile;
    private Tile            numberTile;
    private Tile            textTile;
    private Tile            plusMinusTile;
    private Tile            sliderTile;
    private Tile            switchTile;
    private Tile            worldTile;
    private Tile            weatherTile;
    private Tile            timeTile;
    private Tile            barChartTile;
    private Tile            customTile;
    private Tile            leaderBoardTile;
    private Tile            mapTile;
    private Tile            radialChartTile;
    private Tile            donutChartTile;
    private Tile            circularProgressTile;
    private Tile            stockTile;
    private long            lastTimerCalls;
    private AnimationTimer  timers;
    private DoubleProperty  values;

    //***** DATA INITIALIZE
     ObservableList<BackendlessUser> users = FXCollections.observableArrayList();
     
     int j , roulette = 0 , blackjack = 0 , slot = 0 , nbrplayers , wins ,loses , chipswined = 0 , chipsloses = 0;
     
       int chipswinday =0 , chipslosday =0 ,winsDay = 0 , losesDay = 0 ;
     ObservableList<Game> Games = FXCollections.observableArrayList();
     ObservableList<Round> rounds = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //**************GET DATA *****************

        //TYPE GAME
        
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
        
        
        
        for(j = 0 ; j< Games.size(); j++){
             System.out.println(Games.get(j).getTypegame());
            if( null != Games.get(j).getTypegame() )switch (Games.get(j).getTypegame()) {
                case "roulette":
                    System.out.println(Games.get(j).getTypegame());
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
        
        
        //**************players nbr 
        
        
        
        
        
        
        
         BackendlessDataQuery dataQuerys = new BackendlessDataQuery();

        queryOptions.setPageSize( 100 );
        dataQuerys.setQueryOptions( queryOptions );
 
// ***********************************************************
// Synchronous API:
// ***********************************************************
BackendlessCollection<BackendlessUser> results = Backendless.Persistence.of( BackendlessUser.class ).find( dataQuerys );
      System.out.println(result.getData()+"ddd");
    
  Iterator<BackendlessUser> iterators=results.getData().iterator();
 
            while( iterators.hasNext() )
            {
               BackendlessUser user =iterators.next();
               users.add(user);
               
            }
            nbrplayers = users.size()-1 ;
            System.out.println("ccccccccccc" + users.size());
        //*** initializa matrix casino
       matrix            = DotMatrixBuilder.create()
                                            .prefSize(800, 70)
                                            .colsAndRows(128, 13)
                                            .dotOnColor(Color.rgb(255, 55, 0))
                                            .dotShape(DotShape.ROUND)
                                            .matrixFont(MatrixFont8x8.INSTANCE)
                                            .useSpacer(false)
                                            .build();
        x                 = matrix.getCols() + 7;
        text              = "Smart Casino ";
        textLength        = text.length();
        textLengthInPixel = textLength * 8;
        offset            = 3;

        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(final long now) {
                if (now > lastTimerCall + 10_000_000l) {
                    if (x < -textLengthInPixel) {
                        x = matrix.getCols() + 7;
                        if (matrix.getMatrixFont().equals(MatrixFont8x8.INSTANCE)) {
                            matrix.setMatrixFont(MatrixFont8x11.INSTANCE);
                            text       = " Administrator  ";
                            offset     = 1;
                            matrix.setDotShape(DotShape.ROUND);
                        } else {
                            matrix.setMatrixFont(MatrixFont8x8.INSTANCE);
                            text       = "Dashbord ";
                            offset     = 3;
                            matrix.setDotShape(DotShape.ROUND);
                        }
                        textLength        = text.length();
                        textLengthInPixel = textLength * 8;
                    }
                    for (int i = 0 ; i < textLength ; i++) {
                        matrix.setCharAt(text.charAt(i), x + i * 8, offset, i % 2 == 0 ? LIME : RED);
                    }
                    x--;
                    lastTimerCall = now;
                }
            }
        };  
        
        //****** wins and losses in all 
        
        BackendlessDataQuery dataQueryround = new BackendlessDataQuery();

        dataQueryround.setQueryOptions( queryOptions );
// ***********************************************************
// Synchronous API:
// ***********************************************************

 
BackendlessCollection<Round> resultrounds = Backendless.Persistence.of( Round.class ).find( dataQueryround );
      System.out.println(result.getData()+"ddd");
    
  Iterator<Round> iteratorround=resultrounds.getData().iterator();
 
            while( iterator.hasNext() )
            {
               Round round=iteratorround.next();
               rounds.add(round);
                
            }
            System.out.println(rounds);

       wins = 0;
       loses = 0 ;

       
         BackendlessDataQuery dataQuery2 = new BackendlessDataQuery();

 dataQuery2.setQueryOptions( queryOptions );
// ***********************************************************
// Synchronous API:
// ***********************************************************

 
BackendlessCollection<Round> result2 = Backendless.Persistence.of( Round.class ).find( dataQuery2 );
      System.out.println(result2.getData()+"ddd");
    
  Iterator<Round> iterator2=result2.getData().iterator();
 
            while( iterator2.hasNext() )
            {
               Round round=iterator2.next();
               rounds.add(round);
                
            }
            
           
            System.out.println("fff"+rounds.size());
       //*********** wins and losses chips of casino  
       
             for ( j=0 ; j < rounds.size(); j++ ){
       
           if (rounds.get(j).getResult() == 0){
               System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(rounds.get(j).getCreated()));
             if(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(new SimpleDateFormat("yyyy-MM-dd").format(rounds.get(j).getCreated()))){
             chipslosday ++;
             losesDay =(int) (rounds.get(j).getResultfloat()+losesDay);
             }
               System.out.println("********************");
               
       loses ++;
       chipsloses = (int) (rounds.get(j).getResultfloat()+chipsloses);
               System.out.println(rounds.get(j).getResultfloat()+"resuuult");
       
       }
           if(rounds.get(j).getResult() == 1) 
           {
                 if(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(new SimpleDateFormat("yyyy-MM-dd").format(rounds.get(j).getCreated()))){
             chipswinday++;
             winsDay =(int) (rounds.get(j).getResultfloat()+ winsDay);
             }

               
       wins++;
       chipswined = (int) (rounds.get(j).getResultfloat()+chipswined);
           }
       }
             
             
        System.out.println("GUI.MainController.initialize()"+ loses);
        System.out.println(chipswined+ "ccccccccc"+chipsloses);

 
// ***********************************************************
// Synchronous API:
// ***********************************************************


       
       
       
       
       

//************ casino tiles dashbord


  values = new SimpleDoubleProperty(0);

        // LineChart Data
        XYChart.Series<String, Number> series1 = new XYChart.Series();
        series1.setName("Whatever");
        series1.getData().add(new XYChart.Data("MO", 23));
        series1.getData().add(new XYChart.Data("TU", 21));
        series1.getData().add(new XYChart.Data("WE", 20));
        series1.getData().add(new XYChart.Data("TH", 22));
        series1.getData().add(new XYChart.Data("FR", 24));
        series1.getData().add(new XYChart.Data("SA", 22));
        series1.getData().add(new XYChart.Data("SU", 20));

        XYChart.Series<String, Number> series2 = new XYChart.Series();
        series2.setName("Inside");
        series2.getData().add(new XYChart.Data("MO", 8));
        series2.getData().add(new XYChart.Data("TU", 5));
        series2.getData().add(new XYChart.Data("WE", 0));
        series2.getData().add(new XYChart.Data("TH", 2));
        series2.getData().add(new XYChart.Data("FR", 4));
        series2.getData().add(new XYChart.Data("SA", 3));
        series2.getData().add(new XYChart.Data("SU", 5));

        XYChart.Series<String, Number> series3 = new XYChart.Series();
        series3.setName("Outside");
        series3.getData().add(new XYChart.Data("MO", 8));
        series3.getData().add(new XYChart.Data("TU", 5));
        series3.getData().add(new XYChart.Data("WE", 0));
        series3.getData().add(new XYChart.Data("TH", 2));
        series3.getData().add(new XYChart.Data("FR", 4));
        series3.getData().add(new XYChart.Data("SA", 3));
        series3.getData().add(new XYChart.Data("SU", 5));

        // WorldMap Data
        for (int i = 0 ; i < Country.values().length ; i++) {
            double value = RND.nextInt(10);
            Color  color;
            if (value > 8) {
                color = Tile.RED;
            } else if (value > 6) {
                color = Tile.ORANGE;
            } else if (value > 4) {
                color = Tile.YELLOW_ORANGE;
            } else if (value > 2) {
                color = Tile.GREEN;
            } else {
                color = Tile.BLUE;
            }
            Country.values()[i].setColor(color);
        }

        // TimeControl Data
        TimeSection timeSection = TimeSectionBuilder.create()
                                        .start(LocalTime.now().plusSeconds(20))
                                        .stop(LocalTime.now().plusHours(1))
                                        //.days(DayOfWeek.MONDAY, DayOfWeek.FRIDAY)
                                        .color(Tile.GRAY)
                                        .highlightColor(Tile.RED)
                                        .build();

        timeSection.setOnTimeSectionEntered(e -> System.out.println("Section ACTIVE"));
        timeSection.setOnTimeSectionLeft(e -> System.out.println("Section INACTIVE"));

        // Weather (You can get a DarkSky API key here: https://darksky.net/dev/ )
        DarkSky darkSky = new DarkSky("YOUR DARKSKY API KEY", DarkSky.Unit.CA, DarkSky.Language.ENGLISH, 51.911858, 7.632815);
        //darkSky.update();

        // BarChart Items
        barChartItem1 = new BarChartItem("Roulette", roulette, Tile.BLUE);
        barChartItem2 = new BarChartItem("Slot Machine", slot, Tile.RED);
        barChartItem3 = new BarChartItem("BlackJack", blackjack, Tile.GREEN);
      
        

        // LeaderBoard Items
        leaderBoardItem1 = new LeaderBoardItem("sarah", 47);
        leaderBoardItem2 = new LeaderBoardItem("farabi", 43);
        leaderBoardItem3 = new LeaderBoardItem("raid", 12);
        leaderBoardItem4 = new LeaderBoardItem("ahmed", 8);

        // RadialChart Data
        chartData1 = new ChartData("Wins", wins, Tile.GREEN);
        chartData2 = new ChartData("Loses", loses, Tile.RED);
           daychartData1 = new ChartData("Wins", chipswined, Tile.GREEN);
        daychartData2 = new ChartData("Loses", chipslosday, Tile.RED);
       
        //RadialChartData.animated = false;


        // Creating Tiles
        percentageTile = TileBuilder.create()
                                    .skinType(Tile.SkinType.PERCENTAGE)
                                    .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                    .title("Players Today")
                                    .unit("\u0025")
                                    .description("Test")
                                    .maxValue(60)
                                    .build();

        clockTile = TileBuilder.create()
                               .skinType(Tile.SkinType.CLOCK)
                               .prefSize(TILE_WIDTH, TILE_HEIGHT)
                               .title("Clock ")
                               .text("Whatever text")
                               .dateVisible(true)
                               .locale(Locale.US)
                               .running(true)
                               .build();

        gaugeTile = TileBuilder.create()
                               .skinType(Tile.SkinType.GAUGE)
                               .prefSize(TILE_WIDTH, TILE_HEIGHT)
                               .title("Gauge Tile")
                               .unit("V")
                               .threshold(75)
                               .build();

        sparkLineTile = TileBuilder.create()
                                   .skinType(Tile.SkinType.SPARK_LINE)
                                   .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                   .title("SparkLine Tile")
                                   .unit("mb")
                                   .gradientStops(new Stop(0, Tile.GREEN),
                                                  new Stop(0.5, Tile.YELLOW),
                                                  new Stop(1.0, Tile.RED))
                                   .strokeWithGradient(true)
                                   .build();

        //sparkLineTile.valueProperty().bind(value);

        areaChartTile = TileBuilder.create()
                                   .skinType(Tile.SkinType.AREA_CHART)
                                   .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                   .title("AreaChart Tile")
                                   .series(series1)
                                   .build();

        lineChartTile = TileBuilder.create()
                                   .skinType(Tile.SkinType.LINE_CHART)
                                   .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                   .title("LineChart Tile")
                                   .series(series2, series3)
                                   .build();
           dayhighLowTile = TileBuilder.create()
                                 .skinType(Tile.SkinType.HIGH_LOW)
                                 .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                 .title("Gain/loss per day")
                                 .unit("\u0025")
                                 .description("Test")
                                 .text("Whatever text")
                                 .referenceValue(winsDay)
                                 .value(losesDay)
                                 .build();

        highLowTile = TileBuilder.create()
                                 .skinType(Tile.SkinType.HIGH_LOW)
                                 .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                 .title("Gain/loss")
                                 .unit("\u0025")
                                 .description("Test")
                                 .text("Whatever text")
                                 .referenceValue(chipswined)
                                 .value(chipsloses)
                                 .build();

        timerControlTile = TileBuilder.create()
                                      .skinType(Tile.SkinType.TIMER_CONTROL)
                                      .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                      .title("TimerControl Tile")
                                      .text("Whatever text")
                                      .secondsVisible(true)
                                      .dateVisible(true)
                                      .timeSections(timeSection)
                
                                      .build();

        numberTile = TileBuilder.create()
                                .skinType(Tile.SkinType.NUMBER)
                                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                .title("Number of players")
                                .text("Whatever text")
                                .value(nbrplayers)
                                .unit("player")
                                .description("Test")
                                .textVisible(true)
                                .build();

        textTile = TileBuilder.create()
                              .skinType(Tile.SkinType.TEXT)
                              .prefSize(TILE_WIDTH, TILE_HEIGHT)
                              .title("Quotes")
                              .text("Whatever text")
                              .description("Oh, it's not really gambling when you never \n...lose")
                              .descriptionAlignment(Pos.TOP_LEFT)
                              .textVisible(true)
                              .build();

        plusMinusTile = TileBuilder.create()
                                   .skinType(Tile.SkinType.PLUS_MINUS)
                                   .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                   .maxValue(30)
                                   .minValue(0)
                
                                   .title("PlusMinus Tile")
                                   .text("Whatever text")
                                   .description("Test")
                                   .unit("\u00B0C")
                                   .build();

        sliderTile = TileBuilder.create()
                                .skinType(Tile.SkinType.SLIDER)
                                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                .title("Slider Tile")
                                .text("Whatever text")
                                .description("Test")
                                .unit("\u00B0C")
                                .barBackgroundColor(Tile.FOREGROUND)
                                .build();

        switchTile = TileBuilder.create()
                                .skinType(Tile.SkinType.SWITCH)
                                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                .title("Switch Tile")
                                .text("Whatever text")
                                //.description("Test")
                                .build();

        switchTile.setOnSwitchPressed(e -> System.out.println("Switch pressed"));
        switchTile.setOnSwitchReleased(e -> System.out.println("Switch released"));

   

        // Update the weather information by calling weatherTile.updateWeather()
        weatherTile = TileBuilder.create()
                                 .skinType(Tile.SkinType.WEATHER)
                                 .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                 .title("YOUR CITY NAME")
                                 .text("Whatever text")
                                 .darkSky(darkSky)
                                 .build();

        timeTile = TileBuilder.create()
                              .skinType(Tile.SkinType.TIME)
                              .prefSize(TILE_WIDTH, TILE_HEIGHT)
                              .title("Time ")
                              .text("Whatever text")
                              .duration(LocalTime.of(1, 22))
                              .description("Average reply time")
                              .textVisible(true)
                              .build();

        barChartTile = TileBuilder.create()
                                  .skinType(Tile.SkinType.BAR_CHART)
                                  .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                  .title("Games")
                                  .text("Whatever text")
                                  .barChartItems(barChartItem1, barChartItem2, barChartItem3)
                                  .decimals(0)
                                  .build();

        customTile = TileBuilder.create()
                                .skinType(Tile.SkinType.CUSTOM)
                                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                .title("Custom Tile")
                                .text("Whatever text")
                                .graphic(new Button("Click Me"))
                                .roundedCorners(false)
                                .build();

        leaderBoardTile = TileBuilder.create()
                                     .skinType(Tile.SkinType.LEADER_BOARD)
                                     .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                     .title("LeaderBoard ")
                                     .text("Whatever text")
                                     .leaderBoardItems(leaderBoardItem1, leaderBoardItem2, leaderBoardItem3, leaderBoardItem4)
                                     .build();


        radialChartTile = TileBuilder.create()
                                     .skinType(Tile.SkinType.RADIAL_CHART)
                                     .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                     .title("Wins And loses per day")
                                     .text("Some text")
                                     .textVisible(false)
                                     .radialChartData(daychartData1, daychartData2)
                                     .build();

        donutChartTile = TileBuilder.create()
                                     .skinType(Tile.SkinType.DONUT_CHART)
                                     .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                     .title("Wins and losses of players")
                                     .text("Some text")
                                     .textVisible(false)
                                     .radialChartData(chartData1, chartData2)
                                     .build();

        circularProgressTile = TileBuilder.create()
                                         .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                                         .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                         .title("CircularProgress")
                                         .text("Some text")
                                         .unit("\u0025")
                                         .build();

        stockTile = TileBuilder.create()
                               .skinType(Tile.SkinType.STOCK)
                               .prefSize(TILE_WIDTH, TILE_HEIGHT)
                               .title("Stock")
                               .minValue(0)
                               .maxValue(1000)
                               .averagingPeriod(100)
                               .build();

        lastTimerCalls = System.nanoTime();
        timers = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCalls + 3_500_000_000L) {
                 
                    sparkLineTile.setValue(RND.nextDouble() * sparkLineTile.getRange() * 1.5 + sparkLineTile.getMinValue());
                    //value.set(RND.nextDouble() * sparkLineTile.getRange() * 1.5 + sparkLineTile.getMinValue());
                    //sparkLineTile.setValue(20);


                   

                  
               
                    stockTile.setValue(RND.nextDouble() * 50 + 500);

                    lastTimerCalls = now;
                }
            }
        };
          FlowGridPane pane = new FlowGridPane(4, 5,
                                         percentageTile, clockTile, 
                                         lineChartTile, timerControlTile, numberTile, textTile,
                                         highLowTile, dayhighLowTile,
                                         barChartTile, leaderBoardTile, 
                                         radialChartTile, donutChartTile);//, weatherTile);

        pane.setHgap(5);
        pane.setVgap(5);
        pane.setAlignment(Pos.CENTER);
        pane.setCenterShape(true);
        pane.setPadding(new Insets(5));
        pane.setPrefSize(1100, 500);
        pane.setBackground(new Background(new BackgroundFill(Color.web("#101214"), CornerRadii.EMPTY, Insets.EMPTY)));
tiles.getChildren().add(pane);

//***
          stack.setPadding(new Insets(10));
       stack.setBackground(new Background(new BackgroundFill(Color.rgb(30, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));

   stack.getChildren().add(matrix);
  //    anchorpane.getChildren().add(matrix) ; 
    timer.start();// TODO
    timers.start();
    }    
    
}
