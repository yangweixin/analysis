package top.young.analysis.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * @Method: top.young.analysis.service
 * @Desc:
 * @Author: Yang Weixin
 * @Date: 2018/2/1 17:35
 */
@Component
public class ReadFileDemo {

    Logger logger = LoggerFactory.getLogger(ReadFileDemo.class);

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public void read(String... args){
        InputStream in = null;
        try {
            in = new URL(args[0]).openStream();
            IOUtils.copyBytes(in, System.out, 4096, false);
        } catch (Exception ex) {
            logger.error("读取失败", ex);
        } finally {
            IOUtils.closeStream(in);
        }

    }

    public void readV2(String... args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        FSDataInputStream in = null;
        try{
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 2096, false);
            in.seek(10);
            IOUtils.copyBytes(in, System.out, 2096, false);
        }catch (Exception ex){
            logger.error("read failed", ex);
        }finally {
            IOUtils.closeStream(in);
        }

    }
}
