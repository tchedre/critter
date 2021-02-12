package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repositories.SchedulesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SchedulesService {

    private final SchedulesRepository scheduleRepository;

    public SchedulesService(SchedulesRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(Long id) {
        return scheduleRepository.findAllByPets_Id(id);
    }

    public List<Schedule> getScheduleForEmployee(Long id) {
        return scheduleRepository.findAllByEmployees_Id(id);
    }

    public List<Schedule> getScheduleForCustomer(Long id) {
        return scheduleRepository.findAllByCustomer_Id(id);
    }
}
