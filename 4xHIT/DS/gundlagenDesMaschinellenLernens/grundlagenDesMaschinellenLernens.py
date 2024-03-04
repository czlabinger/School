import numpy as np
import pandas as pd
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt


def plot1():
    coef = np.polyfit(x.flatten(), y.flatten(), 1)
    ploy1d_fn = np.poly1d(coef)


    plt.scatter(x_train, y_train, label="Originaldaten")
    plt.plot(x, y, 'o', x, ploy1d_fn(x), '--k')
    plt.show()


def plot2():
    coef = np.polyfit(y.flatten(), x.flatten(), 1)
    ploy1d_fn = np.poly1d(coef)


    plt.scatter(y_train, x_train, label="Originaldaten")
    plt.plot(y, x, 'o', y, ploy1d_fn(y), '--k')
    plt.show()


def plot_exp(x, y):
    transformed_y = np.log(y)

    coef = np.polyfit(x.flatten(), transformed_y.flatten(), 1)
    ploy1d_fn = np.poly1d(coef)


    plt.scatter(x, np.exp(transformed_y), label="Originaldaten")
    plt.plot(x, np.exp(transformed_y), 'o', x, np.exp(ploy1d_fn(x)), '--k')
    plt.show()


def plot_log(x, y):
    transformed_x = np.exp(-x)

    coef = np.polyfit(-np.log(transformed_x).flatten(), y.flatten(),  1)
    ploy1d_fn = np.poly1d(coef)

    plt.scatter(transformed_x, y, label="Transformierte Daten")
    plt.plot(transformed_x, y, 'o', transformed_x, ploy1d_fn(transformed_x), '--k')
    plt.show()


"""
Aufgabe 1
Teil 1
"""
economics = pd.read_csv('economics.csv')

x = economics["uempmed"].values.reshape(-1, 1)
y = economics["unemploy"].values.reshape(-1, 1)

x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=42)

model1 = LinearRegression().fit(x_train, y_train)
model2 = LinearRegression().fit(y_train, x_train)

# Gespiegelt da Achsen vertauscht wurden

"""
Teil 2
"""
plot1()
plot2()
"""
Teil 3
"""

plot_exp(y, x)
plot_log(x, y)

"""
Teil 5
"""

pol_model = np.poly1d(np.polyfit(x.flatten(), y.flatten(), 10))

plt.scatter(x, y, label="original daten")
plt.plot(x, y, 'o', x, pol_model(x), '--k')
plt.show()

"""
Teil 6
"""


