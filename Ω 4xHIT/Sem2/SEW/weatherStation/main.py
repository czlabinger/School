from random import normalvariate

class Reading(object):

    def __init__(self, temp: float, pres: float):
        self.temp = temp
        self.pres = pres

class Sensor(object):
   
    def get_reading(self) -> Reading:
        return Reading(normalvariate(20, 15), normalvariate(1113, 150))


class Weatherstation(object):

    def __init__(self, sensor: Sensor):
        self.sensor = sensor
        self.readings = []

    def record_reading(self):
        self.readings.clear()
        for _ in range(0, 10):
            self.readings.append(self.sensor.get_reading())

    def avg_temp(self) -> float:
        if not self.readings:
            raise ValueError
        avg = 0;
        for i in range(0, self.readings.__len__()):
            avg += self.readings[i].temp

        return avg/10

    def avg_pres(self) -> float:
        if not self.readings:
            raise ValueError
        avg = 0;
        for i in range(0, self.readings.__len__()):
            avg += self.readings[i].pres

        return avg/10


if __name__ == "__main__":
    s = Sensor()
    print(f"Reading temp: {s.get_reading().temp}, Reading pres: {s.get_reading().pres}")

    w = Weatherstation(s)
    #print(w.avg_temp()) # ValueError

    w.record_reading()
    print(f"Avg. temp: {w.avg_temp()}, Avg. pres: {w.avg_pres()}")


