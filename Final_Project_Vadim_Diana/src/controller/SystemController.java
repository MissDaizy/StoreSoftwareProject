package controller;

import command.AddProductCommand;
import command.Command;
import command.LoadDataFromFileCommand;
import command.ReadDataFromFileCommand;
import command.RemoveAllProductsCommand;
import command.RemoveProductCommand;
import command.RestoreCommand;
import command.SendCustomerUpdateCommand;
import command.SetSelectedSortTypeCommand;
import command.ShowAllProductsInfoCommand;
import command.ShowProductInfoCommand;
import command.ShowProfitCommand;
import listeners.ShopManagementSystemEventsListener;
import listeners.ShopManagementSystemUIEventsListener;
import memento.CareTaker;
import model.ShopManagementSystem;
import model.SortType;
import view.AbstractView;

public class SystemController implements ShopManagementSystemEventsListener,ShopManagementSystemUIEventsListener {
	
	ShopManagementSystem managementSystem;
	AbstractView theView;
	CareTaker careTaker;
	
	public SystemController(ShopManagementSystem managementSystem, AbstractView theView) {
		this.managementSystem=managementSystem;
		this.theView=theView;
		
		managementSystem.registerListener(this);
		theView.registerListener(this);
		
		careTaker=new CareTaker();
		
		readDataFromFiles();
		
	}

	private void readDataFromFiles() {
		Command readFromFileCommand =new ReadDataFromFileCommand(managementSystem);
		readFromFileCommand.execute();
	}

	@Override
	public void addProductToUIEvent(String productID, String productName, int shopPrice, int sellPrice,
			String customerName, String customerPhoneNumber,boolean updates) {
		Command addProductCommand=new AddProductCommand(managementSystem, productID, productName, shopPrice, sellPrice, customerName, customerPhoneNumber, updates);
		careTaker.save(managementSystem.saveState());
		addProductCommand.execute();
		
	}

	@Override
	public void addProductToModelEvent() {
		theView.addProductUI();
		
	}

	@Override
	public void showProductInfoUIEvent(String productID) {
		Command showProductInfoCommand=new ShowProductInfoCommand(managementSystem, productID);
		showProductInfoCommand.execute();
		
	}

	@Override
	public void showProductInfoModelEvent(String info) {
		theView.showProductInfoUI(info);
		
	}

	@Override
	public void showAllProductsUIEvent() {
		Command showAllProductInfoCommand=new ShowAllProductsInfoCommand(managementSystem);
		showAllProductInfoCommand.execute();
		
	}

	@Override
	public void showAllProductsModelEvent(String info) {
		theView.ShowAllProductsInfoUI(info);
		
	}

	

	

	@Override
	public void selectedSortTypeUIEvent(SortType sortType) {
		Command setSelectSortCommand = new SetSelectedSortTypeCommand(managementSystem, sortType);
		setSelectSortCommand.execute();
	}

	

	@Override
	public void selectedSortTypeModelEvent(SortType eSortType) {
		theView.selectSortTypeUI(eSortType);
		
	}

	@Override
	public void restoreLastStateUIEvent() {
		Command restoreCommand = new RestoreCommand(managementSystem,careTaker);
		restoreCommand.execute();
		
	}

	

	@Override
	public void restoreLastStateModelEvent(String msg) {
		theView.restoreLastStateUI(msg);
		
	}

	@Override
	public void removeProductUIEvent(String productID) {
		Command removceCommand=new RemoveProductCommand(managementSystem, productID);
		removceCommand.execute();
		
	}

	@Override
	public void removeProductFromFileModelEvent(boolean isRemoved) {
		theView.removeProductFromFileUI(isRemoved);
		
	}

	@Override
	public void removeAllProductsUIEvent() {
		Command removeAllCommand=new RemoveAllProductsCommand(managementSystem);
		removeAllCommand.execute();
		
	}

	@Override
	public void removeAllProductsFromFileModelEvent() {
		theView.removeAllProductsUI();
		
	}

	@Override
	public void loadDataFromFileUIEvent() {
		Command loadDataCommand=new LoadDataFromFileCommand(managementSystem);
		loadDataCommand.execute();
		
	}

	@Override
	public void loadDataFromFileModelEvent() {
		theView.loadDataFromFileUI();
		
	}

	@Override
	public void showAllProductsProfitUIEvent() {
		Command showProfitCommand=new ShowProfitCommand(managementSystem);
		showProfitCommand.execute();
		
		
	}

	@Override
	public void showAllProductsProfitModelEvent(String profit) {
		theView.showAllProductsProfitUI(profit);
		
	}

	@Override
	public void customerUpdateModelEvent(String name) {
		theView.customerUpdate(name);
		
	}

	@Override
	public void sendUpdateUIEvent(String msg) {
		Command sendUpdateCommand =new SendCustomerUpdateCommand(managementSystem, msg);
		sendUpdateCommand.execute();
		
	}

}
