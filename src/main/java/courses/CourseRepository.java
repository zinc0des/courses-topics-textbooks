package courses;

import java.util.Collection;
import java.util.Collections;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {

	Collection<Course> findByTopicsContains(Topic topic);
	
	Collection<Course> findByTopicsId(Long id);
	
	Collection<Course> findByTextbooksContains(Textbook textbook);
	
	Collection<Course> findByTextbooksId(Long id);

}
