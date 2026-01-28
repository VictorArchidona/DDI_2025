package com.ddi.services;

import com.ddi.exceptions.AddressNotFoundException;
import com.ddi.exceptions.EmployeeNotFoundException;
import com.ddi.models.Address;
import com.ddi.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Class that implements an Address Service
 *
 * @author Alvaro Juan Ciriaco
 */
@Service
public class AddressService {

    /**
     * Link with repository (JPA)
     */
    @Autowired
    private AddressRepository repository;

    /**
     * Method to obtain all address
     * @return a list of all addresses stored in the database
     */
    public List<Address> getAllAddress() {
        return repository.findAll();
    }

    /**
     * Method to insert a new address
     * @param newAddress Contains the new address to be inserted
     * @return the new address added to the database in json format
     */
    public Address newAddress(@RequestBody Address newAddress) {
        return repository.save(newAddress);
    }

    /**
     * Method to obtain a specific address
     * @param id identifier of the address you want to obtain
     * @return address whose id is {id}
     * @throws AddressNotFoundException if address doesn't exist
     */
    public Address getAddress(@PathVariable Long id) throws AddressNotFoundException {
        return repository.findById(id).
                orElseThrow(() -> new AddressNotFoundException("Address with id = " + id + " not found."));
    }

    /**
     * Method to replace a specific address
     * @param newAddress new data to insert
     * @param id identifier of the address you want to replace
     * @return the address replaced in json format
     */
    public Address replaceAddress(@RequestBody Address newAddress, @PathVariable Long id) {
        return repository.findById(id)
                .map(address -> {
                    address.setLocation(newAddress.getLocation());
                    return repository.save(address);
                })
                .orElseGet(() -> {
                    return repository.save(newAddress);
                });
    }

    /**
     * Method to replace a specific address
     * @param id identifier of the address you want to delete
     */
    public void deleteAddress(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
