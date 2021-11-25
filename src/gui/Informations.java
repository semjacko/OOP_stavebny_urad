package gui;

import java.util.ArrayList;

import file.FileUsers;
import items.TypStavby;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import users.Financier;
import users.User;
import users.Customer;
import items.Building;
 
public class Informations{
 
    public void setScene(Stage okno, User prihlasenyPouzivatel) {
        
    	BorderPane bPane = new BorderPane();
    	bPane.setPadding(new Insets(10, 15, 10, 15));
    	
    	FileUsers fu = new FileUsers();
    	
    	ObservableList<Building> data = FXCollections.observableArrayList();   // UPRAVIT
    	for(User u : fu.getUsers()) {
    		if(u instanceof Customer) {
    			for(Building s : ((Customer) u).getBuildings()) {
    				data.add(s);
    			}
    		}
    	}
    	
    	TableView<Building> table = new TableView<Building>();
        table.setItems(data);
        table.setMaxWidth(670);
    
        TableColumn<Building,String> name = new TableColumn<Building,String>("Nazov stavby");
        name.setPrefWidth(200);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
 
        TableColumn<Building,TypStavby> type = new TableColumn<Building,TypStavby>("Typ");
        type.setPrefWidth(80);
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
 
        TableColumn<Building,Integer> area = new TableColumn<Building,Integer>("Rozloha (m" + '\u00b2' + ")");
        area.setPrefWidth(100);
        area.setCellValueFactory(new PropertyValueFactory<>("area"));
        
        TableColumn<Building,Integer> forest = new TableColumn<Building,Integer>("Vyrub (m" + '\u00b2' + ")");
        forest.setPrefWidth(100);
        forest.setCellValueFactory(new PropertyValueFactory<>("forest"));   
        
        TableColumn<Building,Integer> fond = new TableColumn<Building,Integer>("Fond (" + '\u20ac' + ")" );
        fond.setPrefWidth(100);
        fond.setCellValueFactory(new PropertyValueFactory<>("fond"));
        
        TableColumn<Building,Integer> price = new TableColumn<Building,Integer>("Hodnota (" + '\u20ac' + ")");
        price.setPrefWidth(100);
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        TableColumn<Building,String> owner = new TableColumn<Building,String>("Majitel");
        owner.setPrefWidth(100);
        owner.setCellValueFactory(new PropertyValueFactory<>("owner"));
        
        table.getColumns().addAll(name, owner, type, area, forest, price, fond);
        bPane.setCenter(table);
        
        Button domov = new Button("Domov");
	    bPane.setBottom(domov);
	    	
	    domov.setOnAction(new EventHandler<ActionEvent>() {            
	            @Override
	            public void handle(ActionEvent e) {
	            	Main m = new Main();
	            	if(prihlasenyPouzivatel instanceof Financier)
	            		m.setScene(okno, (Financier)prihlasenyPouzivatel);
	            	else
	            		m.setScene(okno, prihlasenyPouzivatel);
	            }
	        });
 
        Scene scene = new Scene(bPane,700,450);
        scene.getStylesheets().add(Informations.class.getResource("styl.css").toExternalForm());
        okno.setScene(scene);
    }
}