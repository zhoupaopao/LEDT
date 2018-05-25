/*计算大小*/


/*弹出框/模态框(h5)*/
function modal(obj){
    "use strict";
    this.modal=obj||console.log("未传obj");
    this.modalContent=obj.querySelector(".modal-content");
    this.rem=parseFloat(getComputedStyle(document.querySelector("html"),false).fontSize);
    var _this=this;
    this.modal.addEventListener("touchstart",function(ev){
        var oSrc=ev.srcElement||ev.target;
        if(!_this.modalContent.contains(oSrc)){
            _this.hide(ev);
        }
        ev.preventDefault();
        return false;               
    },false);
}
modal.prototype={
    constructor:modal,
    hide:function(ev){
        this.modal.style.display="none";
    },
    show:function(){
        this.modal.style.display="block";
    }
}
/*滑动选框*/
function slideModal(obj){
    modal.call(this,obj);
    this.oUl=obj.querySelector(".modal-option");
    this.aLi=this.oUl.children;
    this.y=0;//Y轴便宜距离
    this.disY=0;
    var  _this=this;//构造函数
    this.oUl.addEventListener("touchstart",function(ev){               
        _this.drop(ev);
    },false)

}
/*指正构造函数和继承modal构造函数的方法*/
inheritPrototype(modal,slideModal);

slideModal.prototype.drop=function(ev){
    this.disY=ev.targetTouches[0].pageY/this.rem-this.y; 
    this.oUl.style.transition="none";
    var start1=ev.targetTouches[0].pageY,
        start2=0,
        _this=this,//构造函数      
        lastY=0,
        speedY=0,
        /*向上最大偏移量*/
        maxY=this.oUl.offsetHeight/this.rem-this.oUl.parentNode.offsetHeight/this.rem+0.4;    
    function fnMove(ev){
        /*记录偏移距离*/        
        _this.y = ev.targetTouches[0].pageY/_this.rem - _this.disY;
        /*到达最大偏移量就==最大偏移量，向上为负数*/ 
        restriction(); 
        _this.oUl.style.transform="translateY("+_this.y+"rem)";
        start2=ev.targetTouches[0].pageY;
        /*求速度*/
        speedY = _this.y-lastY;
        lastY=_this.y;
               
    }
    function fnEnd(ev){
        /*解除事件绑定，不然会多次运行*/
        _this.oUl.removeEventListener("touchmove",fnMove,false);
        _this.oUl.removeEventListener("touchend",fnEnd,false);

        _this.oUl.style.transition="transform 0.7s ease-out";         
       var disMove=start2-start1;//判断移动方向
       /*惯性移动，小于0为上*/
        disMove<0?(_this.y=_this.y+speedY*5):(_this.y=_this.y+speedY*5);
        restriction(); 
        if(_this.y>0.2){          
          _this.oUl.style.transform="translateY(0rem)"; 
          _this.y=0;
        }else if(-_this.y>maxY){
          _this.y=-maxY;
        }
        _this.oUl.style.transform="translateY("+_this.y+"rem)"; 
    }
    function restriction(){
        -_this.y>maxY?_this.y=-maxY-0.5:null; //下拉限制
        _this.y>0.5?_this.y=0.5:null; 
    }
    this.oUl.addEventListener("touchmove",fnMove,false);
    this.oUl.addEventListener("touchend",fnEnd,false);
    ev.preventDefault();
    return false;
}
