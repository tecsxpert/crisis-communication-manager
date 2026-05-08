import re

def sanitize_input(text):
    if not text:
        return text

    text = re.sub(r'<.*?>', '', text)

    if "ignore previous instructions" in text.lower():
        raise ValueError("Prompt injection detected")

    return text