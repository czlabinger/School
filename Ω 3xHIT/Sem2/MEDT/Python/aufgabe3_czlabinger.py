def main():

    # Errors
    print(factorial("a"))
    print(factorialRek("a"))
    print(multiply("a", "a"))

    # Working
    print(factorial(10))
    print(factorialRek(10))
    print(multiply(10, 10))


def multiply(x, y):
    if not (isinstance(x, int) and isinstance(y, int)):
        return 0

    return x * y


def factorial(n):

    if not isinstance(n, int):
        return 0

    factorial = 1

    if n == 0:
        return factorial
    else:
        for i in range(1, n + 1):
            factorial = multiply(factorial, i)
        return factorial


def factorialRek(n):

    if not isinstance(n, int):
        return 0

    return 1 if (n == 1 or n == 0) else n * factorialRek(n - 1)


if __name__ == "__main__":
    main()
