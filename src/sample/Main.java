package sample;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;


public class Main extends Application {


    Button button;
    Stage window;
    Scene scene1;
    TextField url,thread_no;
    String responseMessage,timeDelay;
    String request;
    URL urlR;
    GridPane gridRight;
    VBox leftBox;
    static boolean isAlive = true;
    static ListView<Object> result;
    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;
        button = new Button();


        //Vbox
        leftBox = new VBox();

//        //image
//        Image image = new Image(getClass().getResourceAsStream("doos.jpg"));
//
//        ImageView view = new ImageView();
//        view.setImage(image);
//        view.setFitHeight(100);
//        view.setFitWidth(300);
//        view.setSmooth(true);
        Label top = new Label("DDOS");
        top.setStyle(" -fx-font-size: 50px;\n" +
                "  -fx-text-fill: a9a9a9;\n"+
                "   -fx-font-family: \"Arial \";\n" +
                "   -fx-fill: #818181;\n" +
                "  -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 ); ");
        //left grid
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,0,10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        //left
        leftBox.getChildren().add(gridPane);

        result = new ListView<>();
        result.setStyle("  -fx-background-color: #F8F8FF;  -fx-background-radius: 5,4,3,5;");


        //right grid
        gridRight = new GridPane();
        gridRight.setPadding(new Insets(15,10,10,10));
        gridRight.setVgap(10);
        gridRight.setHgap(10);
        gridRight.getChildren().add(result);


        SplitPane sp = new SplitPane();

        //vertical
        VBox box = new VBox(15);
        box.getChildren().addAll(top,gridPane);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(50,0,0,0));

//        VBox box1 = new VBox(50);
        sp.getItems().addAll(box,gridRight);
        sp.getDividers();

        //labe for sec
        Label sec = new Label("seem wasting time");
       // gridRight.getChildren().add(sec);

        //label


        //url label
        Label url_label = new Label("Enter URL:");
        GridPane.setConstraints(url_label,0,0);

        //url input
        url = new TextField();
        url.setPromptText("URL");
        GridPane.setConstraints(url,1,0);


        //box


        //label thread number
        Label thread = new Label("thread No:");
        GridPane.setConstraints(thread,0,1);

        //thread input
        thread_no = new TextField();
        thread_no.setPromptText("Thread Number");
        GridPane.setConstraints(thread_no,1,1);

        //button
        button = new Button("ATTACK!");
        button.setOnAction(event -> {
            int val= 0;
            request= "";
            //ddos runnable class
            //System.out.println(thread_no.getText().toString()+" "+thread_no.getText());
            if(!thread_no.getText().isEmpty()  &&  (!thread_no.getText().isEmpty())){

                val = Integer.parseInt(thread_no.getText().toString());
                request = (url.getText().toString());
                url.setText("");
                thread_no.setText("");
                for (int i = 0;i<val;i++){
                    ddos attack = new ddos(request);
                    Thread me = new Thread(attack);
                    me.start();
                    if (isAlive==false){
                        me.stop();
                    }

//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                System.out.println(request+ "check from main: " + connection.getResponseMessage());
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });

               }

               // System.out.println("term!");
            }

            else{
                display.displayResult();

            }
        });
        GridPane.setConstraints(button,1,2);

        gridPane.getChildren().addAll(url_label,url,thread,thread_no,button);
        //gridPane.setAlignment(Pos.CENTER);

        scene1 = new Scene(sp,600,400);

        scene1.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        //second screen

        window.setTitle("Take it Down!");

        window.setScene(scene1);
        window.show();
    }

    public String getResponseMessage(){
        return responseMessage;
    }
    public  String getTimeDelay(){
        return timeDelay;
    }

    private void setResponseMessage(String resp){
        this.responseMessage = resp;
    }
    private void setTimeDelay(String delay){
        this.timeDelay = delay;
    }

    public static void main(String[] args) {

        launch(args);

    }


}


