public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r){

		this.suit = s;
		this.rank = r;
		
	}
	
	public int compareTo(Card c){
		if (rank == c.rank){

			if(suit > c.suit){
				return 1;

			}else if(suit == c.suit){
				return 0;

			}else {
				return -1;
			}
		

		}else if(rank > c.rank){
			return 1;
		}else {
			return -1;
		}

	}
	
	public String toString(){

		switch(suit){

			case 1:
			return rankPlusSuit() + "heart";

			case 2:
			return rankPlusSuit() + "spade";
			
			case 3:
			return rankPlusSuit() + "club";
			
			case 4:
			return rankPlusSuit() + "diamond";

			default:
			return "error in switch";

		}
	}
		
		*/
	public String rankPlusSuit(){
		if (rank == 1)
			return "ace";
		if (rank > 1 && rank <= 10)
			return "" + rank;
		if (rank == 11)
			return "jack";
		if (rank == 12)
			return "queen";
		if (rank == 13)
			return "king";
		else{
			return "error in String rankPlusSuit()";
		}

	}
		

	public int getSuit(){
		return suit;
	}

	public int getRank(){
		return rank;
	}	

}


