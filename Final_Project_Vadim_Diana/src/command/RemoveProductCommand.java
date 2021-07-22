package command;

import model.ShopManagementSystem;

public class RemoveProductCommand implements Command {

	ShopManagementSystem model;
	String productID;
	
	
	public RemoveProductCommand(ShopManagementSystem model,String productID) {
		
		this.model = model;
		this.productID=productID;
	}



	@Override
	public void execute() {
		model.removeProductFromFile(productID);
		
	}

}
