package app;

public class AcceptTrade extends Request {
	
	public AcceptTrade(boolean isSeller, Product product) {
		super(0.0f, product, isSeller, null);
	}
	
	@Override
	public void accept(TradeManager visitor) {
		visitor.processRequest(this);
	}
}
