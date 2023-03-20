from abc import ABC, abstractmethod

class Pokemon(ABC):
   
    pokedex = 2
           
    def __init__(self, name, hp):
        self.__name = name
        self.__hp = hp

    @abstractmethod
    def attack(self, p):
        pass

    @staticmethod
    def printPokedex():
        print(Pokemon.pokedex)

class Bisasam(Pokemon):
    def __init__(self, pokedex : int, name : str, hp : int):
        Bisasam.pokedex = pokedex
        self.__name = name
        self.__hp = hp
    
    def attack(self, p : Pokemon):
        p.__hp -= 3

    @property
    def getHP(self):
        return self.__hp

    @hp.setter
    def setHP(self, hp):
        self.__hp = hp

class Glumanda(Pokemon):
    def __init__(self, pokedex, name, hp):
        Glumanda.pokedex = pokedex
        self.__name = name
        self.__hp = hp

    def attack(self, p : Pokemon):
        p.__hp -= 3
    
class Shiggy(Pokemon):
    def __init__(self, pokedex, name, hp):
        Shiggy.pokedex = pokedex
        self.__name = name
        self.__hp = hp

    def attack(self, p : Pokemon):
        p.__hp -= 3

def main():
    bisasam = Bisasam(1, "Bisasam", 15)
    bisasam.printPokedex()

    glumanda = Glumanda(3, "Glumanda", 15)
    glumanda.attack(bisasam)

    print(bisasam.__hp)


if __name__ == "__main__":
    main()
