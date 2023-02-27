package com.example.controller;


import com.example.model.Blog;
import com.example.model.Category;
import com.example.service.IBlogService;
import com.example.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("blogs")
public class BlogRestController {
    private final ICategoryService categoryService;
    private final IBlogService blogService;

    @Autowired
    public BlogRestController(IBlogService blogService, ICategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }


    @GetMapping("")
    public ResponseEntity<Page<Blog>> getList(@PageableDefault(size = 3) Pageable pageable) {
        Page<Blog> blogList = blogService.findByDeleted(pageable);
        if (blogList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }

    @GetMapping("{id}/{page}")
    public ResponseEntity<Page<Blog>> getByID(@PathVariable("id") int id, @PathVariable("page") int page) {
        Pageable pageable = PageRequest.of(page - 1, 2);
        Category category = categoryService.findById(id);
        Page<Blog> blogCategory = blogService.findByCategoryContains(pageable, category);
        if (blogCategory.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogCategory, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Blog> detail(@PathVariable("id") int id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Blog> delete(@PathVariable("id") int id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        blogService.remove(id);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Blog> update(@PathVariable("id") int id, @RequestBody Blog blog) {
        if (blog.isDeleted()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blogService.save(blog);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }
}
