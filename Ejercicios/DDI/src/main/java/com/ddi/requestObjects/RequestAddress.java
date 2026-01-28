package com.ddi.requestObjects;

import com.ddi.models.Address;

public class RequestAddress {
    private Address address;
    private Long employee_id;

    public RequestAddress(Address address, Long employee_id) {
        this.address = address;
        this.employee_id = employee_id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }
}
