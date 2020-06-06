package com.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.StringTokenizer;

public class MapReduceTemplete extends Configured  implements Tool {

    public static class  ModuleMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

        private Text mapOutputKey = new Text();
        private final static IntWritable mapOutputValue = new IntWritable(1);


        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {


        }

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            super.cleanup(context);
        }
    }

    public static class  ModuleReduce extends Reducer<Text, IntWritable,Text, IntWritable> {

        private final static IntWritable mapOutputValue = new IntWritable();
        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        }

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            super.cleanup(context);
        }
    }

    @Override
    public int run(String[] strings) throws Exception {
        //1.configuration
        Configuration conf = new Configuration();
        //2.create job
        Job job = Job.getInstance(conf, "lpp");
        //3.set job
        //input-->map->reduce->output
        //3.1:input
        Path inPath = new Path(strings[0]);
        FileInputFormat.addInputPath(job, inPath);
        //3.2:map
        job.setMapperClass(ModuleMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        /**********shuffle***************************************************************************************/
        //1)partitioner
        //job.setPartitionerClass();
        //2)sort
        //job.setSortComparatorClass();
        //3)option conbine
        //job.setCombinerClass();
        //4) group
        //job.setGroupingComparatorClass();

        /******************************************************************************************************/
        //3.3 reduce
        job.setReducerClass(ModuleReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //3.4 output
        Path outPath = new Path(strings[1]);
        FileOutputFormat.setOutputPath(job, outPath);

        //4:submit job
        boolean isSuccess = job.waitForCompletion(true);

        return isSuccess ? 0:1;
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();

        int status = ToolRunner.run(configuration, (Tool) new ModuleMapper(), args);

        System.exit(status);
    }
}
