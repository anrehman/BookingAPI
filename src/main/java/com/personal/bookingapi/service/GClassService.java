package com.personal.bookingapi.service;

import com.personal.bookingapi.model.GClass;
import com.personal.bookingapi.reporitories.GClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GClassService {

    @Autowired
    private GClassRepository gClassRepository;

    public List<GClass> listAllClasses() {
        return gClassRepository.findAll();
    }
    public GClass saveClass(GClass gclass) {
        return gClassRepository.save(gclass);
    }
    public Optional<GClass> getClassById(Long id) {
        return gClassRepository.findById(id);
    }

}
