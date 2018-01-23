$(window).scroll(function(){
    var mm=$(window).scrollTop();
    if(mm>1500){
        $(".zxr_nav").css({position:"fixed",top:"0px"})}
    else {
        $(".zxr_nav").css({position:""});}
});
$(function(){
    $(" a").hover(function(){
        $(this).css({color:" #be202e"})
    },function(){
        $(this).not(".show_zxr>ul>li:first").css({color:""})})

    $(".showshangpin_zxr_img  img").click(function(){
    var url=$(this).attr("src");
        $(this).parent().siblings("img").attr("src",url);
    $(this).css({border:"1px solid red"})
        $(this).siblings().css({border:"1px solid white"})
    })
    //$(".showshangpin_zxr_").hover(function(){
    //
    //    $(this).css({boxShadow:"0px 0px 10px #828282"})
    //},function(){
    //    $(this).css({boxShadow:"0px 0px 0px #828282"})
    //})
})