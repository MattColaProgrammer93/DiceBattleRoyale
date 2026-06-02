import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class GameHelper {
	
	private static String getPlayerName(Map<String, Object> player) {
		String playerName = "";
		for(String key : player.keySet()) { 
			if (key.equals("name")) {
				playerName = (String)player.get(key);
			}
		}
		return playerName;
	}
	
	/*
	 * Validation
	 */
	
	/**
	 * Will check if the name is a number
	 * @param playerName The inputed name of the player
	 * @return Returns true if the name isn't a number, otherwise return false if it is
	 */
	public static boolean checkNameForNumber(String playerName) {
		if (!isInteger(playerName) || !isDouble(playerName)) {
			return true;
		}
		return false;
	}
	/**
	 * Check the playerList for any names that the player has already entered. Duplicates
	 * aren't allowed to be on the queue.
	 * @param playerList The list of players
	 * @param playerName The name of the player
	 * @return
	 */
	public static boolean scanForDuplicates(Queue<Map<String, Object>> playerList, String playerName) {
		for (Map<String, Object> player : playerList) {
			for(Map.Entry<String, Object> entry : player.entrySet()) {
				String key = entry.getKey();
				if (key.equals("name")) { // If the key is 'name'
					String currentName = (String)entry.getValue();
					// If the player's name is already in the list
					if (currentName.equals(playerName)) { 
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if the name is a integer
	 * @param name The name of the player
	 * @return Returns true if it is a integer, otherwise return false
	 */
	private static boolean isInteger(String name) {
		try {
			Integer.parseInt(name);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	/**
	 * Checks if the name is a double
	 * @param name The name of the player
	 * @return Returns true if it is a double, otherwise return false
	 */
	private static boolean isDouble(String name) {
		try {
			Double.parseDouble(name);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	
	
	/*
	 *  Messages
	 */
	
	/**
	 * Displays a welcoming message to the user
	 */
	public static void welcomeText() {
		System.out.println("Welcome to the Dice Battle Royale");
		System.out.println("Where players will compete with each other using dice to");
		System.out.println("decide their fates. You will have a chance to get items or encounter events");
		System.out.println("that can influence the game. The maximum amount of players is 20");
		System.out.println("To play: type 'start'");
		System.out.println("To quit: type 'quit'");
	}
	/**
	 * Displays the menu for each player
	 * @param playerName The current player
	 */
	public static void playerMenu(Map<String, Object> player) {
		// Get the current player's name
		String playerName = getPlayerName(player);
		System.out.println("Pick your options, " + playerName);
		System.out.println("Attack  Search  Item  Block");
	}
	
	/**
	 * The player will encounter a fairy and recover health points
	 * @param player The current player being healed
	 */
	public static void fairyEncounter(Map<String, Object> player) {
		System.out.println("As you wander around in the deep dark forest, you have appear to see a floating ball of light.");
		System.out.println("Upon a closer look, it is a fairy floating there! It gives a sense of serenity.");
		System.out.println("You felt a bit better after getting close to it");
		// TODO: Health points will be restored to the player
		System.out.println("The fairy disappears after you were lost in the moment of peace.");
		System.out.println("You press onwards.");
	}
	
	/**
	 * The player will pick up a strange idol and receive a small boost to a random stat.
	 * @param player The current player receiving the boost
	 */
	public static void strangeIdol(Map<String, Object> player) {
		System.out.println("As you trend a random path, you found something laying in a bush.");
		System.out.println("After picking it up, you take a close look and it appears to be a idol of some sort.");
		System.out.println("It looks old and damaged to a point that you don't recognize it at all");
		System.out.println("Suddenly, the idol disappears at the exact moment you blinked");
		System.out.println("Strangely, you feel slightly rejuvenated.");
		// TODO: Small boost to a random stat.
		System.out.println("After standing there confused, you decided to press on and not to ask any questions regarding this moment.");
	}
	
	/**
	 * A message about a major event. The player encounters a stranger in thick heavy clothing
	 * that appears to give off a strange feeling.
	 */
	public static void majorEventMessage() {
		System.out.println("In the distance, you see a tall man wearing a thick heavy coat, brown pants, thick scraf and dark glasses.");
		System.out.println("You can't see his face or anything on his person that looks strange.");
		System.out.println("He doesn't seem to be a one of the partcipants in the game.");
		System.out.println("While you were distracted, he somehow stand before you.");
		System.out.println("He takes out a strange colored pill that gives off a strange and powerful smell");
		System.out.println("You feel that taking the pill will make you stronger though you are not sure that you can handle it.");
		System.out.println("Should you really be taking that strange pill from a mysterious strange?");
	}
	
	/*
	 * Game Mechanics
	 */
	/**
	 * A random event will occur for the player to have a chance at a small boost or a
	 * big boost for a price.
	 * @param player The current player
	 */
	public static void chanceForEvent(Map<String,Object> player) {
		Random rand = new Random();
		String playerName = getPlayerName(player);
		
		// 0 to 6 : 1 in 5 chance
		int n = rand.nextInt(6);
		// If the player succeeds in getting the event
		if (n == 5) {
			// 1 in 3 chance to trigger a small boost for multiple stats
			int j = rand.nextInt(4);
			if (j == 3) {
				majorEvent(playerName);
				return;
			}
			System.out.println("Event triggered by " + playerName);
			// If major event not trigger, player gets one of the two event options
			n = rand.nextInt(2);
			if (n == 0) {
				strangeIdol(player);
			} else {
				fairyEncounter(player);
			}
		}
	}
	
	private static void majorEvent(String playerName) {
		System.out.println("Major Event has been triggered by " + playerName);
		majorEventMessage();
		// TODO: Set up major event where player can refuse the reward or accept it
		// and pay the price
	}
}
