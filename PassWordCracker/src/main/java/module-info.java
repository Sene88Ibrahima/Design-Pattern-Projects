module com.example.passwordcracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;


    opens com.example.passwordcracker to javafx.fxml;
    exports com.example.passwordcracker;
}