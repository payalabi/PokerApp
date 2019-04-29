package myApp;

import java.util.ArrayList;
import java.util.Comparator;

public final class Hand {
	private final Card[] cards;
	private final int[] order;
	private final ArrayList<Card> cardOrderedList;

	Hand(Deck d) {
		order = new int[6];
		cards = new Card[5];
		cardOrderedList = new ArrayList<Card>();

		for (int iCount = 0; iCount < 5; iCount++) {
			cards[iCount] = d.drawFromDeck();
			cardOrderedList.add(cards[iCount]);
		}
// Sorting based on card value
		cardOrderedList.sort(new Comparator<Card>() {

			@Override
			public int compare(Card o1, Card o2) {

				return o1.getValue() - o2.getValue();
			}
		});

		int[] cardvalues = new int[13];
		int[] orderedvalues = new int[5];
		boolean flush = true, straight = false;
		int sameCards = 1, sameCards2 = 1;
		int largeGroupValue = 0, smallGroupValue = 0;
		int index = 0;
		int topStraightValue = 0;

		for (int iCount = 0; iCount < 13; iCount++) {
			cardvalues[iCount] = 0;
		}
		for (int iCount = 0; iCount <= 4; iCount++) // assign hand card values and increment for pairs
		{
			cardvalues[cards[iCount].getValue()]++;

		}

		for (int iCount = 0; iCount < 4; iCount++) // check for flush
		{
			if (cards[iCount].getSuit() != cards[iCount + 1].getSuit())
				flush = false;
		}

		for (int iCount = 12; iCount >= 0; iCount--) {
			if (cardvalues[iCount] > sameCards) {
				if (sameCards != 1)
				{
					sameCards2 = sameCards;
					smallGroupValue = largeGroupValue;
				}

				sameCards = cardvalues[iCount];
				largeGroupValue = iCount;

			} else if (cardvalues[iCount] > sameCards2) {
				sameCards2 = cardvalues[iCount];
				smallGroupValue = iCount;
			}
		}

		for (int iCount = 12; iCount >= 0; iCount--) {
			if (cardvalues[iCount] == 1) {
				orderedvalues[index] = iCount;
				index++;
			}
		}

		for (int iCount = 0; iCount <= 8; iCount++)// check for straight

		{
			if (cardvalues[iCount] == 1 && cardvalues[iCount + 1] == 1 && cardvalues[iCount + 2] == 1
					&& cardvalues[iCount + 3] == 1 && cardvalues[iCount + 4] == 1) {
				straight = true;
				topStraightValue = iCount + 4; 
				break;
			}
		}

		if (cardvalues[8] == 1 && cardvalues[9] == 1 && cardvalues[10] == 1 && cardvalues[11] == 1
				&& cardvalues[12] == 1) // ace high
		{
			straight = true;
			topStraightValue = 12; 

		}

		for (int x = 0; x <= 5; x++) {
			order[x] = 0;
		}

		// hand evaluation
		if (sameCards == 1) {
			order[0] = 1;
			order[1] = orderedvalues[0];
			order[2] = orderedvalues[1];
			order[3] = orderedvalues[2];
			order[4] = orderedvalues[3];
			order[5] = orderedvalues[4];
		}

		if (sameCards == 2 && sameCards2 == 1) {
			order[0] = 2;
			order[1] = largeGroupValue; // rank of pair
			order[2] = orderedvalues[0];
			order[3] = orderedvalues[1];
			order[4] = orderedvalues[2];
		}

		if (sameCards == 2 && sameCards2 == 2) // two pair
		{
			order[0] = 3;
			// rank of greater pair
			order[1] = largeGroupValue > smallGroupValue ? largeGroupValue : smallGroupValue;
			order[2] = largeGroupValue < smallGroupValue ? largeGroupValue : smallGroupValue;
			order[3] = orderedvalues[0]; // extra card
		}

		if (sameCards == 3 && sameCards2 != 2) //three of a kind
		{
			order[0] = 4;
			order[1] = largeGroupValue;
			order[2] = orderedvalues[0];
			order[3] = orderedvalues[1];
		}

		if (straight && !flush) {
			order[0] = 5;

		}

		if (flush && !straight) {
			order[0] = 6;
			order[1] = orderedvalues[0]; 
			order[2] = orderedvalues[1];
			order[3] = orderedvalues[2];
			order[4] = orderedvalues[3];
			order[5] = orderedvalues[4];
		}

		if (sameCards == 3 && sameCards2 == 2) //full house
		{
			order[0] = 7;
			order[1] = largeGroupValue;
			order[2] = smallGroupValue;
		}

		if (sameCards == 4) //four of a kind
		{
			order[0] = 8;
			order[1] = largeGroupValue;
			order[2] = orderedvalues[0];
		}

		if (straight && flush) {
			order[0] = 9;
			if (topStraightValue == 12)
				order[0] = 10;

		}

	}

	void display() {
		String str;
		switch (order[0]) {

		case 1:
			str = "high card";
			break;
		case 2:
			str = "pair of " + Card.valueAsString(order[1]) + "'s";
			break;
		case 3:
			str = "two pair " + Card.valueAsString(order[1]) + " and " + Card.valueAsString(order[2]);
			break;
		case 4:
			str = "three of a kind " + Card.valueAsString(order[1]) + "'s";
			break;
		case 5:
			str = Card.valueAsString(order[1]) + " high straight";
			break;
		case 6:
			str = "flush";
			break;
		case 7:
			str = "full house " + Card.valueAsString(order[1]) + " over " + Card.valueAsString(order[2]);
			break;
		case 8:
			str = "four of a kind " + Card.valueAsString(order[1]);
			break;
		case 9:
			str = "straight flush " + Card.valueAsString(order[1]) + " high";
			break;
		case 10:
			str = "Royal flush" + Card.valueAsString(order[1]) + " high";
		default:
			str = "error in Hand.display: value[0] contains invalid value";
		}
		String strhand = "<Hand[";

		// Arrays.so

		for (int iCount = 0; iCount < 5; iCount++) {			
			strhand += cardOrderedList.get(iCount) + ",";
		}
		strhand += "]'" + str + "'>";
		System.out.println(strhand);
	}

// Compare Hands
	int compareTo(Hand that) {
		for (int iCount = 0; iCount < 6; iCount++) {
			if (this.order[iCount] > that.order[iCount])
				return 1;
			else if (this.order[iCount] < that.order[iCount])
				return -1;
		}
		return 0; // if hands are equal
	}

}
