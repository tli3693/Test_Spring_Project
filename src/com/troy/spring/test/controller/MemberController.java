package com.troy.spring.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.troy.spring.test.data.entity.Member;

@Controller
@RequestMapping("/member.htm")
public class MemberController {

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(Model model) {
		Member member = new Member();
		List<String> preCheckedVals = new ArrayList<String>();
		preCheckedVals.add("Yoga");
		member.setCourses2(preCheckedVals);
		model.addAttribute("member", member);
		List<String> courses2 = new ArrayList<String>();
		courses2.add("Yoga");
		courses2.add("Stretching");
		courses2.add("Pilates");
		courses2.add("Aerobic");
		courses2.add("Oriental");
		model.addAttribute("courses", courses2);
		return "member";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(Model model, Member member,
			BindingResult result) {
		model.addAttribute("member", member);
		return "successMember";
	}

}