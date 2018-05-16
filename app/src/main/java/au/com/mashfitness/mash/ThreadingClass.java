package au.com.mashfitness.mash;

public class ThreadingClass extends Thread {

    public void run()
    {
        try
        {
            // Displaying the thread that is running
            System.out.println ("New thread, " +
                    Thread.currentThread().getId() +
                    " is running");

        }
        catch (Exception e)
        {
            // Throwing an exception
            System.out.println ("Exception is caught");
        }
    }

}
