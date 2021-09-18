package com.personal.bookingapi.controller;

import com.personal.bookingapi.model.GBooking;
import com.personal.bookingapi.model.GClass;
import com.personal.bookingapi.service.GBookingService;
import com.personal.bookingapi.service.GClassService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ExampleProperty;
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
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class GBookingController {
    @Autowired
    GBookingService gBookingService;
    @Autowired
    GClassService gClassService;

    @GetMapping
    public List<GBooking> getAllBookings(){
        return gBookingService.listAllBookings();
    }

    @PostMapping
    public ResponseEntity<?> saveBooking(@RequestBody GBooking gBooking){
       try {
           gBooking.initializeDatesNow();

           Optional<GClass> tempClass = gClassService.getClassById(gBooking.getGclass().getId());
           if(tempClass != null && tempClass.isPresent()) {
               // check date falls inside the start and end of the class
               if(gBooking.getClassDate().compareTo((tempClass.get()).getEndDate()) <= 0 && gBooking.getClassDate().compareTo((tempClass.get()).getStartDate()) >= 0) {
                   gBooking.setGclass(tempClass.get());
                   return new ResponseEntity<>(gBookingService.saveBooking(gBooking), HttpStatus.OK);
               }else {
                   return new ResponseEntity<>("Date is outside class dates", HttpStatus.INTERNAL_SERVER_ERROR);
               }
           }else {
               return new ResponseEntity<>("Class doesn't exist", HttpStatus.INTERNAL_SERVER_ERROR);
           }
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
