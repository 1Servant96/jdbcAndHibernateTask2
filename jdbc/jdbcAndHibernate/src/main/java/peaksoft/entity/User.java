package peaksoft.entity;

//import lombok.Data;
//import lombok.NoArgsConstructor;

import javax.persistence.*;

//@NoArgsConstructor
//@Data
@Entity
@Table(name = "usersNew")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;

    public User() {
    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User:\n" +
                "id = " + id + '\n' +
                "name = " + name + '\n' +
                "lastName = " + lastName + '\n' +
                "age = " + age + '\n';
                };
    }