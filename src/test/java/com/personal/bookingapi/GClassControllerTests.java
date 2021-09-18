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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GClassController.class)
public class GClassControllerTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    GClassRepository gClassRepository;

    @MockBean
    private GClassService gClassService;
    @Test
    public void createRecord_success() throws Exception {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        GClass record_inner = new GClass();
        record_inner.setName("Pilates");
        record_inner.setCapacity(new Long(10));
        record_inner.setEndDate(dateFormat.parse("2021-09-18 13:01:01"));
        record_inner.setStartDate(dateFormat.parse("2021-09-10 13:01:01"));
        Mockito.when(gClassRepository.save(record_inner)).thenReturn(record_inner);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/classes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(record_inner));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }
}
