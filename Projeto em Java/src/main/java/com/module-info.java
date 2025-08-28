module com.mycompany.brasileirao.analytic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.brasileirao.analytic to javafx.fxml;
    exports com.mycompany.brasileirao.analytic;
}
