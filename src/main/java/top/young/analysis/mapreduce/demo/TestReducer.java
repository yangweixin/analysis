package top.young.analysis.mapreduce.demo;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Method: top.young.analysis.mapreduce.demo
 * @Desc:
 * @Author: Yang Weixin
 * @Date: 2018/1/15 16:53
 */
public class TestReducer extends Reducer<Text,IntWritable,Text,IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int cnt = 0;
        for( IntWritable value : values ){
            cnt++;
        }

        context.write(key, new IntWritable(cnt));
    }
}
