package MIN;
import java.io.IOException;

public class Main {
    public static long startSA(int N, boolean noWait) throws IOException {
        long min = 1000000000;

        //N次重复实验取最小值
        for ( int i = 0; i < N; ++i ) {
            long temp;
            if ( noWait ) {
                temp = SA.noWaitSimulate();
            } else {
                temp = SA.simulate();
            }
            if ( min > temp ) {
                for ( int o = 0; o < Init.machines_num; ++o ) {
                    for ( int j = 0; j < Init.works_num; ++j ) {
                        Init.timeTable_min[o][j][0] = Init.timeTable_cur[o][j][0];
                        Init.timeTable_min[o][j][1] = Init.timeTable_cur[o][j][1];
                    }
                }
                Init.series_min = SA.series.clone();
                min = temp;
            }
        }

        //结果持久化
        Init.timeTablePersistence();
        for ( int i : Init.series_min ) {
            System.out.print(i + " ");
        }System.out.println();

        return min;
    }

    private static void entrance(String str, boolean noWait) throws IOException {
        //计算运行时间
        long startTime = System.currentTimeMillis(); //获取开始时间
        //启动初始化
        Init.init(str);
        //启动模拟退火算法
        System.out.println(startSA(40, noWait));

        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }

    public static void main(String[] args) throws IOException {
        //这里是整体程序的主进口
        //若需满足无等待约束，将下面函数的第二个参数设为true
        entrance(".\\test.txt", false);
    }
}
