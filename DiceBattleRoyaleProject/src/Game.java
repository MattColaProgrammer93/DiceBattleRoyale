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
			String inputLine = sc.nextLine();
			boolean isAName = GameHelper.checkNameForNumber(inputLine); // Check if name isn't just a number
			if(!isAName) {
				throw new PlayerException("This isn't a valid name, try again.");
			} else if (inputLine == "quit") {
				return; // Will exit the game
			} else if (inputLine == "start"){ // Will start the game
				if (playerList.size() <= 1) { // If the game tries to start with one or zero players
					throw new GameException("Game must have two or more players to start.");
					inputLine = "";
				}
			} else {
				Map<String, PlayerStats> player = new HashMap<>();
				boolean checkDuplicates = GameHelper.scanForDuplicates(playerList, inputLine);
				if(!checkDuplicates) {
					player.put(inputLine, new PlayerStats()); // Creates player
					playerList.add(player); // Insert the player into the list
				} else {
					throw new PlayerException("Player name is already on the list, try again.");
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
			while(playerList.size() != 1) {
				Map<String, PlayerStats> player = playerList.remove(); // Current Player
				// TODO: Player should be able have a random chance to trigger event
				
				boolean playerTurn = true;
				// The player's turn
				while (playerTurn) {
					GameHelper.playerMenu(player.getKey());
					String command = sc.nextLine();
					// Player will attack another player.
					if (command.equalsIgnoreCase("attack")) { 
						
					} 
					// Player will have a chance to find item.
					else if (command.equalsIgnoreCase("search")) {
						
					} 
					// Player will blocking this turn. Reducing any incoming damage by half.
					else if (command.equalsIgnoreCase("block")) {
						
					} else { // If the command is not of any of the three.
						throw new GameException("Not a valid command, try again.");
					}
				}
			}
		}
	}
}
