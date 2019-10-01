
function validateKey(evt, regex){
    var key = evt.keyCode;
    key = String.fromCharCode(key);
    if( !regex.test(key) ) {
        evt.returnValue = false;
        if(evt.preventDefault)
            evt.preventDefault();
    }
}



function validate(){
    var flag=true;
    txtText= document.getElementById("text");
    txtNumber= document.getElementById("number");
    if(txtText.value==""){
        showTooltip(txtText.id);
        flag=false;
    }
    if(txtNumber.value==""){
        showTooltip(txtNumber.id);
        flag=false;
    }
    return flag;
}

function showTooltip(element_id){
    document.getElementById("tooltip_"+element_id).style.visibility ="visible";
}

function hideTooltip(element_id){
    document.getElementById("tooltip_"+element_id).style.visibility ="hidden";
}