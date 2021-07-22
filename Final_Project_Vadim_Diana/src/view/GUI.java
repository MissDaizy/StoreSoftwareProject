package view;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.SystemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import listeners.ShopManagementSystemUIEventsListener;
import model.SortType;

public class GUI implements AbstractView {

	private ArrayList<ShopManagementSystemUIEventsListener> uiEventsListeners = new ArrayList<ShopManagementSystemUIEventsListener>();

	private Text txtProductInfo = new Text();
	private Text txtAllProductInfo = new Text();
	private Text txtAllProductsProfit = new Text();
	private Text txtCustomersUpdates = new Text();
	private RadioButton rbSortTypeAsc = new RadioButton("Ascending Product ID");
	private RadioButton rbSortTypeDes = new RadioButton("Descending Product ID");
	private RadioButton rbSortTypeInsertion = new RadioButton("Insertion order");
	private SortType eSortType = SortType.eNoSortType;

	public GUI(Stage primaryStage) {

		BorderPane mainPane = new BorderPane();
		VBox vbTop=new VBox();
		VBox vbCenter=new VBox();
		VBox vbRight=new VBox();
		VBox vbLeft=new VBox();
		VBox vbBottom=new VBox();
		
		vbCenter.setPadding(new Insets(15));
		vbTop.setPadding(new Insets(15));
		vbRight.setPadding(new Insets(15));
		vbBottom.setPadding(new Insets(15));
		vbLeft.setPadding(new Insets(15));
		
		vbCenter.setSpacing(20);
		vbTop.setSpacing(20);
		vbRight.setSpacing(20);
		vbBottom.setSpacing(20);
		vbLeft.setSpacing(20);
		
	
		mainPane.setPadding(new Insets(20));
		
		
		Button btnAddProduct = new Button("Add Product");
		Button btnShowProductInfo = new Button("Show info of a product");
		Button btnShowAllProducts = new Button("Show info of all products");
		Button btnUndoLast = new Button("Undo last prodcut");
		Button btnRemoveProduct = new Button("Remove product");
		Button btnRemoveAllProducts = new Button("Remove all products");
		Button btnLoadDataFromFile = new Button("Load data from file");
		Button btnShowShopProfit = new Button("Show shop profit");
		Button btnSendUpdate = new Button("Send update");
		Button btnInstructions=new Button("Instructions");
		
		Font font =new Font("Verdana",14);
		
		Text txtWelcome=new Text("Welcome to the shop managment system software!\nFor instructions on how to use the system press the instructions button.");
		txtWelcome.setFont(font);
		Label lblSortType = new Label("Choose Sort type:");
		mainPane.setPadding(new Insets(15));
		Text txtInstructions=new Text();
		txtInstructions.setText("The shop management system allows the user to track its products, track its profits\n"
				+ "And allow maximum user experience that will help streamline store management.\n\n"
				+ "Explanation of the buttons and their actions:\n\n"
				+ "Add product - add a product purchased by a customer\n"
				+ "(Note, ID number is a required field to fill in)\n\n"
				+ "Remove product - Deleting a product from the system by ID number\n"
				+ "(Note, ID number is a required field to fill in)\n\n"
				+ "Remove all products - Deleting all products that exist in the system\n"
				+ "(Note this will force you to select the sorting method again)\n\n"
				+ "Undo last product - Immediately after inserting a new product you can cancel the last operation\n"
				+ "And restore the system to the pre-insertion state\n\n"
				+ "Send update - send an update on a sale or any other update in order to update the customers in the shop`s telephone mailing list\n"
				+ "(as a result of this action, a window will open with the names of the customers who received the update)\n\n"
				+ "Show info of a product - Presentation of a product and its details as stored in the system by ID number\n"
				+ "(Note, ID number is a required field to fill in)\n\n"
				+ "Show info of all products - Presentation of all products and their details as stored in the system\n\n"
				+ "Show shop profit - Displaying the shop profit on each product\n"
				+ "The total shop profit will appear at the bottom of the window that will open\n\n"
				+ "Load data from file - Up-to-date loading of all the information that appears in the file to the management system\n\n"
				+ "\n\n"
				+ "Important emphasis!\n"
				+ "Before using the system, the sorting method must be selected\n"
				+ "This method will be maintained until the action of deleting all the products in the system\n"
				+ "Only in this case the option of selecting a sorting method will open again.\n\n"
				+ "In addition, any actions you take on the system will be saved and re-uploaded each time you run the program.");
		txtInstructions.setFont(font);

		rbSortTypeAsc.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (ShopManagementSystemUIEventsListener l : uiEventsListeners) {
					l.selectedSortTypeUIEvent(SortType.eSortByAscendingOrder);
				}

			}

		});

		rbSortTypeDes.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (ShopManagementSystemUIEventsListener l : uiEventsListeners) {
					l.selectedSortTypeUIEvent(SortType.eSortByDescendingOrder);
				}

			}

		});

		rbSortTypeInsertion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (ShopManagementSystemUIEventsListener l : uiEventsListeners) {
					l.selectedSortTypeUIEvent(SortType.eSortByInsertionOrder);
				}

			}

		});

		btnLoadDataFromFile.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (ShopManagementSystemUIEventsListener l : uiEventsListeners) {
					l.loadDataFromFileUIEvent();
				}

			}
		});

		btnSendUpdate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (eSortType == SortType.eNoSortType) {
					JOptionPane.showMessageDialog(null, "Please choose sort type for the products!");
					return;
				}

				Stage sendUpdateStage = new Stage();
				sendUpdateStage.setTitle("Cusomter updates");
				GridPane gpUpdates = new GridPane();
				gpUpdates.setPadding(new Insets(12));
				gpUpdates.setHgap(25);
				gpUpdates.setVgap(10);

				TextField tfUpdateMsg = new TextField();
				Label lblEnterMsg = new Label("Enter the update message:");
				Button btnSend = new Button("Send");

				tfUpdateMsg.setPrefSize(250, 20);

				btnSend.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						txtCustomersUpdates.setText("");

						Thread t = new Thread(() -> {

							for (ShopManagementSystemUIEventsListener l : uiEventsListeners) {
								l.sendUpdateUIEvent(tfUpdateMsg.getText());
							}

						});
						t.start();

						Stage customerNamesStage = new Stage();
						customerNamesStage.setTitle("Customer names");

						ScrollPane spCustomerNames = new ScrollPane();
						spCustomerNames.setPadding(new Insets(12));

						spCustomerNames.setContent(txtCustomersUpdates);
						customerNamesStage.setScene(new Scene(spCustomerNames, 400, 400));
						customerNamesStage.show();
						customerNamesStage.setX(140);
						customerNamesStage.setY(170);
						sendUpdateStage.close();
					}
				});
				gpUpdates.add(tfUpdateMsg, 2, 4);
				gpUpdates.add(lblEnterMsg, 2, 2);
				gpUpdates.add(btnSend, 2, 6);
				sendUpdateStage.setScene(new Scene(gpUpdates, 400, 200));
				sendUpdateStage.show();
			}
		});

		btnRemoveProduct.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (eSortType == SortType.eNoSortType) {
					JOptionPane.showMessageDialog(null, "Please choose sort type for the products!");
					return;
				}
				Stage removeProductStage = new Stage();
				removeProductStage.setTitle("Remove product");
				GridPane gpRemoveProduct = new GridPane();
				gpRemoveProduct.setPadding(new Insets(12));
				gpRemoveProduct.setHgap(25);
				gpRemoveProduct.setVgap(10);

				Label lblProductID = new Label("Enter product ID:");
				TextField tfProductID = new TextField();

				Button btnRemove = new Button("Remove");

				btnRemove.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						if (tfProductID.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Product ID must have at least one character!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						for (ShopManagementSystemUIEventsListener l : uiEventsListeners) {
							l.removeProductUIEvent(tfProductID.getText());
						}
						removeProductStage.close();
					}
				});

				gpRemoveProduct.add(tfProductID, 3, 1);
				gpRemoveProduct.add(lblProductID, 1, 1);
				gpRemoveProduct.add(btnRemove, 1, 3);

				removeProductStage.setScene(new Scene(gpRemoveProduct, 350, 150));
				removeProductStage.initOwner(primaryStage);
				removeProductStage.initModality(Modality.WINDOW_MODAL);
				removeProductStage.show();
				
			}
		});

		btnRemoveAllProducts.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				if (eSortType == SortType.eNoSortType) {
					JOptionPane.showMessageDialog(null, "Please choose sort type for the products!");
					return;
				}
				for (ShopManagementSystemUIEventsListener l : uiEventsListeners) {
					l.removeAllProductsUIEvent();
				}

			}
		});

		btnAddProduct.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (eSortType == SortType.eNoSortType) {
					JOptionPane.showMessageDialog(null, "Please choose sort type for the products!");
					return;
				}
				Stage addProductStage = new Stage();
				addProductStage.setTitle("Add product");
				GridPane gpAddProduct = new GridPane();
				gpAddProduct.setPadding(new Insets(12));
				gpAddProduct.setHgap(25);
				gpAddProduct.setVgap(10);

				Label lblProduct = new Label("Product:");
				lblProduct.setTextFill(Color.BLUE);

				Label lblCustomer = new Label("Customer:");
				lblCustomer.setTextFill(Color.BLUE);

				Button btnAddTheProduct = new Button("Add The product");

				TextField tfPrdouctID = new TextField();
				Label lblPrdouctID = new Label("Enter product ID(required):");
				lblPrdouctID.setTextFill(Color.RED);

				TextField tfPrdouctName = new TextField();
				Label lblPrdouctName = new Label("Enter product Name:");

				TextField tfPrdouctShopPrice = new TextField();
				Label lblPrdouctShopPrice = new Label("Enter product Shop cost:");

				TextField tfPrdouctCustomerPrice = new TextField();
				Label lblPrdouctCustomerPrice = new Label("Enter product sell price:");

				TextField tfCustomerName = new TextField();
				Label lblCustomerName = new Label("Enter customer name:");

				TextField tfCustomerPhoneNubmer = new TextField();
				Label lblCustomerPhoneNubmer = new Label("Enter customer phone number:");

				Label lblKeepUpdated = new Label("Want to get updates\non promotions?");
				CheckBox chkKeepUpdated = new CheckBox();

				btnAddTheProduct.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						int shopPrice;
						int sellPrice;
						try {
							if (tfPrdouctShopPrice.getText().isEmpty()) {
								tfPrdouctShopPrice.setText("0");
							}
							if (tfPrdouctCustomerPrice.getText().isEmpty()) {
								tfPrdouctCustomerPrice.setText("0");
							}

							shopPrice = Integer.parseInt(tfPrdouctShopPrice.getText());
							sellPrice = Integer.parseInt(tfPrdouctCustomerPrice.getText());
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Please enter only numbers for price!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;

						}
						if (tfPrdouctID.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Product ID must have at least one character!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (sellPrice < 0 || shopPrice < 0) {
							JOptionPane.showMessageDialog(null, "Invalid price:please enter only positive values!",
									"Error", JOptionPane.ERROR_MESSAGE);
							return;
						}

						for (ShopManagementSystemUIEventsListener l : uiEventsListeners) {
							l.addProductToUIEvent(tfPrdouctID.getText(), tfPrdouctName.getText(), shopPrice, sellPrice,
									tfCustomerName.getText(), tfCustomerPhoneNubmer.getText(),
									chkKeepUpdated.isSelected());
						}

						addProductStage.close();
					}
				});

				gpAddProduct.add(lblProduct, 1, 2);
				gpAddProduct.add(lblPrdouctID, 1, 3);
				gpAddProduct.add(tfPrdouctID, 3, 3);
				gpAddProduct.add(lblPrdouctName, 1, 4);
				gpAddProduct.add(tfPrdouctName, 3, 4);
				gpAddProduct.add(lblPrdouctShopPrice, 1, 5);
				gpAddProduct.add(tfPrdouctShopPrice, 3, 5);
				gpAddProduct.add(lblPrdouctCustomerPrice, 1, 6);
				gpAddProduct.add(tfPrdouctCustomerPrice, 3, 6);
				gpAddProduct.add(lblCustomer, 1, 7);
				gpAddProduct.add(lblCustomerName, 1, 8);
				gpAddProduct.add(tfCustomerName, 3, 8);
				gpAddProduct.add(lblCustomerPhoneNubmer, 1, 9);
				gpAddProduct.add(tfCustomerPhoneNubmer, 3, 9);
				gpAddProduct.add(lblKeepUpdated, 1, 11);
				gpAddProduct.add(chkKeepUpdated, 2, 11);

				gpAddProduct.add(btnAddTheProduct, 1, 13);
				addProductStage.setScene(new Scene(gpAddProduct, 500, 400));
				addProductStage.initOwner(primaryStage);
				addProductStage.initModality(Modality.WINDOW_MODAL);
				addProductStage.show();

			}
		});

		btnShowProductInfo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (eSortType == SortType.eNoSortType) {
					JOptionPane.showMessageDialog(null, "Please choose sort type for the products!");
					return;
				}
				Stage showProductInfoStage = new Stage();
				showProductInfoStage.setTitle("Show product info");
				GridPane gpShowProductInfo = new GridPane();
				gpShowProductInfo.setPadding(new Insets(12));
				gpShowProductInfo.setHgap(25);
				gpShowProductInfo.setVgap(10);

				TextField tfPrdouctID = new TextField();
				Label lblPrdouctID = new Label("Enter product ID:");

				Button btnShowProductInfo = new Button("Show");

				btnShowProductInfo.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						if (tfPrdouctID.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Product ID must have at least one character!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						for (ShopManagementSystemUIEventsListener l : uiEventsListeners) {
							l.showProductInfoUIEvent(tfPrdouctID.getText());
						}
						showProductInfoStage.close();
						Stage showInfoStage = new Stage();
						showInfoStage.setTitle("Product information");
						ScrollPane spInfo = new ScrollPane();

						spInfo.setContent(txtProductInfo);
						showInfoStage.setScene(new Scene(spInfo, 400, 400));
						showInfoStage.show();
					}
				});

				gpShowProductInfo.add(tfPrdouctID, 1, 3);
				gpShowProductInfo.add(lblPrdouctID, 1, 2);

				gpShowProductInfo.add(btnShowProductInfo, 1, 5);
				showProductInfoStage.setScene(new Scene(gpShowProductInfo, 300, 150));
				showProductInfoStage.initOwner(primaryStage);
				showProductInfoStage.initModality(Modality.WINDOW_MODAL);
				showProductInfoStage.show();

			}
		});

		btnShowAllProducts.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (eSortType == SortType.eNoSortType) {
					JOptionPane.showMessageDialog(null, "Please choose sort type for the products!");
					return;
				}
				for (ShopManagementSystemUIEventsListener l : uiEventsListeners) {
					l.showAllProductsUIEvent();
				}

				Stage allProductsStage = new Stage();
				allProductsStage.setTitle("all Products information");
				ScrollPane spInfo = new ScrollPane();

				spInfo.setContent(txtAllProductInfo);
				allProductsStage.setScene(new Scene(spInfo, 400, 400));
				allProductsStage.show();

			}
		});

		btnShowShopProfit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (eSortType == SortType.eNoSortType) {
					JOptionPane.showMessageDialog(null, "Please choose sort type for the products!");
					return;
				}

				for (ShopManagementSystemUIEventsListener l : uiEventsListeners) {
					l.showAllProductsProfitUIEvent();
				}

				Stage shopProfitStage = new Stage();
				shopProfitStage.setTitle("Profit");
				ScrollPane spShopProfit = new ScrollPane();

				spShopProfit.setContent(txtAllProductsProfit);
				shopProfitStage.setScene(new Scene(spShopProfit, 400, 400));
				shopProfitStage.show();

			}
		});

		btnUndoLast.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (eSortType == SortType.eNoSortType) {
					JOptionPane.showMessageDialog(null, "Please choose sort type for the products!");
					return;
				}
				for (ShopManagementSystemUIEventsListener l : uiEventsListeners) {
					l.restoreLastStateUIEvent();
				}

			}
		});
		
		btnInstructions.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Stage instructionsStage=new Stage();
				instructionsStage.setTitle("Instructions");
				
				ScrollPane spInstructions=new ScrollPane();
				
				
				spInstructions.setContent(txtInstructions);
			
				instructionsStage.setScene(new Scene(spInstructions,930,700));
				instructionsStage.show();
				
			}
		});
		mainPane.setTop(vbTop);
		mainPane.setLeft(vbLeft);
		mainPane.setRight(vbRight);
		mainPane.setBottom(vbBottom);
		mainPane.setCenter(vbCenter);
		
		
		mainPane.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(0), Insets.EMPTY)));
	
		btnAddProduct.setFont(font);
		btnSendUpdate.setFont(font);
		btnInstructions.setTextFill(Color.BLUE);
		
		vbTop.getChildren().addAll(txtWelcome,btnInstructions);
		vbCenter.getChildren().addAll(btnAddProduct,btnSendUpdate);
		vbLeft.getChildren().addAll(btnRemoveProduct,btnRemoveAllProducts,btnUndoLast);
		vbRight.getChildren().addAll(btnShowProductInfo,btnShowAllProducts,btnShowShopProfit,btnLoadDataFromFile);
		vbBottom.getChildren().addAll(lblSortType,rbSortTypeAsc,rbSortTypeDes,rbSortTypeInsertion);
		
		
		
		primaryStage.setScene(new Scene(mainPane, 550, 500));
		primaryStage.setTitle("Shop management system");
		primaryStage.show();
	}

	@Override
	public void registerListener(SystemController systemController) {
		uiEventsListeners.add(systemController);

	}

	@Override
	public void addProductUI() {
		JOptionPane.showMessageDialog(null, "The product added to the system !");

	}

	@Override
	public void showProductInfoUI(String info) {
		if (info == null)
			txtProductInfo.setText("The product was not found");
		else
			txtProductInfo.setText(info);
	}

	@Override
	public void ShowAllProductsInfoUI(String info) {
		if (info.isEmpty())
			txtAllProductInfo.setText("No products in system!");
		else
			txtAllProductInfo.setText(info);

	}

	@Override
	public void selectSortTypeUI(SortType sortType) {
		this.eSortType = sortType;
		switch (eSortType) {
		case eNoSortType:
			rbSortTypeAsc.setDisable(false);
			rbSortTypeDes.setDisable(false);
			rbSortTypeInsertion.setDisable(false);
			rbSortTypeAsc.setSelected(false);
			rbSortTypeDes.setSelected(false);
			rbSortTypeInsertion.setSelected(false);
			break;
		case eSortByAscendingOrder:
			rbSortTypeAsc.setSelected(true);
			rbSortTypeAsc.setDisable(true);
			rbSortTypeDes.setDisable(true);
			rbSortTypeInsertion.setDisable(true);

			break;
		case eSortByDescendingOrder:
			rbSortTypeDes.setSelected(true);
			rbSortTypeAsc.setDisable(true);
			rbSortTypeDes.setDisable(true);
			rbSortTypeInsertion.setDisable(true);
			break;
		case eSortByInsertionOrder:
			rbSortTypeInsertion.setSelected(true);
			rbSortTypeAsc.setDisable(true);
			rbSortTypeDes.setDisable(true);
			rbSortTypeInsertion.setDisable(true);
			break;
		default:
			break;
		}

	}

	@Override
	public void restoreLastStateUI(String msg) {
		JOptionPane.showMessageDialog(null, msg);

	}

	@Override
	public void removeProductFromFileUI(boolean isRemoved) {
		if (isRemoved) {
			JOptionPane.showMessageDialog(null, "Product removed from system !");
		} else {
			JOptionPane.showMessageDialog(null, "Product was not found !");
		}

	}

	@Override
	public void removeAllProductsUI() {
		JOptionPane.showMessageDialog(null, "All of the products have been removed !");

	}

	@Override
	public void loadDataFromFileUI() {
		JOptionPane.showMessageDialog(null, "Data loaded!");
	}

	@Override
	public void showAllProductsProfitUI(String profit) {
		
		
		txtAllProductsProfit.setText("Shop profit for each product \n(The total profit of the shop is in the bottom of the page)\n\n"+profit);

	}

	@Override
	public void customerUpdate(String name) {

		txtCustomersUpdates.setText(txtCustomersUpdates.getText() + name + " recived the update\n\n");

	}

}
