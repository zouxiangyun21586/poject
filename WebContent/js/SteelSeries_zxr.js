/*产品展示放大镜*/
$(function(){
    $(".nav_left_tu_zxr1 a img").hover(function(){
            var url=$(this).attr("src");
            $(this).animate({borderColor:"red"});
            $(".wrapp .img1 img").attr("src",url);
        },function(){
            $(this).animate({borderColor:"#DCDCDC"});
        });
        $(".img1").hover(
            function(){
                $(this).css({cursor:"crosshair",position: "relative",
                    float:"left"});
                $(this).append("<div class='text'></div>");
                $(".text").css({width:"200px",height:"200px",
                    backgroundColor:"rgba(0,0,0,0.2)",
                    position: "absolute"});
                var url=$(this).find("img").attr("src");
                $(".box").append("<img src='"+url+"' style='position:absolute' width='1000px' height='1000px' >");
                $(".img1").mousemove(function(e){
                    var w=$(".text").width();
                    var h=$(".text").height();
                    var l=$(".img1").offset().left;
                    var t=$(".img1").offset().top;
                    var left=e.pageX-w/2-l;
                    var top=e.pageY-h/2-t;
                    if(left<0){left=0;}
                    else if(left>300){left=300}
                    if(top<0){top=0;}
                    else if(top>300){top=300}
                    $(".text").css({left:left,top:top});
                    $(".box img").css({left:-left*2,top:-top*2})
                })
            },
            function(){
                $("div").remove(".text");
            }
        )
    });
    $(function(){
        $(".wrapp").hover(function(){
            $(".box").css({display:"block"});
        },function(){
            $(".box").css({display:"none"});
        })
        /********没增加一个样式就要增加一遍div点击事件；图片左侧的我已经写死是三张图；别改了***********/
        $(".nav_right_zxr .nav_shangpinS_zxr .nav_shangpinS_zxr1 a .bphone").click(function(){
            $(this).find("div").css({display:"none"});
            $(this).find("img").css({border:"1px solid red"});
            $(this).parent().siblings().find("div").css({display:"block"});
            $(this).parent().siblings().find("img").css({border:"1px solid gainsboro"});
            $(".nav_shangpinS_zxr p span").text("黑色")/* 更改版本颜色或者型号*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(0).find("img").attr("src","image/black_phone2.jpg");/*更改左侧图片第一张路径*/
             $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(1).find("img").attr("src","image/black_phone.jpg");/*更改左侧图片第二张路径*/
             $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(2).find("img").attr("src","image/black_phone1.jpg");/*更改左侧图片第三张路径*/
            $(".wrapp .img1 img").attr("src","image/black_phone2.jpg")/*更改商品主图片路径*/

        })
        /***********div事件 *************/
        $(".nav_right_zxr .nav_shangpinS_zxr .nav_shangpinS_zxr1 a .wphone").click(function(){
            $(this).find("div").css({display:"none"})
            $(this).find("img").css({border:"1px solid red"});
            $(this).parent().siblings().find("div").css({display:"inline-block"});
            $(this).parent().siblings().find("img").css({border:"1px solid gainsboro"});
            $(".nav_shangpinS_zxr p span").text("白色");
             $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(0).find("img").attr("src","image/fang_shubiao1_zxr.jpg");
             $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(1).find("img").attr("src","image/fang_shubiao2_zxr.jpg");
             $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(2).find("img").attr("src","image/fang_shubiao3_zxr.jpg");
            $(".wrapp .img1 img").attr("src","image/fang_shubiao1_zxr.jpg")
        })
        $(".nav_right_zxr .nav_shangpinS_zxr .nav_shangpinS_zxr1 a .bjianpan").click(function(){
            $(this).find("div").css({display:"none"});
            $(this).find("img").css({border:"1px solid red"});
            $(this).parent().siblings().find("div").css({display:"block"});
            $(this).parent().siblings().find("img").css({border:"1px solid gainsboro"});
            $(".nav_shangpinS_zxr p span").text("黑色黑轴")/* 更改版本颜色或者型号*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(0).find("img").attr("src","image/jianpan_zxr.jpg");/*更改左侧图片第一张路径*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(1).find("img").attr("src","image/jianpan2_zxr.jpg");/*更改左侧图片第二张路径*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(2).find("img").attr("src","image/jianpan4_zxr.jpg");/*更改左侧图片第三张路径*/
            $(".wrapp .img1 img").attr("src","image/jianpan_zxr.jpg")/*更改商品主图片路径*/

        })
        $(".nav_right_zxr .nav_shangpinS_zxr .nav_shangpinS_zxr1 a .bqjianpan").click(function(){
            $(this).find("div").css({display:"none"});
            $(this).find("img").css({border:"1px solid red"});
            $(this).parent().siblings().find("div").css({display:"block"});
            $(this).parent().siblings().find("img").css({border:"1px solid gainsboro"});
            $(".nav_shangpinS_zxr p span").text("黑色青轴")/* 更改版本颜色或者型号*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(0).find("img").attr("src","image/jianpan_zxr.jpg");/*更改左侧图片第一张路径*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(1).find("img").attr("src","image/jianpan2_zxr.jpg");/*更改左侧图片第二张路径*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(2).find("img").attr("src","image/jianpan4_zxr.jpg");/*更改左侧图片第三张路径*/
            $(".wrapp .img1 img").attr("src","image/jianpan_zxr.jpg")/*更改商品主图片路径*/

        })
        $(".nav_right_zxr .nav_shangpinS_zxr .nav_shangpinS_zxr1 a .bcjianpan").click(function(){
            $(this).find("div").css({display:"none"});
            $(this).find("img").css({border:"1px solid red"});
            $(this).parent().siblings().find("div").css({display:"block"});
            $(this).parent().siblings().find("img").css({border:"1px solid gainsboro"});
            $(".nav_shangpinS_zxr p span").text("黑色茶轴")/* 更改版本颜色或者型号*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(0).find("img").attr("src","image/jianpan_zxr.jpg");/*更改左侧图片第一张路径*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(1).find("img").attr("src","image/jianpan2_zxr.jpg");/*更改左侧图片第二张路径*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(2).find("img").attr("src","image/jianpan4_zxr.jpg");/*更改左侧图片第三张路径*/
            $(".wrapp .img1 img").attr("src","image/jianpan_zxr.jpg")/*更改商品主图片路径*/

        })
        $(".nav_right_zxr .nav_shangpinS_zxr .nav_shangpinS_zxr1 a .bhjianpan").click(function(){
            $(this).find("div").css({display:"none"});
            $(this).find("img").css({border:"1px solid red"});
            $(this).parent().siblings().find("div").css({display:"block"});
            $(this).parent().siblings().find("img").css({border:"1px solid gainsboro"});
            $(".nav_shangpinS_zxr p span").text("黑色红轴")/* 更改版本颜色或者型号*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(0).find("img").attr("src","image/jianpan_zxr.jpg");/*更改左侧图片第一张路径*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(1).find("img").attr("src","image/jianpan2_zxr.jpg");/*更改左侧图片第二张路径*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(2).find("img").attr("src","image/jianpan4_zxr.jpg");/*更改左侧图片第三张路径*/
            $(".wrapp .img1 img").attr("src","image/jianpan_zxr.jpg")/*更改商品主图片路径*/

        })
        $(".nav_right_zxr .nav_shangpinS_zxr .nav_shangpinS_zxr1 a .wjianpan").click(function(){
            $(this).find("div").css({display:"none"});
            $(this).find("img").css({border:"1px solid red"});
            $(this).parent().siblings().find("div").css({display:"block"});
            $(this).parent().siblings().find("img").css({border:"1px solid gainsboro"});
            $(".nav_shangpinS_zxr p span").text("白色黑轴")/* 更改版本颜色或者型号*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(0).find("img").attr("src","image/baisejianpan_zxr.jpg");/*更改左侧图片第一张路径*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(1).find("img").attr("src","image/baisejianpan2_zxr.jpg");/*更改左侧图片第二张路径*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(2).find("img").attr("src","image/baisejianpan3_zxr.jpg");/*更改左侧图片第三张路径*/
            $(".wrapp .img1 img").attr("src","image/baisejianpan_zxr.jpg")/*更改商品主图片路径*/

        })
        $(".nav_right_zxr .nav_shangpinS_zxr .nav_shangpinS_zxr1 a .wqjianpan").click(function(){
            $(this).find("div").css({display:"none"});
            $(this).find("img").css({border:"1px solid red"});
            $(this).parent().siblings().find("div").css({display:"block"});
            $(this).parent().siblings().find("img").css({border:"1px solid gainsboro"});
            $(".nav_shangpinS_zxr p span").text("白色青轴")/* 更改版本颜色或者型号*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(0).find("img").attr("src","image/baisejianpan_zxr.jpg");/*更改左侧图片第一张路径*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(1).find("img").attr("src","image/baisejianpan2_zxr.jpg");/*更改左侧图片第二张路径*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(2).find("img").attr("src","image/baisejianpan3_zxr.jpg");/*更改左侧图片第三张路径*/
            $(".wrapp .img1 img").attr("src","image/baisejianpan_zxr.jpg")/*更改商品主图片路径*/

        })
        $(".nav_right_zxr .nav_shangpinS_zxr .nav_shangpinS_zxr1 a .wcjianpan").click(function(){
            $(this).find("div").css({display:"none"});
            $(this).find("img").css({border:"1px solid red"});
            $(this).parent().siblings().find("div").css({display:"block"});
            $(this).parent().siblings().find("img").css({border:"1px solid gainsboro"});
            $(".nav_shangpinS_zxr p span").text("白色茶轴")/* 更改版本颜色或者型号*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(0).find("img").attr("src","image/baisejianpan_zxr.jpg");/*更改左侧图片第一张路径*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(1).find("img").attr("src","image/baisejianpan2_zxr.jpg");/*更改左侧图片第二张路径*/
            $(".nav_left_zxr .nav_left_tu_zxr1 a").eq(2).find("img").attr("src","image/baisejianpan3_zxr.jpg");/*更改左侧图片第三张路径*/
            $(".wrapp .img1 img").attr("src","image/baisejianpan_zxr.jpg")/*更改商品主图片路径*/

        })
    })
    $(function(){
        var val= $("input[name='names']").val();
        val=parseInt(val);
        $(".nav_right_zxr .nav_shuliangz_zxr .nav_shuliang_zxr .jia_zxr").click(function(){
            val+=1;
            $("input[name='names']").val(val);
        })
        $(".nav_right_zxr .nav_shuliangz_zxr .nav_shuliang_zxr .jian_zxr").click(function(){

            if(val==0){
                $("input[name='names']").val(0);
            }else {
                val-=1;
                $("input[name='names']").val(val);
            }
        })
        $(".sub_zxr").hover(function(){
            $(this).animate({backgroundColor:"#931925"});
        },function(){
            $(this).animate({backgroundColor:"#be202e"});
        })
    })
        $(function(){
            $(".input_zxr").blur(function(){
                var val= $("input[name='names']").val();
                val=parseInt(val);
            $(".nav_right_zxr .nav_shuliangz_zxr .nav_shuliang_zxr .jia_zxr").click(function(){
                val+=1;
                $("input[name='names']").val(val);
            })
            $(".nav_right_zxr .nav_shuliangz_zxr .nav_shuliang_zxr .jian_zxr").click(function(){

                if(val==0){
                    $("input[name='names']").val(0);
                }else {
                    val-=1;
                    $("input[name='names']").val(val);
                }
            })
            $(".sub_zxr").hover(function(){
                $(this).animate({backgroundColor:"#931925"});
            },function(){
                $(this).animate({backgroundColor:"#be202e"});
            })
        })
        })









/*悬浮定位*/
$(function(){
    $(".dianji .dianji2").click(function(){
        $(this).css({backgroundColor:"#FF6802"});
        $(this).siblings().css({backgroundColor:"gray"})
        $(".canshu .shuai").animate({scrollLeft:"1090"+"px"},500)
    })
    $(".dianji .dianji1").click(function(){
        $(this).css({backgroundColor:"#FF6802"});
        $(this).siblings().css({backgroundColor:"gray"})
        $(".canshu .shuai").animate({scrollLeft:"0"+"px"},500)
    })
})

$(function(){
    $(".bigimg_zxr>img").addClass("concon");
    $(".fird_zxr .main_zxr ul li").eq(0).click(function(){
        //var bb=$("body").scrollTop();
        //alert(bb);
        $(".bigimg_zxr>img").addClass("concon");
        $(".bigimg_zxr>img").css({display:"block"})
        $("body").animate({scrollTop:"900"+"px"},800);
        $(this).css({color:" #be202e"});
        $(this).siblings().css({color:""});
    })
    $(".fird_zxr .main_zxr ul li").eq(1).click(function(){
        $(".bigimg_zxr>img").removeClass("concon");
        $(".bigimg_zxr>img").css({display:"none"})
        $("body").animate({scrollTop:"900"+"px"},800);
        $(this).css({color:" #be202e"});
        $(this).siblings().css({color:""});
    })
    $(".fird_zxr .main_zxr ul li").eq(2).click(function(){
        $(this).css({color:" #be202e"});
        $(this).siblings().css({color:""});
       if($(".bigimg_zxr>img").hasClass("concon")){
            $("body").animate({scrollTop:"10117"+"px"},800);
        }
        else {
           $("body").animate({scrollTop:"1500"+"px"},800);
       }


    })
    $(".fird_zxr .main_zxr ul li").eq(3).click(function() {
        $(this).css({color:" #be202e"});
        $(this).siblings().css({color:""});
        if ($(".bigimg_zxr>img").hasClass("concon")) {
            $("body").animate({scrollTop: "12500" + "px"}, 800);
        }
        else {
            $("body").animate({scrollTop: "3900" + "px"}, 800);
        }
    })
})
$(window).scroll(function(){
    var mm=$(window).scrollTop();
    if(mm>850){
        $(".fird_zxr").css({display:"block"});
        $(".fird_zxr").animate({top:"0"},500);
    }
    else  {
        $(".fird_zxr").css({display:"none"});
    }
});

