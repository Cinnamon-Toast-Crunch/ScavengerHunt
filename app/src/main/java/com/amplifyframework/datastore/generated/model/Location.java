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

/** This is an auto generated class representing the Location type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Locations")
@Index(name = "byUser", fields = {"userID"})
public final class Location implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField USER_ID = field("userID");
  public static final QueryField NAME = field("name");
  public static final QueryField LAT = field("lat");
  public static final QueryField LON = field("lon");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String userID;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Float") Float lat;
  private final @ModelField(targetType="Float") Float lon;
  private final @ModelField(targetType="QuestJoiner") @HasMany(associatedWith = "location", type = QuestJoiner.class) List<QuestJoiner> quests = null;
  private final @ModelField(targetType="Hunt") @HasMany(associatedWith = "locationID", type = Hunt.class) List<Hunt> hunts = null;
  public String getId() {
      return id;
  }
  
  public String getUserId() {
      return userID;
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
  
  public List<QuestJoiner> getQuests() {
      return quests;
  }
  
  public List<Hunt> getHunts() {
      return hunts;
  }
  
  private Location(String id, String userID, String name, Float lat, Float lon) {
    this.id = id;
    this.userID = userID;
    this.name = name;
    this.lat = lat;
    this.lon = lon;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Location location = (Location) obj;
      return ObjectsCompat.equals(getId(), location.getId()) &&
              ObjectsCompat.equals(getUserId(), location.getUserId()) &&
              ObjectsCompat.equals(getName(), location.getName()) &&
              ObjectsCompat.equals(getLat(), location.getLat()) &&
              ObjectsCompat.equals(getLon(), location.getLon());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUserId())
      .append(getName())
      .append(getLat())
      .append(getLon())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Location {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("userID=" + String.valueOf(getUserId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("lat=" + String.valueOf(getLat()) + ", ")
      .append("lon=" + String.valueOf(getLon()))
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
  public static Location justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Location(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      userID,
      name,
      lat,
      lon);
  }
  public interface UserIdStep {
    NameStep userId(String userId);
  }
  

  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    Location build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep lat(Float lat);
    BuildStep lon(Float lon);
  }
  

  public static class Builder implements UserIdStep, NameStep, BuildStep {
    private String id;
    private String userID;
    private String name;
    private Float lat;
    private Float lon;
    @Override
     public Location build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Location(
          id,
          userID,
          name,
          lat,
          lon);
    }
    
    @Override
     public NameStep userId(String userId) {
        Objects.requireNonNull(userId);
        this.userID = userId;
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
    private CopyOfBuilder(String id, String userId, String name, Float lat, Float lon) {
      super.id(id);
      super.userId(userId)
        .name(name)
        .lat(lat)
        .lon(lon);
    }
    
    @Override
     public CopyOfBuilder userId(String userId) {
      return (CopyOfBuilder) super.userId(userId);
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
  }
  
}
