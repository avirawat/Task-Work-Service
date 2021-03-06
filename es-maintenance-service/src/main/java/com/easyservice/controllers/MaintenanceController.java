package com.easyservice.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyservice.model.Maintenance;
import com.easyservice.model.Priority;
import com.easyservice.model.Status;
import com.easyservice.model.Task;
import com.easyservice.service.IMaintenanceService;

/**
 * @author GAYATHRI S
 *
 */

@RestController
@RequestMapping("/maintenance-service")
public class MaintenanceController {

	IMaintenanceService maintenanceService;

	@Autowired
	public void setMaintenanceService(IMaintenanceService maintenanceService) {
		this.maintenanceService = maintenanceService;
	}

	@PostMapping("/maintenance")
	Maintenance addMaintenance(@RequestBody Maintenance maintenance) {
		Maintenance maintenance1 = maintenanceService.addMaintenance(maintenance);
		return maintenance1;
	}

	@PutMapping("/maintenance")
	String updateMaintenance(@RequestBody Maintenance maintenance) {
		maintenanceService.updateMaintenance(maintenance);
		return "Updated..";
	}

	@DeleteMapping("/maintenance/{maintenanceId}")
	String deleteMaintenance(@PathVariable("maintenanceId") int maintenanceId) {
		maintenanceService.deleteMaintenance(maintenanceId);
		return "Deleted Sucessfully!!";
	}

	@GetMapping("/maintenance/{maintenanceId}")
	Maintenance getById(@PathVariable("maintenanceId") int maintenanceId) {
		return maintenanceService.getById(maintenanceId);
	}

//http://localhost:8071/maintenance-service/maintenance
	@GetMapping("/maintenance")
	List<Maintenance> getAll() {
		return maintenanceService.getAll();

	}

//http://localhost:8071/maintenance-service/maintenance/maintenanceName/Cleaning
	@GetMapping("/maintenance/maintenanceName/{maintenanceName}")
	Maintenance getByMaintenanceName(@PathVariable("maintenanceName") String maintenanceName) {
		return maintenanceService.getByMaintenanceName(maintenanceName);

	}

	@GetMapping("/maintenance/maintenanceManager/{maintenanceManager}")
	Maintenance getByMaintenanceManager(@PathVariable("maintenanceManager") String maintenanceManager) {
		return maintenanceService.getByMaintenanceManager(maintenanceManager);

	}

	@GetMapping("/maintenance/maintenanceStartDate/{mStartDate}")
	List<Maintenance> getByMaintenanceStartDate(@PathVariable("mStartDate") String mStartDate) {
		return maintenanceService.getByMaintenanceStartDate(LocalDate.parse(mStartDate));
	}

	@GetMapping("/maintenance/maintenanceEndDate/{mEndDate}")
	List<Maintenance> getByMaintenanceEndDate(@PathVariable("mEndDate") String mEndDate) {
		return maintenanceService.getByMaintenanceEndDate(LocalDate.parse(mEndDate));
	}

	@GetMapping("/maintenance/maintenanceStatus/{mStatus}")
	List<Maintenance> getByMaintenanceStatus(@PathVariable("mStatus") Status mStatus) {
		return maintenanceService.getByMaintenanceStatus(mStatus);
	}

	@GetMapping("/maintenance/maintenancePriority/{mPriority}")
	List<Maintenance> getByMaintenancePriority(@PathVariable("mPriority") Priority mPriority) {
		return maintenanceService.getByMaintenancePriority(mPriority);
	}

	@GetMapping("/maintenance/maintenanceStartDate/{mStartDate}/maintenanceEndDate/{mEndDate}")
	List<Maintenance> getByMaintenanceStartAndEndDate(@PathVariable("mStartDate") String mStartDate,
			@PathVariable("mEndDate") String mEndDate) {
		return maintenanceService.getByMaintenanceStartAndEndDate(LocalDate.parse(mStartDate),
				LocalDate.parse(mEndDate));
	}

	@GetMapping("/maintenance/maintenanceStatus/{mStatus}/maintenancePriority/{mPriority}")
	List<Maintenance> getByMaintenanceStatusAndPriority(@PathVariable("mStatus") Status mStatus,
			@PathVariable("mPriority") Priority mPriority) {
		return maintenanceService.getByMaintenanceStatusAndPriority(mStatus, mPriority);
	}

//**
	@GetMapping("/maintenance/maintenanceName/{maintenanceName}/maintenanceStatus/{mStatus}")
	Maintenance getByMaintenanceNameAndStatus(@PathVariable("maintenanceName") String maintenanceName,
			@PathVariable("mStatus") Status mStatus) {
		return maintenanceService.getByMaintenanceNameAndStatus(maintenanceName, mStatus);
	}

	@GetMapping("/maintenance/maintenanceName/{maintenanceName}/maintenancePriority/{mPriority}")
	Maintenance getByMaintenanceNameAndPriority(@PathVariable("maintenanceName") String maintenanceName,
			@PathVariable("mPriority") Priority mPriority) {
		return maintenanceService.getByMaintenanceNameAndPriority(maintenanceName, mPriority);
	}
	
	//**************** TASK*******************************
	
	
	@PostMapping("/{maintenanceId}")
	ResponseEntity<Task> addTask(@RequestBody Task task,@PathVariable("maintenanceId")int maintenanceId) {
		Task task1=maintenanceService.addTask(task, maintenanceId);
		ResponseEntity<Task> response=new ResponseEntity<Task>(task,HttpStatus.OK);
		return response;
	}

	@PutMapping("/task")
	void updateTask(@RequestBody Task task) {
		maintenanceService.updateTask(task);
	}

	@DeleteMapping("/task/{taskId}")
	void deleteTask(@PathVariable("taskId") int taskId) {
		maintenanceService.deleteTask(taskId);
	}

	@GetMapping("/task/taskId/{taskId}")
	Maintenance getByTaskId(@PathVariable("taskId") int taskId) {
		return maintenanceService.getById(taskId);
	}

	@GetMapping("/task")
	List<Task> getAllTask() {
		return maintenanceService.getAllTask();
	}

	@GetMapping("/task/maintenanceId/{maintenanceId}")
	List<Task> getByMaintenanceId(@PathVariable("maintenanceId") int maintenanceId) {
		return maintenanceService.getByMaintenanceId(maintenanceId);
	}

	@GetMapping("/task/organiser/{organiser}/tStatus/{tStatus}")
	Task getByOrganiserAndStatus(@PathVariable("organiser") String organiser, @PathVariable("tStatus") String tStatus) {
		return maintenanceService.getByOrganiserAndStatus(organiser, tStatus);
	}

	@GetMapping("/task/tStartDate/{tStartDate}/tEndDate/{tEndDate}")
	Task getByTaskStartDateAndEndDate(@PathVariable("tStartDate") String tStartDate,
			@PathVariable("tEndDate") String tEndDate) {
		return maintenanceService.getByTaskStartDateAndEndDate(LocalDate.parse(tStartDate), LocalDate.parse(tEndDate));
	}

}