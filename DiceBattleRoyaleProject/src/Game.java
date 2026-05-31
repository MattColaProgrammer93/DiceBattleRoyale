import java.util.*;

public class Game {
	public static void main(String[] args) {
		Queue<Map<String, PlayerStats>> playerList = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		int maxSize = 20; // The number of current players on list
		String input; // The input of the user
		
		// Display welcome message.
		GameHelper.welcomeText();
		// Setting the players
		while (input != "quit" || maxSize == playerList.size() || input != "start") {
			System.out.println("Enter player name");
			String playerName = sc.nextLine();
			boolean isAName = GameHelper.checkNameForNumber(playerName); // Check if name isn't just a number
			if(!isAName) {
				System.out.println("This isn't a valid name, try again.");
			} else {
				Map<String, PlayerStats> player = new HashMap<>();
				boolean checkDuplicates = GameHelper.scanForDuplicates(playerList, playerName);
				if(!checkDuplicates) {
					player.put(playerName, new PlayerStats()); // Creates player
					playerList.add(player); // Insert the player into the list
				} else {
					System.out.println("Player name is already on the list, try again.");
				}
			}
		}
		// Game Conditions on starting
		boolean startGame = true;
		if (input.equals("quit")) {
			startGame = false;
		}
		// Check if the game can start
		if (!startGame) {
			// Send a message indicates game won't start.
			System.out.println("See you around, come back if you have time!");
		} else {
			// The game will officially begin.
			while(playerList.size() == 1) {
				Map<String, PlayerStats> player = playerList.remove(); // Current Player
				
			}
		}
	}
}
