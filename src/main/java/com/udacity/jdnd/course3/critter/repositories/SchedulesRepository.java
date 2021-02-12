package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchedulesRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByPets_Id(Long id);

    List<Schedule> findAllByEmployees_Id(Long id);

    @Query("SELECT s FROM Schedule s JOIN s.pets AS p JOIN p.customer AS c WHERE c.id = :id")
    List<Schedule> findAllByCustomer_Id(Long id);
}
