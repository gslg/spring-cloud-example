package com.lg.mybatis.helper.samples.controller;

import com.lg.mybatis.helper.samples.model.BaseUser;
import com.lg.mybatis.helper.samples.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BaseUserController {

    @Autowired
    private BaseUserService baseUserService;

    @GetMapping("/users")
    @ResponseBody
    public List<BaseUser> select(){
        return baseUserService.selectAll();
    }




}
