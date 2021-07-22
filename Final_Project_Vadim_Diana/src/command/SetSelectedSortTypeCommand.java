package command;

import model.ShopManagementSystem;
import model.SortType;

public class SetSelectedSortTypeCommand implements Command {

	ShopManagementSystem model;
	SortType eSortType;
	
	
	public SetSelectedSortTypeCommand(ShopManagementSystem model, SortType eSortType) {
		this.model = model;
		this.eSortType = eSortType;
	}






	@Override
	public void execute() {
	
		model.setSelectedSortType(eSortType);
		
	}

}
