package com.task.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TaskID")
    private int taskId;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "DueDate")
    private LocalDate dueDate;

    @Column(name = "Priority")
    private String priority;

    @Column(name = "Status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "AssignedTo")
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;

   
    public Task() {}

    public Task(String title, String description, LocalDate dueDate, String priority, String status,
                User assignedTo, Category category) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.assignedTo = assignedTo;
        this.category = category;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", title=" + title + ", description=" + description + ", dueDate=" + dueDate
				+ ", priority=" + priority + ", status=" + status + ", assignedTo=" + assignedTo + ", category="
				+ category + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", getTaskId()=" + getTaskId()
				+ ", getTitle()=" + getTitle() + ", getDescription()=" + getDescription() + ", getDueDate()="
				+ getDueDate() + ", getPriority()=" + getPriority() + ", getStatus()=" + getStatus()
				+ ", getAssignedTo()=" + getAssignedTo() + ", getCategory()=" + getCategory() + ", getCreatedAt()="
				+ getCreatedAt() + ", getUpdatedAt()=" + getUpdatedAt() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public void setUser(User savedUser) {
		
		
	}

   
}
