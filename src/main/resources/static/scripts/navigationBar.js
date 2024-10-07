function onMouseOverCol() {
    let cols = document.getElementsByClassName("col");
    for(let i = 0; i < cols.length; i++) {
        cols[i].addEventListener("mouseover", function(event) {
            event.target.classList.add("border-primary");
        });
    }
}

function onMouseOutCol() {
    let cols = document.getElementsByClassName("col");
    for(let i = 0; i < cols.length; i++) {
        cols[i].addEventListener("mouseout", function(event) {
            event.target.classList.remove("border-primary");
        });
    }
}

onload = function () {
    onMouseOverCol();
    onMouseOutCol();
}