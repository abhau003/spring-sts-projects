package com.arunabha.springboot.rms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arunabha.springboot.rms.model.User;
import com.arunabha.springboot.rms.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	public UserRepository userrepository;
	
	List<User> list = new ArrayList<>();
	
	public void sendUserDetailsforRegistration(User user) {
		int return_code=userrepository.VerifyUserCredinDB(user);
		if ( return_code == 1)
			System.out.println("Username Already Exists");
		else
		userrepository.RegisterUserinDB(user);
	}

	public void sendUserDetailsforLogin(User user) {
		int user_verify=userrepository.VerifyUserCredinDB(user);
		if (user_verify == 1)
			userrepository.LoginUserinApp(user);
		else
			System.out.println("You are not Registered, Please register to Continue");
			
	}

	public void sendUserDetailsforDelete(User user) {
		int return_code=userrepository.VerifyUserCredinDB(user);
		if ( return_code == 1)
			userrepository.DeleteUserinApp(user);
		else
			System.out.println("User Deletion Not permitted, User doesnt exist, please verify");
	}

	public void sendUserDetailsforUpdate(User user) {
		int login_response=0;
		int user_verify=userrepository.VerifyUserCredinDB(user);
		if (user_verify == 1) 
		{
			login_response=userrepository.LoginUserinApp(user);
			if (login_response == 1)
				userrepository.UpdateUserinApp(user);
			else
				System.out.println("User Not Logged In, Please Login to Continue");
		}
		else
		{
			System.out.println("User Not Registered, please register User first");
		}
	}




/*	public void AddUserDetails(User user) {
		list.add(user);
	}


	public List<User> UserDetails(){
		return list;
	}
*/	
}