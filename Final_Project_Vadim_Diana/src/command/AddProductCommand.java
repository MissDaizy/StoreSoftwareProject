package command;

import model.ShopManagementSystem;

public class AddProductCommand implements Command {
	
	private ShopManagementSystem model;
	private String productID;
	private  String productName;
	private int shopPrice;
	private int sellPrice;
	private String customerName;
	private String customerPhoneNumber;
	private boolean updates;
	
	

	public AddProductCommand(ShopManagementSystem model, String productID, String productName, int shopPrice,
			int sellPrice, String customerName, String customerPhoneNumber, boolean updates) {
		this.model = model;
		this.productID = productID;
		this.productName = productName;
		this.shopPrice = shopPrice;
		this.sellPrice = sellPrice;
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.updates = updates;
	}



	@Override
	public void execute(){
		model.AddProduct(productID, productName, shopPrice, sellPrice, customerName, customerPhoneNumber, updates);
		
	}

}
