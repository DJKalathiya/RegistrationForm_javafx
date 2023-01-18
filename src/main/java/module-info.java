module com.example.registrationform {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.registrationform to javafx.fxml;
    exports com.example.registrationform;
}