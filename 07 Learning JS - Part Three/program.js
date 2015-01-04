/**
 * Created by theowinter on 30/12/14.
 */

var helloWorld = function () {
    //Siehe "use strict" für Erklärung.
    "use strict";
    alert("hello world");
};


window.onload = function () {
    //BIND example
    //Only works for existing elements. See ON.
    $("#first").bind("click",function(){
        helloWorld();
    });

    //Leeres Objekt
    $({})

    //CSS
    $('#id')

    //DOM Objekte
    $(document), $(document.body)

    //Spezifisches Element mit ID
    $('#idName'), z.B. $('#helloWorldDiv')

    //Elemente mit spezifischem Tag
    $('tag'), z.B. $('div')

    //Element mit id und Tag
    $('#id > div'), auch $('#id div') möglich

    //Elemente mit Attribute
    $(':attribute'), z.B. $(':checked')



    //LIVE example
    //Deprecated as of jQuery 1.9
    /*
    $("second").live('mouseover', function(){
        helloWorld();
    });*/

    //ON example
    //Does the same as live, e.g. also works for newly added elements.
    $("#third").on('click', function(){
        helloWorld();
    });

    $("#LastChild.third").on('click', function(){
        helloWorld();
    });

    //var newcontent = '<p id=\"first\"> first - 2</p><p id=\"third\"> third - 2</p>';

   // $('#LastChild').html(newcontent);
   // $('#list div:last').after("<p id=\"first\"> first - 2</p>");
   // $('#list div:last').after("<p id=\"second\"> second - 2</p>");
};