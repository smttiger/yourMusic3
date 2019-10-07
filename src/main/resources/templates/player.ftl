<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"/>
    <META NAME="ROBOTS" CONTENT="NOINDEX,NOFOLLOW">
    <title>Your music - player</title>

    <link rel="stylesheet" media="screen" href="/static/css/reset.css"/>
    <link rel="stylesheet" media="screen" href="/static/css/mplayer.css"/>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="/static/js/perfect-scrollbar.jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/mplayer.js"></script>
</head>
<body>
<#include "parts/navbar.ftl">
<div class="mplayer">
    <div class="mplayer__front-header">
        <div class="mplayer__front-header-next-bg"></div>
        <div class="mplayer__front-header-current-bg"></div>
        <div class="mplayer__front-btn-playlist"></div>
        <div class="mplayer__cover-art">
            <img class="mplayer__cover-art-next" src="#" alt="cover art">
            <img class="mplayer__cover-art-current" src="#" alt="cover art">
        </div>
        <div class="mplayer__toolbar">
            <div class="mplayer__prev"></div>
            <div class="mplayer__pause"></div>
            <div class="mplayer__next"></div>
        </div>
        <div class="mplayer__front-body">
            <div class="mplayer__front-row">
                <div class="mplayer__front-title-wrap">
                    <div class="mplayer__front-title-track"></div>
                    <div class="mplayer__front-title-author"></div>
                </div>
            </div>
            <div class="mplayer__front-row">
                <div class="mplayer__long">
                    <div class="mplayer__current-time">0:00</div>
                    <div class="mplayer__long-slider"></div>
                    <div class="mplayer__all-time"></div>
                </div>
            </div>
            <div class="mplayer__front-row">
                <div class="mplayer__order-btn-wrap">
                    <div class="mplayer__repeat"></div>
                    <div class="mplayer__random"></div>
                </div>
                <div class="mplayer__volume">
                    <div class="mplayer__volume-icon"></div>
                    <div class="mplayer__volume-slider"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="mplayer__playlist ">
        <div class="mplayer__playlist-header">
            <div class="mplayer__playlist-btn-front"></div>
            <div class="mplayer__playlist-header-title">
                Playlist
            </div>
        </div>
        <div class="mplayer__playlist-body">
            <div class="mplayer__playlist-body-next-bg"></div>
            <div class="mplayer__playlist-body-current-bg"></div>
            <div class="mplayer__playlist-body-inner js-perfect-scrollbar"></div>
        </div>
    </div>
</div>

<script type="text/javascript">
    "use strict";
    var arr = [];
    <#list songs as song>
    var obj = {
                "title": "${song.name}",
                "author": "${song.artist}",
                "pfile": "/aud/${song.filename}"
            };
    arr.push(obj);
    <#else>
    var obj = {
        "title": "There was not any song found"
    };
    arr.push(obj);
    </#list>

    $(function () {
        $('.mplayer').mplayer({
            'volume': 80,
            'playlist': arr
        });
    });

</script>
</body>
</html>