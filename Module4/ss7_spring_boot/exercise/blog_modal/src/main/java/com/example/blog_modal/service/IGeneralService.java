package com.example.blog_modal.service;


import com.example.blog_modal.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Page<Blog> findByBlogNameContainingOrAuthorContainingOrderByAuthor(String name, Pageable pageable);

    Optional<T> findById(Integer id);

    void save(T t);

    void remove(Integer id);
}
