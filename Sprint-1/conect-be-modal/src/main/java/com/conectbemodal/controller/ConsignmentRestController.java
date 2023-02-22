package com.conectbemodal.controller;

import com.conectbemodal.model.Consignment;
import com.conectbemodal.service.IConsignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("consignment")
public class ConsignmentRestController {
    @Autowired
    private IConsignmentService consignmentService;

    @GetMapping("")
    public ResponseEntity<Page<Consignment>> display(@RequestParam(defaultValue = "") String productName, @RequestParam(defaultValue = "") String importDate,
                                                     @RequestParam(defaultValue = "1") int page,
                                                     @RequestParam() int size) {
        Pageable pageable = Pageable.ofSize(size);
        Page<Consignment> consignments = consignmentService.getList(productName, importDate, pageable.withPage(page));
        if (consignments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(consignments, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Consignment> create(@RequestBody Consignment consignment) {
        if (consignment.isDeleted()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        consignmentService.save(consignment);
        return new ResponseEntity<>(consignment, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Consignment> update(@RequestBody Consignment consignment) {
        if (consignment.isDeleted()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        consignmentService.save(consignment);
        return new ResponseEntity<>(consignment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Consignment> deletePackage(@PathVariable("id") Integer id) {
        Consignment consignment = consignmentService.findById(id);
        if (consignment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        consignmentService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Consignment> findById(@PathVariable("id") Integer id) {
        Consignment consignment = consignmentService.findById(id);
        if (consignment == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(consignment, HttpStatus.OK);
    }
}
