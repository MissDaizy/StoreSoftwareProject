package view;

import controller.SystemController;
import model.SortType;

public interface AbstractView {

	void registerListener(SystemController systemController);

	void addProductUI();

	void showProductInfoUI(String info);

	void ShowAllProductsInfoUI(String info);

	void selectSortTypeUI(SortType eSortType);

	void restoreLastStateUI(String msg);

	void removeProductFromFileUI(boolean isRemoved);

	void removeAllProductsUI();

	void loadDataFromFileUI();

	void showAllProductsProfitUI(String profit);

	void customerUpdate(String name);

	

	

}
