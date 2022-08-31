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

    @Test
    public void TestpostStatus(){
        status status = new status();
        status.setSenderId("1");
        status.setText("erfwf");
        status.setPics("1122323");
        status.setCreateDate(13232344L);
        boolean b = StatusService.postStatus(status);
        System.out.println(b);
    }

    @Test
    public void TestdelStatus(){
        boolean b = StatusService.delStatus("1564436341058641922");
        System.out.println(b);
    }
    
    @Test
    public void TestlikeStatus(){
        boolean b = StatusService.likeStatus("1", true);
        System.out.println(b);
    }
}