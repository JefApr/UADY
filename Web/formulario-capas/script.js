var alertError = document.getElementById("error");
var lockScreen = document.getElementById("lockScreen");
var alertSuccess = document.getElementById("success");
var alertMessage= document.getElementById("alertMessage");

var txtName = document.getElementById("name");
var errorName = document.getElementById("error-name");

var txtPassword = document.getElementById("password");
var txtPassword2 = document.getElementById("password2");
var errorPassword = document.getElementById("error-password");

var cmbPerfil = document.getElementById("perfil");
var errorPerfil = document.getElementById("error-perfil");

var alertActivo = false;
var elementError;

function validar() {
    var esValido=true;
  if (!nombreValido()) {
    errorName.style.visibility = "visible";
    showError("El nombre no debe estar vacío.", txtName);
    esValido=false;
  }else{
    errorName.style.visibility = "hidden";
  }
if(!passwordValido()){
    errorPassword.style.visibility="visible";
    showError("Las contraseñas no coinciden", txtPassword);
    txtPassword.value="";
    txtPassword2.value="";
    esValido=false;
}else{
    errorPassword.style.visibility="hidden";
}

if(!perfilValido()){
    errorPerfil.style.visibility="visible";
    showError("No ha seleccionado un perfil", cmbPerfil);
    esValido=false;
}else{
    errorPerfil.style.visibility="hidden";
}

if(esValido){
    showSuccess();
}

}

function nombreValido() {
  return txtName.value != "";
}

function passwordValido(){
    return txtPassword.value!="" && txtPassword.value===txtPassword2.value;
}

function perfilValido(){
    return cmbPerfil.value!=0;
}

function showError(message, element) {
  if (!alertActivo) {
    alertError.classList.toggle("alert-show");
    alertMessage.innerHTML= message;
    lockScreen.classList.toggle("lock-screen-show");
    alertActivo=true; 
    elementError= element;
  }
}

function showSuccess() {
  errorName.style.visibility = "hidden";
  alertSuccess.classList.toggle("alert-show");
  lockScreen.classList.toggle("lock-screen-show");
}

function hideError() {
  alertError.classList.remove("alert-show");
  lockScreen.classList.remove("lock-screen-show");
  lockScreen.classList.toggle("alert-hide");
  alertError.classList.toggle("alert-hide");
  alertActivo=false;
  elementError.focus();
}

function hideSuccess() {
  alertSuccess.classList.remove("alert-show");
  lockScreen.classList.remove("lock-screen-show");
  lockScreen.classList.toggle("alert-hide");
  alertSuccess.classList.toggle("alert-hide");
}
