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

/** This is an auto generated class representing the QuestJoiner type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "QuestJoiners")
@Index(name = "byQuest", fields = {"questID","locationID"})
@Index(name = "byLocation", fields = {"locationID","questID"})
public final class QuestJoiner implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField QUEST = field("questID");
  public static final QueryField LOCATION = field("locationID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Quest", isRequired = true) @BelongsTo(targetName = "questID", type = Quest.class) Quest quest;
  private final @ModelField(targetType="Location", isRequired = true) @BelongsTo(targetName = "locationID", type = Location.class) Location location;
  public String getId() {
      return id;
  }
  
  public Quest getQuest() {
      return quest;
  }
  
  public Location getLocation() {
      return location;
  }
  
  private QuestJoiner(String id, Quest quest, Location location) {
    this.id = id;
    this.quest = quest;
    this.location = location;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      QuestJoiner questJoiner = (QuestJoiner) obj;
      return ObjectsCompat.equals(getId(), questJoiner.getId()) &&
              ObjectsCompat.equals(getQuest(), questJoiner.getQuest()) &&
              ObjectsCompat.equals(getLocation(), questJoiner.getLocation());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getQuest())
      .append(getLocation())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("QuestJoiner {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("quest=" + String.valueOf(getQuest()) + ", ")
      .append("location=" + String.valueOf(getLocation()))
      .append("}")
      .toString();
  }
  
  public static QuestStep builder() {
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
  public static QuestJoiner justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new QuestJoiner(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      quest,
      location);
  }
  public interface QuestStep {
    LocationStep quest(Quest quest);
  }
  

  public interface LocationStep {
    BuildStep location(Location location);
  }
  

  public interface BuildStep {
    QuestJoiner build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements QuestStep, LocationStep, BuildStep {
    private String id;
    private Quest quest;
    private Location location;
    @Override
     public QuestJoiner build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new QuestJoiner(
          id,
          quest,
          location);
    }
    
    @Override
     public LocationStep quest(Quest quest) {
        Objects.requireNonNull(quest);
        this.quest = quest;
        return this;
    }
    
    @Override
     public BuildStep location(Location location) {
        Objects.requireNonNull(location);
        this.location = location;
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
    private CopyOfBuilder(String id, Quest quest, Location location) {
      super.id(id);
      super.quest(quest)
        .location(location);
    }
    
    @Override
     public CopyOfBuilder quest(Quest quest) {
      return (CopyOfBuilder) super.quest(quest);
    }
    
    @Override
     public CopyOfBuilder location(Location location) {
      return (CopyOfBuilder) super.location(location);
    }
  }
  
}
