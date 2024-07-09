package com.Assignment;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import java.util.Random;

public class Main { //Main method start
    public static void main(String[] args) {
        // Create actor system
        final ActorSystem system = ActorSystem.create("BankSystem");

        // Create BankAccount actor
        final ActorRef bankAccount = system.actorOf(BankAccount.props(), "bankAccount");

        // Start with initial balance
        System.out.println("Initial Balance: £100");

        // Generate 10 random transactions
        Random random = new Random();
        int[] transactions = random.ints(10, -1000, 1000).toArray();

        // Send transactions to BankAccount actor
        for (double amount : transactions) {
            if (amount > 0) {
                bankAccount.tell(new Deposit(amount), ActorRef.noSender());
            } else {
                bankAccount.tell(new Withdrawal(-amount), ActorRef.noSender());
            }
            try {
                Thread.sleep(500); // Simulate some delay between transactions for better readability
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Shutdown the system after transactions are done
        //System.out.println("System will terminated");
        system.terminate();
    }
}