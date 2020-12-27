package com.arunabha.springboot.rms.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


import com.arunabha.springboot.rms.model.User;

@Repository
public class UserRepository {
	
	@Autowired
     JdbcTemplate jdbctemplate;


	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	public UserRepository(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}
	
	public void RegisterUserinDB(User user){
	    String REGISTER_USER_SQL="insert into rms_springdb.user(username,first_name,last_name,email_id,password) values(?,?,?,?,?)";
		jdbctemplate.update(REGISTER_USER_SQL, user.getUsername(),user.getFirst_name(),user.getLast_name(),user.getEmail_id(),passwordEncoder().encode(user.getPassword()));
	}

	/*public int CheckUserExistenceinDB(User user) {
		String SEARCH_USER_SQL="select count(*) from rms_springdb.user where username = ?";
		int count = jdbctemplate.queryForObject(SEARCH_USER_SQL,Integer.class,user.getUsername());
		return count;
		
	}*/

	public int VerifyUserCredinDB(User user) {
		String USERNAME_VERIFY_USER_SQL="select count(*) from rms_springdb.user where username = ?";
		int usr_cnt = jdbctemplate.queryForObject(USERNAME_VERIFY_USER_SQL,Integer.class,user.getUsername());
		return usr_cnt;
	}
	
	public int LoginUserinApp(User user) {
		int passwd_matched=0;
		String PASSWORD_GET_SQL="select password from rms_springdb.user where username = ?";
	    /*String usr_db_passwd=jdbctemplate.queryForObject(PASSWORD_GET_SQL,String.class,user.getUsername());*/
		String usr_db_passwd=jdbctemplate.queryForObject(PASSWORD_GET_SQL,String.class,user.getUsername());
		//String PASSWORD_VERIFY_USER_SQL="select count(*) from rms_springdb.user where username = ? and password = ?";
		//System.out.println("usr_db_passwd" +usr_db_passwd);
		
		if (passwordEncoder().matches(user.getPassword(), usr_db_passwd))
		{
			passwd_matched=1;
			System.out.println("Password matched");
			System.out.println("You are Logged-In, please continue to Explore");
		}
		else
		{
			System.out.println("Password Didn't Match");
			System.out.println("You are not Logged-In, please Login to Explore");
		}
			
		/*String usr_encode_passwd=passwordEncoder().encode(user.getPassword());
		System.out.println("usr_encode_passwd" +usr_encode_passwd);*/
		//usr_pswd_cnt = jdbctemplate.queryForObject(PASSWORD_VERIFY_USER_SQL,Integer.class,user.getUsername(),passwordEncoder().matches(user.getPassword(),passwordEncoder().encode(usr_db_passwd)));
		//usr_pswd_cnt = jdbctemplate.queryForObject(PASSWORD_VERIFY_USER_SQL,Integer.class,user.getUsername(),passwordEncoder().matches(usr_db_passwd,passwordEncoder().encode(user.getPassword())));
		/*System.out.println("Before usr_pswd_cnt" +usr_pswd_cnt);
		usr_pswd_cnt = jdbctemplate.queryForObject(PASSWORD_VERIFY_USER_SQL,Integer.class,user.getUsername(),passwordEncoder().encode(user.getPassword()));
		System.out.println("After usr_pswd_cnt" +usr_pswd_cnt);	
		if (usr_pswd_cnt == 1)
			System.out.println("You are Logged-In, please continue to Explore");
		else
			System.out.println("You are not Logged-In, please Login to Explore");*/
		return passwd_matched;
	}

	public void DeleteUserinApp(User user) {
		String DELETE_USER_SQL="delete from rms_springdb.user where username = ?";
		jdbctemplate.update(DELETE_USER_SQL,user.getUsername());
	}

	public void UpdateUserinApp(User user) {
		String UPDATE_USER_SQL="update rms_springdb.user set first_name = ?,last_name = ?, email_id = ?, password = ? where username = ?";
		//jdbctemplate.update(UPDATE_USER_SQL,user.setFirst_name(user.getFirst_name()),user.setLast_name(user.getLast_name()),user.setEmail_id(user.getEmail_id()),user.setPassword(passwordEncoder().encode(user.getPassword())),user.getUsername());
		String encode_passwd=passwordEncoder().encode(user.getNew_password());
		jdbctemplate.update(UPDATE_USER_SQL,user.first_name,user.last_name,user.email_id,encode_passwd,user.username);
	}

}
