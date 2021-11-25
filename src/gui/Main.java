package gui;

import java.util.ArrayList;

import file.FileUsers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import users.User;
import users.Customer;
import users.Financier;
import users.Admin;

public class Main {
	
	private int i;
			
	public void setScene(Stage okno, User prihlasenyPouzivatel) {
	          
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
	    grid.setVgap(20);
	    grid.setHgap(20);
	    grid.setPadding(new Insets(30, 0, 0, 20));
	    	
	    Button profil = new Button("Profil");
	    Button ziadost = new Button("éiadosù");
	    Button info = new Button("Inform·cie");
	    Button message = new Button("Spr·vy");
	    Button odhlasit = new Button("Odhl·siù");
	    VBox btns = new VBox(40);
	    btns.getChildren().addAll(profil, ziadost, info, message, odhlasit);
	    grid.add(btns, 2, 2, 1, 1);
	    
	    Image image = new Image("file:obr.png");
	    ImageView iv = new ImageView();
	    iv.setImage(image);
	    iv.setPreserveRatio(true);
	    iv.setFitHeight(200);
	    grid.add(iv, 9, 1, 1, 3);
	    	
	    odhlasit.setOnAction(new EventHandler<ActionEvent>() {           
	            @Override
	            public void handle(ActionEvent e) {
	            	Logon l = new Logon();
	            	l.setScene(okno);
	            }
	        });
	    
	    profil.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent e) {
	    		Profile p = new Profile();
	    		if(prihlasenyPouzivatel instanceof Customer)
	    			p.setScene(okno, (Customer)prihlasenyPouzivatel);
	    		else
	    			p.setScene(okno, (Admin)prihlasenyPouzivatel);
	    	}
	    });
	    
	    ziadost.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent e) {
	    		Demand d = new Demand();
	    		if(prihlasenyPouzivatel instanceof Customer)
	    			d.setScene(okno, (Customer)prihlasenyPouzivatel);
	    		else
	    			d.setScene(okno, (Admin)prihlasenyPouzivatel);
	    			
	    	}
	    });
	    
	    info.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent e) {
	    		Informations i = new Informations();
	    		i.setScene(okno, prihlasenyPouzivatel);
	    	}
	    });
	    
	    message.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent e) {
	    		Chat c = new Chat();
	    		if(prihlasenyPouzivatel instanceof Customer)
	    			c.setScene(okno, (Customer)prihlasenyPouzivatel);
	    		else
	    			c.setScene(okno, (Admin)prihlasenyPouzivatel);
	    	}
	    });
	    
	    
	    	
	    //grid.setGridLinesVisible(true);

	    Scene scene = new Scene(grid, 700, 450);
	    scene.getStylesheets().add(Main.class.getResource("styl.css").toExternalForm());
	    okno.setScene(scene);
	    	
		}
	
public void setScene(Stage okno, Financier prihlasenyPouzivatel) {
		
		BorderPane bPane = new BorderPane();
		bPane.setPadding(new Insets(0, 30, 0, 30));
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
	    grid.setVgap(20);
	    grid.setHgap(20);
	    bPane.setRight(grid);
	    	
	    Button ziadostP = new Button("éiadosù Platy");
	    Button ziadostS = new Button("éiadosù Stavby");
	    Button info = new Button("Inform·cie");
	    Button odhlasit = new Button("Odhl·siù");
	    VBox btns = new VBox(40);
	    btns.setAlignment(Pos.CENTER_LEFT);
	    btns.getChildren().addAll(ziadostP,ziadostS, info, odhlasit);
	    bPane.setLeft(btns);
	    
	    Label hladat = new Label("Hæadaù:");
    	grid.add(hladat, 0, 0);
    	ChoiceBox<String> adminChoice = new ChoiceBox<String>();
    	FileUsers fu = new FileUsers();
    	ArrayList<String> admins = fu.getAdminLogins();
	    adminChoice.getItems().addAll(admins);
	    if(admins.size() > 0)
	    	adminChoice.setValue(admins.get(0));
	    grid.add(adminChoice, 1, 0);
    	Button zobrazitB = new Button("Zobraziù");
    	grid.add(zobrazitB, 2, 0);
    	
    	Label historia = new Label("HistÛria:");
    	HBox hist = new HBox();
    	hist.setAlignment(Pos.TOP_LEFT);
    	hist.getChildren().add(historia);
    	grid.add(hist, 0, 1);
	    TextArea histT = new TextArea();
	    histT.setPrefHeight(200);   
	    histT.setPrefWidth(410);
	    histT.setEditable(false);
	    grid.add(histT, 1, 1, 3, 1);
	    
	    Label novaSprava = new Label("Nov·:");
    	grid.add(novaSprava, 0, 2);
	    TextArea novaT = new TextArea();
	    novaT.setPrefHeight(50);
	    novaT.setPrefWidth(410);
	    novaT.setEditable(false);
	    grid.add(novaT, 1, 2, 3, 1);
	    
	    Button odoslatB = new Button("Odoslat");
    	grid.add(odoslatB, 3, 3);
	        	
	    odhlasit.setOnAction(new EventHandler<ActionEvent>() {           
	            @Override
	            public void handle(ActionEvent e) {
	            	Logon l = new Logon();
	            	l.setScene(okno);
	            }
	        });
	    
	    ziadostS.setOnAction(new EventHandler<ActionEvent>() {           
            @Override
            public void handle(ActionEvent e) {
            	ZiadostFin z = new ZiadostFin();
            	z.ziadostS(okno, prihlasenyPouzivatel);
            }
        });
	    
	    ziadostP.setOnAction(new EventHandler<ActionEvent>() {           
            @Override
            public void handle(ActionEvent e) {
            	ZiadostFin z = new ZiadostFin();
            	z.ziadostP(okno, prihlasenyPouzivatel);
            }
        });
	    
	    info.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent e) {
	    		Informations i = new Informations();
	    		i.setScene(okno, prihlasenyPouzivatel);
	    	}
	    });	   
	    
	    zobrazitB.setOnAction(new EventHandler<ActionEvent>() {             
	        @Override
	        public void handle(ActionEvent e) {
	        	
	        	String login = adminChoice.getValue();
	        	i = fu.indexOfLogin(login);
	        	histT.setText(((Admin)fu.getUsers().get(i)).getMessage());
	        	novaT.setEditable(true);
	        }
	    });
	    
	    odoslatB.setOnAction(new EventHandler<ActionEvent>() {             
            @Override
            public void handle(ActionEvent e) {
            	String s = novaT.getText();
            	((Admin)fu.getUsers().get(i)).setMessage(s, prihlasenyPouzivatel); 
            	fu.write();
            	histT.setText(fu.getUsers().get(i).getMessage());
            	novaT.setText("");
            }
        });
	    	
	    //grid.setGridLinesVisible(true);

	    Scene scene = new Scene(bPane, 700, 450);
	    scene.getStylesheets().add(Main.class.getResource("styl.css").toExternalForm());
	    okno.setScene(scene);
	    
	}

}
