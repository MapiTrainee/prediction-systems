"use strict";

$(function () {
    getDraws().then(function (draws) {
        renderDraws(draws);
    });
});

function renderDraws(multiMultiDraws) {

    var amount = 0;
    var twoThird = 0;
    var threeThird = 0;
    var actives = 0;

    var maxPlus = 0;
    var maxBlack = 0;
    var steadyPlus = 0;
    var steadyBlack = 0;

    $.each(multiMultiDraws, function (i, multiMultiDraw) {
        var multiTable = [];
        var ticketTable = ["<tr>"];

        multiTable.push((new Date(multiMultiDraw.date)).toLocaleString());
        if (multiMultiDraw.draw.length !== 0) {
            $.each(multiMultiDraw.draw, function (j, m) {
                multiTable.push(m);
            });
        } else {
            for (var k = 0; k < 20; k++) {
                multiTable.push("-");
            }
        }
        $('#multiTable').append("<tr><td>" + multiTable.join("</td><td>") + "</td></tr>");

        if (multiMultiDraw.ticket !== null) {
            var resultArray = multiMultiDraw.result;
            var counter = 0;

            $.each(multiMultiDraw.ticket, function (l, t) {

                if (resultArray !== null) {
                    if (resultArray[l] === 1) {
                        ticketTable.push("<td class='correct'>" + t + "</td>");
                        counter++;
                    } else {
                        ticketTable.push("<td>" + t + "</td>");
                    }
                } else {
                    ticketTable.push("<td>" + t + "</td>");
                }
            });

            if (resultArray === null) {
                ticketTable.push("<td class='text-muted'>" + "-" + 7.5 + "</td></tr>");
            } else if (counter === 2) {
                amount += 8.5;
                twoThird++;
                steadyPlus++;
                actives++;

                if (maxBlack < steadyBlack)
                    maxBlack = steadyBlack;
                if (maxPlus < steadyPlus)
                    maxPlus = steadyPlus;

                steadyBlack = 0;

                ticketTable.push("<td class='text-success'>" + "+" + 8.5 + "</td></tr>");
            } else if (counter === 3) {
                amount += 40.5;
                threeThird++;
                steadyPlus++;
                actives++;

                if (maxBlack < steadyBlack)
                    maxBlack = steadyBlack;
                if (maxPlus < steadyPlus)
                    maxPlus = steadyPlus;

                steadyBlack = 0;

                ticketTable.push("<td class='text-success'>" + "+" + 40.5 + "</td></tr>");
            } else {
                amount -= 7.5;
                steadyBlack++;
                actives++;

                if (maxBlack < steadyBlack)
                    maxBlack = steadyBlack;
                if (maxPlus < steadyPlus)
                    maxPlus = steadyPlus;

                steadyPlus = 0;

                ticketTable.push("<td class='text-danger'>" + "-" + 7.5 + "</td></tr>");
            }
        } else {
            ticketTable.push("<td>-</td><td>-</td><td>-</td><td>-</td></tr>");
        }

        $('#ticketTable').append(ticketTable.join(""));

    });
    var css = 'text-danger';
    if (amount >= 0) {
        css = "text-success";
    }
    var count = multiMultiDraws.length;

    $('#count').text(count);
    $('#twoThird').text(twoThird);
    $('#threeThird').text(threeThird);
    $('#blackSeries').text(maxBlack);
    $('#plusSeries').text(maxPlus);
    $('#amount').html("<span class=" + css + ">" + amount + "</span>");

    if (actives > 0) {
        var barThreeThird = parseInt(threeThird / actives * 100);
        var barTwoThird = parseInt(twoThird / actives * 100);
        var barCount = 100 - barThreeThird - barTwoThird;
    } else {
        var barThreeThird = 33;
        var barTwoThird = 33;
        var barCount = 34;
    }

    $('#barThreeThird').text(barThreeThird + "%");
    $('#barThreeThird').css("width", barThreeThird + "%");
    $('#barTwoThird').text(barTwoThird + "%");
    $('#barTwoThird').css("width", barTwoThird + "%");
    $('#barCount').text(barCount + "%");
    $('#barCount').css("width", barCount + "%");

}

function getDraws() {
    return $.ajax({
        type: "GET",
        url: "/multi",
        dataType: "json",
        error: function () {
            alert("Błąd pobrania losów!");
        }
    });
}

