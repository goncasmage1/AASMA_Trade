package app;

public class GiveUpTrade extends Request {
	
	public GiveUpTrade(boolean isSeller, String message) {
		super(isSeller, message);
	}
	
	@Override
	public void accept(TradeManager visitor) {
		visitor.processRequest(this);
	}
}
