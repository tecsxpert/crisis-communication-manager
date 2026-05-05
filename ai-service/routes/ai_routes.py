import time
from flask import Blueprint, request, jsonify
import os
import json
from datetime import datetime
from services.groq_client import call_groq
from utils.metrics import start_time, response_times

ai_bp = Blueprint("ai", __name__)

@ai_bp.route("/describe", methods=["POST"])
def describe():
    data = request.get_json()

    # 🔒 1. INPUT VALIDATION
    if not data or "input" not in data:
        return jsonify({"error": "Missing 'input' field"}), 400

    user_input = data["input"].strip()

    if len(user_input) == 0:
        return jsonify({"error": "Input cannot be empty"}), 400

    # 📄 2. LOAD PROMPT
    prompt_path = os.path.join("prompts", "describe_prompt.txt")

    try:
        with open(prompt_path, "r") as f:
            prompt_template = f.read()
    except Exception:
        return jsonify({"error": "Prompt file not found"}), 500

    prompt = prompt_template.replace("{user_input}", user_input)

    # 🤖 3. CALL GROQ
    ai_response = call_groq(prompt)

    # 🚨 4. HANDLE FAILURE
    if "error" in ai_response:
        return jsonify(ai_response), 500

    # 🕒 5. ADD TIMESTAMP
    ai_response["generated_at"] = datetime.utcnow().isoformat()

    return jsonify(ai_response)

@ai_bp.route("/generate-report", methods=["POST"])
def generate_report():
    data = request.get_json()

    if not data or "input" not in data:
        return jsonify({"error": "Missing input"}), 400

    user_input = data.get("input")

    with open("prompts/report_prompt.txt") as f:
        prompt = f.read().replace("{input}", user_input)

    response = call_groq(prompt)

    try:
        return jsonify(json.loads(response))
    except:
        return jsonify({"error": "Invalid JSON from AI"})


@ai_bp.route("/health", methods=["GET"])
def health():
    uptime = time.time() - start_time

    avg_time = 0
    if response_times:
        avg_time = sum(response_times) / len(response_times)

    return jsonify({
        "status": "UP",
        "model": "groq-llama",
        "avg_response_time_ms": round(avg_time, 2),
        "uptime_seconds": int(uptime)
    })