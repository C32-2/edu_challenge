from flask import Flask

app = Flask(__name__)

@app.route('/')
def say_hello():
    return "Hello, world!"

if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=5000)