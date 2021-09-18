package com.personal.bookingapi.controller;

import com.personal.bookingapi.model.GClass;
import com.personal.bookingapi.service.GClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/classes")
public class GClassController {

    @Autowired
    GClassService gClassService;

    @GetMapping
    public List<GClass> getAllClasses(){
        return gClassService.listAllClasses();
    }

    @PostMapping
    public ResponseEntity<?> saveClass(@RequestBody GClass gClass){
        try {
            gClass.initializeDatesNow();
            return new ResponseEntity<>(gClassService.saveClass(gClass), HttpStatus.OK);
        }catch (Exception exp){
            return new ResponseEntity<>(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
