package com.dailycodebuffer.user.service;

import com.dailycodebuffer.user.entity.User;
import com.dailycodebuffer.user.repository.UserRepository;
import com.dailycodebuffer.user.vo.Department;
import com.dailycodebuffer.user.vo.UserWithDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public UserWithDepartment findUserById(Long userId) {
        User user = userRepository.findByUserId(userId);

        Department department =
                restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),
                Department.class);
        UserWithDepartment userWithDepartment = new UserWithDepartment();
        userWithDepartment.setUser(user);
        userWithDepartment.setDepartment(department);
        return userWithDepartment;
    }

    public User findUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }
}
