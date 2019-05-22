package app;

//Agent negotiation strategies:
//Linear: offer decreases
public class Strategy {
	
	public static String LINEAR = "LINEAR";
	public static String CONCEDER = "CONCEDER";
	public static String BOULWARE = "BOULWARE";
	public static String CONSTANT = "CONSTANT";
	
	public String strategy;
	public int numRequests;
	public float constant;
	public float eFactor;
	public float previousFunctionRes;
	
	//Total number of requests: int
	//Strategy decider e: float
	//Initial constant k: float
	Strategy(int nR, float e, float k) {
		//Linear: agent concedes linearly
		if (e == 1.0f) {
			strategy = LINEAR;
		}
		
		//Boulware: initial offer is maintained until the negotiation is almost over
		//When the agent concedes up to its reservation value
		else if (e < 1.0f && e > 0.0f) {
			strategy = BOULWARE;
		}
		
		//Conceder: agent goes up to its reservation value very quickly
		else if (e > 1.0f) {
			strategy = CONCEDER;
		}
		
		//Constant: agent concedes with a constant conceding factor
		else if (e == 0.0f) {
			strategy = CONSTANT;
		}
		
		constant = k;
		eFactor=e;
		
		//for each agent the number of requests that use a conceding factor correspond to
		//half of number of requests minus 2 (because the first buyer and seller requests
		//don't use a conceding factor
		numRequests = nR/2-2;
	}

	public void reset() {
		previousFunctionRes = 0;
	}
	
	float getConcedingFactor(int currentRequestAgent) {
		float concedingFactor = 0.0f;
		//if strategy is constant or number of requests is lower or equal to 0
		//conceding factor is constant
		if(numRequests<=0 || strategy.equals(CONSTANT)) { 
			concedingFactor = constant;
		}
		
		//Use formula:
		// f = k + (1-k)(min(number of current agent request, agent total requests)/agent total request)**(1/e)
		else { 
			float functionRes = (float) (constant + (1-constant)*Math.pow((double)Math.min(currentRequestAgent, numRequests)/numRequests, (double)1/eFactor));
			concedingFactor = (functionRes - previousFunctionRes);
			previousFunctionRes = functionRes;
		}
		
		System.out.println(strategy + " Current Request: " + currentRequestAgent + " Total Requests: " + numRequests + " Conceding Factor: " + concedingFactor);
		
		return concedingFactor;
	}
}
