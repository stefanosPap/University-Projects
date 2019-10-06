function [xk1, xk2] = step3( x )
% apply w identity and finish the proof with
% y = [ xk1+xk2; xk1-xk2 ];
w = @(n,e) exp(-2*pi*1i.*e/n);
n = length(x);
k = (0:n/2-1)';
   
   ybe = zeros(n/2,1);
   ybo = zeros(n/2,1);
   
   
 
   for j = 0:n/2-1
        ybe(j + 1) = sum(w(n,j*2*k) .* x(2*k +1)); 
        ybo(j + 1) = sum(w(n,j*(2*k)) .* x(2*k+1+1));
   end
   ybo = w(n,(0:n/2-1)') .* ybo; 
    xk1 = ybe;
    xk2 = ybo; 
    
end
