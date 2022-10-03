import java.util.Vector;

public class IceCream {

	private String id;
	private String name;
	private String size;
	private Vector<String> topping;
	private int price;
	
	public IceCream() {
		// TODO Auto-generated constructor stub
	}

	public IceCream(String id, String name, String size) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		topping = new Vector<>();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSize() {
		return size;
	}
	
	public void addTopping(String toppingName) {
		topping.addElement(toppingName);
	}

	public String getTopping() {
		String topping = "";
		int len = this.topping.size(); 
		for(int i=0; i<len; i++) {
			topping += this.topping.get(i);
			if(i < len-1) {
				topping += ", ";
			}
		}
		return topping;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
