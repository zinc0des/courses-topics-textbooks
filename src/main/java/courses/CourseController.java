package courses;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {
	
	@Resource
	CourseRepository courseRepo;

	@Resource
	TopicRepository topicRepo;
	
	@Resource
	private TextbookRepository textbookRepo;
	
	@RequestMapping("/course")
	public String findOneCourse(@RequestParam(value="id")long id, Model model) throws CourseNotFoundException {
		Optional<Course> course = courseRepo.findById(id);
		
		if(course.isPresent()) {
			model.addAttribute("courses", course.get());
			return "course";
		}
		throw new CourseNotFoundException();
	}

	@RequestMapping("/show-courses")
	public String findAllCourses(Model model) {
		model.addAttribute("courses", courseRepo.findAll());
		return "courses";
		
	}
	
	@RequestMapping("/topic")
	public String findOneTopic(@RequestParam(value = "id") long id, Model model) throws TopicNotFoundException {
		Optional<Topic> topic = topicRepo.findById(id);
		
		if(topic.isPresent()) {
			model.addAttribute("topics",topic.get());
			model.addAttribute("courses", courseRepo.findByTopicsContains(topic.get()));
			return "topic";
			}
		throw new TopicNotFoundException();
	}
	@RequestMapping("/topics")
	public String findAllTopics(Model model) {
		model.addAttribute("topics", topicRepo.findAll());
		return "topics";
	}
	
	@RequestMapping("/textbook")
	public String findOneTextBook(@RequestParam(value="id")long textBookId, Model model) throws TextBookNotFoundException {
		Optional<Textbook> textbook = textbookRepo.findById(textBookId);
		
		if(textbook.isPresent()) {
			model.addAttribute("textbooks", textbook.get());
			model.addAttribute("courses", courseRepo.findByTextbooksContains(textbook.get()));
			return ("textbook");
		}
		throw new TextBookNotFoundException();
	}
	
	@RequestMapping("/show-textbooks")
	public String findAllBooks(Model model) {
		model.addAttribute("textbooks", textbookRepo.findAll());
		return ("textbooks");
	}
}
