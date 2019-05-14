package app;

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
	public float rangeScale;
	
	Strategy(int nR, float e, float k, float scale) {
		if (e == 1.0f) {
			strategy = LINEAR;
		}
		else if (e < 1.0f && e > 0.0f) {
			strategy = BOULWARE;
		}
		
		else if (e > 1.0f) {
			strategy = CONCEDER;
		}
		else if (e == 0.0f) {
			strategy = CONSTANT;
		}
		
		constant = k;
		eFactor=e;
		numRequests = nR/2-2;
		rangeScale = scale;
	}
	
	float getConcedingFactor(int currentRequestAgent) {
		float concedingFactor = 0.0f;
			if(numRequests<=0 || strategy.equals(CONSTANT)) {
				concedingFactor = constant * rangeScale;
			}
			else {
				float functionRes = (float) (constant + (1-constant)*Math.pow((double)Math.min(currentRequestAgent, numRequests)/numRequests, (double)1/eFactor));
				concedingFactor = (functionRes - previousFunctionRes) * rangeScale;
				previousFunctionRes = functionRes;
			}
			
			System.out.println();
			System.out.println(strategy + " Current Request: " + currentRequestAgent + " Total Requests: " + numRequests + " Conceding Factor: " + concedingFactor);
			
			return concedingFactor;
	}
}
