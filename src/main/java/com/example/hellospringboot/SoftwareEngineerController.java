package com.example.hellospringboot;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {
    
    /** Service to handle software engineer operations */
    private final SoftwareEngineerService softwareEngineerService;
    
    /**
     * Constructor for SoftwareEngineerController.
     * 
     * @param softwareEngineerService
     */
    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }
    
    @GetMapping
    /**
     * Retrieves a list of software engineers.
     * 
     * @return a list of SoftwareEngineer objects
     */
    public List<SoftwareEngineer> getEngineers() {
            return softwareEngineerService.getAllSoftwareEngineers();
    }

    @PostMapping
    /**
     * Adds a new software engineer.
     * 
     * @param softwareEngineer the SoftwareEngineer object to be added
     */
    public void addNewSoftwareEngineer(
        @RequestBody SoftwareEngineer softwareEngineer) {
        softwareEngineerService.insertSoftwareEngineer(softwareEngineer);
    }
}
