package command;

import model.ShopManagementSystem;

public class RemoveAllProductsCommand implements Command {

	ShopManagementSystem model;
	
	
	
	public RemoveAllProductsCommand(ShopManagementSystem model) {
		this.model = model;
	}



	@Override
	public void execute() {
	model.removeAllProducts();
		
	}

}
