package command;

import model.ShopManagementSystem;

public class ReadDataFromFileCommand implements Command {

	ShopManagementSystem model;
	
	
	
	public ReadDataFromFileCommand(ShopManagementSystem model) {
		this.model = model;
	}



	@Override
	public void execute() {
	model.readAllProductsFromFile();
		
	}

}
