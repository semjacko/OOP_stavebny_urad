package gui;

import users.User;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import users.Financier;
import file.FileUsers;

public class Logon {
		
	public void setScene(Stage okno) {
		
		BorderPane bpane = new BorderPane();  
        
		GridPane grid = new GridPane();
		bpane.setCenter(grid);
	 	grid.setAlignment(Pos.CENTER);
	 	grid.setVgap(10);
	 	grid.setPadding(new Insets(0, 0, 0, 0));
	 	 	
	 	Label login = new Label("Login:");
	 	grid.add(login, 0, 0);
	 	TextField loginField = new TextField();
	 	loginField.setPromptText("Login");
	 	grid.add(loginField, 0, 1);
	 	
	 	Label heslo = new Label("Heslo:");
	 	grid.add(heslo, 0, 2);
	 	PasswordField hesloField = new PasswordField();
	 	hesloField.setPromptText("Heslo");
	 	grid.add(hesloField, 0, 3);
	 	
	 	Button prih = new Button("Prihl·siù");
	 	Button reg = new Button("Registrovaù");
	 	HBox btns = new HBox(25);
	 	btns.getChildren().add(reg);
	 	reg.setAlignment(Pos.BASELINE_LEFT);
	 	btns.getChildren().add(prih);
	 	grid.add(btns, 0, 6);
	 	
	 	Text actiontarget = new Text();
	    grid.add(actiontarget, 0, 7);
	    actiontarget.setId("actiontarget");
	     
	    prih.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent e) {
	        	String login = loginField.getText();
	        	String heslo = hesloField.getText();
	        	FileUsers fu = new FileUsers();
	        	int i = fu.zvalidujPrihlasenie(login, heslo);
	        	if(i != -1) {
	        		Main m = new Main();
	        		if(fu.getUsers().get(i) instanceof Financier)
	        			m.setScene(okno, (Financier)fu.getUsers().get(i));
	        		else 
	        			m.setScene(okno, fu.getUsers().get(i));	        	
	        	}
	        	else
	        		actiontarget.setText("Zadali ste nespr·vne heslo");
	        }
	        
	    });
	     
	    reg.setOnAction(e -> {             
	         Registration r = new Registration();
	         r.setScene(okno);
	     });
	 	
	 	//grid.setGridLinesVisible(true);
	    
	    Scene prihlasenie = new Scene(bpane, 390, 350);
	    prihlasenie.getStylesheets().add(Logon.class.getResource("styl.css").toExternalForm());
	    okno.setScene(prihlasenie);
     
	}

}
