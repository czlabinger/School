from abc import ABC, abstractmethod


class Pokemon(ABC):
    pokedex = {}

    def __init__(self, name: str, hp: int):
        self.__name = name
        self.__hp = hp

    @abstractmethod
    def attack(self, p):
        p.__hp -= 3
    
    @staticmethod
    def printPokedex():
        print(Pokemon.pokedex)

    @property
    def name(self):
        return self.__name

    # setter-Methode für name
    @name.setter
    def name(self, n):
        self.__name = n

    @property
    def hp(self):
        return self.__hp

    @hp.setter
    def hp(self, n):
        self.__hp = n


class Bisasam(Pokemon):
    Pokemon.pokedex["Bisasam"] = 0

    def __init__(self, name: str, hp: int):
        super().__init__(name, hp)
        Pokemon.pokedex["Bisasam"] += 1

    def attack(self, p):
        p.hp -= 3


class Glumanda(Pokemon):
    Pokemon.pokedex["Glumanda"] = 0

    def __init__(self, name: str, hp: int):
        super().__init__(name, hp)
        Pokemon.pokedex["Glumanda"] += 1

    def attack(self, p):
        p.hp -= 3


class Schiggy(Pokemon):
    Pokemon.pokedex["Schiggy"] = 0

    def __init__(self, name: str, hp: int):
        super().__init__(name, hp)
        Pokemon.pokedex["Schiggy"] += 1

    def attack(self, p):
        p.hp -= 3


def main():
    bisasam = Bisasam("Bisasam", 15)
    glumanda = Glumanda("Glumanda", 15)
    glumanda.attack(bisasam)
    print(bisasam.hp)
    Pokemon.printPokedex()


if __name__ == "__main__":
    main()
