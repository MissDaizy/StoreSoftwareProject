package listeners;

import model.SortType;

public interface ShopManagementSystemUIEventsListener {

	void addProductToUIEvent(String productID, String productName, int ShopCost, int sellPrice, String customerName, String customerPhoneNumber, boolean updated);

	void showProductInfoUIEvent(String productID);

	void showAllProductsUIEvent();

	void selectedSortTypeUIEvent(SortType  sortType);

	void restoreLastStateUIEvent();

	void removeProductUIEvent(String text);

	void removeAllProductsUIEvent();

	void loadDataFromFileUIEvent();

	void showAllProductsProfitUIEvent();

	void sendUpdateUIEvent(String msg);

}
