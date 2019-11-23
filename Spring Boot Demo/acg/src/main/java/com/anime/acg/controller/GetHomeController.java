package com.anime.acg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 创建人：帝歌恋雪
 * 创建时间：2017-9-12
 * 说明：
 */
@Controller  // 返回一个网页
@RequestMapping("")
public class GetHomeController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String myHomePage() {
        return "index";
    }

    @RequestMapping(value = {"/animation"}, method = RequestMethod.GET)
    public String myAnimationPage() {
        return "animation/index";
    }

    @RequestMapping(value = {"/comic"}, method = RequestMethod.GET)
    public String myComicPage() {
        return "comic/index";
    }

    @RequestMapping(value = {"/game"}, method = RequestMethod.GET)
    public String myGamePage() {
        return "game/index";
    }

    @RequestMapping(value = {"/music"}, method = RequestMethod.GET)
    public String myMusicPage() {
        return "music/index";
    }


    @RequestMapping(value = {"/girl"}, method = RequestMethod.GET)
    public String myGirlPage() {
        return "girl/index";
    }

}


