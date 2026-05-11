import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaxDriver {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf, "Maximum Number");

        job.setJarByClass(MaxDriver.class);

        job.setMapperClass(MaxMapper.class);
        job.setReducerClass(MaxReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
    /*
    Compile:
    javac -classpath `hadoop classpath` -d . *.java

    Create jar:
    jar -cvf max.jar *.class

    Upload input to HDFS:
    hdfs dfs -mkdir -p /input
    hdfs dfs -put input.txt /input

    Run on Hadoop:
    hadoop jar max.jar MaxDriver /input /output

    View output:
    hdfs dfs -cat /output/part-r-00000
    */