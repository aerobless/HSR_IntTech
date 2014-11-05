/**
 * Created by theowinter on 05/11/14.
 */
/*global THREE:false */
/*jslint browser: true*/

var test = "test";

var scene = new THREE.Scene();
var camera = new THREE.PerspectiveCamera(50, window.innerWidth / window.innerHeight, 0.1, 1000);

var renderer;

renderer = new THREE.WebGLRenderer();
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);

var geometry = new THREE.BoxGeometry(1, 1, 1);

var material = new THREE.MeshBasicMaterial({color: 0xbf0 });
var greenMaterial = new THREE.MeshBasicMaterial({color: 0x00ff1e });

var cube = new THREE.Mesh(geometry, material);
cube.position.set(0, 0, 0);

var makeGreenCube = function (posX, posY, posZ) {
    "use strict";
    var geometry = new THREE.BoxGeometry(0.5, 0.5, 0.5),
        cube = new THREE.Mesh(geometry, greenMaterial); //L: only one var!

    cube.position.set(posX, posY, posZ);
    return cube;
};

var greenCube1 = makeGreenCube(-1.5, 1, 1),
    greenCube2 = makeGreenCube(1.5, 1, 1),
    greenCube3 = makeGreenCube(-1.5, -1, 1),
    greenCube4 = makeGreenCube(1.5, -1, 1);

var cubes = [cube, greenCube1, greenCube2, greenCube3, greenCube4];

//ForEach example, basically just a function expecting one param
cubes.forEach(function (entry) {
    "use strict";
    scene.add(entry);
});

camera.position.z = 5;


var render = function () {
    "use strict";
    requestAnimationFrame(render);

    //Auto-Resize the rendered window:
    renderer.setSize(window.innerWidth, window.innerHeight);

    cube.rotation.x += 0.01;
    cube.rotation.y += 0.01;

    greenCube1.rotation.x += 0.2;
    greenCube1.rotation.y += 0.2;

    greenCube2.rotation.x += 0.2;
    greenCube2.rotation.y += 0.2;

    greenCube3.rotation.x += 0.2;
    greenCube3.rotation.y += 0.2;

    greenCube4.rotation.x += 0.2;
    greenCube4.rotation.y += 0.2;

    renderer.render(scene, camera);
};

render();