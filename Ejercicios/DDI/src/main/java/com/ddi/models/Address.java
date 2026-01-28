package com.ddi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Class that contains an Address
 *
 * @author Alvaro Juan Ciriaco
 */
@Entity
public class Address {

    /**
     * Id of the address
     */
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    /**
     * Location of the address
     */
    @Column(nullable = false)
    private String location;

    /**
     * Employee who lives at the addres.
     * If we do not add ignore, an infinite loop is generated because the JSON
     * never stops forming.
     */
    @JsonIgnoreProperties("address")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    /**
     * Create an empty address
     */
    public Address() {}

    /**
     * Create an address with location
     * @param location The location of the address
     */
    public Address(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Employee getEmployee() { return employee; }

    public void setEmployee(Employee employee) { this.employee = employee; }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Address))
            return false;
        Address address = (Address) o;
        return Objects.equals(this.id, address.id) && Objects.equals(address.location, address.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.location);
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + this.id + ", location='" + this.location +
                ", employee='" + this.employee + '\'' + '}';
    }

}
