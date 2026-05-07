function addMessage(text, type) {
    const chat = document.getElementById("chat");

    const div = document.createElement("div");
    div.classList.add("bubble", type);
    div.innerText = text;

    chat.appendChild(div);
    chat.scrollTop = chat.scrollHeight;

}

async function sendMessage() {
    const messageInput = document.getElementById("message");
    const personalityInput = document.getElementById("personality");

    const message = (messageInput.value || "").trim();
    const personality = personalityInput.value;

    if (!message) return;

    addMessage(message, "user");
    messageInput.value = "";

    try {
        const res = await fetch("/api/v1/chat", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                personality,
                message,
                sessionId: "user-123"
            })
        });

        const data = await res.json().catch(() => ({}));

        if (!res.ok) {
            addMessage(data.message || `HTTP ${res.status}`, "ai");
            return;
        }

        addMessage(data.reply || data.message || "(empty response)", "ai");
    } catch (e) {

        addMessage("Kunde inte nå servern.", "ai");
    }


}

document.addEventListener("DOMContentLoaded", () => {
    const messageInput = document.getElementById("message");

    messageInput.addEventListener("keydown", (e) => {
        if (e.key === "Enter") {
            e.preventDefault();
            sendMessage();
        }
    });
});