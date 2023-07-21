package MIN;

public class NoWaitCalcTime {

    public static void init(int[] series) {
        int time = 0;//初始化动态规划边界条件
        for ( int i = 0; i < Init.machines_num; ++i ) {
            Init.machines[i].setStartTime(time);
            time += Init.chart[series[0]][i];
            Init.machines[i].setEndTime(time);
        }
        for ( int i = 0; i < Init.works_num; ++i ) {
            Init.works[i].setStartTime(0);
            Init.works[i].setEndTime(0);
        }
//        Init.works[series[0]].setEndTime(Init.chart[series[0]][0]);
    }

    public static long calcTime(int[] series) {
        init(series);
        //进行动态规划
        for (int k : series) {
            if ( SA.findWork(k) > 0 ) {
                Init.works[k].setStartTime(Init.machines[Init.machines_num - 1].getEndTime());
                Init.works[k].setEndTime(Init.works[k].getStartTime());
            }
            for (int j = 0; j < Init.machines_num; ++j) {
                process(k, Init.machines[j]);
            }
            if ( SA.findWork(k) > 0 ) {
                reduceTime(SA.findWork(k));
            }
        }

        Init.time = Init.timeTable_cur[Init.machines_num - 1][Init.works_num - 1][1];
//        if (true) {System.out.println(Init.time);}
//        for ( int i = 0; i < series.length; ++i ) {
//            System.out.print(series[i] + " ");
//        } System.out.println();
        return Init.time;
    }


    public static void process(int work, Machine machine) {
        //计算开始和结束时间
        machine.setStartTime(Init.works[work].getEndTime());

        Init.works[work].setStartTime(machine.getStartTime());

        machine.setEndTime(machine.getStartTime() + Init.chart[work]
                [Integer.parseInt(machine.name)]);

        Init.works[work].setEndTime(machine.getEndTime());
        //结果固化
        Init.timeTable_cur[Integer.parseInt(machine.name)]
                [SA.findWork(work)][0] = machine.getStartTime();
        Init.timeTable_cur[Integer.parseInt(machine.name)]
                [SA.findWork(work)][1] = machine.getEndTime();
    }

    private static void reduceTime(int work) {
        //反向减少等待时间
        long min = 1000000000;
        for ( int i = 0; i < Init.machines_num; ++i ) {
            long temp = Init.timeTable_cur[i][work][0] - Init.timeTable_cur[i][work - 1][1];
            min = Math.min(min, temp);
        }

        for ( int i = 0; i < Init.machines_num; ++i ) {
            Init.timeTable_cur[i][work][0] -= min;
            Init.timeTable_cur[i][work][1] -= min;

            Init.machines[i].setStartTime(Init.timeTable_cur[i][work][0]);
            Init.works[work].setStartTime(Init.machines[i].getStartTime());

            Init.machines[i].setEndTime(Init.timeTable_cur[i][work][1]);
            Init.works[work].setEndTime(Init.machines[i].getEndTime());
        }
    }
}
