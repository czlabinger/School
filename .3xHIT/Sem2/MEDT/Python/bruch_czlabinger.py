class Bruch():
    def __init__(self, number1, number2):
        self.number1 = number1
        self.number2 = number2

    # +
    def __add__(self, b2):
        top = self.number1 * b2.number2 + b2.number1 * self.number2
        bottom = self.number2 * b2.number2
        return "(" + top.__str__() + "/" + bottom.__str__() + ")"

    # -
    def __sub__(self, b2):
        top = self.number1 * b2.number2 - b2.number1 * self.number2
        bottom = self.number2 * b2.number2
        return "(" + top.__str__() + "/" + bottom.__str__() + ")"

    # *-1
    def __neg__(self):
        self.number1 = self.number1 * -1
        return "("+self.number1.__str__()+"/"+self.number2.__str__()+")"

    # *
    def __mul__(self, b2):
        top = self.number1 * b2.number1
        bottom = self.number2 * b2.number2
        return "(" + top.__str__() + "/" + bottom.__str__() + ")"

    # Invert
    def __invert__(self):
        top = self.number1
        self.number1 = self.number2
        self.number2 = top
        return "("+self.number1.__str__()+"/"+self.number2.__str__()+")"

    # /
    def __truediv__(self, b2):
        ~b2
        return self * b2

    # <
    def __lt__(self, b2):
        top1 = self.number1 * b2.number2
        top2 = b2.number1 * self.number2

        return top1 < top2

    # <=
    def __le__(self, b2):
        top1 = self.number1 * b2.number2
        top2 = b2.number1 * self.number2

        return top1 <= top2

    # >
    def __gt__(self, b2):
        top1 = self.number1 * b2.number2
        top2 = b2.number1 * self.number2

        return top1 > top2

    # >=
    def __ge__(self, b2):
        top1 = self.number1 * b2.number2
        top2 = b2.number1 * self.number2

        return top1 >= top2

    # ==
    def __eq__(self, b2):
        top1 = self.number1 * b2.number2
        top2 = b2.number1 * self.number2

        return top1 == top2

    # !=
    def __ne__(self, b2):
        top1 = self.number1 * b2.number2
        top2 = b2.number1 * self.number2

        return top1 != top2


def main():
    b1 = Bruch(1, 5)
    b2 = Bruch(3, 2)
    print(b1 + b2)
    print(b1 - b2)
    print(-b1)
    print(b1 * b2)
    print(~b1)
    print(b1 / b2)
    print(b2 < b1)
    print(b2 <= b1)
    print(b1 > b2)
    print(b1 >= b2)
    print(b1 == b2)
    print(b1 != b2)


if __name__ == "__main__":
    main()
