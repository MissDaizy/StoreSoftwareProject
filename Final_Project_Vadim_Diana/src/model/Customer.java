package model;

import java.io.Serializable;

import observer.Observer;

public class Customer implements Serializable,Observer {

	
	private static final long serialVersionUID = 1L;
	private String name;
	private String phoneNumber;
	private boolean keepUpdate;
	
	
	
	public Customer(String name, String phoneNumber, boolean keepUpdate) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.keepUpdate = keepUpdate;
	}



	public String getName() {
		return name;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public boolean isKeepUpdate() {
		return keepUpdate;
	}



	@Override
	public String toString() {
		return "Customer name: " + name + "\nPhone number: " + phoneNumber + "\nUpdates: " + keepUpdate;
	}



	@Override
	public String updateCustomer(String msg) {
		return getName();
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
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
		Customer other = (Customer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}
	
	
	
	
	
}
