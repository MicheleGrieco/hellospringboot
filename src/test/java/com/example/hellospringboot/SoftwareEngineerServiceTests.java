package com.example.hellospringboot;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SoftwareEngineerServiceTests {


    @Mock
    private SoftwareEngineerRepository softwareEngineerRepository;
    
    @Mock
    private AIService aiService;

    @InjectMocks
    private SoftwareEngineerService softwareEngineerService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllSoftwareEngineersTest() {
        SoftwareEngineer se1 = new SoftwareEngineer(1, "Alice", "Java", null);
        SoftwareEngineer se2 = new SoftwareEngineer(2, "Bob", "Python", null);
        when(softwareEngineerRepository.findAll()).thenReturn(Arrays.asList(se1, se2));
        
        List<SoftwareEngineer> result = softwareEngineerService.getAllSoftwareEngineers();
        assertThat(result).hasSize(2).contains(se1, se2);
        verify(softwareEngineerRepository).findAll();
    }

    @Test
    public void insertSoftwareEngineerTest() {
        SoftwareEngineer se = new SoftwareEngineer(3, "Alice", "Java", null);
        when(aiService.chat(anyString())).thenReturn("Learn Java basics, Spring Boot and Hibernate.");
        when(softwareEngineerRepository.save(any(SoftwareEngineer.class))).thenReturn(se);

        softwareEngineerService.insertSoftwareEngineer(se);

        assertThat(se.getLearningPathRecommendation()).isEqualTo("Learn Java basics, Spring Boot and Hibernate.");
        verify(softwareEngineerRepository).save(se);
    }

    @Test
    public void getSoftwareEngineerByIdTest() {
        // This test will check if a software engineer can be retrieved by ID
    }

    @Test
    public void deleteSoftwareEngineerByIdTest() {
        // This test will check if a software engineer can be deleted by ID
    }

    @Test
    public void updateSoftwareEngineerTest() {
        // This test will check if a software engineer can be updated correctly
    }
}
