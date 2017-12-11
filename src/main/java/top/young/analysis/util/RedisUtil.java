package top.young.analysis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    private Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    @Autowired
    private RedisTemplate redisTemplate;

    public Long removeKeys(final String... keys){
        Long cnt = 0L;
        for (int i = 0; i < keys.length; i++) {
            if(redisTemplate.delete(keys[i])){
                cnt++;
            }
        }
        return cnt;
    }

    public Long removePattern(final String pattern){
        Set set = redisTemplate.keys(pattern);
        if(set.size() > 0){
            return redisTemplate.delete(set);
        }else{
            return 0L;
        }
    }

    public boolean  remove(final String key){
        try{
            if(exists(key)){
                return redisTemplate.delete(key);
            }
        }catch (Exception ex){
            logger.error("移除键异常",ex);
        }
        return false;
    }

    public boolean exists(final String key){
        return redisTemplate.hasKey(key);
    }

    public boolean set(final String key, Object value, Long expireTime){
        try{
            ValueOperations valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key, value);
            redisTemplate.expire(key,expireTime, TimeUnit.SECONDS);
            return true;
        }catch (Exception ex){
            logger.error("设置redis键值异常",ex);
            return false;
        }
    }

    public boolean set(final String key, Object value){
        try{
            ValueOperations valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key, value);
            return true;
        }catch (Exception ex){
            logger.error("设置redis键值异常",ex);
            return false;
        }
    }

    public Object get(final String key){
        try{
            ValueOperations valueOperations = redisTemplate.opsForValue();
            Object result = valueOperations.get(key);
            return result;
        }catch (Exception ex){
            logger.error("获取redis键值异常",ex);
            return null;
        }

    }
}
