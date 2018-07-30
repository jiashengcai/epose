package com.engcorner.epose.controller;

import com.engcorner.epose.domain.course.Course;
import com.engcorner.epose.domain.user.User;
import com.engcorner.epose.domain.user.UserPose;
import com.engcorner.epose.repository.course.CourseRepository;
import com.engcorner.epose.repository.user.UserPoseRepository;
import com.engcorner.epose.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
        model.addAttribute("currentAction", user.getUserPose().getCurrentAction());
        model.addAttribute("courseList", courseRepository.findAll());
        return "classroom";
    }

    @RequestMapping(value = {"/class2"})
    public String class2(Model model, HttpServletRequest request) {
        String courseid = request.getParameter("courseid");
        Course curcourse = courseRepository.findById(Long.parseLong(courseid)).get();
        model.addAttribute("course", curcourse);
        model.addAttribute("actions", curcourse.getActions());
        return "class2";
    }

    @GetMapping("/course")
    public String course(Model model, HttpServletRequest request) {
        String actionid = request.getParameter("actionid");
        if (actionid == null) actionid = "1-1";
        model.addAttribute("actionid", actionid);
        return "course";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/setting")
    public String edit() {
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
    public String person() {
        return "person";
    }
    @GetMapping("/test")
    public String test() {
        return "test";
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
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
}
