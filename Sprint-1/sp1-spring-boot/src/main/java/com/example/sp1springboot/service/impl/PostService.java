package com.example.sp1springboot.service.impl;
import com.example.sp1springboot.dto.PostDto;
import com.example.sp1springboot.repository.IPostRepository;
import com.example.sp1springboot.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService {
    @Autowired
    private IPostRepository postRepository;

    @Override
    public List<PostDto> getAll() {
        return postRepository.getAll();
    }

    @Override
    public List<PostDto> searchYear(String year) {
        return postRepository.searchYear(year);
    }

    @Override
    public List<PostDto> searchYearAndMonth(String year, String month) {
        return postRepository.searchYearAndMonth(year, month);
    }
}
