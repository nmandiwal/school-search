package com.mandiwal.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mandiwal.service.UserService;
import com.mandiwal.vo.ClassRecord;

@Controller
public class WelcomeController {

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String main(Model model, Principal principal) {
		String username = ((UsernamePasswordAuthenticationToken) principal).getCredentials().toString();
		if (!userService.isClassDataRecorded(username)) {
			return details(model, principal);
		}

		model.addAttribute("classmates", userService.getClassmates(username));
		return "index"; // view
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login"; // view
	}

	@GetMapping("/details")
	public String details(Model model, Principal principal) {
		String username = ((UsernamePasswordAuthenticationToken) principal).getCredentials().toString();
		model.addAttribute("classRecords", userService.getClassRecords(username));
		return "details"; // view
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long classRoomId, Model model, Principal principal) {
		String username = ((UsernamePasswordAuthenticationToken) principal).getCredentials().toString();
		userService.deleteClassRoom(username, classRoomId);
		return "redirect:/details"; // view
	}

	@GetMapping("/new-class")
	public String newClass(Model model, Principal principal) {
		String username = ((UsernamePasswordAuthenticationToken) principal).getCredentials().toString();
		model.addAttribute("classRecord", ClassRecord.builder().build());
		model.addAttribute("schools", userService.getSchools());
		model.addAttribute("classStandards", IntStream.rangeClosed(1, 12).boxed().collect(Collectors.toList()));
		model.addAttribute("years", IntStream.rangeClosed(1963, LocalDate.now().getYear()).boxed()
				.sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
		model.addAttribute("classRecords", userService.getClassRecords(username));
		return "new"; // view
	}

	@PostMapping("/add-class")
	public String addClass(ClassRecord classRecord, BindingResult result, Model model, Principal principal) {
		String username = ((UsernamePasswordAuthenticationToken) principal).getCredentials().toString();
		userService.addClassRoom(username, classRecord);
		return "redirect:/details"; // view
	}

}