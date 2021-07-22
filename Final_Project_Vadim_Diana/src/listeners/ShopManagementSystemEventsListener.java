package listeners;

import model.SortType;

public interface ShopManagementSystemEventsListener {

	void addProductToModelEvent();

	void showProductInfoModelEvent(String info);

	void showAllProductsModelEvent(String info);

	void selectedSortTypeModelEvent(SortType eSortType);

	void restoreLastStateModelEvent(String msg);

	void removeProductFromFileModelEvent(boolean isRemoved);

	void removeAllProductsFromFileModelEvent();

	void loadDataFromFileModelEvent();

	void showAllProductsProfitModelEvent(String profit);

	void customerUpdateModelEvent(String name);

}
