package com.example.librarymanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private Integer age;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @JsonIgnoreProperties({"student"})
    @OneToMany(mappedBy = "student")
    private List<Book> bookList;

    @JsonIgnoreProperties({"student"})
    @OneToMany(mappedBy = "student")
    private List<Transaction> transactionList;


    @JsonIgnoreProperties({"student"})
    @OneToOne
    @JoinColumn
    private LibraryUser securedUser;
}
