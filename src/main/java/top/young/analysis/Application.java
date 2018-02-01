package top.young.analysis;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.young.analysis.mapreduce.demo.TestJob;
import top.young.analysis.mapreduce.demo.TestMapper;
import top.young.analysis.mapreduce.demo.TestReducer;
import top.young.analysis.service.ReadFileDemo;

@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    private TestJob testJob;
    @Autowired
    private ReadFileDemo readFileDemo;

    public static void main(String[] args){
        SpringApplication app = new SpringApplication(Application.class);
        app.setWebEnvironment(false);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.exit(testJob.run(args) ? 0 : -1);
        readFileDemo.read(args);
    }
}
