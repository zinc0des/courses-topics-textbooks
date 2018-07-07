package courses;

import java.util.Collection;
import java.util.Collections;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {

	Collection<Course> findByTopicsContains(Topic java);
	
	Collection<Course> findByTopicsId(Long id);

}
