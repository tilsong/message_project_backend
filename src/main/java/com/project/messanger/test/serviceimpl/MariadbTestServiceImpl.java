package com.project.messanger.test.serviceimpl;

import com.project.messanger.test.Model.TestUser;
import com.project.messanger.test.dao.MariadbTestDao;
import com.project.messanger.test.service.MariadbTestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MariadbTestServiceImpl {

    MariadbTestDao daoMapper;

    public MariadbTestServiceImpl(MariadbTestDao daoMapper) {
        this.daoMapper = daoMapper;
    }

    public List<TestUser> getAllUsers(){
        return daoMapper.getAllUsers();
    }
}
