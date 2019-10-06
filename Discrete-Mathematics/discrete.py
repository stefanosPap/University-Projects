import itertools
import numpy as np
import math
import time


"""
function for terminating while loop
"""
def check(myarray):
    count = 0
    for i in range(len(myarray)):
        if any(myarray[i,:]) == False:
             count=count+1
    return count
"""
function for updating the variable num which containing the permutations
"""
def compute(myarray,k,num,temp):
    found = 0
    for i in range(len(myarray)):
        if any(temp - myarray[i,0:len(myarray[0])-k]) == False:
            found = 1
            num = np.append(num,myarray[i,len(myarray[0])-k:len(myarray[0])])
            myarray[i,:] = 0

            myarray[0,:] = 0
            return num,found
    return num,found
"""
function for verification
"""
def correctness(backup,num):
    count = 0
    for j in range(len(backup)):
            for i in range(len(num)-len(backup[0])+1):
                a=backup[j,:]
                b=num[i:i+len(backup[0])]
                if any(a - b) == False:
                    count = count + 1
                    backup[j,:] = 0
                    break

    if count == len(backup) and len(num) == sum:
        print ("Least array:",num)
        print ("Success")
    else:
        print("Fail")
    return count

"""
---------------------start of the programm --------------------------------
"""
start = time.time()

a=input('Give me number:')

#initialization of array
elements = np.zeros(a)

for i in range(a):
    elements[i] = i+1

array=list(itertools.permutations(elements))
myarray = 'global'
myarray = np.asarray(array)
backup = np.asarray(array)
print ('Factorial of input is:',math.factorial(a))
print ('Length of array with permutations is:',len(array))

#sum is the minimum length of the desired number
sum = 0
n=a
for i in range(n):
    sum = sum + math.factorial(n)
    n = n-1
print ("minimum digits:",sum)

num = myarray[0,:]
temp = num[1:len(num)]

k=1
found = 0
#calculate the next number to append to num
num,found = compute(myarray,k,num,temp)
while True:
    if found == 0:
        k=k+1
        temp = num[len(num)-a+k:len(num)]
        num,found=compute(myarray,k,num,temp)
    else:
        k=1
        temp = num[len(num)-a+k:len(num)]
        num,found=compute(myarray,k,num,temp)
    if check(myarray) == len(myarray):
        break
if a==1:
    num = [1]
print ("Do you want to verify your result?\n")
print("1. Yes")
print("2. No and exit")
ans = input()
if ans == 1:
    count = correctness(backup,num)
end = time.time()
print ("Program's execution time:",end - start)
