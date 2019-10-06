clear
x = [1 0 0 1 1 1 0 1 1 1 0 1 1 0 1 1]';
n = length(x);
[xk1,xk2] = step3( x );
y = [xk1 + xk2; xk1 - xk2];
a = fft(x);
dif = a-y;
fprintf('split input even odd : %e\n', norm(dif))