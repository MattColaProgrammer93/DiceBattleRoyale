import java.util.*;

public class Game {
	public static void main(String[] args) {
		Queue<Map<String, PlayerStats>> playerList = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		int size = 0; // The number of current players on list
		String input; // The input of the user
		
		// Setting the players
		while (input != "quit") {
			System.out.println("Enter player name");
			String playerName = sc.nextLine();
			boolean isAName = GameHelper.checkNameForNumber(playerName); // Check if name isn't just a number
			if(!isAName) {
				System.out.println("This isn't a valid name, try again.");
			} else {
				Map<String, PlayerStats> player = new HashMap<>();
				boolean checkDuplicates = GameHelper.scanForDuplicates(playerList);
				if(!checkDuplicates) {
					player.put(playerName, new PlayerStats());
					playerList.add(player);
				} else {
					System.out.println("Player name is already on the list, try again.");
				}
			}
		}
	}
}
