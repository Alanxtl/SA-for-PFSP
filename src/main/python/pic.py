import numpy as np
import matplotlib.pyplot as plt

if __name__ == "__main__":
    box_list = []

    path = r'./timeTable.txt'

    f = open(path)
    string = f.readline()
    series = string.split(sep=' ')
    timeTable = np.loadtxt(path, dtype=int, skiprows=1)
    print(timeTable)
    color = ['c', 'g', 'b', 'm', 'r', 'y']

    for i in range(0, len(timeTable)):
        for j in range(0, len(timeTable[i]) - 1):
            if j % 2 != 0:
                continue
            plt.barh(i, width=timeTable[i][j + 1] - timeTable[i][j], left=timeTable[i][j],
                     color=plt.get_cmap('rainbow')(j * 15), edgecolor="black")
            if timeTable[i][j + 1] - timeTable[i][j] > 0:
                plt.text(x=timeTable[i][j],
                         y=i, s='work' + series[int(j / 2)], rotation=45)

    yLabels = []
    for i in range(0, len(timeTable)):
        yLabels.append("machine" + str(i))
    plt.yticks(range(len(timeTable)), yLabels)

    plt.show()
