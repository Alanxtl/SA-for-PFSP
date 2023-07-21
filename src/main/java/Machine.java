package MIN;

import java.util.Objects;

public class Machine {
    //机器类
    String name;
    private long startTime;
    private long endTime;
    int tablePointer = 0;
    public Machine(String name) {
        this.name = name;
    }
    public long getStartTime() {
        return startTime;
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Machine machine = (Machine) o;
        return startTime == machine.startTime && endTime == machine.endTime && tablePointer == machine.tablePointer && Objects.equals(name, machine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startTime, endTime, tablePointer);
    }


}
