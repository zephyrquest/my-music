package com.github.busiedpandora.mymusic.controller;

import com.github.busiedpandora.mymusic.model.NavigationBarField;
import com.github.busiedpandora.mymusic.service.AlbumService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"", "/{" + MyMusicController.ATTRIBUTE_LANGUAGE + "}"})
public class MyMusicController {
    @Autowired
    private AlbumService albumService;

    //Controller
    private static final String CTRL_TEST = "/test";
    public static final String CTRL_MUSIC = "/music";
    public static final String CTRL_ABOUT = "/about";

    //View
    private static final String VIEW_TEST = "/test";
    private static final String VIEW_MUSIC = "/music";
    private static final String VIEW_ABOUT = "/about";

    //Model
    public static final String MODEL_ARTIST_NAME = "artistName";
    public static final String MODEL_MUSIC_URL = "musicUrl";
    public static final String MODEL_ABOUT_URL = "aboutUrl";
    public static final String MODEL_NAVIGATION_BAR_FIELD = "navigationBarField";
    public static final String MODEL_LANGUAGE = "lang";
    public static final String MODEL_SERVLET_PATH = "servletPath";
    private static final String MODEL_ALBUMS = "albums";

    //Attribute variables
    public static final String ATTRIBUTE_LANGUAGE = "lang";


    @Value("${host.name}")
    private String basePath;

    @Value("${artist.name}")
    private String artistName;

    @ModelAttribute
    public void addAttributes(HttpServletRequest request, ModelAndView mv) {
        mv.addObject(MODEL_ARTIST_NAME, artistName);
        mv.addObject(MODEL_MUSIC_URL, CTRL_MUSIC);
        mv.addObject(MODEL_ABOUT_URL, CTRL_ABOUT);
        mv.addObject(MODEL_SERVLET_PATH, request.getServletPath());
    }

    @GetMapping(CTRL_TEST)
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView(VIEW_TEST);
        mv.addObject("variable", "var from controller to view");
        return mv;
    }

    @GetMapping({"/", CTRL_MUSIC})
    public ModelAndView music(ModelAndView mv) {
        mv.setViewName(VIEW_MUSIC);

        mv.addObject(MODEL_NAVIGATION_BAR_FIELD, NavigationBarField.MUSIC.getName());

        mv.addObject(MODEL_ALBUMS, albumService.getAllAlbums());

        mv.addObject(AlbumController.MODEL_ALBUM_URL, AlbumController.CTRL_ALBUM);

        return mv;
    }

    @GetMapping(CTRL_ABOUT)
    public ModelAndView about(ModelAndView mv) {
        mv.setViewName(VIEW_ABOUT);

        mv.addObject(MODEL_NAVIGATION_BAR_FIELD, NavigationBarField.ABOUT.getName());

        return mv;
    }
}
