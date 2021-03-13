"use strict";

// Get the modal
const modal = document.getElementById("resultsModal");

// Get the element that closes the modal
const span = document.getElementsByClassName("modal-close")[0];

// When the user clicks on (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target === modal) {
    modal.style.display = "none";
  }
}
