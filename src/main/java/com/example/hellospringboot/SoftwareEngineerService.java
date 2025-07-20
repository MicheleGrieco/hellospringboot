package com.example.hellospringboot;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository softwareEngineerRepository;
    private final AIService aiService;

    /**
     * Constructor for SoftwareEngineerService.
     * 
     * @param softwareEngineerRepository
     */
    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository, AIService aiService) {
        this.softwareEngineerRepository = softwareEngineerRepository;
        this.aiService = aiService;
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
        String prompt = """
            Based on the programming tech stack %s that %s has given
            Provide a full learning path and recommendations for this person.
            """.formatted(
                softwareEngineer.getTechStack(),
                softwareEngineer.getName()
        );
        String chatRes = aiService.chat(prompt);
        softwareEngineer.setLearningPathRecommendation(chatRes);
        softwareEngineerRepository.save(softwareEngineer);
    }

    /**
     * Retrieves a software engineer by ID.
     * 
     * @param id the ID of the software engineer
     * @return the SoftwareEngineer object with the specified ID
     */
    public SoftwareEngineer getSoftwareEngineerById(Integer id) {
        return softwareEngineerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Software engineer with " + id + " not found"));
    }

    /**
     * Deletes a software engineer by ID.
     * 
     * @param id the ID of the software engineer to be deleted
     */
    public void deleteSoftwareEngineer(Integer id) {
        boolean exists = softwareEngineerRepository.existsById(id);
        if(!exists) {
            throw new RuntimeException("Software engineer with " + id + " not found");
        }
        softwareEngineerRepository.deleteById(id);
    }

    /**
     * Updates an existing software engineer.
     * 
     * @param id the ID of the software engineer to be updated
     * @param update the updated SoftwareEngineer object
     */
    public void updateSoftwareEngineer(Integer id, SoftwareEngineer update) {
        SoftwareEngineer softwareEngineer = softwareEngineerRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(
            "Software engineer with " + id + " not found"
        ));
        softwareEngineer.setName(update.getName());
        softwareEngineer.setTechStack(update.getTechStack());
        softwareEngineer.setSalary(update.getSalary());
        softwareEngineerRepository.save(softwareEngineer);
    }

    /**
     * Retrieves software engineers with a salary above a specified threshold.
     * 
     * @param salaryThreshold the salary threshold
     * @return a list of SoftwareEngineer objects with salaries above the threshold
     */
    public List<SoftwareEngineer> getEngineersWithSalaryAbove(Float salaryThreshold) {
        return softwareEngineerRepository.findAll()
                                            .stream()
                                            .filter(softwareEngineer ->
                                                        softwareEngineer.getSalary() != null
                                                        &&
                                                        softwareEngineer.getSalary() > salaryThreshold)
                                            .toList();
    }
}
