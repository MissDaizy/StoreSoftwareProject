package model;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private int shopPrice;
	private int sellPrice;
	private Customer customer;

	public Product(String name, int shopPrice, int sellPrice, Customer customer) {
		this.name = name;
		this.shopPrice = shopPrice;
		this.sellPrice = sellPrice;
		this.customer = customer;
	}

	public String getName() {
		return name;
	}

	public int getShopPrice() {
		return shopPrice;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	@Override
	public String toString() {
		return "Product name: " + name + "\nShop price: " + shopPrice +"$"+ "\nSell Price: " + sellPrice +"$"+ "\n"
				+ customer.toString() + "\n\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + sellPrice;
		result = prime * result + shopPrice;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sellPrice != other.sellPrice)
			return false;
		if (shopPrice != other.shopPrice)
			return false;
		return true;
	}

}
