/**
 * 
 */
package com.mathtabolism.bo.account;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.joda.time.DateTime;

import com.mathtabolism.constants.AccountRole;
import com.mathtabolism.eao.account.AccountEAO;
import com.mathtabolism.eao.account.AccountSettingEAO;
import com.mathtabolism.eao.account.AccountWeightEAO;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.AccountSetting;
import com.mathtabolism.entity.account.AccountWeight;
import com.mathtabolism.entity.account.DailyIntake;
import com.mathtabolism.util.PasswordEncryption;
import com.mathtabolism.util.date.DateUtils;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class AccountBO {
  private static Logger logger = Logger.getLogger(AccountBO.class);
  
  @Inject
  private AccountEAO accountEAO;
  @Inject
  private AccountSettingEAO accountSettingEAO;
  @Inject
  private AccountWeightEAO accountWeightEAO;
  @Inject
  private DailyIntakeBO dailyIntakeBO;
  
  /**
   * 
   * @param username
   *          the username to search for
   * @return an {@link Account}
   */
  public Account findAccountByUsername(String username) {
    return accountEAO.findAccountByUsername(username);
  }
  
  public AccountSetting findAccountSettingsForAccount(Account account) {
    return accountSettingEAO.findCurrentAccountSetting(account);
  }
  
  public AccountSetting findLatestSettingsForDate(Account account, Date date) {
    return accountSettingEAO.findLatestSettingsForDate(account.getId(), date);
  }
  
  public List<DailyIntake> findCurrentDailyIntakeWeekForAccount(Account account, AccountSetting currentSettings) {
    return dailyIntakeBO.findCurrentWeek(account, currentSettings);
  }
  
  public List<AccountWeight> findCurrentAccountWeightWeek(Account account, AccountSetting currentSettings) {
    DateTime startDate = DateUtils.findStartDate(currentSettings.getRecalculationDay().toInt());
    return accountWeightEAO.findCurrentAccountWeightWeek(account.getId(), startDate);
  }
  
  public AccountWeight findTodaysWeight(Account account) {
    return accountWeightEAO.findTodaysWeight(account);
  }
  
  public AccountWeight findLatestWeight(Account account) {
    return accountWeightEAO.findLatestWeight(account);
  }
  
  /**
   * 
   * @param account
   *          the account to update
   * @return an account with an updated last login date
   */
  public Account updateLastLogin(Account account) {
    return accountEAO.updateLastLogin(account);
  }
  
  /**
   * 
   * @param account
   *          the account to create
   * @return an Account with a generated primary key and default {@link AccountSetting}
   */
  public Account create(Account account) {
    Date creationDate = Calendar.getInstance().getTime();
    account.setPassword(PasswordEncryption.encrypt(account.getUnhashedPassword()));
    account.setUnhashedPassword("");
    account.setRole(AccountRole.USER);
    account.setActiveSince(creationDate);
    accountEAO.create(account);
    
    AccountSetting accountSetting = new AccountSetting(account, creationDate);
    accountSetting.setDefaults();
    accountSettingEAO.create(accountSetting);
    logger.debug("Account created: " + account);
    return account;
  }
  
  /**
   * 
   * @param account
   * @return
   */
  public Account updateSettings(Account account, AccountSetting currentSettings) {
    accountEAO.update(account);
    currentSettings.setDateChanged(Calendar.getInstance().getTime());
    AccountSetting currentSettingsDB = accountSettingEAO.findCurrentAccountSetting(account);
    if(DateUtils.isSameDate(currentSettings.getDateChanged(), currentSettingsDB.getDateChanged())) {
      accountSettingEAO.update(currentSettings);
    }
    else {
      accountSettingEAO.create(currentSettings);
    }
    return account;
  }
  
  public AccountWeight createOrUpdateWeight(AccountWeight weight) {
    Date d = Calendar.getInstance().getTime();
    if(DateUtils.isSameDate(d, weight.getWeighInDate())) {
      return accountWeightEAO.update(weight);
    } else {
      AccountWeight currentWeight = new AccountWeight();
      currentWeight.setWeighInDate(d);
      currentWeight.setWeight(weight.getWeight());
      accountWeightEAO.create(currentWeight);
      return currentWeight;
    }
  }
}
