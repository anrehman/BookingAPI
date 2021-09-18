package com.personal.bookingapi.service;

import com.personal.bookingapi.model.GBooking;
import com.personal.bookingapi.model.GClass;
import com.personal.bookingapi.reporitories.GBookingRepository;
import com.personal.bookingapi.reporitories.GClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GBookingService {

    @Autowired
    private GBookingRepository gBookingRepository;

    public List<GBooking> listAllBookings() {
        return gBookingRepository.findAll();
    }
    public GBooking saveBooking(GBooking gBooking) {
        return gBookingRepository.save(gBooking);
    }

}
