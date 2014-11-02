/**
 * 
 */
package com.mathtabolism.entity.food;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.mathtabolism.entity.BaseGeneratedEntity;
import com.mathtabolism.util.unit.Measurement;

/**
 * 
 * @author mlaursen
 */
@Entity
public class MealPart extends BaseGeneratedEntity {
	
	@ManyToOne
	@JoinColumn(name="meal_id")
	private Meal meal;
	
	@OneToMany
	@JoinColumn(name="ingredient_id")
	private List<Ingredient> ingredient;
	
	@AttributeOverrides({
		@AttributeOverride(name="unitMeasurement", column=@Column(name="ingredient_serving")),
		@AttributeOverride(name="value", column=@Column(name="ingredient_size"))
	})
	private Measurement serving;
	
	public MealPart() {
	}

	/**
	 * 
	 * @return 
	 */
	public Meal getMeal() {
		return meal;
	}

	/**
	 * 
	 * @param meal 
	 */
	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	/**
	 * 
	 * @return 
	 */
	public List<Ingredient> getIngredient() {
		return ingredient;
	}

	/**
	 * 
	 * @param ingredient 
	 */
	public void setIngredient(List<Ingredient> ingredient) {
		this.ingredient = ingredient;
	}

	/**
	 * 
	 * @return 
	 */
	public Measurement getServing() {
		return serving;
	}

	/**
	 * 
	 * @param serving 
	 */
	public void setServing(Measurement serving) {
		this.serving = serving;
	}
	
	
}