package app;

//Product involved in the negotiation
public class Product {
	
	public String name;
	public float marketValue = 0.0f;
	public float quality = 1.0f;
	
	//Name of the product: String
	//Market value of the product: float
	//Quality of the product: float
	public Product(String name, float marketValue, float quality) {
		this.name = name;
		this.marketValue = marketValue;
		this.quality = quality;
	}

	public float getValue() {
		return marketValue * quality; //Real value of the product
	}
}
