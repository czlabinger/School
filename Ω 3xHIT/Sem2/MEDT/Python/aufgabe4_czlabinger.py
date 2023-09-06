class Fahrzeug:

    def __init__(self, geschwindigkeit, marke):
        self.geschwindigkeit = geschwindigkeit
        self.marke = marke

    def fahren(self):
        for i in range(self.geschwindigkeit):
            print("brumm")


class Auto(Fahrzeug):

    def __init__(self, geschwindigkeit, marke, model):
        super().__init__(geschwindigkeit, marke)
        self.model = model


class Fahrrad(Fahrzeug):

    def __init__(self, geschwindigkeit, marke):
        super().__init__(geschwindigkeit, marke)

    def fahren(self):
        for i in range(self.geschwindigkeit):
            print("ding")


def main():
    fahreug = Fahrzeug(10, "Marke1")
    fahreug.fahren()

    auto = Auto(20, "Marke2", "Model1")
    auto.fahren()

    fahrrad = Fahrrad(2, "Marke3")
    fahrrad.fahren()


if __name__ == "__main__":
    main()
