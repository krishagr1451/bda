import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxMapper extends Mapper<Object, Text, Text, IntWritable> {

    private Text word = new Text("max");

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {

        int number = Integer.parseInt(value.toString());

        context.write(word, new IntWritable(number));
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