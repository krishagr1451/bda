import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {

        StringTokenizer itr = new StringTokenizer(value.toString());

        while (itr.hasMoreTokens()) {
            word.set(itr.nextToken());
            context.write(word, one);
        }
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