package app;

public class GiveUpTrade extends Request {
	
	public GiveUpTrade(boolean isSeller, Product product) {
		super(0.0f, product, isSeller, null);
	}
	
	@Override
	public void accept(TradeManager visitor) {
		visitor.processRequest(this);
	}
}
