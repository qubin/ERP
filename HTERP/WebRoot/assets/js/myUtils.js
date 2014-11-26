var barId = "barId";
var cateId = "cateId";
var goodsId = "goodsId";
var filterPriceId = "filterPriceId";
var hiddenPriceId = "hiddenPriceId";
var priceId = "priceId";
var conntId = "connt";

var barName = "barName";
var cateName = "goods.cateId";
var goodsName = "goodsIds";
var filterPriceName = "filerPrice";
var hiddenPriceName = "goodsPrices";
var priceName = "prices";
var conntName = "connt";

//id分隔符
var ID_SEPRATOR = "-";
/**
 * 表格增加一行
 * @param tbody : 表格中tbody的id
 * @param cateName : 商品分类名称的控件名称
 * @param goodsName : 商品的控件名称
 * @param priceHiddenName : 隐藏的商品价格的控件名称
 * @param priceName : 商品价格的控件名称
 */
function addRow(tbody, sell) {
	var root = document.getElementById(tbody);
	var allRows = root.getElementsByTagName("tr");
	var cRow = allRows[0].cloneNode(true);
	var newaddRow = root.appendChild(cRow);
	//    newaddRow.style.width="100%";
	newaddRow.removeAttribute("style");

	//给新增的控件加上id属性
	//resetSubComponentsId(sell);
	var sel = document.getElementsByName(sell);
	for ( var a = 0; a < sel.length; a++) {
		sel[a].id = sell + a;
	}

}

/**
 * 页面已有多组select控件时，重新设置这些控件的id
 * @param {Object} name1 ： 第一个控件的名称
 * @param {Object} name2 ： 第二个控件的名称
 * @param {Object} name3 ： 第三个控件的名称
 * @param {Object} name4 ： 第四个控件的名称
 */
function resetSubSelectId(name1, name2, name3, name4) {
	var cate = document.getElementsByName(name1);
	var goods = document.getElementsByName(name2);
	var hiddenPrices, prices;
	if (name3 != null) {
		hiddenPrices = document.getElementsByName(name3);
		prices = document.getElementsByName(name4);
	}

	var length = cate.length;
	for ( var i = 1; i < length; i++) {
		var sep = ID_SEPRATOR + i;
		cate[i].id = "cateId" + sep
		goods[i].id = "goodsId" + sep;

		if (name3 != null) {
			hiddenPrices[i].id = "hiddenPricesId" + sep;
		}

		if (name4 != null) {
			prices[i].id = "pricesId" + sep;
		}

	}
}

/**
 * 重置子列表组件的id
 * @param {Object} sell ： 是否为销售，1：销售
 */
function resetSubComponentsId(sell) {
	var cate = document.getElementsByName(cateName);
	var goods = document.getElementsByName(goodsName);
	var bar, filter, hiddenPrice, price, connt;

	if (sell == "1") {
		bar = document.getElementsByName(barName);
		filter = document.getElementsByName(filterPriceName);
		hiddenPrice = document.getElementsByName(hiddenPriceName);
		price = document.getElementsByName(priceName);
		connt = document.getElementsByName(conntName);
	}

	for ( var i = 1; i < cate.length; i++) {
		var sep = ID_SEPRATOR;
		sep = sep + i;
		cate[i].id = cateId + sep;
		goods[i].id = goodsId + sep;

		if (sell == "1") {
			bar[i].id = barId + sep;
			filter[i].id = filterPriceId + sep;
			hiddenPrice[i].id = hiddenPriceId + sep;
			price[i].id = priceId + sep;
			connt[i].id = conntId + sep;
		}
	}

}

/**
 * 
 * @param {Object} id : 商品分类id
 * @param {Object} price ： 价格
 */
function goodsFilter(id, price) {
	if (isNaN(parseFloat(price))) {
		return;
	}
	var index = getComponentIdIndex(id);
	var sep = ID_SEPRATOR + index;

	var cate = document.getElementById(cateId + sep);
	var goods = document.getElementById(goodsId + sep);
	goods.options.length = 0;

	cascadeGoods.getGoodsListByPrice(cate.value, price, function(backData) {
		if (backData == null || backData.length == 0) {
			goods.options.length = 0;
			return;
		}

		goods.add(new Option("--请选择--", "0"));
		for ( var i = 0; i < backData.length; i++) {
			goods.add(new Option(backData[i].goodsName, backData[i].goodsId));
		}
	});
}

/**
 * 获取同一行中，其他空间的id序号
 * @param {Object} id
 * @return {TypeName} 
 */
function getComponentIdIndex(id) {
	return index = id.substr((id.indexOf(ID_SEPRATOR) + 1));
}

/**
 * 商品分类、商品二级联动
 * @param {Object} id ： 主动select控件id
 * @param id2nd: 被动控件的id，列表中可以没有，列表的id是自动生成的
 */
function cascade2(id, id2nd, sell) {
	var mySelect = document.getElementById(id);
	var sltIndex = mySelect.selectedIndex;
	var v1 = mySelect.options[sltIndex].value;

	//获取控件
	var goodsSlt, filter, hiddenPrice, price;
	var index = getComponentIdIndex(id);
	var sep = ID_SEPRATOR + index;

	//获取被动select
	if (id2nd == null || id2nd === undefined) {
		goodsSlt = document.getElementById(goodsId + sep);
	} else {
		goodsSlt = document.getElementById(id2nd);
	}

	//如果是销售则联动价格
	if (sell == "1") {
		hiddenPrice = document.getElementById(hiddenPriceId + sep);
		price = document.getElementById(priceId + sep);
		price.value = "0.00";
		filter = document.getElementById(filterPriceId + sep);
	} else {
		hiddenPrice = null;
		price = null;
		filter = null;
	}

	//未选择商品分类
	if (v1 == "-1") {
		price.value = "0.00";
		goodsSlt.options.length = 0;
		goodsSlt.add(new Option("--请选择--", "0"));
		return;
	}

	cascadeGoods.getGoodsList(v1, function(data) {
		var goods = data;

		//商品联动1
			goodsSlt.options.length = 0;
			goodsSlt.add(new Option("--请选择--", "0"));
			if (goods == null || goods.length == 0) {
				return;
			}

			for ( var i = 0; i < goods.length; i++) {
				goodsSlt.add(new Option(goods[i].goodsName, goods[i].goodsId));
			}

			//价格联动
			//将价格放到一个隐藏的select中
			if (hiddenPrice != null) {
				hiddenPrice.options.length = 0;
				hiddenPrice.add(new Option("0", "0"));
				if (goods == null || goods.length == 0) {
					return;
				}

				for ( var i = 0; i < goods.length; i++) {
					hiddenPrice.add(new Option(goods[i].sellPrice,
							goods[i].goodsId));
				}
			}

			if (price != null) {
				price.value = "0.00";
			}

			if (filter != null) {
				filter.value = "";
			}

		});
}

/**
 * 选择商品，价格联动
 */
function cascade3(goodsId) {
	var goods = document.getElementById(goodsId);
	var index = getComponentIdIndex(goodsId);
	var sep = ID_SEPRATOR + index;

	var hprice = document.getElementById(hiddenPriceId + sep);
	var price = document.getElementById(priceId + sep);

	var value = goods.value;
	for ( var i = 0; i < hprice.options.length; i++) {
		if (hprice.options[i].value == value) {
			value = hprice.options[i].text;

			if (value.indexOf(".") == -1) {
				value = value + ".00";
			}
		}
	}

	if (isNaN(parseFloat(value))) {
		value = "0.00";
	}
	price.value = value;
}

function dele(tt2) {
	var pho = document.getElementById(tt2);
	var pho1 = pho.value;
	rennwalMember.findByphone(pho1, function(data) {
		var member = data;
		if (member == null) {
			alert("该电话号码未注册！");
			document.getElementById("tt").value = "";
			document.getElementById("muuid").value = "";
			document.getElementById("tt1").value = "";
			document.getElementById("leftmoney").value = "";
		}
		document.getElementById("tt").value = member.memberCode;
		document.getElementById("muuid").value = member.memberUuid;
		document.getElementById("tt1").value = member.name;
		document.getElementById("leftmoney").value = member.leftMoney;
	});
}

/**
 * 删除一行
 * @param {Object} tbody ： 表的内容部分包含在tbody之中
 * @param {Object} obj   ： 当前激活事件的控件
 */
function delRow(tbody, obj) {
	var curTr = obj.parentNode.parentNode.parentNode;
	var root = document.getElementById(tbody);
	var allRows = root.getElementsByTagName('tr');
	if (allRows.length > 1) {
		root.removeChild(curTr);
	}
}

/**
 * radioName : radio控件的name
 * modifyBtnId : 修改按钮的id
 * otherBtnId : 其他需要控制的控件id
 * @return 选中的radio的value 
 */
function setButtonUse(radioName, modifyBtnId, otherBtnId) {
	var myRadios = document.getElementsByName(radioName);
	var radioValue;
	var modifyBtn = document.getElementById(modifyBtnId);
	var otherBtn = document.getElementById(otherBtnId);

	for ( var i = 0; i < myRadios.length; i++) {
		if (myRadios[i].checked) {
			index = myRadios[i].value;
			modifyBtn.disabled = "";
			modifyBtn.style.color = "";

			if (otherBtn != null) {
				otherBtn.disabled = "";
				otherBtn.style.color = "";
			}

			break;
		} else {
			modifyBtn.disabled = "disabled";
			modifyBtn.style.color = "gray";

			if (otherBtn != null) {
				modifyBtn.disabled = "disabled";
				modifyBtn.style.color = "gray";
			}
		}
	}

	return index;
}

/**
 * 
 * @param {Object} url : 跳转的url： 必须将查询字符串拼在其后
 * @return {TypeName} 
 */
function showModify(url) {
	var value = setButtonUse();
	if (value == null) {
		return;
	}
	window.location = url + value;
}

/**
 * 条形码输入框离开事件
 * @param {Object} barId ： 条形码id
 * @param {Object} barCode ： 条形码
 * @return {TypeName} 
 */
function laserAway(barId1, barCode, tbody) {
	cascadeGoods.getGoodsByBarCode(barCode, function(goods) {
		if (goods == null) {
			return;
		}
		
		var index = getComponentIdIndex(barId1);
		var myCateId = goods.cateId;
		var sep = ID_SEPRATOR + index;
		document.getElementById(cateId + sep).value = myCateId;
		
		//设置价格
		var price = goods.sellPrice.toString();
		if (price.indexOf(".") == -1) {
				price = price + ".00";
		}
		document.getElementById(priceId + sep).value = price;
		
		//设置商品名称
		var goodsSlt = document.getElementById(goodsId + sep);
		goodsSlt.options.length = 0;
		goodsSlt.add(new Option(goods.goodsName, goods.goodsId));
		
		//设置数量
		document.getElementById(conntId + sep).value = 1;
		
		//增加行数
		addRow(tbody, "1");
		document.getElementById(barId + ID_SEPRATOR +(parseInt(index) + 1) ).focus();
		addprice();
	});
}
/**
 * 类型-名称联动
 * @param obj
 * @return
 */
	function findmate(obj){
		var td = obj.parentNode; 
		var td2 = td.nextElementSibling;
		var sel2 = td2.firstElementChild;
		FIND1.getOne(obj.value, function(data){
		sel2.options.length=0;
			for(var key in data){
				sel2.add(new Option(data[key],key));
			}
		});
		
	}
   /**
	* 验证唯一性 customerCode和retrieveId
	*/
    function retrieveId1(obj){
    	var span=document.getElementById("span1");
        var qqww001=document.getElementById("qqww001");
        var  qq=qqww001.value;
    	var v1=obj.value;
    	FIND1.retrieveId(v1,function(date){
    		if(date==true){
    			span.innerHTML="";
    		}
    		if(date==false){
    			if(v1!=qq){
    			span.innerHTML="出库单编号已存在";
    			span.style.color='red';
    			span.style.fontWeight='bold';
    			}else{
    				span.innerHTML="";
    			}
    			}
    	});
    }
     function retrieveId(obj){
    	var span=document.getElementById("span1");
       
    	var v1=obj.value;
    	FIND1.retrieveId(v1,function(date){
    		if(date==true){
    			span.innerHTML="";
    		}
    		if(date==false){
    			
    			span.innerHTML="出库单编号已存在";
    			span.style.color='red';
    			span.style.fontWeight='bold';
    			};
    			
    	});
    }
    function custoemrCode(obj){
    	var span202=document.getElementById("span202");
    	var v2=obj.value;
    	FIND1.custoemrCode(v2,function(date){
    		if(date==true){
    			span202.innerHTML="";
    		}
    		if(date==false){
    			span202.innerHTML="客户编号已存在";
    			span202.style.color='red';
    			span202.style.fontWeight='bold';
    		}
    	});
    }
     function custoemrCode1(obj){
    	var span202=document.getElementById("span202");
    	var qqww002=document.getElementById("qqww002");
    	var qq=qqww002.value;
    	var v2=obj.value;
    	FIND1.custoemrCode(v2,function(date){
    		if(date==true){
    			span202.innerHTML="";
    		}
    		if(date==false){
    			if(v2!=qq){
    			span202.innerHTML="客户编号已存在";
    			span202.style.color='red';
    			span202.style.fontWeight='bold';
    			}else{
    				span202.innerHTML="";
    			}
    		}
    	});
    }
   /**
	* 联动选择customerCode显示name和phone
	*/
    function codeAndNameAndPhone(obj){
    	var vs002=document.getElementById("vs002");
    	var vs003=document.getElementById("vs003");
    	var vs1=obj.value;
    	FIND1.findCustomerById(vs1,function(date){
    		vs002.value=date.name;
    		vs003.value=date.cellPhone1;
    		vs002.disabled="disabled";
    	});
    	
    }
    /**
	* 确认出库数量是否够
	*/
    function shuliang(obj){
    	var v1=obj.parentNode.parentNode.firstElementChild.firstElementChild.value;
    	var v2=obj.parentNode.parentNode.firstElementChild.nextElementSibling.firstElementChild.value; 
    	var span=obj.nextElementSibling;   
    	var v=obj.value;
    	FIND1.selectInventory(v1,v2,function(date){
    		if(date-v<0){
    		    span.innerHTML="库存不够!库存还有:"+date;
    		}else{
    		    span.innerHTML="";
    		}
    	});
    }
     /**
	* 确认是否可以输入价格
	*/
    
    function priceQueren(obj){
     var v1=obj.value;
     var price=obj.parentNode.nextElementSibling.nextElementSibling.firstElementChild; 
     
     if(v1!=2){
       // price.setAttribute("readOnly",true);
    	 price.value='';
    	 price.readOnly=true;
     }else{
      // price.setAttribute("readOnly",false);
    	 price.readOnly=false;
     }
    }
    /**
	* 确认出库单维护反显时价格栏是否可以输入
	*/
   function one(){  
    var item=document.getElementsByName("cateId");  
     for(var i=1;i<item.length;i++){
       if(item[i].value!=2){
         var ss=item[i].parentNode.nextElementSibling.nextElementSibling.firstElementChild;
        
         ss.readOnly=true;
      }else{
        item[i].parentNode.nextElementSibling.nextElementSibling.firstElementChild.readOnly=false;
      }
      
    }
}  




    