"use strict";

// Create a custom mini alert class
let AlertBox = function (id, type) {

  this.show = function (msg) {
    //Τhrow an exception on an inappopriate message
    if (msg === '' || typeof msg === 'undefined' || msg === null) {
      throw '"Invalid msg parameter"';
    } else {
      //Get the div of the message
      const alertArea = document.getElementById(id);
      const alertBox = document.createElement('div');
      const alertContent = document.createElement('div');

      const alertClass = this;

      alertContent.classList.add('alert-content');
      alertContent.innerText = msg;

      //Apply the message style according to the type of the message
      if(type === 'Success')
          alertBox.classList.add('alert-box-success');
      else if(type === 'Error')
          alertBox.classList.add('alert-box-error');


      alertBox.appendChild(alertContent);
      alertArea.appendChild(alertBox);

      //Wait 1s before applying hide()
      const alertTimeout = setTimeout(function () {
        alertClass.hide(alertBox);
        clearTimeout(alertTimeout);
      }, 1000);

    }
  };

  this.hide = function (alertBox) {
    alertBox.classList.add('alert-box-hide');
    const disperseTimeout = setTimeout(function () {
      alertBox.parentNode.removeChild(alertBox);
      clearTimeout(disperseTimeout);
    }, 2000);
  };
};



