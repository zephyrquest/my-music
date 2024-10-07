package com.github.busiedpandora.mymusic.controller;

import com.github.busiedpandora.mymusic.model.NavigationBarField;
import com.github.busiedpandora.mymusic.model.Track;
import com.github.busiedpandora.mymusic.service.AlbumService;
import com.github.busiedpandora.mymusic.model.Album;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({AlbumController.CTRL_ALBUM,
        "/{" + MyMusicController.ATTRIBUTE_LANGUAGE + "}" + AlbumController.CTRL_ALBUM})
public class AlbumController {
    //Controller
    public static final String CTRL_ALBUM = MyMusicController.CTRL_MUSIC + "/album";
    private static final String CTRL_TRACK = "/track";

    //View
    private static final String VIEW_ALBUM = "/album";
    private static final String VIEW_TRACK = "/track";

    //Model
    private static final String MODEL_ALBUM = "album";
    private static final String MODEL_TRACKS = "tracks";
    public static final String MODEL_ALBUM_URL = "albumUrl";
    public static final String MODEL_TRACK_URL = "trackUrl";
    public static final String MODEL_TRACK = "track";

    @Autowired
    private AlbumService albumService;

    @Value("${artist.name}")
    private String artistName;

    @ModelAttribute
    public void addAttributes(HttpServletRequest request, ModelAndView mv) {
        mv.addObject(MyMusicController.MODEL_ARTIST_NAME, artistName);
        mv.addObject(MyMusicController.MODEL_MUSIC_URL, MyMusicController.CTRL_MUSIC);
        mv.addObject(MyMusicController.MODEL_ABOUT_URL, MyMusicController.CTRL_ABOUT);
        mv.addObject(MyMusicController.MODEL_SERVLET_PATH, request.getServletPath());
    }

    @GetMapping("/{albumTitle}")
    public ModelAndView viewAlbum(ModelAndView mv,
                                  @PathVariable("albumTitle") String albumTitle) {
        mv.setViewName(VIEW_ALBUM);

        mv.addObject(MyMusicController.MODEL_NAVIGATION_BAR_FIELD, NavigationBarField.MUSIC.getName());

        Album album = albumService.getAlbumByTitleWithDashesAndIgnoreCase(albumTitle);
        mv.addObject(MODEL_ALBUM, album);

        mv.addObject(MODEL_TRACKS, albumService.getTracksByAlbumOrderByTrackNumber(album));

        mv.addObject(MODEL_TRACK_URL, CTRL_ALBUM + "/" + albumTitle + CTRL_TRACK);

        return mv;
    }

    @GetMapping("/{albumTitle}" + CTRL_TRACK + "/{trackTitle}")
    public ModelAndView viewTrack(ModelAndView mv,
                                  @PathVariable("albumTitle") String albumTitle,
                                  @PathVariable("trackTitle") String trackTitle) {
        mv.setViewName(VIEW_TRACK);

        mv.addObject(MyMusicController.MODEL_NAVIGATION_BAR_FIELD, NavigationBarField.MUSIC.getName());

        Album album = albumService.getAlbumByTitleWithDashesAndIgnoreCase(albumTitle);
        mv.addObject(MODEL_ALBUM, album);

        Track track = albumService.getTrackByAlbumAndTitleWithDashesAndIgnoreCase(album, trackTitle);
        mv.addObject(MODEL_TRACK, track);

        mv.addObject(MODEL_ALBUM_URL, CTRL_ALBUM);

        System.out.println(track);
        return mv;
    }
}
