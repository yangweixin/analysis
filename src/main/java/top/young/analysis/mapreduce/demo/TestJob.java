package top.young.analysis.mapreduce.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.stereotype.Component;
import top.young.analysis.Application;

/**
 * @Method: top.young.analysis.mapreduce.demo
 * @Desc:
 * @Author: Yang Weixin
 * @Date: 2018/2/1 17:32
 */
@Component
public class TestJob {

    public boolean run(String... args) throws Exception{
        if(args.length != 2){
            System.err.println("params not enough");
            System.exit(-1);
        }

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(Application.class);
        job.setJobName("demoJob");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(TestMapper.class);
        job.setReducerClass(TestReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        return job.waitForCompletion(true);
    }
}
