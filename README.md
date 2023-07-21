# SA for PFSP
## 中文

 应用模拟退火算法解决置换流水线车间调度问题
 
 本库主要探讨的是置换流水线车间调度问题（PFSP）中，如何使用模拟退火算法寻找最优解，以最小化完成所有作业所需的总时间。本文使用 Java 实现了模拟退火算法，并使用 Python 实现了甘特图的绘制，能够在可接受的时间内给出确定解并且快速生成可视化图表。同时本文对算法中参数的选择也进行了实验测试，增强了实验的可信度和可重复性。本文只是针对一组实验数据进行了测试，没有进行大规模实验来验证算法的鲁棒性和可靠性。为了更加全面地评估算法的性能，需要在不同规模和不同数据类型的实例上进行测试，以检验算法的适用性。

 本库提供了完整的报告论文
 
 结果展示如下：
 
 ![image](https://github.com/102365478/SA-for-PFSP/assets/25652981/5da60c57-cb7f-4832-ac5d-5e47745b0695)

 使用方法：修改Java和python代码中的所有关于路径的代码，按照正确的格式提供测试集，先运行Java的main代码，之后会自动生成调度表，之后运行python代码（需要将调度表的路径正确填入代码）即可自动生成甘特图。

## English
Applying Simulated annealing Algorithm to Solve the Job Shop Scheduling Problem of Displacement Pipeline

This paper mainly discusses how to use Simulated annealing algorithm to find the optimal solution in permutation flow shop scheduling problem (PFSP) to minimize the total time required to complete all jobs. This paper uses Java to implement the Simulated annealing algorithm, and uses Python to implement the drawing of Gantt chart, which can give a definite solution in an acceptable time and quickly generate visual charts. At the same time, this article also conducted experimental tests on the selection of parameters in the algorithm, enhancing the credibility and repeatability of the experiment. This article only tested a set of experimental data and did not conduct large-scale experiments to verify the robustness and reliability of the algorithm. In order to comprehensively evaluate the performance of the algorithm, it is necessary to test its applicability on instances of different scales and data types.

This repository provides complete report papers

The results are shown as follows:

 ![image](https://github.com/102365478/SA-for-PFSP/assets/25652981/5da60c57-cb7f-4832-ac5d-5e47745b0695)

How to use it: Modify all the path related codes in Java and python code, provide test sets in the correct format, run Java main code first, then automatically generate a schedule table, and then run python code (the path of the schedule table needs to be correctly filled in the code) to automatically generate a Gantt chart.
