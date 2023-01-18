package com.example.sp1springboot.controller;

import com.example.sp1springboot.dto.PostDto;
import com.example.sp1springboot.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("post")
public class PostRestController {
    @Autowired
    private IPostService postService;

    @GetMapping("")
    public ResponseEntity<List<PostDto>> displayList() {
        List<PostDto> postDtoList = postService.getAll();
        if (postDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> search(@RequestParam(defaultValue = "-1") Integer year, @RequestParam(defaultValue = "-1") Integer month) {
        List<PostDto> postDtoList = null;
        if (month == -1) {
            postDtoList = postService.searchYear(String.valueOf(year));
        }
        if (year != -1 && month != -1) {
            postDtoList = postService.searchYearAndMonth(String.valueOf(year), String.valueOf(month));
        }
        if (postDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }
}
