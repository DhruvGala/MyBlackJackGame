/*
 * filename: MyCards.java
 * 
 * log: 10-24-2015
 * 
 * Versions: 1.0 basic implementation of a card deck.
 * 			 1.1 shuffling process of the cards implemented.
 * 			 1.2 drawing card and pushing used cards at back of the deck was implemented
 * 			Now working on draw card and game logic.
 */

import java.util.Random;



/**
 * implementation of card deck for my next project a BlackJack Game simulator.
 *
 * @author DhruvGala
 *
 */
public class MyCards {
	static int[][] cardDeck = { { 1, 1 }, { 1, 2 }, { 1, 3 }, { 1, 4 }, { 1, 5 }, { 1, 6 }, 
			{ 1, 7 }, { 1, 8 }, { 1, 9 }, { 1, 10 }, { 1, 11 }, { 1, 12 }, { 1, 13 }, 
			{ 2, 1 }, { 2, 2 }, { 2, 3 }, { 2, 4 }, { 2, 5 }, { 2, 6 },
			{ 2, 7 }, { 2, 8 }, { 2, 9 }, { 2, 10 }, { 2, 11 }, { 2, 12 }, { 2, 13 },
			{ 3, 1 }, { 3, 2 }, { 3, 3 }, { 3, 4 }, { 3, 5 }, { 3, 6 }, 
			{ 3, 7 }, { 3, 8 }, { 3, 9 }, { 3, 10 }, { 3, 11 }, { 3, 12 }, { 3, 13 },
			{ 4, 1 }, { 4, 2 }, { 4, 3 }, { 4, 4 }, { 4, 5 }, { 4, 6 }, 
			{ 4, 7 }, { 4, 8 }, { 4, 9 }, { 4, 10 }, { 4, 11 }, { 4, 12 }, { 4, 13 }, };

	
	/**
	 * method to display;
	 * 
	 */
	public static void display() {
		
		for (int i = 0; i < 52; i++) {
			String card = "";
			if (cardDeck[i][1] == 1) {
				card += "A";
			} else if (cardDeck[i][1] == 11) {
				card += "J";
			} else if (cardDeck[i][1] == 12) {
				card += "Q";
			} else if (cardDeck[i][1] == 13) {
				card += "K";
			} else {
				card += cardDeck[i][1];
			}
			
			//card += " of ";

			if (cardDeck[i][0] == 1) {
				card += "C ";
			} else if (cardDeck[i][0] == 2) {
				card += "H ";
			} else if (cardDeck[i][0] == 3) {
				card += "S ";
			} else if (cardDeck[i][0] == 4) {
				card += "D ";
			}

			/*if((i+1)%13 == 0){
				System.out.println();
			}*/
			
			System.out.print(card);
		}
		
	}
	
	
	/**
	 * display method to show a card.
	 * 
	 * @param thisCard
	 */
	public static void display(int thisCard[]){
		String card = "";
		if (thisCard[1] == 1) {
			card += "A";
		} else if (thisCard[1] == 11) {
			card += "J";
		} else if (thisCard[1] == 12) {
			card += "Q";
		} else if (thisCard[1] == 13) {
			card += "K";
		} else {
			card += thisCard[1];
		}
		
		//card += " of ";

		if (thisCard[0] == 1) {
			card += "C ";
		} else if (thisCard[0] == 2) {
			card += "H ";
		} else if (thisCard[0] == 3) {
			card += "S ";
		} else if (thisCard[0] == 4) {
			card += "D ";
		}
		
		System.out.println(card);
		
	}
	
	
	/**
	 * drawing a card from the deck is performed using this method
	 * 
	 * 
	 * @param i
	 * @return
	 */
	public static int[] drawACard(int i){
		return cardDeck[i];
	}
	
	/**
	 * Method to shuffle the cards.
	 * 
	 */
	public void ShuffleArray()
	{
		int index, temp[][] = new int[1][2];
	    Random random = new Random();
	    for (int i = 51; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        temp[0][0] = cardDeck[index][0];
	        temp[0][1] = cardDeck[index][1];
	        
	        cardDeck[index][0] = cardDeck[i][0];
	        cardDeck[index][1] = cardDeck[i][1];
	        
	        cardDeck[i][0] = temp[0][0];
	        cardDeck[i][1] = temp[0][1];
	    }
	}
	
	
	/**
	 * After each deal the used cards are pushed back in the deck. This logic is implemented
	 * here in this method.
	 * 
	 * @param thisDeck
	 * @param cardPointer
	 * @param usedCards
	 * @return
	 */
	public static int[][] pushBackUsedCards(int[][] thisDeck, int cardPointer, int[][] usedCards){
		int[][] newDeck = new int[52][2];
		int i,j;
		for(i = cardPointer,j=0;i<thisDeck.length;i++,j++){
			newDeck[j][0] = thisDeck[i][0];
			newDeck[j][1] = thisDeck[i][1];
		}
		
		for(i=0;j<52;j++,i++ ){
			newDeck[j][0] = usedCards[i][0];
			newDeck[j][1] = usedCards[i][1];
		}
		return newDeck;
	}
	
	/**
	 * just for debugging.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new MyCards().ShuffleArray();
		display();
	}
}
