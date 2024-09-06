package fr.formation.blog.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity

public class User {

    @EqualsAndHashCode.Include
    // @Id lui dis que c'est la clé primaire
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    // contrainte d'unicité, le mail peut pas etre sur pls user le meme
    private String email;

    private String password;

    private String role;

    @OneToMany(mappedBy = "author")
    @SQLRestriction("DTYPE='Comment'")
    @Builder.Default
    // mappedBy= ne crée pas la clé étrangère car elle est crée dans comment
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    @SQLRestriction("DTYPE='Content'")
    @Builder.Default
    // mappedBy= ne crée pas la clé étrangère car elle est crée dans comment
    private List<Content> posts = new ArrayList<>();

   

    
}
