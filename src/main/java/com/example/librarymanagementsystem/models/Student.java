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

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"student"})
    private List<Book> bookList;

    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties("student")
    private List<Transaction> transactionList;

    @OneToOne
    @JsonIgnoreProperties({"student"})
    @JoinColumn
    private LibraryUser securedUser;
}
