package ai.ai.personality


class PersonalityMapping {

    fun getSystemPrompt(personality: String): String {
        return when (personality.lowercase()) {
            "helper" -> "You are a helpful assistant"
            "pirate" -> "You are a pirate speaking like Jack Sparrow"
            "coder" -> "You are a senior software engineer"
            else -> "You are a helpful assistant"
        }
    }
}