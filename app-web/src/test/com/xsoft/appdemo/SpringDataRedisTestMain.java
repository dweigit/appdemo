package com.xsoft.appdemo;

import com.xsoft.appdemo.model.UserInfo;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Created by dengwei06015 on 2016/6/1.
 */
public class SpringDataRedisTestMain {
    /**
     * @param args
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-redis.xml");
        RedisTemplate redisTemplate = (RedisTemplate)context.getBean("jedisTemplate");
        //其中key采取了StringRedisSerializer  
        //其中value采取JdkSerializationRedisSerializer  
        ValueOperations<String, UserInfo> valueOper = redisTemplate.opsForValue();
        UserInfo u1 = new UserInfo();
        u1.setUserId(1);
        u1.setUserName("zhangsan");
        u1.setComments("hello zhangshan");
        UserInfo u2 = new UserInfo();
        u2.setUserId(2);
        u2.setUserName("lisi");
        u2.setComments("hello lisi");

        valueOper.set("u:u1", u1);
        valueOper.set("u:u2", u2);

        System.out.println(valueOper.get("u:u1").getUserName());
        System.out.println(valueOper.get("u:u2").getUserName());
    }

}
