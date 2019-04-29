package myApp;

import java.util.Random;
import java.util.ArrayList;
public class Deck {
    private ArrayList<Card> cards;

     Deck()
    {
        cards = new ArrayList<Card>();
        int index_1, index_2;
        Random generator = new Random();
        Card temp;

        for (int iSuit=0; iSuit<=3; iSuit++)
        {
            for (int iValue=0; iValue<=12; iValue++)
             {
               cards.add( new Card(iSuit,iValue) );
             }
        }

        int size = cards.size() -1;

        for (int iIndex=0; iIndex<100; iIndex++) //Card Shuffling 
        {
            index_1 = generator.nextInt( size );
            index_2 = generator.nextInt( size );

            temp = (Card) cards.get( index_2 );
            cards.set( index_2 , cards.get( index_1 ) );
            cards.set( index_1, temp );
        }
    }

    public Card drawFromDeck()
    {       
        return cards.remove( cards.size()-1 );
    }


}