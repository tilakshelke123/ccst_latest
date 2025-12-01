
from openai import OpenAI
client = OpenAI(base_url="http://127.0.0.1:1234/v1",
               api_key = "lm-studio")

completion = client.chat.completions.create(
    model= "mistral-7b-instruct-v0.3",
    messages =[
        {"role":"user","content":"hi there !"},
        {"role":"assistant","content":"Nice to meet  you! !"},
        {"role":"user","content":" give me gajar ka halwa recipe for bachlors "}
    ],
 temperature=0.7,
)
print(completion.choices[0].message.content)
