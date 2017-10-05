package cricket;

import java.util.*;
public class HandCricket {
	private Random rand;
	private Scanner ob;
	
	private boolean bat;
	private boolean chase;
	private boolean gameOver;
	
	private int playerScore;
	private int computerScore;
	
	private String name;
	
	public HandCricket() {
		rand = new Random();
		ob = new Scanner(System.in);
		
		initialize();
	}
	
	private void initialize() {
		gameOver = false;
		chase = false;
		
		playerScore = computerScore = 0;
	}
	
	private void gameLoop() {
		intro();
		
		while (!gameOver) {
			toss();
			play();
			innings();
			bat = !bat;
			play();
			gameOver();
			playAgain();
		}
	}
	
	private void intro() {
		print("Enter your name: ");
		name = ob.nextLine();
		
		printPattern("*", 4);
		print(name + ", welome to the CRICKET MANIA!\n");
	}
	
	private void toss() {
		int n;
		
		do {
			n = getInput("Enter 1 for heads, 2 for tails: ");
		} while (!isInRange(n, 1, 2));
		
		int toss = genNumber(2);
		if (toss == n) { 
			print("You win the toss!");
			do {
				n = getInput("Enter 1 to bat, 2 to ball");
			} while (!isInRange(n, 1, 2));
			if (n == 1) 
				bat = true;
			else
				bat = false;
		}
		else {
			print("Computer wins the toss!");
			int cc = genNumber(2);
			String s = cc == 1 ? "bat" : "ball";
			bat = cc != 1;
			print("Computer will " + s + "!");
		}
		print("\n");
	}
	
	private void play() {
		int playerNumber = 0;
		boolean out = false;
		
		String inputStr, outStr, currScore;
		
		if (bat) {
			print("Now you will bat!");
			
			inputStr = "Enter runs scored: ";
			outStr = "Oh no! You are out!";
		}
		else {
			print("Now you will ball!");
			
			inputStr = "Enter ball number: ";
			outStr = "Yes! The computer is out!";
		}
		
		boolean runsScored = false;
		
		while (!out && !runsScored) {
			int computerNumber = genNumber(10);
			do {
				playerNumber = getInput(inputStr);
			} while (!isInRange(playerNumber, 1, 10));
			
			print("Your number: " + playerNumber);
			print("Computer number: " + computerNumber);
			
			if (playerNumber == computerNumber) {
				out = true;
				print(outStr);
			}
			else {
				if (bat) {
					playerScore += playerNumber;
					currScore = "Your current score: " + playerScore;
					if (chase) {
						int diff = 0;
						if (computerScore - playerScore > 0)
							diff = computerScore - playerScore;
						currScore += "\nRuns needed to win: " + diff;
					}
				}
				else {
					computerScore += computerNumber;
					currScore = "Computer's current score: " + computerScore;
					if (chase) {
						int diff = 0;
						if (playerScore - computerScore > 0)
							diff = playerScore - computerScore;
						currScore += "\nRuns needed by computer to win: " + diff;
					}
				}
					
				print(currScore);
				
				if (chase) {
					if (bat)
						runsScored = playerScore >= computerScore;
					else
						runsScored = computerScore >= playerScore;
				}
			}
			printPattern("=", 2);
		}
	}
	
	private void innings() {
		printPattern("\u00bb", 4);
		print("----------------INNINGS OVER-----------------");
		
		String score;
		
		if (bat)
			score = "Your score: " + playerScore;
		else
			score = "Computer score: " + computerScore;
		print("\n" + score);
		print("Alright! Time for the next round!");
		chase = true;
		
		printPattern("\u00bb", 4);
	}
	
	private void gameOver() {
		print("Your score: " + playerScore);
		print("Computer score: " + computerScore + "\n");
		int diff = computerScore - playerScore;
		String winner;
		
		String message;
		if (diff < 0) {
			winner = "\t" + name.toUpperCase() + " WINS";
			message = "Congratulations! You won! Audience, give a big hand to "
					+ name + " for this excellent performance!";
		}
		else if (diff > 0) {
			winner = "\tCOMPUTER WINS";
			message = "Deepest sorrows! But you gave an excellent fight! Thank you for"
					+ " showing us the true spirit of the game. Better luck next time!";
		}
		else {
			winner = "\tBOTH WIN";
			message = "Congratulations to both of you! It was a close fight, and it was "
					+ "expected from two players of such high callibre! Audience, a big hand to "
					+ "both of them!";
		}
		
		print(winner + "\n");
		print(message);
		
		printPattern("♥", 4);
	}
	
	private void playAgain() {
		int n;
		do {
			n = getInput("Do you want to play again? 1 for yes, 2 for no: ");
		} while (!isInRange(n, 1, 2));
		gameOver = n == 2;
		if (!gameOver)
			initialize();
	}
	
	private boolean isInRange(int n, int ll, int hl) {
		if (n <= hl && n >= ll)
			return true;
		return false;
	}
	
	private void printPattern(String c, int l) {
		print("");
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < 45; j++)
				System.out.print(c);
			print("");
		}
		print("");
	}
	
	private int genNumber(int l) {
		int n = rand.nextInt(l) + 1;
		return n;
	}
	
	private void print(String s) {
		System.out.println(s);
	}
	
	public int getInput(String s) {
		int n;
		print(s);
		n = ob.nextInt();
		return n;
	}
	
	public static void main(String args[]) {
		HandCricket app = new HandCricket();
		app.gameLoop();
	}
}
