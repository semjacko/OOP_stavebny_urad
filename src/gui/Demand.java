package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import items.Building;
import items.Request;
import items.RequestBuilding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import users.*;
import file.*;

public class Demand {
	
	private static int i;
	
	public void setScene(Stage okno, Admin prihlasenyPouzivatel) {
		
		BorderPane bPane = new BorderPane();
		bPane.setPadding(new Insets(25, 0, 20, 25));
		GridPane grid = new GridPane();
		bPane.setCenter(grid);
		grid.setAlignment(Pos.CENTER);
	    grid.setVgap(15);
	    grid.setHgap(15);
	    grid.setPadding(new Insets(5, 0, 25, 0));
	    
	    FileRequests fr = new FileRequests();
	    FileUsers fu = new FileUsers();
	    grid.add(vypisZiadost(fr.getBuildingRequests()), 0, 0);
	    
	    Button pred = new Button("Pred");
	    Button dalsia = new Button("œalöia");
	    Button potvrdit = new Button("Schv·liù");
	    Button zamietnut = new Button("Zamietnuù");
	    Button sprava = new Button("Spr·va");
	    Button financne = new Button("FinanËnÈ");
	    HBox btns = new HBox(40);
	    btns.getChildren().addAll(pred, zamietnut, sprava, potvrdit, dalsia, financne);
	    grid.add(btns, 0, 1);  
	    
	    Button domov = new Button("Domov");
	    bPane.setBottom(domov);
	    
	    
	    pred.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent e) {
            	i--; 
            	setScene(okno, prihlasenyPouzivatel);
            }
        });
	    
	    dalsia.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent e) {
            	i++; 
            	setScene(okno, prihlasenyPouzivatel);
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
            	if (result.get() == ButtonType.OK && fr.getBuildingRequests().size() > 0){
            		fr.removeBuildingRequest(i);
                 	((Admin)fu.getUsers().get(fu.indexOfLogin(prihlasenyPouzivatel.getLogin()))).addExperiences();
                	fu.write();
            	}
            	else
            		return;
            	
            	setScene(okno, (Admin)fu.getUsers().get(fu.indexOfLogin(prihlasenyPouzivatel.getLogin())));
            }
        });
	    
	    sprava.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent e) {
	    		Chat c = new Chat();
	    		c.setScene(okno, prihlasenyPouzivatel);
	    	}
	    });
	    
	    potvrdit.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent e) {           	
            	Alert alert = new Alert(AlertType.CONFIRMATION);
            	alert.setHeaderText("Schv·liù");
            	alert.setContentText("UrËite chcete schv·liù t˙to ûiadosù?");
            	alert.setX(okno.getX() + 180);
                alert.setY(okno.getY() + 110);
            	Optional<ButtonType> result = alert.showAndWait();
            	if (result.get() == ButtonType.OK && fr.getBuildingRequests().size() > 0){
            		Building building = fr.getBuildingRequests().get(i).getBuilding();
                	int index = fu.indexOfLogin(fr.getBuildingRequests().get(i).getCustomer().getLogin());
                	((Customer)fu.getUsers().get(index)).addBuilding(building);
                	((Admin)fu.getUsers().get(fu.indexOfLogin(prihlasenyPouzivatel.getLogin()))).addExperiences();
                	fu.write();
                	fr.removeBuildingRequest(i);
            	} 
            	else
            		return;
            	
            	setScene(okno, (Admin)fu.getUsers().get(fu.indexOfLogin(prihlasenyPouzivatel.getLogin())));
            }
        });
	    
	    domov.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent e) {
            	Main m = new Main();
            	m.setScene(okno, prihlasenyPouzivatel);
            }
        });
	    
	    financne.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent e) {
            	if(fr.getBuildingRequests().size() == 0)
            		return;
            	((Financier)fu.getUsers().get(0)).addRequest(fr.getBuildingRequests().get(i));
            	((Admin)fu.getUsers().get(fu.indexOfLogin(prihlasenyPouzivatel.getLogin()))).addExperiences();
            	fu.write();
            	fr.removeBuildingRequest(i);
            	
            	Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("FinanËnÈ");
                alert.setContentText("éiadosù bola odoslan· na finanËnÈ oddelenie.");
                alert.setX(okno.getX() + 180);
                alert.setY(okno.getY() + 110);
                alert.showAndWait();
                
            	setScene(okno, (Admin)fu.getUsers().get(fu.indexOfLogin(prihlasenyPouzivatel.getLogin())));
            }
        });
	    	
	    //grid.setGridLinesVisible(true);

	    Scene ziadanie = new Scene(bPane, 700, 450);
	    ziadanie.getStylesheets().add(Demand.class.getResource("styl.css").toExternalForm());
	    okno.setScene(ziadanie);
	    	
	}
	
	
	public void setScene(Stage okno, Customer prihlasenyPouzivatel) {
		
		BorderPane bpane = new BorderPane();
		bpane.setPadding(new Insets(25, 0, 0, 25));
		GridPane grid = new GridPane();
		bpane.setCenter(grid);
		grid.setAlignment(Pos.CENTER);
	    grid.setVgap(15);
	    grid.setHgap(15);
	    grid.setPadding(new Insets(5, 0, 25, 0));
	    
	    Text scenetitle = new Text("éiadosù");
	    scenetitle.setId("nadpis");
	    bpane.setTop(scenetitle);
	    
	    Label nazov = new Label("N·zov:");
	    grid.add(nazov, 0, 0);
	    TextField nazovField = new TextField();
	    grid.add(nazovField, 1, 0);

	    Label vyrLes = new Label("V˝rub lesa:");
	    grid.add(vyrLes, 0, 1);
	    TextField vyrLesField = new TextField();
	    grid.add(vyrLesField, 1, 1);
	    Label metreStvorcove1 = new Label("m" + '\u00b2');
	    grid.add(metreStvorcove1, 2, 1);
	       
	    Label rozloha = new Label("Rozloha stavby:");
	    grid.add(rozloha, 0, 2);
	    TextField rozlohaField = new TextField();
	    grid.add(rozlohaField, 1, 2);
	    Label metreStvorcove3 = new Label("m" + '\u00b2');
	    grid.add(metreStvorcove3, 2, 2);

	    Label typBudovy = new Label("Typ budovy:");
	    grid.add(typBudovy, 0, 3);
	    ChoiceBox<String> typChoice = new ChoiceBox<String>();
	    typChoice.getItems().addAll("Obytn·", "Priemyseln·", "Verejn·", "Kult˙rna");
	    typChoice.setValue("Obytn·");
	    grid.add(typChoice, 1, 3);
	    
	    Label ohodnotenie = new Label("FinanËne ohodnotiù:");
	    grid.add(ohodnotenie, 0, 4);
	    ToggleGroup groupO = new ToggleGroup();
	    RadioButton rbAnoO = new RadioButton("¡no");
	    rbAnoO.setToggleGroup(groupO);
	    rbAnoO.setSelected(true);
	    RadioButton rbNieO = new RadioButton("Nie") ;
	    rbNieO.setToggleGroup(groupO);
	    HBox vyberOhodnotenia = new HBox(20);
	    vyberOhodnotenia.getChildren().addAll(rbAnoO,rbNieO);
	    grid.add(vyberOhodnotenia, 1 ,4);	
	    
	    Label fondovanie = new Label("Poûiadaù o fond:");
	    grid.add(fondovanie, 0, 5);
	    ToggleGroup groupF = new ToggleGroup();
	    RadioButton rbAnoF = new RadioButton("¡no");
	    rbAnoF.setToggleGroup(groupF);
	    rbAnoF.setSelected(true);
	    RadioButton rbNieF = new RadioButton("Nie") ;
	    rbNieF.setToggleGroup(groupF);
	    HBox vyberFondu = new HBox(20);
	    vyberFondu.getChildren().addAll(rbAnoF,rbNieF);
	    grid.add(vyberFondu, 1 ,5);	
	    
	    Button domov = new Button("Domov");
	    Button podat = new Button("Podaù");
	    HBox btns = new HBox(170);
	    btns.getChildren().addAll(domov, podat);
	    grid.add(btns, 0, 8, 2, 1);
	    	
	    domov.setOnAction(new EventHandler<ActionEvent>() {            
	            @Override
	            public void handle(ActionEvent e) {
	            	Main m = new Main();
	            	m.setScene(okno, prihlasenyPouzivatel);
	            }
	        });
	    
	    podat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	try {
	            	String nazov = nazovField.getText();
	            	String typ = typChoice.getValue();            
	                int rozloha = Integer.parseInt(rozlohaField.getText());
	                int vyrLes = Integer.parseInt(vyrLesField.getText());
	                boolean isFond = rbAnoF.isSelected();
	                boolean isOhodnotenie = rbAnoO.isSelected();
	                
	                RequestBuilding newRequest = new RequestBuilding(new Building(nazov,typ,rozloha,vyrLes, prihlasenyPouzivatel.getLogin()),prihlasenyPouzivatel,isFond,isOhodnotenie);
	                FileRequests fr = new FileRequests();
	                fr.add(newRequest);
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
	            
                
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("éiadosù");
                alert.setContentText("éiadosù bola odoslan· na schv·lenie.");
                alert.setX(okno.getX() + 180);
                alert.setY(okno.getY() + 110);
                alert.showAndWait();
                
                Main m = new Main();
            	m.setScene(okno, prihlasenyPouzivatel);
            }
        });
	    	
	   //grid.setGridLinesVisible(true);

	   Scene ziadanie = new Scene(bpane, 700, 450);
	   ziadanie.getStylesheets().add(Demand.class.getResource("styl.css").toExternalForm());
	   okno.setScene(ziadanie);
	}

	
	

	private GridPane vypisZiadost(ArrayList<RequestBuilding> ziadosti) {
		
		if(ziadosti.size() == 0)
			return new GridPane();
		if(i >= ziadosti.size())
			i = 0;
		if(i < 0)
			i = ziadosti.size() - 1;
		RequestBuilding rb = ziadosti.get(i);
		Label meno = new Label("Meno:");
		Text menoT = new Text(rb.getCustomer().getName());
		menoT.setId("textik");
		Label vek = new Label("Vek:");
		Text vekT = new Text(String.valueOf(rb.getCustomer().getAge()));
		vekT.setId("textik");
		Label login = new Label("Login:");
		Text loginT = new Text(rb.getCustomer().getLogin());
		loginT.setId("textik");
		Label fond = new Label("Fond:");
		Text fondT = new Text(String.valueOf(rb.getBuilding().getFond()));
		fondT.setId("textik");
		Label hodnota = new Label("Hodnota:");
		Text hodnotaT = new Text(String.valueOf(rb.getBuilding().getPrice()));
		hodnotaT.setId("textik");
		Label nazov = new Label("Nazov:");
		Text nazovT = new Text(rb.getBuilding().getName());
		nazovT.setId("textik");
		Label rozloha = new Label("Rozloha:");
		Text rozlohaT = new Text(String.valueOf(rb.getBuilding().getArea()));
		rozlohaT.setId("textik");
		Label typ = new Label("Typ:");
		Text typT = new Text(String.valueOf(rb.getBuilding().getType()));
		typT.setId("textik");
		Label vyrub = new Label("Vyrub:");
		Text vyrubT = new Text(String.valueOf(rb.getBuilding().getForest()));
		vyrubT.setId("textik");
		Label ziadostFond = new Label("Ziadost o fond:");
		String s;
		if(rb.isZiadostFond())
			s = "·no";
		else 
			s = "nie";
		Text ziadostFondT = new Text(s);
		ziadostFondT.setId("textik");
		Label ziadostOhodnotenie = new Label("Ziadost o ohodnotenie:");
		if(rb.isZiadostOhodnotenie())
			s = "·no";
		else 
			s = "nie";
		Text ziadostOhodnotenieT = new Text(s);
		ziadostOhodnotenieT.setId("textik");
		
		GridPane g = new GridPane();
		g.setHgap(5);
		g.addColumn(0, meno,vek,login,fond,hodnota,nazov,rozloha,typ,vyrub,ziadostFond,ziadostOhodnotenie);
		g.addColumn(1, menoT,vekT,loginT,fondT,hodnotaT,nazovT,rozlohaT,typT,vyrubT,ziadostFondT,ziadostOhodnotenieT);
		
		return g;
		
	}
	
}





