
##在最新的Python 3版本中，字符串是以Unicode编码的，也就是说，Python的字符串支持多语言
###字符串与编码
print('包含中文的str')
print('hello, world')
print('hello python')

print( '\u4e2d\u6587')
print(ord('A'))
print(chr(25991))
print('ABC'.encode('ascii'))
print(b'\xe4\xb8\xad\xe6\x96\x87'.decode('utf-8'))
print('Age: %s. Gender: %s' % (25, True))
##基本数据类型
##['a','b','c' ] list 列表 类似java中的数组 len()表示列表中的数量 列表的操作 insert/pop/
classmates = ['Michael', 'Bob', 'Tracy']
print(len(classmates))
classmates.append("evan")
print(classmates)
##遍历列表中的元素
for item in classmates:
    print('mate is {0}'.format(item))

##tuple 元组 初始化之后不能修改 只有1个元素的tuple定义时必须加一个逗号,，来消除歧义
t = (1,)
print(t)
##list(range(5)) range(n)表达一个数据范围
print(range(10))
for i in range(10):
    print(i)
#循环 for x in xxx while break continue
n=100
sum=0
while n > 0:
    sum+=n
    n=n-2
print(sum)

### dict and set  dict.get/dict.pop  set.add  set.remove
s = set([1,2,3,4,6,5,7,7])
s.add(10)
print(len(s))
print(s)
s.remove(10)
print(s)
dict={'name':'json','id':10,'score':100}
print(dict)
print(dict['id'])
print(dict['name'])
print(dict.get('name1','student'))
print(dict.get('addr','china beijing'))
print(dict)
#### 数据类型转换 int(x) float(x) str(x) bool(x)  max min
print(max(1,2,3,4))
print(min(1,3,4,0))

### from python file name (moudle) import func
## def name() : pass pass是占位符
def my_abs(x):
    if not isinstance(x, (int, float)):
        raise TypeError('bad operand type')
    if x >= 0:
        return x
    else:
        return -x

#print(my_abs('abc'))
print(my_abs(100))
## 函数可以返回多个值  使用豆号分隔即可 其实就是一个没有用括号的tuple
import math
print(math.pi)
## 函数的参数 *num表示可变参数 **kw 表示关键字参数 参数其实被封装成了dict
def calc(*numbers):
    sum = 0
    for n in numbers:
        sum = sum + n * n
    return sum
def person(name, age, **kw):
    print('name:', name, 'age:', age, 'other:', kw)

print(calc(1,2,3,4))
print(person('Michael', 30))
### *args是可变参数，args接收的是一个tuple；
##  **kw是关键字参数，kw接收的是一个dict。
