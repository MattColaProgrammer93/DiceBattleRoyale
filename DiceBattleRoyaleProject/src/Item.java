import java.util.Map;

//Gari Garcia
public class Item {
    private String name;
    private String statAffected;
    private int boostAmount;
    private boolean used;

    public Item(String name, String statAffected, int boostAmount) {
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

    public int getBoostAmount() {
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
            int health = (int) player.get("health");
            player.put("health", health + boostAmount);
        } 
        else if (statAffected.equalsIgnoreCase("attack")) {
            int attack = (int) player.get("attack");
            player.put("attack", attack + boostAmount);
        } 
        else if (statAffected.equalsIgnoreCase("defense")) {
            int defense = (int) player.get("defense");
            player.put("defense", defense + boostAmount);
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
