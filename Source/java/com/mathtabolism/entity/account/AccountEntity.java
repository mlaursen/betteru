/**
 * 
 */
package com.mathtabolism.entity.account;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.constants.AccountRole;
import com.mathtabolism.constants.Gender;
import com.mathtabolism.constants.Indicator;
import com.mathtabolism.emcontract.Account;
import com.mathtabolism.entity.BaseGeneratedEntity;

/**
 * @author mlaursen
 *
 */
@Entity
@NamedQueries(@NamedQuery(name = AccountEntity.Q_findByUsername, query = "SELECT a FROM AccountEntity a WHERE a.username = :username"))
public class AccountEntity extends BaseGeneratedEntity implements Account {
  
  public static final String Q_findByUsername = "AccountEntity.findByUsername";
  
  @Column(unique = true)
  private String username;
  private String password;
  
  @Enumerated(EnumType.STRING)
  private AccountRole role;
  
  @Enumerated(EnumType.STRING)
  private Gender gender;
  
  @Temporal(TemporalType.DATE)
  private Date birthday;
  
  @Temporal(TemporalType.DATE)
  private Date lastLogin;
  
  @Temporal(TemporalType.DATE)
  private Date activeSince;
  
  @Enumerated(EnumType.ORDINAL)
  private Indicator useBirthday;
  
  private String email;
  
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "accountEntity")
  private List<AccountWeightEntity> accountWeights;
  
  @Transient
  private String unhashedPassword;
  
  public AccountEntity() {
  }
  
  @Override
  public String getUsername() {
    return username;
  }
  
  @Override
  public void setUsername(String username) {
    this.username = username;
  }
  
  @Override
  public String getPassword() {
    return password;
  }
  
  @Override
  public void setPassword(String password) {
    this.password = password;
  }
  
  /**
   * 
   * @return the {@link AccountRole}
   */
  public AccountRole getRole() {
    return role;
  }
  
  /**
   * 
   * @param role the new {@link AccountRole}
   */
  public void setRole(AccountRole role) {
    this.role = role;
  }
  
  @Override
  public Gender getGender() {
    return gender;
  }
  
  @Override
  public void setGender(Gender gender) {
    this.gender = gender;
  }
  
  @Override
  public Date getBirthday() {
    return birthday;
  }
  
  @Override
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
  
  /**
   * 
   * @return the last login date
   */
  public Date getLastLogin() {
    return lastLogin;
  }
  
  /**
   * 
   * @param lastLogin the new last login date
   */
  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }
  
  /**
   * 
   * @param activeSince
   */
  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }
  
  /**
   * 
   * @return the date the account was created
   */
  public Date getActiveSince() {
    return activeSince;
  }
  
  /**
   * 
   * @return
   */
  public String getUnhashedPassword() {
    return unhashedPassword;
  }
  
  /**
   * 
   * @param unhashedPassword
   */
  public void setUnhashedPassword(String unhashedPassword) {
    this.unhashedPassword = unhashedPassword;
  }
  
  /**
   * 
   * @return
   */
  public List<AccountWeightEntity> getAccountWeights() {
    return accountWeights;
  }
  
  /**
   * 
   * @param accountWeightEntities
   */
  public void setAccountWeights(List<AccountWeightEntity> accountWeights) {
    this.accountWeights = accountWeights;
  }
  
  @Override
  public Indicator getUseBirthday() {
    return useBirthday;
  }

  @Override
  public void setUseBirthday(Indicator useBirthday) {
    this.useBirthday = useBirthday;
  }
  
  public boolean isUsingBirthday() {
    return Indicator.isTrue(useBirthday);
  }
  
  public void setUsingBirthday(boolean isUsingBirthday) {
    useBirthday = Indicator.fromBoolean(isUsingBirthday);
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", id).append("username", username)
        .append("password", password).append("unhashedPassword", unhashedPassword).append("role", role)
        .append("birthday", birthday).append("lastLogin", lastLogin).append("activeSince", activeSince)
        .append("email", email).append("useBirthday", useBirthday).toString();
  }
  
}