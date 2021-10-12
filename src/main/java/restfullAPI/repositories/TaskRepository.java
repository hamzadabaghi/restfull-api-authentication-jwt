package restfullAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import restfullAPI.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
