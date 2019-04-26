package app;

public class Seller extends Agent {
	
	public float productKnowledge = -1.0f;
	public float trust = 0.5f;
	
	public Seller(float riskWillingness) {
		super(riskWillingness);
	}

	@Override
	public Request giveResponse(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

}
