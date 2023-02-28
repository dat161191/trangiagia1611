package com.personalprojectbe.controller.product;

import com.personalprojectbe.dto.clock.ClockCreateDto;
import com.personalprojectbe.dto.clock.ClockHomeDto;
import com.personalprojectbe.entity.Clock;
import com.personalprojectbe.entity.MachineType;
import com.personalprojectbe.entity.Trademark;
import com.personalprojectbe.service.IMachineTypeService;
import com.personalprojectbe.service.ITrademarkService;
import com.personalprojectbe.service.IClockService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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

//    @GetMapping("public/clock/list")
//    public ResponseEntity<Page<ClockHomeDto>> getList(@PageableDefault() Pageable pageable, @RequestParam(defaultValue = "") String search) {
//        Page<ClockHomeDto> clockHomeDtos = clockService.getListClock(pageable, search);
//        if (clockHomeDtos.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(clockHomeDtos, HttpStatus.OK);
//    }

    @GetMapping("public/clock/list")
    public ResponseEntity<?> getList(@PageableDefault() Pageable pageable, @RequestParam(defaultValue = "") String search) {
        Page<ClockHomeDto> clockHomeDtos = clockService.getListClock(pageable, search);
        List<Trademark> trademarks = trademarkService.findAll();
        List<MachineType> machineTypes = machineTypeService.findAll();
        List<Object> objects = new ArrayList<>();
        objects.add(clockHomeDtos);
        objects.add(trademarks);
        objects.add(machineTypes);
        if (clockHomeDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    /**
     * Method findById(@PathVariable("id") Long id) use to find Clock in DB
     *
     * @param id
     * @return
     */
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

    @PostMapping("admin/create-clock")
    public ResponseEntity<Clock> createClock(@Valid @RequestBody ClockCreateDto clockCreateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        Clock clock = new Clock();
        BeanUtils.copyProperties(clockCreateDto, clock);
        clockService.save(clock);
        return ResponseEntity.ok(clock);
    }
}
