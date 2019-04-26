package app;

public class Buyer extends Agent {
	
	public boolean useBoulware = false;
	
	public Buyer(float riskWillingness, boolean useBoulware) {
		super(riskWillingness);
		this.useBoulware = useBoulware;
	}

	@Override
	public Request giveResponse(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

}
