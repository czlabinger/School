import tensorflow as tf
import numpy as np
from tensorflow._api.v2.random import set_seed
from tensorflow.keras.preprocessing import image
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Flatten
from sklearn.model_selection import train_test_split
import kagglehub
import os

path = kagglehub.dataset_download("xainano/handwrittenmathsymbols")
print("Path to dataset files:", path)

path_open = os.path.join(path, "extracted_images", "(")
path_close = os.path.join(path, "extracted_images", ")")

images_open = []
images_close = []

for file in os.listdir(path_open):
    img_path = os.path.join(path_open, file)
    if not os.path.isfile(img_path):
        continue
    img = image.load_img(img_path, target_size=(150, 150))
    img_array = image.img_to_array(img)
    img_array = np.expand_dims(img_array, axis=0)
    images_open.append(img_array)
    if len(images_open) >= 30:
        break

print("Loaded all ( images")

for file in os.listdir(path_close):
    img_path = os.path.join(path_close, file)
    if not os.path.isfile(img_path):
        continue
    img = image.load_img(img_path, target_size=(150, 150))
    img_array = image.img_to_array(img)
    img_array = np.expand_dims(img_array, axis=0)
    images_close.append(img_array)
    if len(images_close) >= 30:
        break

print("Loaded all ) images")

images_open = np.vstack(images_open)
images_close = np.vstack(images_close)

labels_open = np.ones(len(images_open)) # ( = 1
labels_close = np.zeros(len(images_close)) # ) = 0

X = np.vstack((images_open, images_close))
y = np.concatenate((labels_open, labels_close))

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.33)

X_train = X_train / 255.0
X_test = X_test / 255.0

def train_sequentially(X_train, y_train, epochs=50):

    model = Sequential([
        Flatten(input_shape=(150, 150, 3)),
        Dense(64, activation='relu'), # ReLU = Rectified Linear Unit
        Dense(1, activation='sigmoid')
    ])
    model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])

    for i in range(len(X_train)):
        print(f"{i}/{len(X_train)}")
        model.fit(X_train[i:i+1], y_train[i:i+1], epochs=epochs)

    return model



def train_randomly(X_train, y_train, epochs=50):

    model = Sequential([
        Flatten(input_shape=(150, 150, 3)),
        Dense(64, activation='relu'),
        Dense(1, activation='sigmoid')
    ])
    model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])

    indices = np.arange(len(X_train))
    for epoch in range(epochs):
        np.random.shuffle(indices)
        for i in indices:
            print(f"{epoch}/{epochs}")
            model.fit(X_train[i:i+1], y_train[i:i+1], epochs=1)

    return model


def build_model_with_hidden_layers(hidden_layers):

    model = Sequential([Flatten(input_shape=(150, 150, 3))])
    for _ in range(hidden_layers):
        model.add(Dense(64, activation='relu'))
    model.add(Dense(1, activation='sigmoid'))
    model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])

    model.fit(X_train, y_train, epochs=500, verbose=0)
    return model


def evaluate_model(model, X_test, y_test):
    loss, accuracy = model.evaluate(X_test, y_test, verbose=0)
    print(f"Loss: {loss}, Accuracy: {accuracy}")
    return (loss, accuracy)

model_seq = train_sequentially(X_train, y_train)
model_ran = train_randomly(X_train, y_train)

model1 = build_model_with_hidden_layers(1)
model3 = build_model_with_hidden_layers(3)
model10 = build_model_with_hidden_layers(10)


print("Seq: ")
evaluate_model(model_seq, X_test, y_test)
print("Random: ")
evaluate_model(model_ran, X_test, y_test)

print("Hidden layers: ")
evaluate_model(model1, X_test, y_test)
evaluate_model(model3, X_test, y_test)
evaluate_model(model10, X_test, y_test)
