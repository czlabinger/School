from flask import Flask, render_template, request, escape


app = Flask(__name__)

@app.route('/login', methods=['GET', 'POST'])
def login():
    username = "Stoffi05"
    password = "test1234"
    if request.method == 'POST':
        usernameInput = request.form['username']
        passwordInput = request.form['password']

        if usernameInput == username and passwordInput == password:
            return f"Hello {escape(username)}, the login was successful!"
        else:
            return render_template("index.html", error="Wrong E-Mail or Password")
    else:
        return render_template("index.html")