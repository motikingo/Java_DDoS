package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class display {



    public  static void displayResult(){

        Stage window;
        Scene scene;


        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Alert!");


        Label f = new Label("fill the damm field..");

        Button check = new Button("okay pal");
        check.setOnAction(event -> window.close());

        VBox v = new VBox();
        v.getChildren().addAll(f,check);
        v.setAlignment(Pos.CENTER);

        scene = new Scene(v,200,100);


        window.setScene(scene);
        window.show();
    }
}
