package com.Assignment;
import akka.actor.AbstractActor;
import akka.actor.Props;

class BankAccount extends AbstractActor {
    private double balance = 100.0;

    static public Props props() {
        return Props.create(BankAccount.class, BankAccount::new);
    }

    public Receive createReceive() {
        return receiveBuilder()
                .match(Deposit.class, deposit -> {
                    balance += deposit.amount;
                    System.out.println("Deposited Amount: £" + deposit.amount + "\t New Account Balance: £" + balance);
                })
                .match(Withdrawal.class, withdrawal -> {

                    if(balance > withdrawal.amount) {
                        balance -= withdrawal.amount;
                        System.out.println("Withdrawn Amount: £" + withdrawal.amount + "\t New Account Balance: £" + balance);
                    }
                    else{
                        System.out.println("Withdrawn Amount: £" + withdrawal.amount +"\t Insufficient amount for withdrawal"+"\t Account Balance : £"+balance);
                    }

                })
                .build();
    }

}