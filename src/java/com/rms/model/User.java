package com.rms.model;

/**
 * Model class representing a User in the system.
 */
public class User {

    private int id;
    private String name;
    private String email;
    private int age;
    private String gender;
    private String password;
    private byte[] profilePic;
    private String role; // "user" or "admin"

    // Default constructor
    public User() {}

    // Constructor for signup
    public User(String name, String email, int age, String gender, String password) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.password = password;
        this.role = "user";
    }

    // Full constructor
    public User(int id, String name, String email, int age, String gender, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public byte[] getProfilePic() { return profilePic; }
    public void setProfilePic(byte[] profilePic) { this.profilePic = profilePic; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isAdmin() { return "admin".equalsIgnoreCase(this.role); }
}
