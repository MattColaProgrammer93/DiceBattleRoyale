import java.util.*;

public class Game {
	public static void main(String[] args) {
		Queue<Map<String, Object>> playerList = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		int maxSize = 20; // The number of current players on list
		String inputLine = ""; // The input of the user
		
		// Display welcome message.
		GameHelper.welcomeText();
		// Setting the players
		while (inputLine != "quit" || maxSize == playerList.size() || inputLine != "start") {
			System.out.println("Enter player name");
			inputLine = sc.nextLine();
			boolean isAName = GameHelper.checkNameForNumber(inputLine); // Check if name isn't just a number
			if(!isAName) {
				throw new PlayerException("This isn't a valid name, try again.");
			} else if (inputLine == "quit") {
				return; // Will exit the game
			} else if (inputLine == "start"){ // Will start the game
				if (playerList.size() <= 1) { // If the game tries to start with one or zero players
					inputLine = "";
					throw new GameException("Game must have two or more players to start.");
				}
			} else {
				Map<String, Object> player = new HashMap<>();
				boolean checkDuplicates = GameHelper.scanForDuplicates(playerList, inputLine);
				if(!checkDuplicates) {
					GameHelper.initializePlayerStats(player, inputLine);
					playerList.add(player); // Insert the player into the list
				} else {
					throw new PlayerException("Player name is already on the list, try again.");
				}
			} 
		}
		// Game Conditions on starting
		boolean startGame = true;
		if (inputLine.equals("quit")) {
			startGame = false;
		}
		// Check if the game can start
		if (!startGame) {
			// Send a message indicates game won't start.
			System.out.println("See you around, come back if you have time!");
		} else {
			// The game will officially begin.
			while(playerList.size() != 1) {
				Map<String, Object> player = playerList.remove(); // Current Player
				// TODO: Check if the player is alive
				boolean isPlayerAlive = GameHelper.checkStatus(player);
				// TODO: Player should be able have a random chance to trigger event
				if (isPlayerAlive) {
					GameHelper.chanceForEvent(player);
				}
				boolean playerTurn = true;
				boolean confirmPlayerAction = false;
				// The player's turn
				while (playerTurn && isPlayerAlive) {
					try {
					GameHelper.playerMenu(player);
					String command = sc.nextLine();
					// Player choose the one of the following options
					if (command.equals("attack") || command.equals("search") || 
							command.equals("item") || command.equals("block")) {
						// Player is asked to confirm their actions
						confirmPlayerAction = GameHelper.confirm(command);
						if (confirmPlayerAction) {
							// Action will be executed
							if (command.equals("attack")) {
								GameHelper.selectTargetAndAttack(player, playerList);
							}
							else if (command.equals("search")) {
								// TODO: Player will search for item
								// The method will check for
								if (GameHelper.itemInHand(player)) {
									throw new GameException("Player already has an item or they haven't used it, try again.");
								} else if (!GameHelper.itemUsed(player)) {
									throw new GameException("Player has not used their item yet.");
								} else {
									// Search for item
									GameHelper.searchItem(player);
									playerTurn = false;
								}
							} else if (command.equals("item")) {
								if (!GameHelper.itemInHand(player)) {
									throw new GameException("Player doesn't have an item or they used it, try again.");
								} else {
									// Use the item
									try {
									Item currItem = (Item)player.get("item");
									currItem.useItem(player);
									playerTurn = false;
									} catch (IllegalStateException s) {
										// Item was used in Item class
									} catch (IllegalArgumentException a) {
										// Invalid stat
									}
								}
							} else { // block
								player.put("isBlocking", true);
								playerTurn = false;
							}
							
						}
					} else { // If the command is not of any of the three.
						throw new GameException("Not a valid command, try again.");
					}
					} catch (GameException g) {
						// GameException thrown in methods from GameHelper
					}
				}
				// If the player is still active
				if (isPlayerAlive) {
					playerList.add(player);
				}
			}
			// Once there is only one player left on the list
			Map<String,Object> player = playerList.remove();
			String playerName = (String)player.get("name");
			System.out.println("Congrats, " + playerName + ". You are victorious.");
		}
	}

}
