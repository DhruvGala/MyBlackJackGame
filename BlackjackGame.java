/*
 * filename: BlackjackGame.java
 * 
 * log: 10-26-2015
 * 
 * Versions: 1.0 Initial logic for a single game was implemented
 * 			 1.1 Change such as shuffling the card deck after consecutive 
 * ``			 3 games played by the player.
 */

import java.util.Scanner;


/**
 * This is the basic implementation of a Blackjack game simulator.
 * The universal rules of Blackjack are followed.
 * 
 * @author DhruvGala
 *
 */
public class BlackjackGame {

	static MyCards newDeck = new MyCards();
	
	
	/**
	 * The user gets the initial deal of 2 cards.
	 * 
	 * @return
	 */
	public static int[][] deal(){
		
		int cardPointer = 0;
		int usedCards[][] = new int[52][2];
		boolean deal = true;
		while(deal){
			int newCard[] = MyCards.drawACard(cardPointer);	
			MyCards.display(newCard);
			usedCards[cardPointer][0] = newCard[0];
			usedCards[cardPointer][1] = newCard[1];
			cardPointer++;
			
			newCard = MyCards.drawACard(cardPointer);
			MyCards.display(newCard);
			usedCards[cardPointer][0] = newCard[0];
			usedCards[cardPointer][1] = newCard[1];
			cardPointer++;
			
			deal = false;
		}
		
		
		
		//MyCards.cardDeck = MyCards.pushBackUsedCards(newDeck.cardDeck, cardPointer, usedCards);
		//System.out.println("=======AFTER 2 deals=========length:"+newDeck.cardDeck.length);
		//MyCards.display();
		
		return usedCards;
	}
	
	
	
	/**
	 * This method computes the score of the cards at hand for user
	 * as well as the dealer.
	 * 
	 * @param cards
	 * @param scoreSoFar
	 * @param start
	 * @return
	 */
	public static int calculateScore(int[][] cards,int scoreSoFar,int start){
		int score = 0;
		for(int card=start;card<cards.length;card++){
			if(cards[card][1] == 11 || cards[card][1] == 12 || cards[card][1] == 13){
				score += 10;
			}
			else if(cards[card][1] == 1){
				//score += 11;
				if(scoreSoFar + 11 > 21){
					score += 1;
				}
				else{
					score += 11;
				}
				
			}
			else{
				score += cards[card][1];
			}
		}
		return score;
	}
	
	
	/**
	 * The actual simulation of the game logic is implemented in the
	 * following method.
	 * 
	 */
	public static void letsPlay(){
		int[][] cards = new int[2][52];
		cards = deal();
		
		boolean dealerBurst = false,playerBurst = false;
		int pointer = 2,dealerScore = 0;
		int score = calculateScore(cards,0,0);
		System.out.println("score: "+score);
		
		int choice = 0;
		Scanner takeInput = new Scanner(System.in);
		
		if(score == 21){
			System.out.println("BLACKJACK!");
		}
		else{
			System.out.print("Your choice:\n1.Hit\n2.Stand");
			choice = takeInput.nextInt();
			
			while(choice != 2){
				
				int[] card = MyCards.drawACard(pointer);
				MyCards.display(card);
				cards[pointer][0] = card[0];
				cards[pointer][1] = card[1];
				pointer++;
				
				score = calculateScore(cards, score,0);
				System.out.println("score: " +score);
				
				if(score > 21){
					System.out.println("Burst!");
					playerBurst = true;
					break;
				}
				
				else if(score == 21){
					System.out.println("BLACKJACK!");
					break;
				}
				
				
				System.out.println("Your choice:\n1.Hit\n2.Stand");
				choice = takeInput.nextInt();
			}
		}
		
		int playerStop = pointer;
		
		System.out.println("=======DEALER'S TURN==========");
		
		int[] card = MyCards.drawACard(pointer);
		MyCards.display(card);
		cards[pointer][0] = card[0];
		cards[pointer][1] = card[1];
		pointer++;
		
		card = MyCards.drawACard(pointer);
		MyCards.display(card);
		cards[pointer][0] = card[0];
		cards[pointer][1] = card[1];
		pointer++;
		
		dealerScore = calculateScore(cards, dealerScore,playerStop);
		System.out.println("score: " +dealerScore);
		
		while(dealerScore <= 17){
			card = MyCards.drawACard(pointer);
			MyCards.display(card);
			cards[pointer][0] = card[0];
			cards[pointer][1] = card[1];
			pointer++;
			
			dealerScore = calculateScore(cards, dealerScore,playerStop);
			System.out.println("score: " +dealerScore);
			
			/*if(dealerScore > 17){
				System.out.println("Dealer Stands!");
				break;
			}*/
		}
		
		System.out.println("========== GAME RESULTS ===========");
		System.out.println("Your score: " +score+ "\nDealer's score: " +dealerScore);
		
		if(dealerScore > 21){
			System.out.println("Dealer is Burst!");
			dealerBurst = true;
		}
		
		if(dealerBurst && playerBurst || (score == dealerScore)){
			System.out.println("ITS A DRAW!");
		}
		
		else if(dealerBurst && !playerBurst){
			System.out.println("Player Wins!");
		}
		
		else if(!dealerBurst && playerBurst){
			System.out.println("Dealer Wins!");
		}
		
		else if(!playerBurst && !dealerBurst){
			if(dealerScore > score){
				System.out.println("Dealer Wins!");
			}
			else if(score > dealerScore){
				System.out.println("Player Wins!");
			}
			
		}
		
		
		MyCards.cardDeck = MyCards.pushBackUsedCards(MyCards.cardDeck, pointer, cards);
		//MyCards.display();

		//takeInput.close();
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		newDeck.ShuffleArray();
		
		System.out.println("==========BLACKJACK==========");
		int choice = 0,iteration = 0;
		Scanner takeChoice = new Scanner(System.in);
		
		while(choice != 2){
			
			letsPlay();
			iteration++;
			
			System.out.println("===========Choice:==========\n1.Deal again!\n2.Exit!");
			choice = Integer.parseInt(takeChoice.nextLine());
			
			if(iteration >= 3){
				newDeck.ShuffleArray();
			}
		}
		
		takeChoice.close();
	}
			
}
