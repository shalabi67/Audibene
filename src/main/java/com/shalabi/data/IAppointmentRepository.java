package com.shalabi.data;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface IAppointmentRepository extends CrudRepository<Appointment, Long> {
	@Query("select c from Appointment c where c.appointmentDate = :appointmentDate")
	public List<Appointment> getAppointmentsByDate(@Param("appointmentDate") Date appointmentDate);
	
	@Query("select c from Appointment c inner join c.audiologist a inner join c.customer d where a.id=:audiologistId and c.appointmentDate >= :startDate and c.appointmentDate <= :endDate")
	public List<Appointment> getAudiologistAppointmentsBetweenDates(@Param("audiologistId") Long audiologistId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	List<Appointment> findByAudiologist_id(Long id);
}
