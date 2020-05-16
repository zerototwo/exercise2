package com.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

public class WordCount {

    public static class  WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable>{

        private Text mapOutputKey = new Text();
        private final static IntWritable mapOutputValue = new IntWritable(1);


        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String lineValue = value.toString();
            StringTokenizer stringTokenizer = new StringTokenizer(lineValue);

            while (stringTokenizer.hasMoreTokens()) {

                String wordValue = stringTokenizer.nextToken();
                mapOutputKey.set(wordValue);
                context.write(mapOutputKey,mapOutputValue);
            }

        }
    }

    public static class  WordCountReduce extends Reducer<Text, IntWritable,Text, IntWritable> {

        private final static IntWritable mapOutputValue = new IntWritable();
        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values) {

                sum += value.get();
            }
            mapOutputValue.set(sum);

            context.write(key,mapOutputValue);
        }
    }

    public static int run(String[] args) throws Exception {

        //1.configuration
        Configuration conf = new Configuration();
        //2.create job
        Job job = Job.getInstance(conf, "lpp");
        //3.set job
        //input-->map->reduce->output
        //3.1:input
        Path inPath = new Path(args[0]);
        FileInputFormat.addInputPath(job, inPath);
        //3.2:map
        job.setMapperClass(WordCountMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //3.3 reduce
        job.setReducerClass(WordCountReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //3.4 output
        Path outPath = new Path(args[1]);
        FileOutputFormat.setOutputPath(job, outPath);

        //4:submit job
        boolean isSuccess = job.waitForCompletion(true);

        return isSuccess ? 0:1;

    }

    public static void main(String[] args) throws Exception {

        int status = new WordCount().run(args);
        System.exit(status);
    }
}
