package com.mandiwal.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

//	@Autowired
//	private UserRepository userRepository;

	public MyUserDetailsService() {
		super();
	}

	// API

	@Override
	public UserDetails loadUserByUsername(final String username) {
		/*
		 * final User user = userRepository.findByUsername(username); if (user == null)
		 * { throw new UsernameNotFoundException(username); } return new
		 * org.springframework.security.core.userdetails.User(username,
		 * user.getPassword(), true, true, true, true, Arrays.asList(new
		 * SimpleGrantedAuthority("ROLE_USER")));
		 */
		return null;
	}
}
