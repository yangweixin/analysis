package top.young.analysis.mapreduce.demo;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapreduce.Mapper;


import java.io.IOException;

/**
 * @Method: top.young.analysis.mapreduce
 * @Desc:
 * @Author: Yang Weixin
 * @Date: 2018/1/15 16:52
 */
public class TestMapper extends Mapper<LongWritable,Text,Text,IntWritable>{

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        for(String k : line.split(" ")){
            context.write(new Text(k),new IntWritable(1));
        }
    }
}
