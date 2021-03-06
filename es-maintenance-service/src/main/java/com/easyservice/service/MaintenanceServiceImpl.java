package com.easyservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.easyservice.model.Maintenance;
import com.easyservice.model.Priority;
import com.easyservice.model.Status;
import com.easyservice.model.Task;
import com.easyservice.repository.IMaintenanceRepository;

/**
 * @author GAYATHRI S
 *
 */
@Service
public class MaintenanceServiceImpl implements IMaintenanceService {

	@Autowired
	RestTemplate restTemplate;

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Autowired
	IMaintenanceRepository maintenanceRepository;

	public static final String BASEURL = "http://localhost:8072/task-service/task";

	@Override
	public Maintenance addMaintenance(Maintenance maintenance) {
		return maintenanceRepository.save(maintenance);
	}

	@Override
	public void updateMaintenance(Maintenance maintenance) {
		maintenanceRepository.save(maintenance);
	}

	@Override
	public void deleteMaintenance(int maintenanceId) {
		maintenanceRepository.deleteById(maintenanceId);
	}

	@Override
	public Maintenance getById(int maintenanceId) {
		return maintenanceRepository.findById(maintenanceId).get();
	}

	@Override
	public List<Maintenance> getAll() {
		return maintenanceRepository.findAll();
	}

	@Override
	public Maintenance getByMaintenanceName(String maintenanceName) {
		return maintenanceRepository.findByMaintenanceName(maintenanceName);
	}

	@Override
	public Maintenance getByMaintenanceManager(String maintenanceManager) {
		return maintenanceRepository.findByMaintenanceManager(maintenanceManager);
	}

	@Override
	public List<Maintenance> getByMaintenanceStartDate(LocalDate mStartDate) {
		return maintenanceRepository.findByMaintenanceStartDate(mStartDate);
	}

	@Override
	public List<Maintenance> getByMaintenanceEndDate(LocalDate mEndDate) {
		return maintenanceRepository.findByMaintenanceEndDate(mEndDate);
	}

	@Override
	public List<Maintenance> getByMaintenanceStatus(Status mStatus) {
		return maintenanceRepository.findByMaintenanceStatus(mStatus);
	}

	@Override
	public List<Maintenance> getByMaintenancePriority(Priority mPriority) {
		return maintenanceRepository.findByMaintenancePriority(mPriority);
	}

	@Override
	public List<Maintenance> getByMaintenanceStartAndEndDate(LocalDate mStartDate, LocalDate mEndDate) {
		return maintenanceRepository.findByMaintenanceStartAndEndDate(mStartDate, mEndDate);
	}

	@Override
	public List<Maintenance> getByMaintenanceStatusAndPriority(Status mStatus, Priority mPriority) {
		return maintenanceRepository.findByMaintenanceStatusAndPriority(mStatus, mPriority);
	}

	@Override
	public Maintenance getByMaintenanceNameAndStatus(String maintenanceName, Status mStatus) {
		return maintenanceRepository.findByMaintenanceNameAndStatus(maintenanceName, mStatus);
	}

	@Override
	public Maintenance getByMaintenanceNameAndPriority(String maintenanceName, Priority mPriority) {
		return maintenanceRepository.findByMaintenanceNameAndPriority(maintenanceName, mPriority);
	}

	@Override
	public Task addTask(Task task,int maintenanceId) {
		Maintenance maintenance=maintenanceRepository.findById(maintenanceId).get();
		task.setMaintenance(maintenance);
		String url = BASEURL;
		ResponseEntity<Task> newTask=restTemplate.postForEntity(url,task,Task.class);
		return newTask.getBody();
		
	}

	public void updateTask(Task task) {
		String url = BASEURL + "/" + task;
		Task tasks = restTemplate.getForObject(url, Task.class);
	}

	public void deleteTask(int taskId) {
		String url = BASEURL + "/ taskId/" + taskId;
		Task tasks = restTemplate.getForObject(url, Task.class);
	}

	public Task getByTaskId(int taskId) {
		String url = BASEURL + "/taskId/" + taskId;
		Task maintenanceList = restTemplate.getForObject(url, Task.class);
		return maintenanceList;
	}

	@Override
	public List<Task> getAllTask() {

		String url = BASEURL;
		List<Task> taskList = restTemplate.getForObject(url, List.class);
		return taskList;
	}

	@Override
	public List<Task> getByMaintenanceId(int maintenanceId) {
		String url = BASEURL + "/maintenanceId/" + maintenanceId;
		List<Task> maintenanceList = restTemplate.getForObject(url, List.class);
		return maintenanceList;
	}

	@Override
	public Task getByOrganiserAndStatus(String organiser, String tStatus) {
		String url = BASEURL + "/organiser/" + organiser + "/tStatus/" + tStatus;
		Task task = restTemplate.getForObject(url, Task.class);
		return task;
	}

	@Override
	public Task getByTaskStartDateAndEndDate(LocalDate tStartDate, LocalDate tEndDate) {
		String url = BASEURL + "/tStartDate/" + tStartDate + "/tEndDate/" + tEndDate;
		Task task = restTemplate.getForObject(url, Task.class);
		return task;
	}
}
