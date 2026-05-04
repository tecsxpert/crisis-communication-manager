from flask import Blueprint, request, jsonify
import os
from datetime import datetime
from services.groq_client import call_groq

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