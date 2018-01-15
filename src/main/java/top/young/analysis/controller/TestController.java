package top.young.analysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.young.analysis.configure.ConfigureText;
import top.young.analysis.dao.UserMapper;
import top.young.analysis.entity.User;
import top.young.analysis.util.RedisUtil;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private User user;
    @Autowired
    private String message;
    @Value("${young.test}")
    private String youngTest;

    @RequestMapping("/getUser")
    public Object getUser(){
        System.out.println(message);
        System.out.println(youngTest);
        Object obj = userMapper.getUser();
        return obj;
    }

    @RequestMapping("/setRedis/{key}/{value}")
    public boolean setRedis(@PathVariable String key,@PathVariable String value){
        return redisUtil.set(key,value);
    }

    @RequestMapping("/Redis/{key}")
    public Object Redis(@PathVariable String key){
        return redisUtil.get(key);
    }
}
