// Implementation of the BlogpostService interface.
package com.example.demo.domain.blogpost;

import com.example.demo.core.generic.AbstractRepository;
import com.example.demo.core.generic.AbstractServiceImpl;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
// Marks this class as a service component.
@Service
public class BlogpostServiceImpl extends AbstractServiceImpl<Blogpost> implements BlogpostService {

    // Injects the repository via constructor.
    public BlogpostServiceImpl(AbstractRepository<Blogpost> repository) {
        super(repository);
    }

    @Override
    public boolean hasMoreThanThreePosts(int pageNumber) {
        return repository.count() > 3*(pageNumber+1);
    }


    public Blogpost createBlogpost(Blogpost blogpost) {
        log.info("Creating new blog post: {}", blogpost);
        return save(blogpost);
    }

    // Deletes a blog post by ID.
    public void deleteById(UUID id) {
        log.warn("Deleting blog post with ID: {}", id);
        repository.deleteById(id);
    }

    // Updates an existing blog post.
    public Blogpost updateBlogpost(UUID id, Blogpost updatedBlogpost) {
        log.info("Updating blog post with ID: {}", id);
        Blogpost existingBlogpost = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Blog post with ID {} not found!", id);
                    return new RuntimeException("Blogpost mit ID " + id + " nicht gefunden!");
                });

        existingBlogpost.setTitle(updatedBlogpost.getTitle());
        existingBlogpost.setDescription(updatedBlogpost.getDescription());
        existingBlogpost.setCategory(updatedBlogpost.getCategory());

        log.info("Successfully updated blog post with ID: {}", id);
        return repository.save(existingBlogpost);
    }

    // Handles pagination and sorting of blog posts.
    @Transactional
    @Override
    public Page<Blogpost> paginateAndSort(int page, int size, String sort) {
        log.info("Paginating blog posts - Page: {}, Size: {}, Sort: {}", page, size, sort);

        String[] sortParams = sort.split(",");

        String sortField = sortParams[0];
        String sortDirection = sortParams[1];
        Sort.Direction direction;

        // Determines sort direction.
        if (sortDirection.equals("desc")) {
            direction = Sort.Direction.DESC;
        } else {
            direction = Sort.Direction.ASC;
        }

        Pageable pageable  = PageRequest.of(page, size, Sort.by(direction, sortField));

        Page<Blogpost> result = repository.findAll(pageable);
        log.info("Retrieved {} blog posts", result.getTotalElements());


        return repository.findAll(pageable);
    }

}
