package MIN;
import java.util.Random;

public class SA {
    //该类为模拟退火算法的具体实现类

    public static int[] series;//当前序列
    public static int[] last;//最优化序列

    static {
        series = new int[Init.works_num];
        last = new int[Init.works_num];
        for ( int i = 0; i < series.length; ++i ) {
            series[i] = i;
            last[i] = i;
        }//初始化序列
    }

    public static int findWork(int a) {  //用以找到指定序号的工件在序列中的位置
        for ( int i = 0; i < series.length; ++i ) {
            if ( series[i] == a ) {
                return i;
            }
        }
        return -1;
    }

    private static void randomNext() {//从邻域中随机寻找下一组值
        last = series.clone();
        for ( int i = 0; i < Init.timeTable_last.length; ++i ) {
            for ( int j = 0; j < Init.timeTable_last[0].length; ++j ) {
                System.arraycopy(Init.timeTable_cur[i][j], 0, Init.timeTable_last[i][j], 0, Init.timeTable_last[0][0].length);
            }
        }
        Random rand = new Random();
        int a, b;
        a = rand.nextInt(0, series.length);
        b = rand.nextInt(0, series.length);
        while ( a == b ) { b = rand.nextInt(0, series.length); }
        int temp = series[b];
        series[b] = series[a];
        series[a] = temp;
    }

    public static long simulate() {
        final double theta = 0.999;//温度下降系数
        final double T_freeze = 0.01;//冷却时间阈值
        final int stop_max = 150;//停止阈值
        long time_cur = 1000000000;//时间
        double T_start = 1000;//起始温度
        int stop = 0;//未接收新解计数
        int cnt = 0;
        double p;

        while ( T_start > T_freeze ) {
            if ( Init.debug ) {
                System.out.println("Simulating " + ++cnt + " times. Last res is " + Init.time + ".");
            }
            T_start = T_start * theta;//温度下降
            randomNext();//选择新解

            CalcTime.calcTime(series);//计算时间

            if ( Init.time < time_cur ) {
                time_cur = Init.time;
                stop = 0;
            } else {
                p = Math.exp((time_cur - Init.time) * 100d / time_cur / T_start);//是否接受差解
                if ( new Random().nextDouble(0,1) <= p ) {
                    time_cur = Init.time;
                    stop = 0;
                }
                else {
                    series = last;
                    for ( int i = 0; i < Init.timeTable_last.length; ++i ) {
                        for ( int j = 0; j < Init.timeTable_last[0].length; ++j ) {
                            System.arraycopy(Init.timeTable_last[i][j], 0, Init.timeTable_cur[i][j], 0, Init.timeTable_last[0][0].length);
                        }
                    }
                    if ( ++stop > stop_max ) { break; }
                }
            }
        }
        return Init.time;
    }

    public static long noWaitSimulate() {//无等待约束
        //系数的含义同上
        final double theta = 0.999;
        final double T_freeze = 0.01;
        final int stop_max = 150;
        long time_cur = 1000000000;
        double T_start = 1000;
        int stop = 0;
        int cnt = 0;
        double p;

        while ( T_start > T_freeze ) {
            if ( Init.debug ) {
                System.out.println("Simulating " + ++cnt + " times. Last res is " + Init.time + ".");
            }
            T_start = T_start * theta;
            randomNext();

            NoWaitCalcTime.calcTime(series);

            if ( Init.time < time_cur ) {
                time_cur = Init.time;
                stop = 0;
            } else {
                p = Math.exp((time_cur - Init.time) * 100d / time_cur / T_start);
                if ( new Random().nextDouble(0,1) <= p ) {
                    time_cur = Init.time;
                    stop = 0;
                }
                else {
                    series = last;
                    for ( int i = 0; i < Init.timeTable_last.length; ++i ) {
                        for ( int j = 0; j < Init.timeTable_last[0].length; ++j ) {
                            System.arraycopy(Init.timeTable_last[i][j], 0, Init.timeTable_cur[i][j], 0, Init.timeTable_last[0][0].length);
                        }
                    }
                    if ( ++stop > stop_max ) { break; }
                }
            }
        }
        return Init.time;
    }

    public static void main(String[] args) {
        simulate();
        System.out.println(Init.time);
    }
}
