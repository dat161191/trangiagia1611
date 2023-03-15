package com.shoppingbe.controller.product;

import com.shoppingbe.dto.clock.ImgDto;
import com.shoppingbe.service.clock.IImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("api/public/img")
public class ImgController {
    @Autowired
    private IImgService imgService;

    @GetMapping("{clockId}")
    public ResponseEntity<List<ImgDto>> findByIdClock(@PathVariable("clockId") Long clockId) {
        List<ImgDto> imgDtoList = imgService.findByIdClock(clockId);
        if (imgDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(imgDtoList, HttpStatus.OK);
    }
}
