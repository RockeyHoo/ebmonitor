
//设置选中导航样式
window.onload = function () {
    var href = location.href;
    if (href.lastIndexOf('?') != -1) {
        href = href.substring(0, href.lastIndexOf('?'));
    }
    if (href.lastIndexOf('/') != -1) {
        href = href.substring(href.lastIndexOf('/') + 1);
    }
    href = href.toLowerCase();
    if (href == "tz.aspx" || href == "login.aspx") {
        $(this).find("a").attr("href", "Index.aspx").addClass("back1 back2");
    } else {
        $(".nav .navs").each(function () {
            var currenthref = $(this).find("a").attr("href").toLowerCase();
            if (currenthref.lastIndexOf('?') != -1) {
                currenthref = currenthref.substring(0, currenthref.lastIndexOf('?'));
            }
            if (currenthref.lastIndexOf('/') != -1) {
                currenthref = currenthref.substring(currenthref.lastIndexOf('/') + 1);
            }
            if (currenthref == href) {
                if (currenthref == "index.aspx") {
                    $(this).find("a").addClass("back1");
                    $(this).find("a").addClass("back2");
                } else {
                    $(this).find("a").addClass("back");
                    $(this).find("a").removeClass("back2");
                }
            } else {
                if (currenthref == "index.aspx") {
                    $(this).find("a").removeClass("back1");
                    $(this).find("a").addClass("back2");
                }
                else {
                    $(this).find("a").removeClass("back2");
                    $(this).find("a").removeClass("back");
                }
            }
        })
    }
}