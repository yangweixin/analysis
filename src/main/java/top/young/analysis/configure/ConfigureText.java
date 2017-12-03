package top.young.analysis.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.young.analysis.entity.User;

@Configuration
public class ConfigureText {

    @Bean(name = "user")
    public User user(){
        return new User("马加特","male");
    }
}
