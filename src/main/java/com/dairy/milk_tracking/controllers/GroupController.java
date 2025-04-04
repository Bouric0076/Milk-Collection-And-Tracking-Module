package com.dairy.milk_tracking.controllers;

import com.dairy.milk_tracking.models.Group;
import com.dairy.milk_tracking.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    /**
     * Get all groups.
     * @return List of all groups.
     */
    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    /**
     * Get a group by their ID.
     * @param id Group's ID.
     * @return Group object if found, else 404.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable Long id) {
        Optional<Group> group = groupService.getGroupById(id);
        return group.map(g -> new ResponseEntity<>(g, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Save a new or update an existing group.
     * @param group Group object to be saved.
     * @return Saved or updated group object.
     */
    @PostMapping
    public ResponseEntity<Group> saveGroup(@RequestBody Group group) {
        Group savedGroup = groupService.saveGroup(group);
        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
    }

    /**
     * Update an existing group.
     * @param id Group's ID.
     * @param group Group data to update.
     * @return Updated group object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable Long id, @RequestBody Group group) {
        Optional<Group> existingGroup = groupService.getGroupById(id);
        if (existingGroup.isPresent()) {
            group.setId(id);
            Group updatedGroup = groupService.saveGroup(group);
            return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete a group by their ID.
     * @param id Group's ID.
     * @return Success or failure response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
