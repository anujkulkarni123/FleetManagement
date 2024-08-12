package com.exavalu.fleetmanagementapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.exavalu.fleetmanagementapp.models.User;
import com.exavalu.fleetmanagementapp.repositories.RoleRepository;
import com.exavalu.fleetmanagementapp.repositories.UserRepository;

 
@Component
public class CustomUserDetailsService implements UserDetailsService {
 
	@Autowired
	private UserRepository empRepo;
 
	@Autowired
	private RoleRepository roleRepository;
 
	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User emp = empRepo.findByEmail(email);
		String roleName = roleRepository.findRoleNameByUserId(emp.getUserId());
 
		if (emp == null) {
			throw new UsernameNotFoundException("user name not found");
		} else {
			return new CustomUser(emp, roleName);
		}
 
	}
 
}