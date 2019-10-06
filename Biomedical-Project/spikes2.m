 %---------second exercise------

 syms a;
 f=x.p1*a^4+x.p2*a^3+x.p3*a^2+x.p4*a+x.p5;
 %----------------2.1----------------
 [spikeTimesEst9,spikeTimesEst10,spikeTimesEst11,spikeTimesEst12]=ALLFUNCS.spikes_find(f,data9,data10,data11,data12);
  
 %----------------2.2----------------
 spikesEst9=ALLFUNCS.peaks_plot(spikeTimesEst9,data9,f,1);
 spikesEst10=ALLFUNCS.peaks_plot(spikeTimesEst10,data10,f,2);
 spikesEst11=ALLFUNCS.peaks_plot(spikeTimesEst11,data11,f,3);
 spikesEst12=ALLFUNCS.peaks_plot(spikeTimesEst12,data12,f,4);
 
%----------------2.3----------------
 diff9=ALLFUNCS.diff(spikeTimes9,spikeTimesEst9);
 diff10=ALLFUNCS.diff(spikeTimes10,spikeTimesEst10);
 diff11=ALLFUNCS.diff(spikeTimes11,spikeTimesEst11);
 diff12=ALLFUNCS.diff(spikeTimes12,spikeTimesEst12);
  
 [match9,spikesEst9]=ALLFUNCS.match(data9,spikeTimes9,5,f,spikesEst9);
 [match10,spikesEst10]=ALLFUNCS.match(data10,spikeTimes10,6,f,spikesEst10);
 [match11,spikesEst11]=ALLFUNCS.match(data11,spikeTimes11,7,f,spikesEst11);
 [match12,spikesEst12]=ALLFUNCS.match(data12,spikeTimes12,8,f,spikesEst12);
 
 %----------------2.4----------------
 first_char9=ALLFUNCS.first_char(spikesEst9);
 first_char10=ALLFUNCS.first_char(spikesEst10);
 first_char11=ALLFUNCS.first_char(spikesEst11);
 first_char12=ALLFUNCS.first_char(spikesEst12);
  
 second_char9=ALLFUNCS.second_char(spikesEst9);
 second_char10=ALLFUNCS.second_char(spikesEst10);
 second_char11=ALLFUNCS.second_char(spikesEst11);
 second_char12=ALLFUNCS.second_char(spikesEst12);

 ALLFUNCS.char_plot(first_char9,second_char9,spikeClass9,9);
 ALLFUNCS.char_plot(first_char10,second_char10,spikeClass10,10);
 ALLFUNCS.char_plot(first_char11,second_char11,spikeClass11,11);
 ALLFUNCS.char_plot(first_char12,second_char12,spikeClass12,12);
 
 %----------------2.5----------------
 [Data9,group9] = ALLFUNCS.classification(first_char9,second_char9,spikeClass9,spikesEst9);
 Acc9 = ALLFUNCS.MyClassify(Data9,group9);
 [Data10,group10] = ALLFUNCS.classification(first_char10,second_char10,spikeClass10,spikesEst10);
 Acc10 = ALLFUNCS.MyClassify(Data10,group10);
 [Data11,group11] = ALLFUNCS.classification(first_char11,second_char11,spikeClass11,spikesEst11);
 Acc11 = ALLFUNCS.MyClassify(Data11,group11);
 [Data12,group12] = ALLFUNCS.classification(first_char12,second_char12,spikeClass12,spikesEst12);
 Acc12 = ALLFUNCS.MyClassify(Data12,group12);
 
 