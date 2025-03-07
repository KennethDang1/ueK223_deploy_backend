// Controller for handling blog post-related HTTP requests.
package com.example.demo.domain.blogpost;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import com.example.demo.domain.blogpost.dto.BlogpostMapper;
import com.example.demo.domain.blogpost.dto.BlogpostDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
// Marks this class as a REST controller.
@RestController
// Base URL mapping for this controller.
@RequestMapping("/blogpost")
public class BlogpostController {

    private final BlogpostService blogpostService;
    private final BlogpostMapper blogpostMapper;

    // Injects dependencies for service and mapper.
    @Autowired
    public BlogpostController(BlogpostService blogpostService, BlogpostMapper blogpostMapper) {
        this.blogpostService = blogpostService;
        this.blogpostMapper = blogpostMapper;
    }

    // Fetches a blog post by ID.
    @GetMapping("/{id}")
    public ResponseEntity<BlogpostDTO> getBlogpostById(@PathVariable UUID id) {
        log.info("Fetching blog post with ID: {}", id);
        Blogpost blogpost = blogpostService.findById(id);
        return new ResponseEntity<>(blogpostMapper.toDTO(blogpost), HttpStatus.OK);
    }

    // Fetches all blog posts with pagination and sorting.
    @GetMapping({"", "/"})
    public ResponseEntity<List<BlogpostDTO>> getAllBlogposts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title,asc") String sort) {

        log.info("Fetching all blog posts - Page: {}, Size: {}, Sort: {}", page, size, sort);
        List<Blogpost> blogposts = blogpostService.paginateAndSort(page, size, sort).toList();
        return ResponseEntity.ok().body(blogpostMapper.toDTOs(blogposts));
    }

    // Deletes a blog post if the user has permission.
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('BLOGPOST_DELETE_ALL') || @userPermissionEvaluator.evaluateBlogUpdateRights(authentication.principal.user, #updatedBlogpost.author.id)")
    public ResponseEntity<Void> deleteBlogpost(@PathVariable UUID id) {
        log.warn("Deleting blog post with ID: {}", id);
        blogpostService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Creates a new blog post (requires specific permission).
    @PostMapping
    @PreAuthorize("hasAuthority('BLOGPOST_CREATE')")
    public ResponseEntity<BlogpostDTO> createBlogpost(@Valid @RequestBody BlogpostDTO blogpost) {
        log.info("Creating new blog post: {}", blogpost);
        Blogpost savedBlogpost = blogpostService.createBlogpost(blogpostMapper.fromDTO(blogpost));
        return new ResponseEntity<>(blogpostMapper.toDTO(savedBlogpost), HttpStatus.CREATED);
    }

    // Updates a blog post if the user has permission.
    @PreAuthorize("hasAuthority('BLOGPOST_UPDATE_ALL') || @userPermissionEvaluator.evaluateBlogUpdateRights(authentication.principal.user, #updatedBlogpost.author.id)")
    @PutMapping("/{id}")
    public ResponseEntity<BlogpostDTO> updateBlogpost(@PathVariable UUID id, @RequestBody BlogpostDTO updatedBlogpost) {
        log.info("Updating blog post with ID: {}", id);
        Blogpost updated = blogpostService.updateBlogpost(id, blogpostMapper.fromDTO(updatedBlogpost));
        return new ResponseEntity<>(blogpostMapper.toDTO(updated), HttpStatus.CREATED);
    }
}
