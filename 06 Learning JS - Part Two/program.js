document.writeln('Exercise 09 for IntTech@HSR');

function run(){
    document.write('<pre>');
    print("{a:[3,2,1], b:3}['b']",{a:[3,2,1], b:3}['b']);
    print("{a:[3,2,1], b:3}.a", {a:[3,2,1], b:3}.a);
    print("typeof({a:[3,2,1], b:3}.a[9/3])", typeof({a:[3,2,1], b:3}.a[9/3]));

    a=[0.1,0.2,0.3];
    print("a[0]+a[1]-a[2]==0", a[0]+a[1]-a[2]==0);
    print("a[0]+a[1]-a[2]", a[0]+a[1]-a[2]);
    print("(function(){a=[1,2,3];return(a[1])})()", (function(){a=[1,2,3];return(a[1])})());

    String.prototype.charAt = function(){return 'a'};
    print("String.prototype.charAt = function(){return 'a'}; \"abcdefgh\".charAt(3));  ", "abcdefgh".charAt(3));

    MyClass=function(){var a=1; this.b=2; this.getA=function(){return a};this.getB =function(){return this.b}};
    myObj=new MyClass();
    myObj.a=7; myObj.b=8;
    print("myObj.getA()", myObj.getA());
    print("myObj.getB()", myObj.getB());

    myObj.getA=function(){return a};
    print("myObj.getA()", myObj.getA());
}

run();

function print(original, executed){
    document.writeln(original +" ==> <b>"+executed+"</b>");
}