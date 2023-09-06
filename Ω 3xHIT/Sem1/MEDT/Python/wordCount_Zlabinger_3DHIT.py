def main():

    d = {}
    userInput = ""

    while not userInput == "exit":
        userInput = input("Type a word: ")

        if userInput in d:
            d[userInput] += 1
        else:
            d[userInput] = 1

    print(max(d, key=d.get))


if __name__ == "__main__":
    main()
