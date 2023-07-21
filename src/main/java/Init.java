package MIN;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Init  {
    //此为整体系统初始化类
    static boolean debug = false;
    static int works_num;
    static int machines_num;
    static int[][] chart;
    static Machine[] machines;
    static Work[] works;
    static long time = 0;
    static long min_time = 1000000000;
    static long[][][] timeTable_cur;
    static long[][][] timeTable_last;
    static long[][][] timeTable_min;
    static int[] series_min;

    public static void init(String a) throws IOException {
        //读入文件
        //初始化工件和机器
        Path path = Paths.get(a);
        String data = Files.readString(path);
        String[] sp =  data.split("\n");
        var temp = sp[0].substring(0,sp[0].length() - 1).split("\\s+");

        works_num = Integer.parseInt(temp[0]);
        machines_num = Integer.parseInt(temp[1]);

        sp = Arrays.copyOfRange(sp, 1, sp.length);

        chart = new int[works_num][machines_num];
        for ( int i = 0; i < sp.length; ++i ) {
            String[] inner = sp[i].split("\\s+");
            for ( int j = 0; j < machines_num * 2; ++j ) {
                ++j;
                chart[i][j/2] = Integer.parseInt(inner[j]);
            }
        }

        if ( debug ) {
            for ( int[] i : chart ) {
                for ( int j : i ) {
                    System.out.print(j + " ");
                }
                System.out.println();
            }
        }

        machines = new Machine[machines_num];
        works = new Work[works_num];
        timeTable_cur = new long[machines_num][works_num][2];
        timeTable_min = new long[machines_num][works_num][2];
        timeTable_last = new long[machines_num][works_num][2];
        for ( int i = 0; i < machines.length; ++i ) {
            machines[i] = new Machine(String.valueOf(i));
        }
        for ( int i = 0; i < works.length; ++i ) {
            works[i] = new Work(String.valueOf(i));
        }
    }

    public static void timeTablePersistence() throws IOException {
        //计算结果持久化
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter
                ("./MIN/timeTable.txt"));
        for ( var i : series_min ) {
            bufferedWriter.write(i + " ");
        }
        bufferedWriter.write("\n");
        for ( int i = 0; i < machines_num; ++i ) {
            for ( int j = 0; j < works_num; ++j ) {
                bufferedWriter.write(timeTable_min[i][j][0] + " " +
                        timeTable_min[i][j][1] + " ");
            }
            bufferedWriter.write("\n");
        }
        bufferedWriter.close();
    }

    public static void main(String[] args) throws IOException {
        debug = true;
        init("C:\\Users\\Alan\\Desktop\\programme\\.java\\MIN\\test.txt");
    }
}
