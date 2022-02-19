##一些高级feature
## list的切片
list = (range(100))
print(list[-1])  # 倒数第一个元素
print(list[0:3])
print(list[:10])  # 前10个元素
print(list[-10:])  # 最后10个元素

##迭代
for x, y in [(1, 1), (2, 4), (3, 9)]:
    print(x, y)

##生成全排列
permute = [m + n for m in 'ABC' for n in 'XYZ']
print(permute)

L = ['Hello', 'World', 'IBM', 'Apple']
print([s.lower() for s in L])

from functools import reduce

def add(x, y):
    return x + y

sum = reduce(add, [1, 3, 5, 7, 9])
print(sum)

