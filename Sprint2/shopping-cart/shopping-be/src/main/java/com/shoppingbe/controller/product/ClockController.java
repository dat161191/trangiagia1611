package com.shoppingbe.controller.product;
import com.shoppingbe.dto.clock.ClockCreateDto;
import com.shoppingbe.dto.clock.ClockHomeDto;
import com.shoppingbe.entity.Clock;
import com.shoppingbe.entity.MachineType;
import com.shoppingbe.entity.Trademark;
import com.shoppingbe.service.IClockService;
import com.shoppingbe.service.IMachineTypeService;
import com.shoppingbe.service.ITrademarkService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api")
public class ClockController {
    @Autowired
    private ITrademarkService trademarkService;
    @Autowired
    private IClockService clockService;
    @Autowired
    private IMachineTypeService machineTypeService;

    @GetMapping("public/trademark")
    public ResponseEntity<List<Trademark>> getListTrademark() {
        List<Trademark> trademarkList = trademarkService.findAll();
        if (trademarkList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(trademarkList, HttpStatus.OK);
    }

    @GetMapping("public/machine")
    public ResponseEntity<List<MachineType>> getListHostType() {
        List<MachineType> machineTypeList = machineTypeService.findAll();
        if (machineTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(machineTypeList, HttpStatus.OK);
    }

    @GetMapping("public/clock/list")
    public ResponseEntity<?> getList(@PageableDefault() Pageable pageable, @RequestParam(defaultValue = "") String search) {
        Page<ClockHomeDto> clockHomeDtos = clockService.getListClock(pageable, search);
        if (clockHomeDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clockHomeDtos, HttpStatus.OK);
    }
    @GetMapping("public/clock/header")
    public ResponseEntity<?> getListCarousel() {
        List<ClockHomeDto> clockHeaders = clockService.getListCarousel();
        if (clockHeaders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clockHeaders, HttpStatus.OK);
    }

    @GetMapping("public/clock/detail/{id}")
    public ResponseEntity<Clock> findById(@PathVariable("id") Long id) {
        Clock clock = clockService.findById(id);
        if (clock == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clock, HttpStatus.OK);
    }

    @GetMapping("public/clock/detail-by-trademark/{idTrademark}")
    public ResponseEntity<List<ClockHomeDto>> getListByTrademarkId(@PathVariable("idTrademark") Long idTrademark) {
        List<ClockHomeDto> clockHomeDtos = clockService.getListByTrademarkId(idTrademark);
        if (clockHomeDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clockHomeDtos, HttpStatus.OK);
    }

    @PostMapping("/admin/create-clock")
    public ResponseEntity<Clock> createClock(@Valid @RequestBody ClockCreateDto clockCreateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Clock clock = new Clock();
        BeanUtils.copyProperties(clockCreateDto, clock);
        clockService.save(clock);
        return ResponseEntity.ok(clock);
    }
}
