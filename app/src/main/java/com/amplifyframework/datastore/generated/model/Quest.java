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

/** This is an auto generated class representing the Quest type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Quests")
@Index(name = "byUser", fields = {"userID"})
public final class Quest implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField USER_ID = field("userID");
  public static final QueryField TITLE = field("title");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String userID;
  private  @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="LocationInstance") @HasMany(associatedWith = "questID", type = LocationInstance.class) List<LocationInstance> locations = null;
  public String getId() {
      return id;
  }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
      return userID;
  }
  
  public String getTitle() {
      return title;
  }
  
  public List<LocationInstance> getLocations() {
      return locations;
  }
  
  private Quest(String id, String userID, String title) {
    this.id = id;
    this.userID = userID;
    this.title = title;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Quest quest = (Quest) obj;
      return ObjectsCompat.equals(getId(), quest.getId()) &&
              ObjectsCompat.equals(getUserId(), quest.getUserId()) &&
              ObjectsCompat.equals(getTitle(), quest.getTitle());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUserId())
      .append(getTitle())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Quest {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("userID=" + String.valueOf(getUserId()) + ", ")
      .append("title=" + String.valueOf(getTitle()))
      .append("}")
      .toString();
  }
  
  public static UserIdStep builder() {
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
  public static Quest justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Quest(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      userID,
      title);
  }
  public interface UserIdStep {
    TitleStep userId(String userId);
  }
  

  public interface TitleStep {
    BuildStep title(String title);
  }
  

  public interface BuildStep {
    Quest build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements UserIdStep, TitleStep, BuildStep {
    private String id;
    private String userID;
    private String title;
    @Override
     public Quest build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Quest(
          id,
          userID,
          title);
    }
    
    @Override
     public TitleStep userId(String userId) {
        Objects.requireNonNull(userId);
        this.userID = userId;
        return this;
    }
    
    @Override
     public BuildStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
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
    private CopyOfBuilder(String id, String userId, String title) {
      super.id(id);
      super.userId(userId)
        .title(title);
    }
    
    @Override
     public CopyOfBuilder userId(String userId) {
      return (CopyOfBuilder) super.userId(userId);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
  }


  
}
