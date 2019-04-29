package myApp;

public class Card{
    private int iValue, iSuit;

    private static String[] suits = { "H", "S", "D", "C" };
    private static String[] values  = {"2", "3", "4", "5", "6", "7", 
                                       "8", "9", "T", "J", "Q", "K","A" };

    public static String valueAsString( int __values ) {
        return values[__values];
    }

    Card(int iSuit, int iValue)
    {
        this.iValue=iValue;
        this.iSuit=iSuit;
    }

    public @Override String toString()
    {
         // return values[iValue] + " of " + suits[iSuit];
    	return values[iValue] + suits[iSuit];
    }

    public int getValue() {
         return iValue;
    }

    public int getSuit() {
        return iSuit;
    }
}
