import java.util.*;

public class Game {
	public static void main(String[] args) {
		Queue<Map<String, PlayerStats>> playerList = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		String input;
		
		// Setting the players
		while (input != "quit") {
			System.out.println("Enter player name");
			String playerName = sc.nextLine();
			boolean isAName = false;
			if(!isAName) {
				System.out.println("This isn't a valid name, try again.");
			} else {
				Map<String, PlayerStats> element = new HashMap<>();
				boolean checkDuplicates;
				if(!checkDuplicates) {
					element.put(playerName, new PlayerStats());
					playerList.add(element);
				} else {
					System.out.println("Player name is already on the list, try again.");
				}
			}
		}
	}
}
