package com.mathtabolism.entity.account;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 
 * @author mlaursen
 */
@Entity
@NamedQueries({
    @NamedQuery(
        name = AccountWeight.Q_findLatestWeight,
        query = "SELECT aw from AccountWeight aw WHERE aw.account.id = :account_id "
              + "AND aw.weighInDate = (SELECT max(aw2.weighInDate) FROM AccountWeight aw2 WHERE aw2.account.id = :account_id) "
              + "AND aw.weight is not null"
    ),
    @NamedQuery(
        name = AccountWeight.Q_findTodaysWeight,
        query = "SELECT aw FROM AccountWeight aw WHERE aw.account.id = :account_id "
              + "AND aw.weighInDate = :today"
    ),
    @NamedQuery(
        name = AccountWeight.Q_findCurrentAccountWeightWeek,
        query = "SELECT aw FROM AccountWeight aw WHERE aw.account.id = :account_id "
              + "AND aw.weighInDate BETWEEN :start_date AND :end_date ORDER BY aw.weighInDate"
    ),
    @NamedQuery(
        name = AccountWeight.Q_findAccountWeightByDate,
        query = "SELECT aw FROM AccountWeight aw WHERE aw.account.id = :account_id AND aw.weighInDate = :weigh_in_date"
    ),
    @NamedQuery(
        name = AccountWeight.Q_findAccountWeightsInRange,
        query = "SELECT aw FROM AccountWeight aw WHERE aw.account.id = :account_id AND aw.weighInDate >= :start_date "
              + "AND aw.weighInDate <= :end_date ORDER BY aw.weighInDate"
    )
})
public class AccountWeight extends AccountFKEntity {
  public static final String Q_findLatestWeight             = "AccountWeight.findLatestWeight";
  public static final String Q_findTodaysWeight             = "AccountWeight.findTodaysWeight";
  public static final String Q_findCurrentAccountWeightWeek = "AccountWeight.findCurrentAccountWeightWeek";
  public static final String Q_findAccountWeightByDate      = "AccountWeight.findAccountWeightByDate";
  public static final String Q_findAccountWeightsInRange    = "AccountWeight.findAccountWeightsInRange";
  
  @Temporal(TemporalType.DATE)
  private Date weighInDate;
  private Double weight;
  
  public AccountWeight() {
  }
  
  public AccountWeight(Account account, Date weighInDate) {
    this.account = account;
    this.weighInDate = weighInDate;
  }
  
  public Date getWeighInDate() {
    return weighInDate;
  }
  
  public void setWeighInDate(Date weighInDate) {
    this.weighInDate = weighInDate;
  }
  
  public Double getWeight() {
    return weight;
  }
  
  public void setWeight(Double weight) {
    this.weight = weight;
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id).append("accountId", account.getId())
        .append("weight", weight).append("weighInDate", weighInDate).toString();
  }

}
