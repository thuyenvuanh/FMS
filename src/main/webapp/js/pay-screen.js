var value = 0;

function dis(digit) {
    let val = document.getElementById("result").value;
    if (digit !== '.')
    document.getElementById("result").value = val + digit;
}

//function that evaluates the digit and return result
//  function solve()
//  {
//      let x = document.getElementById("result").value
//      let y = eval(x)
//      document.getElementById("result").value = y
//  }

//function that clear the display
function clr() {
    document.getElementById("result").value = ""
}