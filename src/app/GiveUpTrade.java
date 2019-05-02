package app;

public class GiveUpTrade extends Request {
	
	public GiveUpTrade(boolean isSeller) {
		super(0.0f, null, isSeller, "");
	}
	
	@Override
	public void accept(TradeManager visitor) {
		visitor.processRequest(this);
	}
}
