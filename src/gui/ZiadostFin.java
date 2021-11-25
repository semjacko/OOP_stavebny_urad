package gui;

import java.util.ArrayList;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import users.*;
import items.Building;
import file.*;
import items.RequestBuilding;
import items.RequestSalary;

public class ZiadostFin {
	
	private static int  i;
	
	public void ziadostS(Stage okno, Financier prihlasenyPouzivatel) {
		
		BorderPane bPane = new BorderPane();
		bPane.setPadding(new Insets(25, 0, 20, 25));
		GridPane grid = new GridPane();
		bPane.setCenter(grid);
		grid.setAlignment(Pos.CENTER);
	    grid.setVgap(15);
	    grid.setHgap(15);
	    grid.setPadding(new Insets(5, 0, 25, 0));
	    FileUsers fu = new FileUsers();	
	    grid.add(vypisZiadostS(prihlasenyPouzivatel.getRequestsB()), 0, 0, 2 ,1);
	    
	    Label newOhodnotenie = new Label("Ohodnotenie:");
	    grid.add(newOhodnotenie, 0, 1);
	    TextField newOhodnotenieField = new TextField();
	    newOhodnotenieField.setMaxWidth(110);
	    grid.add(newOhodnotenieField, 1, 1);
	    if(prihlasenyPouzivatel.getRequestsB().size() == 0 || ! prihlasenyPouzivatel.getRequestsB().get(i).isZiadostOhodnotenie()) {
	    	newOhodnotenieField.setText("0");
	    	newOhodnotenieField.setEditable(false);
	    }
	    Label newFond = new Label("Fond:");
	    grid.add(newFond, 0, 2);
	    TextField newFondField = new TextField();
	    newFondField.setMaxWidth(110);
	    grid.add(newFondField, 1, 2);
	    if(prihlasenyPouzivatel.getRequestsB().size() == 0 || ! prihlasenyPouzivatel.getRequestsB().get(i).isZiadostFond()) {
	    	newFondField.setText("0");
	    	newFondField.setEditable(false);
	    }
	    
	    Button pred = new Button("Pred");
	    Button dalsia = new Button("œalöia");
	    Button potvrdit = new Button("Potvrdiù");
	    HBox btns = new HBox(40);
	    btns.getChildren().addAll(pred, potvrdit, dalsia);
	    grid.add(btns, 0, 3, 2, 1);  
	    
	    Button domov = new Button("Domov");
	    bPane.setBottom(domov);
	    
	    domov.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent e) {
            	Main m = new Main();
            	m.setScene(okno, prihlasenyPouzivatel);
            }
        });
	    
	    pred.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent e) {
            	i--; 
            	ziadostS(okno, prihlasenyPouzivatel);
            }
        });
	    
	    dalsia.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent e) {
            	i++; 
            	ziadostS(okno, prihlasenyPouzivatel);
            }
        });
	    
	    potvrdit.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent e) {
            	Alert alert = new Alert(AlertType.CONFIRMATION);
            	alert.setHeaderText("Potvrdiù");
            	alert.setContentText("UrËite chcete potvrdiù t˙to ûiadosù?");
            	alert.setX(okno.getX() + 180);
                alert.setY(okno.getY() + 110);
            	Optional<ButtonType> result = alert.showAndWait();
            	if (result.get() == ButtonType.OK && prihlasenyPouzivatel.getRequestsB().size() > 0){
            		try {
	            		int fond = Integer.parseInt(newFondField.getText());
	                	int hodnota = Integer.parseInt(newOhodnotenieField.getText());
	            		Building building = prihlasenyPouzivatel.getRequestsB().get(i).getBuilding();
	                	building.setFond(fond);
	                	building.setPrice(hodnota);
	                	int index = fu.indexOfLogin(prihlasenyPouzivatel.getRequestsB().get(i).getCustomer().getLogin());
	                	((Customer)fu.getUsers().get(index)).addBuilding(building); 
	                	((Financier)fu.getUsers().get(0)).removeRequestB(i);
	                	fu.write();	
            		}
            		catch(NumberFormatException NFException) {
            			Alert error = new Alert(AlertType.ERROR);
            			error.setHeaderText("ERROR");
            			error.setContentText("Zadali ste nespr·vne ˙daje.");
            			error.setX(okno.getX() + 180);
            			error.setY(okno.getY() + 110);
            			error.showAndWait();
    	                return;
            		}
            	} 
            	else
            		return;
            	
            	ziadostS(okno, (Financier)fu.getUsers().get(0));
            }
        });
	    
	    Scene hlavnaFin = new Scene(bPane, 700, 450);
	    hlavnaFin.getStylesheets().add(ZiadostFin.class.getResource("styl.css").toExternalForm());
	    okno.setScene(hlavnaFin);
	
	}
	
	public void ziadostP(Stage okno, Financier prihlasenyPouzivatel) {
		
		BorderPane bPane = new BorderPane();
		bPane.setPadding(new Insets(25, 0, 20, 25));
		GridPane grid = new GridPane();
		bPane.setCenter(grid);
		grid.setAlignment(Pos.CENTER);
	    grid.setVgap(15);
	    grid.setHgap(15);
	    grid.setPadding(new Insets(5, 0, 25, 0));
	    FileUsers fu = new FileUsers();
	    grid.add(vypisZiadostP(prihlasenyPouzivatel.getRequestsS()), 0, 0, 2, 1);
	    
	    Label newPlat = new Label("Nov˝ plat:");
	    grid.add(newPlat, 0, 1);
	    TextField newPlatField = new TextField();
	    newPlatField.setMaxWidth(110);
	    grid.add(newPlatField, 1, 1);
	    
	    Button pred = new Button("Pred");
	    Button dalsia = new Button("œalöia");
	    Button potvrdit = new Button("Potvrdiù");
	    Button zamietnut = new Button("Zamietnuù");
	    HBox btns = new HBox(40);
	    btns.getChildren().addAll(pred, zamietnut, potvrdit, dalsia);
	    grid.add(btns, 0, 2, 2, 1);  
	    
	    Button domov = new Button("Domov");
	    bPane.setBottom(domov);
	    
	    domov.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent e) {
            	Main m = new Main();
            	m.setScene(okno, prihlasenyPouzivatel);
            }
        });
	    
	    pred.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent e) {
            	i--; 
            	ziadostP(okno, prihlasenyPouzivatel);
            }
        });
	    
	    dalsia.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent e) {
            	i++; 
            	ziadostP(okno, prihlasenyPouzivatel);
            }
        });
	    
	    potvrdit.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent e) {
            	Alert alert = new Alert(AlertType.CONFIRMATION);
            	alert.setHeaderText("Potvrdiù");
            	alert.setContentText("UrËite chcete potvrdiù t˙to ûiadosù?");
            	alert.setX(okno.getX() + 180);
                alert.setY(okno.getY() + 110);
            	Optional<ButtonType> result = alert.showAndWait();
            	if (result.get() == ButtonType.OK  && prihlasenyPouzivatel.getRequestsS().size() > 0){
            		try {
	            		int newPlat = Integer.parseInt(newPlatField.getText());
	            		prihlasenyPouzivatel.nastavPlat((Admin)fu.getUsers().get(fu.indexOfLogin(prihlasenyPouzivatel.getRequestsS().get(i).getAdmin().getLogin())), newPlat);
	                	((Financier)fu.getUsers().get(i)).removeRequestS(i);		
	                	fu.write();
            		}
            		catch(NumberFormatException NFException) {
            			Alert error = new Alert(AlertType.ERROR);
            			error.setHeaderText("ERROR");
            			error.setContentText("Zadali ste nespr·vny ˙daj.");
            			error.setX(okno.getX() + 180);
            			error.setY(okno.getY() + 110);
            			error.showAndWait();
    	                return;
            		}
            	} 
            	else
            		return;
            	
            	ziadostP(okno, (Financier)fu.getUsers().get(0));
            }
        });
	    
	    zamietnut.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent e) {
            	Alert alert = new Alert(AlertType.CONFIRMATION);
            	alert.setHeaderText("Zamietnuù");
            	alert.setContentText("UrËite chcete zamietnuù t˙to ûiadosù?");
            	alert.setX(okno.getX() + 180);
                alert.setY(okno.getY() + 110);
            	Optional<ButtonType> result = alert.showAndWait();
            	if (result.get() == ButtonType.OK  && prihlasenyPouzivatel.getRequestsB().size() > 0){
            		((Financier)fu.getUsers().get(i)).removeRequestS(i);		
                	fu.write();
            	}
            	else
            		return;
            	
            	ziadostP(okno, (Financier)fu.getUsers().get(0));
            }
        });
	    
	    Scene hlavnaFin = new Scene(bPane, 700, 450);
	    hlavnaFin.getStylesheets().add(ZiadostFin.class.getResource("styl.css").toExternalForm());
	    okno.setScene(hlavnaFin);
	
	}
	
	
	
	
	private GridPane vypisZiadostP(ArrayList<RequestSalary> ziadostiP) {
		
		if(ziadostiP.size() == 0)
			return new GridPane();
		if(i >= ziadostiP.size())
			i = 0;
		if(i < 0)
			i = ziadostiP.size() - 1;
		
		RequestSalary rs = ziadostiP.get(i);
		Label meno = new Label("Meno:");
		Text menoT = new Text(rs.getAdmin().getName());
		menoT.setId("textik");
		Label vek = new Label("Vek:");
		Text vekT = new Text(String.valueOf(rs.getAdmin().getAge()));
		vekT.setId("textik");
		Label login = new Label("Login:");
		Text loginT = new Text(rs.getAdmin().getLogin());
		loginT.setId("textik");
		Label plat = new Label("Plat:");
		Text platT = new Text(String.valueOf(rs.getAdmin().getSalary()));
		platT.setId("textik");
		Label newPlat = new Label("Poûadovan˝:");
		Text newPlatT = new Text(String.valueOf(rs.getNewSalary()));
		newPlatT.setId("textik");
		Label skusenosti = new Label("Sk˙senosti:");
		Text skusenostiT = new Text(String.valueOf(rs.getAdmin().getExperiences()));
		skusenostiT.setId("textik");
		
		
		GridPane g = new GridPane();
		g.setHgap(5);
		g.addColumn(0, meno,vek,login,skusenosti,plat,newPlat);
		g.addColumn(1, menoT,vekT,loginT,skusenostiT,platT,newPlatT);
		
		return g;
		
	}
	
	private GridPane vypisZiadostS(ArrayList<RequestBuilding> ziadostiS) {
		
		if(ziadostiS.size() == 0)
			return new GridPane();
		if(i >= ziadostiS.size())
			i = 0;
		if(i < 0)
			i = ziadostiS.size() - 1;
		
		RequestBuilding z = ziadostiS.get(i);
		Label fond = new Label("Fond:");
		Text fondT = new Text(String.valueOf(z.getBuilding().getFond()));
		fondT.setId("textik");
		Label hodnota = new Label("Hodnota:");
		Text hodnotaT = new Text(String.valueOf(z.getBuilding().getPrice()));
		hodnotaT.setId("textik");
		Label nazov = new Label("Nazov:");
		Text nazovT = new Text(z.getBuilding().getName());
		nazovT.setId("textik");
		Label rozloha = new Label("Rozloha:");
		Text rozlohaT = new Text(String.valueOf(z.getBuilding().getArea()));
		rozlohaT.setId("textik");
		Label typ = new Label("Typ:");
		Text typT = new Text(String.valueOf(z.getBuilding().getType()));
		typT.setId("textik");
		Label vyrub = new Label("Vyrub:");
		Text vyrubT = new Text(String.valueOf(z.getBuilding().getForest()));
		vyrubT.setId("textik");
		
		GridPane g = new GridPane();
		g.setHgap(50);
		g.addColumn(0, fond,hodnota,nazov,rozloha,typ,vyrub);
		g.addColumn(1, fondT,hodnotaT,nazovT,rozlohaT,typT,vyrubT);
		return g;
		
	}

}
