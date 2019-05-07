package app;

import java.util.ArrayList;

public class Request {
	
	public float value = 0.0f;
	public Product product = null;
	public ArrayList<String> messages;
	public boolean isSeller = false;
	
	public Request(float value, Product product, boolean isSeller, ArrayList<String> messages) {
		this.value = value;
		this.product = product;
		this.isSeller = isSeller;
		this.messages = messages;
	}
	
	public void accept(TradeManager visitor) {
		
	}
}
