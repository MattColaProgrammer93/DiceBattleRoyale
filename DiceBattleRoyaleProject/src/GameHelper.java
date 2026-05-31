import java.util.Map;
import java.util.Queue;

public class GameHelper {
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
	public static boolean scanForDuplicates(Queue<Map<String, PlayerStats>> playerList, String playerName) {
		for (Map<String, PlayerStats> player : playerList) {
			String name = player.getKey(player);
			if (name.equalsIgnoreCase(playerName)) {
				return true;
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
	
	// Messages
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
	
	public static void playerMenu() {
		System.out.println("Pick your options");
		System.out.println("Attack  Search  Block");
	}
}
