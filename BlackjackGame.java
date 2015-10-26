import java.util.Scanner;

public class BlackjackGame {

	static MyCards newDeck = new MyCards();
	
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
	
	public static int calculateScore(int[][] cards,int scoreSoFar){
		int score = 0;
		for(int card=0;card<cards.length;card++){
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
	
	public static void letsPlay(){
		int[][] cards = new int[2][52];
		cards = deal();
		
		//int usedCards[][] = new int[52][2];
		int pointer = 2;//,cardsAtHand = 2;
		int score = calculateScore(cards,0);
		//System.out.println("score: "+score);
		
		int choice = 0;
		Scanner takeInput = new Scanner(System.in);
		System.out.print("Your choice:\n1.Hit\n2.Stand");
		choice = takeInput.nextInt();
		
		while(choice != 2){
			
			int[] card = MyCards.drawACard(pointer);
			MyCards.display(card);
			//usedCards[pointer][0] = card[0];
			//usedCards[pointer][1] = card[1];
			cards[pointer][0] = card[0];
			cards[pointer][1] = card[1];
			pointer++;
			
			score = calculateScore(cards, score);
			System.out.println("score: " +score);
			
			if(score > 21){
				System.out.println("Burst!");
				break;
			}
			
			System.out.print("Your choice:\n1.Hit\n2.Stand");
			choice = takeInput.nextInt();
		}
		
		
		
		
		MyCards.cardDeck = MyCards.pushBackUsedCards(MyCards.cardDeck, pointer, cards);
		//MyCards.display();

		takeInput.close();
	}
	
	public static void main(String[] args) {
		newDeck.ShuffleArray();
		//newDeck.display();
		//deal();
		letsPlay();
	}
			
}
