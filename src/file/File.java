package file;

import items.RequestSalary;
import items.RequestBuilding;
import users.User;

/**
 * Rozhranie pre pracu so subormi uzivatelia.out a ziadosti.out
 * @author roboj
 *
 */
public interface File {
	
	public void add(RequestSalary r);
	public void add(RequestBuilding r);
	public void add(User u);
	public void erase();
	public void read();
	public void write();
	
}
