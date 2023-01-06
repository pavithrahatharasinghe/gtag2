package com.example.gtag2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GTag extends Application {
    @Override
    public void start(Stage stage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("GTag.fxml"));
        Scene scene = new Scene(root, 1900, 1000);

        String css = this.getClass().getResource("Styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        Image icon = new Image("E:\\Projects\\Java Advanced\\gtag2\\src\\main\\resources\\com\\example\\gtag2\\music.png");
        stage.getIcons().add(icon);
        stage.setTitle("Gtag v1.0!");

        //stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
