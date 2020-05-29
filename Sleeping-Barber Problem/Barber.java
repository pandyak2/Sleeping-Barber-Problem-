package Trying2;

import java.util.Random;

class Barber implements Runnable
{
    BarberShop shop;
 
    public Barber(BarberShop shop)
    {
        this.shop = shop;
    }
    public void run()
    {
    	double mean = 10, std = 3;
    	Random ran = new Random();
    	
        try
        {
        	
            Thread.sleep(Math.round((mean + std*ran.nextGaussian())*100.0/100.0));
        }
        catch(InterruptedException iex)
        {
            iex.printStackTrace();
        }
        System.out.println("Barber "+ Thread.currentThread().getId() +" started..");
        while(true)
        {
            try {
				shop.cutHair();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}




