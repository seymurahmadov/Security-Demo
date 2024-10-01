package com.ltc.securitydemo.security;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepo userRepo;

    private final ModelMapper modelMapper;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsService jwtInMemoryUserDetailsService;




    public UserService(UserRepo userRepo, ModelMapper modelMapper, JwtTokenUtil jwtTokenUtil, UserDetailsService jwtInMemoryUserDetailsService) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtInMemoryUserDetailsService = jwtInMemoryUserDetailsService;
    }


    public List<JwtRequest> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ADMIN"));

        List<UserEntity> all = userRepo.findAll();
        List<JwtRequest> list = all.stream()
                .filter(username -> authentication.getName().equals(username.getEmail()) || isAdmin )
                .map(item -> modelMapper.map(item, JwtRequest.class))
                .toList();

        return list;

    }

    public String update( Long id, JwtRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepo.findById(id).orElseThrow();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));

        if ((authentication != null && authentication.getName().equals(userEntity.getEmail())) || isAdmin) {
            System.out.println("User: " + authentication.getName());

            boolean isUsernameChanged = !request.getEmail().equals(userEntity.getEmail());

            modelMapper.map(request, userEntity);
            userRepo.save(userEntity);

            if (isUsernameChanged) {
                UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(userEntity.getEmail());

                final String newToken = jwtTokenUtil.generateToken(userDetails);

                return "User updated successfully. New token: " + newToken;
            }

            return "User updated successfully";
        } else {
            throw new AccessDeniedException("You do not have permission to update this user.");
        }
    }
}