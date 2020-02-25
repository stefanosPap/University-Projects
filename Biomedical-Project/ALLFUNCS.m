classdef ALLFUNCS    
   methods(Static)
     %function for creating the plots (data has renamed to data1,data2 etc after imported to the workspace by GUI)
     function initial_plot(data1,data2,data3,data4,data5,data6,data7,data8)
        %create time vector
        t=1:10000;
        %plot every set of data points (first 10000 each)
        figure(1);
        plot(t,data1(1:10000));
        figure(2);
        plot(t,data2(1:10000));
        figure(3);
        plot(t,data3(1:10000));
        figure(4);
        plot(t,data4(1:10000));
        figure(5);
        plot(t,data5(1:10000));
        figure(6);
        plot(t,data6(1:10000));
        figure(7);
        plot(t,data7(1:10000));
        figure(8);
        plot(t,data8(1:10000));
        
     end
     %function for the second plot which also returns the vector with the k's values that minimize the difference between real spike's number and calculated spike's number   
     function m=second_plot(data1,spikeNum1,data2,spikeNum2,data3,spikeNum3,data4,spikeNum4,data5,spikeNum5,data6,spikeNum6,data7,spikeNum7,data8,spikeNum8)
        figure(9);
        min_k(1)=ALLFUNCS.plot_k_c(data1,spikeNum1);
        figure(10);
        min_k(2)=ALLFUNCS.plot_k_c(data2,spikeNum2);
        figure(11);
        min_k(3)=ALLFUNCS.plot_k_c(data3,spikeNum3);
        figure(12);
        min_k(4)=ALLFUNCS.plot_k_c(data4,spikeNum4);
        figure(13);
        min_k(5)=ALLFUNCS.plot_k_c(data5,spikeNum5);
        figure(14);
        min_k(6)=ALLFUNCS.plot_k_c(data6,spikeNum6);
        figure(15);
        min_k(7)=ALLFUNCS.plot_k_c(data7,spikeNum7);
        figure(16);
        min_k(8)=ALLFUNCS.plot_k_c(data8,spikeNum8);
        m=min_k;
     end
     function discrete_plot(k,sn)
            figure(17);
            hold on 
            xlabel('sn');
            ylabel('k');
            stem(k,sn);
            hold off
     end
     %function for calculating sn1,sn2 etc
     function [sn1,sn2,sn3,sn4,sn5,sn6,sn7,sn8]=calc(data1,data2,data3,data4,data5,data6,data7,data8)
        sn1=median(abs(data1))/0.6745;
        sn2=median(abs(data2))/0.6745;
        sn3=median(abs(data3))/0.6745;
        sn4=median(abs(data4))/0.6745;
        sn5=median(abs(data5))/0.6745;
        sn6=median(abs(data6))/0.6745;
        sn7=median(abs(data7))/0.6745;
        sn8=median(abs(data8))/0.6745;
     end 
     function my_plot = plot_k_c(data,spikeNum)
        i=0;
        kapa = zeros(1,380);
        count = zeros(1,380);
        %iterate until 3.8 because after that value the limit becomes bigger and we will probably lose spikes   
        sn=median(abs(data))/0.6745;
        for k=0:0.01:3.8  
            i=i+1;
            kapa(i)=k;
            T=kapa(i)*sn;
            count(i)=ALLFUNCS.counter(data,T);
        end
        %calculate kapa that minimizes the difference
        %realSpikes-calculatedSpikes
        min=abs(count(1)-spikeNum);
        min_k=kapa(1);
        for i=1:380
            if(abs(count(i)-spikeNum)<min)
                min=abs(count(i)-spikeNum);
                min_k=kapa(i);
            end
        end
        plot(kapa,count);
        my_plot=min_k; %return kapa
     end
     function count = counter(data,T)
        counter=0;
        i=1;
        while(i<1440000)
            i=i+1;
            if data(i)>T && data(i-1)<T
                i = i + 20;
               counter=counter+1;
            end
        end  
        count=counter;
      end
       
     function [peaks,spikeTimes] = find(data,T)
        [peaks,spikeTimes]=findpeaks(data,'MinPeakHeight',T);
     end
     function [spikeTimesEst9,spikeTimesEst10,spikeTimesEst11,spikeTimesEst12]=spikes_find(f,data9,data10,data11,data12)
          sn9=median(abs(data9))/0.6745;
          a=sn9;
          k9=subs(f);
          T9=double(k9*sn9);
          [peaks9,spikeTimesEst9]=ALLFUNCS.find(data9,T9);
          count9=ALLFUNCS.counter(data9,T9);
          
          sn10=median(abs(data10))/0.6745;
          a=sn10;
          k10=subs(f);
          T10=double(k10*sn10);
          [peaks10,spikeTimesEst10]=ALLFUNCS.find(data10,T10);
          count10=ALLFUNCS.counter(data10,T10);
          
          sn11=median(abs(data11))/0.6745;
          a=sn11;
          k11=subs(f);
          T11=double(k11*sn11);
          [peaks11,spikeTimesEst11]=ALLFUNCS.find(data11,T11);
          count11=ALLFUNCS.counter(data11,T11);
          
          sn12=median(abs(data12))/0.6745;
          a=sn12;
          k12=subs(f);
          T12=double(k12*sn12);
          [peaks12,spikeTimesEst12]=ALLFUNCS.find(data12,T12);
          count12=ALLFUNCS.counter(data12,T12);
     end
     function [fitresult, gof] = createFit4(k, sn)
%CREATEFIT(SN,K)
%  Create a fit.
%
%  Data for 'untitled fit 1' fit:
%      X Input : sn
%      Y Output: k
%  Output:
%      fitresult : a fit object representing the fit.
%      gof : structure with goodness-of fit info.
%
%  See also FIT, CFIT, SFIT.

%  Auto-generated by MATLAB on 14-Nov-2018 19:28:30


%% Fit: 'untitled fit 1'.
[xData, yData] = prepareCurveData( sn, k );

% Set up fittype and options.
ft = fittype( 'poly4' );
opts = fitoptions( 'Method', 'LinearLeastSquares' );
opts.Robust = 'Bisquare';

% Fit model to data.
[fitresult, gof] = fit( xData, yData, ft, opts );

% Plot fit with data.
figure( 'Name', 'untitled fit 1' );
h = plot( fitresult, xData, yData );
legend( h, 'k vs. sn', 'untitled fit 1', 'Location', 'NorthEast' );
% Label axes
xlabel sn
ylabel k
grid on


     end
     function spikesEst=peaks_plot(spikeTimesEst,data,f,w)
        syms a;
        sn=median(abs(data))/0.6745;
        a=sn;
        k=subs(f);
        T=double(k*sn);
        se=size(spikeTimesEst);
        for i=1:se(2)
            v=1;
            %+32 till -32 in order to take the whole spike 
            for j=spikeTimesEst(i)-32:spikeTimesEst(i)+32
                peaksArray(i,v)=data(j);
                peaksArray_index(i,v)=j;
                v=v+1;
            end
        end
        for k=1:se(2)
            % find the first peak (positive or negative) in the spike
            [q,loc]=findpeaks(abs(peaksArray(k,1:34)),'MinPeakHeight',T);
             s=size(peaksArray);
             if (s(2)~=1)
                for i=1:34
                    if q(1)==peaksArray(k,i) || q(1)==-peaksArray(k,i)
                        loc(1)=peaksArray_index(k,i);
                    end
                end
                %take the range -32 +32 from the first local maximum or
                %minimum
                r=1;
                for j=loc(1)-32:loc(1)+32
                    peaksArray(k,r)=data(j);
                    r=r+1;
                end
             end
        end 
        %plot the spikes aligned 
        t=1:65;
        figure(w);
        for i=1:se(2)
            hold on
            plot(t,peaksArray(i,:))
        end
        spikesEst=peaksArray;
     end
     %function for finding the difference between the real number of spikes
     %and the estimated one 
     function diff=diff(spikeTimes,spikeTimesEst)
         s1=length(spikeTimesEst);
         s2=length(spikeTimes);
         diff=s1-s2;
     end
     %function for matching the real spike's times with the estimated times 
     function [match,spikesEst]=match(data,spikeTimes,num,f,spikesEst)
        syms a;
        sn=median(abs(data))/0.6745;
        a=sn;
        k=subs(f);
        T=double(k*sn);
        %calculate spikes with the built in function findpeaks 
        [peaks,peaks_time]=findpeaks(data,'MinPeakHeight',T);
        %find for every real spike its analog spike
        for i=1:length(spikeTimes)
            %calculate the minimum distance between every spike 
            min = abs(peaks_time(i) - spikeTimes(i));
            min_index = i;
            for j=i:length(peaks_time)
                if abs(peaks_time(j)-spikeTimes(i)) < min
                   min = abs(peaks_time(j)-spikeTimes(i));
                   min_index = j;
                end
            end
            %if the spike that found is different than the spike at the
            %same index we are setting it to inf in order to swift after
            %the end of iterations at the end of the vector
            %
            if i ~= min_index
                peaks_time(i) = inf;
                spikesEst(i,:) = inf;
            end
            peaks_time([i min_index]) = peaks_time([min_index i]) ;
            %apply the same methodology in order to obtain the right 
            %spikes in the spikesEst array too
            spikesEst([i min_index],:) = spikesEst([min_index i],:);
        end
        %put the real and the estimated spikes in a table in order to plot
        %them and compare the correspondence 
        s=length(spikeTimes);
        c=zeros(2,s);
        c(1,:)=spikeTimes(1:s);
        c(2,:)=peaks_time(1:s);
        spikesEst = spikesEst(1:s,:);%throw inf values at the end
        %plot them
        figure(num);
        hold on
        t=1:s;
        plot(t,c(1,:));
        plot(t,c(2,:));  
        %return the table with the matches 
        match=c;
     end
     %calculate first characteristic (peak to peak value)
     function first_char=first_char(spikesEst)
        s = size(spikesEst);
        temp = zeros(1,s(1));
        for i=1:s(1)
            temp(i) = peak2peak(spikesEst(i,:));
        end
        first_char = temp;
     end
     %calculate second characteristic (zero crossing number)
     function second_char=second_char(spikesEst)
         zcd = dsp.ZeroCrossingDetector;
         s = size(spikesEst);
         temp = zeros(1,s(1));
         for i=1:s(1)
            temp(i) = step(zcd,spikesEst(i,:)');
         end
         second_char = temp;
     end
     %calculate third characteristic 
     function third_char =third_char(spikesEst)
        s = size(spikesEst);
        for i=1:s(1)
            temp(i) = max(abs(spikesEst(i,:)));
        end
        third_char = temp;
     end
     %calculate fourth characteristic
     function fourth_char =fourth_char(spikesEst)
        s = size(spikesEst);
        for i=1:s(1)
            temp(i) = min(abs(spikesEst(i,:)));
        end
        fourth_char = temp;
     end
     %calculate fifth characteristic
     function fifth_char =fifth_char(spikesEst)
        s = size(spikesEst);
        for i=1:s(1)
            temp(i) = norm(spikesEst(i,:));
        end
        fifth_char = temp;
     end
     %calculate sixth characteristic
     function sixth_char =sixth_char(spikesEst)
        s = size(spikesEst);
        for i=1:s(1)
            temp(i) = mean(spikesEst(i,:));
        end
        sixth_char = temp;
     end
     %calculate seventh characteristic
     function seventh_char =seventh_char(spikesEst)
        s = size(spikesEst);
        for i=1:s(1)
            temp(i) = skewness(spikesEst(i,:));
        end
        seventh_char = temp;
     end
     %calculate eighth characteristic
     function eighth_char =eighth_char(spikesEst)
        s = size(spikesEst);
        for i=1:s(1)
            temp(i) = kurtosis(spikesEst(i,:));
        end
        eighth_char = temp;
     end
     %calculate nineth characteristic
     function nineth_char =nineth_char(spikesEst)
        s = size(spikesEst);
        for i=1:s(1)
            temp(i) = var(spikesEst(i,:));
        end
        nineth_char = temp;
     end
     %calculate tenth characteristic
     function tenth_char =tenth_char(spikesEst)
        s = size(spikesEst);
        for i=1:s(1)
            min = abs(spikesEst(i,1)-spikesEst(i,2));
            for j = 1:64
                if abs(spikesEst(i,j)-spikesEst(i,j+1))
                    min = abs(spikesEst(i,j)-spikesEst(i,j+1));
                    temp(i) = min;
                end
            end
        end
        tenth_char = temp;
     end
     %plot two characteristics
     function char_plot(first_char,second_char,spikeClass,fig)
        
         for i=1:length(spikeClass)
            if spikeClass(i)==3
                three(1,i)=first_char(i);
                three(2,i)=second_char(i);
            elseif spikeClass(i)==2
                two(1,i)=first_char(i);
                two(2,i)=second_char(i);
            elseif spikeClass(i)==1
                one(1,i)=first_char(i);
                one(2,i)=second_char(i);      
            end
         end
       
         figure(fig);
         hold on 
         
         scatter(three(1,:),three(2,:),'r');
         scatter(two(1,:),two(2,:),'g');
         scatter(one(1,:),one(2,:),'b');
         
     end
     function Acc = MyClassify(Data,group)
            group=categorical(group);
            idx=randperm(size(Data,1),floor(size(Data,1)*0.7));
            train=Data(idx,:);
            trGroup=group(idx);
            test=Data; test(idx,:)=[];
            teGroup=group; teGroup(idx)=[];
            class = classify(test,train,trGroup);
            Acc=(sum(class==teGroup)/numel(teGroup))*100;
     end
     function [Data,group]=classification(first_char,second_char,spikeClass,spikesEst)
         Data(:,1) = first_char(:)';
         Data(:,2) = second_char(:)';
         Data(:,3) = ALLFUNCS.third_char(spikesEst)';
         Data(:,4) = ALLFUNCS.fourth_char(spikesEst)';
         Data(:,5) = ALLFUNCS.fifth_char(spikesEst)';
         Data(:,6) = ALLFUNCS.sixth_char(spikesEst)';
         Data(:,7) = ALLFUNCS.seventh_char(spikesEst)';
         Data(:,8) = ALLFUNCS.eighth_char(spikesEst)';
         Data(:,9) = ALLFUNCS.nineth_char(spikesEst)';
         Data(:,10) = ALLFUNCS.tenth_char(spikesEst)';
         for i=1:length(spikeClass)
            if spikeClass(i) == 1
                group(i) = 1;
            elseif spikeClass(i) == 2
                group(i) = 2;
            elseif spikeClass(i) == 3
                group(i) = 3;
            end
         end
         group = group';
     end
   end
end

