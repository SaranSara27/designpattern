package com.cts.designpattern.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.designpattern.Application.Main;


/**
 * Implement FactoryPattern for to get the  object instances for sub classes as follows(SavingAccount, FixedAccount, CurrentAccount),
 * Account is an interface. Create a class AccountFactory which gives the Account objects
 *
 */
public class AccountFactoryImpl {

	public static void main(String[] args) {
		
		Logger log = LoggerFactory.getLogger(Main.class);
		
		Account account= AccountFactory.getAccountInstance("SavingAccount");
		
		log.debug("AccountType : {}",account.getAccountType());

	}
	

	interface Account {
		public String getAccountType();
	}

}
