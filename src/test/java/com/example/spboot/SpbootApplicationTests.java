package com.example.spboot;

import com.example.spboot.dao.TUserMapper;
import com.example.spboot.model.TUser;
import com.example.spboot.mq.Sender;

//import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpbootApplication.class)
public class SpbootApplicationTests {
    @Resource
    private TUserMapper tUserMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private Sender sender;
    @Test
    public void test1() {
        TUser tUser = new TUser();

        tUser.setAge(12);
        tUser.setLength(123l);
        tUser.setUserName("lison");
        tUserMapper.insert(tUser);
    }
    @Test
    public void testRedis(){
        ValueOperations v = redisTemplate.opsForValue();
        v.set("name","aaa");
        String name = (String) v.get("name");
        System.out.println(name);
    }
    @Test
    public void testRabbitmq(){
        sender.sendMsg();
    }

    /*@Test
    public void testWebservice(){
            String address="http://localhost:8080/services/";
            JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
            factoryBean.setAddress(address);
            factoryBean.setServiceClass(UserService.class);
            UserService userService= (UserService) factoryBean.create();
            String name = userService.getName("123456");
            System.out.println("返回结果："+name);
    }*/
}
