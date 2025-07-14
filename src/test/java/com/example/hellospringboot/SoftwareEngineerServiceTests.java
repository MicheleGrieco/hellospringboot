package com.example.hellospringboot;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SoftwareEngineerServiceTests {

    @Mock
    private SoftwareEngineerRepository softwareEngineerRepository;
    
    @Mock
    private AIService aiService;

    @InjectMocks
    private SoftwareEngineerService softwareEngineerService;
    
    @BeforeEach
    @SuppressWarnings("unused")
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
        SoftwareEngineer se = new SoftwareEngineer(1, "Alice", "Java", null);
        when(softwareEngineerRepository.findById(1)).thenReturn(Optional.of(se));

        SoftwareEngineer result = softwareEngineerService.getSoftwareEngineerById(1);

        assertThat(result).isEqualTo(se);
        verify(softwareEngineerRepository).findById(1);
    }

    @Test
    public void deleteSoftwareEngineerByIdTest() {
        when(softwareEngineerRepository.existsById(1)).thenReturn(true);
        softwareEngineerService.deleteSoftwareEngineer(1);
        verify(softwareEngineerRepository).deleteById(1);
    }

    @Test
    public void updateSoftwareEngineerTest() {
        SoftwareEngineer existing = new SoftwareEngineer(1, "Alice", "Java", null);
        SoftwareEngineer update = new SoftwareEngineer(2, "Alice Updated", "Kotlin", null);
        when(softwareEngineerRepository.findById(1)).thenReturn(Optional.of(existing));
        when(softwareEngineerRepository.save(any(SoftwareEngineer.class))).thenReturn(existing);

        softwareEngineerService.updateSoftwareEngineer(1, update);

        assertThat(existing.getName()).isEqualTo("Alice Updated");
        assertThat(existing.getTechStack()).isEqualTo("Kotlin");
        verify(softwareEngineerRepository).save(existing);
    }

    @Test
    @SuppressWarnings("")
    public void getSoftwareEngineerById_NotFoundTest() {
        when(softwareEngineerRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(
            RuntimeException.class,
            () -> softwareEngineerService.getSoftwareEngineerById(99)
        );
        verify(softwareEngineerRepository).findById(99);
    }

    @Test
    @SuppressWarnings("")
    public void deleteSoftwareEngineerById_NotFoundTest() {
        when(softwareEngineerRepository.existsById(99)).thenReturn(false);

        assertThrows(
            
        RuntimeException.class,
            () -> softwareEngineerService.deleteSoftwareEngineer(99)
        );
        verify(softwareEngineerRepository).existsById(99);
    }

    @Test
    @SuppressWarnings("")
    public void updateSoftwareEngineer_NotFoundTest() {
        SoftwareEngineer update = new SoftwareEngineer(null, "Not Found", "Go", null);
        when(softwareEngineerRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(
            RuntimeException.class,
            () -> softwareEngineerService.updateSoftwareEngineer(99, update)
        );
        verify(softwareEngineerRepository).findById(99);
    }
}
