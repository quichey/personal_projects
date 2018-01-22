def fact(n):
    """
    >>> fact(4)
    24
    >>> fact(0)
    1
    >>> fact(1)
    1
    >>> fact(7)
    5040
    """
    if n == 0:
        return 1
    total = 1
    index = 1
    while index <= n:
        total *= index
        index += 1
    return total

def choose(n, k):
    """
    >>> choose(5, 0)
    1
    >>> choose(5,5)
    1
    >>> choose(9, 4)
    126
    """
    return fact(n)//( fact(n-k) * fact(k) )

def perm(n, k):
    """
    >>> perm(12, 3)
    1320
    """
    return fact(n)//fact(n-k)

def sum(start, end, func):
    """
    >>> sum(1, 10, lambda x: x)
    55
    """
    total = 0
    index = start
    while index <= end:
        total += func(index)
        index += 1
    return total

def divide(a,b):
        """
        >>> divide(4,2)
        (2, 1)
        >>> divide(4, 18)
        (2, 9)
        """
        gcd = 1
        ind = 1
        while ind <= min(a,b):
                if (a % ind == 0) and (b % ind == 0):
                        gcd = ind
                ind += 1
        return (a // gcd, b // gcd)

my_answer = sum(0, 5, lambda x: choose(10, x)*choose(10-x, x))

their_answer = sum(0, 5, lambda x: choose(10, x)*choose(10-x, 10-2*x))

def lin_reg_values():
    a_lst = [2, 4, 6, 8]
    b_lst = []
    for a in a_lst:
        b_lst.append(divide(17*a, 15))
    return b_lst

def lin_reg_difference(x, y):
    return x[0]/x[1] - y

def squared_error(x, y):
    return (lin_reg_difference(x, y))**2

def squared_error_values():
    b_prime_lst = lin_reg_values()
    b_lst = [3, 6, 7, 8]
    values = []
    for i in range(4):
        values.append(squared_error(b_prime_lst[i], b_lst[i]))
    return values

def quadratic_formula(a, b, c):
    neg_b = -b
    determinant = b**2 - 4*a*c
    sqrt_det = determinant**(1/2)
    two_a = 2*a
    plus_answer = (neg_b + sqrt_det)/two_a
    neg_answer = (neg_b - sqrt_det)/two_a
    return [plus_answer, neg_answer]
