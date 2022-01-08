package com.project.messanger.test.service;

import com.project.messanger.test.Model.TestUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MariadbTestService {
    public List<TestUser> getAllUsers();
}
