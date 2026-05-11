import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FilterMapper extends Mapper<Object, Text, NullWritable, Text> {

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {

        String[] fields = value.toString().split(",");

        if (fields[2].equals("HR")) {
            context.write(NullWritable.get(), value);
        }
    }
}
/*
Compile:
javac -classpath `hadoop classpath` -d . *.java

Create jar:
jar -cvf filter.jar *.class

Upload input to HDFS:
hdfs dfs -mkdir -p /input
hdfs dfs -put input.csv /input

Run on Hadoop:
hadoop jar filter.jar FilterDriver /input /output

View output:
hdfs dfs -cat /output/part-m-00000
*/