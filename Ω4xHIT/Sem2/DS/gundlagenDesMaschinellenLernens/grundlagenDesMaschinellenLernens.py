import numpy as np
import pandas as pd
from sklearn.linear_model import LinearRegression, LogisticRegression
from sklearn.model_selection import cross_val_score, train_test_split
import matplotlib.pyplot as plt
from sklearn.preprocessing import PolynomialFeatures
from sklearn.metrics import ConfusionMatrixDisplay, confusion_matrix, mean_squared_error
from sklearn.metrics import median_absolute_error
import math



"""
Aufgabe 1
Teil 1
"""
economics = pd.read_csv('economics.csv')
economics = economics.sort_values(by=['unemploy'])

uempmed = economics["uempmed"].values.reshape(-1, 1)
unemploy = economics["unemploy"].values.reshape(-1, 1)

unemploy_train, unemploy_test, uempmed_train, uempmed_test = train_test_split(unemploy, uempmed, test_size=0.2, random_state=42)

model1 = LinearRegression()
model1.fit(unemploy_train, uempmed_train)

model2 = LinearRegression()
model2.fit(uempmed_train, unemploy_train)

print(f"unemploy/uempmed: {model1.score(unemploy_test, uempmed_test)}")
print(f"uempmed/unemploy: {model2.score(uempmed_test, unemploy_test)}")

# Mit steigender Anzahl der Arbeitslosigkeit seitgt auch die durchschnittliche Zeit der Arbeitslosigkeit (uempmed duch enemply)
# Mit steigender Dauer der Arbeitslosigkeit steigt auch die Anzahl der Arbeitslosigkeit (uemply durch uempmed)

"""
Aufgabe 1
Teil 2
"""

########################### unemploy/uempmed ############################
y1_pred = model1.predict(unemploy)

plt.figure(figsize=(8, 6))
plt.scatter(unemploy, uempmed, color='blue', label='Originaldaten')
plt.plot(unemploy, y1_pred, color='red', label='Vorhersagen des Modells')

plt.xlabel('Anzahl der Arbeitslosen (unemploy)')
plt.ylabel('Mittlere Anzahl der Tage in Arbeitslosigkeit (uempmed)')
plt.legend()

plt.show()

########################## uempmed/unemploy ############################
y2_pred = model2.predict(uempmed)

plt.figure(figsize=(8, 6))
plt.scatter(uempmed, unemploy, color='blue', label='Originaldaten')
plt.plot(uempmed, y2_pred, color='red', label='Vorhersagen des Modells')

plt.xlabel('Mittlere Anzahl der Tage in Arbeitslosigkeit (uempmed)')
plt.ylabel('Anzahl der Arbeitslosen (unemploy)')
plt.legend()

plt.show()


"""
Aufgabe 1
Teil 3
"""

uempmed_log = np.log(uempmed)

plt.scatter(unemploy, uempmed_log, color='blue', label='Log angewendet')
plt.xlabel('unemploy')
plt.ylabel('log(uempmed)')
plt.legend()
plt.show()

log = LinearRegression()
log.fit(unemploy, uempmed_log)
log_pred = log.predict(unemploy)


"""
Aufgabe 1
Teil 4
"""

plt.title('Auf log exp anwenden')

plt.scatter(unemploy, uempmed, color='blue', label='Originaldaten')
plt.plot(unemploy, np.exp(log_pred), color='red', label='Vorhersage des Modells')

plt.xlabel('Anzah der Arbeitslosen (unemploy)')
plt.ylabel('Vorhergesagte mittlere Anzahl der Tage in Arbeitslosigkeit (uempmed)')
plt.legend()

plt.show()

"""
Aufgabe 1
Teil 5
"""

poly = PolynomialFeatures(degree=10, include_bias=False)
poly_features = poly.fit_transform(unemploy)
poly_reg_model = LinearRegression()

poly_reg_model.fit(poly_features, uempmed)

poly_pred = poly_reg_model.predict(poly_features)

plt.title('Ploy')

plt.scatter(unemploy, uempmed, color='blue', label='Originaldaten')
plt.plot(unemploy, poly_pred, color='red', label='Vorhersage des Modells')

plt.xlabel('Anzah der Arbeitslosen (unemploy)')
plt.ylabel('Vorhergesagte mittlere Anzahl der Tage in Arbeitslosigkeit (uempmed)')
plt.legend()

plt.show()


"""
Aufgabe 1
Teil 6
"""

# Linear
mse = mean_squared_error(uempmed, y1_pred)
rmse = math.sqrt(mse)
mad = median_absolute_error(uempmed, y1_pred)

print('Linear:\n MSE:' + mse.__str__())
print('RMSE: ' + rmse.__str__()) 
print('MAD: ' + mad.__str__())


# Log
mse = mean_squared_error(uempmed, np.exp(log_pred))
rmse = math.sqrt(mse)
mad = median_absolute_error(uempmed, np.exp(log_pred))

print('Log/Exp:\n MSE:' + mse.__str__())
print('RMSE: ' + rmse.__str__())
print('MAD: ' + mad.__str__())


# Poly
mse = mean_squared_error(uempmed, poly_pred)
rmse = math.sqrt(mse)
mad = median_absolute_error(uempmed, poly_pred)

print('Poly:\n MSE:' + mse.__str__())
print('RMSE: ' + rmse.__str__())
print('MAD: ' + mad.__str__())

"""
Aufgabe 2
"""

print("\nAufgabe 2\n")

"""
Train (In sample)
"""
train = pd.read_csv('Pima.tr.csv')

train['type'] = train['type'].map({'Yes': 1, 'No': 0})

X_train = train.drop(['rownames', 'type'], axis=1)
y_train = train['type']

model = LogisticRegression(max_iter=1000)
model.fit(X_train, y_train)


y_prob = model.predict_proba(X_train)[:,1]
y_pred_train = (y_prob >= 0.3).astype(int)

cm_train = confusion_matrix(y_train, y_pred_train)
print("Confusion Matrix (Train):\n", cm_train)

disp = ConfusionMatrixDisplay(confusion_matrix=cm_train, display_labels=model.classes_)
disp.plot()
plt.show()

TN, FP, FN, TP = cm_train.ravel()

sensitivity = TP / (TP + FN)
specificity = TN / (TN + FP)

print(f"Sensitivität Train: {sensitivity}")
print(f"Spezifizität Train: {specificity}")


"""
Test (Out of sample)
"""
test = pd.read_csv('Pima.te.csv')

test['type'] = test['type'].map({'Yes': 1, 'No': 0})
X_test = test.drop(['rownames', 'type'], axis=1)
y_test = test['type']

y_prob = model.predict_proba(X_test)[:,1]
y_pred_test = (y_prob >= 0.3).astype(int)

cm_test = confusion_matrix(y_test, y_pred_test)
print("Confusion Matrix (Test):\n", cm_test)

disp = ConfusionMatrixDisplay(confusion_matrix=cm_test, display_labels=model.classes_)
disp.plot()
plt.show()

TN, FP, FN, TP = cm_test.ravel()

sensitivity = TP / (TP + FN)
specificity = TN / (TN + FP)

print(f"Sensitivität Test: {sensitivity}")
print(f"Spezifizität Test: {specificity}")


"""
2.2
"""

pima = pd.concat([train, test], axis=0)

scores = cross_val_score(model, pima.drop(['type'], axis=1), pima['type'], cv=10)
print("Cross Validation Scores Test:", scores)

