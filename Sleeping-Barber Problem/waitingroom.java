package Trying2;

import java.util.Date;
import java.util.Random;

import java.util.concurrent.TimeUnit;

class waitingroom implements Runnable
{
    BarberShop shop;
    int custNumber;
    int i =0;
    public waitingroom(BarberShop shop,int custNumber)
    {
        this.shop = shop;
        this.custNumber = custNumber;
    }
 
    public void run()
    {
    	double[] custList = new double[custNumber];
    	double mean = 6, std = 3;
    	Random ran = new Random();
    	Customer customer = new Customer(shop);
        for(int i=0;i<custNumber;i++)
        {
 
        	
            customer.setInTime(new Date());
            Thread thcustomer = new Thread(customer);
            custList[i] = Math.round((mean + std*ran.nextGaussian())*20.0/100.0);
            customer.setName("Customer Thread "+thcustomer.getName());
            thcustomer.start();
            
 
            try
            {
                TimeUnit.SECONDS.sleep((long)custList[i]);
            }
            catch(InterruptedException iex)
            {
                iex.printStackTrace();
            }
        }
 
}
}


