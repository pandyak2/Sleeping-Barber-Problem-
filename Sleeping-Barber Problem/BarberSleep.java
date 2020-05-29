

package Trying2;
import java.util.Scanner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



 
public class BarberSleep {
 
    public static void main(String args[])
    {

    	BarberShop shop = new BarberShop();
        try (Scanner sn = new Scanner(System.in)) {
			System.out.println("Enter the number of Barbers");
			int numberOfBarbers = sn.nextInt();
    
			System.out.println("Enter number of customers : ");
			int custNumber = sn.nextInt();
			
			//Barber barber = new Barber(shop);
			waitingroom wg = new waitingroom(shop,custNumber);

			Thread thwg = new Thread(wg);
			thwg.start();
			
			ExecutorService bar = Executors.newFixedThreadPool(numberOfBarbers);
     
      
			
			for(int i=0 ; i<= numberOfBarbers;i++)
			{
				
				bar.execute(new Barber(shop));
			}
		}
		
    }
}
