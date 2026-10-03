package com.vg.week4.bank;

import java.util.Scanner;

/*
 * Author: Vadim Gubin
 * Student ID: 301538942
 * Date: October 3, 2026
 * Description: Opens 3 bank accounts. Deposit and withdraw from a menu.
 * A bad amount does not stop the program.
 */

public class MainDriver {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BankAccount[] accounts = new BankAccount[3];

		System.out.println("Open 3 accounts.");
		System.out.println();
		for (int i = 0; i < accounts.length; i++) {
			accounts[i] = readAccount(sc, i + 1);
			System.out.println();
		}

		String choice = "";
		while (!choice.equals("0")) {
			System.out.println("Accounts:");
			for (int i = 0; i < accounts.length; i++) {
				System.out.println((i + 1) + "  " + line(accounts[i]));
			}
			System.out.println();
			System.out.println("Enter the choice for operation");
			System.out.println("1 - Deposit");
			System.out.println("2 - Withdraw");
			System.out.println("0 - Exit");
			choice = sc.nextLine().trim();

			if (choice.equals("1") || choice.equals("2")) {
				doTransaction(sc, accounts, choice);
			} else if (!choice.equals("0")) {
				System.out.println("Wrong choice.");
			}
			System.out.println();
		}

		System.out.println("Bye.");
		sc.close();
	}

	private static BankAccount readAccount(Scanner sc, int n) {
		while (true) {
			System.out.println("Account " + n);
			System.out.print("Account number: ");
			String accountNo = sc.nextLine();
			System.out.print("Name: ");
			String name = sc.nextLine();
			System.out.print("Starting balance: ");
			String balLine = sc.nextLine().trim();

			try {
				double balance = Double.parseDouble(balLine);
				BankAccount acc = new BankAccount(accountNo, name, balance);
				System.out.println("Account opened. " + line(acc));
				return acc;
			} catch (NumberFormatException e) {
				System.out.println("Balance must be a number.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Try this account again.");
			System.out.println();
		}
	}

	private static void doTransaction(Scanner sc, BankAccount[] accounts, String choice) {
		System.out.print("Which account (1-3): ");
		String pickLine = sc.nextLine().trim();
		System.out.print("Amount: ");
		String amtLine = sc.nextLine().trim();

		try {
			int pick = Integer.parseInt(pickLine);
			double amount = Double.parseDouble(amtLine);
			if (pick < 1 || pick > accounts.length) {
				throw new IllegalArgumentException("Pick an account from 1 to 3.");
			}
			BankAccount acc = accounts[pick - 1];
			if (choice.equals("1")) {
				acc.deposit(amount);
				System.out.println("Doing deposit.. ");
			} else {
				acc.withdraw(amount);
				System.out.println("Doing withdraw.. ");
			}
			System.out.println("New balance: $" + String.format("%.2f", acc.getBalance()));
		} catch (NumberFormatException e) {
			System.out.println("Enter a number for the account and the amount.");
		} catch (InsufficientFundsException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("The transaction has been processed.");
		}
	}

	private static String line(BankAccount acc) {
		return acc.getAccountNo() + "  " + acc.getName() + "  $"
				+ String.format("%.2f", acc.getBalance());
	}
}
