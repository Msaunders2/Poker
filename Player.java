import java.util.Collections;
import java.util.ArrayList;

public class Player {
	
		
	private ArrayList<Card> hand; 
    // the player's cards
	private double bankroll;
    private double bet;

		
	public Player(){
        hand = new ArrayList<Card>();
        bankroll = 50;
        bet = 0;
   
	}

	public void addCard(Card c){
        hand.add(c); 

	}

	public void removeCard(Card c){
        hand.remove(c);
        }
		
        public void bets(double amt){
            bankroll = bankroll - amt;
            bet = amt;

        }

        public void winnings(double odds){
            bankroll = bankroll + odds * bet;
        }

        public double getBankroll(){
            return bankroll;
        }

        public void sortHand(){
            Collections.sort(hand);
        }

        public ArrayList<Card> getHand() {
            return hand;
        }

        public void swapCard(int i, Card c) {
            hand.add(i, c);
        }

}


