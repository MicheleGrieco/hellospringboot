package com.example.hellospringboot;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SoftwareEngineerServiceTests {

    @Mock
    private SoftwareEngineerRepository softwareEngineerRepository;
    
    @Mock
    private AIService aiService;

    @InjectMocks
    private SoftwareEngineerService softwareEngineerService;
    private SoftwareEngineer testEngineer;
    
    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        testEngineer = new SoftwareEngineer();
        testEngineer.setId(1);
        testEngineer.setName("John Doe");
        testEngineer.setTechStack("Java, Spring Boot, PostgreSQL");
        testEngineer.setLearningPathRecommendation("Learn advanced Spring features");
    }

    @Test
    @DisplayName("Should retrieve a list of software engineers")
    void getAllSoftwareEngineersTest() {
        // Given
        SoftwareEngineer se1 = new SoftwareEngineer();
        se1.setId(1);
        se1.setName("John Doe");
        
        SoftwareEngineer se2 = new SoftwareEngineer();
        se2.setId(2);
        se2.setName("Jane Smith");
        
        List<SoftwareEngineer> expectedEngineers = Arrays.asList(se1, se2);
        when(softwareEngineerRepository.findAll()).thenReturn(expectedEngineers);
        
        // When
        List<SoftwareEngineer> actualEngineers = softwareEngineerService.getAllSoftwareEngineers();
        
        // Then
        assertEquals(expectedEngineers, actualEngineers);
        assertEquals(2, actualEngineers.size());
        verify(softwareEngineerRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return an empty list")
    void getAllSoftwareEngineersTest_EmptyList() {
        // Given
        when(softwareEngineerRepository.findAll()).thenReturn(Arrays.asList());

        // When
        List<SoftwareEngineer> actualEngineers = softwareEngineerService.getAllSoftwareEngineers();

        // Then
        assertTrue(actualEngineers.isEmpty());
        verify(softwareEngineerRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should insert a new software engineer")
    void insertSoftwareEngineerTest() {
        // Given
        SoftwareEngineer se = new SoftwareEngineer();
        se.setName("Alice Johnson");
        se.setTechStack("React, Node.js, MongoDB");

        String expectedAIResponse = "Learn TypeScript and advanced React patterns";
        when(aiService.chat(anyString())).thenReturn(expectedAIResponse);
        
        // When
        softwareEngineerService.insertSoftwareEngineer(se);

        // Then
        ArgumentCaptor<String> promptCaptor = ArgumentCaptor.forClass(String.class);
        verify(aiService, times(1)).chat(promptCaptor.capture());

        String capturedPrompt = promptCaptor.getValue();
        assertTrue(capturedPrompt.contains("React, Node.js, MongoDB"));
        assertTrue(capturedPrompt.contains("Alice Johnson"));
        assertTrue(capturedPrompt.contains("learning path"));

        assertEquals(expectedAIResponse, se.getLearningPathRecommendation());
        verify(softwareEngineerRepository, times(1)).save(se);
    }

    @Test
    @DisplayName("Should handle null tech stack in insert")
    void insertSoftwareEngineerTest_NullTechStack() {
        // Given
        SoftwareEngineer se = new SoftwareEngineer();
        se.setName("Bob Wilson");
        se.setTechStack(null);

        String expectedAIResponse = "Start with programming fundamentals";
        when(aiService.chat(anyString())).thenReturn(expectedAIResponse);

        // When
        softwareEngineerService.insertSoftwareEngineer(se);

        // Then
        ArgumentCaptor<String> promptCaptor = ArgumentCaptor.forClass(String.class);
        verify(aiService, times(1)).chat(promptCaptor.capture());

        String capturedPrompt = promptCaptor.getValue();
        assertTrue(capturedPrompt.contains("null"));
        assertTrue(capturedPrompt.contains("Bob Wilson"));

        assertEquals(expectedAIResponse, se.getLearningPathRecommendation());
        verify(softwareEngineerRepository, times(1)).save(se);

    }

    @Test
    @DisplayName("Should retrieve a software engineer by ID")
    void getSoftwareEngineerByIdTest() {
        // Given
        Integer id = 1;
        when(softwareEngineerRepository.findById(id)).thenReturn(Optional.of(testEngineer));
        SoftwareEngineer result = softwareEngineerService.getSoftwareEngineerById(id);

        // Then
        assertEquals(testEngineer, result);
        assertEquals("John Doe", result.getName());
        verify(softwareEngineerRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should throw exception when software engineer not found by ID")
    void getSoftwareEngineerByIdTest_NotFound() {
        // Given
        Integer id = 999;
        when(softwareEngineerRepository.findById(999)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> {softwareEngineerService.getSoftwareEngineerById(id);}
        );

        assertEquals("Software engineer with 999 not found", exception.getMessage());
        verify(softwareEngineerRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should delete a software engineer by ID")
    void deleteSoftwareEngineerByIdTest() {
        // Given
        Integer id = 1;
        when(softwareEngineerRepository.existsById(id)).thenReturn(true);

        // When
        softwareEngineerService.deleteSoftwareEngineer(id);

        // Then
        verify(softwareEngineerRepository, times(1)).existsById(id);
        verify(softwareEngineerRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Should throw exception when trying to delete non-existing software engineer by ID")
    void deleteSoftwareEngineerById_NotFound() {
        // Given
        Integer id = 999;
        when(softwareEngineerRepository.existsById(id)).thenReturn(false);

        // When & Then
        RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> {softwareEngineerService.deleteSoftwareEngineer(id);}
        );

        assertEquals("Software engineer with 999 not found", exception.getMessage());
        verify(softwareEngineerRepository, times(1)).existsById(id);
        verify(softwareEngineerRepository, never()).deleteById(anyInt());
    }

    @Test
    @DisplayName("Should update a software engineer")
    void updateSoftwareEngineerTest() {
      // Given
      Integer id = 1;
      SoftwareEngineer updateData = new SoftwareEngineer();
      updateData.setName("John Doe Updated");
      updateData.setTechStack("Java, Spring Boot, PostgreSQL, Docker");
      
      when(softwareEngineerRepository.findById(id)).thenReturn(Optional.of(testEngineer));

      // When
      softwareEngineerService.updateSoftwareEngineer(id, updateData);

      // Then
      assertEquals("John Doe Updated", testEngineer.getName());
      assertEquals("Java, Spring Boot, PostgreSQL, Docker", testEngineer.getTechStack());

      verify(softwareEngineerRepository, times(1)).findById(id);
      verify(softwareEngineerRepository,times(1)).save(testEngineer);
    }

    @Test
    @DisplayName("Should throw exception when trying to update non-existing software engineer by ID")
    void updateSoftwareEngineerTest_NotFound() {
        // Given
        Integer id = 999;
        SoftwareEngineer updateData = new SoftwareEngineer();
        updateData.setName("Updated Name");
        updateData.setTechStack("Updated Tech Stack");

        when(softwareEngineerRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> {softwareEngineerService.updateSoftwareEngineer(id, updateData);}
        );

        assertEquals("Software engineer with 999 not found", exception.getMessage());
        verify(softwareEngineerRepository, times(1)).findById(id);
        verify(softwareEngineerRepository, never()).save(any(SoftwareEngineer.class));
    }

    @Test
    @DisplayName("Should update software engineer with null values")
    void updateSoftwareEngineerTest_NullValues () {
        // Given
        Integer id = 1;
        SoftwareEngineer updateData = new SoftwareEngineer();
        updateData.setName(null);
        updateData.setTechStack(null);

        when(softwareEngineerRepository.findById(id)).thenReturn(Optional.of(testEngineer));

        // When
        softwareEngineerService.updateSoftwareEngineer(id, updateData);

        // Then
        assertNull(testEngineer.getName());
        assertNull(testEngineer.getTechStack());

        verify(softwareEngineerRepository, times(1)).findById(id);
        verify(softwareEngineerRepository, times(1)).save(testEngineer);
    }

    @Test
    @DisplayName("Constructor should initialize service correctly")
    void testConstructor() {
        // Given
        SoftwareEngineerRepository repository = mock(SoftwareEngineerRepository.class);
        AIService mockAiService = mock(AIService.class);

        // When
        SoftwareEngineerService service = new SoftwareEngineerService(repository, mockAiService);

        // Then
        assertNotNull(service);
    }

    @Test
    @DisplayName("Should insert software engineer and handle AI service response")
    void insertSoftwareEngineerTest_AIServiceThrowsException() {
        // Given
        SoftwareEngineer se = new SoftwareEngineer();
        se.setName("Test Engineer");
        se.setTechStack("Test Stack");

        when(aiService.chat(anyString())).thenThrow(new RuntimeException("AI service error"));

        // When & Then
        RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> {softwareEngineerService.insertSoftwareEngineer(se);}
        );

        assertEquals("AI service error", exception.getMessage());
        verify(aiService, times(1)).chat(anyString());
        verify(softwareEngineerRepository, never()).save(any(SoftwareEngineer.class));
    }

    @Test
    @DisplayName("Should insert software engineer and verify AI prompt format")
    void insertSoftwareEngineerTest_VerifyAIPromptFormat() {
        // Given
        SoftwareEngineer se = new SoftwareEngineer();
        se.setName("Alice Johnson");
        se.setTechStack("React, Node.js, MongoDB");

        String expectedAIResponse = "Learn TypeScript and advanced React patterns";
        when(aiService.chat(anyString())).thenReturn(expectedAIResponse);
        
        // When
        softwareEngineerService.insertSoftwareEngineer(se);

        // Then
        ArgumentCaptor<String> promptCaptor = ArgumentCaptor.forClass(String.class);
        verify(aiService, times(1)).chat(promptCaptor.capture());

        String capturedPrompt = promptCaptor.getValue();
        
        // More specific prompt validation
        assertAll(
            () -> assertTrue(capturedPrompt.contains("Alice Johnson"), "Prompt should contain engineer name"),
            () -> assertTrue(capturedPrompt.contains("React, Node.js, MongoDB"), "Prompt should contain tech stack"),
            () -> assertTrue(capturedPrompt.toLowerCase().contains("learning path"), "Prompt should mention learning path"),
            () -> assertTrue(capturedPrompt.toLowerCase().contains("recommendation"), "Prompt should mention recommendation")
        );

        assertEquals(expectedAIResponse, se.getLearningPathRecommendation());
        verify(softwareEngineerRepository, times(1)).save(se);
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    @DisplayName("Should insert software engineer with timeout")
    void insertSoftwareEngineerTest_WithTimeout() {
        // Given
        SoftwareEngineer se = new SoftwareEngineer();
        se.setName("Test Engineer");
        se.setTechStack("Java, Spring");

        when(aiService.chat(anyString())).thenReturn("Learn advanced concepts");
        
        // When
        softwareEngineerService.insertSoftwareEngineer(se);

        // Then
        verify(aiService, times(1)).chat(anyString());
        verify(softwareEngineerRepository, times(1)).save(se);
    }

}
