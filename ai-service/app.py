import time
from dotenv import load_dotenv

load_dotenv()

start_time = time.time()
response_times = []
from flask import Flask
from routes.ai_routes import ai_bp
from services.model_loader import load_model

def create_app():
    app = Flask(__name__)

    # Load model ONCE at startup
    load_model()

    return app
app = Flask(__name__)

app.register_blueprint(ai_bp)

@app.route("/health", methods=["GET", "POST"])
def health():
    return {"status": "ok"}

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)

@app.after_request
def add_headers(response):
    response.headers["Content-Type"] = "application/json"
    return response


@app.after_request
def add_security_headers(response):
    response.headers['X-Content-Type-Options'] = 'nosniff'
    response.headers['X-Frame-Options'] = 'DENY'
    response.headers['Content-Security-Policy'] = "default-src 'self'"
    return response  
