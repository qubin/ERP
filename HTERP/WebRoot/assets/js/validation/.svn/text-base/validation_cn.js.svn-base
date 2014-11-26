/**
 */

/*
 * Really easy field validation with Prototype
 * http://tetlaw.id.au/view/blog/really-easy-field-validation-with-prototype
 * Andrew Tetlaw
 * Version 1.5.3 (2006-07-15)
 * 
 * Copyright (c) 2006 Andrew Tetlaw
 * http://www.opensource.org/licenses/mit-license.php
 */
Validator = Class.create();

Validator.messagesSourceEn = [
	['validation-failed' , 'Validation failed.'],
	['required' , 'This is a required field.'],
	['validate-number' , 'Please enter a valid number in this field.'],
	['validate-digits' , 'Please use numbers only in this field. please avoid spaces or other characters such as dots or commas.'],
	['validate-specialchar','Please input the special characters'],
	['validate-alpha' , 'Please use letters only (a-z) in this field.'],
	['validate-alphanum' , 'Please use only letters (a-z) or numbers (0-9) only in this field. No spaces or other characters are allowed.'],
	['validate-date' , 'Please enter a valid date.'],
	['validate-email' , 'Please enter a valid email address. For example fred@domain.com .'],
	['validate-url' , 'Please enter a valid URL.'],
	['validate-date-au' , 'Please use this date format: dd/mm/yyyy. For example 17/03/2006 for the 17th of March, 2006.'],
	['validate-currency-dollar' , 'Please enter a valid $ amount. For example $100.00 .'],
	['validate-one-required' , 'Please select one of the above options.'],
	['validate-date-cn' , 'Please use this date format: yyyy-mm-dd. For example 2006-03-16.'],
	['validate-integer' , 'Please enter a valid integer in this field'],
	['min-value' , 'min value is %s.'],
	['max-value' , 'max value is %s.'],
	['min-length' , 'min length is %s,current length is %s.'],
	['max-length' , 'max length is %s,current length is %s.'],
	['validate-int-range' , 'Please enter integer value between %s and %s'],
	['validate-float-range' , 'Please enter number between %s and %s'],
	['validate-length-range' , 'Please enter value length between %s and %s,current length is %s'],
	['validate-file' , 'Please enter file type in [%s]'],
	['validate-pattern' , 'Validation failed.'],
	['validate-chinese','Please enter chinese'],
	['validate-ip','Please enter a valid IP address'],
	['validate-phone','Please enter a valid phone number,current length is %s.'],
	['validate-mobile-phone','Please enter a valid mobile phone,For example 13910001000.current length is %s.'],
	['validate-equals','Conflicting with above value.']
	['less-than','Input value must be less than above value.'],
	['great-than','Input value must be great than above value.'],
	['lessequal-than', 'must less or equal than above value.']
]

Validator.messagesSourceCn = [
	['validation-failed' , '验证失败.'],
	['required' , '不能为空.'],
	['validate-number' , '请输入有效的数字.'],
	['validate-specialchar','请输入非特殊的字符'],
	['validate-digits' , '请输入一个数字. 避免输入空格],逗号,分号等字符'],
	['validate-alpha' , '请输入英文字母.'],
	['validate-alphanum' , '请输入英文字母或是数字,其它字符是不允许的.'],
	['validate-date' , '请使用这样的日期格式: yyyy/mm/dd. 例如:2008/08/08.'],
	['validate-email' , '请输入有效的邮件地址,如 username@example.com.'],
	['validate-url' , '请输入有效的URL地址.'],
	['validate-date-au' , 'Please use this date format: dd/mm/yyyy. For example 17/03/2006 for the 17th of March, 2006.'],
	//['validate-currency-dollar' , '您填的金额格式不对 Please enter a valid $ amount. For example $100.00 .'],
	['validate-currency-dollar' , '您填的金额格式不对,例如：100.09'],
	['validate-one-required' , '在上面选项至少选择一个.'],
	['validate-date-cn' , '请使用这样的日期格式: yyyy-mm-dd. 例如:2008-08-08.'],
	['validate-integer' , '请输入正确的整数'],
	['min-value' , '最小值为%s'],
	['max-value' , '最大值为%s'],
	['min-length' , '最小长度为%s,当前长度为%s.'],
	['max-length', '最大长度为%s,当前长度为%s.'],
	['validate-int-range' , '输入值应该为 %s 至 %s 的整数'],
	['validate-float-range' , '输入值应该为 %s 至 %s 的数字'],
	['validate-length-range' , '输入值的长度应该在 %s 至 %s 之间,当前长度为%s'],
	['validate-file' , '文件类型应该为[%s]其中之一'],
	['validate-pattern' , '输入的值不匹配'],
	['validate-chinese','请输入中文'],
	['validate-ip','请输入正确的IP地址'],
	['validate-tel','请输入正确的电话号码,如:029-88888888,当前长度为%s.'],
	['validate-tel-phone','请输入正确的电话号码或手机号码'],
	['validate-mobile-phone','请输入正确的手机号码,当前长度为%s.'],
	['validate-equals','与上面不一至,请重新输入'],
	['validate-passequals','确认密码与密码不一致,请重新输入'],
	['validate-postcode','请输入正确的邮编，如 310000'],
	['less-than','应该小于前面的值'],
	['great-than','应该大于前面的值'],
	['lessequal-than', '应该小于等于前面的值'],
	['less-thantime','您选择的时间应该小于当前时间'],
	['great-thantime','您选择的时间应该大于当前时间'],
	['validate-qq','请输入有效的QQ号码.'],
	['validate-id-number','公民身份证号不合法'],
	['validate-id-numberNew','公民身份证号不合法'],
	['validate-id-numberNew1','公民身份证号不合法'],
	['validate-selection','请选择一个可选项'],
	 ['validate-wangzhi' , '请输入正确的网站地址.']
]
ValidationUtils = {
	isVisible : function(elm) {
		while(elm && elm.tagName != 'BODY') {
			if(!$(elm).visible()) return false;
			elm = elm.parentNode;
		}
		return true;
	},
	getReferenceForm : function(elm) {
		while(elm && elm.tagName != 'BODY') {
			if(elm.tagName == 'FORM') return elm;
			elm = elm.parentNode;
		}
		return null;
	},
	getInputValue : function(elm) {
		var elm = $(elm);
		if(elm.type.toLowerCase() == 'file') {
			return elm.value;
		}else {
			return $F(elm);
		}
	},
	getElmID : function(elm) {
		return elm.id ? elm.id : elm.name;
	},
	format : function(str,args) {
		args = args || [];
		ValidationUtils.assert(args.constructor == Array,"ValidationUtils.format() arguement 'args' must is Array");
		var result = str
		for (var i = 0; i < args.length; i++){
			result = result.replace(/%s/, args[i]);	
		}
		return result;
	},
	// 通过classname传递的参数必须通过'-'分隔各个参数
	// 返回值包含一个参数singleArgument,例:validate-pattern-/[a-c]/gi,singleArgument值为/[a-c]/gi
	getArgumentsByClassName : function(prefix,className) {
		if(!className || !prefix)
			return [];
		var pattern = new RegExp(prefix+'-(\\S+)');
		var matchs = className.match(pattern);
		if(!matchs)
			return [];
		var results = [];
		results.singleArgument = matchs[1];
		var args =  matchs[1].split('-');
		for(var i = 0; i < args.length; i++) {
			if(args[i] == '') {
				if(i+1 < args.length) args[i+1] = '-'+args[i+1];
			}else{
				results.push(args[i]);
			}
		}
		return results;
	},
	assert : function(condition,message) {
		var errorMessage = message || ("assert failed error,condition="+condition);
		if (!condition) {
			alert(errorMessage);
			throw new Error(errorMessage);
		}else {
			return condition;
		}
	},
	isDate : function(v,dateFormat) {
		var MONTH = "MM";
	   	var DAY = "dd";
	   	var YEAR = "yyyy";
		var regex = '^'+dateFormat.replace(YEAR,'\\d{4}').replace(MONTH,'\\d{2}').replace(DAY,'\\d{2}')+'$';
		if(!new RegExp(regex).test(v)) return false;

		var year = v.substr(dateFormat.indexOf(YEAR),4);
		var month = v.substr(dateFormat.indexOf(MONTH),2);
		var day = v.substr(dateFormat.indexOf(DAY),2);
		
		var d = new Date(ValidationUtils.format('%s/%s/%s',[year,month,day]));
		return ( parseInt(month, 10) == (1+d.getMonth()) ) && 
					(parseInt(day, 10) == d.getDate()) && 
					(parseInt(year, 10) == d.getFullYear() );		
	},
	//document: http://ajaxcn.org/space/start/2006-05-15/2
	fireSubmit: function(form) {
	    var form = $(form);
	    if (form.fireEvent) { //for ie
	    	if(form.fireEvent('onsubmit'))
	    		form.submit();
	    } else if (document.createEvent) { // for dom level 2
			var evt = document.createEvent("HTMLEvents");
	      	//true for can bubble, true for cancelable
	      	evt.initEvent('submit', false, true); 
	      	form.dispatchEvent(evt);
	    }
 	},
 	getLanguage : function() {
 		var lang = null;
		if (typeof navigator.userLanguage == 'undefined')
			lang = navigator.language.toLowerCase();
		else
			lang = navigator.userLanguage.toLowerCase();
 		return lang;
 	},
 	getMessageSource : function() {
 		var lang = ValidationUtils.getLanguage();
 		var messageSource = Validator.messageSource['zh-cn'];
		if(Validator.messageSource[lang]) {
			messageSource = Validator.messageSource[lang];
		}
		return messageSource;
 	}
}



Validator.messagesSource = Validator.messagesSourceCn;
Validator.messages = {};
//init Validator.messages
Validator.messagesSource.each(function(ms){
	Validator.messages[ms[0]] = ms[1];
});

Validator.format = function(str,args) {
	args = args || [];
	Validation.assert(args.constructor == Array,"Validator.format() arguement 'args' must is Array");
	var result = str
	for (var i = 0; i < args.length; i++){
		result = result.replace(/%s/, args[i]);	
	}
	return result;
}

Validator.prototype = {
	initialize : function(className, error, test, options) {
		this.options = Object.extend({}, options || {});
		this._test = test ? test : function(v,elm){ return true };
		this._error = error ? error : Validator.messages['validation-failed'];
		this.className = className;
	},
	test : function(v, elm) {
		if(this.options.depends && this.options.depends.length > 0) {
			var dependsResult = $A(this.options.depends).all(function(depend){
				return Validation.get(depend).test(v,elm);
			});
			if(!dependsResult) return dependsResult;
		}
		if(!elm) elm = {}
		return this._test(v,elm,Validation.getArgumentsByClassName(this.className,elm.className),this);
	},

	error : function(v,elm,useTitle) {
		var dependError = null;
		$A(this.options.depends).any(function(depend){
			var validation = Validation.get(depend);
			if(!validation.test(v,elm))  {
				dependError = validation.error(v,elm,useTitle)
				return true;
			}
			return false;
		});
		if(dependError != null) return dependError;

		var args  = Validation.getArgumentsByClassName(this.className,elm.className);
		var error = this._error;
		if(typeof error == 'string') {
			if(v) args.push(v.length);
			error = Validator.format(this._error,args);
		}else if(typeof error == 'function') {
			error = error(v,elm,args,this);
		}else {
			alert('error must type of string or function');
		}
		if(!useTitle) useTitle = elm.className.indexOf('useTitle') >= 0;
		return useTitle ? ((elm && elm.title) ? elm.title : error) : error;
	}
}

var Validation = Class.create();

Validation.prototype = {
	initialize : function(form, options){
		this.options = Object.extend({
			onSubmit : true,
			stopOnFirst : false,
			immediate : false,
			focusOnError : true,
			useTitles : false,
			onFormValidate : function(result, form) {},
			onElementValidate : function(result, elm) {}
		}, options || {});
		this.form = $(form);
		var id =  Validation.getElmID(this.form);
		Validation.validations[id] = this;
		if(this.options.onSubmit) Event.observe(this.form,'submit',this.onSubmit.bind(this),false);
		if(this.options.immediate) {
			var useTitles = this.options.useTitles;
			var callback = this.options.onElementValidate;
			Form.getElements(this.form).each(function(input) { // Thanks Mike!
				Event.observe(input, 'blur', function(ev) { Validation.validate(Event.element(ev),{useTitle : useTitles, onElementValidate : callback}); });
			});
		}
	},
	onSubmit :  function(ev){
		if(!this.validate()) Event.stop(ev);
	},
	validate : function() {
		var result = false;
		var useTitles = this.options.useTitles;
		var callback = this.options.onElementValidate;
		if(this.options.stopOnFirst) {
			result = Form.getElements(this.form).all(function(elm) { return Validation.validate(elm,{useTitle : useTitles, onElementValidate : callback}); });
		} else {
			result = Form.getElements(this.form).collect(function(elm) { return Validation.validate(elm,{useTitle : useTitles, onElementValidate : callback}); }).all();
		}
		if(!result && this.options.focusOnError) {
			var first = Form.getElements(this.form).findAll(function(elm){return $(elm).hasClassName('validation-failed')}).first();
			if(first.select) first.select();
			first.focus();
		}
		this.options.onFormValidate(result, this.form);
		return result;
	},
	reset : function() {
		Form.getElements(this.form).each(Validation.reset);
	}
}

Object.extend(Validation, {
	validate : function(elm, options){
		options = Object.extend({
			useTitle : false,
			onElementValidate : function(result, elm) {}
		}, options || {});
		elm = $(elm);
		var cn = elm.classNames();
		return result = cn.all(function(value) {
			var test = Validation.test(value,elm,options.useTitle);
			options.onElementValidate(test, elm);
			return test;
		});
	},
	_getInputValue : function(elm) {
		var elm = $(elm);
		if(elm.type.toLowerCase() == 'file') {
			return elm.value;
		}else {
			return $F(elm);
		}
	},
	_getErrorMsg : function(useTitle,elm,validation) {
		return validation.error(Validation._getInputValue(elm),elm,useTitle);
	},
	test : function(name, elm, useTitle) {
		var v = Validation.get(name);
		var prop = '__advice'+name.camelize();
		if(Validation.isVisible(elm) && !v.test(Validation._getInputValue(elm),elm)) {
			if(!elm[prop]) {
				var advice = Validation.getAdvice(name, elm);
				if(typeof advice == 'undefined') {
					var errorMsg = Validation._getErrorMsg(useTitle,elm,v);
					advice = '<div class="validation-advice" id="advice-' + name + '-' + Validation.getElmID(elm) +'" style="display:none">' + errorMsg + '</div>'
					switch (elm.type.toLowerCase()) {
						case 'checkbox':
						case 'radio':
							var p = elm.parentNode;
							if(p) {
								new Insertion.Bottom(p, advice);
							} else {
								new Insertion.After(elm, advice);
							}
							break;
						default:
							new Insertion.After(elm, advice);
				    }
					advice = $('advice-' + name + '-' + Validation.getElmID(elm));
				}
				if(typeof Effect == 'undefined') {
					advice.style.display = 'block';
				} else {
					new Effect.Appear(advice, {duration : 1 });
				}
			}
			var advice = Validation.getAdvice(name, elm);
			advice.innerHTML = Validation._getErrorMsg(useTitle,elm,v);
			elm[prop] = true;
			elm.removeClassName('validation-passed');
			elm.addClassName('validation-failed');
			return false;
		} else {
			var advice = Validation.getAdvice(name, elm);
			if(typeof advice != 'undefined') {
				if(typeof Effect == 'undefined')
					advice.hide()
				else 
					new Effect.Fade(advice, {duration : 1 });
			}
			
			elm[prop] = '';
			elm.removeClassName('validation-failed');
			elm.addClassName('validation-passed');
			return true;
		}
	},
	isVisible : function(elm) {
		while(elm && elm.tagName != 'BODY') {
			if(!$(elm).visible()) return false;
			elm = elm.parentNode;
		}
		return true;
	},
	getAdvice : function(name, elm) {
		return Try.these(
			function(){ return $('advice-' + name + '-' + Validation.getElmID(elm)) },
			function(){ return $('advice-' + Validation.getElmID(elm)) }
		);
	},
	getElmID : function(elm) {
		return elm.id ? elm.id : elm.name;
	},
	reset : function(elm) {
		elm = $(elm);
		var cn = elm.classNames();
		cn.each(function(value) {
			var prop = '__advice'+value.camelize();
			if(elm[prop]) {
				var advice = Validation.getAdvice(value, elm);
				advice.hide();
				elm[prop] = '';
			}
			elm.removeClassName('validation-failed');
			elm.removeClassName('validation-passed');
		});
	},
	add : function(className, error, test, options) {
		var nv = {};
		nv[className] = new Validator(className, error, test, options);
		Object.extend(Validation.methods, nv);
	},
	addAllThese : function(validators) {
		var nv = {};
		$A(validators).each(function(value) {
				nv[value[0]] = new Validator(value[0], value[1], value[2], (value.length > 3 ? value[3] : {}));
			});
		Object.extend(Validation.methods, nv);
	},
	get : function(name) {
		var resultMethodName;
		for(var methodName in Validation.methods) {
			if(name == methodName) {
				resultMethodName = methodName;
				break;
			}
			if(name.indexOf(methodName) >= 0) {
				resultMethodName = methodName;
			}
		}
		return Validation.methods[resultMethodName] ? Validation.methods[resultMethodName] : new Validator();
		//return  Validation.methods[name] ? Validation.methods[name] : new Validator();
	},
	// 通过classname传递的参数必须通过'-'分隔各个参数
	getArgumentsByClassName : function(prefix,className) {
		if(!className || !prefix)
			return [];
		var pattern = new RegExp(prefix+'-(\\S+)');
		var matchs = className.match(pattern);
		if(!matchs)
			return [];
		var results = [];
		var args =  matchs[1].split('-');
		for(var i = 0; i < args.length; i++) {
			if(args[i] == '') {
				if(i+1 < args.length) args[i+1] = '-'+args[i+1];
			}else{
				results.push(args[i]);
			}
		}
		return results;
	},
	assert : function(condition,message) {
		var errorMessage = message || ("assert failed error,condition="+condition);
		if (!condition) {
			alert(errorMessage);
			throw new Error(errorMessage);
		}else {
			return condition;
		}
	},
	methods : {}
});

Validation.add('IsEmpty', '', function(v) {
				return  ((v == null) || (v.length == 0)); // || /^\s+$/.test(v));
			});

Validation.addAllThese([
     /**
	 * Usage :   required
	 * Example : 判断元素不能为空
	 */
	['required', Validator.messages['required'], function(v) {
				return !(Validation.get('IsEmpty').test(v) || /^\s+$/.test(v));
			}],
	/**
	 * Usage :   required
	 * Example : 判断元素不能为空格
	 */
	['required-space', Validator.messages['required-space'], function(v) {
	            var t=v.replace(/\s/g,"");
	            if(t.length==0){
	               return false;
	            }else{
	              return true;
	            }
			}],
	 /**
	 * Usage :   validate-number
	 * Example : 判断元素必须为整数
	 */
	['validate-number', Validator.messages['validate-number'], function(v) {
				return Validation.get('IsEmpty').test(v) || (!isNaN(v) && !/^\s+$/.test(v));
			}],
	/**
	* Usage :   validate-postcode
	* Example : 判断元素为邮政编码的格式
	*/
   ['validate-postcode', Validator.messages['validate-postcode'], function(v) {
				return Validation.get('IsEmpty').test(v) || (!isNaN(v) && !/^\s+$/.test(v)) && (v.length==6);
			}],
   
	/**
	* Usage :   validate-digits
	* Example : 判断元素为数字的格式
	*/
	['validate-digits', Validator.messages['validate-digits'], function(v) {
				return Validation.get('IsEmpty').test(v) ||  !/[^\d]/.test(v);
			}],
	
	/**
	* Usage :   validate-digits
	* Example : 判断元素中是否含有特殊字符
	*/
	['validate-specialchar', Validator.messages['validate-specialchar'], function(v) {
				return Validation.get('IsEmpty').test(v) ||  /^[^@\/\'\\\"#$%&<>\^\*]+$/.test(v);
			}],
	/**
	* Usage :   validate-alpha
	* Example : 判断元素为字母
	*/
	['validate-alpha', Validator.messages['validate-alpha'], function (v) {
				return Validation.get('IsEmpty').test(v) ||  /^[a-zA-Z]+$/.test(v)
			}],
	/**
	* Usage :   validate-alphanum
	* Example : 判断元素为数字或字母
	*/
	['validate-alphanum', Validator.messages['validate-alphanum'], function(v) {
				return Validation.get('IsEmpty').test(v) ||  !/\W/.test(v)
			}],
	/**
	* Usage :   validate-date
	* Example : 判断元素为时间的格式 2008/08/08
	*/
	['validate-date', Validator.messages['validate-date'], function(v) {
				var test = new Date(v);
				return Validation.get('IsEmpty').test(v) || !isNaN(test);
			}],
    /**
	* Usage :   validate-email
	* Example : 判断元素为邮箱的格式
	*/
	['validate-email', Validator.messages['validate-email'], function (v) {
				return Validation.get('IsEmpty').test(v) || /\w{1,}[@][\w\-]{1,}([.]([\w\-]{1,})){1,3}$/.test(v)
			}],
	/**
	* Usage :   validate-selection
	* Example : 请选择一个可选项
	*/		
	['validate-selection', Validator.messages['validate-selection'], function(v,elm){
				return elm.options ? elm.selectedIndex > 0 : !Validation.get('IsEmpty').test(v);
			}],
    /**
	* Usage :   validate-url
	* Example : 判断元素为www网址或FTP网址的格式
	*/
	['validate-url', Validator.messages['validate-url'], function (v) {
				return Validation.get('IsEmpty').test(v) || /^(http|https|ftp):\/\/(([A-Z0-9][A-Z0-9_-]*)(\.[A-Z0-9][A-Z0-9_-]*)+)(:(\d+))?\/?/i.test(v)
			}],
			
			  /**
			* Usage :   validate-url
			* Example : 判断元素为www网址
			*/
			['validate-wangzhi', Validator.messages['validate-wangzhi'], function (v) {
					return Validation.get('IsEmpty').test(v) ||/^(([A-Z0-9][A-Z0-9_-]*)(\.[A-Z0-9][A-Z0-9_-]*)+)(:(\d+))?\/?/i.test(v)
					}],
	/**
	* Usage :   validate-date-au
	* Example : 判断元素为
	*/
	['validate-date-au', Validator.messages['validate-date-au'], function(v) {
				if(Validation.get('IsEmpty').test(v)) return true;
				var regex = /^(\d{2})\/(\d{2})\/(\d{4})$/;
				if(!regex.test(v)) return false;
				var d = new Date(v.replace(regex, '$2/$1/$3'));
				return ( parseInt(RegExp.$2, 10) == (1+d.getMonth()) ) && 
							(parseInt(RegExp.$1, 10) == d.getDate()) && 
							(parseInt(RegExp.$3, 10) == d.getFullYear() );
			}],
	/**
	* Usage :   validate-currency-dollar
	* Example : 判断元素为钱币的格式
	*/
	['validate-currency-dollar', Validator.messages['validate-currency-dollar'], function(v) {
				// [$]1[##][,###]+[.##]
				// [$]1###+[.##]
				// [$]0.##
				// [$].##
				return Validation.get('IsEmpty').test(v) ||  /^\$?\-?([1-9]{1}[0-9]{0,2}(\,[0-9]{3})*(\.[0-9]{0,2})?|[1-9]{1}\d*(\.[0-9]{0,2})?|0(\.[0-9]{0,2})?|(\.[0-9]{1,2})?)$/.test(v)
			}],
	/**
	* Usage :   validate-one-required
	* Example : 判断元素为被选中
	*/
	['validate-one-required', Validator.messages['validate-one-required'], function (v,elm) {
				var p = elm.parentNode;
				var options = p.getElementsByTagName('INPUT');
				return $A(options).any(function(elm) {
					return $F(elm);
				});
			}]
]);


//custom validate start
Validation.addAllThese([
/**
	* Usage :   validate-date
	* Example : 判断元素为时间的格式 2008-08-08
	*/
	['validate-date-cn', Validator.messages['validate-date-cn'], function(v) {
				if(Validation.get('IsEmpty').test(v)) return true;
				var regex = /^(\d{4})-(\d{2})-(\d{2})$/;
				if(!regex.test(v)) return false;
				var d = new Date(v.replace(regex, '$1/$2/$3'));
				return ( parseInt(RegExp.$2, 10) == (1+d.getMonth()) ) && 
							(parseInt(RegExp.$3, 10) == d.getDate()) && 
							(parseInt(RegExp.$1, 10) == d.getFullYear() );
			}],

	['validate-integer', Validator.messages['validate-integer'], function(v) {
				return Validation.get('IsEmpty').test(v) || (/^[-+]?[\d]+$/.test(v));
			}],
    /**
	* Usage :   validate-chinese
	* Example : 判断元素为中文
	*/
	['validate-chinese', Validator.messages['validate-chinese'], function(v) {
				return Validation.get('IsEmpty').test(v) || (/^[\u4e00-\u9fa5]+$/.test(v));
			}],
	/**
	* Usage :   validate-ip
	* Example : 判断元素为IP 地址
	*/
	['validate-ip', Validator.messages['validate-ip'], function(v) {
				return Validation.get('IsEmpty').test(v) || (/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(v));
			}],
	/**
	* Usage :   validate-id-number
	* Example : 判断元素为身份证
	*/	
	['validate-id-number',Validator.messages['validate-id-number'],function(v,elm,args,metadata) {
			DWREngine.setAsync(false); 
				VerificationCard.checkSfzjhm(v,{callback:function(sta){
				str=sta;
				}});
			DWREngine.setAsync(true); 
			return str;
		//if(!(/^\d{17}(\d|x)$/i.test(v) || /^\d{15}$/i.test(v))) return false;
		//var provinceCode = parseInt(v.substr(0,2));
		//if((provinceCode < 11) || (provinceCode > 91)) return false;
		//var forTestDate = v.length == 18 ? v : v.substr(0,6)+"19"+v.substr(6,15);
		//var birthday = forTestDate.substr(6,8);
		//if(!ValidationUtils.isDate(birthday,'yyyyMMdd')) return false;
		//if(v.length == 18) {
		//	v = v.replace(/x$/i,"a");
		//	var verifyCode = 0;
		//	for(var i = 17;i >= 0;i--)   
        //    	verifyCode += (Math.pow(2,i) % 11) * parseInt(v.charAt(17 - i),11);
        //    if(verifyCode % 11 != 1) return false;
		//}
		//return true;
	}],
	
	
	/**
	* Usage :   validate-id-numberNew1
	* Example : 判断元素为身份证
	* 高鹏飞 2011-11-09
	* 身份证可以为空
	*/	
	['validate-id-numberNew1',Validator.messages['validate-id-numberNew1'],function(v,elm,args,metadata) {
		if(v != ""){
		
			//以下代码判断身份证是否正确
					DWREngine.setAsync(false); 
					VerificationCard.checkSfzjhm(v,{callback:function(sta){
					str=sta;
					}});
					DWREngine.setAsync(true); 
					return str;
				//判断结束
			//if(!(/^\d{17}(\d|x)$/i.test(v) || /^\d{15}$/i.test(v))) return false;
			//var provinceCode = parseInt(v.substr(0,2));
			//if((provinceCode < 11) || (provinceCode > 91)) return false;
			//var forTestDate = v.length == 18 ? v : v.substr(0,6)+"19"+v.substr(6,15);
			//var birthday = forTestDate.substr(6,8);
			//if(!ValidationUtils.isDate(birthday,'yyyyMMdd')) return false;
			//if(v.length == 18) {
			//	v = v.replace(/x$/i,"a");
			//	var verifyCode = 0;
			//	for(var i = 17;i >= 0;i--)   
	        //    	verifyCode += (Math.pow(2,i) % 11) * parseInt(v.charAt(17 - i),11);
	        //    if(verifyCode % 11 != 1) return false;
			//}
		}
		return true;
	}],
	
	/**
	* Usage :   validate-id-numberNew
	* Example : 判断元素为身份证
	* 高鹏飞 2011-09-15 新加 
	* 配偶身份证不填写，不验证该身份证是否合法，填写后验证该身份证是否合法，
	* 同时身份证、姓名，户籍地编码三个其中一个填写后，剩余两项必须填写。
	*/	
	['validate-id-numberNew',Validator.messages['validate-id-numberNew'],function(v,elm,args,metadata) {
		var  str=true;
		if(v != ""){
		
			//以下代码判断身份证是否正确
					DWREngine.setAsync(false); 
					VerificationCard.checkSfzjhm(v,{callback:function(sta){
					str=sta;
					}});
					DWREngine.setAsync(true); 
				//助产机构编号判断结束
			//if(!(/^\d{17}(\d|x)$/i.test(v) || /^\d{15}$/i.test(v))) return false;
			//var provinceCode = parseInt(v.substr(0,2));
			//if((provinceCode < 11) || (provinceCode > 91)) return false;
			//var forTestDate = v.length == 18 ? v : v.substr(0,6)+"19"+v.substr(6,15);
			//var birthday = forTestDate.substr(6,8);
			//if(!ValidationUtils.isDate(birthday,'yyyyMMdd')) return false;
			//if(v.length == 18) {
			//	v = v.replace(/x$/i,"a");
			//	var verifyCode = 0;
			//	for(var i = 17;i >= 0;i--)   
	        //    	verifyCode += (Math.pow(2,i) % 11) * parseInt(v.charAt(17 - i),11);
	        //    if(verifyCode % 11 != 1) return false;
			//}
			if(jQuery("input[name='flowpop.spouseName']").val() == "")
				jQuery("input[name='flowpop.spouseName']").addClass("required validate-specialchar");
		} else {
			if(jQuery("#hjdareano1").val().substring(6,9) != "000" && jQuery("#hjdareano1").val() != ""){
				jQuery("input[name='flowpop.spouseName']").addClass("required validate-specialchar");
				jQuery("#scardno").addClass("required validate-id-numberNew");
			}
		}
		if(jQuery("input[name='flowpop.spouseName']").val() != "" && v == ""){
			jQuery("#scardno").addClass("required validate-id-numberNew");
		}
			return str;
		
	}],
	/**
	* Usage :   validate-tel
	* Example : 判断元素为电话
	*/
	['validate-tel', Validator.messages['validate-tel'], function(v) {
				return Validation.get('IsEmpty').test(v) || /^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/.test(v);
			}],
	/**
	* Usage :   validate-tel-phone
	* Example : 判断元素为电话或固定电话
	*/
	['validate-tel-phone', Validator.messages['validate-tel-phone'], function(v) {
			if(v.length<11||v.length==12)
			{
			return Validation.get('IsEmpty').test(v) || /^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/.test(v);}
			else
			{
			return Validation.get('IsEmpty').test(v) || (/(^0?[1][35][0-9]{9}$)/.test(v));
			}
		}],
	/**
	* Usage :   validate-mobile-phone
	* Example : 判断元素为手机
	*/
	['validate-mobile-phone', Validator.messages['validate-mobile-phone'], function(v) {
				return Validation.get('IsEmpty').test(v) || (/(^0?[1][35][0-9]{9}$)/.test(v));
			}],
	/**
	* Usage :   validate-qq
	* Example : 判断元素为QQ
	*/	
	
	['validate-qq',Validator.messages['validate-qq'], function(v) {
				return Validation.get('IsEmpty').test(v) || (/(^[1-9]\d{4,10}$)/.test(v));
			}],
     /**
	 * Usage : validate-passequals-otherInputId   验证确认密码
	 * Example : validate-passequals-username or validate-equals-email etc..
	 */
	['validate-passequals',Validator.messages['validate-passequals'], function(v,elm,args,metadata) {
				return Validation.get('IsEmpty').test(v) || $F(args[0]) == v;
			}],
     /**
	 * Usage : validate-equals-otherInputId   判断与前一个文本框一致
	 * Example : validate-equals-username or validate-equals-email etc..
	 */
	['validate-equals',Validator.messages['validate-equals'], function(v,elm,args,metadata) {
				return Validation.get('IsEmpty').test(v) || $F(args[0]) == v;
			}],
	/**
	 * Usage : less-than-otherInputId 判断比前一个文本框的值小 
	 */
	['less-than',Validator.messages['less-than'], function(v,elm,args,metadata) {
				if(Validation.get('validate-number').test(v) && Validation.get('validate-number').test($F(args[0])))
					return Validation.get('IsEmpty').test(v) || parseFloat(v) < parseFloat($F(args[0]));
				return Validation.get('IsEmpty').test(v) || v < $F(args[0]);
			}],
	/**
	 * Usage : less-thantime-otherInputId 判断比前一个文本框的时间小 
	 */
	['less-thantime',Validator.messages['less-thantime'], function(v,elm,args,metadata) {
				if(Validation.get('validate-number').test(v) && Validation.get('validate-number').test($F(args[0])))
					return Validation.get('IsEmpty').test(v) || parseFloat(v) < parseFloat($F(args[0]));
				return Validation.get('IsEmpty').test(v) || v < $F(args[0]);
			}],
	 /**
	 * Usage : great-than-otherInputId  判断比前一个文本框的值大 
	 */
	['great-than',Validator.messages['great-than'], function(v,elm,args,metadata) {
				if(Validation.get('validate-number').test(v) && Validation.get('validate-number').test($F(args[0])))
					return Validation.get('IsEmpty').test(v) || parseFloat(v) > parseFloat($F(args[0]));
				return Validation.get('IsEmpty').test(v) || v > $F(args[0]);
			}],
			
	 /**
	 * Usage : lessequal-than-otherInputId  判断小于等于前一个文本框的值
	 */
	['lessequal-than',Validator.messages['lessequal-than'], function(v,elm,args,metadata) {
				if(Validation.get('validate-number').test(v) && Validation.get('validate-number').test($F(args[0])))
					return Validation.get('IsEmpty').test(v) || parseFloat(v) <= parseFloat($F(args[0]));
				return Validation.get('IsEmpty').test(v) || v <= $F(args[0]);
			}],
	 /**
	 * Usage : great-than-otherInputId  判断选择时间大于当前时间
	 */
	['great-thantime',Validator.messages['great-thantime'], function(v,elm,args,metadata) {
				if(Validation.get('validate-number').test(v) && Validation.get('validate-number').test($F(args[0])))
					return Validation.get('IsEmpty').test(v) || parseFloat(v) > parseFloat($F(args[0]));
				return Validation.get('IsEmpty').test(v) || v > $F(args[0]);
			}],
	/*
	 * Usage: min-length-number 限定输入字符的最小长度
	 * Example: min-length-10
	 */
	['min-length',Validator.messages['min-length'],function(v,elm,args,metadata) {
		return Validation.get('IsEmpty').test(v) || v.length >= parseInt(args[0]);
	}],
	
	/*
	 * Usage: max-length-number限定输入字符的最大长度
	 * Example: max-length-10
	 */
	['max-length',Validator.messages['max-length'],function(v,elm,args,metadata) {
		return Validation.get('IsEmpty').test(v) || v.length <= parseInt(args[0]);
	}],
	/*
	 * Usage: validate-file-type1-type2-typeX  限定上传文件的格式 
	 * Example: validate-file-png-jpg-jpeg
	 */
	['validate-file', function(v,elm,args,metadata) {
		return Validator.format(Validator.messages['validate-file'],[args.join(',')]);
	},function(v,elm,args,metadata) {
		return Validation.get('IsEmpty').test(v) || $A(args).any(function(extentionName) {
			return new RegExp('\\.'+extentionName+'$','i').test(v);
		});
	}],
	/*
	 * Usage: validate-float-range-minValue-maxValue  限定数值为一定区间的FLOAT 
	 * Example: -2.1 to 3 = validate-float-range--2.1-3
	 */
	['validate-float-range', Validator.messages['validate-float-range'],function(v,elm,args,metadata) {
		return Validation.get('IsEmpty').test(v) || (parseFloat(v) >= parseFloat(args[0]) && parseFloat(v) <= parseFloat(args[1]))
	},{depends : ['validate-number']}],
	
	/*
	 * Usage: validate-int-range-minValue-maxValue 限定数值为一定区间的 int
	 * Example: -10 to 20 = validate-int-range--10-20
	 */
	['validate-int-range',Validator.messages['validate-int-range'],function(v,elm,args,metadata) {
		return Validation.get('IsEmpty').test(v) || (parseInt(v) >= parseInt(args[0]) && parseInt(v) <= parseInt(args[1]))
	},{depends : ['validate-integer']}],
	/*
	 * Usage: validate-length-range-minLength-maxLength 限定一定区间长度的字符串
	 * Example: 10 to 20 = validate-length-range-10-20
	 */
	['validate-length-range', Validator.messages['validate-length-range'],function(v,elm,args,metadata) {
		return Validation.get('IsEmpty').test(v) || (v.length >= parseInt(args[0]) && v.length <= parseInt(args[1]))
	}],
	/*
	 * Usage: max-value-number  限定输入最大的数值
	 * Example: max-value-10
	 */
	['max-value',Validator.messages['max-value'] ,function(v,elm,args,metadata) {
		return Validation.get('IsEmpty').test(v) || parseFloat(v) <= parseFloat(args[0]);
	},{depends : ['validate-number']}],
	/*
	 * Usage: min-value-number 限定输入最大的数值
	 * Example: min-value-10
	 */
	['min-value',Validator.messages['min-value'],function(v,elm,args,metadata) {
		return Validation.get('IsEmpty').test(v) || parseFloat(v) >= parseFloat(args[0]);
	},{depends : ['validate-number']}],
	/*
	 * Usage: validate-pattern-RegExp  给定一个的正则表达式
	 * Example: <input id='sex' class='validate-pattern-/^[fm]$/i'>
	 */
	['validate-pattern',Validator.messages['validate-pattern'],function(v,elm,args,metadata) {
		var extractPattern = /validate-pattern-\/(\S*)\/(\S*)?/;
		Validation.assert(extractPattern.test(elm.className),"invalid validate-pattern expression,example: validate-pattern-/a/i");
		elm.className.match(extractPattern);
		return Validation.get('IsEmpty').test(v) || new RegExp(RegExp.$1,RegExp.$2).test(v);
	}],
	/*
	 * Example: <input id='email' class='validate-ajax' validateUrl='http://localhost:8080/validate-email.jsp' validateFailedMessage='email already exists'>
	 */
	['validate-ajax',function(v,elm,args,metadata) {
		return elm.getAttribute('validateFailedMessage') || Validator.messages['validation-failed'];
	},function(v,elm,args,metadata) {
		Validation.assert(elm.getAttribute('validateUrl'),'element validate by ajax must has "validateUrl" attribute');
		//Validation.assert(elm.getAttribute('validateFailedMessage'),'element validate by ajax must has "validateFailedMessage" attribute');
		
		if(elm._ajaxValidating && elm._hasAjaxValidateResult) {
			elm._ajaxValidating = false;
			elm._hasAjaxValidateResult = false;
			return elm._ajaxValidateResult;
		}

		var sendRequest = function() {
			new Ajax.Request(elm.getAttribute('validateUrl'),{
				parameters : Form.Element.serialize(elm),
				onSuccess : function(response) {
					if('true' != response.responseText.strip()  && 'false' != response.responseText.strip())
						Validation.assert(false,'validate by ajax,response.responseText must equals "true" or "false",actual='+response.responseText);
					elm._ajaxValidateResult = eval(response.responseText);
					elm._hasAjaxValidateResult = true;
					Validation.test('validate-ajax',elm);
				}
			});
			elm._ajaxValidating = true;
			return true;
		}

		return elm._ajaxValidating || Validation.get('IsEmpty').test(v) || sendRequest();
	}]
]);
Validation.add(
	'validate-equalss', 
	function(v,elm) {
		var results = elm.className.match(/validate-equalss-([\S]*)/);
		var expectedValuesStr = results[1];
		var expectedValues = expectedValuesStr.split('-');
		return '期待的值应该为其中['+expectedValues.join(',')+"]之一";
	},
	function(v,elm) {
		var results = elm.className.match(/validate-equalss-([\S]*)/);
		var expectedValuesStr = results[1];
		var expectedValues = expectedValuesStr.split('-');
		return Validation.get('IsEmpty').test(v) || expectedValues.any(function(expectedValue) {
			return v == expectedValue;
		});
	}
)
Validation.validations = {};
Validation.autoBind = function() {
	 var forms = document.getElementsByClassName('required-validate');
	 $A(forms).each(function(form){
		var validation = new Validation(form,{immediate:true});
		Event.observe(form,'reset',function() {validation.reset();},false);
	 });
};

Validation.$ = function(id) {
	return Validation.validations[id];
}

Event.observe(window,'load',Validation.autoBind,false);