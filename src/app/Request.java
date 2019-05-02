package app;

public class Request {
	
	public float value = 0.0f;
	public Product product = null;
	public String message = "";
	public boolean isSeller = false;
	
	public Request(float value, Product product, boolean isSeller, String message) {
		this.value = value;
		this.product = product;
		this.isSeller = isSeller;
		this.message = message;
	}
	
	public void accept(TradeManager visitor) {
		
	}
}
