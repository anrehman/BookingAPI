package com.personal.bookingapi.reporitories;

import com.personal.bookingapi.model.GBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GBookingRepository extends JpaRepository<GBooking, Long> {
}
