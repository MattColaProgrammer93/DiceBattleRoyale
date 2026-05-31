
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
}
