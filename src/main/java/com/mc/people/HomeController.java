package com.mc.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class HomeController {
    @Autowired
    ColorRepository colorRepository;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/")
    public String showHomepage(Model model){
        model.addAttribute("persons", personRepository.findAll());
        model.addAttribute("colors", colorRepository.findAll());
        return "index";
    }

    @RequestMapping("/addcolor")
    public String addColor(Model model){
        model.addAttribute("color", new Color());
        model.addAttribute("personList", personRepository.findAll());
        return "inputForm";
    }

    @RequestMapping("/savecolor")
    public String saveColor(@ModelAttribute("color") Color color){
        colorRepository.save(color);
        return "redirect:/";
    }

    @PostConstruct
    public void fillPerson(){
        Person person = new Person();
        person.setPersonName("John Doe");
        personRepository.save(person);

        person = new Person();
        person.setPersonName("Adam Smith");
        personRepository.save(person);

        person = new Person();
        person.setPersonName("Sheryl Sandberg");
        personRepository.save(person);
    }
}
