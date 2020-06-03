package com.raise.crowd;

import com.raise.crowd.entity.po.MemberPO;
import com.raise.crowd.mapper.MemberPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zmj
 * @date 2020/6/3 15:01
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CrowdMysqlMainClassTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    MemberPOMapper memberPOMapper;

    @Test
    public void memberPOTest(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        MemberPO memberPO = new MemberPO(null,"jack123",encode,"jack","jack@163.com",1,1,"杰克","123456",2);
        memberPOMapper.insertSelective(memberPO);
    }
    @Test
    public void dataSourceTest(){
        try {
            System.out.println(dataSource.getClass().getName());
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
