package app;

public class AcceptTrade extends Request {
	
	public AcceptTrade(boolean isSeller, String message) {
		super(isSeller, message);
	}
	
	@Override
	public void accept(TradeManager visitor) {
		visitor.processRequest(this);
	}
}
