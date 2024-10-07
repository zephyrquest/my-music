let audio = document.getElementById("track");
audio.setAttribute("controls", "controls");
audio.setAttribute("controlsList", "nofullscreen nodownload noremoteplayback noplaybackrate");

let lyricsContainer = document.getElementById("lyricsContainer");
let lyricsPath = lyricsContainer.getAttribute("data-lyricsPath");

let request = new Request(lyricsPath);
fetch(request)
    .then(response => {
        return response.text();
    })
    .then(function (response) {
        lyricsContainer.innerText = response;
    })


function checkForControlsListAttribute() {
    let audio = document.getElementById("track");

    if (!audio.controlsList.contains("nodownload")) {
        audio.controlsList.add("nodownload");
    }

    if(!audio.controlsList.contains("nofullscreen")) {
        audio.controlsList.add("nofullscreen");
    }

    if(!audio.controlsList.contains("noremoteplayback")) {
        audio.controlsList.add("noremoteplayback");
    }

    if(!audio.controlsList.contains("noplaybackrate")) {
        audio.controlsList.add("noplaybackrate");
    }
}

// Create a MutationObserver instance
const observer = new MutationObserver(checkForControlsListAttribute);

// Configuration for the observer (observe changes to subtree and attributes)
const config = { subtree: true, attributes: true };

// Start observing the document
observer.observe(document, config);