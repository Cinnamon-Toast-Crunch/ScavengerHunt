package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Hunt type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Hunts")
@Index(name = "byLocation", fields = {"locationID"})
public final class Hunt implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField LOCATION_ID = field("locationID");
  public static final QueryField NAME = field("name");
  public static final QueryField TOTAL_POINTS = field("totalPoints");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String locationID;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Int") Integer totalPoints;
  private final @ModelField(targetType="Task") @HasMany(associatedWith = "huntID", type = Task.class) List<Task> tasks = null;
  public String getId() {
      return id;
  }
  
  public String getLocationId() {
      return locationID;
  }
  
  public String getName() {
      return name;
  }
  
  public Integer getTotalPoints() {
      return totalPoints;
  }
  
  public List<Task> getTasks() {
      return tasks;
  }
  
  private Hunt(String id, String locationID, String name, Integer totalPoints) {
    this.id = id;
    this.locationID = locationID;
    this.name = name;
    this.totalPoints = totalPoints;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Hunt hunt = (Hunt) obj;
      return ObjectsCompat.equals(getId(), hunt.getId()) &&
              ObjectsCompat.equals(getLocationId(), hunt.getLocationId()) &&
              ObjectsCompat.equals(getName(), hunt.getName()) &&
              ObjectsCompat.equals(getTotalPoints(), hunt.getTotalPoints());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getLocationId())
      .append(getName())
      .append(getTotalPoints())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Hunt {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("locationID=" + String.valueOf(getLocationId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("totalPoints=" + String.valueOf(getTotalPoints()))
      .append("}")
      .toString();
  }
  
  public static LocationIdStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static Hunt justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Hunt(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      locationID,
      name,
      totalPoints);
  }
  public interface LocationIdStep {
    NameStep locationId(String locationId);
  }
  

  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    Hunt build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep totalPoints(Integer totalPoints);
  }
  

  public static class Builder implements LocationIdStep, NameStep, BuildStep {
    private String id;
    private String locationID;
    private String name;
    private Integer totalPoints;
    @Override
     public Hunt build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Hunt(
          id,
          locationID,
          name,
          totalPoints);
    }
    
    @Override
     public NameStep locationId(String locationId) {
        Objects.requireNonNull(locationId);
        this.locationID = locationId;
        return this;
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep totalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String locationId, String name, Integer totalPoints) {
      super.id(id);
      super.locationId(locationId)
        .name(name)
        .totalPoints(totalPoints);
    }
    
    @Override
     public CopyOfBuilder locationId(String locationId) {
      return (CopyOfBuilder) super.locationId(locationId);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder totalPoints(Integer totalPoints) {
      return (CopyOfBuilder) super.totalPoints(totalPoints);
    }
  }
  
}
