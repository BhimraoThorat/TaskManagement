package com.task.Entity;



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
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CommentID")
    private int commentId;

    @ManyToOne
    @JoinColumn(name = "TaskID")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @Column(name = "CommentText")
    private String commentText;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    
    public Comment() {}

    public Comment(Task task, User user, String commentText, LocalDateTime createdAt) {
        this.task = task;
        this.user = user;
        this.commentText = commentText;
        this.createdAt = createdAt;
    }

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", task=" + task + ", user=" + user + ", commentText=" + commentText
				+ ", createdAt=" + createdAt + "]";
	}

    
}
