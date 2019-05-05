package app;

public abstract class Agent {
	
	TradeManager manager = TradeManager.get();
	public float riskWillingness = 0.0f;
	public float profitMargin = 0.0f;
	public float productUtility = 0.0f;
	public float offerInflation = 0.0f;
	
	public Agent(float riskWillingness, float profitMargin, float offerInflation) {
		this.riskWillingness = riskWillingness;
		this.profitMargin = profitMargin;
		this.offerInflation = offerInflation;
	}

	float lerp(float point1, float point2, float alpha)
	{
		return point1 + alpha * (point2 - point1);
	}

	String[] parseMessage(String message)
	{
		return message.split(" ");
	}
	
	public abstract Request giveResponse(Request request);

	protected abstract float createNextOffer(Request request);
}
