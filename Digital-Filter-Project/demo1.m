clear
x = [0 1 0 1 0 1 0 0]';
[yt,yb] = step1(x);
y = [yt ; yb];
a = fft(x);
dif = a-y;
fprintf('split output top bottom : %e\n', norm(dif))