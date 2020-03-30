package com.cts.designpattern.factory;

import com.cts.designpattern.factory.AccountFactoryImpl.Account;

public class AccountFactory {
	
	
	static Account getAccountInstance(String type) {
		if(type==null)
			return null;
		if(type.equals("CurrentAccount")) 
			return new CurrentAccount();
		if(type.equals("FixedAccount")) 
			return new FixedAccount();
		if(type.equals("SavingAccount")) 
			return new SavingAccount();
		return null;
	}

}
