package myApp;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PokerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokerAppApplication.class, args);
		ArrayList<Hand> handlist = new ArrayList<Hand>();
		Deck deck = new Deck();
		//Number of hands
		for (int iPlayerCount = 0; iPlayerCount <11; iPlayerCount++) {
			if(iPlayerCount==10) {
				System.out.println("Cannot have more than 10 Players, Playing for rest 10 hands.\n");
				break;
			}
			Hand hand = new Hand(deck);
			handlist.add(hand);
		}
		System.out.println("Total hands are : "+handlist.size()+"\n");
		System.out.println("Original Order of Hands ");	
        for (Hand hand : handlist) {
        	hand.display();
		}
   
        // Compare  Hands		

     		for(int iHand=0;iHand<handlist.size();iHand++) {
     			
     			for(int jHand=1; jHand<handlist.size()-iHand;jHand++) {
     			
     				if(handlist.get(jHand-1).compareTo(handlist.get(jHand))== 1) {
     					
     					Hand hand=handlist.get(jHand-1);
     					handlist.set(jHand-1,handlist.get(jHand) );
     					handlist.set(jHand, hand);
     				}
     				
     			}				
     			
     		}
     		Collections.reverse(handlist);
     		System.out.println("\n Order of hands as per poker");				
    		for (Hand hand : handlist) {
    			hand.display();
    		}
    		
	}
	
}
