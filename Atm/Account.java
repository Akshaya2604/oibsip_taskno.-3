package Atm;

import java.util.ArrayList;
import java.util.List;

public class Account {
	private double balance;
	private List<String> transactions;
	
	public Account() {
		this.balance=0;
		this.transactions=new ArrayList<>();
	}
	
	public void deposit(double amount) {
		balance+=amount;
		transactions.add("Deposit:"+amount);
	}
	
	public void withdraw(double amount) {
		if(balance>=amount) {
			balance-=amount;
			transactions.add("Withdrawal: "+amount);
		}else {
			System.out.println("Insufficient funds.");
		}
	}
	public void transfer(double amount, Account recipientAccount) {
		if(balance>=amount) {
			balance-=amount;
			recipientAccount.deposit(amount);
			transactions.add("Transfer: "+amount);
		}else {
			System.out.println("Insufficient funds.");
		}
	}
	public double getBalance() {
		return balance;
	}
	public List<String> getTransactions() {
        return transactions;
    }
}
