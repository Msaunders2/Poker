import java.util.Random;

public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck
	private int totalcards = 52;
    private Random r = new Random();

	public Deck(){
		
		int counter = 0;
		top = 0;
		
		cards = new Card[52];

		for(int suit=1; suit<5; suit++){
			for(int rank=1; rank<14; rank++) {
				cards[counter] = new Card (suit,rank);
				counter++;
			}
		}
	}
	

	// shuffling deck here
	public void shuffle(){
		Random rnd = new Random();

		for(int i=0; i<52; i++){
			Card temp = cards[i];
			int random_index = rnd.nextInt(52);
			cards[i] = cards[random_index];
            cards[random_index] = temp;

		}

	}
	
	public Card deal(){
		Card deal = cards[top];
		top++;
		return deal;
		// deal the top card in the deck
	}
	
	//for the tester so that I can have acess to the deck and to cards
	public Card[] getCards(){
		return cards;
	}

	public Card getTop(){
		return cards[top];
	}
}

