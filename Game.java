import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
	
	private Player p;
	private Deck cards;
	
	public Game(String[] testHand){
		p = new Player();
		cards = new Deck();
		cards.shuffle();
		

        for(int i=0; i<5; i++){
            int rank = Integer.parseInt(testHand[i].substring(1));
            int suit = 0;

            if(testHand[i].charAt(0)=='h'){
				 suit = 1;
			}else if(testHand[i].charAt(0)=='s'){
				 suit=2;
			}else if(testHand[i].charAt(0)=='c'){
				suit=3;
			}else{
				suit=4;
			}
            Card newhandCards = new Card(suit,rank);
            
            p.addCard(newhandCards);
		}
	
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush
	}
	
	public Game(){
		Scanner input = new Scanner(System.in);
		p = new Player();
		cards = new Deck();
		cards.shuffle();

		for(int i=1;i<=5;i++){
			Card firsthand = cards.deal();
			p.addCard(firsthand);
		}

	}
	
	public void play(){
		System.out.println("Welcome to poker you have 50 token");
		System.out.println("what is your bet?");
		Scanner s = new Scanner(System.in);
		double betAmount = s.nextInt();
		p.bets(betAmount);
		
		System.out.println(p.getHand());

		System.out.println("would you like to reject any of these cards? 1 to keep cards, 2 to reject cards");
		
		int rejectOrAccept = s.nextInt();


		if(rejectOrAccept == 2){
			System.out.println("How many cards do you want to rejects? 1,2,3,4,5");
			int numberOfRejectedCards = s.nextInt();

			for(int i=0; i< numberOfRejectedCards; i++)	{
				System.out.println("Which position of card do you want to replace? (0-4) 0-first card 4- 5th card");
				int positionReplace = s.nextInt();
				
                p.getHand().remove(p.getHand().get(positionReplace));
				p.swapCard(positionReplace, cards.deal());

			}

		}
		
        System.out.println(p.getHand());

		System.out.println(checkHand(p.getHand()));
		
		winnings();

		System.out.println(p.getBankroll());

	}
	
	public String checkHand(ArrayList<Card> hand){
		 if(isRoyalFlush(hand) == true ){
			return ("Royal Flush");
		}else if(isStraightFlush(hand) == true){
			return ("Straight Flush");

		}else if(isFourOfAKind(hand) == true){
			return ("Four of a kind");

		}else if(isFullHouse(hand) == true){
			return ("Full house");

		}else if(isFlush(hand) == true){
			return ("Flush");

		}else if(isStraight(hand) == true){
			return ("Straight");

		}else if(isThreeOfAKind(hand) == true){
			return("Three of a kind");

		}else if(isTwoPairs(hand) == true){
			return("Two pairs");

		}else if(isOnePair(hand) == true){
			return("One pair");
		}else{
			return ("No pairs");
		}

    }
	
	private void winnings(){
		String s = getCheckHand(); 
			System.out.println("you got" + s);
    
        switch(s) {

		case "Royal Flush":
		p.winnings(250);
		break;

		case "Straight Flush":
		p.winnings(50);
		break;

		case "Four of a kind":
		p.winnings(25);
		break;

		case "Full house":
		p.winnings(6);
		break;

		case "Flush":
		p.winnings(5);
		break;

		case "Straight":
		p.winnings(4);
		break;

		case "Three of a kind":
		p.winnings(3);
		break;

		case "Two pairs":
		p.winnings(2);
		break;

		case "OnePair":
		p.winnings(1);
		break;

		case "No pairs":
		p.winnings(0);
		break;

		default:
		System.out.println("error in winnings method");
		break;
		}

	}

	public String getCheckHand() {
		return checkHand(p.getHand());
	}

	private boolean isFlush (ArrayList<Card> hand){
		int suit = hand.get(0).getSuit();

		if(suit == hand.get(0).getSuit() && suit == hand.get(1).getSuit() && 
		suit == hand.get(2).getSuit() && suit == hand.get(3).getSuit()
		 && suit == hand.get(4).getSuit()){
			return true;
        }else{
		
        return false;
	    }
    }


	private boolean isStraightFlush (ArrayList<Card> hand){
		Collections.sort(hand);
		
		int suit = hand.get(0).getSuit();

        if (isStraight(hand) && isFlush(hand)){
            return true;
        }else{
            return false;
        }
	
	}
	
	private boolean isRoyalFlush (ArrayList<Card> hand){
		Collections.sort(hand);

		boolean ace = false;
		boolean jack = false;
		boolean queen = false;
		boolean king = false;
		boolean ten = false;

		for(int i = 0; i>5; i++){
			if(hand.get(0).getRank() == 1){
				ace = true;
			}else if(hand.get(1).getRank() == 13){
				king = true;
			}else if(hand.get(2).getRank() == 12){
				queen = true;
			}else if(hand.get(3).getRank() == 11){
				jack = true;
			}else if(hand.get(4).getRank() == 10){
				ten = true;
			}

		}

		while(ace == true && king == true && queen  == true && jack  == true && 
		ten  == true){
			if(isFlush(hand))
			return true;

		}
		
		return false; 
	}

	private boolean isStraight(ArrayList<Card> hand){
		Collections.sort(hand);

		for(int i=0; i<4; i++){
			if (hand.get(i).getRank() + 1 != (hand.get(i+1).getRank()))
				return false;
					}

						return true;
					
		}

	private boolean isFourOfAKind(ArrayList<Card> hand){
		Collections.sort(hand);

		if(hand.get(0).getRank() == hand.get(1).getRank() && hand.get(1).getRank() == hand.get(2).getRank() && hand.get(2).getRank() == hand.get(3).getRank()|| hand.get(1).getRank() == hand.get(2).getRank() && hand.get(2).getRank() == hand.get(3).getRank() && hand.get(3).getRank() == hand.get(4).getRank()){
			return true;
        }else{
            return false;
        }


	}

	private boolean isFullHouse(ArrayList<Card> hand){
		Collections.sort(hand);

		if(isThreeOfAKind(hand) && isOnePair(hand)){
			return true;
        }else{
            return false;
        }
	}

	private boolean isThreeOfAKind(ArrayList<Card> hand){
		Collections.sort(hand);

		if(hand.get(0).getRank() == hand.get(2).getRank() || hand.get(1).getRank() == hand.get(3).getRank() || hand.get(2).getRank() == hand.get(4).getRank()){
			return true;
        }else{
            return false;
        }

	}

	private boolean isTwoPairs(ArrayList<Card> hand){
		Collections.sort(hand);

		if(hand.get(0).getRank() == hand.get(1).getRank() && hand.get(2).getRank() == hand.get(3).getRank() || hand.get(0).getRank() == hand.get(1).getRank() && hand.get(3).getRank()== hand.get(4).getRank()|| hand.get(1).getRank()== hand.get(2).getRank() && hand.get(3).getRank()== hand.get(4).getRank()){
			return true;
		}else{
			return false;
		}
	
	}


	private boolean isOnePair(ArrayList<Card> hand){
		Collections.sort(hand);

		if(hand.get(0).getRank() == hand.get(1).getRank() || hand.get(1).getRank() == hand.get(2).getRank() || hand.get(2).getRank() == hand.get(3).getRank() || hand.get(3).getRank() == hand.get(4).getRank()){
			return true;
		}else{

		
        return false;
		}
	}


} 
