package app;

public abstract class Agent {
	
	TradeManager manager = TradeManager.get();
	public float riskWillingness = 0.0f;
	public float profitMargin = 0.0f;
	public float productUtility = 0.0f;
	public float offerInflation = 0.0f;
	public float necessity = 0.0f;
	public float concedingFactor = 0.25f;
	protected float perceivedValue = 0.0f;

	public static String INFLATE = "INFLATE";
	
	public Agent(float riskWillingness, float profitMargin, float offerInflation, float necessity, float concedingFactor) {
		this.riskWillingness = riskWillingness;
		this.profitMargin = profitMargin;
		this.offerInflation = offerInflation;
		this.necessity = necessity;
		this.concedingFactor = concedingFactor;
	}

	float lerp(float point1, float point2, float alpha)
	{
		return point1 + alpha * (point2 - point1);
	}

	float difference (float value1, float value2) {
		return value1 > value2 ? value2 / value1 : value1 / value2;
	}

	String[] parseMessage(String message)
	{
		return message.split(" ");
	}
	
	public abstract Request giveResponse(Request request);

	protected abstract float createNextOffer(Request request);

	protected abstract Request processLastRequest(Request request);
}
