package com.troy.spring.test.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.troy.spring.test.data.dao.HibernateUserDAOImpl;
import com.troy.spring.test.data.entity.User;
import com.troy.spring.test.delegate.UserDelegate;


@Controller
@RequestMapping("/home")
public class LoginController {

	@Autowired
	private UserDelegate userDelegate;
	
	@Autowired
    protected AuthenticationManager authenticationManager;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model = new ModelAndView("hello");
			model.addObject("title", "You are already logged in!");
			model.addObject("message", "This is default page!");
		    /* The user is logged in :) */
		    return model;
		}
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Welcome");
		model.addObject("message", "Login Page!");
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("LoginForm");

		return model;

	}

	@RequestMapping("/register")
	public ModelAndView register(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ModelAndView model2 = new ModelAndView("hello");
			model2.addObject("title", "You are already logged in!");
			model2.addObject("message", "This is default page!");
		    /* The user is logged in :) */
		    return model2;
		}
		ModelAndView model2 = new ModelAndView("RegistrationForm");
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Registration Page!");
		model.addAttribute("user", new User());
		return model2;
	}

	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public String loginUser(User user, Model model) {
		
		if (userDelegate.loginUser(user) != null) {
			model.addAttribute(user);
			return "LoginSuccess";
			
		}
		return "LoginFailure";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, Model model) throws SQLException {
		ModelAndView model2 = new ModelAndView("LoginSuccess");
		if (bindingResult.hasErrors()) {
			model2.setViewName("RegistrationForm");
			model2.addObject("title", "Registration failed");
			model2.addObject("message", "Fix the errors to continue.");
			return model2;
		}
		String password = user.getPassword();
		
		// Hash Password
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		user.setPassword(hashedPassword);
		
		// Attempt to register user
		if (userDelegate.registerUser(user)) {
			model.addAttribute(user);
			Authentication authenticatedUser = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), password));
			SecurityContext securityContext = SecurityContextHolder.getContext();

	        securityContext.setAuthentication(authenticatedUser);
		} else { // if user already exists
			model2.setViewName("RegistrationForm");
			model2.addObject("title", "Registration failed");
			model2.addObject("message", "Username taken.");
		}
		
		//userDelegate.testHibernate();
		return model2;

	}
	
}
