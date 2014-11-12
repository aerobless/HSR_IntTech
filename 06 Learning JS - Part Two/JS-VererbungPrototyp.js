
// This way of implementing inheritance has the following properties
// Advantages 
//    - fields (such as color) are only accessible through the setters (using closure)
//    - the check "if (this == global) ..." ensures that the constructure is called 
//      with new ... 
// Disadvantages
//    - Every instance creates its own functions anew, this can lead to problems if 
//      large numbers of these objects are needed or time for creation is critical
//    - Method lookup is slightly slower as the method ha to be retrieved from the prototype 


var global = this;

function Shape(initColor) {
  //make sure constructor is called with new Shape...
  if (this == global) { return new Shape(initColor); }

  //lexical "hiding" of the property color
  this._color = String (initColor);
}

// define getColor on Shape.prototype for space efficiency  
Shape.prototype.getColor = function() {
    return this._color;
  }; 
  
function Rectangle(initColor, initX, initY) {
  //make sure constructor is called with new Shape...
  if (this == global) { return new Rectangle(initColor, initX, initY); }

  //lexical "hiding" of the property x, y
  this._x = initX;
  this._y = initY;
 
  // Initalize color
  Shape.apply(this, arguments);

}

//Attention: Changes to the prototype (value/methods) will be visible everywhere; 
//Advantage: default values for fields (e.g. color) can be saved in the prototype
//e.g. by using a regularly constructed Object as prototype as with
//Rectangle.prototype = new Shape('');
//here we init without values
Rectangle.prototype = Object.create(Shape.prototype);

Rectangle.prototype.constructor=Rectangle // be a good citizen

Rectangle.prototype.getX = function() {
    return this._x;
  }; 
Rectangle.prototype.getY = function() {
    return this._y;
  }; 
Rectangle.prototype.getArea = function() {
    return (this._x * this._y);
  }; 

function test () {
  var r = new Rectangle("green", 5, 7);
  var s = new Shape("blue");
  console.log('r='+r);
  console.log('r instanceof Rectangle='+(r instanceof Rectangle)) 
  console.log('r instanceof Shape='+(r instanceof Shape)) 
  console.log('r.getColor()='+r.getColor());
  console.log('r.getArea()='+r.getArea());
  console.log('s='+s);
  console.log('s.getColor()='+r.getColor());
  console.log('s instanceof Rectangle='+(s instanceof Rectangle)) 
  console.log('s instanceof Shape='+(s instanceof Shape)) 
  //error
  //console.log('s.getArea()='+s.getArea());
}