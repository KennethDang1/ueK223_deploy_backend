package com.example.demo.domain.blogpost.dto;

import com.example.demo.core.generic.AbstractDTO;
import com.example.demo.domain.user.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.example.demo.domain.blogpost.Category;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class BlogpostDTO extends AbstractDTO {

    private String title;

    private String description;

    private Category category;

    private UserDTO author;


    public BlogpostDTO(UUID id, String title, String description, Category category, UserDTO authorId) {
        super(id);
        this.title = title;
        this.description = description;
        this.category = category;
        this.author = author;
    }
}
