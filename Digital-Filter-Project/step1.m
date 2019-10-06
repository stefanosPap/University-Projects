function [yt, yb] = step1( x , w)
% split output to top and bottom
   
%------------------------sol-----------------------
  w = @(n,e) exp(-2*pi*1i.*e/n);
  n = length(x);
  k = (0:n-1)';
  y = zeros(n,1);
  yb = zeros(n/2,1);
  yt = zeros(n/2,1);
 
   for m=0:n/2-1
           y(m+1) = sum(w(n,m*k).*x(k+1));
   end
   
   for m=n/2:n-1
           y(m+1)=sum(w(n,m*k).*x(k+1));
   end
   
  yt = y(1:n/2);
  yb = y(n/2+1:n); %---------------------------------------------------
end




