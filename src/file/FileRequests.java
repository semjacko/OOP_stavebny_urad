package file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import items.Request;
import items.RequestBuilding;
import items.RequestSalary;
import users.User;

/**
 * Trieda, kotra pracuje so suborom ziadosti.out
 * @author roboj
 *
 */
public class FileRequests implements File{

	private ArrayList<Request> requests;
	private ArrayList<RequestBuilding> buildingRequests;
	private ArrayList<RequestSalary> salaryRequests;
	
	/**
	 * vytvori nastroj na pracu s databazou ziadosti a nacita ziadosti z databazy
	 */
	public FileRequests() {
		read();
	}
	
	/**
	 * vrati ArrayList ziadosti o stavby
	 * @return ziadosti o stavby
	 */
	public ArrayList<RequestBuilding> getBuildingRequests() {
		return buildingRequests;
	}
	
	/**
	 * vrati ArrayList ziadosti o platy
	 * @return ziadosti o platy
	 */
	public ArrayList<RequestSalary> getSalaryRequests() {
		return salaryRequests;
	}
	
	/**
	 * prida ziadost o stavbu do ArrayListov ziadosti o stavbu a ziadosti 
	 * @param r ziadost o stabu
	 */
	public void add(RequestBuilding r) {
		buildingRequests.add(r);
		requests.add(r);
		write();
	}

	/**
	 * prida ziadost o plat do ArrayListov ziadosti o plat a ziadosti 
	 * @param r ziadost o plat
	 */
	public void add(RequestSalary r) {
		salaryRequests.add(r);
		requests.add(r);
		write();
	}
	
	/**
	 * vymaze ziadost o stavbu s indexom i s ArrayListov ziadosti o stavbu a ziadosti
	 * @param i index ziadosti
	 */
	public void removeBuildingRequest(int i) {
		requests.remove(requests.indexOf(buildingRequests.get(i)));    //mozno chybne skontrolovat!!!!
		buildingRequests.remove(i);
		write();
	}
	
	/**
	 * vymaze ziadost o plat s indexom i s ArrayListov ziadosti o platy a ziadosti
	 * @param i index ziadosti
	 */
	public void removeSalaryRequest(int i) {
		requests.remove(requests.indexOf(salaryRequests.get(i)));    //mozno chybne skontrolovat!!!!
		salaryRequests.remove(i);
		write();
	}
	
	/**
	 * zapise ArrayList ziadosti do suboru ziadosti.out
	 */
	public void write() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ziadosti.out"));
			out.writeObject(requests);
			out.close();
		}catch(IOException e) {}
	}
	
	/**
	 * vymaze subor ziadosti.out
	 */
	public void erase() {
		try {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ziadosti.out"));
		requests = new ArrayList<Request>();
		buildingRequests = new ArrayList<RequestBuilding>();
		salaryRequests = new ArrayList<RequestSalary>();
		out.writeObject(requests);
		out.close();
		} catch (IOException e) {}
	}
	
	/**
	 * nacita ziadosti zo suboru ziadosti.out
	 */
	public void read() {    
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("ziadosti.out"));
			requests = ((ArrayList<Request>)in.readObject());
			buildingRequests = new ArrayList<>();
			salaryRequests = new ArrayList<>();
			in.close();
			for(Request r:requests) {
				if(r instanceof RequestBuilding)
					buildingRequests.add((RequestBuilding)r);
				else
					salaryRequests.add((RequestSalary)r);
			}
		} catch (ClassNotFoundException e) {} catch (IOException e2) {} 
	}

	public void add(User u) {
	}
	
}
