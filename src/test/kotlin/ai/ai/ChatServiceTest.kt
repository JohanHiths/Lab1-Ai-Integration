package ai.ai


import ai.ai.client.ChatClient
import ai.ai.service.ChatService

import ai.ai.service.TestService

import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.test.context.ActiveProfiles

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.springframework.boot.test.context.SpringBootTest

import kotlin.test.assertEquals


@ExtendWith(MockitoExtension::class)
@ActiveProfiles("test")
class ChatServiceTest() {

    @Mock
    lateinit var chatClient: ChatClient


    @InjectMocks
    lateinit var chatService: ChatService





    @Test
    fun `should return response from client`() {
        whenever(chatClient.callLLM(any(), any()))
            .thenReturn("Test response")

        val result = chatService.sendMessage("hello", "coder")

        assertEquals("Test response", result)
        verify(chatClient).callLLM(any(), any())
    }


    @Test
    fun `should return coder system prompt`() {

        val result = chatService.buildSystemPrompt("coder")

        assertEquals("You are a helpful coding assistant.", result)
    }


}