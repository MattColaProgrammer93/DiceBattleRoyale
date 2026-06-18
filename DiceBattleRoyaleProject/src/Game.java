import java.util.*;

public class Game {
	public static void main(String[] args) {
		// Global Scanner
		final Scanner SC = new Scanner(System.in);
		Queue<Map<String, Object>> playerList = new LinkedList<>();
		int maxSize = 20; // The number of current players on list
		String inputLine = ""; // The input of the user
		
		// Display welcome message.
		GameHelper.welcomeText();
		// Setting the players
		boolean startGame = false;
		while (!inputLine.equalsIgnoreCase("quit") && playerList.size() < maxSize && !startGame) {
			System.out.println("Enter player name");
			inputLine = SC.nextLine();
			boolean isAName = GameHelper.checkNameForNumber(inputLine); // Check if name isn't just a number
			if(!isAName) {
				throw new PlayerException("This isn't a valid name, try again.");
			} else if (inputLine.equalsIgnoreCase("quit")) {
				// Will exit the game
				System.out.println("See you around, come back if you have time!");
				return;
			} else if (inputLine.equalsIgnoreCase("start")){ // Will start the game
				if (playerList.size() <= 1) { // If the game tries to start with one or zero players
					inputLine = "";
					throw new GameException("Game must have two or more players to start.");
				} else {
					startGame = true;
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
					GameHelper.chanceForEvent(player, SC);
				}
				boolean playerTurn = true;
				boolean confirmPlayerAction = false;
				// The player's turn
				while (playerTurn && isPlayerAlive) {
				    try {
				        GameHelper.playerMenu(player);
				        String command = SC.nextLine();
				        if (command.equalsIgnoreCase("attack") || command.equalsIgnoreCase("search") ||
				            command.equalsIgnoreCase("item") || command.equalsIgnoreCase("block")) {
				        	// System will ask if the player wants to execute command
				            confirmPlayerAction = GameHelper.confirm(command, SC);
				            if (confirmPlayerAction) {
				            	// Player will attack
				                if (command.equalsIgnoreCase("attack")) {
				                    GameHelper.selectTargetAndAttack(player, playerList, SC);
				                    playerTurn = false;
				                }
				                // Player will search for item
				                else if (command.equalsIgnoreCase("search")) {
				                    if (GameHelper.itemInHand(player)) {
				                        throw new GameException("You already have an item. Use it before searching again.");
				                    }

				                    GameHelper.searchItem(player);
				                    playerTurn = false;
				                }
				                // Player will use item
				                else if (command.equalsIgnoreCase("item")) {
				                    if (!GameHelper.itemInHand(player)) {
				                        throw new GameException("Player doesn't have an item or they used it, try again.");
				                    }

				                    try {
				                        Item currItem = (Item) player.get("item");
				                        System.out.println(currItem);
				                        currItem.useItem(player);
				                    } catch (IllegalStateException | IllegalArgumentException e) {
				                        // item logic handled internally
				                    }
				                }
				                // Player will block
				                else { // block
				                    player.put("isBlocking", true);
				                    System.out.println((String)player.get("name") + " is now blocking.");
				                    playerTurn = false;
				                }
				            }

				        } else {
				            throw new GameException("Not a valid command, try again.");
				        }

				    } catch (GameException g) {
				        System.out.println(g.getMessage());
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
