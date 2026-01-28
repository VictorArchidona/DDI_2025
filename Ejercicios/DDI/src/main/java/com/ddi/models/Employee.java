package com.ddi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Class that contains an Employee
 *
 * @author Alvaro Juan Ciriaco
 */
@Entity
public class Employee {

    /**
     * Id of the employee
     */
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    /**
     * Name of the employee
     */
    private String name;

    /**
     * Role of the employee
     */
    private String role;

    /**
     * Si no añadimos el ignore, se genera un bucle infinito porque el JSON
     * nunca deja de formarse.
     */
    @JsonIgnoreProperties("employee")
    @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    public Employee() {}

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getRole() {
        return this.role;
    }

    public List<Address> getAddress() { return this.addresses; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAddress(List<Address> addresses) { this.addresses = addresses; }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
                && Objects.equals(this.role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.role, this.addresses);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' +
                ", size_address='" + this.addresses.size() + '\'' + '}';
    }
}