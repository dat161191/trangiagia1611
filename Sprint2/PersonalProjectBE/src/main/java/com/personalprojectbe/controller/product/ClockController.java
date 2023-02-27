package com.personalprojectbe.controller.product;

import com.personalprojectbe.dto.clock.ClockHomeDto;
import com.personalprojectbe.entity.Clock;
import com.personalprojectbe.entity.MachineType;
import com.personalprojectbe.entity.Trademark;
import com.personalprojectbe.service.IMachineTypeService;
import com.personalprojectbe.service.ITrademarkService;
import com.personalprojectbe.service.IClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/pulic/clock")
public class ClockController {
    @Autowired
    private ITrademarkService trademarkService;
    @Autowired
    private IClockService clockService;
    @Autowired
    private IMachineTypeService hostTypeService;

    @GetMapping("trademark")
    public ResponseEntity<List<Trademark>> getListTrademark() {
        List<Trademark> trademarkList = trademarkService.findAll();
        if (trademarkList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(trademarkList, HttpStatus.OK);
    }

    @GetMapping("machine")
    public ResponseEntity<List<MachineType>> getListHostType() {
        List<MachineType> machineTypeList = hostTypeService.findAll();
        if (machineTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(machineTypeList, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<ClockHomeDto>> getList(@PageableDefault() Pageable pageable, @RequestParam(defaultValue = "") String search) {
        Page<ClockHomeDto> clockHomeDtos = clockService.getListClock(pageable, search);
        if (clockHomeDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clockHomeDtos, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Clock> findById(@PathVariable("id") Long id) {
        Clock clock = clockService.findById(id);
        if (clock == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clock, HttpStatus.OK);
    }

    @GetMapping("detail/{idTrademark}")
    public ResponseEntity<List<ClockHomeDto>> getListByTrademarkId(@PathVariable("idTrademark") Long idTrademark) {
        List<ClockHomeDto> clockHomeDtos = clockService.getListByTrademarkId(idTrademark);
        if (clockHomeDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clockHomeDtos, HttpStatus.OK);
    }
}
