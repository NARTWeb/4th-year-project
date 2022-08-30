package com.nart.service.impl;


import com.nart.dao.messageDao;
import com.nart.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private messageDao MessageDao;



}
