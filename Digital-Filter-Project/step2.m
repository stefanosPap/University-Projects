function [ybe, ybo, yte, yto ,yb,yt] = step2( x )
  w = @(n,e) exp(-2*pi*1i.*e/n);
   

   n = length(x);
   k = (0:n/2-1)';
   
   ybe = zeros(n/2,1);
   ybo = zeros(n/2,1);
   yte = zeros(n/2,1);
   yto = zeros(n/2,1);
   
 
   for j = 0:n/2-1
        yte(j + 1) = sum(w(n,j*2*k) .* x(2*k +1)); 
        yto(j + 1) = sum(w(n,j*(2*k+1)) .* x(2*k+1 +1));
   end
   for j = n/2:n-1
        ybe(j -n/2 + 1) = sum(w(n,j*2*k) .* x(2*k +1)); 
        ybo(j -n/2 + 1) = sum(w(n,j*(2*k+1)) .* x(2*k+1 +1));
   end
   yb = ybe + ybo;
   yt = yte + yto;
end