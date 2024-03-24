package com.task.DAO;

import java.util.List;

import com.task.Entity.Comment;

public interface CommentDAO {
    void addComment(Comment comment);
    Comment getCommentById(int commentId);
    void updateComment(Comment comment);
    void deleteComment(int commentId);
	List<Comment> getAllComments();
	List<Comment> getCommentsByUserId(int userId);

	
}
