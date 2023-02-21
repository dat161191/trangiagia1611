package com.personalprojectbe.controller.product;

import com.personalprojectbe.dto.product.ImgDto;
import com.personalprojectbe.service.IImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/img")
public class ImgController {
    @Autowired
    private IImgService imgService;

    @GetMapping("")
    public ResponseEntity<List<ImgDto>> findImgByIdProduct(@RequestParam Long id) {
        List<ImgDto> imgDtoList = imgService.findByIdProduct(id);
        if (imgDtoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }return new ResponseEntity<>(imgDtoList,HttpStatus.OK);
    }
}
