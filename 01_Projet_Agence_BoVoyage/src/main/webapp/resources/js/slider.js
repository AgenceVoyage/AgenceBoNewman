console.log("slider")

var slideIndex = 1;
showDivs(slideIndex,1);

function plusDivs(incr,v_id){
	
	if(incr==1){
		console.log("+1  //"+v_id)
	}else{
		console.log("-1  //"+v_id)
	}
	
	showDivs(slideIndex += incr,v_id);
}


function showDivs(n,v_id) {
	console.log("Bouge")
    var i;
    var x = document.getElementsByClassName("voyage_"+v_id);
    if (n > x.length) {slideIndex = 1} 
    if (n < 1) {slideIndex = x.length} ;
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none"; 
    }
    x[slideIndex-1].style.display = "block"; 
}


setInterval(lol, 5);
function lol(){
	console.log("lol")
}

function funcName() {
    alert("test");
}

var func = funcName();
var run = setInterval(funcName(),10000)