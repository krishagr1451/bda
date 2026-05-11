import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {

        int max = Integer.MIN_VALUE;

        for (IntWritable val : values) {

            if (val.get() > max) {
                max = val.get();
            }
        }

        context.write(key, new IntWritable(max));
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