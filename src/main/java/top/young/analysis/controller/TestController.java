package top.young.analysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.young.analysis.dao.UserMapper;
import top.young.analysis.util.RedisUtil;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/getUser")
    public Object getUser(){

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
