package app;

public class InitialOffer extends Request {
	
	public Product product = null;
	public float value = 0.0f;
	
	public InitialOffer(boolean isSeller, String message, Product product, float value) {
		super(isSeller, message);
		this.product = product;
		this.value = value;
	}
	
	@Override
	public void accept(TradeManager visitor) {
		visitor.processRequest(this);
	}
}
