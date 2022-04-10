package learntogether.Controller;

import learntogether.DTO.UserDTO;
import learntogether.IService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;


/*
  Created by Luvbert
*/
@Controller
public class HomeController {
    @Autowired
    private IUserService userService;

    @GetMapping(path = "/welcome")
    public ModelAndView welcomePage(){
        ModelAndView modelAndView = new ModelAndView("welcome");
        return modelAndView;
    }

    @GetMapping(path = "/home")
    public ModelAndView homePage(){
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    @GetMapping(path = "/course")
    public ModelAndView coursePage(){
        ModelAndView modelAndView = new ModelAndView("course");
        return modelAndView;
    }

    @GetMapping(path = "/news")
    public ModelAndView newsPage(){
        ModelAndView modelAndView = new ModelAndView("news");
        return modelAndView;
    }

    @GetMapping(path = "/discussion")
    public ModelAndView discussionPage(){
        ModelAndView modelAndView = new ModelAndView("news");
        return modelAndView;
    }

    @PostMapping(path = "/register")
    public ModelAndView registerUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult){
        ModelAndView mav;
        //check annotation validate
        if(bindingResult.hasErrors()){
            mav = new ModelAndView("welcome");
            //send error to view
            String error = bindingResult.getFieldError().getField().toString();
            mav.addObject(bindingResult.getFieldError().getField(), bindingResult.getFieldError().getDefaultMessage());
            //send-keep old data to view
            mav.addObject("tempUserData", userDTO);
            return mav;
        }
        //Call service to register new account
        Map<String, String> registered = userService.registerNewUserAccount(userDTO);
        //Check service validate
        if(!registered.isEmpty()){
            mav = new ModelAndView("welcome");
            //send validate error to view
            mav.addAllObjects(registered);
            //send-keep old data to view
            mav.addObject("tempUserData", userDTO);
            return mav;
        }

        mav = new ModelAndView("redirect:/welcome?login");
        mav.addObject("Success", "Registered");
        return mav;
    }

    /*
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null) new SecurityContextLogoutHandler().logout(request, response, auth);
        return new ModelAndView("redirect:/welcome");
    }
     */
}
