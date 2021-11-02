module open.domainliveliness {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    opens open.domainliveliness to javafx.fxml;
    exports open.domainliveliness;
}
