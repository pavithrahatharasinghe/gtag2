module com.example.gtag2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;
    requires java.desktop;
    requires jsoup;
    requires mp3agic;

    opens com.example.gtag2 to javafx.fxml;
    exports com.example.gtag2;
}