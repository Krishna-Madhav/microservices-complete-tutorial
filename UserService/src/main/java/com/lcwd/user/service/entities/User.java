package com.lcwd.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity  // Makes this class a table in SQL database
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "micro_users")  // This annotation is used to change properties of table

public class User {

    @Id // Primary key in this class (table)
    @Column(name = "ID")
    private String userId;

    @Column(name = "NAME", length = 20)
    private String userName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ABOUT")
    private  String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}