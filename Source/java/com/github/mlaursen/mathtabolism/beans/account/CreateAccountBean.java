/**
 * 
 */
package com.github.mlaursen.mathtabolism.beans.account;

import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.mlaursen.mathtabolism.beans.BaseBean;
import com.github.mlaursen.mathtabolism.bo.account.AccountBO;
import com.github.mlaursen.mathtabolism.entity.account.Account;

/**
 * 
 * @author laursenm
 */
@Named
@RequestScoped
public class CreateAccountBean extends BaseBean {
	@Inject
	private AccountBO accountBO;
	private Account account;
	
	public Account getAccount() {
		if(account == null) {
			account = new Account();
		}
		return account;
	}
	
	public String createAccount() {
		try {
			account = accountBO.create(account);
		}
		catch(EJBException e) {;
			sendErrorMessageToUser("The username already exists.");
			return null;
		}
		sendInfoMessageToUser("Your account has been created! Please log in");
		return "create";
	}
}