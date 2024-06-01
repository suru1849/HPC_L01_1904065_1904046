//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {

        // * Reentrant implement
        ReentrantLock rel = new ReentrantLock();
        

        System.out.println("Hello and welcome!");

        System.out.printf("Initializing banking system..");
        
        // * Number of simulation
        int totalNumberOfSimulaion = 10;

        // * Initialize object of OperationsQueue class
        OperationsQueue operationsQueue = new OperationsQueue(rel);

        // * Initialize object of Bank class
        Bank bank = new Bank("123", operationsQueue);

        System.out.println("Initializing simulation....");
        // * Using lemda expression Runnable interface's implemented
        Thread simulationThread = new Thread(() -> {
            operationsQueue.addSimulation(totalNumberOfSimulaion);
        });
        simulationThread.start();


        System.out.println("Initializing deposit systen....");
        // * Using lemda expression Runnable interface's implemented
        Thread depositThread = new Thread(() -> {
            bank.deposit();
        });
        depositThread.start();
        System.out.println("completed");

        System.out.println("Initializing withdraw systen....");
        // * Using lemda expression Runnable interface's implemented
        Thread withdrawThread = new Thread(() -> {
            bank.withdraw();
        });
        withdrawThread.start();
        System.out.println("completed");



    }
}