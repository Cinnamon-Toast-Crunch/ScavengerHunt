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

/** This is an auto generated class representing the Contact type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Contacts")
@Index(name = "byUser", fields = {"userID"})
public final class Contact implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField USER_ID = field("userID");
  public static final QueryField NAME = field("name");
  public static final QueryField PHONE_NUMBER = field("phoneNumber");
  public static final QueryField EMAIL = field("email");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String userID;
  private final @ModelField(targetType="String") String name;
  private final @ModelField(targetType="String") String phoneNumber;
  private final @ModelField(targetType="String") String email;
  public String getId() {
      return id;
  }
  
  public String getUserId() {
      return userID;
  }
  
  public String getName() {
      return name;
  }
  
  public String getPhoneNumber() {
      return phoneNumber;
  }
  
  public String getEmail() {
      return email;
  }
  
  private Contact(String id, String userID, String name, String phoneNumber, String email) {
    this.id = id;
    this.userID = userID;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Contact contact = (Contact) obj;
      return ObjectsCompat.equals(getId(), contact.getId()) &&
              ObjectsCompat.equals(getUserId(), contact.getUserId()) &&
              ObjectsCompat.equals(getName(), contact.getName()) &&
              ObjectsCompat.equals(getPhoneNumber(), contact.getPhoneNumber()) &&
              ObjectsCompat.equals(getEmail(), contact.getEmail());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUserId())
      .append(getName())
      .append(getPhoneNumber())
      .append(getEmail())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Contact {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("userID=" + String.valueOf(getUserId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("phoneNumber=" + String.valueOf(getPhoneNumber()) + ", ")
      .append("email=" + String.valueOf(getEmail()))
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
  public static Contact justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Contact(
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
      phoneNumber,
      email);
  }
  public interface UserIdStep {
    BuildStep userId(String userId);
  }
  

  public interface BuildStep {
    Contact build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep name(String name);
    BuildStep phoneNumber(String phoneNumber);
    BuildStep email(String email);
  }
  

  public static class Builder implements UserIdStep, BuildStep {
    private String id;
    private String userID;
    private String name;
    private String phoneNumber;
    private String email;
    @Override
     public Contact build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Contact(
          id,
          userID,
          name,
          phoneNumber,
          email);
    }
    
    @Override
     public BuildStep userId(String userId) {
        Objects.requireNonNull(userId);
        this.userID = userId;
        return this;
    }
    
    @Override
     public BuildStep name(String name) {
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    
    @Override
     public BuildStep email(String email) {
        this.email = email;
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
    private CopyOfBuilder(String id, String userId, String name, String phoneNumber, String email) {
      super.id(id);
      super.userId(userId)
        .name(name)
        .phoneNumber(phoneNumber)
        .email(email);
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
     public CopyOfBuilder phoneNumber(String phoneNumber) {
      return (CopyOfBuilder) super.phoneNumber(phoneNumber);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
  }
  
}
