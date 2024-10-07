//handle click on play button
let playButtons = document.getElementsByClassName("playButton");
for(let i = 0; i < playButtons.length; i++) {
    let playButton = playButtons[i];

    playButton.addEventListener("click", function () {
        //pause and reset all other the tracks and replace pause button for play button
        let audios = document.getElementsByTagName("audio");
        for(let j = 0; j < audios.length; j++) {
            if(audios[j].closest("div").querySelector(".playButton") !== playButton) {
                audios[j].pause();
                audios[j].currentTime = 0;

                let otherPauseButton = audios[j].closest("div").querySelector(".pauseButton");
                otherPauseButton.style.display = "none";

                let otherPlayButton = audios[j].closest("div").querySelector(".playButton");
                otherPlayButton.style.display = "inline-block";
            }
        }

        //reproduce the selected track
        let audio = playButton.closest("div").querySelector("audio");
        audio.play();

        playButton.style.display = "none";

        let pauseButton = playButton.closest("div").querySelector(".pauseButton");
        pauseButton.style.display = "inline-block";
    })
}

//handle click on pause button
let pauseButtons = document.getElementsByClassName("pauseButton");
for(let i = 0; i < pauseButtons.length; i++) {
    let pauseButton = pauseButtons[i];
    pauseButton.addEventListener("click", function () {
        //pause the selected track
        let audio = pauseButton.closest("div").querySelector("audio");
        audio.pause();

        pauseButton.style.display = "none";

        let playButton = pauseButton.closest("div").querySelector(".playButton");
        playButton.style.display = "inline-block";
    })
}

function checkForControlsAttribute() {
    let audios = document.getElementsByTagName("audio");
    for(let i = 0; i < audios.length; i++) {
        if(audios[i].getAttribute("controls") != null) {
            audios[i].removeAttribute("controls");
        }
    }
}

// Create a MutationObserver instance
const observer = new MutationObserver(checkForControlsAttribute);

// Configuration for the observer (observe changes to subtree and attributes)
const config = { subtree: true, attributes: true };

// Start observing the document
observer.observe(document, config);