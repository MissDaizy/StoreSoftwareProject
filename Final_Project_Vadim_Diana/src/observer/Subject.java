package observer;

import java.util.ArrayList;

public interface Subject {

	 void attach(Observer o);
	 void detach(Observer o);
	 void detachAll();
	 ArrayList<String> notifyUpdate(String msg);
}
