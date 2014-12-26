package com.mathtabolism.model.account;

import java.util.Date;

import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.emcontract.AccountSetting;
import com.mathtabolism.model.BaseModel;
import com.mathtabolism.util.number.NumberUtils;
import com.mathtabolism.util.unit.UnitSystem;

/**
 * 
 * @author mlaursen
 *
 */
public class AccountSettingModel extends BaseModel implements AccountSetting {
  
  private String heightSmall;
  private String heightLarge;
  
  private Date dateChanged;
  private Integer age;
  private Double height;
  private ActivityMultiplier activityMultiplier;
  private Weekday recalculationDay;
  private TDEEFormula tdeeFormula;
  private UnitSystem unitSystem;
  
  public void updateHeight() {
    double smallH = NumberUtils.stringToDouble(heightSmall);
    double largeH = NumberUtils.stringToDouble(heightLarge);
    int mult = UnitSystem.isImperial(unitSystem) ? 12 : 100;
    setHeight(largeH * mult + smallH);
  }

  public String getHeightSmall() {
    return heightSmall;
  }

  public void setHeightSmall(String heightSmall) {
    this.heightSmall = heightSmall;
  }

  public String getHeightLarge() {
    return heightLarge;
  }

  public void setHeightLarge(String heightLarge) {
    this.heightLarge = heightLarge;
  }
  
  @Override
  public void setDateChanged(Date dateChanged) {
    this.dateChanged = dateChanged;
  }

  @Override
  public Date getDateChanged() {
    return dateChanged;
  }

  @Override
  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public Integer getAge() {
    return age;
  }

  @Override
  public void setHeight(Double height) {
    this.height = height;
  }

  @Override
  public Double getHeight() {
    return height;
  }

  @Override
  public void setActivityMultiplier(ActivityMultiplier activityMultiplier) {
    this.activityMultiplier = activityMultiplier;
  }

  @Override
  public ActivityMultiplier getActivityMultiplier() {
    return activityMultiplier;
  }

  @Override
  public void setRecalculationDay(Weekday recalculationDay) {
    this.recalculationDay = recalculationDay;
  }

  @Override
  public Weekday getRecalculationDay() {
    return recalculationDay;
  }

  @Override
  public void setTdeeFormula(TDEEFormula tdeeFormula) {
    this.tdeeFormula = tdeeFormula;
  }

  @Override
  public TDEEFormula getTdeeFormula() {
    return tdeeFormula;
  }

  @Override
  public void setUnitSystem(UnitSystem unitSystem) {
    this.unitSystem = unitSystem;
  }

  @Override
  public UnitSystem getUnitSystem() {
    return unitSystem;
  }
  
}
