package fr.formation.blog.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
// dis que ca correspond à une table
public class Comment extends Content {

    @ManyToOne()
    private String subject;

    
}
