package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;
import java.util.TreeMap;

import controller.SystemController;
import listeners.ShopManagementSystemEventsListener;
import memento.ShopMemento;
import observer.Sender;

public class ShopManagementSystem {

	private Map<String, Product> allProducts;
	private ArrayList<ShopManagementSystemEventsListener> systemEventsListeners;
	private SortType eSortType;
	private RandomAccessFile raf;
	private Sender updateSender;

	public ShopManagementSystem() {

		this.systemEventsListeners = new ArrayList<ShopManagementSystemEventsListener>();
		updateSender = Sender.getInstance();
		try {
			raf = new RandomAccessFile("products.txt", "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void registerListener(SystemController systemController) {
		systemEventsListeners.add(systemController);

	}

	public ShopMemento saveState() {
		return new ShopMemento(this);
	}
	
	//restore the last state of the system using memento pattern
	public void restore(ShopMemento memento) {
		String msg;
		if (memento == null) {
			msg = "There is no last state to restore to";
		} else {
			this.allProducts.clear();
			this.allProducts.putAll(memento.getAllProducts());
			reattachAllCustomers();
			autoSaveToFile();
			msg = "Last state restored!";
		}
		fireRestoreLastStateModelEvent(msg);
	}
	
	
	private void reattachAllCustomers() {
		updateSender.detachAll();
		Set<Entry<String, Product>> entrySet = allProducts.entrySet();
		for (Entry<String, Product> e : entrySet) {
			if (e.getValue().getCustomer().isKeepUpdate()) {
				updateSender.attach(e.getValue().getCustomer());
			}
		}

	}

	private void fireRestoreLastStateModelEvent(String msg) {
		for (ShopManagementSystemEventsListener l : systemEventsListeners) {
			l.restoreLastStateModelEvent(msg);
		}

	}

	public Map<String, Product> getAllProducts() {
		return allProducts;
	}

	public SortType geteSortType() {
		return eSortType;
	}

	public void AddProduct(String productID, String productName, int shopPrice, int sellPrice,
			String customerName, String customerPhoneNumber, boolean updates) {
		
		Customer customer = new Customer(customerName, customerPhoneNumber, updates);
		Product newProduct = new Product(productName, shopPrice, sellPrice, customer);
		
		allProducts.put(productID, newProduct);

		if (updates)
			updateSender.attach(customer);

		autoSaveToFile();
		fireAddProductToModelEvent();

	}
	//Read from file using the file iterator
	public void readAllProductsFromFile() {
		try {

			FileIterator fit = new FileIterator(raf);
			if (!fit.hasNext()) {
				return;
			}
			this.eSortType = fit.readSortType();
			allProducts=setMapType(this.eSortType);
			updateSender.detachAll();
			while (fit.hasNext()) {
				Entry<String, Product> entry = (Entry<String, Product>) fit.next();
				allProducts.put(entry.getKey(), entry.getValue());
				if (entry.getValue().getCustomer().isKeepUpdate()) {
					updateSender.attach(entry.getValue().getCustomer());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		fireSelectedSortTypeModelEvent(eSortType);
	}
	//remove from file using the file iterator(using iterator remove function)
	public void removeProductFromFile(String productID) {
		boolean isRemoved = false;
		try {
			FileIterator fit = new FileIterator(raf);
			if (!fit.hasNext()) {
				return;
			}
			fit.readSortType();

			while (fit.hasNext()) {
				Entry<String, Product> entry = (Entry<String, Product>) fit.next();
				if (entry.getKey().equals(productID)) {
					fit.remove();
					if (entry.getValue().getCustomer().isKeepUpdate()) {
						updateSender.detach(entry.getValue().getCustomer());
					}
					isRemoved = true;
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		readAllProductsFromFile();
		fireRemoveProductFromFileModelEvent(isRemoved);
	}

	private void fireRemoveProductFromFileModelEvent(boolean isRemoved) {
		for (ShopManagementSystemEventsListener l : systemEventsListeners) {
			l.removeProductFromFileModelEvent(isRemoved);
		}

	}
	//writing all the products to the file
	//writing the size of the string and then the string itself
	private void autoSaveToFile() {
		try {
			raf.setLength(0);
			raf.writeUTF(eSortType.name());
			if (allProducts==null) {
				return;
			}
			Set<Entry<String, Product>> entrySet = allProducts.entrySet();
			for (Entry<String, Product> e : entrySet) {

				int idSize = e.getKey().length();
				int productNameSize = e.getValue().getName().length();

				int customerNameSize = e.getValue().getCustomer().getName().length();
				int customerPhoneNumerSize = e.getValue().getCustomer().getPhoneNumber().length();
				int booleanSize = Boolean.toString(e.getValue().getCustomer().isKeepUpdate()).length();

				raf.writeInt(idSize);
				raf.write(e.getKey().getBytes());
				raf.writeInt(productNameSize);
				raf.write(e.getValue().getName().getBytes());
				raf.writeInt(e.getValue().getShopPrice());
				raf.writeInt(e.getValue().getSellPrice());
				raf.writeInt(customerNameSize);
				raf.write(e.getValue().getCustomer().getName().getBytes());
				raf.writeInt(customerPhoneNumerSize);
				raf.write(e.getValue().getCustomer().getPhoneNumber().getBytes());
				raf.writeInt(booleanSize);
				raf.write(Boolean.toString(e.getValue().getCustomer().isKeepUpdate()).getBytes());

			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void fireAddProductToModelEvent() {
		for (ShopManagementSystemEventsListener l : systemEventsListeners) {
			l.addProductToModelEvent();
		}

	}

	public void showProductInfo(String productID) {
		Product tempProduct = allProducts.get(productID);
		String info;
		if (tempProduct == null)
			info = null;
		else
			info = tempProduct.toString();

		fireShowProductInfoModelEvent(info);

	}

	private void fireShowProductInfoModelEvent(String info) {
		for (ShopManagementSystemEventsListener l : systemEventsListeners) {
			l.showProductInfoModelEvent(info);
		}

	}

	public void showAllProductsInformation() {
		String info = "";
		Set<Entry<String, Product>> entrySet = allProducts.entrySet();
		for (Entry<String, Product> e : entrySet) {
			info += "Product ID:" + e.getKey() + "\n" + e.getValue().toString() + "\n";
		}

		fireShowAllProductsModelEvent(info);
	}

	private void fireShowAllProductsModelEvent(String info) {
		for (ShopManagementSystemEventsListener l : systemEventsListeners) {
			l.showAllProductsModelEvent(info);
		}

	}

	public void setSelectedSortType(SortType eSortType) {
		this.eSortType = eSortType;
		allProducts=setMapType(eSortType);
		autoSaveToFile();
		fireSelectedSortTypeModelEvent(eSortType);
	}

	private void fireSelectedSortTypeModelEvent(SortType eSortType) {
		for (ShopManagementSystemEventsListener l : systemEventsListeners) {
			l.selectedSortTypeModelEvent(eSortType);
		}

	}
	//initialize map by sort type
	public Map<String, Product> setMapType(SortType eSortType) {
		Map<String, Product> products = null;
		switch (eSortType) {
		case eNoSortType:
			break;

		case eSortByAscendingOrder:
			products = new TreeMap<String, Product>();
			break;

		case eSortByDescendingOrder:
			products = new TreeMap<String, Product>(new CompareByProductIdDesc());
			break;

		case eSortByInsertionOrder:
			products = new LinkedHashMap<String, Product>();
			break;

		default:
			break;
		}
		return products;

	}
	//remove all products from file and allowing the option to choose sort type again
	public void removeAllProducts() {
		try {
			FileIterator fit = new FileIterator(raf);
			if (!fit.hasNext()) {
				return;
			}
			updateSender.detachAll();
			fit.readSortType();
			while (fit.hasNext()) {
				fit.next();
				fit.remove();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		setSelectedSortType(SortType.eNoSortType);
		readAllProductsFromFile();
		fireRemoveAllProductsFromFileModelEvent();

	}

	private void fireRemoveAllProductsFromFileModelEvent() {
		for (ShopManagementSystemEventsListener l : systemEventsListeners) {
			l.removeAllProductsFromFileModelEvent();
		}

	}

	public void loadDataFromFile() {
		readAllProductsFromFile();
		fireLoadDataFromFileModelEvent();

	}

	private void fireLoadDataFromFileModelEvent() {
		for (ShopManagementSystemEventsListener l : systemEventsListeners) {
			l.loadDataFromFileModelEvent();
		}

	}
	//show the profit of each product and the total shop profit
	public void showAllProductsProfit() {
		String profit = "";
		int totalProfit = 0;
		Set<Entry<String, Product>> entrySet = allProducts.entrySet();
		for (Entry<String, Product> e : entrySet) {
			int productProfit = (e.getValue().getSellPrice() - e.getValue().getShopPrice());
			profit += "Product ID:" + e.getKey() + "\n" + "Product name:" + e.getValue().getName() + "\nProfit:"
					+ productProfit + "$\n\n";
			totalProfit += productProfit;
		}
		profit += "The total shop profit:" + totalProfit + "$";
		fireShowAllProductsProfitModelEvent(profit);
	}

	private void fireShowAllProductsProfitModelEvent(String profit) {
		for (ShopManagementSystemEventsListener l : systemEventsListeners) {
			l.showAllProductsProfitModelEvent(profit);
		}

	}
	
	public void updateCustomers(String msg) {

		ArrayList<String> customersNames = updateSender.notifyUpdate(msg);
		for (String name : customersNames) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			fireCustomerUpdateModelEvent(name);
		}
	}

	private void fireCustomerUpdateModelEvent(String name) {
		for (ShopManagementSystemEventsListener l : systemEventsListeners) {
			l.customerUpdateModelEvent(name);

		}

	}

}
