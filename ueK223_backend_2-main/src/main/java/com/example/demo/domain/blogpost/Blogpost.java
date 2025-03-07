package com.example.demo.domain.blogpost;

import com.example.demo.core.generic.AbstractEntity;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.UUID;

@Entity
@Table(name="blogpost")
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Blogpost extends AbstractEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "category", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_author", referencedColumnName = "id")
    private User author;

    public Blogpost(UUID id, String title, String description, Category category, User author) {
        super(id);
        this.title = title;
        this.description = description;
        this.category = category;
        this.author = author;
    }

}
