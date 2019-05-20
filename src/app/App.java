package app;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class App {

    public static ArrayList<TradeResult> trades = new ArrayList<>();
    public static int numOfSimulations = 500;

    public static int maxTrades = 15;
    public static int minTrades = 10;
    public static Random rand = new Random();

    static AgentConfig[] sellerConfigs = {
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 0.0f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 0.0f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 0.0f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 0.0f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 2.0f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 2.0f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 2.0f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 2.0f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 0.3f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 0.3f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 0.3f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 0.3f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 2.0f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 2.0f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 2.0f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 2.0f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 0.3f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 0.3f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 0.3f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(0, 0.3f, 0.1f)),
    };

    static AgentConfig[] buyerConfigs = {
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 0.0f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 0.0f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 0.0f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 0.0f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 0.3f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 0.3f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 0.3f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 0.3f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 2.0f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 2.0f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 2.0f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 2.0f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 2.0f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 2.0f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 2.0f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 2.0f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 0.3f, 0.1f)),
        new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 0.3f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 0.3f, 0.1f)),
        new AgentConfig(1.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (0, 0.3f, 0.1f)),
    };

    public static double randomFloatInRange(float min, float max) {
		return min + Math.random() * (max - min);
    }
    
    public static void main(String[] args) throws Exception {
        TradeManager tradeManager = TradeManager.get();
        FileWriter out;

        AgentConfig sellerConfig;
        AgentConfig buyerConfig;
        
        for (int j = 0; j < sellerConfigs.length; j++) {
            out = new FileWriter("TradeResults/TradeResults" + (j+1) + ".csv");
            out.write("");
            for (int i = 0; i < numOfSimulations; i++) {
                
                sellerConfig = sellerConfigs[j];
                buyerConfig = buyerConfigs[j];
                int maxRequests = rand.nextInt((maxTrades - minTrades) + 1) + minTrades;
                sellerConfig.strategy.numRequests = maxRequests;
                buyerConfig.strategy.numRequests = maxRequests;

                //System.out.println("TRADE " + (i+1));
                TradeResult tradeResult = tradeManager.startTrade(maxRequests, buyerConfig, sellerConfig);
                trades.add(tradeResult);
                //System.out.println();

                String productValue = String.valueOf(tradeResult.productValue).replace(".", ",");
                String profitAmount = String.valueOf(tradeResult.sellerProfitAmount).replace(".", ",");
                String profitMargin = String.valueOf(tradeResult.sellerProfitMargin).replace(".", ",");

                out.append(productValue + ";" + tradeResult.accepted + ";" + profitAmount + ";" + profitMargin + "\n");
            }
            out.close();
        }
        
    }
}