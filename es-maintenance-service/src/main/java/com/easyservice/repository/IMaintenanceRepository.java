package com.easyservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easyservice.model.Maintenance;
import com.easyservice.model.Priority;
import com.easyservice.model.Status;

/**
 * @author GAYATHRI S
 *
 */
@Repository
public interface IMaintenanceRepository extends JpaRepository<Maintenance, Integer> {
	
	Maintenance findByMaintenanceName(String maintenanceName);

	Maintenance findByMaintenanceManager(String maintenanceManager);

	@Query("from Maintenance where mStartDate=?1")
	List<Maintenance> findByMaintenanceStartDate(LocalDate mStartDate);

	@Query("from Maintenance where mEndDate=?1")
	List<Maintenance> findByMaintenanceEndDate(LocalDate mEndDate);

	@Query("from Maintenance where mStatus=?1")
	List<Maintenance> findByMaintenanceStatus(Status mStatus);

	@Query("from Maintenance where mPriority=?1")
	List<Maintenance> findByMaintenancePriority(Priority mPriority);

	@Query("from Maintenance where mStartDate=?1 and mEndDate=?2")
	List<Maintenance> findByMaintenanceStartAndEndDate(LocalDate mStartDate, LocalDate mEndDate);

	@Query("from Maintenance where mStatus=?1 and mPriority=?2")
	List<Maintenance> findByMaintenanceStatusAndPriority(Status mStatus, Priority mPriority);

	@Query("from Maintenance where maintenanceName=?1 and mStatus=?2")
	Maintenance findByMaintenanceNameAndStatus(String maintenanceName, Status mStatus);

	@Query("from Maintenance where maintenanceName=?1 and mPriority=?2")
	Maintenance findByMaintenanceNameAndPriority(String maintenanceName, Priority mPriority);
	

}
