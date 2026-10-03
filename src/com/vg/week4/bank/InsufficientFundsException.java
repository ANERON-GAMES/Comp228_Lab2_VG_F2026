package com.vg.week4.bank;

/*
 * Author: Vadim Gubin
 * Student ID: 301538942
 * Date: October 3, 2026
 * Description: Thrown when a withdraw is bigger than the balance.
 */

public class InsufficientFundsException extends Exception {

	public InsufficientFundsException(String message) {
		super(message);
	}
}
