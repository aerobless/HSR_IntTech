a = 7; b = 9; // global

function hoistTest (a) {
  console.log('a='+a);
  console.log('b='+b);
  if (a > 2) {
    var b = a;
  }
  console.log('b='+b);
}
hoistTest(1);
hoistTest(3);


