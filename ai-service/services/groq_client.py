import requests
import os
import json
import time
from dotenv import load_dotenv

load_dotenv()

GROQ_API_KEY = os.getenv("GROQ_API_KEY")

def call_groq(prompt):
    url = "https://api.groq.com/openai/v1/chat/completions"

    headers = {
        "Authorization": f"Bearer {GROQ_API_KEY}",
        "Content-Type": "application/json"
    }

    data = {
        "model": "llama3-70b-8192",
        "messages": [
            {"role": "user", "content": prompt}
        ],
        "temperature": 0.3
    }

    # 🔁 RETRY LOGIC (3 times)
    for attempt in range(3):
        try:
            response = requests.post(url, headers=headers, json=data, timeout=10)

            if response.status_code != 200:
                raise Exception("Bad response")

            result = response.json()
            output_text = result["choices"][0]["message"]["content"]

            # 🧹 CLEAN MARKDOWN
            if output_text.startswith("```"):
                output_text = output_text.strip("```json").strip("```")

            return json.loads(output_text)

        except Exception as e:
            print(f"Attempt {attempt+1} failed:", e)
            time.sleep(2)

    # 🚨 FALLBACK (IMPORTANT FOR PROJECT)
    return {
        "title": "AI unavailable",
        "summary": "Unable to process request at the moment",
        "severity": "MEDIUM",
        "key_points": ["Service temporarily down", "Retry later", "Fallback response"],
        "is_fallback": True
    }