package app;

public class Request {
	
	public String message = "";
	public boolean isSeller = false;
	
	public Request(boolean isSeller, String message) {
		this.isSeller = isSeller;
		this.message = message;
	}
	
	public void accept(TradeManager visitor) {
		
	}
}
