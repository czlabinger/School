class Bruch():
    def __init__(self, number1, number2):
        self.number1 = number1
        self.number2 = number2

    def __add__(self, b2):
        top = self.number1 * b2.number2 + b2.number1 * self.number2
        bottom = self.number2 * b2.number2
        return "(" + top.__str__() + "/" + bottom.__str__() + ")"

    def __sub__(self, b2):
        top = self.number1 * b2.number2 - b2.number1 * self.number2
        bottom = self.number2 * b2.number2
        return "(" + top.__str__() + "/" + bottom.__str__() + ")"

    def __neg__(self):
        self.number1 = self.number1
        return "(" + self.number1.__str__() + "/" + self.number2.__str__() + ")"


def main():
    b1 = Bruch(1, 5)
    b2 = Bruch(3, 2)
    print(b1 + b2)
    print(b1 - b2)
    print(-b1)


if __name__ == "__main__":
    main()
