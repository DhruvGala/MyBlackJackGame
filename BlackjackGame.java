
public class BlackjackGame {

	static MyCards newDeck = new MyCards();
	
	public static void deal(){
		
		int cardPointer = 0;
		int usedCards[][] = new int[52][2];
		boolean deal = true;
		while(deal){
			int newCard[] = newDeck.drawACard(cardPointer);	
			newDeck.display(newCard);
			usedCards[cardPointer][0] = newCard[0];
			usedCards[cardPointer][1] = newCard[1];
			cardPointer++;
			
			newCard = newDeck.drawACard(cardPointer);
			newDeck.display(newCard);
			usedCards[cardPointer][0] = newCard[0];
			usedCards[cardPointer++][1] = newCard[1];
			
			deal = false;
		}
		
		newDeck.cardDeck = newDeck.pushBackUsedCards(newDeck.cardDeck, cardPointer, usedCards);
		//System.out.println("=======AFTER 2 deals=========length:"+newDeck.cardDeck.length);
		//newDeck.display();
	}
	
	public static void main(String[] args) {
		newDeck.ShuffleArray();
		//newDeck.display();
		deal();
	}
			
}
