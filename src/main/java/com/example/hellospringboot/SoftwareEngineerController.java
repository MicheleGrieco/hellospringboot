package com.example.hellospringboot;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
     * @param softwareEngineerService the service to manage software engineers
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

    @GetMapping("{id}")
    /**
     * Retrieves a software engineer by ID.
     * 
     * @param id the ID of the software engineer
     * @return the SoftwareEngineer object with the specified ID
     */
    public SoftwareEngineer getEngineerById(@PathVariable Integer id) {
            return softwareEngineerService.getSoftwareEngineerById(id);
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

    @DeleteMapping("{id}")
    /**
     * Deletes a software engineer by ID.
     * 
     * @param id the ID of the software engineer to be deleted
     */
    public void deleteSoftwareEngineer(@PathVariable Integer id) {
        softwareEngineerService.deleteSoftwareEngineer(id);
    }

    @PutMapping("{id}")
    /**
     * Updates an existing software engineer.
     * 
     * @param id the ID of the software engineer to be updated
     * @param softwareEngiener the updated SoftwareEngineer object
     */
    public void updateSoftwareEngineer(@PathVariable Integer id, @RequestBody SoftwareEngineer softwareEngiener) {
        softwareEngineerService.updateSoftwareEngineer(id, softwareEngiener);
    }
}
