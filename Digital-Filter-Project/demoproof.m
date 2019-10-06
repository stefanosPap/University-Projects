
%CREATING SAMPLE
nLen = 16;
fsam = 0:2*pi/nLen:2*pi;
tsamp = 0:1/nLen:1;
sample = zeros(nLen,1);
for i=1:1:nLen
  sample(i) = sin(fsam(i));
end
sample = sample(:);

%INITIALIZING FT VECTORS
SAMP1 = zeros(nLen,1);
SAMP1 = fft(sample);
SAMP1 = SAMP1(:);

[yt, yb] = step1(sample);

test1 = sum(abs(SAMP1-[yt;yb]));

[ybe, ybo, yte, yto] = step2( sample);

test2t = sum(abs(yt -(yte+yto))) ;
test2b = sum(abs(yb -(ybe+ybo))) ;


[xk1, xk2] = step3( sample);

yf = [ xk1+xk2; xk1-xk2 ];
test3 = sum(abs(SAMP1-yf));

test32 = norm(yf-SAMP1);

disp([test1 test2t test2b test3 test32])
