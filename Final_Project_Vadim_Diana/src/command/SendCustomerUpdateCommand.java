package command;

import model.ShopManagementSystem;

public class SendCustomerUpdateCommand implements Command {

	ShopManagementSystem model;
	String updateMessage;
	
	
	
	public SendCustomerUpdateCommand(ShopManagementSystem model,String updateMessage) {
		this.model = model;
		this.updateMessage=updateMessage;
	}



	@Override
	public void execute() {
	model.updateCustomers(updateMessage);
		
	}

}
