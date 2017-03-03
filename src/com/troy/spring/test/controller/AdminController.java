package com.troy.spring.test.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.troy.spring.test.data.entity.Admin;
import com.troy.spring.test.data.entity.User;
import com.troy.spring.test.delegate.UserDelegate;
import com.troy.spring.test.validation.UserCheckBoxValidation;

@Controller
@RequestMapping("/test")
public class AdminController {
	
	@Autowired
	private UserDelegate userDelegate;
	
	@Autowired
	private UserCheckBoxValidation checkBoxValidator;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView getdata() {

		List<User> list = getList();

		//return back to index.jsp
		ModelAndView model = new ModelAndView("allUsers");
		model.addObject("title", "Admin Page");
		model.addObject("message", "List of Users");
		model.addObject("filterUser", new User());
		model.addObject("lists", list);
		//model.addObject("listUsername", null);
		Admin admin = new Admin();
		
		model.addObject("admin", admin);
		

		return model;

	}

	private List<User> getList() {
		List<User> list = userDelegate.getAllUsers();
		if (list == null) {
			list = new LinkedList<User>();
		}
		return list;
	}
	
	@RequestMapping(value="/users/selected", method = RequestMethod.POST, params="modifyButton")
	public ModelAndView selectedUsers(Admin admin, BindingResult result, HttpServletResponse response) throws IOException {
		ModelAndView model2 = new ModelAndView("ModifyUsers");
		try {
			checkBoxValidator.validate(admin, result);
			System.out.println("MODIFY BUTTON");
			if (result.hasErrors()) {
				model2 = new ModelAndView("allUsers");
				model2.addObject("title", "Admin Page");
				model2.addObject("message", "List of Users");
				model2.addObject("filterUser", new User());
				model2.addObject("lists", getList());
				return model2;
			}
			// go to modify users page
			List<User> selectedUsers = userDelegate.getUsersByUsername(admin.getListUsername());
	
			if(selectedUsers==null||selectedUsers.isEmpty()) {
				model2 = new ModelAndView("allUsers");
				model2.addObject("title", "Admin Page");
				model2.addObject("message", "List of Users");
				model2.addObject("lists", getList());
				model2.addObject("filterUser", new User());
				model2.addObject("error", "Must select at least one user.");
				return model2;
			}
			model2.addObject("title", "Admin Page");
			model2.addObject("message", "List of Users");
			model2.addObject("lists", selectedUsers);
			
			model2.addObject("admin", admin);
			StringBuilder str = new StringBuilder(selectedUsers.size() + " users selected: ");
			if(admin.getListUsername()!=null) {
				for(String username : admin.getListUsername()) {
					str.append(username + ", ");
				}
				str.delete(str.length()-2, str.length());
				str.append(".");
			}
			
			model2.addObject("selected", str);
		} catch(Exception e) {
			e.printStackTrace();
			model2.setViewName("ErrorPage");
			model2.addObject("title", "Error Page");
			model2.addObject("message", "See message below");
			return model2;
		}
		return model2;
	}
	
	@RequestMapping(value="/users/selected", method = RequestMethod.POST, params="deleteButton")
	public ModelAndView deleteUsers(Admin admin, BindingResult result) {
		System.out.println("DELETE BUTTON");
		ModelAndView model2 = new ModelAndView("allUsers");
		try {
			checkBoxValidator.validate(admin, result);
			model2.addObject("title", "Admin Page");
			model2.addObject("message", "List of Users");
			model2.addObject("filterUser", new User());
			if (result.hasErrors()) {
				model2.setViewName("allUsers");
				model2.addObject("lists", getList());
				return model2;
			}
			
			String msg = null;
			
			if(!userDelegate.deleteUsers(admin.getListUsername())) {
				msg = "Failed to delete users: ";
			} else
				msg = admin.getListUsername().size() + " users deleted: ";
			StringBuilder str = new StringBuilder(msg);
			if(admin.getListUsername()!=null) {
				for(String username : admin.getListUsername()) {
					str.append(username + ", ");
				}
				str.delete(str.length()-2, str.length());
				str.append(".");
			}
			model2.addObject("selected", str);
			List<User> list = getList();
			model2.addObject("lists", list);
		} catch(Exception e) {
			model2.addObject("title", "Admin Page");
			model2.addObject("message", "Failed to delete users.");
			System.out.println("CAUGHT EXCEPTION");
			return model2;
		} finally {
			
		}
		return model2;

	}
	
	@RequestMapping(value="/users/update", method = RequestMethod.POST, params="updateButton")
	public ModelAndView updateUsers(Admin admin, BindingResult result) {
		System.out.println("UPDATE BUTTON");
		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView("allUsers");
			model.addObject("filterUser", new User());
			model.addObject("error", "Error?");
			return model;
		}
		//return back to list users
		ModelAndView model2 = new ModelAndView("allUsers");
		model2.addObject("filterUser", new User());
		model2.addObject("title", "Admin Page");
		model2.addObject("message", "List of Users");
		
		String msg = null;
		
		if(admin.getListUsers()!=null&&!admin.getListUsers().isEmpty()&&!userDelegate.updateUsers(admin.getListUsers())) {
			msg = "Failed to update users: ";
		} else {
			msg = admin.getListUsers().size() + " users updated: ";
		}
		StringBuilder str = new StringBuilder(msg);
		if(admin.getListUsers()!=null) {
			for(User user : admin.getListUsers()) {
				str.append(user.getUsername() + ", ");
			}
			str.delete(str.length()-2, str.length());
			str.append(".");
		}
		model2.addObject("selected", str);
		List<User> list = getList();
		model2.addObject("lists", list);
		return model2;

	}
	
	@RequestMapping(value="/users/update", method = RequestMethod.POST, params="cancelButton")
	public ModelAndView cancelAction(Admin admin, BindingResult result) {
		System.out.println("CANCEL BUTTON");
		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView("allUsers");
			model.addObject("error", "Error?");
			return model;
		}
		//return back to list users
		ModelAndView model2 = new ModelAndView("allUsers");
		model2.addObject("filterUser", new User());
		model2.addObject("title", "Admin Page");
		model2.addObject("message", "List of Users");
		
		List<User> list = getList();
		model2.addObject("lists", list);
		return model2;

	}
	
	@RequestMapping(value="/users/filtered", method = RequestMethod.POST, params="filterButton")
	public ModelAndView filterUsers(Admin admin, BindingResult result, User filterUser) {
		System.out.println("FILTER BUTTON");
		ModelAndView model2 = new ModelAndView("allUsers");

		// TODO: Change to textbox validator
		model2.addObject("title", "Admin Page");
		model2.addObject("message", "List of Users");
		model2.addObject("filterUser", new User());
		List<User> listUsers = userDelegate.filterUsers(getList(), filterUser);
		
		model2.addObject("lists", listUsers);
		if (result.hasErrors()) {
			model2.addObject("lists", getList());
			return model2;
		}
	return model2;

	}

}
