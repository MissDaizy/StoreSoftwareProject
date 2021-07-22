package memento;

public class CareTaker {

	private ShopMemento memento;

	public CareTaker() {
		memento=null;
	}
	
	public void save(ShopMemento memento) {
		this.memento=memento;
	}
	
	public ShopMemento restore() {
		ShopMemento temp=memento;
		this.memento=null;
		return temp;
	}

}
