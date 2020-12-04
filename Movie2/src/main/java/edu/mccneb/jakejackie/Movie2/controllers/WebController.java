package edu.mccneb.jakejackie.Movie2.controllers;

import edu.mccneb.jakejackie.Movie2.Repository.MovieRepository;
import edu.mccneb.jakejackie.Movie2.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/example")
    public String example() {
        return "example";
    }

    @RequestMapping("/securedPage")
    public String securedPage(Model model, Principal principal) {
        System.out.println(principal);
        System.out.println(principal.getName());
        System.out.println(principal.toString());
        System.out.println(principal.getClass().getName());
        try {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            Map<String, Object> attributes = token.getPrincipal().getAttributes();
            System.out.println(attributes);
            String login= (String) attributes.get("login");
            String name = (String) attributes.get("name");
            String email = (String) attributes.get("email");
            model.addAttribute("login",login);
            model.addAttribute("name",name);
            model.addAttribute("email",email);
        } catch (Exception e) {
            e.printStackTrace(); //TODO: use a logger!
        }
        return "securedPage";
    }


    @RequestMapping("/")
    public String index(Model model, Principal principal) {
        List<Movie> movieList = new ArrayList<>();
        movieList = movieRepository.getAllMovies();
        model.addAttribute("movieList", movieList);

        return "index";
    }


    private void setCurrentDate(Model model) {
        String currentDate = (new Date()).toString();
        model.addAttribute("currentDate", currentDate);
    }

}
