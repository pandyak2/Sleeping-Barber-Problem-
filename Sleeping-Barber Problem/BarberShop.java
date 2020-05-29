package Trying2;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;



class BarberShop
{
	
	Scanner sn = new Scanner(System.in);
	static LinkedBlockingQueue<Object> lbq;
	int nchair;
	
	public BarberShop()
	{
		System.out.println("Enter number of chairs: ");
    	nchair = sn.nextInt();
    	lbq = new LinkedBlockingQueue<Object>(nchair);
	}

   
	public void cutHair() throws InterruptedException
    {
        Customer customer;
        
        
        synchronized (lbq)
        {
 
            while(lbq.size()==0)
            {
                System.out.println("Barber " +Thread.currentThread().getId() +" is waiting for customer.");
                try
                {
                    lbq.wait();
                }
                catch(InterruptedException iex)
                {
                    iex.printStackTrace();
                }
            }
            System.out.println("Barber" +Thread.currentThread().getId()+ " found customer");
            
            customer = (Customer) lbq.poll();
        }
        
        String custName = customer.getName();
        double mean = 6, std = 2;
    	Random ran = new Random();
    	long timing=0;
        System.out.println("Cuting hair of  "+custName+" by barber "+Thread.currentThread().getId());
        timing = Math.round((mean + std*ran.nextGaussian())*100.0/100.0);
        TimeUnit.SECONDS.sleep(timing);
        
        
        System.out.println("#####Completed Cuting hair of Customer : "+ custName + " in "+timing+ " seconds by Barber " +Thread.currentThread().getId() + "########");
    }
    public void add(Customer customer)
    {
        System.out.println("*****Customer : "+customer.getName()+ " entering the shop at "+customer.getInTime() +"*****");
        
        synchronized (lbq)
        {
            if(lbq.size() == nchair)
            {
                System.out.println("~~~~No chair available, Sorry! Have a Nice Day~~~~~"+customer.getName());
                
                return ;
            }
 
            lbq.offer(customer);
            System.out.println("Customer : "+customer.getName()+ " got the chair.");
             
            if(lbq.size()==1)
                lbq.notify();
        }
    }
}