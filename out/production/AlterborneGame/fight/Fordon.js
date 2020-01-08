function fordon (maker, driving){
    this.makername = maker;

    this.drive = function() {
        return driving;
    }
}

function bil(maker) {
    fordon.call(this, maker);

    this.modelnumber = function() {
        return 354;
    }
}

function motorcykel(maker) {

    fordon.call(this, maker);

    this.modelnumber = function() {
        return 65; 
    }
}

bil.prototype = new fordon();
var minicar = new bil("Volvo");
console.log(minicar.maker());


motorcykel.prototype = new fordon();
var snabbcykel = new motorcykel("Epic MC");
console.log(snabbcykel.maker());
