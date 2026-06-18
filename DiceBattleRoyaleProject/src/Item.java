// Gari Garcia
import java.util.Map;

/* Represents an item that can be used to temporarily or permanetly
improve a player's combat stats. */
public class Item {

    // Item name
    private String name;

    // Stat that will be affected when item is used
    private String statAffected;

    // Amount added to stat
    private double boostAmount;

    // Prevents item from being used repeatedly
    private boolean used;

    /*
     * Creates an empty item which is used when initialzing player that doesn't have
     * an item
     */
    public Item() {
        this.name = null;
        this.statAffected = null;
        this.boostAmount = 0;
        this.used = false;
    }
    /* Creates a item that can be used with a specific stat boost */

    public Item(String name, String statAffected, double boostAmount) {
        this.name = name;
        this.statAffected = statAffected;
        this.boostAmount = boostAmount;
        this.used = false;
    }

    // Returns item name
    public String getName() {
        return name;
    }

    // Returns stat affected by item
    public String getStatAffected() {
        return statAffected;
    }

    // Returns boost amount
    public double getBoostAmount() {
        return boostAmount;
    }

    // Returns whether or not the item was used
    public boolean isUsed() {
        return used;
    }
    /*
     * Applies item's effect to the player's stats.
     * Throws exception if item was used or if it no longer exists
     */

    public void useItem(Map<String, Object> player) {

        // Prevents item from being reused
        if (used) {
            throw new IllegalStateException("This item was already used.");
        }
        // Prevents using an empty item slot
        if (name == null || statAffected == null) {
            throw new IllegalStateException("No item available to use.");
        }
        // Increases appropriate stat
        if (statAffected.equalsIgnoreCase("health")) {
            int health = (int) player.getOrDefault("health", 100);
            player.put("health", health + (int) boostAmount);
        } // Increase defense stat
        else if (statAffected.equalsIgnoreCase("defense")) {
            int defense = (int) player.getOrDefault("defense", 10);
            player.put("defense", defense + (int) boostAmount);
        } // Increase base damage
        else if (statAffected.equalsIgnoreCase("baseDamage")) {
            int baseDamage = (int) player.getOrDefault("baseDamage", 12);
            player.put("baseDamage", baseDamage + (int) boostAmount);
        } // Increases chance of critical hit
        else if (statAffected.equalsIgnoreCase("critChance")) {
            double critChance = (double) player.getOrDefault("critChance", 0.10);
            player.put("critChance", critChance + boostAmount);
        } // Increases crit damage multiplier
        else if (statAffected.equalsIgnoreCase("critBonus")) {
            double critBonus = (double) player.getOrDefault("critBonus", 1.5);
            player.put("critBonus", critBonus + boostAmount);
        } // Invalid stat name
        else {
            throw new IllegalArgumentException("Invalid stat: " + statAffected);
        }
        // Marks item as consumed after it's used
        used = true;
        // Set name to null
        name = null;
    }
    /* Returns description of item */

    public String toString() {
        if (name == null) {
            return "No item";
        }

        return name + " boosts " + statAffected + " by " + boostAmount;
    }
}