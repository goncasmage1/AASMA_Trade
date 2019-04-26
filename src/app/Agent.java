package app;

public abstract class Agent {
	
	public float riskWillingness = 0.0f;
	
	public Agent(float riskWillingness) {
		this.riskWillingness = riskWillingness;
	}
	
	public abstract Request giveResponse(Request request);
}
