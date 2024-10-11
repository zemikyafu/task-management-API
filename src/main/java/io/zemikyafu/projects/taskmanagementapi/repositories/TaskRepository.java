package io.zemikyafu.projects.taskmanagementapi.repositories;

import io.zemikyafu.projects.taskmanagementapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
