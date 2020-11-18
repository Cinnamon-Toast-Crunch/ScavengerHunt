package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;

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

/** This is an auto generated class representing the TaskJoiner type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "TaskJoiners")
@Index(name = "byLocationInstance", fields = {"locationInstanceID","taskID"})
@Index(name = "byTask", fields = {"taskID","locationInstanceID"})
public final class TaskJoiner implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField LOCATION_INSTANCE = field("locationInstanceID");
  public static final QueryField TASK = field("taskID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="LocationInstance", isRequired = true) @BelongsTo(targetName = "locationInstanceID", type = LocationInstance.class) LocationInstance locationInstance;
  private final @ModelField(targetType="Task", isRequired = true) @BelongsTo(targetName = "taskID", type = Task.class) Task task;
  public String getId() {
      return id;
  }
  
  public LocationInstance getLocationInstance() {
      return locationInstance;
  }
  
  public Task getTask() {
      return task;
  }
  
  private TaskJoiner(String id, LocationInstance locationInstance, Task task) {
    this.id = id;
    this.locationInstance = locationInstance;
    this.task = task;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      TaskJoiner taskJoiner = (TaskJoiner) obj;
      return ObjectsCompat.equals(getId(), taskJoiner.getId()) &&
              ObjectsCompat.equals(getLocationInstance(), taskJoiner.getLocationInstance()) &&
              ObjectsCompat.equals(getTask(), taskJoiner.getTask());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getLocationInstance())
      .append(getTask())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("TaskJoiner {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("locationInstance=" + String.valueOf(getLocationInstance()) + ", ")
      .append("task=" + String.valueOf(getTask()))
      .append("}")
      .toString();
  }
  
  public static LocationInstanceStep builder() {
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
  public static TaskJoiner justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new TaskJoiner(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      locationInstance,
      task);
  }
  public interface LocationInstanceStep {
    TaskStep locationInstance(LocationInstance locationInstance);
  }
  

  public interface TaskStep {
    BuildStep task(Task task);
  }
  

  public interface BuildStep {
    TaskJoiner build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements LocationInstanceStep, TaskStep, BuildStep {
    private String id;
    private LocationInstance locationInstance;
    private Task task;
    @Override
     public TaskJoiner build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new TaskJoiner(
          id,
          locationInstance,
          task);
    }
    
    @Override
     public TaskStep locationInstance(LocationInstance locationInstance) {
        Objects.requireNonNull(locationInstance);
        this.locationInstance = locationInstance;
        return this;
    }
    
    @Override
     public BuildStep task(Task task) {
        Objects.requireNonNull(task);
        this.task = task;
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
    private CopyOfBuilder(String id, LocationInstance locationInstance, Task task) {
      super.id(id);
      super.locationInstance(locationInstance)
        .task(task);
    }
    
    @Override
     public CopyOfBuilder locationInstance(LocationInstance locationInstance) {
      return (CopyOfBuilder) super.locationInstance(locationInstance);
    }
    
    @Override
     public CopyOfBuilder task(Task task) {
      return (CopyOfBuilder) super.task(task);
    }
  }
  
}
