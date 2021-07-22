package command;

import memento.CareTaker;
import model.ShopManagementSystem;

public class RestoreCommand implements Command {

	ShopManagementSystem model;
	CareTaker ct;
	
	
	public RestoreCommand(ShopManagementSystem model,CareTaker ct) {
		
		this.model = model;
		this.ct=ct;
	}



	@Override
	public void execute() {
		model.restore(ct.restore());
		
	}

}
