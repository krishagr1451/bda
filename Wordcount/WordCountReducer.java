import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {

        int sum = 0;

        for (IntWritable val : values) {
            sum += val.get();
        }

        context.write(key, new IntWritable(sum));
    }
}
/*
Compile:
javac -classpath `hadoop classpath` -d . *.java

Create jar:
jar -cvf wordcount.jar *.class

Upload input to HDFS:
hdfs dfs -mkdir -p /input
hdfs dfs -put input.txt /input

Run on Hadoop:
hadoop jar wordcount.jar WordCountDriver /input /output

View output:
hdfs dfs -cat /output/part-r-00000
*/