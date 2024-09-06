package fr.formation.blog.models;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
// @JsonTypeInfo et JsonSubTypes quand tu as une relation d'HERITAGE
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Content.class, name = "POST"),
        @JsonSubTypes.Type(value = Comment.class, name = "COMMENT"),
})
@Entity
@SuperBuilder
public class Content {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private long id;

    private String content;

    private ZonedDateTime creationDate;

    private ContentType type;

    @OneToMany(mappedBy = "subject")
    // mappedBy= ne crée pas la clé étrangère car elle est crée dans comment
    private List<Comment> comments;

    @ManyToOne
    private String author;

}
