package com.personal.bookingapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.personal.bookingapi.util.AuditModel;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gbooking")
public class GBooking extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden=true)
    Long id;

    @Column
    @NotNull
    private String memberName; //is just to be simple but it should be a separate class, and should be linked with id

    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date classDate;

    @ManyToOne
    @JoinColumn(name = "gclass_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    GClass gclass;

    public GBooking() {
    }

    public GBooking(Long id, String memberName, Date classDate, GClass gclass) {
        this.id = id;
        this.memberName = memberName;
        this.classDate = classDate;
        this.gclass = gclass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

    public GClass getGclass() {
        return gclass;
    }

    public void setGclass(GClass gclass) {
        this.gclass = gclass;
    }
}
