import java.util.*;

public class GameHelper {
		
	/**
	 * Gets the player from the list based on the player name
	 * @param playerList The list of players
	 * @param playerName The player's name
	 * @return The player with the same name as input will be returned.
	 */
	private static Map<String,Object> getPlayer(Queue<Map<String, Object>> playerList, String playerName) {
		Map<String,Object> chosen = new HashMap<>();
		for (Map<String, Object> player : playerList) {
			if (player.get("name").equals(playerName)) {
				chosen = player;
			}
		}
		return chosen;
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
		String playerName = (String)player.get("name");
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
	
	/**
	 * The player has accepted the strange item. The message will be displayed indicating that.
	 * If the player refuses, then a different message will also be displayed.
	 * @param choice
	 */
	public static void playerAcceptsStrangeItemMessage(boolean choice) {
		if (choice) {
			System.out.println("You took the pill and your body starts to burn");
			System.out.println("It felt you're set ablaze.");
			System.out.println("After a painful moment, you felt stronger than before.");
			System.out.println("The man is gone, seemingly disappering after you took the pill.");
			System.out.println("After calming down, you press onwards.");
		} else {
			System.out.println("You wouldn't take a random pill by a stranger.");
			System.out.println("Sensing your refusal, the stranger nods in understanding.");
			System.out.println("He then disappered into thin air, startling you.");
			System.out.println("After that strange encounter, you decided to stop thinking about");
			System.out.println("and leave.");
		}
	}
	
	/**
	 * The message will ask the player to confirm that command. Based on which one, the
	 * message will be unique to all four.
	 * @param command The command that the player is asking for
	 */
	private static void confirmMessage(String command) {
		// Player decides to attack
		if (command.equalsIgnoreCase("attack")) { 
			System.out.println("Do you wish to attack or would you like to think it over?");
		} 
		// Player decides to search for an item
		else if (command.equalsIgnoreCase("search")) {
			System.out.println("Do you want to search for an item or do it at a better time?");
		} 
		// Player decides use a item.
		else if (command.equalsIgnoreCase("item")) {
			System.out.println("Do you want to use the item or save it for later?");
		}
		// Player decides to block this turn.
		else {
			System.out.println("Do you want to block now or decide on something else?");
		}
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
		String playerName = (String)player.get("name");
		
		// 0 to 6 : 1 in 5 chance
		int n = rand.nextInt(6);
		// If the player succeeds in getting the event
		if (n == 5) {
			// 1 in 3 chance to trigger a small boost for multiple stats
			int j = rand.nextInt(4);
			if (j == 3) {
				majorEvent(player);
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
	
	/**
	 * The major event is where the player can get a small boost to three random stats
	 * for a price of 15 health points. The player can refuse or accept the offer.
	 * @param player The lucky player
	 */
	private static void majorEvent(Map<String,Object> player) {
		String playerName = (String)player.get("name");
		System.out.println("Major Event has been triggered by " + playerName);
		majorEventMessage(); // Displays the event message
		Scanner scanner = new Scanner(System.in);
		boolean playerAccepts = false;
		String inputLine = ""; // The input of the user
		// Will loop until user says yes or no
		System.out.println("You will lost 15 health points if you accept");
		System.out.println("but gain a small boost to three random stats");
		System.out.println("yes no");
		while (inputLine != "no" || inputLine != "yes") {
			try {
			inputLine = scanner.nextLine();
			if (inputLine != "no" || inputLine != "yes") {
				throw new GameException("Please say yes or no");
			}
			} catch (GameException g) {
				// Exception thrown above
			}
		}
		// User made their choice
		if (inputLine == "yes") { // The player accepts
			playerAccepts = true;
			playerAcceptsStrangeItemMessage(playerAccepts);
			// TODO: Give 3 small boosts to current player
		} else { // The player refuses
			playerAcceptsStrangeItemMessage(playerAccepts);
		}
		scanner.close();
	}
	
	/**
	 *Initializes a player map with the baseline combat stats.
	 *@param playerMap the map representing an active combatant.
	 *@param playerName The name of the player.
	 */
	public static void initializePlayerStats(Map<String, Object> playerMap, String playerName) {
		playerMap.put("name", playerName);
		playerMap.put("health", 100);	// Our Base HP
		playerMap.put("defense", 10);	// Flat damage reduction
		playerMap.put("baseDamage", 12); // Base attack power
		playerMap.put("critChance", 0.10); // 10% chance to deal a crit attack
		playerMap.put("critBonus", 1.5); // 1.5x damage buff for crits
		playerMap.put("isBlocking", false); // Defense stance toggle
		playerMap.put("status", true);
		playerMap.put("item", new Item());
	}
	
	/**
	 * The method will ask the player to confirm their actions
	 * @return Return true if they made their decision, otherwise return false if not
	 */
	public static boolean confirm(String command) {
		// The player can choose to attack or not
		Scanner scanner = new Scanner(System.in);
		String inputLine = ""; // The input of the user
		confirmMessage(command);
		System.out.println("confirm cancel");
		while(inputLine != "cancel" || inputLine != "confirm") {
			inputLine = scanner.nextLine();
			if (inputLine != "cancel" || inputLine != "confirm") {
				throw new GameException("Please pick one of the two opitions.");
			}
		}
		scanner.close();
		// If player choose to attack, return true;
		if (inputLine.equalsIgnoreCase("confirm")) {
			return true;
		}
		return false;
	}
	
	/**
	 * The method will check if the player is still alive in the game.
	 * @param player The current player
	 * @return Return true if they are alive, otherwise return false if they are not alive.
	 */
	public static boolean checkStatus(Map<String,Object> player) {
		int currentHealth = (int)player.get("health");
		// Check if player has 0 or negative health points
		if (currentHealth <= 0) {
			// Player is now considered dead
			player.put("status", false);
			return false;
		}
		return true;
	}
	
	/**
	 * The method will check if the player has an item.
	 * @param player The current player
	 * @return Return true if they have an item, otherwise return false if not.
	 */
	public static boolean itemInHand(Map<String,Object> player) {
		Item currItem = (Item)player.get("item");
		// If item is not in their possession.
		if (currItem.getName() == null) {
			return false;
		}
		return true;
	}
	
	/*
	 * Attacking
	 */
	
	/**
	 * Calculates total raw damage output & checks for crits
	 * @param attacker The map of the player attacking
	 * @return The raw damage generated from the roll
	 */
	public static int calculateAttackDamage(Map<String, Object> attacker) {
		int baseDamage = (int) attacker.getOrDefault("baseDamage", 12);
		double critChance = (double) attacker.getOrDefault("critChance", 0.10);
		double critBonus = (double) attacker.getOrDefault("critBonus", 1.5);
		
		// Dice-style variance (base damage +/- a variance of 1-6)
		int diceRoll = (int) (Math.random() * 6) + 1;
		int rawDamage = baseDamage + diceRoll;
		
		// Roll for a crit
		if (Math.random() < critChance) {
			rawDamage = (int) (rawDamage * critBonus);
			System.out.println("CRITICAL HIT!");
		}
		return rawDamage;
	}
	
	/**
	 * The player will select the target to attack
	 * @param attacker The attacking player
	 * @return The player that the attacker will target
	 */
	public static void selectTargetAndAttack(Map<String, Object> attacker, Queue<Map<String, Object>> playerList){
		// Display the player list
		for (Map<String, Object> player : playerList) {
			String playerName = (String)player.get("name");
			// Attacking player's name will not be shown as a target
			if (attacker.get("name") != playerName) {
				System.out.print(playerName + " ");
			}
		}
		
		// The attacking player will pick the target that they wished to attack
		Scanner scanner = new Scanner(System.in);
		String inputLine = ""; // The input of the user
		boolean isValidTarget = false;
		// Loop will continue until the attacking player chooses valid target
		while(!isValidTarget) {
			try { 
				System.out.println("Choose a target on the list");
				inputLine = scanner.nextLine();
				// Check if the entered name is valid
				if (checkingValidChosenPlayer(playerList, inputLine)) {
					isValidTarget = true;
				}
				// If the target's name is valid
				if (isValidTarget) {
					Map<String, Object> defender = getPlayer(playerList, inputLine);
					// If the target is alive
					if (validateAttackTarget(attacker, defender)) {
						int damage = calculateAttackDamage(attacker);
						int defenderHealth = (int)defender.get("health");
						defender.put("health", defenderHealth - damage);
					}
				}
				} catch (PlayerException e) {
					// PlayerException thrown in other methods
				}
		}
		scanner.close();
		// TODO: The attacking player will now deal damage to chosen target.
	}
	
	/**
	 * The method will check if the inputed name of the chosen player is on the list.
	 * @param playerList The list of players.
	 * @param input The inputed name of the chosen player
	 * @return Return true if the name of the player is on the list, otherwise return false if not.
	 */
	private static boolean checkingValidChosenPlayer(Queue<Map<String, Object>> playerList, String input) {
		boolean isValidName = false;
		// Check if the inputed name of the player is on the list
		for (Map<String, Object> player : playerList) {
			if (input == (String)player.get("name")) {
				isValidName = true;
			}
		}
		// If the name is valid
		if (isValidName) {
			return true;
		} else {
			throw new PlayerException("The inputed name of the player isn't on the list, try again.");
		}
		
	}
	
	/**
	 * This section validates if an intended attack is legal
	 * Throws a PlayerException if a combatant targets themselves 
	 * or an eliminated player.
	 * 
	 * @param attacker: the map of the player initiating the action
	 * @param defender: the map of the targeted opponent
	 * @return Return true if the defender can be attacked, otherwise return false if not.
	 */
	public static boolean validateAttackTarget(Map<String, Object> attacker, Map<String, Object> defender) {
		String attackerName = (String) attacker.getOrDefault("name", "Attacker");
		String defenderName = (String) defender.getOrDefault("name", "Targer");
		
		//Throw an exception if the player attempts to attack themselves
		if (attackerName.equalsIgnoreCase(defenderName)) {
			throw new PlayerException("Illegal Move: You cannot target yourself for an attack! Select another player.");
		}
		//Throw an exception if the target is already dead
		int defenderHealth = (int) defender.getOrDefault("health", 100);
		if (defenderHealth <= 0) {
			throw new PlayerException("Illegal Move: " + defenderName + "is already dead and cannot be targeted.");
		}
		return true; 
	}
	
	
	/*
	 * Blocking
	 */
	
	
	/*
	 * Search
	 */
	
	
	/*
	 * Use Item
	 */
}
