package edu.swe266.service.impl;

import edu.swe266.dao.UserMapper;
import edu.swe266.service.IMainService;
import org.springframework.beans.factory.annotation.Autowired;

public class MainServiceImpl implements IMainService {

    @Autowired
    UserMapper mapper;

    public void foo(){

    }
}
