package app;

public class AgentConfig {
    public float riskWillingness = 0.0f;
    public float profitMargin = 0.0f;
    public float offerInflation = 0.0f;
    public float necessity = 0.0f;
    public Strategy strategy = null;

    public AgentConfig(float riskWillingness, float profitMargin, float offerInflation, float necessity, Strategy strategy) {
        this.riskWillingness = riskWillingness;
		this.profitMargin = profitMargin;
		this.offerInflation = offerInflation;
		this.necessity = necessity;
		this.strategy = strategy;
    }
}