package top.young.analysis;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class TestController {

    @Autowired
    User user;

    @RequestMapping("test")
    public String test(){
        System.out.println(System.getProperty("user.dir"));
        return user.getUsername();
    }
}
