package com.cko.sampleSpringProject.controller;

import com.cko.sampleSpringProject.dao.FilmDAO;
import com.cko.sampleSpringProject.model.Films;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class FilmController {

    @Autowired
    FilmDAO filmDAO;

    @GetMapping("/allFilms")
    public String showFilms() {
        return "allFilms";
    }

    @GetMapping("/creatFilm")
    public String showCreatFilm() {
        return "creatFilm";
    }

    @PostMapping("/creatFilm")
    public String addNewFilm(Films films) {
        filmDAO.save(films);
        return "allFilms";
    }

    @GetMapping("/editFilm")
    public ModelAndView showEditFilm(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView();
//        Optional<Films> optional = filmDAO.findById(id);
//        Films films = optional.get(); можно писать так, если не писать об этом в дао
        Films films = filmDAO.findAllById(id);
        modelAndView.addObject("film",films);
        modelAndView.setViewName("editFilm");

        return modelAndView;
    }

    @PostMapping("/editFilm")
    public String editFie(Films films){

        filmDAO.save(films);

        return "allFilms";
    }


}
