package app;

public class Strategy {
	
	public static String LINEAR = "LINEAR";
	public static String CONCEDER = "CONCEDER";
	public static String BOULWARE = "BOULWARE";
	
	public String strategy;
	public int numRequests;
	public float constant;
	public float eFactor;
	public float previousFunctionRes;
	
	Strategy(int nR, float e, float k) {
		if (e == 1) {
			strategy = LINEAR;
		}
		else if (e < 1 && e > 0) {
			strategy = BOULWARE;
		}
		
		else if (e > 1) {
			strategy = CONCEDER;
		}
		constant = k;
		eFactor=e;
		numRequests = nR/2;
	}
	
	float getConcedingFactor(int currentRequestAgent) {		
		//por contas linear ou deixar como esta?
		if (strategy.equals(LINEAR)) {
			return constant;
		}
		else if (strategy.equals(CONCEDER) || strategy.equals(BOULWARE)) {
			float functionRes = (float) (constant + (1-constant)*Math.pow((double)Math.min(currentRequestAgent, numRequests)/numRequests, (double)1/eFactor));
			float concedingFactor = functionRes - previousFunctionRes;
			previousFunctionRes = functionRes;
			System.out.println(strategy + " Current Request: " + currentRequestAgent + " Total Requests: " + numRequests + " Conceding Factor: " + concedingFactor);
			return concedingFactor;

		}
		else {
			return 0.0f;
		}
	}
}
