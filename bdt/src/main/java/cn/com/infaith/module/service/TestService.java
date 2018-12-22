package cn.com.infaith.module.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestService {

    @Autowired
    private TableDataService tableDataService;

    @Test
    public void test1() {

        System.out.println(tableDataService.getTableResultCalCount(1,2,40));
    }
}
