package app;

public class Product {
	
	public String name;
	public float marketValue = 0.0f;
	public float quality = 1.0f;
	
	public Product(String name, float marketValue, float quality) {
		this.name = name;
		this.marketValue = marketValue;
		this.quality = quality;
	}

}
