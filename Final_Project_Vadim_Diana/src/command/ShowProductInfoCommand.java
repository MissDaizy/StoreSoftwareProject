package command;

import model.ShopManagementSystem;

public class ShowProductInfoCommand implements Command {

	private ShopManagementSystem model;
	private String productID;
	
	
	
	public ShowProductInfoCommand(ShopManagementSystem model, String productID) {
		
		this.model = model;
		this.productID = productID;
	}



	@Override
	public void execute() {
		model.showProductInfo(productID);
		
	}

}
