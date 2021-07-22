package observer;

import java.util.ArrayList;

public class Sender implements Subject {

	private ArrayList<Observer> observers;
	private static Sender _instance;

	private Sender() {
		
		observers=new ArrayList<Observer>();
	}
	
	public static Sender getInstance() {
		if (_instance==null) {
			_instance=new Sender();
		}
		return _instance;
	}

	@Override
	public void attach(Observer o) {
		observers.add(o);

	}

	@Override
	public void detach(Observer o) {
		observers.remove(o);

	}

	@Override
	public ArrayList<String> notifyUpdate(String msg) {
		ArrayList<String> allCustomersNames = new ArrayList<String>();
		for (Observer observer : observers) {
			allCustomersNames.add(observer.updateCustomer(msg));
			
		}
		return allCustomersNames;
	}

	@Override
	public void detachAll() {
		observers.clear();
		
	}

}
