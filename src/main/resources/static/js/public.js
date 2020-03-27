// 声明一个全局对象Namespace，用来注册命名空间
var Namespace = new Object();

//全局对象仅仅存在register函数，参数为名称空间全路径，如"Grandsoft.GEA"
Namespace.register = function(fullNS){
    // 将命名空间切成N部分, 比如Grandsoft、GEA等
    var nsArray = fullNS.split('.');
    var sEval = "";
    var sNS = "";
    for (var i = 0; i < nsArray.length; i++)
    {
        if (i != 0) sNS += ".";
        sNS += nsArray[i];
        // 依次创建构造命名空间对象（假如不存在的话）的语句
        // 比如先创建Grandsoft，然后创建Grandsoft.GEA，依次下去
        sEval += "if (typeof(" + sNS + ") == 'undefined') " + sNS + " = new Object();"
    }
    if (sEval != "") eval(sEval);
};
Namespace.register("Angel");
var isIe8 = !(typeof(ie8) == "undefined");

var $curmenu,lastIndex;//最后弹窗索引
var webHistory = Webit.history;

$(function(){
	var aMenu = $("#nav li ul li");
	var $main_content = $("#fill-main-content");
	aMenu.on("click",function(){
		var href = $(this).find("a").attr('href');
		$main_content.empty();
		$.ajax({
			type: "GET",
			url: href,
			success: function(str, action, token) {
			/*	$main_content.load(href, data, function(){
					//alert("The last 25 entries in the feed have been loaded");
				});*/
				 $main_content.load(href);
				// $main_content.html(loadHtmlPage(str));
				   var curMenu = $("#nav li").find("a[href='"+token+"']");
				   changeMenu(curMenu);
				 //  changeMenu(curMenu);
			}
		});
		//阻止跳转
		$(this).parents('li').addClass('active').siblings('li').removeClass('active');
		return false;
	});
	
	//属性模式
	$(document).on('click','[data-mode]',function(){
		var data = $(this).data();
		if(undefined != data['data'] && typeof data['data'] != "object") {
			data['data'] = eval("("+data.data+")");
		}
		$.cuslayer(data);
	});
	
});

;(function($){
	var cuslayer = function(params){
		var defaults = {
			mode:'normal',
			type:1, //0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
			title:false,
			shade: [0.5, '#000'], //[遮罩透明度, 遮罩颜色]
			border:[3, 0.5, '#666'],
			closeBtn:[0, true],
			url:undefined, //请求回来弹窗的url
			data:{}, //请求弹窗携带的参数
			maxmin:true, //是否输出窗口最小化/全屏/还原按钮。 
			width:'0',
			height:'0',
			btns:2,
			btn:['确 定','取 消'],
			msg:'',
			reloadurl:false //是否url刷新,默认false当前右侧刷新
		};
		
		params = $.extend(defaults, params);
		
		var mode = params.mode;
		if(undefined != params.closebtn){
			params.closeBtn = params.closebtn;
		}
		
		if (mode == 'xjdel' || mode == 'del' || mode == 'delete' || mode == 'page'
			|| mode == 'delSelect' || mode == 'detail') {
			if(undefined == params.url) {
				alert("请求url未填写");
				return false;
			}
		}
		if (mode == 'xjdel' || mode == 'del' || mode == 'delete' || mode == 'delSelect') {
			// 处理delSelect情况，删除bootstrap table数据
			if (mode == 'delSelect') {
				var rows = $('#' + params.table).bootstrapTable("getAllSelections");
				if (rows.length <= 0) {
					layer.msg('<span class="red bigger-120">请选择需要操作的数据</span>');
					return;
				} else {
					var ids = [];
					for (var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					params.data = {
						ids : ids
					};
				}
			}
			
			var loadi;
			layer.confirm(params.msg,function(index){
				$.ajax({
					url:params.url,
					data:params.data,
					type:'post',
					beforeSend:function(){
						loadi = layer.load(5,0);
					}
				}).done(function(data){
					layer.close(loadi);
        			if(data>0) {
        				if(params.success == undefined){
        					layer.msg('<span class="red bigger-120">操作成功</span>', 1, 1,function(){
        						if(mode == 'xjdel'){
        							var dateType = $("#dateType").val();
        							var typeName = $("#typeName").val();
        							show(dateType,typeName);
        						}else{
        							if(params.reloadurl){
                						location.reload();
                					}else{
                						//$curmenu.trigger('click');
                						if(params.callback != undefined) {
                							if(typeof params.callback === "string"){
                								eval(params.callback)
                							}else{
                								params.callback();
                							}
                						}else{
                							$curmenu.trigger('click');
                						}
                					}
        						}
            				});
        				}else{
        					params.success();
        				}
        			}else if(data<0){
        				layer.alert('<span class="red bigger-120">删除失败，数据正在被使用！</span>', 8, !1);
        			}
        		}).fail(function(error){
        			layer.msg('<span class="red bigger-120">删除失败</span>', 2, 8);
        		});
			},params.title);
			return false;
		}
		if(mode == 'page' || mode == 'detail'){
			var loadi; //加载窗
			var oheight,owidth;
			$.ajax({
				url:params.url,
				data:params.data,
				type:'post',
				dataType:'html',
				beforeSend:function(){
					loadi = layer.load(5,0);
				}
			}).done(function(data){
				var layerObj; //弹窗
				var increment = 36,dheight = params.height,dwidth = params.width;
				var _scrollHeight = $(document).scrollTop();
				var _windowHeight = $(window).height();
				var _windowWidth = $(window).width();
				var borderWidth = params.borderwidth==undefined?4:params.borderwidth;
				layer.close(loadi); //关闭加载框
				lastIndex = $.layer({
				    type : 1,
				    title : params.title,
				    maxmin: params.maxmin,
				    closeBtn: params.closeBtn,
				    //area : ['99%','100%'],
				    border:[borderWidth, 0.5, '#888'],
				    page : {html:data},
				    success:function(layero){
				    	layerObj = layero;
				    },
				    full:function(layero){
				    	layero.find('.xuboxPageHtml').css({'height':_windowHeight-increment-(borderWidth*2)});
				    },
				    restore: function(layero){
				    	layerObj.css({
				    		'width':owidth,
				    		'height':oheight
				    	});
				    	layerObj.find(".xubox_main").css({
				    		'width':owidth,
				    		'height':oheight
				    	});
				    	layero.find('.xuboxPageHtml').css({'height':oheight-increment});
				    	layerObj.find(".xubox_border").width(owidth+8).height(oheight+8);
				    },
				    close: function(index){
				    	layer.closeTips();
				    }
				});
				var saveTag = layerObj.find('div[tag-save-btn]');
				if(saveTag.length > 0) increment = 36*2;
				oheight = layerObj.find("div.layer").outerHeight()+increment;
				owidth = layerObj.find("div.layer").outerWidth();
				if(oheight>_windowHeight) oheight = _windowHeight;
				if(owidth>_windowWidth) owidth = _windowWidth;
				//默认设置
				if(dheight!=0 && dheight!="") { //显式的指定高度情况
					var bf = dheight.indexOf('%');
					if(bf != -1) { //百分比
						oheight = parseInt($.trim(dheight.substring (0,bf)))/100.0 * _windowHeight;
					}else{ //px
						var px = dheight.indexOf('px');
						oheight = parseInt($.trim(dheight.substring (0,px)));
					}
				}
				if(dwidth!=0 && dwidth!="") {//显式的指定宽度情况
					var bf = dwidth.indexOf('%');
					if(bf != -1){
						owidth = parseInt($.trim(dwidth.substring (0,bf)))/100.0 * _windowWidth;
					}else{
						var px = dwidth.indexOf('px');
						owidth = parseInt($.trim(dwidth.substring (0,px)));
					}
				}else{
					//owidth = 0.46 * _windowWidth;
				}
				var _posiTop = _posiLeft = 0;
				if(oheight != _windowHeight){
					_posiTop = (_windowHeight - oheight-8)/2;
				}else{
					oheight = _windowHeight-8;
				}
				if(owidth != _windowWidth){
					_posiLeft = (_windowWidth - owidth-8)/2;
				}else{
					owidth = _windowWidth-8;
				}
				
				layer.area(lastIndex, {width:owidth,height:oheight,top:_posiTop,left:_posiLeft});
		    	
				var bottom = '0px';
				if(saveTag.length > 0) {
					bottom = '-36px';
				}
		    	layerObj.find('.xuboxPageHtml').css({
		    		'overflowY':'auto',
		    		'height':layerObj.height()-increment,
		    	});
		    	saveTag.css({'bottom':bottom});
		    	layerObj.find(".xubox_page").css({width:'100%'});
		    	
			}).fail(function(err){
				layer.msg('操作失败', 2, 8);
			});
		}
		
	};
	$.cuslayer = cuslayer;
})(jQuery);

//文件下载
function doloadfile(fileName,fileUrl){
 	var url = "download/file?fileUrl="+fileUrl+"&fileName="+fileName;
 	window.open(url).focus();
}

function loadHtmlPage(path) {
    //path = "/base" + "/" + path;
    var result;
    $.ajax({
        url: path,
        dataType: "text",
        async: false,
        success: function(data) {
            result = data;
        }
    });
    return result;
};

function changeMenu(obj){
	$this = $curmenu = obj,pli = $this.parents("li");
	var $sibling = $this.closest("li[data-level='1']").siblings("li.open");
	if($sibling.size()>0){
		$sibling.removeClass("open").find("li.open").removeClass("open");
		$sibling.find("ul.nav-show").attr("class","submenu nav-hide").hide();
	}
	if($this.attr('haschild') == "false"){
		$this.closest("li[data-level='1']").addClass("open");
		var pul = $this.parents("ul.submenu");
		pul.attr("class","submenu nav-show").show();
		$("#nav").find("li").removeClass("active");
		pli.addClass("active");
		//$(".page-header h1").text($this.find(">span").text());

		var menuArray = [];
		var cur = $this.find(".menu-text").text();
		menuArray.push(cur);

		pli = $this.parent().parent().parent();
		while(pli.data("level") >= 1){
			var ts = pli.find(".dropdown-toggle .menu-text");
			if(ts.length > 1){
				cur = $(ts[0]).text();
			}else{
				cur = $(ts).text();
			}
//			if(cur.indexOf("审批流程")!=-1){
//				cur="审批申请";
//			}
			pli = pli.parents("li");
			menuArray.push(cur);
		}
		var crumb = $("#breadcrumb");
		crumb.html("");
		crumb.append('<li><i class="ace-icon fa fa-home home-icon"></i><a href="'+ctxPath+'" >首页</a></li>')

		for(var i = menuArray.length - 1 ; i >= 0;i-- ){
			if(i == 0){
				crumb.append('<li class="active"> '+menuArray[i]+' </li>');
			}else{
				crumb.append('<li> <a href="#">'+menuArray[i]+'</a> </li>');
			}
		}
		
		//
		var $historyM = $("#historyMenu");
		$historyM.find("li").show();

		var href = $this.attr("href");
		var $li = $historyM.find("li[h='"+href+"']");
		if($li.length > 0){
			$li.remove();
		}


		var html = '<li h="'+href +'" >'+
						'<a href="javascript:void(0)" >'+
							'<span class="mail-tag badge badge-success "></span>'+
							'<span class="success">'+$this.find(".menu-text").text()+'</span>'+
						'</a>'+
					'</li>'
		
		$li = $historyM.find("li");
		if($li.length <= 0){
			$historyM.append(html);
		}else{
			$historyM.find("li:first").before(html);
		}
		$historyM.find("li:first").hide();
		if($historyM.find("li").length > 1){
			$historyM.parents(".widget-toolbar").show();
		}else{
			$historyM.parents(".widget-toolbar").hide();
		}

	}
}

function getTotalList(formid,searchbtn){
	 $("#"+formid)[0].reset();
	 $("#"+formid).getPageList({'submitBtnId':searchbtn});
}
//分页
function
paging(formId,pageNo){
	var $form = $("#"+formId),$target = $("#"+$form.attr('target')),spinner;
	var pageNoInput = $form.find('input[name="pageNum"]');
	var pageSize = $form.find('input[name="pageSize"]');
	if(pageNoInput.size() == 0){
		$form.append("<input type='hidden'  name = 'pageNum' value='1'/>");
		pageNoInput = $form.find('input[name="pageNum"]');
	}
	if(pageSize.size() == 0){
		$form.append("<input type='hidden'  name = 'pageSize' value='10'/>");
	}
	pageNoInput.val(pageNo);
	$.ajax({
		url:$form.attr('action'),
		type:'post',
		dataType:'html',
		data:$form.serialize(),
	}).done(function(data){
		if ($target) {
			$target.stop();
		}
		$target.html(data);
	}).fail(function(error){
		alert("请求超时...!");
		top.location.href = ctxPath;
	})
	return false;
};
//条件查询分页
;(function($){
	$.fn.getPageList = function(settings){
		return this.each(function(){
			var $this = $(this);
			this.opt = $.extend({},$.fn.getPageList.defaults,settings);
			$("#"+this.opt.submitBtnId).on('click',function(){
				paging($this.attr("id"),1);
				return false;
			});
			if(this.opt.trigger) $("#"+this.opt.submitBtnId).trigger('click');
		});
	}
	
	$.fn.getPageList.defaults = {
		submitBtnId:"", //提交按钮
		trigger:true 
	}
})(jQuery);
/***********************页面通用方法***********************************/
function getTotalList(formid,searchbtn){
	 $("#"+formid)[0].reset();
	 $("#"+formid).getPageList({'submitBtnId':searchbtn});
}
function getCenterHeight(){
	return $(window).height() - topHeight;
}

function getCenterWidth(){
	$(window).width() - $("#sidebar").width();
}

//刷新url
function reloadUrl(){
	window.location.href = (window.location.href).split("?")[0]+"?menuid="+$curmenu.attr("id");
}

(function(jQuery){ 

	if(jQuery.browser) return; 

	jQuery.browser = {}; 
	jQuery.browser.mozilla = false; 
	jQuery.browser.webkit = false; 
	jQuery.browser.opera = false; 
	jQuery.browser.msie = false; 

	var nAgt = navigator.userAgent; 
	jQuery.browser.name = navigator.appName; 
	jQuery.browser.fullVersion = ''+parseFloat(navigator.appVersion); 
	jQuery.browser.majorVersion = parseInt(navigator.appVersion,10); 
	var nameOffset,verOffset,ix; 

	// In Opera, the true version is after "Opera" or after "Version" 
	if ((verOffset=nAgt.indexOf("Opera"))!=-1) { 
	jQuery.browser.opera = true; 
	jQuery.browser.name = "Opera"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+6); 
	if ((verOffset=nAgt.indexOf("Version"))!=-1) 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+8); 
	} 
	// In MSIE, the true version is after "MSIE" in userAgent 
	else if ((verOffset=nAgt.indexOf("MSIE"))!=-1) { 
	jQuery.browser.msie = true; 
	jQuery.browser.name = "Microsoft Internet Explorer"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+5); 
	} 
	// In Chrome, the true version is after "Chrome" 
	else if ((verOffset=nAgt.indexOf("Chrome"))!=-1) { 
	jQuery.browser.webkit = true; 
	jQuery.browser.name = "Chrome"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+7); 
	} 
	// In Safari, the true version is after "Safari" or after "Version" 
	else if ((verOffset=nAgt.indexOf("Safari"))!=-1) { 
	jQuery.browser.webkit = true; 
	jQuery.browser.name = "Safari"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+7); 
	if ((verOffset=nAgt.indexOf("Version"))!=-1) 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+8); 
	} 
	// In Firefox, the true version is after "Firefox" 
	else if ((verOffset=nAgt.indexOf("Firefox"))!=-1) { 
	jQuery.browser.mozilla = true; 
	jQuery.browser.name = "Firefox"; 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+8); 
	} 
	// In most other browsers, "name/version" is at the end of userAgent 
	else if ( (nameOffset=nAgt.lastIndexOf(' ')+1) < 
	(verOffset=nAgt.lastIndexOf('/')) ) 
	{ 
	jQuery.browser.name = nAgt.substring(nameOffset,verOffset); 
	jQuery.browser.fullVersion = nAgt.substring(verOffset+1); 
	if (jQuery.browser.name.toLowerCase()==jQuery.browser.name.toUpperCase()) { 
	jQuery.browser.name = navigator.appName; 
	} 
	} 
	// trim the fullVersion string at semicolon/space if present 
	if ((ix=jQuery.browser.fullVersion.indexOf(";"))!=-1) 
	jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix); 
	if ((ix=jQuery.browser.fullVersion.indexOf(" "))!=-1) 
	jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix); 

	jQuery.browser.majorVersion = parseInt(''+jQuery.browser.fullVersion,10); 
	if (isNaN(jQuery.browser.majorVersion)) { 
	jQuery.browser.fullVersion = ''+parseFloat(navigator.appVersion); 
	jQuery.browser.majorVersion = parseInt(navigator.appVersion,10); 
	} 
	jQuery.browser.version = jQuery.browser.majorVersion; 
})(jQuery); 

//提示tip
var tip={
	errorTip:function(msg,obj,style){
		style = style == undefined?['background-color:#F26C4F; color:#fff','#F26C4F' ]:style;
		layer.tips(msg, obj, {
			guide:0,
			time: 4,
			style : style
		});
	}
};

/****************time ago************************/

(function (factory) {
	  if (typeof define === 'function' && define.amd) {
	    // AMD. Register as an anonymous module.
	    define(['static/js/jquery'], factory);
	  } else {
	    // Browser globals
	    factory(jQuery);
	  }
	}(function ($) {
	  $.timeago = function(timestamp) {
	    if (timestamp instanceof Date) {
	      return inWords(timestamp);
	    } else if (typeof timestamp === "string") {
	      return inWords($.timeago.parse(timestamp));
	    } else if (typeof timestamp === "number") {
	      return inWords(new Date(timestamp));
	    } else {
	      return inWords($.timeago.datetime(timestamp));
	    }
	  };
	  var $t = $.timeago;
	 
	  $.extend($.timeago, {
	    settings: {
	      refreshMillis: 60000,
	      allowFuture: false,
	      localeTitle: false,
	      cutoff: 0,
	      strings: {
	        prefixAgo: null,
	        prefixFromNow: null,
	        suffixAgo: "前",
	        suffixFromNow: "from now",
	        seconds: "1分钟",
	        minute: "1分钟",
	        minutes: "%d分钟",
	        hour: "1小时",
	        hours: "%d小时",
	        day: "1天",
	        days: "%d天",
	        month: "1月",
	        months: "%d月",
	        year: "1年",
	        years: "%d年",
	        wordSeparator: "",
	        numbers: []
	      }
	    },
	    inWords: function(distanceMillis) {
	      var $l = this.settings.strings;
	      var prefix = $l.prefixAgo;
	      var suffix = $l.suffixAgo;
	      if (this.settings.allowFuture) {
	        if (distanceMillis < 0) {
	          prefix = $l.prefixFromNow;
	          suffix = $l.suffixFromNow;
	        }
	      }
	 
	      var seconds = Math.abs(distanceMillis) / 1000;
	      var minutes = seconds / 60;
	      var hours = minutes / 60;
	      var days = hours / 24;
	      var years = days / 365;
	 
	      function substitute(stringOrFunction, number) {
	        var string = $.isFunction(stringOrFunction) ? stringOrFunction(number, distanceMillis) : stringOrFunction;
	        var value = ($l.numbers && $l.numbers[number]) || number;
	        return string.replace(/%d/i, value);
	      }
	 
	      var words = seconds < 45 && substitute($l.seconds, Math.round(seconds)) ||
	        seconds < 90 && substitute($l.minute, 1) ||
	        minutes < 45 && substitute($l.minutes, Math.round(minutes)) ||
	        minutes < 90 && substitute($l.hour, 1) ||
	        hours < 24 && substitute($l.hours, Math.round(hours)) ||
	        hours < 42 && substitute($l.day, 1) ||
	        days < 30 && substitute($l.days, Math.round(days)) ||
	        days < 45 && substitute($l.month, 1) ||
	        days < 365 && substitute($l.months, Math.round(days / 30)) ||
	        years < 1.5 && substitute($l.year, 1) ||
	        substitute($l.years, Math.round(years));
	 
	      var separator = $l.wordSeparator || "";
	      if ($l.wordSeparator === undefined) { separator = " "; }
	      return $.trim([prefix, words, suffix].join(separator));
	    },
	    parse: function(iso8601) {
	      var s = $.trim(iso8601);
	      s = s.replace(/\.\d+/,""); // remove milliseconds
	      s = s.replace(/-/,"/").replace(/-/,"/");
	      s = s.replace(/T/," ").replace(/Z/," UTC");
	      s = s.replace(/([\+\-]\d\d)\:?(\d\d)/," $1$2"); // -04:00 -> -0400
	      return new Date(s);
	    },
	    datetime: function(elem) {
	      var iso8601 = $t.isTime(elem) ? $(elem).attr("datetime") : $(elem).attr("title");
	      return $t.parse(iso8601);
	    },
	    isTime: function(elem) {
	      // jQuery's `is()` doesn't play well with HTML5 in IE
	      return $(elem).get(0).tagName.toLowerCase() === "time"; // $(elem).is("time");
	    }
	  });
	 
	  // functions that can be called via $(el).timeago('action')
	  // init is default when no action is given
	  // functions are called with context of a single element
	  var functions = {
	    init: function(){
	      var refresh_el = $.proxy(refresh, this);
	      refresh_el();
	      var $s = $t.settings;
	      if ($s.refreshMillis > 0) {
	        setInterval(refresh_el, $s.refreshMillis);
	      }
	    },
	    update: function(time){
	      $(this).data('timeago', { datetime: $t.parse(time) });
	      refresh.apply(this);
	    },
	    updateFromDOM: function(){
	      $(this).data('timeago', { datetime: $t.parse( $t.isTime(this) ? $(this).attr("datetime") : $(this).attr("title") ) });
	      refresh.apply(this);
	    }
	  };
	 
	  $.fn.timeago = function(action, options) {
	    var fn = action ? functions[action] : functions.init;
	    if(!fn){
	      throw new Error("Unknown function name '"+ action +"' for timeago");
	    }
	    // each over objects here and call the requested function
	    this.each(function(){
	      fn.call(this, options);
	    });
	    return this;
	  };
	 
	  function refresh() {
	    var data = prepareData(this);
	    var $s = $t.settings;
	 
	    if (!isNaN(data.datetime)) {
	      if ( $s.cutoff == 0 || distance(data.datetime) < $s.cutoff) {
	        $(this).text(inWords(data.datetime));
	      }
	    }
	    return this;
	  }
	 
	  function prepareData(element) {
	    element = $(element);
	    if (!element.data("timeago")) {
	      element.data("timeago", { datetime: $t.datetime(element) });
	      var text = $.trim(element.text());
	      if ($t.settings.localeTitle) {
	        element.attr("title", element.data('timeago').datetime.toLocaleString());
	      } else if (text.length > 0 && !($t.isTime(element) && element.attr("title"))) {
	        element.attr("title", text);
	      }
	    }
	    return element.data("timeago");
	  }
	 
	  function inWords(date) {
	    return $t.inWords(distance(date));
	  }
	 
	  function distance(date) {
	    return (new Date().getTime() - date.getTime());
	  }
	 
	  // fix for IE6 suckage
	  document.createElement("abbr");
	  document.createElement("time");
}));