package MIN;

import java.io.IOException;

public class CalcTime {
//    public static int[] series;
    public static void init(int[] series) {
//        CalcTime.series = series;
        int time = 0;//初始化动态规划边界数据
        for ( int i = 0; i < Init.machines_num; ++i ) {
            Init.machines[i].setStartTime(time);
            Init.machines[i].setEndTime(0);
            time += Init.chart[series[0]][i];
        }
        time = 0;
        for ( int i = 0; i < Init.works_num; ++i ) {
            Init.works[series[i]].setStartTime(time);
            Init.works[series[i]].setEndTime(0);
            time += Init.chart[series[i]][0];
        }
    }

    public static long calcTime(int[] series) {
        init(series);//进行动态规划
        for (int k : series) {
            for (int j = 0; j < Init.machines_num; ++j) {
                process(Init.works[k], Init.machines[j]);
            }
        }
        if (Init.debug) {System.out.println(Init.machines[Init.machines_num - 1].getEndTime());}
        Init.time = Init.machines[Init.machines_num - 1].getEndTime();
        return Init.time;
    }

    public static void process(Work work, Machine machine) {
        //为machine和work定下开始和结束时间
        machine.setStartTime(Math.max(work.getEndTime(), machine.getEndTime()));

        work.setStartTime(machine.getStartTime());

        machine.setEndTime(machine.getStartTime() + Init.chart[Integer.parseInt(work.name)]
                [Integer.parseInt(machine.name)]);

        work.setEndTime(machine.getEndTime());

        //结果固化
        Init.timeTable_cur[Integer.parseInt(machine.name)]
                [SA.findWork(Integer.parseInt(work.name))][0] = machine.getStartTime();
        Init.timeTable_cur[Integer.parseInt(machine.name)]
                [SA.findWork(Integer.parseInt(work.name))][1] = machine.getEndTime();

        if ( Init.debug ) {
            System.out.println(work.name + " " + work.getStartTime() + " " + work.getEndTime());
            System.out.println(machine.name + " " + machine.getStartTime() + " " + machine.getEndTime());
            System.out.println("-----");
        }
    }

    public static void main(String[] args) throws IOException {
        int[] temp = {0, 1, 2};
        calcTime(temp);
        Init.timeTable_min = Init.timeTable_cur.clone();
        Init.timeTablePersistence();
    }
}
