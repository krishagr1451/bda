import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

    public void reduce(Text key, Iterable<DoubleWritable> values, Context context)
            throws IOException, InterruptedException {

        double sum = 0;
        int count = 0;

        for (DoubleWritable val : values) {
            sum += val.get();
            count++;
        }

        context.write(key, new DoubleWritable(sum / count));
    }
}
/*
Compile:
javac -classpath `hadoop classpath` -d . *.java

Create jar:
jar -cvf average.jar *.class

Upload input to HDFS:
hdfs dfs -mkdir -p /input
hdfs dfs -put input.txt /input

Run on Hadoop:
hadoop jar average.jar <YourAvgDriver> /input /output

View output:
hdfs dfs -cat /output/part-r-00000
*/