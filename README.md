🤖 AI Chat Service (Spring Boot)

A Spring Boot middleware service that connects a user to an LLM (via OpenRouter or LM Studio).

It supports personalities, validation, and structured error handling.

⚙️ Configuration
🔑 Option 1: OpenRouter (recommended)

Create an API key at OpenRouter
Set environment variable:

OPENROUTER_API_KEY=your_api_key_here

Use these properties:

llm.provider=openrouter
llm.base-url=https://openrouter.ai/api
llm.api-key=${OPENROUTER_API_KEY}

## 🧪 How to test

 Frontend (easiest)
Open:
http://localhost:8080/

→ Send messages directly in the UI.

---

💻 Option 2: LM Studio (no API key needed)
Start LM Studio local server (default port: 1234)

Use these properties:
llm.provider=lmstudio
llm.base-url=http://localhost:1234
llm.api-key=



 Option 3: Swagger (API testing)
Open:
http://localhost:8080/swagger-ui/index.html

→ Test the API manually using JSON requests.




