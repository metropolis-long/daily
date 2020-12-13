package com.daily.controller;

import com.daily.msg.ResultBody;
import com.daily.pojo.Comment;
import com.daily.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName FC
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/11/28 16:14
 * @VERSION 1.0.0
 */
@RestController
@RequestMapping("/api/comment")
public class CommentCtrl {
    @Autowired
    private CommentService commentService;
    @RequestMapping(value = {"/save"}, produces = {"application/json;charset=UTF-8"})
    public ResultBody save(Comment food) {
        return new ResultBody(commentService.save(food));
    }
    @RequestMapping(value = {"/list"}, produces = {"application/json;charset=UTF-8"})
    public Object province() {
        return new ResultBody(commentService.findAll());
    }
}
