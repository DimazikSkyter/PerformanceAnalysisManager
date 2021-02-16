import numbers


def sum2(a, b):
    scale = 0
    if not isinstance(a, numbers.Number):
        scale = 1
    if not isinstance(b, numbers.Number):
        scale = scale + 2
    if (scale == 3):
        return "all arguments are not a numbers"
    if (scale == 2):
        return "2nd argument is not a number"
    if (scale == 1):
        return "1st argument is not a number"
    return a + b


x1 = sum2("1", sum2(1, 3))
print(x1)

L = [1, 4, 7, 4, 2, 65, 66]

for i in range(len(L) - 1):
    if abs(L[i] - L[i + 1]) == 1:
        index = i
        break
print(index)


def counter(T):
    smb = map(lambda x: (len(set(x.lower())), len(x)), T)
    max_smb = max(
        smb)  # reduce(lambda a, b: a if a[0] > b[0] else (b if a[0] < b[0] else (a if a[1] > b[1] else b)), smb)
    return max_smb[1]


x1 = ('AaaAAAaaa', 'aaaaaab', "aCB")
print(counter(x1))

with open("file.txt", "r") as myfile:
    source = myfile.readline().rstrip('\n')
    destination = myfile.readline().rstrip('\n')
    index = int(myfile.readline())
fp = open(source)
for i, line in enumerate(fp):
    if i == index:
        with open(destination, "a") as myfile:
            myfile.write(line.lower().rstrip('\n'))
        break
fp.close()

x2 = ('A', 'AAAA')
print(counter(x2))

x3 = ('AAAaaa', 'ab')
print(counter(x3))
# x1, x2, x3 = [int(input()) for i in (1,2,3)]

# print("#".join(map(str, [sum(map(int, input().split())) for _ in range(2)])))
# print(*set(input().split(sep='&')))
# [print(*([l[i] for i in (1, 2, -2)])) for l in [input().split()]]
# print("-$-".join(input().split()[::-1]))
# x = input().split()
# print(len(x), len(list(filter(lambda x: x == "one", x))))

# print(sum(map(int, input().split())))

B1 = "asdaszxczxc1231hk"
B2 = "".join(set(B1[1::2]))
print(B2)
print(B1[-2])
import ast

C1 = "['mix', 'extra', '', 'x-files', 'xyz', 'xapple', 'apple']"
C2 = ast.literal_eval(C1)
print(C2)

# inpt = input()
#
# if inpt == "int":
#     (lambda x, y: print(x + y) if x or y else print("Empty Ints"))(int(input()), int(input()))
# elif inpt == "str":
#     (lambda x: print(x) if x else print("Empty String"))(input())
# elif inpt == "list":
#     (lambda x: print(x[-1]) if x else print("Empty List"))(input().split())
# else:
#     print("Unknown type")

x = 10
for i in range(1,x):
    print(i)

# print(*list(filter(lambda y: y %2 == 0, map(lambda x: x ** 2, range(x)))), sep="\n")
#
#
# zz = "exaggeration *romotion run into admit exactly *idelity *erspective treat check certain"
# print(*list(filter(lambda x: not x.startswith("*"), zz.split())), sep = "\n")
#
# z = 2589
#
# print((list(i for i in range(2, z) if z % i == 0))[0])
#
#
#
# x = -5
#
# print(*map(lambda x: x ** 3, range(1, abs(x))), sep = "\n")
#
#
# print(*map(lambda x: x ** 3, range(1, abs(int(input())))), sep = "\n")
print("________")

def dfactorial(n):
    if n <= 1:
        return 1
    return n * dfactorial(n - 2)



print(dfactorial(6))


def Kfactorial(n, k = 1):
    if n <= 1:
        return 1
    if n <= k:
        return n
    return n * Kfactorial(n-k, k)

print(Kfactorial(20, 10))

def translate(x, b = 2):
    if x < b:
        return x
    return int(str(translate(int(x / b), b)) + str(x % b))

print(translate(19, 2))

def factorial(n):
    return 1 if n < 2 else n * factorial(n-1)

def sf(n):
    return 1 if n < 2 else factorial(n) * sf(n - 1)


print(factorial(0), sf(0))


L = [1, 2, '42', '3', '4', '5', 6, 13]

def convert(L):
    return [int(i) for i in L]

def maxId(L):
    X = convert(L)
    return [i for i, j in enumerate(X) if j == max(X)][0]

print(maxId(L))

maxId = lambda L: L.index(max(L, key=int))

# print("OK" if statistics.mean([int(x) for x in open(sheet, 'r', encoding="utf-8").readlines() if ("(автомат)" in x or "(экзамен)" in x)]) == float(open(mean, 'r').read()) else "ERROR")

path = "../java/ru/performancetool/analysis/Analysis.java"
# print(open(path, 'r', encoding="utf-8").read()) if os.path.isfile(path) else print("ERROR:\nЭто каталог, а не файл") if os.path.exists(path) else print("ERROR:\nФайл не существует")

file_name  = '../../../test2.txt'
event = "git fetch origin"
import os.path
import re
if os.path.exists(file_name ):
    with open(file_name, 'r+', encoding='utf-8') as f:
        lines = f.readlines()
        filtered_lines = list(filter(lambda a: "event" in a, lines))
        f.seek(0)
        if filtered_lines:
            event_str = filtered_lines[0]
            print(event_str)
            num = int(re.match("event \\d+", event_str).group(0)[5:])  + 1
            print(num)
            f.write("event " + str(num) + " - '"  + event + "'\n"+ "".join(lines) )
        else:
            f.write("event 1 - '" + event + "'")
else:
    with open(file_name , 'w', encoding='utf-8') as f:
        f.write("event 1 - '" + event + "'")


import math
def f(x):
    return 2*math.atan(x)
lim = 100000000
print(round(f(lim), 3))

from math import exp
def def_e(x):
    delta = 0.0000001
    return round((exp(x + delta) - exp(x))/delta, 3)
number_list = [2, 3, 4, 5, 10, 25, 100]
for number in number_list:
    p = def_e(num)
    v = round(exp(num),3)
    print(p, v)
