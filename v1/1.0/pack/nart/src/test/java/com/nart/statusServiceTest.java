package com.nart;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nart.pojo.status;
import com.nart.service.StatusService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class statusServiceTest {
    @Autowired
    private StatusService StatusService;
    @Test
    void contextLoads() {
    }

    @Test
    public void showStatusListTest(){
        IPage<status> page=new Page<>(1,2);
        IPage iPage = StatusService.showStatusList(1L, page);
        System.out.println("一共多少页:"+iPage.getPages());
        System.out.println(iPage.getRecords());

    }
}
