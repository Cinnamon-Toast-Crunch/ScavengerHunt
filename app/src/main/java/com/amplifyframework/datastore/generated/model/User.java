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

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users")
public final class User implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField USER_NAME = field("userName");
  public static final QueryField EMAIL = field("email");
  public static final QueryField PICTURE = field("picture");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String userName;
  private final @ModelField(targetType="String", isRequired = true) String email;
  private final @ModelField(targetType="String") String picture;
  private final @ModelField(targetType="Quest") @HasMany(associatedWith = "userID", type = Quest.class) List<Quest> quests = null;
  private final @ModelField(targetType="Location") @HasMany(associatedWith = "userID", type = Location.class) List<Location> locations = null;
  private final @ModelField(targetType="Contact") @HasMany(associatedWith = "userID", type = Contact.class) List<Contact> contacts = null;
  public String getId() {
      return id;
  }
  
  public String getUserName() {
      return userName;
  }
  
  public String getEmail() {
      return email;
  }
  
  public String getPicture() {
      return picture;
  }
  
  public List<Quest> getQuests() {
      return quests;
  }
  
  public List<Location> getLocations() {
      return locations;
  }
  
  public List<Contact> getContacts() {
      return contacts;
  }
  
  private User(String id, String userName, String email, String picture) {
    this.id = id;
    this.userName = userName;
    this.email = email;
    this.picture = picture;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      User user = (User) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getUserName(), user.getUserName()) &&
              ObjectsCompat.equals(getEmail(), user.getEmail()) &&
              ObjectsCompat.equals(getPicture(), user.getPicture());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUserName())
      .append(getEmail())
      .append(getPicture())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("userName=" + String.valueOf(getUserName()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("picture=" + String.valueOf(getPicture()))
      .append("}")
      .toString();
  }
  
  public static UserNameStep builder() {
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
  public static User justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new User(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      userName,
      email,
      picture);
  }
  public interface UserNameStep {
    EmailStep userName(String userName);
  }
  

  public interface EmailStep {
    BuildStep email(String email);
  }
  

  public interface BuildStep {
    User build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep picture(String picture);
  }
  

  public static class Builder implements UserNameStep, EmailStep, BuildStep {
    private String id;
    private String userName;
    private String email;
    private String picture;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          userName,
          email,
          picture);
    }
    
    @Override
     public EmailStep userName(String userName) {
        Objects.requireNonNull(userName);
        this.userName = userName;
        return this;
    }
    
    @Override
     public BuildStep email(String email) {
        Objects.requireNonNull(email);
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep picture(String picture) {
        this.picture = picture;
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
    private CopyOfBuilder(String id, String userName, String email, String picture) {
      super.id(id);
      super.userName(userName)
        .email(email)
        .picture(picture);
    }
    
    @Override
     public CopyOfBuilder userName(String userName) {
      return (CopyOfBuilder) super.userName(userName);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder picture(String picture) {
      return (CopyOfBuilder) super.picture(picture);
    }
  }
  
}
