package com.personal.bookingapi.model;

import com.personal.bookingapi.util.AuditModel;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gclass")
public class GClass extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @NotNull
    private Long capacity;

    public GClass() {
    }

    public GClass(Long id, String name, Date startDate, Date endDate, Long capacity) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }
}
