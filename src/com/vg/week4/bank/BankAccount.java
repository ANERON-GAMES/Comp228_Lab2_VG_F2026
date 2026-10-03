package com.vg.week4.bank;

/*
 * Author: Vadim Gubin
 * Student ID: 301538942
 * Date: October 3, 2026
 * Description: Bank account. Account number, name, and balance.
 * Deposit and withdraw throw if the amount is not allowed.
 */

public class BankAccount {

	private String accountNo;
	private String name;
	private double balance;

	public BankAccount(String accountNo, String name, double balance) {
		if (accountNo == null) {
			throw new IllegalArgumentException("Account number must be at least 9 digits.");
		}
		accountNo = accountNo.trim();
		if (accountNo.length() < 9) {
			throw new IllegalArgumentException("Account number must be at least 9 digits.");
		}
		for (int i = 0; i < accountNo.length(); i++) {
			char c = accountNo.charAt(i);
			if (c < '0' || c > '9') {
				throw new IllegalArgumentException("Account number must be at least 9 digits.");
			}
		}

		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Name cannot be blank.");
		}
		if (balance < 0) {
			throw new IllegalArgumentException("Balance cannot be negative.");
		}

		this.accountNo = accountNo;
		this.name = name.trim();
		this.balance = Math.round(balance * 100.0) / 100.0;
	}

	public void deposit(double amount) {
		amount = Math.round(amount * 100.0) / 100.0;
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit amount must be more than 0.");
		}
		balance = Math.round((balance + amount) * 100.0) / 100.0;
	}

	public void withdraw(double amount) throws InsufficientFundsException {
		amount = Math.round(amount * 100.0) / 100.0;
		if (amount <= 0) {
			throw new IllegalArgumentException("Withdraw amount must be more than 0.");
		}
		if (amount > balance) {
			throw new InsufficientFundsException(
					"Not enough funds. Balance is $" + String.format("%.2f", balance) + ".");
		}
		balance = Math.round((balance - amount) * 100.0) / 100.0;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}
}
