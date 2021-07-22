package memento;

import java.util.Map;

import model.Product;
import model.ShopManagementSystem;

public class ShopMemento {
	private Map<String, Product> allProducts;

	public ShopMemento(ShopManagementSystem managementSystem) {
		copyMap(managementSystem);
	}

	private void copyMap(ShopManagementSystem managementSystem) {

		allProducts=managementSystem.setMapType(managementSystem.geteSortType());
		allProducts.putAll(managementSystem.getAllProducts());
	}

	public Map<String, Product> getAllProducts() {
		return allProducts;
	}
	
	
	
}
