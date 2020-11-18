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

/** This is an auto generated class representing the LocationInstance type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "LocationInstances")
@Index(name = "byQuest", fields = {"questID"})
public final class LocationInstance implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField QUEST_ID = field("questID");
  public static final QueryField NAME = field("name");
  public static final QueryField LAT = field("lat");
  public static final QueryField LON = field("lon");
  public static final QueryField TOTAL_POINTS = field("totalPoints");
  public static final QueryField PRIVATE = field("private");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String questID;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Float") Float lat;
  private final @ModelField(targetType="Float") Float lon;
  private final @ModelField(targetType="Int") Integer totalPoints;
  private final @ModelField(targetType="Boolean") Boolean private;
  private final @ModelField(targetType="TaskJoiner") @HasMany(associatedWith = "locationInstance", type = TaskJoiner.class) List<TaskJoiner> tasks = null;
  public String getId() {
      return id;
  }
  
  public String getQuestId() {
      return questID;
  }
  
  public String getName() {
      return name;
  }
  
  public Float getLat() {
      return lat;
  }
  
  public Float getLon() {
      return lon;
  }
  
  public Integer getTotalPoints() {
      return totalPoints;
  }
  
  public Boolean getPrivate() {
      return private;
  }
  
  public List<TaskJoiner> getTasks() {
      return tasks;
  }
  
  private LocationInstance(String id, String questID, String name, Float lat, Float lon, Integer totalPoints, Boolean private) {
    this.id = id;
    this.questID = questID;
    this.name = name;
    this.lat = lat;
    this.lon = lon;
    this.totalPoints = totalPoints;
    this.private = private;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      LocationInstance locationInstance = (LocationInstance) obj;
      return ObjectsCompat.equals(getId(), locationInstance.getId()) &&
              ObjectsCompat.equals(getQuestId(), locationInstance.getQuestId()) &&
              ObjectsCompat.equals(getName(), locationInstance.getName()) &&
              ObjectsCompat.equals(getLat(), locationInstance.getLat()) &&
              ObjectsCompat.equals(getLon(), locationInstance.getLon()) &&
              ObjectsCompat.equals(getTotalPoints(), locationInstance.getTotalPoints()) &&
              ObjectsCompat.equals(getPrivate(), locationInstance.getPrivate());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getQuestId())
      .append(getName())
      .append(getLat())
      .append(getLon())
      .append(getTotalPoints())
      .append(getPrivate())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("LocationInstance {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("questID=" + String.valueOf(getQuestId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("lat=" + String.valueOf(getLat()) + ", ")
      .append("lon=" + String.valueOf(getLon()) + ", ")
      .append("totalPoints=" + String.valueOf(getTotalPoints()) + ", ")
      .append("private=" + String.valueOf(getPrivate()))
      .append("}")
      .toString();
  }
  
  public static QuestIdStep builder() {
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
  public static LocationInstance justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new LocationInstance(
      id,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      questID,
      name,
      lat,
      lon,
      totalPoints,
      private);
  }
  public interface QuestIdStep {
    NameStep questId(String questId);
  }
  

  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    LocationInstance build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep lat(Float lat);
    BuildStep lon(Float lon);
    BuildStep totalPoints(Integer totalPoints);
    BuildStep private(Boolean private);
  }
  

  public static class Builder implements QuestIdStep, NameStep, BuildStep {
    private String id;
    private String questID;
    private String name;
    private Float lat;
    private Float lon;
    private Integer totalPoints;
    private Boolean private;
    @Override
     public LocationInstance build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new LocationInstance(
          id,
          questID,
          name,
          lat,
          lon,
          totalPoints,
          private);
    }
    
    @Override
     public NameStep questId(String questId) {
        Objects.requireNonNull(questId);
        this.questID = questId;
        return this;
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep lat(Float lat) {
        this.lat = lat;
        return this;
    }
    
    @Override
     public BuildStep lon(Float lon) {
        this.lon = lon;
        return this;
    }
    
    @Override
     public BuildStep totalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
        return this;
    }
    
    @Override
     public BuildStep private(Boolean private) {
        this.private = private;
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
    private CopyOfBuilder(String id, String questId, String name, Float lat, Float lon, Integer totalPoints, Boolean private) {
      super.id(id);
      super.questId(questId)
        .name(name)
        .lat(lat)
        .lon(lon)
        .totalPoints(totalPoints)
        .private(private);
    }
    
    @Override
     public CopyOfBuilder questId(String questId) {
      return (CopyOfBuilder) super.questId(questId);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder lat(Float lat) {
      return (CopyOfBuilder) super.lat(lat);
    }
    
    @Override
     public CopyOfBuilder lon(Float lon) {
      return (CopyOfBuilder) super.lon(lon);
    }
    
    @Override
     public CopyOfBuilder totalPoints(Integer totalPoints) {
      return (CopyOfBuilder) super.totalPoints(totalPoints);
    }
    
    @Override
     public CopyOfBuilder private(Boolean private) {
      return (CopyOfBuilder) super.private(private);
    }
  }
  
}
