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

/** This is an auto generated class representing the Task type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Tasks")
@Index(name = "byLocation", fields = {"locationID"})
public final class Task implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField LOCATION_ID = field("locationID");
  public static final QueryField INSTRUCTION = field("instruction");
  public static final QueryField OBJECTIVE = field("objective");
  public static final QueryField COMPLETED = field("completed");
  public static final QueryField POINT_VALUE = field("pointValue");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String locationID;
  private final @ModelField(targetType="String") String instruction;
  private final @ModelField(targetType="String", isRequired = true) String objective;
  private final @ModelField(targetType="Boolean", isRequired = true) Boolean completed;
  private final @ModelField(targetType="Int") Integer pointValue;
  private final @ModelField(targetType="Hint") @HasMany(associatedWith = "taskID", type = Hint.class) List<Hint> hints = null;
  private final @ModelField(targetType="TaskJoiner") @HasMany(associatedWith = "task", type = TaskJoiner.class) List<TaskJoiner> locations = null;
  public String getId() {
      return id;
  }
  
  public String getLocationId() {
      return locationID;
  }
  
  public String getInstruction() {
      return instruction;
  }
  
  public String getObjective() {
      return objective;
  }
  
  public Boolean getCompleted() {
      return completed;
  }
  
  public Integer getPointValue() {
      return pointValue;
  }
  
  public List<Hint> getHints() {
      return hints;
  }
  
  public List<TaskJoiner> getLocations() {
      return locations;
  }
  
  private Task(String id, String locationID, String instruction, String objective, Boolean completed, Integer pointValue) {
    this.id = id;
    this.locationID = locationID;
    this.instruction = instruction;
    this.objective = objective;
    this.completed = completed;
    this.pointValue = pointValue;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Task task = (Task) obj;
      return ObjectsCompat.equals(getId(), task.getId()) &&
              ObjectsCompat.equals(getLocationId(), task.getLocationId()) &&
              ObjectsCompat.equals(getInstruction(), task.getInstruction()) &&
              ObjectsCompat.equals(getObjective(), task.getObjective()) &&
              ObjectsCompat.equals(getCompleted(), task.getCompleted()) &&
              ObjectsCompat.equals(getPointValue(), task.getPointValue());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getLocationId())
      .append(getInstruction())
      .append(getObjective())
      .append(getCompleted())
      .append(getPointValue())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Task {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("locationID=" + String.valueOf(getLocationId()) + ", ")
      .append("instruction=" + String.valueOf(getInstruction()) + ", ")
      .append("objective=" + String.valueOf(getObjective()) + ", ")
      .append("completed=" + String.valueOf(getCompleted()) + ", ")
      .append("pointValue=" + String.valueOf(getPointValue()))
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
  public static Task justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Task(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      locationID,
      instruction,
      objective,
      completed,
      pointValue);
  }
  public interface LocationIdStep {
    ObjectiveStep locationId(String locationId);
  }
  

  public interface ObjectiveStep {
    CompletedStep objective(String objective);
  }
  

  public interface CompletedStep {
    BuildStep completed(Boolean completed);
  }
  

  public interface BuildStep {
    Task build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep instruction(String instruction);
    BuildStep pointValue(Integer pointValue);
  }
  

  public static class Builder implements LocationIdStep, ObjectiveStep, CompletedStep, BuildStep {
    private String id;
    private String locationID;
    private String objective;
    private Boolean completed;
    private String instruction;
    private Integer pointValue;
    @Override
     public Task build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Task(
          id,
          locationID,
          instruction,
          objective,
          completed,
          pointValue);
    }
    
    @Override
     public ObjectiveStep locationId(String locationId) {
        Objects.requireNonNull(locationId);
        this.locationID = locationId;
        return this;
    }
    
    @Override
     public CompletedStep objective(String objective) {
        Objects.requireNonNull(objective);
        this.objective = objective;
        return this;
    }
    
    @Override
     public BuildStep completed(Boolean completed) {
        Objects.requireNonNull(completed);
        this.completed = completed;
        return this;
    }
    
    @Override
     public BuildStep instruction(String instruction) {
        this.instruction = instruction;
        return this;
    }
    
    @Override
     public BuildStep pointValue(Integer pointValue) {
        this.pointValue = pointValue;
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
    private CopyOfBuilder(String id, String locationId, String instruction, String objective, Boolean completed, Integer pointValue) {
      super.id(id);
      super.locationId(locationId)
        .objective(objective)
        .completed(completed)
        .instruction(instruction)
        .pointValue(pointValue);
    }
    
    @Override
     public CopyOfBuilder locationId(String locationId) {
      return (CopyOfBuilder) super.locationId(locationId);
    }
    
    @Override
     public CopyOfBuilder objective(String objective) {
      return (CopyOfBuilder) super.objective(objective);
    }
    
    @Override
     public CopyOfBuilder completed(Boolean completed) {
      return (CopyOfBuilder) super.completed(completed);
    }
    
    @Override
     public CopyOfBuilder instruction(String instruction) {
      return (CopyOfBuilder) super.instruction(instruction);
    }
    
    @Override
     public CopyOfBuilder pointValue(Integer pointValue) {
      return (CopyOfBuilder) super.pointValue(pointValue);
    }
  }
  
}
