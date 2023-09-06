import random


def main():
    rand = random.randint(0, 100)

    for i in range(1, 10):
        userInput = int(input("Pick a number between 0 and 100: "))

        if userInput == rand:
            print("bingo")
            break
        elif userInput > rand:
            print("Number is to high")
        else:
            print("Number is to low")


if __name__ == "__main__":
    main()
