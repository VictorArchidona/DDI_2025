package com.ddi.controllers;

import com.ddi.exceptions.AddressNotFoundException;
import com.ddi.exceptions.EmployeeNotFoundException;
import com.ddi.models.Address;
import com.ddi.requestObjects.RequestAddress;
import com.ddi.services.AddressService;
import com.ddi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class that implements an Address Controller
 *
 * @author Alvaro Juan Ciriaco
 */
@RestController
class AddressController {

    /**
     * Link with address service
     */
    @Autowired
    private AddressService addressService;

    /**
     * Link with employee service
     */
    @Autowired
    EmployeeService employeeService;

    /**
     * Get method to obtain all address
     * @return a list of all addresses stored in the database
     */
    @GetMapping("/address")
    public List<Address> getAllAddress() {
        return addressService.getAllAddress();
    }

    /**
     * Post method to insert a new address
     * @param json Contains the new address to be inserted together with
     *             the id of the employee to which it belongs
     * @return the new address added to the database in json format
     */
    @PostMapping("/address")
    public Address newAddress(@RequestBody RequestAddress json) throws EmployeeNotFoundException {
        json.getAddress().setEmployee(employeeService.getEmployee(json.getEmployee_id()));
        return addressService.newAddress(json.getAddress());
    }

    /**
     * Get method to obtain a specific address
     * @param id identifier of the address you want to obtain
     * @return address whose id is {id}
     * @throws AddressNotFoundException if address doesn't exist
     */
    @GetMapping("/address/{id}")
    public Address getAddress(@PathVariable Long id) throws AddressNotFoundException {
        return addressService.getAddress(id);
    }

    /**
     * Put method to replace a specific address
     * @param newAddress new data to insert
     * @param id identifier of the address you want to replace
     * @return the address replaced in json format
     */
    @PutMapping("/address/{id}")
    public Address replaceAddress(@RequestBody Address newAddress, @PathVariable Long id) {
        return addressService.replaceAddress(newAddress, id);
    }

    /**
     * Delete method to replace a specific address
     * @param id identifier of the address you want to delete
     */
    @DeleteMapping("/address/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}