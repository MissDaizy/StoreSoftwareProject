package command;

import model.ShopManagementSystem;

public class ShowProfitCommand implements Command {

	ShopManagementSystem model;
	
	
	
	public ShowProfitCommand(ShopManagementSystem model) {
		this.model = model;
	}



	@Override
	public void execute() {
	model.showAllProductsProfit();
		
	}

}
