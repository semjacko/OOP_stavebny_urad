package gui;

import users.User;
import file.FileUsers;
import file.FileRequests;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI extends Application {
	
    @Override
	public void start(Stage okno) {
        
    	/*    //VYMAZANIE DATABAZ
        FileUsers fu = new FileUsers();        
    	FileRequests fr = new FileRequests();
    	fu.erase();fr.erase();
    	*/
        
    	okno.setTitle("Stavebný úrad");	
        okno.initStyle(StageStyle.UTILITY);
        Logon l = new Logon();
        l.setScene(okno);
        okno.show();
        
	}

	public static void main(String[] args) {
		launch(args);
	}
}
