"use strict";

$(function () {
    getDraws().then(function (draws) {
        renderDraws(draws);
    });
});


