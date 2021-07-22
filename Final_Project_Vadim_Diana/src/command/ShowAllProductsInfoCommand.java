package command;

import model.ShopManagementSystem;

public class ShowAllProductsInfoCommand implements Command {

	private ShopManagementSystem model;
	
	
	
	
	public ShowAllProductsInfoCommand(ShopManagementSystem model) {
		this.model = model;
	}



	@Override
	public void execute() {
		model.showAllProductsInformation();
		
	}

}
