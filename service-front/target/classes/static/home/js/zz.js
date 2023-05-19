$(function () {
    var $height = $(window).height();
    $("body").height($height);
    $(window).resize(function () {
        var $height = $(window).height();
        $("body").height($height);
    })
    var loginhide = $("#loginhide").text();
    if (loginhide != '游客') {
        $(".login1").hide()
    }
    if (loginhide == '游客') {
        $("#information").hide();
        $("#exit").hide();
    }
    $(".ch-page li").click(function () {
        $index = $(this).index();
        if ($index >= 0 && $index <= 5) {
            $(".next").show();
        } else {
            $(".next").hide();
        }
        if ($index > 0 && $index <= 5) {
            $(".login").show();
        } else {
            $(".login").hide();
        }
        $(this).addClass("add").siblings().removeClass("add");
        $(".body").css("transform", "translate3d(0," + -($index) * 100 + "%,0");
        $(".body section").eq($index).addClass("count").siblings().removeClass("count");
    })
    $index = $(".ch-page li.add").index();
    flag = true;

    function scroll(e) {
        e = e || window.event;
        if (!flag) return;
        flag = false;
        var loginhide = $("#loginhide").text();
        setTimeout(function () {
            if (e.wheelDelta) {
                if (e.wheelDelta < 0) {
                    if ($index >= 6) {
                        $index = 5;
                    }
                    if ($index >= 0 && loginhide == '游客') {
                        $(".login").show();
                    }
                    $index++;
                    $(".body").css("transform", "translate3d(0," + -($index) * 100 + "%,0");
                    $(".body section").eq($index).addClass("count").siblings().removeClass("count");
                    $(".ch-page li").eq($index).addClass("add").siblings().removeClass("add");
                    flag = true;
                    if ($index == 6) {
                        $(".next").hide();
                    }
                } else {
                    if ($index <= 1) {
                        $(".login").hide();
                        $index = 1;
                    }
                    $index--;
                    $(".body").css("transform", "translate3d(0," + -($index) * 100 + "%,0");
                    $(".body section").eq($index).addClass("count").siblings().removeClass("count");
                    $(".ch-page li").eq($index).addClass("add").siblings().removeClass("add");
                    flag = true;
                    if ($index <= 5 && $index >= 0) {
                        $(".next").show();
                    }
                }
            }
        }, 300)
    }

    if (window.addEventListener) {
        window.addEventListener("DOMMouseScroll", scroll, false);
    }
    window.onmousewheel = scroll;
    $(".next").click(function () {
        if ($index == 5) {
            $(".next").hide();
        }
        if ($index >= 0) {
            $(".login").show();
        }
        $(".body").css("transform", "translate3d(0," + -($index + 1) * 100 + "%,0");
        $(".ch-page li").eq($index + 1).addClass("add").siblings().removeClass("add");
        $(".body section").eq($index + 1).addClass("count").siblings().removeClass("count");
        $index++;
    })
    var video = $("video").get(0);
    video.addEventListener("canplay", function () {
        $(".click").click(function () {
            $("video").show();
            $(video).css("opacity", "1");
            $(".post").hide();
            video.play();
            $(this).hide();
        });
        video.addEventListener("timeupdate", function () {
            if (!$(".sec0").hasClass("count")) {
                video.pause();
                $(".click").show();
            } else {
                video.play();
            }
            if (video.duration === video.currentTime) {
                $(".post").show();
                $(".click").show();
                $("video").hide();
            }
        })
    })
});