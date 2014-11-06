/**
 * 
 */
package com.mathtabolism.entity.food;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.mathtabolism.entity.BaseGeneratedEntity;

/**
 * 
 * @author mlaursen
 */
@Entity
@NamedQueries({
  @NamedQuery(name = Brand.Q_findAllBrands, query = "SELECT b FROM Brand b ORDER BY name")
})
public class Brand extends BaseGeneratedEntity {
  public static final String Q_findAllBrands = "Brand.findAllBrands";
  
  public Brand() {
  }
  
  public Brand(String name) {
    this.name = name;
  }
  
  private String name;
  
  /**
   * 
   * @return
   */
  public String getName() {
    return name;
  }
  
  /**
   * 
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }
  
  @Override
  public boolean equals(Object object) {
    if(object instanceof Brand) {
      Brand b = (Brand) object;
      return id.equals(b.id);
    }
    return false;
  }
  
  /**
   * @return
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }
}
