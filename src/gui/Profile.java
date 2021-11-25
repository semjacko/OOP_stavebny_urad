package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import items.Building;
import items.RequestSalary;
import file.FileUsers;
import users.*;

public class Profile {
	
	public void setScene(Stage okno, Admin prihlasenyPouzivatel) {
		
		BorderPane bPane = new BorderPane();
		bPane.setPadding(new Insets(10, 15, 0, 15));
		GridPane grid = new GridPane();
		bPane.setCenter(grid);
		grid.setAlignment(Pos.CENTER_LEFT);
	    grid.setVgap(15);
	    grid.setHgap(15);
	    grid.setPadding(new Insets(20, 0, 25, 25));
	    
	    Label nadpis = new Label("Profil");
	    nadpis.setId("nadpis");
	    bPane.setTop(nadpis);
	    
	    Label meno = new Label("Meno:");
    	grid.add(meno, 0, 0);
    	Text menoT = new Text(prihlasenyPouzivatel.getName());
    	grid.add(menoT,1,0);
    	menoT.setId("textik");
    	
    	Label vek = new Label("Vek:");
    	grid.add(vek, 0, 1);
    	Text vekT = new Text(String.valueOf(prihlasenyPouzivatel.getAge()));
    	grid.add(vekT,1,1);
    	vekT.setId("textik");
    	
    	Label login = new Label("Login:");
    	grid.add(login, 0, 2);
    	Text loginT = new Text(prihlasenyPouzivatel.getLogin());
    	grid.add(loginT,1,2);
	    loginT.setId("textik");
	    
	    Label skusenosti = new Label("Sk˙senosti:");
    	grid.add(skusenosti, 0, 3);
    	Text skusenostiT = new Text(String.valueOf(prihlasenyPouzivatel.getExperiences()));
    	grid.add(skusenostiT,1,3);
	    skusenostiT.setId("textik");
	    
	    Label plat = new Label("Plat:");
    	grid.add(plat, 0, 4);
    	Text platT = new Text(String.valueOf(prihlasenyPouzivatel.getSalary()));
    	grid.add(platT,1,4);
	    platT.setId("textik");
	    
	    Button domovB = new Button("Domov");
	    Button platB = new Button("Poûiadaù o zv˝öenie platu");
	    TextField platField = new TextField();
	    HBox btns = new HBox(20);
	    btns.getChildren().addAll(domovB,platField,platB);
	    grid.add(btns, 0, 7, 3, 1);
	    
	    
	    	
	    domovB.setOnAction(new EventHandler<ActionEvent>() {             
	            @Override
	            public void handle(ActionEvent e) {
	            	Main m = new Main();
	            	m.setScene(okno, prihlasenyPouzivatel);
	            }
	        });
	    
	    platB.setOnAction(new EventHandler<ActionEvent>() {             
            @Override
            public void handle(ActionEvent e) {
            	try {
            		int newPlat = Integer.parseInt(String.valueOf(platField.getText()));
            		FileUsers fu = new FileUsers();
                	((Financier)fu.getUsers().get(0)).addRequest(new RequestSalary((Admin)prihlasenyPouzivatel, newPlat));
                	fu.write();
            	}
            	catch(NumberFormatException NFException) {
            		Alert error = new Alert(AlertType.ERROR);
            		error.setHeaderText("Error");
            		error.setContentText("Zadali ste nespr·vny ˙daj.");
            		error.setX(okno.getX() + 180);
            		error.setY(okno.getY() + 110);
            		error.showAndWait();
	                return;
            	}
            	
            	Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("éiadosù");
                alert.setContentText("éiadosù o zv˝öenie platu bola odoslan· na schv·lenie.");
                alert.setX(okno.getX() + 180);
                alert.setY(okno.getY() + 110);
                alert.showAndWait();
                
                setScene(okno, prihlasenyPouzivatel);
            }
        });
	    
	   //grid.setGridLinesVisible(true);

	   Scene profil = new Scene(bPane, 700, 450);
	   profil.getStylesheets().add(Profile.class.getResource("styl.css").toExternalForm());
	   okno.setScene(profil);
	   
	}
	
	
	
	public void setScene(Stage okno, Customer prihlasenyPouzivatel) {
		
		BorderPane bPane = new BorderPane();
		bPane.setPadding(new Insets(10, 15, 0, 15));
		GridPane grid = new GridPane();
		bPane.setCenter(grid);
		grid.setAlignment(Pos.CENTER);
	    grid.setVgap(15);
	    grid.setHgap(15);
	    grid.setPadding(new Insets(20, 0, 25, 25));
	    
	    Label nadpis = new Label("Profil");
	    nadpis.setId("nadpis");
	    bPane.setTop(nadpis);
	    
	    Label meno = new Label("Meno:");
    	grid.add(meno, 0, 0);
    	Text menoT = new Text(prihlasenyPouzivatel.getName());
    	grid.add(menoT,1,0);
    	menoT.setId("textik");
    	
    	Label vek = new Label("Vek:");
    	grid.add(vek, 0, 1);
    	Text vekT = new Text(String.valueOf(prihlasenyPouzivatel.getAge()));
    	grid.add(vekT,1,1);
    	vekT.setId("textik");
    	
    	Label login = new Label("Login:");
    	grid.add(login, 0, 2);
    	Text loginT = new Text(prihlasenyPouzivatel.getLogin());
    	grid.add(loginT,1,2);
	    loginT.setId("textik");
	    
	    Label stavby = new Label("Stavby:");
	    HBox sta = new HBox();
	    sta.getChildren().add(stavby);
	    sta.setAlignment(Pos.TOP_LEFT);
    	grid.add(sta, 0, 3);
    	
    	TreeItem<String> polozky = new TreeItem<>();
    	polozky.setExpanded(true);
    	for(Building b : (prihlasenyPouzivatel).getBuildings()) {
    		polozky.getChildren().add(vratPolozku(b));
    	}
    	
    	TreeView<String> treeView = new TreeView<String>(polozky);
    	treeView.setShowRoot(false);
    	treeView.setPrefWidth(400);
    	grid.add(treeView, 1, 3);
	    
	    Button domov = new Button("Domov");
	    HBox btns = new HBox(170);
	    btns.getChildren().add(domov);
	    grid.add(btns, 0, 7, 2, 1);
	    	
	    domov.setOnAction(new EventHandler<ActionEvent>() {             
	            @Override
	            public void handle(ActionEvent e) {
	            	Main m = new Main();
	            	m.setScene(okno, prihlasenyPouzivatel);
	            }
	        });
	    	
	   //grid.setGridLinesVisible(true);

	   Scene profil = new Scene(bPane, 700, 450);
	   profil.getStylesheets().add(Profile.class.getResource("styl.css").toExternalForm());
	   okno.setScene(profil);
	
	}
	
	private TreeItem<String> vratPolozku(Building b) {
		TreeItem<String> stavba = new TreeItem<String>(b.getName());
   	 	stavba.getChildren().addAll(
   	 			new TreeItem<String>(String.valueOf("rozloha: " + b.getArea())+ "m" + '\u00b2'),
   	 			new TreeItem<String>(String.valueOf("hodnota: " + b.getPrice()) + '\u20ac'),
   	 			new TreeItem<String>(String.valueOf("typ: " + b.getType()))
   	 	);
		return stavba;
	}

}
