package com.mathtabolism.entity.account;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.util.unit.UnitSystem;

/**
 * 
 * @author mlaursen
 */
@Entity
@NamedQueries({
  @NamedQuery(
      name = AccountSetting.Q_findCurrentAccountSetting,
      query = "SELECT as1 FROM AccountSetting as1 WHERE as1.account.id = :account_id "
            + "AND as1.dateChanged = (SELECT max(as2.dateChanged) FROM AccountSetting as2 WHERE as2.account.id = :account_id)"
  ),
  @NamedQuery(
      name = AccountSetting.Q_findLatestSettingsForDate,
      query = "SELECT as1 FROM AccountSetting as1 WHERE as1.account.id = :account_id "
            + "AND as1.dateChanged = (SELECT max(as2.dateChanged) FROM AccountSetting as2 WHERE as2.account.id = :account_id "
            + "AND as2.dateChanged <= :date)"
  ),
  @NamedQuery(
      name = AccountSetting.Q_findLatestAccountSettingByAccountId,
      query = "SELECT max(as1.dateChanged) FROM AccountSetting as1 WHERE as1.account.id = :account_id"
  )
})
public class AccountSetting extends AccountFKEntity {
  public static final String Q_findCurrentAccountSetting = "AccountSetting.findCurrentAccountSetting";
  public static final String Q_findLatestSettingsForDate = "AccountSetting.findLatestSettingsForDate";
  public static final String Q_findLatestAccountSettingByAccountId = "AccountSetting.findLatestAccountSettingByAccountId";
  
  @Temporal(TemporalType.DATE)
  private Date dateChanged;
  private Integer age;
  private Integer height;
  
  private boolean isUsingAge;
  
  @Enumerated(EnumType.STRING)
  private Weekday recalculationDay;
  
  @Enumerated(EnumType.STRING)
  private ActivityMultiplier activityMultiplier;
  
  @Enumerated(EnumType.STRING)
  private TDEEFormula tdeeFormula;
  
  @Enumerated(EnumType.STRING)
  private UnitSystem unitSystem;
  
  public AccountSetting() {
  }
  
  public AccountSetting(Account account, Date dateChanged) {
    this.account = account;
    this.dateChanged = dateChanged;
  }
  
  public Date getDateChanged() {
    return dateChanged;
  }
  
  public void setDateChanged(Date dateChanged) {
    this.dateChanged = dateChanged;
  }
  
  public Weekday getRecalculationDay() {
    return recalculationDay;
  }
  
  public void setRecalculationDay(Weekday recalculationDay) {
    this.recalculationDay = recalculationDay;
  }
  
  public ActivityMultiplier getActivityMultiplier() {
    return activityMultiplier;
  }
  
  public void setActivityMultiplier(ActivityMultiplier activityMultiplier) {
    this.activityMultiplier = activityMultiplier;
  }
  
  public TDEEFormula getTdeeFormula() {
    return tdeeFormula;
  }
  
  public void setTdeeFormula(TDEEFormula tdeeFormula) {
    this.tdeeFormula = tdeeFormula;
  }
  
  public Integer getAge() {
    return age;
  }
  
  public void setAge(Integer age) {
    this.age = age;
  }
  
  public void setUsingAge(boolean isUsingAge) {
    this.isUsingAge = isUsingAge;
  }
  
  public boolean isUsingAge() {
    return isUsingAge;
  }
  
  public Integer getHeight() {
    return height;
  }
  
  public void setHeight(Integer height) {
    this.height = height;
  }
  
  public UnitSystem getUnitSystem() {
    return unitSystem;
  }

  public void setUnitSystem(UnitSystem unitSystem) {
    this.unitSystem = unitSystem;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("accountId", account.getId())
        .append("recalculationDay", recalculationDay).append("activityMultiplier", activityMultiplier)
        .append("tdeeFormula", tdeeFormula).append("dateChanged", dateChanged).append("age", age)
        .append("height", height).toString();
  }

}
