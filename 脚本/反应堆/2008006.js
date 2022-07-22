var objDate = new Date();
var dayD = objDate.getDay();
var day = getDay(dayD);
var music = "";

function getDay(dayD) {
    switch (dayD) {
        case 0:
            day = "有趣的";
            music = "Bgm11/Aquarium";
            break;
        case 1:
            day = "恐怖的";
            music = "Bgm05/WolfWood";
            break;
        case 2:
            day = "有意思";
            music = "Bgm07/FunnyTimeMaker";
            break;
        case 3:
            day = "憂鬱的";
            music = "Bgm02/MissingYou";
            break;
        case 4:
            day = "冰冷冷的";
            music = "Bgm01/AncientMove";
            break;
        case 5:
            day = "晴朗的";
            music = "Bgm02/AboveTheTreetops";
            break;
        case 6:
            day = "雄壯的";
            music = "Bgm00/Nightmare";
            break;
    }
    return day;
}

function act() {
    if (rm != null) {
        rm.changeMusic(music);
        rm.playerMessage(5, "音樂盒撥出了" + day + "音樂。");
    }
    var em = rm.getEventManager("OrbisPQ");
    if (em != null) {
        em.setProperty("stage3", "1");
    }
}