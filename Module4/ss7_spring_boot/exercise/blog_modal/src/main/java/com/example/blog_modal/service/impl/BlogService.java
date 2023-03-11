package com.example.blog_modal.service.impl;


import com.example.blog_modal.model.Blog;
import com.example.blog_modal.repository.IBlogRepository;
import com.example.blog_modal.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    private IBlogRepository iBlogRepository;

    @Autowired
    public BlogService(IBlogRepository iBlogRepository) {
        this.iBlogRepository = iBlogRepository;
    }

    @Override
    public Iterable<Blog> findAll() {
        return iBlogRepository.findAll();
    }

    @Override
    public Page<Blog> findByBlogNameContainingOrAuthorContainingOrderByAuthor(String name, Pageable pageable) {
        return iBlogRepository.findByBlogNameContainingOrAuthorContainingOrderByAuthor(name,name,pageable);
    }

    @Override
    public Optional<Blog> findById(Integer id) {
        return iBlogRepository.findById(id);
    }

    @Override
    public void save(Blog blog) {
        iBlogRepository.save(blog);
    }

    @Override
    public void remove(Integer id) {
        iBlogRepository.deleteById(id);
    }
}
