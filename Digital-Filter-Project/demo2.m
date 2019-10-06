clear
x = [1 0 0 1 1 1 0 1 1 1 0 1 1 0 1 1]';
[ybe, ybo, yte, yto , yt , yb] = step2( x );
y = [yb' yt'];
a = fft(x);
dif = a-y';
fprintf('split input even odd : %e\n', norm(dif))