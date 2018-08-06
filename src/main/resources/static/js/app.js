(function() {

  'use strict';

  var app = (function() {
	  
	var $score;  
	var $tip;  
	  
    return {
      init: function init() {
    	  $score = document.getElementById('score');
    	  $tip = document.getElementById('tip');
    	  this.initEvents();
      },

      initEvents: function initEvents() {
    	  var $txtPassword = document.getElementById("txtPass");
    	  $txtPassword.addEventListener('keyup', app.checkPassword, false);
      },
      
      checkPassword: function checkPassword() {
    	  var pwd = this.value;
    	  
    	  if (app.isNullOrEmpty(pwd)) {
    		  app.setDefaultValues();
    		  return;
    	  }
    	  
    	  var ajax = app.createAjaxRequest('GET', pwd, app.applyValues);
    	  ajax.send();
      },
      
      createAjaxRequest: function createAjaxRequest(method, pathParam, callback) { 
    	  var ajax = new XMLHttpRequest();
    	  var url = 'http://localhost:8080/meter';
    	  if (pathParam) url += '/' + encodeURIComponent(pathParam);
          ajax.open(method, url, true);
          ajax.setRequestHeader('Content-Type', 'application/json');
          ajax.addEventListener('readystatechange', callback, false);
          return ajax;
      },
      
      applyValues: function applyValues() {
    	  if (!app.isReady.call(this)) return;
    	  var level = JSON.parse(this.responseText);
    	  app.setValues(level.score, level.tip);
      },
      
      setValues: function setValues(score, tip) {
    	  $score.innerHTML = score + '%';
    	  $tip.innerHTML = tip;
    	  $tip.style.backgroundPosition = "-" + parseInt(score * 4) + "px";
      },
      
      isReady: function isReady() {
    	  return (this.readyState === 4 && this.status === 200);
      },
      
      isNullOrEmpty: function isNullOrEmpty(value) {
    	  return value === null || value === undefined || value.length === 0;
      },
      
      setDefaultValues: function setDefaultValues() {
    	  app.setValues(0, 'Muito curta');
      }

    }
  })();
  app.init();
})();