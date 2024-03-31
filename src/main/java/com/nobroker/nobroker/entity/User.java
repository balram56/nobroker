package com.nobroker.nobroker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String mobile;
    @Column(nullable = false)
    private String password;
    private boolean emailVerified; //default value for boolean is false , so no supply this value

 //  @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
 //  private List<OwnerPlanSubscription> ownerPlanSubscriptions;

}
