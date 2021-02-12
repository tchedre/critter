package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomersRepository;
import com.udacity.jdnd.course3.critter.repositories.PetsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PetsService {

    private PetsRepository petsRepository;

    private CustomersRepository customersRepository;

    public PetsService(PetsRepository petsRepository, CustomersRepository customersRepository) {
        this.petsRepository = petsRepository;
        this.customersRepository = customersRepository;
    }

    public List<Pet> getAllPets() {
        return petsRepository.findAll();
    }

    public List<Pet> getPetsByCustomerId(long customerId) {
        return petsRepository.getAllByCustomerId(customerId);
    }

    public Pet getPetById(long petId) {
        return petsRepository.getOne(petId);
    }

    public Pet savePet(Pet pet, long ownerId) {
        Customer customer = customersRepository.getOne(ownerId);
        pet.setCustomer(customer);
        pet = petsRepository.save(pet);
        customer.addPet(pet);
        customersRepository.save(customer);
        return pet;
    }
}
