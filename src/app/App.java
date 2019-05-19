package app;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class App {

    public static ArrayList<TradeResult> trades = new ArrayList<>();
    public static int numOfSimulations = 50;

    public static int maxTrades = 15;
    public static int minTrades = 10;
    public static Random rand = new Random();

    public static double randomFloatInRange(float min, float max) {
		return min + Math.random() * (max - min);
    }
    
    public static void main(String[] args) throws Exception {
        TradeManager tradeManager = TradeManager.get();
        
        //No decoys
        FileWriter out = new FileWriter("TradeResults1.csv");
        out.write("");
        for (int i = 0; i < numOfSimulations; i++) {
    
            int maxRequests = rand.nextInt((maxTrades - minTrades) + 1) + minTrades;
            AgentConfig sellerConfig = new AgentConfig(0.0f, 0.3f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy(maxRequests, 2.0f, 0.1f));
            AgentConfig buyerConfig = new AgentConfig(1.0f, 0.4f, 0.1f, (float)randomFloatInRange(0.1f, 0.9f), new Strategy (maxRequests, 0.3f, 0.1f));

            System.out.println("TRADE " + (i+1));
            TradeResult tradeResult = tradeManager.startTrade(maxRequests, buyerConfig, sellerConfig);
            trades.add(tradeResult);
            System.out.println();

            out.append(tradeResult.productValue + "," + tradeResult.accepted + "," + tradeResult.sellerProfitAmount + "," + tradeResult.sellerProfitMargin + "\n");
        }
        out.close();
        

    }
}