from flask import Flask
from flask import render_template

app = Flask(__name__)


@app.route("/")
@app.route('/index')
def home():
    users = User()

    return render_template('index.html', title='Welcome', users=users)


class User:

    def __init__(self):
        dict = {
            "Christof": True,
            "Matthias": False
        }
