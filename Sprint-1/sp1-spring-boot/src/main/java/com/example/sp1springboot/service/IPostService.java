package com.example.sp1springboot.service;

import com.example.sp1springboot.dto.PostDto;

import java.util.List;

public interface IPostService {
    List<PostDto> getAll();
    List<PostDto> searchYear(String year);
    List<PostDto> searchYearAndMonth(String year,String month);

}
