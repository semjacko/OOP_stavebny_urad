package gui;

import java.io.IOException;
import java.util.ArrayList;

import users.*;
import file.FileUsers;
import items.LoginException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Registration{
	
	public void setScene(Stage okno) {
        
		BorderPane bPane = new BorderPane();
		bPane.setPadding(new Insets(10, 15, 0, 15));
        GridPane grid = new GridPane();
        bPane.setCenter(grid);
    	grid.setAlignment(Pos.CENTER);
    	grid.setVgap(15);
    	grid.setHgap(15);
    	grid.setPadding(new Insets(10, 0, 0, 0));
    	
    	Text scenetitle = new Text("Registr·cia");
    	scenetitle.setId("nadpis");
    	bPane.setTop(scenetitle);
    	
    	Label meno = new Label("Meno:");
    	grid.add(meno, 0, 0);
    	TextField menoField = new TextField();
    	grid.add(menoField, 1, 0);
    	
    	Label vek = new Label("Vek:");
    	grid.add(vek, 0, 1);
    	TextField vekField = new TextField();
    	grid.add(vekField, 1, 1);
    	
    	Label login = new Label("Login:");
    	grid.add(login, 0, 2);
    	TextField loginField = new TextField();
    	grid.add(loginField, 1, 2);
    	Text pouzivaSa = new Text();
    	grid.add(pouzivaSa, 2, 2);
    	pouzivaSa.setId("actiontarget");
    	
    	Label heslo = new Label("Heslo:");
    	grid.add(heslo, 0, 3);
    	PasswordField hesloField = new PasswordField();
    	grid.add(hesloField, 1, 3);
    	
    	Label typPouzivatela = new Label("Registrovaù ako:");
    	grid.add(typPouzivatela, 0, 4);
    	ChoiceBox<String> typChoice = new ChoiceBox<String>();
    	typChoice.getItems().addAll("UûÌvateæ", "Zamestnanec");
    	typChoice.setValue("UûÌvateæ");
    	grid.add(typChoice, 1, 4);
    	
    	Button spat = new Button("Sp‰ù");
    	Button reg = new Button("Registrovaù");
    	HBox btns = new HBox(170);
    	btns.getChildren().add(spat);
    	btns.getChildren().add(reg);
    	reg.setAlignment(Pos.BASELINE_RIGHT);
    	grid.add(btns, 0, 6, 2, 1);
    	
        
        reg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	try {
	            	FileUsers fu = new FileUsers();
	                String meno = menoField.getText();
	                String login = loginField.getText();
	                try {
	                	fu.zvalidujLogin(login);
	                }
	                catch(LoginException LException) {
	                	pouzivaSa.setText("pouûÌva sa");
	                	return;
	                }
	                String heslo = hesloField.getText();
	                
	                	int vek = Integer.parseInt(vekField.getText());
	                boolean boolZamestnanec = "Zamestnanec".equals(typChoice.getValue());
	                User u; 
	                if(boolZamestnanec)
	                	u = new Admin(meno, vek, login, heslo);
	                else
	                	u = new Customer(meno, vek, login, heslo);
	                fu.add(u);
	                Alert alert = new Alert(AlertType.INFORMATION);
	                alert.setHeaderText("Registr·cia");
	                alert.setContentText("Registr·cia bola ˙speön·.");
	                alert.setX(okno.getX() + 180);
	                alert.setY(okno.getY() + 110);
	                alert.showAndWait();
	                Logon l = new Logon();
	            	l.setScene(okno);
            	}
            	catch(NumberFormatException NFException) {
	            	Alert alert = new Alert(AlertType.ERROR);
	                alert.setHeaderText("Error");
	                alert.setContentText("Zadali ste nespr·vne ˙daje.");
	                alert.setX(okno.getX() + 180);
	                alert.setY(okno.getY() + 110);
	                alert.showAndWait();
	                return;
            	}
            }
        });
    	
    	spat.setOnAction(new EventHandler<ActionEvent>() {           
            @Override
            public void handle(ActionEvent e) {
            	Logon l = new Logon();
            	l.setScene(okno);
            }
        });
    	
    	//grid.setGridLinesVisible(true);

    	Scene registracia = new Scene(bPane, 700, 450);
    	registracia.getStylesheets().add(Registration.class.getResource("styl.css").toExternalForm());
    	okno.setScene(registracia);
    	
	}
		
}