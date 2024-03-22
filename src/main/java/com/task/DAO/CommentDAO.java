package com.task.DAO;

import com.task.Entity.Comment;

public interface CommentDAO {
    void addComment(Comment comment);
    Comment getCommentById(int commentId);
    void updateComment(Comment comment);
    void deleteComment(int commentId);
}
