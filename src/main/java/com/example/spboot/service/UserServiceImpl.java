package com.example.spboot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Override
    @Transactional
    public void testException() {
        int a=1/0;
    }
}
