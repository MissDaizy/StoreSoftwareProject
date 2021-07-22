package model;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class FileIterator implements Iterator<Entry<String, Product>> {

	RandomAccessFile raf;
	int readPointer;
	int writePointer;

	public FileIterator(RandomAccessFile raf) throws IOException {
		this.raf = raf;
		this.raf.seek(0);
		readPointer = 0;
		writePointer = 0;
	}

	@Override
	public boolean hasNext() {
		try {
			if (raf.getFilePointer() < raf.length())
				return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void remove() {

		try {
			byte[] temp = new byte[(int) (raf.length() - readPointer)];
			raf.seek(readPointer);
			raf.read(temp);
			raf.seek(writePointer);
			raf.write(temp);
			raf.setLength(raf.getFilePointer());
			raf.seek(writePointer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Entry<String, Product> next() {
		try {
			writePointer = (int) raf.getFilePointer();
			String productID = readString(raf.readInt());
			String productName = readString(raf.readInt());
			int shopCost = raf.readInt();
			int sellPrice = raf.readInt();
			String customerName = readString(raf.readInt());
			String customerPhoneNumber = readString(raf.readInt());
			boolean updates = Boolean.parseBoolean(readString(raf.readInt()));

			Customer customer = new Customer(customerName, customerPhoneNumber, updates);
			Product product = new Product(productName, shopCost, sellPrice, customer);
			readPointer = (int) raf.getFilePointer();

			return new AbstractMap.SimpleImmutableEntry<String, Product>(productID, product);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public SortType readSortType() throws IOException {
		raf.seek(0);
		return SortType.valueOf(raf.readUTF());
	}

	private String readString(int size) throws IOException {
		byte[] data = new byte[size];
		raf.read(data);
		return new String(data);

	}

	

}
