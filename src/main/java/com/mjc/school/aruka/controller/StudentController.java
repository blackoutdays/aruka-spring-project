package com.mjc.school.aruka.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import com.mjc.school.aruka.persistent.Persistent;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mjc.school.aruka.model.Student;

@Controller
public class StudentController {

	@GetMapping("/addStudent")
	public ModelAndView addStudent() {
		ModelAndView mv = new ModelAndView("addStudent");
		mv.addObject("student", new Student());

		mv.addObject("genders", Persistent.getInstance().getGenders());

		mv.addObject("getSubjectsOffered", Persistent.getInstance().getSubjectsOffered());

		mv.addObject("getInterests", Persistent.getInstance().getInterests());
		System.out.print("Interests" + Persistent.getInstance().getInterests());
		return mv;
	}
	// use this function for showing gender selection
//	public Map<String, String> getGenders() {
//		Map<String, String> genders = new HashMap<>();
//		genders.put("M", "Male");
//		genders.put("F", "Female");
//		return genders;
//	}

	@PostMapping("/addStudent")
	public ModelAndView addStudentPosting(@Valid Student student, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("addStudent");
		if (!bindingResult.hasErrors()) {
			Persistent.getInstance().addStudent(student);
			mv.addObject("successMessage", "New Student added");
		} else {
			// System.out.print(student.toString());
			mv.addObject("errorMessage", "Some validations are not met");
		}
		mv.addObject("student", new Student());
		mv.addObject("genders", Persistent.getInstance().getGenders());
		mv.addObject("getSubjectsOffered", Persistent.getInstance().getSubjectsOffered());
		mv.addObject("getInterests", Persistent.getInstance().getInterests());
		return mv;
	}

	@GetMapping("/searchStudents")
	public ModelAndView searchStudents() {
		ModelAndView mv = new ModelAndView("showStudents");
		
		List<Student> list = Persistent.getInstance().showStudents();
		if (!list.isEmpty())
			mv.addObject("students", list);
		else {
			mv.addObject("students", Collections.emptyList());
			mv.addObject("errorMessage", "No students found");
		}
		return mv;
	}

	@PostMapping("/studentgridclick")
	public ModelAndView studentGridclick(@ModelAttribute("selectedStudent") String value,
			@ModelAttribute("search") String action) {
		System.out.print("safeer : " + value + " : " + action);
		String[] splitted = value.split("-");
		ModelAndView mv = new ModelAndView();
		if (splitted[0].equals("show")) {
			System.out.print("ShoW : " + splitted.toString());
			mv.setViewName("redirect:/studentDetails/" + splitted[1]);
		} else if (splitted[0].equals("delete")) {
			Persistent.getInstance().deleteStudent(Integer.parseInt(splitted[1]));
			mv.setViewName("showStudents");
			mv.addObject("students", Persistent.getInstance().showStudents());
		} else if (splitted[0].equals("search")) {
			mv.setViewName("showStudents");
			List<Student> list = Persistent.getInstance().searchStudents(Integer.valueOf(action));
			if (list.isEmpty()) {
				mv.addObject("students", Collections.emptyList());
			} else
				mv.addObject("students", list);
		} else {
			mv.addObject("students", Persistent.getInstance().showStudents());
		}
		return mv;
	}

	@GetMapping("/studentDetails/{registrationid}")
	public ModelAndView showStudentDetails(@PathVariable("registrationid") String registrationid) {
		System.out.print("Show : " + registrationid);
		ModelAndView mv = new ModelAndView("studentDetails");
		try {
			Student student = Persistent.getInstance().getStudent(Integer.valueOf(registrationid));
			if (student != null)
				mv.addObject("student", student);
			else
				mv.addObject("student", new Student());
			mv.addObject("interests", student.getInterests());
			return mv;
		} catch (Exception e) {
			mv.addObject("errorMessage", "Unknown error occurred");
			mv.addObject("student", new Student());
			return mv;
		}
	}
}
