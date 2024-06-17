import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

from sklearn.datasets import load_iris
from sklearn.preprocessing import MinMaxScaler
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import accuracy_score, confusion_matrix, ConfusionMatrixDisplay, recall_score
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis, QuadraticDiscriminantAnalysis

iris = load_iris()
X = iris.data
y = iris.target

"""
1.1
"""

df = pd.DataFrame(X, columns=iris.feature_names)

# Normalisierung der ersten vier Spalten
scaler = MinMaxScaler(feature_range=(0, 1))
normalized_df = pd.DataFrame(scaler.fit_transform(df), columns=df.columns)

normalized_features = normalized_df.values
target_vector = y

print("Normalized features:")
print(normalized_features)
print(target_vector)
print("\n\n")


"""
1.2
"""

X_train, X_test, y_train, y_test = train_test_split(normalized_features, target_vector, test_size=0.2, random_state=24) # 42 = 1.0

"""
1.3
"""

knn = KNeighborsClassifier(n_neighbors=10)


knn.fit(X_train, y_train)

y_pred = knn.predict(X_test)

accuracy = accuracy_score(y_test, y_pred)
print(f"Genauigkeit: {accuracy} \n")

"""
1.4
"""
target_names = ['Setosa', 'Versicolor', 'Viginica']
cm = confusion_matrix(y_test, y_pred)

disp = ConfusionMatrixDisplay(confusion_matrix=cm, display_labels=target_names)
disp.plot(cmap=plt.cm.Blues)
plt.title("Konfusionsmatrix")
plt.xlabel("Predicted Label")
plt.ylabel("True Label")
plt.xticks(ticks=np.arange(len(target_names)), labels=target_names, rotation=45)
plt.yticks(ticks=np.arange(len(knn.classes_)), labels=target_names)
plt.show()

# Berechnung der Sensitivit채t
sensitivity = recall_score(y_test, y_pred, average='macro')
print(f"Sensitivit채t: {sensitivity}")

# Berechnung der Spezifizit채t
specificities = []
for i in range(cm.shape[0]):
    true_negatives = cm[i, i]
    false_positives = sum(cm[i]) - true_negatives
    specificity = true_negatives / (true_negatives + false_positives)
    specificities.append(specificity)

print(f"Spezifitäten für jede Klasse:\n{np.array(specificities)}")


"""
1.5 (EK)
"""
"""
for i in range(0, 3):
    results = []
    for neighbors in range(5, 16):
        knn = KNeighborsClassifier(n_neighbors=neighbors)

        # Modell trainieren und vorhersagen treffen
        knn.fit(X_train, y_train)
        y_pred = knn.predict(X_test)

        # Sensitivität und Spezifität berechnen
        report = classification_report(y_test, y_pred, output_dict=True)
        sensitivity = report[str(i)]['recall']
        specificity = report[str(i)]['precision']
        results.append([neighbors, sensitivity, specificity])

    # Ergebnisse in eine Pandas-Tabelle schreiben
    df_results = pd.DataFrame(results, columns=['Nachbarn', 'Sensitivität', 'Spezifität'])
    print("Knn 5-15")
    print(df_results)

    #1.6 (EK)

    plt.figure(figsize=(10, 6))
    plt.plot(df_results['Nachbarn'], df_results['Sensitivität'], label='Sensitivität')
    plt.plot(df_results['Nachbarn'], df_results['Spezifität'], label='Spezifität')

    plt.xlabel('Anzahl der Nachbarn')
    plt.ylabel('Leistungsmessungen')
    plt.title('Optimale Auswahl der nächsten Nachbarn')
    plt.legend()
    plt.grid(True)
    plt.title(target_names[i])
    plt.show()
"""

"""
2.0
"""
read = pd.read_csv('olives.csv')
data = read.drop(columns=['eicosenoic', 'Region'])

"""
2.1
"""

X = data.drop(columns='Area')
y = data['Area']

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Durchführen der linearen Diskriminanzanalyse
lda = LinearDiscriminantAnalysis()
lda.fit(X_train, y_train)

y_pred = lda.predict(X_test)

"""
2.2
"""

# Berechne die Genauigkeit
accuracy = accuracy_score(y_test, y_pred)
print(f'Accuracy LDA: {accuracy:%}')

# Erstelle die Konfusionsmatrix
cm = confusion_matrix(y_test, y_pred, labels=lda.classes_)

# Visualisiere die Konfusionsmatrix mit sinnvollen Labels und Achsenbeschriftungen
disp = ConfusionMatrixDisplay(confusion_matrix=cm, display_labels=lda.classes_)
disp.plot(cmap=plt.cm.Blues)
plt.title('Confusion Matrix of LDA Classifier')
plt.xlabel('Predicted label')
plt.ylabel('True label')
plt.xticks(rotation=45)
plt.show()

"""
2.3 (EK)
"""

# TODO: EK

"""
2.4
"""
qda = QuadraticDiscriminantAnalysis()
qda.fit(X_train, y_train)

predictions = qda.predict(X_test)

accuracy = accuracy_score(y_test, predictions)
print(f"Genauigkeit der QDA: {accuracy:%}")

"""
2.5
"""
cm = confusion_matrix(y_test, predictions, labels=qda.classes_)

disp = ConfusionMatrixDisplay(confusion_matrix=cm, display_labels=qda.classes_)
disp.plot(cmap=plt.cm.Blues)
plt.title('Confusion Matrix of QDA Classifier')
plt.xlabel('Predicted label')
plt.ylabel('True label')
plt.xticks(rotation=45)
plt.show()

"""
2.6 (EK)
"""

#TODO: EK

