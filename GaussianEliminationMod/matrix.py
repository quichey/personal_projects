import doctest

def extended_gcd(x, y):
    """ Returns a three element tuple (d, a, b) where d = gcd(x,y) and d = ax + by

    >>> extended_gcd(16, 10)
    (2, 2, -3)
    """

    assert type(x) == int and type(y) == int, 'Arguments must be integers'
    assert x >= y, 'First argument must be greater than or equal to second argument'
    assert x >= 0 and y >= 0, 'Aguments cannot be negative'

    if y == 0:
        return x, 1, 0
    else:
        d, a, b = extended_gcd(y, x % y)
        return d, b, a - b*(x // y)

def inv_mod(x, y):
    """ Return the multiplicative inverse of x mod y

    >>> inv_mod(2, 3)
    2
    """
    assert x < y, 'First argument must be smaller than second argument'

    d, a, b = extended_gcd(y, x)

    assert d == 1, 'Multiplicative does not exist'

    while b < 0:
        b += y

    while b > y:
        b -= y

    return b

class Matrix():

    def __init__(self, rows1, mod):
        self.rows = [] + rows1
        self.columns = len(rows1[0])
        self.mod = mod
        self.n = len(self.rows)

    def __repr__(self):
        """
        >>> a = Matrix([ [1, 2], [3, 4] ], 7)
        >>> a
        [1, 2]
        [3, 4]
        """
        string = ''

        last_index = len(self.rows) - 1

        for i in range(last_index):
            string += str(self.rows[i]) + '\n'

        string += str(self.rows[last_index])

        return string

    def swap(self, i, j):
        """
        Swaps row_i with row_j

        >>> b = Matrix([ [1, 2], [3, 4], [5, 6] ], 7)
        >>> b.swap(0, 1)
        >>> b
        [3, 4]
        [1, 2]
        [5, 6]
        """
        self.rows[i], self.rows[j] = self.rows[j], self.rows[i]

    def multiply(self, i, scalar):
        """
        Multiply the ith row by scalar

        >>> a = Matrix( [ [1, 2], [3, 4] ], 7 )
        >>> a.multiply(0, 2)
        >>> a
        [2, 4]
        [3, 4]
        """
        for j in range(self.columns):
            self.rows[i][j] = self.rows[i][j] * scalar
        self.reduce1(i)

    def combine(self, i, j, scalar):
        """
        Replace the R_i with R_i + scalar*R_j

        >>> a = Matrix( [ [1, 2], [3, 4] ], 7 )
        >>> a.combine(0, 1, 2)
        >>> a
        [0, 3]
        [3, 4]
        """
        for k in range(self.columns):
            self.rows[i][k] = self.rows[i][k] + scalar*self.rows[j][k]
        self.reduce1(i)

    def reduce1(self, i):
        """
        For every element x in Row_i, replace it with x mod self.mod

        >>> a = Matrix( [ [10, 8] ], 7 )
        >>> a.reduce1(0)
        >>> a
        [3, 1]
        """
        for j in range(self.columns):
            self.rows[i][j] = self.rows[i][j] % self.mod

    def make_not_0(self, i):
        """
        Make sure element in ith row, ith column is not 0

        >>> a = Matrix( [ [0, 2], [0, 3], [1, 2] ], 7)
        >>> a.make_not_0(0)
        >>> a
        [1, 2]
        [0, 3]
        [0, 2]
        """
        if self.rows[i][i] == 0:
            next_step = False
            index = i + 1
            while index < self.n and next_step == False:
                next_step = self.rows[index][i] != 0
                if next_step:
                    self.swap(i, index)
                    return None
                index += 1
            return 'Not invertible'

    def make_1(self, i):
        """
        >>> a = Matrix( [ [2, 3] ], 3)
        >>> a.make_1(0)
        >>> a
        [1, 0]
        """
        scalar = inv_mod(self.rows[i][i], self.mod)
        self.multiply(i, scalar)

    def make_rest_0(self, i):
        """
        >>> a = Matrix( [ [1, 2], [3, 4], [5, 6] ], 7 )
        >>> a.make_rest_0(0)
        >>> a
        [1, 2]
        [0, 5]
        [0, 3]
        """
        for index in range(i+1, self.n):
            scalar = -self.rows[index][i]
            self.combine(index, i, scalar)

    def make_rest_0_up(self, i):
        """
        >>> a = Matrix( [ [1, 0, 1], [0, 1, 2], [0, 0, 1] ], 7 )
        >>> a.make_rest_0_up(2)
        >>> a
        [1, 0, 0]
        [0, 1, 0]
        [0, 0, 1]
        """
        for index in range(0, i)[::-1]:
            scalar = -self.rows[index][i]
            self.combine(index, i, scalar)

    def rref(self):
        """
        >>> a = Matrix( [ [1, 2], [3, 4] ], 5 )
        >>> a.rref()
        >>> a
        [1, 0]
        [0, 1]
        """
        n_minus = self.n - 1
        for row in range(n_minus):
            self.make_not_0(row)
            self.make_1(row)
            self.make_rest_0(row)
        if self.rows[n_minus][n_minus] == 0:
            return 'Row of zeros'
        else:
            self.make_1(n_minus)
        for row in range(1, self.n)[::-1]:
            self.make_rest_0_up(row)
