package com.amplifyframework.datastore.generated.model;


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

/** This is an auto generated class representing the Hint type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Hints")
@Index(name = "byTasks", fields = {"taskID"})
public final class Hint implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField TASK_ID = field("taskID");
  public static final QueryField CONTENT = field("content");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String taskID;
  private final @ModelField(targetType="String") String content;
  public String getId() {
      return id;
  }
  
  public String getTaskId() {
      return taskID;
  }
  
  public String getContent() {
      return content;
  }
  
  private Hint(String id, String taskID, String content) {
    this.id = id;
    this.taskID = taskID;
    this.content = content;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Hint hint = (Hint) obj;
      return ObjectsCompat.equals(getId(), hint.getId()) &&
              ObjectsCompat.equals(getTaskId(), hint.getTaskId()) &&
              ObjectsCompat.equals(getContent(), hint.getContent());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTaskId())
      .append(getContent())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Hint {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("taskID=" + String.valueOf(getTaskId()) + ", ")
      .append("content=" + String.valueOf(getContent()))
      .append("}")
      .toString();
  }
  
  public static TaskIdStep builder() {
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
  public static Hint justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Hint(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      taskID,
      content);
  }
  public interface TaskIdStep {
    BuildStep taskId(String taskId);
  }
  

  public interface BuildStep {
    Hint build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep content(String content);
  }
  

  public static class Builder implements TaskIdStep, BuildStep {
    private String id;
    private String taskID;
    private String content;
    @Override
     public Hint build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Hint(
          id,
          taskID,
          content);
    }
    
    @Override
     public BuildStep taskId(String taskId) {
        Objects.requireNonNull(taskId);
        this.taskID = taskId;
        return this;
    }
    
    @Override
     public BuildStep content(String content) {
        this.content = content;
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
    private CopyOfBuilder(String id, String taskId, String content) {
      super.id(id);
      super.taskId(taskId)
        .content(content);
    }
    
    @Override
     public CopyOfBuilder taskId(String taskId) {
      return (CopyOfBuilder) super.taskId(taskId);
    }
    
    @Override
     public CopyOfBuilder content(String content) {
      return (CopyOfBuilder) super.content(content);
    }
  }
  
}
