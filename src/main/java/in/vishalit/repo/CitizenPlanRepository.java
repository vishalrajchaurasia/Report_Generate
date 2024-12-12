package in.vishalit.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.vishalit.entity.CitizenPlan;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Integer> {
	
	@Query("Select distinct(planName) from CitizenPlan")
	public List<String> getPlanNames();
	
	@Query("Select distinct(planStatus) from CitizenPlan")
	public List<String> getPlanStatus();
}
