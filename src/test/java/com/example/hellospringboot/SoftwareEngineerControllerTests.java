package com.example.hellospringboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SoftwareEngineerController.class)
@DisplayName("SoftwareEngineerController Unit Tests")
class SoftwareEngineerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SoftwareEngineerService softwareEngineerService;

    @Autowired
    private ObjectMapper objectMapper;

    private SoftwareEngineer testEngineer;
    private List<SoftwareEngineer> testEngineers;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        testEngineer = new SoftwareEngineer();
        testEngineer.setId(1);
        testEngineer.setName("Mario Rossi");
        testEngineer.setTechStack("Java, Spring Boot, PostgreSQL");
        testEngineer.setLearningPathRecommendation("Focus on microservices architecture");

        SoftwareEngineer engineer2 = new SoftwareEngineer();
        engineer2.setId(2);
        engineer2.setName("Luigi Verdi");
        engineer2.setTechStack("React, Node.js, MongoDB");
        engineer2.setLearningPathRecommendation("Learn TypeScript and testing");

        testEngineers = Arrays.asList(testEngineer, engineer2);
    }

    @Test
    @DisplayName("GET /api/v1/software-engineers - Should return all engineers")
    void getEngineers_ShouldReturnAllEngineers() throws Exception {
        // Given
        when(softwareEngineerService.getAllSoftwareEngineers()).thenReturn(testEngineers);

        // When & Then
        mockMvc.perform(get("/api/v1/software-engineers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Mario Rossi")))
                .andExpect(jsonPath("$[0].techStack", is("Java, Spring Boot, PostgreSQL")))
                .andExpect(jsonPath("$[0].learningPathRecommendation", is("Focus on microservices architecture")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Luigi Verdi")))
                .andExpect(jsonPath("$[1].techStack", is("React, Node.js, MongoDB")));

        verify(softwareEngineerService, times(1)).getAllSoftwareEngineers();
    }

    @Test
    @DisplayName("GET /api/v1/software-engineers - Should return empty list when no engineers")
    void getEngineers_ShouldReturnEmptyList() throws Exception {
        // Given
        when(softwareEngineerService.getAllSoftwareEngineers()).thenReturn(Arrays.asList());

        // When & Then
        mockMvc.perform(get("/api/v1/software-engineers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(softwareEngineerService, times(1)).getAllSoftwareEngineers();
    }

    @Test
    @DisplayName("GET /api/v1/software-engineers/{id} - Should return engineer by ID")
    void getEngineerById_ShouldReturnEngineer() throws Exception {
        // Given
        when(softwareEngineerService.getSoftwareEngineerById(1)).thenReturn(testEngineer);

        // When & Then
        mockMvc.perform(get("/api/v1/software-engineers/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Mario Rossi")))
                .andExpect(jsonPath("$.techStack", is("Java, Spring Boot, PostgreSQL")))
                .andExpect(jsonPath("$.learningPathRecommendation", is("Focus on microservices architecture")));

        verify(softwareEngineerService, times(1)).getSoftwareEngineerById(1);
    }

    @Test
    @DisplayName("GET /api/v1/software-engineers/{id} - Should return 404 when engineer not found")
    void getEngineerById_ShouldReturn404WhenNotFound() throws Exception {
        // TODO
    }

    @Test
    @DisplayName("POST /api/v1/software-engineers - Should create new engineer")
    void addNewSoftwareEngineer_ShouldCreateEngineer() throws Exception {
        // Given
        SoftwareEngineer newEngineer = new SoftwareEngineer();
        newEngineer.setName("Anna Bianchi");
        newEngineer.setTechStack("Python, Django, PostgreSQL");

        // When & Then
        mockMvc.perform(post("/api/v1/software-engineers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newEngineer)))
            .andExpect(status().isOk());

        verify(softwareEngineerService, times(1)).insertSoftwareEngineer(any(SoftwareEngineer.class));
    }

    @Test
    @DisplayName("POST /api/v1/software-engineers - Should handle invalid JSON")
    void addNewSoftwareEngineer_ShouldHandleInvalidJson() throws Exception {
        // Given
        String invalidJson = "{ \"name\": \"Mario Rossi\", \"techStack\": }";

        // When & Then
        mockMvc.perform(post("/api/v1/software-engineers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
                .andExpect(status().isBadRequest());

        verify(softwareEngineerService, never()).insertSoftwareEngineer(any());
    }
}