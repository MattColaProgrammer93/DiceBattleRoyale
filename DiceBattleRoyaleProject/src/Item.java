// Gari Garcia
import java.util.Map;

public class Item {
    private String name;
    private String statAffected;
    private double boostAmount;
    private boolean used;
    
    // Blank Item
    public Item() {
    	name = "null";
    	statAffected = "null";
    	boostAmount = 0.0;
    	used = false;
    }
    
    // Current Item
    public Item(String name, String statAffected, double boostAmount) {
        this.name = name;
        this.statAffected = statAffected;
        this.boostAmount = boostAmount;
        this.used = false;
    }

    public String getName() {
        return name;
    }

    public String getStatAffected() {
        return statAffected;
    }

    public double getBoostAmount() {
        return boostAmount;
    }

    public boolean isUsed() {
        return used;
    }

    public void useItem(Map<String, Object> player) {
        if (used) {
            throw new IllegalStateException("This item was already used.");
        }

        if (statAffected.equalsIgnoreCase("health")) {
            int health = (int) player.getOrDefault("health", 100);
            player.put("health", health + (int) boostAmount);
        } 
        else if (statAffected.equalsIgnoreCase("defense")) {
            int defense = (int) player.getOrDefault("defense", 10);
            player.put("defense", defense + (int) boostAmount);
        } 
        else if (statAffected.equalsIgnoreCase("baseDamage")) {
            int baseDamage = (int) player.getOrDefault("baseDamage", 12);
            player.put("baseDamage", baseDamage + (int) boostAmount);
        } 
        else if (statAffected.equalsIgnoreCase("critChance")) {
            double critChance = (double) player.getOrDefault("critChance", 0.10);
            player.put("critChance", critChance + boostAmount);
        } 
        else if (statAffected.equalsIgnoreCase("critBonus")) {
            double critBonus = (double) player.getOrDefault("critBonus", 1.5);
            player.put("critBonus", critBonus + boostAmount);
        } 
        else {
            throw new IllegalArgumentException("Invalid stat: " + statAffected);
        }

        used = true;
    }

    public String toString() {
        return name + " boosts " + statAffected + " by " + boostAmount;
    }
}
