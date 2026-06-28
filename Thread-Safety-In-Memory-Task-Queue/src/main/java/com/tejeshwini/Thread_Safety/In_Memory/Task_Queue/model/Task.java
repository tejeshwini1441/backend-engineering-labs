package com.tejeshwini.Thread_Safety.In_Memory.Task_Queue.model;

import com.tejeshwini.Thread_Safety.In_Memory.Task_Queue.enums.Priority;
import lombok.Data;

import java.time.Instant;
@Data
public class Task {
    private String task;
    private Instant timestamp;
    private Priority priority;
}
