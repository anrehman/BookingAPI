package com.personal.bookingapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.bookingapi.controller.GBookingController;
import com.personal.bookingapi.controller.GClassController;
import com.personal.bookingapi.model.GBooking;
import com.personal.bookingapi.model.GClass;
import com.personal.bookingapi.reporitories.GBookingRepository;
import com.personal.bookingapi.reporitories.GClassRepository;
import com.personal.bookingapi.service.GBookingService;
import com.personal.bookingapi.service.GClassService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(GBookingController.class)
public class GBookingControllerTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    GBookingRepository gBookingRepository;
    @MockBean
    GClassRepository gClassRepository;
    @MockBean
    private GBookingService gBookingService;
    @MockBean
    private GClassService gClassService;

    @Test
    public void createRecord_success() throws Exception {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        GBooking record = new GBooking();
        record.setMemberName("Aniqa");
        record.setClassDate(dateFormat.parse("2021-09-15 13:01:01"));

        GClass record_inner = new GClass();
        record_inner.setName("Pilates");
        record_inner.setCapacity(new Long(10));
        record_inner.setEndDate(dateFormat.parse("2021-09-18 13:01:01"));
        record_inner.setStartDate(dateFormat.parse("2021-09-10 13:01:01"));

        record.setGclass(gClassRepository.save(record_inner));
        Mockito.when(gBookingRepository.save(record)).thenReturn(record);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(record));

        mockMvc.perform(mockRequest)
                .andExpect(status().isInternalServerError());
    }
}
