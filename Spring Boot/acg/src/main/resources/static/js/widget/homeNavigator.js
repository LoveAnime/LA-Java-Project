/**
 * 主页跳转控制
 * Created by LA on 2017/10/23.
 */

(function ($) {
    $(function () {
        $().switchHomePage();
    });

    var homeVue;
    var hasInit = false;
    $.fn.switchHomePage = function () {
        if(!hasInit){
            homeVue =new Vue({
                el:"#navContainer",
                data: {
                    aboutUrl: window.CONTEXT_PATH + "/",
                    animationUrl: window.CONTEXT_PATH + "/animation",
                    comicUrl: window.CONTEXT_PATH + "/comic",
                    gameUrl: window.CONTEXT_PATH + "/game",
                    musicUrl: window.CONTEXT_PATH + "/music",
                    girlUrl: window.CONTEXT_PATH + "/girl"
                },
                methods: {
                    switchAboutHome: function () {
                        window.location.href = this.aboutUrl;
                    },
                    switchAnimationHome: function () {
                        window.location.href = this.animationUrl;
                    },
                    switchComicHome: function () {
                        window.location.href = this.comicUrl;
                    },
                    switchGameHome: function () {
                        window.location.href = this.gameUrl;
                    },
                    switchMusicHome: function () {
                        window.location.href = this.musicUrl;
                    },
                    switchGirlHome: function () {
                        window.location.href = this.girlUrl;
                    }
                }
            });
            hasInit = true;
        }
    };
})(jQuery);
