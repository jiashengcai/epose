package com.engcorner.epose.controller;

import com.engcorner.epose.repository.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PoseController {

    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "course/postdata",method = RequestMethod.GET)
    @ResponseBody
    public Map<String ,Object> postData(@RequestParam("actionid")String actionid,
                                        @RequestParam("part")List<String> part,
                                        @RequestParam("total")String total){
        Map<String,Object> model=new HashMap<>();
        System.out.println(actionid);
        return model;
    }

}
