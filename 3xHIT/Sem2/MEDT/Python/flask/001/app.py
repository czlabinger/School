from flask import Flask

app = Flask(__name__)

@app.route("/")
def hello_world():
    out = "Hello,"
    out += " World!"
    return "<p>" + out + "</p>"
