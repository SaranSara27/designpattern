package com.cts.designpattern.factory;

import com.cts.designpattern.factory.AccountFactoryImpl.Account;

public class SavingAccount implements Account{

	@Override
	public String getAccountType() {
		return "SavingAccount";
	}

}
