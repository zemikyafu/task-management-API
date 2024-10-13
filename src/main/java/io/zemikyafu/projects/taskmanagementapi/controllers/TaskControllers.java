
package io.zemikyafu.projects.taskmanagementapi.controllers;

import io.zemikyafu.projects.taskmanagementapi.entity.Task;
import io.zemikyafu.projects.taskmanagementapi.repositories.TaskRepository;
import io.zemikyafu.projects.taskmanagementapi.services.ResourceNotFoundException;
import io.zemikyafu.projects.taskmanagementapi.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskControllers {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks()
    {
        return taskService.getAllTask();
    }

//    @GetMapping("hello")
//    public String hello (){
//        return "Hello";
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable Long id)
    {

        return taskService.findTaskById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        Task newTask= taskService.createTask(task);
        return new ResponseEntity<>(newTask,HttpStatus.CREATED);
        //return ResponseEntity.status(HttpStatus.CREATED).body(newTask);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task)
    {
        try {
            Task updatedTask=taskService.updateTask( id, task);
            return ResponseEntity.ok(updatedTask);
        } catch (ResourceNotFoundException  e) {
           return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}

