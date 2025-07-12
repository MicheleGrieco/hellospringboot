package com.example.hellospringboot;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository softwareEngineerRepository;

    /**
     * Constructor for SoftwareEngineerService.
     * 
     * @param softwareEngineerRepository
     */
    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    /**
     * Retrieves all software engineers from the repository.
     * 
     * @return a list of SoftwareEngineer objects
     */
    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        return softwareEngineerRepository.findAll();
    }

    /**
     * Inserts a new software engineer into the repository.
     * 
     * @param softwareEngineer the SoftwareEngineer object to be added
     */
    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.save(softwareEngineer);
    }
}
