module pdv {
    requires javafx.controls;
    requires javafx.fxml;

    opens pdv to javafx.fxml;
    exports pdv;

    opens pdv.dominio to javafx.fxml;
    exports pdv.dominio;
}