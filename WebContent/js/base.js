/**
 * Created by SAMSUNG on 2017/1/11.
 */
//滚动导航栏
//$(window).scroll(function(){
//    var mm=$(window).scrollTop();
//    if(mm>1500){
//        $(".zxr_nav").css({position:"fixed",top:"0px"})
//
//    }
//    else {
//        $(".zxr_nav").css({position:""});
//
//    }
//
//});
$(function () {
    //导航
    $(" .zxr_nav .nav_ten .quanbu_zxr .ab_nav .ab_nav_2>div").not(".zxr_nav .nav_ten .quanbu_zxr .ab_nav .ab_nav_2 .main3").css({display:"none"});
    $(" .zxr_nav .nav_ten .quanbu_zxr .ab_nav .ab_nav_2>div").eq(0).css({display:"block"});
    $(".quanbu_zxr1").hover(function(){
        $(".ab_nav").css({display:"block"})
    });
    $(".ab_nav").mouseleave(function(){
        $(this).css({display:"none"})
    });

    $(".ab_nav_l ul li").hover(function(){
        $(this).css({backgroundColor:"white"});
        $(this).siblings().css({backgroundColor:""});
        var i=$(".ul1 li").index(this);
        if(i<=7&&i>=0) {

            $(" .zxr_nav .nav_ten .quanbu_zxr .ab_nav .ab_nav_2>div").eq(i).css({display: "block"});
            $(" .zxr_nav .nav_ten .quanbu_zxr .ab_nav .ab_nav_2>div").eq(i).siblings().not(".zxr_nav .nav_ten .quanbu_zxr .ab_nav .ab_nav_2 .main3").css({display: "none"});
        }
    });
});

