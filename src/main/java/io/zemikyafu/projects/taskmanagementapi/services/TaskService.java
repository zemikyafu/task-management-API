package io.zemikyafu.projects.taskmanagementapi.services;

import io.zemikyafu.projects.taskmanagementapi.entity.Task;
import io.zemikyafu.projects.taskmanagementapi.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTask(){
        return  taskRepository.findAll();
    }

    public Optional<Task> findTaskById(Long id){
       return taskRepository.findById(id);
    }

    public Task createTask(Task task){
       return  taskRepository.save(task);
    }

    public Task updateTask(Long id,Task updatedTask)
    {
      return taskRepository.findById(id).map(task-> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setDueDate(updatedTask.getDueDate());
            task.setStatus(updatedTask.getStatus());
            return taskRepository.save(task);
        }).orElseThrow(()-> new ResourceNotFoundException("Task not found with id " + id));
    }

    public  void deleteTask(Long id)
    {
        taskRepository.deleteById(id);
    }
}
