// User.java
// D. Singletary
// 9/10/25
// User entity for task manager application

package edu.fscj.cen3024c.taskmanager.entities;

import jakarta.persistence.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Set;

@Entity
@Table(name = "users") // avoid 'user' in postgreSQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private byte[] storedHash;

    @Column(nullable = false)
    private byte[] storedSalt;

    @ManyToMany(mappedBy = "users")
    private Set<Task> tasks;

    // Constructors
    public User() {}

    public User(String username, String password) {
        this.username = username;

    }

    // Getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Set<Task> getTasks() { return tasks; }
    public void setTasks(Set<Task> tasks) { this.tasks = tasks; }

    public byte[] getStoredSalt() { return storedSalt; }
    public void setStoredSalt(byte[] storedSalt) {this.storedSalt = storedSalt;}

    public byte[] getStoredHash() { return storedHash; }
    public void setPasswordHash(byte[] storedHash) { this.storedHash = storedHash; }

}
