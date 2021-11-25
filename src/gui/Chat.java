package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import users.*;
import java.util.ArrayList;
import items.Message;
import file.FileUsers;

public class Chat {
	
	private int i;
	
	public void setScene(Stage okno, Admin prihlasenyPouzivatel) {
		
		BorderPane bPane = new BorderPane();
		bPane.setPadding(new Insets(10, 10, 0, 10));
		GridPane grid = new GridPane();
		bPane.setCenter(grid);
		grid.setAlignment(Pos.CENTER);
	    grid.setVgap(10);
	    grid.setHgap(15);
	    
	    Text scenetitle = new Text("Spr·vy");
    	scenetitle.setId("nadpis");
    	bPane.setTop(scenetitle);
    	
    	Label hladat = new Label("Hæadaù:");
    	grid.add(hladat, 0, 0);
    	
    	ChoiceBox<String> userChoice = new ChoiceBox<String>();
    	FileUsers fu = new FileUsers();
    	ArrayList<String> users = fu.getCustomerLogins();
    	userChoice.getItems().add("financnik");
	    userChoice.getItems().addAll(users);
	    userChoice.setValue("financnik");
	    grid.add(userChoice, 1, 0);
    	Button hladatB = new Button("Zobraziù");
    	grid.add(hladatB, 2, 0);
    	Button financnik = new Button("FinanËnÌk");
    	grid.add(financnik, 3, 0);
    	
    	Label historia = new Label("HistÛria:");
    	HBox hist = new HBox();
    	hist.setAlignment(Pos.TOP_LEFT);
    	hist.getChildren().add(historia);
    	grid.add(hist, 0, 1);
	    TextArea histT = new TextArea();
	    histT.setPrefHeight(200);   
	    histT.setMinWidth(410);
	    histT.setEditable(false);
	    grid.add(histT, 1, 1, 2, 1);
	    
	    Label novaSprava = new Label("Nov·:");
    	grid.add(novaSprava, 0, 2);
	    TextArea novaT = new TextArea();
	    novaT.setPrefHeight(50);
	    novaT.setMinWidth(410);
	    grid.add(novaT, 1, 2, 2, 1);
	    
	    Button domov = new Button("Domov");
	    Button odoslat = new Button("Odoslaù");
	    HBox btns = new HBox(200);
	    btns.getChildren().addAll(domov,odoslat);
	    grid.add(btns, 0, 3, 3, 1);
	    
	    	
	    domov.setOnAction(new EventHandler<ActionEvent>() {             
	            @Override
	            public void handle(ActionEvent e) {
	            	Main m = new Main();
	            	m.setScene(okno, prihlasenyPouzivatel);
	            }
	    });
	    
	   financnik.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	userChoice.setValue("financnik");
            	i = fu.indexOfLogin(prihlasenyPouzivatel.getLogin());
        		histT.setText(fu.getUsers().get(i).getMessage());
            }
        });
	    
	    hladatB.setOnAction(new EventHandler<ActionEvent>() {             
        @Override
        public void handle(ActionEvent e) {
        	String login = userChoice.getValue();
        	if(login.equals("financnik")) 
        		i = fu.indexOfLogin(prihlasenyPouzivatel.getLogin());
        	else
        		i = fu.indexOfLogin(login);
        	histT.setText(fu.getUsers().get(i).getMessage());
        }
    });
	    
	    odoslat.setOnAction(new EventHandler<ActionEvent>() {             
            @Override
            public void handle(ActionEvent e) {
            	String s = novaT.getText();
            	if(i == fu.indexOfLogin(prihlasenyPouzivatel.getLogin()))
                	((Admin)fu.getUsers().get(i)).setMessage(s);
            	else
            		((Customer)fu.getUsers().get(i)).setMessage(s, prihlasenyPouzivatel); 
            	fu.write();
            	histT.setText(fu.getUsers().get(i).getMessage());
            	novaT.setText("");
            }
        });
	    	
	   //grid.setGridLinesVisible(true);

	   Scene scene = new Scene(bPane, 700, 450);
	   scene.getStylesheets().add(Chat.class.getResource("styl.css").toExternalForm());
	   okno.setScene(scene);
	   
	}

	
	public void setScene(Stage okno, Customer prihlasenyPouzivatel) {
		
		BorderPane bPane = new BorderPane();
		bPane.setPadding(new Insets(10, 15, 0, 15));
		GridPane grid = new GridPane();
		bPane.setCenter(grid);
		grid.setAlignment(Pos.CENTER);
	    grid.setVgap(10);
	    grid.setHgap(15);
	    grid.setPadding(new Insets(0, 10, 15, 10));
	    
	    Text scenetitle = new Text("Spr·vy");
    	scenetitle.setId("nadpis");
    	bPane.setTop(scenetitle);
    	
    	Label historia = new Label("HistÛria:");
    	HBox hist = new HBox();
    	hist.setAlignment(Pos.TOP_LEFT);
    	hist.getChildren().add(historia);
    	grid.add(hist, 0, 1);
	    TextArea ta = new TextArea(prihlasenyPouzivatel.getMessage());
	    ta.setPrefHeight(200);   
	    ta.setPrefWidth(410);
	    ta.setEditable(false);
	    grid.add(ta, 1, 1);
	    
	    Label novaSprava = new Label("Nov·:");
    	grid.add(novaSprava, 0, 2);
	    TextArea novaT = new TextArea();
	    novaT.setPrefHeight(50);
	    novaT.setPrefWidth(410);
	    grid.add(novaT, 1, 2);
	    
	    Button domov = new Button("Domov");
	    Button odoslat = new Button("Odoslaù");
	    HBox btns = new HBox(200);
	    btns.getChildren().addAll(domov,odoslat);
	    grid.add(btns, 0, 3, 2, 1);
	    	
	    domov.setOnAction(new EventHandler<ActionEvent>() {             
	            @Override
	            public void handle(ActionEvent e) {
	            	Main m = new Main();
	            	m.setScene(okno, prihlasenyPouzivatel);
	            }
	        });
	    
	    odoslat.setOnAction(new EventHandler<ActionEvent>() {             
            @Override
            public void handle(ActionEvent e) {
            	String s = novaT.getText();
            	prihlasenyPouzivatel.setMessage(s); 
            	FileUsers fu = new FileUsers();
            	i = fu.indexOfLogin(prihlasenyPouzivatel.getLogin());
            	((Customer)fu.getUsers().get(i)).setMessage(s);
            	fu.write();													//TOTO IDE ?
            	ta.setText(fu.getUsers().get(i).getMessage());
            	novaT.setText("");
            }
        });
	    	
	   //grid.setGridLinesVisible(true);

	   Scene sprava = new Scene(bPane, 700, 450);
	   sprava.getStylesheets().add(Chat.class.getResource("styl.css").toExternalForm());
	   okno.setScene(sprava);
	
	}

}
