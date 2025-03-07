// Service interface for blog post operations.
package com.example.demo.domain.blogpost;

import com.example.demo.core.generic.AbstractService;
import org.springframework.data.domain.Page;

import java.util.UUID;

// Extends the base service interface for generic CRUD operations.
public interface BlogpostService extends AbstractService<Blogpost> {

    // Creates a new blog post.
    Blogpost createBlogpost(Blogpost blogpost);

    // Updates an existing blog post by ID.
    Blogpost updateBlogpost(UUID id, Blogpost updatedBlogpost);

    // Returns a paginated and sorted list of blog posts.
    Page<Blogpost> paginateAndSort(int page, int size, String sort);
   boolean hasMoreThanThreePosts(int pageNumber);
}
