import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;;
public class OperationsQueue {
    // * List
    private final List<Integer> operations = new ArrayList<>();

    // * flag for fist time check the empty operations list
    // private boolean flag = true;

    // * Reentrant lock
    private ReentrantLock rel;

    OperationsQueue(ReentrantLock rel)
    {
        this.rel = rel;
    }

    public void addSimulation(int totalSimulation) {

        // Add 50 random numbers in the operations list. The number will be range from -100 to 100. It cannot be zero.
        for (int i = 0; i < totalSimulation; i++) {
            int random = (int) (Math.random() * 200) - 100;
            if (random != 0) {
                operations.add(random);
                System.out.println(i + ". New operation added: " + random);
            }
            // add small delay to simulate the time taken for a new customer to arrive
            try {
                Thread.sleep((int) (Math.random() * 80));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        operations.add(-9999);
    }
    public void add(int amount) {
        operations.add(amount);
    }
    
    // Critical section
    public int getNextItem(String name) {
        // add a small delay to simulate the time taken to get the next operation.
        // * if operations list is empty then it will be an infinite loop.
        // System.out.println(name + " - hapaning");
        // if(flag){
        //     if(operations.isEmpty()){
        //         System.out.println("List is empty");
        //         flag = false;
        //         return 0;
        //     }
        // }
        System.out.println("List size - " + operations.size());
        
        boolean ans = rel.tryLock();
        int x = 0;
        
        // * ans is True if resource is not held by any other thread
        if(ans){

           System.out.println("Start - "+name);
            try {
                if(!operations.isEmpty())
                {
                  x = operations.remove(0);
                }
                else
                {
                    x = -9999;
                }
               Thread.sleep((int) (Math.random() * 80));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(name+" completed");
                rel.unlock();
            }

        }
        else
        {
            System.out.println("waiting for - " + name);
        }
        
        return x;
    }
}
