package com.example.registrationform;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Assignment2 extends Application {
    final static int LIMIT = 10;
    private String[] days = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };

    private String[] flagMonth = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    private String[] years = { "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022" };

    private ComboBox<String> cbday = new ComboBox<>();
    private ComboBox<String> cbMonth = new ComboBox<>();
    private ComboBox<String> cbYear = new ComboBox<>();

    @Override
    public void start(Stage primaryStage) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        String mRegx = "\\d{8}|\\d{10}|\\d{11}";
        String regex = "^[A-Za-z]\\w{5,29}$";

        BorderPane borderPane = new BorderPane();

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(5);
        pane.setVgap(10);

        HBox hBoxforText = new HBox();
        Text text1 = new Text(0, 0, "Login");
        text1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25));
        hBoxforText.getChildren().addAll(text1);
        hBoxforText.setAlignment(Pos.BOTTOM_CENTER);

        HBox hBoxforRadio = new HBox();
        hBoxforRadio.setSpacing(5);
        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        hBoxforRadio.getChildren().addAll(male, female);

        ToggleGroup group = new ToggleGroup();
        male.setToggleGroup(group);
        female.setToggleGroup(group);

        HBox hboxforcombo = new HBox();
        cbday.setValue("1");
        cbday.getItems().addAll(days);
        cbMonth.setValue("January");
        cbMonth.getItems().addAll(flagMonth);
        cbYear.setValue("2000");
        cbYear.getItems().addAll(years);
        hboxforcombo.getChildren().addAll(cbday, cbMonth, cbYear);

        HBox hboxforLanguage = new HBox();
        hboxforLanguage.setSpacing(5);
        CheckBox CH = new CheckBox("Hindi");
        CheckBox CE = new CheckBox("English");
        CheckBox CG = new CheckBox("Gujrati");
        hboxforLanguage.getChildren().addAll(CG, CH, CE);

        HBox hBoxforbutton = new HBox();
        hBoxforbutton.setSpacing(5);
        Button btsubmit = new Button("Submit");
        Button btcancle = new Button("Reset");
        hBoxforbutton.getChildren().addAll(btsubmit, btcancle);
        hBoxforbutton.setAlignment(Pos.TOP_CENTER);

        pane.add(new Label("First Name:"), 0, 1);
        pane.add(new Label("Middle Name:"), 0, 2);
        pane.add(new Label("Last Name:"), 0, 3);
        pane.add(new Label("Email Address:"), 0, 4);
        pane.add(new Label("Mobile Number:"), 0, 5);
        pane.add(new Label("Date Of Birth:"), 0, 6);
        pane.add(new Label("Gender:"), 0, 7);
        pane.add(new Label("languages Known:"), 0, 8);

        TextField textFieldFirstName = new TextField();
        pane.add(textFieldFirstName, 1, 1);
        TextField textFieldMiddleName = new TextField();
        pane.add(textFieldMiddleName, 1, 2);
        TextField textFieldLastName = new TextField();
        pane.add(textFieldLastName, 1, 3);
        TextField textFieldForEmail = new TextField();
        pane.add(textFieldForEmail, 1, 4);
        TextField textFieldForNumber = new TextField();
        pane.add(textFieldForNumber, 1, 5);
        pane.add(hboxforcombo, 1, 6);
        pane.add(hBoxforRadio, 1, 7);
        pane.add(hboxforLanguage, 1, 8);

        borderPane.setTop(hBoxforText);
        borderPane.setCenter(pane);
        borderPane.setBottom(hBoxforbutton);

        textFieldForNumber.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if (newValue.intValue()>oldValue.intValue()){
                    if (textFieldForNumber.getText().length() >= LIMIT){
                        textFieldForNumber.setText(textFieldForNumber.getText().substring(0,LIMIT));
                    }
                }
            }
        });

        borderPane.setBottom(hBoxforbutton);
        btsubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                if(textFieldFirstName.getText().isEmpty()
                        || textFieldMiddleName.getText().isEmpty()
                        || textFieldLastName.getText().isEmpty()
                        || textFieldForEmail.getText().isEmpty()
                        || textFieldForNumber.getText().isEmpty()){
                    errorAlert.setHeaderText("Name not valid");
                    errorAlert.setContentText("Please fill name field");
                    errorAlert.showAndWait();
                }
                if(!male.isSelected() ){
                    if(!female.isSelected()){
                        errorAlert.setHeaderText("Error : Gender");
                        errorAlert.setContentText("Please Fill Gender");
                        errorAlert.showAndWait();
                    }
                }
                if(cbday.getSelectionModel().isEmpty()){
                    if(cbMonth.getSelectionModel().isEmpty()){
                        if(cbYear.getSelectionModel().isEmpty()){
                            errorAlert.setHeaderText("Error : Date Of Birth");
                            errorAlert.setContentText("Please Fill date of birth");
                            errorAlert.showAndWait();
                        }
                    }
                }
                if(!CH.isSelected()){
                    if(!CE.isSelected()){
                        if(!CG.isSelected()){
                            errorAlert.setHeaderText("Error : Language ");
                            errorAlert.setContentText("Please fill languages");
                            errorAlert.showAndWait();
                        }
                    }
                }
                if(!textFieldFirstName.getText().matches(regex)){
                    if(!textFieldMiddleName.getText().matches(regex)){
                        if(!textFieldLastName.getText().matches(regex)){
                            errorAlert.setHeaderText("Error : Name");
                            errorAlert.setContentText("The Firsr,Last And Middle Name Must be Charecterr");
                            errorAlert.showAndWait();
                        }
                    }
                }
                if(!textFieldForEmail.getText().matches(emailRegex)){
                    errorAlert.setHeaderText("Error : Email");
                    errorAlert.setContentText("The Email must be like :- example@gmail.com");
                    errorAlert.showAndWait();
                }
                if(!textFieldForNumber.getText().matches(mRegx)){
                    if(textFieldForNumber.getText().length() < LIMIT){
                        errorAlert.setHeaderText("Error : Number");
                        errorAlert.setContentText("The Digits of Mobile No. must be 10");
                        errorAlert.showAndWait();
                    }
                }
                else{
                    Label lz = new Label("Welcome To Assignment2!");
                    lz.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25));

                    BorderPane pane1 = new BorderPane();
                    pane1.setCenter(lz);

                    Scene secondScene = new Scene(pane1, 600, 400);
                    Stage newWindow = new Stage();
                    newWindow.setTitle("Registration Form");
                    newWindow.setScene(secondScene);
                    newWindow.show();
                }
            }
        });
        btcancle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textFieldFirstName.clear();
                textFieldMiddleName.clear();
                textFieldLastName.clear();
                textFieldForEmail.clear();
                textFieldForNumber.clear();
                cbday.setValue("1");
                cbMonth.setValue("January");
                cbYear.setValue("2000");
                male.setSelected(false);
                female.setSelected(false);
                CH.setSelected(false);
                CE.setSelected(false);
                CG.setSelected(false);
            }
        });
        Scene scene = new Scene(borderPane,600,400);
        primaryStage.setTitle("Login.Com");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}