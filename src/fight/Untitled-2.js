function Fordon(brand){
    this.märke = brand;
}

Fordon.prototype.drive = function(){
    console.log(this.märke+"drives away")
}

function Bil(reg){
    Fordon.call(this,brand);
    var regNr;
    var weels;
    this.countWeels = function(){
        weels = 4;
    }
}

function MotorCykel(reg){
    Fordon.call(this,brand);
    var regNr;
    var weels;
    this.countWeels = function(){
        weels = 2;
    }
}

Bil.prototype = new Fordon;
MotorCykel.prototype = new Fordon;
var car = new Bil("123 456");
var cycle = new MotorCykel("987 654");
var veacle = new Fordon("Volvo");

console.log("Bilmärke: "+car.brand+"Däck: "+car.weels + "Reg: "+car.reg);
console.log("Motorcykelmärke: "+cycle.brand+"Däck: "+cycle.weels + "Reg: "+cycle.reg);
vehicle.drive;
