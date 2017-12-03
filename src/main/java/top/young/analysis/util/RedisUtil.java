package top.young.analysis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisUtil {

    private StringRedisTemplate template;

    @Autowired
    public RedisUtil(StringRedisTemplate template) {
        this.template = template;
    }

}
