package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.services.SchedulesService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final SchedulesService schedulesService;

    public ScheduleController(SchedulesService schedulesService) {
        this.schedulesService = schedulesService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = schedulesService.createSchedule(convertScheduleDTOToEntity(scheduleDTO));
        return convertEntityToScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = schedulesService.getAllSchedules();
        return schedules.stream().map(this::convertEntityToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = schedulesService.getScheduleForPet(petId);
        return schedules.stream().map(this::convertEntityToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = schedulesService.getScheduleForEmployee(employeeId);
        return schedules.stream().map(this::convertEntityToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = schedulesService.getScheduleForCustomer(customerId);
        return schedules.stream().map(this::convertEntityToScheduleDTO).collect(Collectors.toList());
    }

    private ScheduleDTO convertEntityToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        if (schedule.getEmployees() != null && schedule.getEmployees().size() > 0) {
            List<Long> employeeIds = new ArrayList<>();
            schedule.getEmployees().forEach(employee -> employeeIds.add(employee.getId()));
            scheduleDTO.setEmployeeIds(employeeIds);
        }

        if (schedule.getPets() != null && schedule.getPets().size() > 0) {
            List<Long> petIds = new ArrayList<>();
            schedule.getPets().forEach(pet -> petIds.add(pet.getId()));
            scheduleDTO.setPetIds(petIds);
        }
        return scheduleDTO;
    }

    public static Schedule convertScheduleDTOToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        if (scheduleDTO.getEmployeeIds() != null && scheduleDTO.getEmployeeIds().size() > 0) {
            List<Employee> employees = new ArrayList<>();
            scheduleDTO.getEmployeeIds().forEach(id -> {
                Employee employee = new Employee();
                employee.setId(id);
                employees.add(employee);
            });
            schedule.setEmployees(employees);
        }

        if (scheduleDTO.getPetIds() != null && scheduleDTO.getPetIds().size() > 0) {
            List<Pet> pets = new ArrayList<>();
            scheduleDTO.getPetIds().forEach(id -> {
                Pet pet = new Pet();
                pet.setId(id);
                pets.add(pet);
            });
            schedule.setPets(pets);
        }
        return schedule;
    }
}
