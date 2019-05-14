package app;

//Agent: Superclass
//Agent can be a buyer or a seller
public abstract class Agent {
	
	TradeManager manager = TradeManager.get();
	public float riskWillingness = 0.0f;
	public float profitMargin = 0.0f;
	public float productUtility = 0.0f;
	public float offerInflation = 0.0f;
	public float necessity = 0.0f;
	public Strategy strategy;
	protected float perceivedValue = 0.0f;

	//deception methods
	//inflate price
	public static String INFLATE = "INFLATE";
	//agent says he has better offer
	public static String BETTER = "BETTER";
	//agent detects deception (inflate)
	public static String DETECTION = "DETECTION";
	
	//Willingness to risk: float
	//Profit Margin: float
	//Offer inflation: float
	//Agent Necessity of selling or buying: float
	//Strategy used: Strategy
	public Agent(float riskWillingness, float profitMargin, float offerInflation, float necessity, Strategy strategy) {
		this.riskWillingness = riskWillingness;
		this.profitMargin = profitMargin;
		this.offerInflation = offerInflation;
		this.necessity = necessity;
		this.strategy = strategy;
	}

	//auxiliary functions
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

	protected abstract float createNextOffer(Request request, boolean inflate);

	protected abstract Request processLastRequest(Request request);

	protected abstract Request processBetterOffer(Request request, float newValue);
}
