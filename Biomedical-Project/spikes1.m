clear;
load('Workspace_load.mat');
%---------first exercise-------

%--------------1.1-------------
ALLFUNCS.initial_plot(data1,data2,data3,data4,data5,data6,data7,data8);

%--------------1.2-------------
k=ALLFUNCS.second_plot(data1,spikeNum1,data2,spikeNum2,data3,spikeNum3,data4,spikeNum4,data5,spikeNum5,data6,spikeNum6,data7,spikeNum7,data8,spikeNum8);

%--------------1.3-------------
%calculate sn
[sn1,sn2,sn3,sn4,sn5,sn6,sn7,sn8]=ALLFUNCS.calc(data1,data2,data3,data4,data5,data6,data7,data8);
sn=[sn1,sn2,sn3,sn4,sn5,sn6,sn7,sn8];
%discrete plot for k-sn
ALLFUNCS.discrete_plot(k,sn);
%create the rule between k and sn 
[x,y]=ALLFUNCS.createFit4(k,sn);
