package com.engcorner.epose.controller;

import com.engcorner.epose.domain.course.Action;
import com.engcorner.epose.domain.course.Course;
import com.engcorner.epose.domain.user.User;
import com.engcorner.epose.domain.user.UserPose;
import com.engcorner.epose.repository.course.CourseRepository;
import com.engcorner.epose.repository.user.UserPoseRepository;
import com.engcorner.epose.repository.user.UserRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.catalina.connector.Response;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController implements WebMvcConfigurer {

    @Autowired
    BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPoseRepository userPoseRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/action1-1").setViewName("action1-1");
    }

    @RequestMapping(value = {"/", "/home", "/index", "/classroom"})
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("name", user.getName());
        Long courseid = user.getUserPose().getLastCourse();
        if (courseid == null) {
            model.addAttribute("lastCourse", null);
        } else {
            model.addAttribute("lastCourse", courseRepository.findById(courseid).get());
        }
        model.addAttribute("recommendCourse", null);//推荐课程
        model.addAttribute("currentAction", user.getUserPose().getCurrentAction());
        model.addAttribute("courseList", courseRepository.findAll());
        return "classroom";
    }

    @RequestMapping(value = {"/test"})
    public String test(Model model) {
        return "test";
    }

    @GetMapping("/course")
    public String course(Model model, HttpServletRequest request,
                         @RequestParam(value = "actionid", defaultValue = "1") String actionid) {
        String courseid = request.getParameter("courseid");//获取请求动作id
        if (courseid == null) {
            return "classroom";
        }
        Course curcourse = courseRepository.findById(Long.parseLong(courseid)).get();
        Action currentAction = null;
        currentAction = new Action();
        List<Action> actions = curcourse.getActions();
        for (Action action :
                actions) {
            if (Long.parseLong(courseid) == action.getId()) {
                currentAction = action;
                break;
            }
        }
        model.addAttribute("actionid", actionid);//当前动作id
        model.addAttribute("currentAction", currentAction);//当前动作Action
        model.addAttribute("course", curcourse);//获取当前课程
        model.addAttribute("actions", curcourse.getActions());//获取课号下的所有动作
        return "course";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/setting")
    public String edit(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUsername());
        return "setting";
    }

    @GetMapping("/register")
    public String register(User user) {
        return "register";
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }

    @GetMapping("/person")
    public String person(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUsername());
        return "person";
    }

    @PostMapping("/register")
    public String checkRegister(@Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (userRepository.findByUsername(user.getUsername()) != null) {
            model.addAttribute("message", "用户名“" + user.getUsername() + "”已存在");
            return "register";
        }

        user.setPassword(bcryptEncoder.encode(user.getPassword()));

        // 初始化用户学习状态
        UserPose userPose = new UserPose();
        userPose.setCourseAverScore(new Long(0));
        userPose.setCourseMaxScore(new Long(0));
        userPose.setCourseMinScore(new Long(0));
        userPose.setPartScores("0,0,0,0,0,0,0,0");
        userPoseRepository.save(userPose);

        user.setUserPose(userPose);
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @GetMapping(value = "sendAjax")
    @ResponseBody
    public String sendAjax(@RequestParam(value = "name",defaultValue = "test") String name){
        System.out.println(name);
        return "Done";
    }




    //把接送数据传输到前端页面
    @GetMapping(value = "/getAjax")
    @ResponseBody
    public Map<String, String> getAjax(){
        Map<String, String> map= new HashMap<>();
        map.put("1", "1");
        map.put("2", "4");
        return map;
    }



}

