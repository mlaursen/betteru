/**
 * 
 */
package com.github.mlaursen.mathtabolism.nutrition;

/**
 * 
 * @author laursenm
 */
public abstract class MacroNutrient extends BaseNutrient {

	private int toCalorieMultiplier;
	protected MacroNutrient(double amount, int toCalorieMultiplier) {
		super(amount);
		this.toCalorieMultiplier = toCalorieMultiplier;
	}
	/**
	 * 
	 * @return 
	 */
	public int getToCalorieMultiplier() {
		return toCalorieMultiplier;
	}
	/**
	 * 
	 * @param toCalorieMultiplier 
	 */
	public void setToCalorieMultiplier(int toCalorieMultiplier) {
		this.toCalorieMultiplier = toCalorieMultiplier;
	}
	
	
	
}