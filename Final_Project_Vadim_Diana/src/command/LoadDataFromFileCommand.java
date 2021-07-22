package command;

import model.ShopManagementSystem;

public class LoadDataFromFileCommand implements Command {

	ShopManagementSystem model;
	
	
	
	public LoadDataFromFileCommand(ShopManagementSystem model) {
		this.model = model;
	}



	@Override
	public void execute() {
	model.loadDataFromFile();
		
	}

}
