from timeit import timeit

def fib_recursive(n):
    if n in {0, 1}:
        return n
    
    return fib_recursive(n - 1) + fib_recursive(n - 2)

def fib_iterative(n):
    if not isinstance(n, int) or n < 0:
        raise ValueError("Positive integer number expected")

    if n in {0, 1}:
        return n

    previous, current = 0, 1
    for _ in range(2, n + 1):
        previous, current = current, previous + current
    return current

def fib_generator():
    a, b = 0, 1
    while True:
        yield a
        a, b = b, a + b

if __name__ == '__main__':
    def run_recursive(start, end):
        result = []
        for i in range(start, end + 1):
            result.append(fib_recursive(i))
        return result

    def run_iterative(start, end):
        result = []
        for i in range(start, end + 1):
            result.append(fib_iterative(i))
        return result

    def run_generator(start, end):
        numbers = fib_generator()
        # skip the first few numbers
        for _ in range(start):
            next(numbers)

        # take the numbers we want
        result = []
        for _ in range(start, end + 1):
            result.append(next(numbers))
        return result

    # expected output: [5, 8, 13, 21, 34, 55]
    print(run_recursive(5, 10))
    print(run_iterative(5, 10))
    print(run_generator(5, 10))

    # timeit.timeit('<code>', number=<iterations>, globals=locals())
    # runs <code> the given number of time. The code is given as a string,
    # so it does not have access to the functions defined in this file implicitly.
    # This is why globals=locals() is given: it gives access to all variables in this file.
    # Source: https://stackoverflow.com/a/1593034/371191

    print("Generator...")
    print(timeit('run_generator(11, 20)', number=1000, globals=locals()))
    print("Iterative...")
    print(timeit('run_iterative(11, 20)', number=1000, globals=locals()))
    print("Recursive...")
    print(timeit('run_recursive(11, 20)', number=1000, globals=locals()))

