var ds = 0;

function start() {
    var Editing = false //false 開始
    if (Editing) {
        cm.sendOk("維修中");
        cm.dispose();
        return;
    }
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1)
        status++;
    else
        status--;
    if (status == 0) {
        if (!cm.haveItem(5150037)) {
            cm.sendSimple("Hello，我是戒指兌換師 \r\n" +
                    "你想花費一些GASH或楓葉點數兌換戒指嗎？\r\n #r也可以到商城購買Floating Market 1688$換到你爽！ \r\n" +
                    //"#L1000##d我想使用1688CASH無限兌換戒指#k\r\n" +
                    //"#L2000##d我想使用1688楓葉點數無限兌換戒指#k\r\n" +
                    "#L3000##d我想使用GASH單次兌換戒指#k\r\n" +
                    "#L4000##d我想使用楓葉點數單次兌換戒指#k\r\n"
                    );
        } else {
            cm.sendSimple("Hello，我是戒指兌換師 \r\n" +
                    "你已經可以無限兌換戒指。 \r\n" +
                    "#L1##d  1. 兌換可樂名牌聊天戒指組合#k\r\n" +
                    "#L2##d  2. 兌換紅底名牌聊天戒指組合#k\r\n" +
                    "#L3##d  3. 兌換白底名牌聊天戒指組合#k\r\n" +
                    "#L4##d  4. 兌換竹編名牌聊天戒指組合#k\r\n" +
                    "#L5##d  5. 兌換水墨名牌聊天戒指組合#k\r\n" +
                    "#L6##d  6. 兌換紅玫瑰名牌聊天戒指組合#k\r\n" +
                    "#L7##d  7. 兌換木乃伊名牌聊天戒指組合#k\r\n" +
                    "#L8##d  8. 兌換豪華珍珠名牌聊天戒指組合#k\r\n" +
                    "#L9##d  9. 兌換喵喵名牌聊天戒指組合#k\r\n" +
                    "#L10##d  10. 兌換浪漫蕾絲名牌聊天戒指組合#k\r\n" +
                    "#L11##d  11. 兌換青蘋果和愛心名牌聊天戒指組合#k\r\n" +
                    "#L12##d  12. 兌換鬍子先生名牌聊天戒指組合#k\r\n" +
                    "#L13##d  13. 兌換NAVER名牌聊天戒指組合#k\r\n" +
                    "#L14##d  14. 兌換天使名牌聊天戒指組合#k\r\n" +
                    "#L15##d  15. 兌換草莓蛋糕名牌聊天戒指組合#k\r\n" +
                    "#L16##d  16. 兌換壓歲錢防禦名牌聊天戒指組合#k\r\n" +
                    "#L17##d  17. 兌換蛙蛙名牌聊天戒指組合#k\r\n" +
                    "#L18##d  18. 兌換粉紅豬名牌聊天戒指組合#k\r\n" +
                    "#L19##d  10. 兌換藍鬍子名牌聊天戒指組合#k\r\n" +
                    "#L20##d  20. 兌換紅鬍子名牌聊天戒指組合#k\r\n" +
                    "#L21##d  21. 兌換西瓜名牌聊天戒指組合#k\r\n" +
                    "#L22##d  22. 兌換呱呱鴨名牌聊天戒指組合#k\r\n" +
                    "#L23##d  23. 兌換環島旅行名牌聊天戒指組合#k\r\n" +
                    "#L24##d  24. 兌換來自星星的我名牌聊天戒指組合#k\r\n" +
                    "#L25##d  25. 兌換綠森林名牌聊天戒指組合#k\r\n" +
                    "#L26##d  26. 兌換星星名牌聊天戒指組合#k\r\n" +
                    "#L27##d  27. 兌換白色小狗名牌聊天戒指組合#k\r\n" +
                    "#L28##d  28. 兌換褐色小狗名牌聊天戒指組合#k\r\n" +
                    //"#L29##d  29. 兌換高音名牌聊天戒指組合#k\r\n" +
                    "#L30##d  30. 兌換進擊的巨人名牌聊天戒指組合#k\r\n" +
                    "#L31##d  31. 兌換夢想中的雪景名牌聊天戒指組合#k\r\n" +
                    "#L32##d  32. 兌換小朋友名牌聊天戒指組合#k\r\n" +
                    "#L33##d  33. 兌換彈性富豪名牌聊天戒指組合#k\r\n" +
                    "#L34##d  34. 兌換黑綠色帽子名牌聊天戒指組合#k\r\n" +
                    "#L35##d  35. 兌換綠色帽子名牌聊天戒指組合#k\r\n" +
                    "#L36##d  36. 兌換藍色帽子名牌聊天戒指組合#k\r\n" +
                    "#L37##d  37. 兌換晚安小怪獸名牌聊天戒指組合#k\r\n" +
                    "#L38##d  38. 兌換吃貨小怪獸名牌聊天戒指組合#k\r\n" +
                    "#L39##d  39. 兌換英雄雪歸島名牌聊天戒指組合#k\r\n" +
                    "#L40##d  40. 兌換英雄戴米安名牌聊天戒指組合#k\r\n" +
                    "#L41##d  41. 兌換英雄超越石名牌聊天戒指組合#k\r\n" +
                    "#L42##d  42. 兌換英雄黑魔法師名牌聊天戒指組合#k\r\n" +
                    "#L43##d  43. 兌換紅雲組合包名牌聊天戒指組合#k\r\n" +
                    "#L44##d  44. 兌換聖誕名牌聊天戒指組合#k\r\n" +
                    "#L45##d  45. 兌換傳說的黃金戒指#k\r\n" +
                    "#L46##d  46. 兌換大香腸名牌戒指#k\r\n" +
                    "#L47##d  47. 兌換名牌戒指 (鏡音鈴&連)#k\r\n" +
                    "#L48##d  48. 藍莓聊天戒指#k\r\n" +
                    "#L49##d  49. 草莓聊天戒指#k\r\n" +
                    "#L50##d  50. 鑽石聊天戒指#k\r\n" +
                    "#L51##d  51. 盛夏彩蝶聊天戒指#k\r\n" +
                    "#L52##d  52. 聊天戒指 (初音未來)#k\r\n" +
                    "#L53##d  53. 聊天戒指 (巡音流歌)#k\r\n" +
                    "#L54##d  54. 聊天戒指 (KAITO)#k\r\n" +
                    "#L55##d  55. 可愛兔聊天戒指#k\r\n" +
                    "#L56##d  56. 可愛羊聊天戒指#k\r\n" +
                    "#L57##d  57. 白雪聖誕聊天泡泡戒指#k\r\n" +
                    "#L58##d  58. 草原小羊聊天泡泡戒指#k\r\n" +
                    "#L59##d  59. 蜜蜂聊天泡泡戒指#k\r\n" +
                    "#L60##d  60. 鳳梨聊天戒指#k\r\n" +
                    "#L61##d  61. 公主日記聊天泡泡戒指#k\r\n" +
                    "#L62##d  62. 兔子聊天泡泡戒指#k\r\n" +
                    "#L63##d  63. 陽光牧場聊天泡泡戒指#k\r\n" +
                    "#L64##d  64. 鯊魚聊天泡泡戒指#k\r\n" +
                    "#L65##d  65. 貓咪線球聊天泡泡戒指#k\r\n" +
                    "#L66##d  66. 後街吉姆泡泡戒指#k\r\n"
                    );
        }


    } else if (status == 1) {

        /*if (selection == 1000) {
         if (cm.getPlayer().getCSPoints(1) < 1688) {
         cm.sendOk("#b你的Cash不夠哦");
         cm.dispose();
         return;
         } else {
         cm.getPlayer().modifyCSPoints(1, -1688, true);
         cm.getPlayer().setOneTimeLog("戒指兌換");
         cm.sendOk("#b可以無限制領取戒指了。~");
         cm.dispose();
         return;
         }
         cm.dispose();
         } else if (selection == 2000) {
         if (cm.getPlayer().getCSPoints(2) < 1688) {
         cm.sendOk("#b你的Cash不夠哦");
         cm.dispose();
         return;
         } else {
         cm.getPlayer().modifyCSPoints(2, -1688, true);
         cm.getPlayer().setOneTimeLog("戒指兌換");
         cm.sendOk("#b可以無限制領取戒指了。~");
         cm.dispose();
         return;
         }
         cm.dispose();
         } else */if (selection == 3000) {
            ds = 1;
            cm.sendSimple("Hello，我是戒指兌換師 \r\n" +
                    "你已經可以無限兌換戒指。 \r\n" +
                    "#L1##d  1. 兌換可樂名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L2##d  2. 兌換紅底名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L3##d  3. 兌換白底名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L4##d  4. 兌換竹編名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L5##d  5. 兌換水墨名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L6##d  6. 兌換紅玫瑰名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L7##d  7. 兌換木乃伊名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L8##d  8. 兌換豪華珍珠名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L9##d  9. 兌換喵喵名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L10##d  10. 兌換浪漫蕾絲名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L11##d  11. 兌換青蘋果和愛心名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L12##d  12. 兌換鬍子先生名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L13##d  13. 兌換NAVER名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L14##d  14. 兌換天使名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L15##d  15. 兌換草莓蛋糕名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L16##d  16. 兌換壓歲錢防禦名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L17##d  17. 兌換蛙蛙名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L18##d  18. 兌換粉紅豬名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L19##d  10. 兌換藍鬍子名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L20##d  20. 兌換紅鬍子名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L21##d  21. 兌換西瓜名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L22##d  22. 兌換呱呱鴨名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L23##d  23. 兌換環島旅行名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L24##d  24. 兌換來自星星的我名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L25##d  25. 兌換綠森林名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L26##d  26. 兌換星星名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L27##d  27. 兌換白色小狗名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L28##d  28. 兌換褐色小狗名牌聊天戒指組合(200Cash)#k\r\n" +
                    //"#L29##d  29. 兌換高音名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L30##d  30. 兌換進擊的巨人名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L31##d  31. 兌換夢想中的雪景名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L32##d  32. 兌換小朋友名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L33##d  33. 兌換彈性富豪名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L34##d  34. 兌換黑綠色帽子名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L35##d  35. 兌換綠色帽子名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L36##d  36. 兌換藍色帽子名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L37##d  37. 兌換晚安小怪獸名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L38##d  38. 兌換吃貨小怪獸名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L39##d  39. 兌換英雄雪歸島名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L40##d  40. 兌換英雄戴米安名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L41##d  41. 兌換英雄超越石名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L42##d  42. 兌換英雄黑魔法師名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L43##d  43. 兌換紅雲組合包名牌聊天戒指組合(200Cash)#k\r\n" +
                    "#L44##d  44. 兌換聖誕名牌聊天戒指組(200Cash)合#k\r\n" +
                    "#L45##d  45. 兌換傳說的黃金戒指(100Cash)#k\r\n" +
                    "#L46##d  46. 兌換大香腸名牌戒指(100Cash)#k\r\n" +
                    "#L47##d  47. 兌換名牌戒指 (鏡音鈴&連)(100Cash)#k\r\n" +
                    "#L48##d  48. 藍莓聊天戒指(100Cash)#k\r\n" +
                    "#L49##d  49. 草莓聊天戒指(100Cash)#k\r\n" +
                    "#L50##d  50. 鑽石聊天戒指(100Cash)#k\r\n" +
                    "#L51##d  51. 盛夏彩蝶聊天戒指(100Cash)#k\r\n" +
                    "#L52##d  52. 聊天戒指 (初音未來)(100Cash)#k\r\n" +
                    "#L53##d  53. 聊天戒指 (巡音流歌)(100Cash)#k\r\n" +
                    "#L54##d  54. 聊天戒指 (KAITO)(100Cash)#k\r\n" +
                    "#L55##d  55. 可愛兔聊天戒指(100Cash)#k\r\n" +
                    "#L56##d  56. 可愛羊聊天戒指(100Cash)#k\r\n" +
                    "#L57##d  57. 白雪聖誕聊天泡泡戒指(100Cash)#k\r\n" +
                    "#L58##d  58. 草原小羊聊天泡泡戒指(100Cash)#k\r\n" +
                    "#L59##d  59. 蜜蜂聊天泡泡戒指(100Cash)#k\r\n" +
                    "#L60##d  60. 鳳梨聊天戒指(100Cash)#k\r\n" +
                    "#L61##d  61. 公主日記聊天泡泡戒指(100Cash)#k\r\n" +
                    "#L62##d  62. 兔子聊天泡泡戒指(100Cash)#k\r\n" +
                    "#L63##d  63. 陽光牧場聊天泡泡戒指(100Cash)#k\r\n" +
                    "#L64##d  64. 鯊魚聊天泡泡戒指(100Cash)#k\r\n" +
                    "#L65##d  65. 貓咪線球聊天泡泡戒指(100Cash)#k\r\n" +
                    "#L66##d  66. 後街吉姆泡泡戒指(100Cash)#k\r\n");
        } else if (selection == 4000) {
            ds = 2;
            cm.sendSimple("Hello，我是戒指兌換師 \r\n" +
                    "你已經可以無限兌換戒指。 \r\n" +
                    "#L1##d  1. 兌換可樂名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L2##d  2. 兌換紅底名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L3##d  3. 兌換白底名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L4##d  4. 兌換竹編名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L5##d  5. 兌換水墨名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L6##d  6. 兌換紅玫瑰名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L7##d  7. 兌換木乃伊名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L8##d  8. 兌換豪華珍珠名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L9##d  9. 兌換喵喵名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L10##d  10. 兌換浪漫蕾絲名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L11##d  11. 兌換青蘋果和愛心名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L12##d  12. 兌換鬍子先生名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L13##d  13. 兌換NAVER名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L14##d  14. 兌換天使名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L15##d  15. 兌換草莓蛋糕名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L16##d  16. 兌換壓歲錢防禦名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L17##d  17. 兌換蛙蛙名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L18##d  18. 兌換粉紅豬名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L19##d  10. 兌換藍鬍子名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L20##d  20. 兌換紅鬍子名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L21##d  21. 兌換西瓜名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L22##d  22. 兌換呱呱鴨名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L23##d  23. 兌換環島旅行名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L24##d  24. 兌換來自星星的我名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L25##d  25. 兌換綠森林名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L26##d  26. 兌換星星名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L27##d  27. 兌換白色小狗名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L28##d  28. 兌換褐色小狗名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    //"#L29##d  29. 兌換高音名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L30##d  30. 兌換進擊的巨人名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L31##d  31. 兌換夢想中的雪景名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L32##d  32. 兌換小朋友名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L33##d  33. 兌換彈性富豪名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L34##d  34. 兌換黑綠色帽子名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L35##d  35. 兌換綠色帽子名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L36##d  36. 兌換藍色帽子名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L37##d  37. 兌換晚安小怪獸名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L38##d  38. 兌換吃貨小怪獸名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L39##d  39. 兌換英雄雪歸島名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L40##d  40. 兌換英雄戴米安名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L41##d  41. 兌換英雄超越石名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L42##d  42. 兌換英雄黑魔法師名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L43##d  43. 兌換紅雲組合包名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L44##d  44. 兌換聖誕名牌聊天戒指組合(200楓葉點數)#k\r\n" +
                    "#L45##d  45. 兌換傳說的黃金戒指(100楓葉點數)#k\r\n" +
                    "#L46##d  46. 兌換大香腸名牌戒指(100楓葉點數)#k\r\n" +
                    "#L47##d  47. 兌換名牌戒指 (鏡音鈴&連)(100楓葉點數)#k\r\n" +
                    "#L48##d  48. 藍莓聊天戒指(100楓葉點數)#k\r\n" +
                    "#L49##d  49. 草莓聊天戒指(100楓葉點數)#k\r\n" +
                    "#L50##d  50. 鑽石聊天戒指(100楓葉點數)#k\r\n" +
                    "#L51##d  51. 盛夏彩蝶聊天戒指(100楓葉點數)#k\r\n" +
                    "#L52##d  52. 聊天戒指 (初音未來)(100楓葉點數)#k\r\n" +
                    "#L53##d  53. 聊天戒指 (巡音流歌)(100楓葉點數)#k\r\n" +
                    "#L54##d  54. 聊天戒指 (KAITO)(100楓葉點數)#k\r\n" +
                    "#L55##d  55. 可愛兔聊天戒指(100楓葉點數)#k\r\n" +
                    "#L56##d  56. 可愛羊聊天戒指(100楓葉點數)#k\r\n" +
                    "#L57##d  57. 白雪聖誕聊天泡泡戒指(100楓葉點數)#k\r\n" +
                    "#L58##d  58. 草原小羊聊天泡泡戒指(100楓葉點數)#k\r\n" +
                    "#L59##d  59. 蜜蜂聊天泡泡戒指(100楓葉點數)#k\r\n" +
                    "#L60##d  60. 鳳梨聊天戒指(100楓葉點數)#k\r\n" +
                    "#L61##d  61. 公主日記聊天泡泡戒指(100楓葉點數)#k\r\n" +
                    "#L62##d  62. 兔子聊天泡泡戒指(100楓葉點數)#k\r\n" +
                    "#L63##d  63. 陽光牧場聊天泡泡戒指(100楓葉點數)#k\r\n" +
                    "#L64##d  64. 鯊魚聊天泡泡戒指(100楓葉點數)#k\r\n" +
                    "#L65##d  65. 貓咪線球聊天泡泡戒指(100楓葉點數)#k\r\n" +
                    "#L66##d  66. 後街吉姆泡泡戒指(100楓葉點數)#k\r\n");
        } else if (selection == 1) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112118, 1);
                    cm.gainItem(1112228, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 2) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112119, 1);
                    cm.gainItem(1112229, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 3) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112120, 1);
                    cm.gainItem(1112230, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 4) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112134, 1);
                    cm.gainItem(1112237, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 5) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112135, 1);
                    cm.gainItem(1112238, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 6) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112141, 1);
                    cm.gainItem(1112252, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 7) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112142, 1);
                    cm.gainItem(1112253, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 8) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112143, 1);
                    cm.gainItem(1112254, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 9) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112144, 1);
                    cm.gainItem(1112256, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 10) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112145, 1);
                    cm.gainItem(1112257, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 11) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112146, 1);
                    cm.gainItem(1112258, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 12) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112148, 1);
                    cm.gainItem(1112259, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 13) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112149, 1);
                    cm.gainItem(1112261, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 14) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112150, 1);
                    cm.gainItem(1112262, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 15) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112151, 1);
                    cm.gainItem(1112263, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 16) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112154, 1);
                    cm.gainItem(1112266, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 17) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112155, 1);
                    cm.gainItem(1112267, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 18) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112156, 1);
                    cm.gainItem(1112268, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 19) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112157, 1);
                    cm.gainItem(1112269, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 20) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112158, 1);
                    cm.gainItem(1112270, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 21) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112160, 1);
                    cm.gainItem(1112272, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 22) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112161, 1);
                    cm.gainItem(1112273, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 23) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112162, 1);
                    cm.gainItem(1112274, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 24) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112163, 1);
                    cm.gainItem(1112275, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 25) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112165, 1);
                    cm.gainItem(1112277, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 26) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112170, 1);
                    cm.gainItem(1112282, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 27) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112171, 1);
                    cm.gainItem(1112283, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 28) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112172, 1);
                    cm.gainItem(1112284, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
            /*} else if (selection == 29) {
             if (!cm.haveItem(5150037)) {
             cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
             cm.dispose();
             return;
             } else {
             if (cm.canHoldByType(1, 3)) {
             cm.gainItem(1112176, 1);
             cm.gainItem(1112288, 1);
             cm.sendOk("領取成功!");
             cm.dispose();
             return;
             } else {
             cm.sendOk("前請確認裝備欄是否有空格!");
             cm.dispose();
             return;
             }
             cm.dispose();
             return;
             }
             cm.dispose();*/
        } else if (selection == 30) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112177, 1);
                    cm.gainItem(1112289, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 31) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112178, 1);
                    cm.gainItem(1112290, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 32) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112182, 1);
                    cm.gainItem(1112295, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 33) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112184, 1);
                    cm.gainItem(1115030, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 34) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112194, 1);
                    cm.gainItem(1115007, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 35) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112195, 1);
                    cm.gainItem(1115008, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 36) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112196, 1);
                    cm.gainItem(1115009, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 37) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112197, 1);
                    cm.gainItem(1115010, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 38) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1112198, 1);
                    cm.gainItem(1115011, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 39) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1115103, 1);
                    cm.gainItem(1115016, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 40) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1115108, 1);
                    cm.gainItem(1115019, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 41) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1115109, 1);
                    cm.gainItem(1115020, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 42) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1115110, 1);
                    cm.gainItem(1115021, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 43) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1115116, 1);
                    cm.gainItem(1115027, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 44) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 3)) {
                    cm.gainItem(1115104, 1);
                    cm.gainItem(1115017, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 45) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1112103, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 46) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1112136, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 47) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1112168, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 48) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1112264, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 49) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1112265, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 50) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1112271, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 51) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1112276, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 52) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1112279, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 53) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1112280, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 54) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1112281, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 55) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1112285, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 56) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1112294, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 57) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1112291, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 58) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1112296, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 59) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1115004, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 60) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1115005, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 61) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1115006, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 62) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1115022, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 63) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1115023, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 64) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1112025, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 65) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1115026, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        } else if (selection == 66) {
            if (!cm.haveItem(5150037)) {
                cm.sendOk("#b你還沒有花費1688CASH或楓葉點數無限兌換戒指");
                cm.dispose();
                return;
            } else {
                if (cm.canHoldByType(1, 2)) {
                    cm.gainItem(1115029, 1);
                    cm.sendOk("領取成功!");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("前請確認裝備欄是否有空格!");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                return;
            }
            cm.dispose();
        }

    } else if (status == 2) {
        if (selection == 1) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112118, 1);
                cm.gainItem(1112228, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 2) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112119, 1);
                cm.gainItem(1112229, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 3) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112120, 1);
                cm.gainItem(1112230, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;

        } else if (selection == 4) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112134, 1);
                cm.gainItem(1112237, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;

        } else if (selection == 5) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112135, 1);
                cm.gainItem(1112238, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;

        } else if (selection == 6) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112141, 1);
                cm.gainItem(1112252, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 7) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112142, 1);
                cm.gainItem(1112253, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 8) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112143, 1);
                cm.gainItem(1112254, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 9) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112144, 1);
                cm.gainItem(1112256, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 10) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112145, 1);
                cm.gainItem(1112257, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 11) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112146, 1);
                cm.gainItem(1112258, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 12) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112148, 1);
                cm.gainItem(1112259, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 13) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112149, 1);
                cm.gainItem(1112261, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 14) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112150, 1);
                cm.gainItem(1112262, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 15) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112151, 1);
                cm.gainItem(1112263, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 16) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112154, 1);
                cm.gainItem(1112266, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 17) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112155, 1);
                cm.gainItem(1112267, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 18) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112156, 1);
                cm.gainItem(1112268, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 19) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112157, 1);
                cm.gainItem(1112269, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 20) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112158, 1);
                cm.gainItem(1112270, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 21) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112160, 1);
                cm.gainItem(1112272, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 22) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112161, 1);
                cm.gainItem(1112273, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 23) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112162, 1);
                cm.gainItem(1112274, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 24) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112163, 1);
                cm.gainItem(1112275, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 25) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112165, 1);
                cm.gainItem(1112277, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 26) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112170, 1);
                cm.gainItem(1112282, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 27) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112171, 1);
                cm.gainItem(1112283, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 28) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112172, 1);
                cm.gainItem(1112284, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
            /*} else if (selection == 29) {
             if (ds == 1) {
             if (cm.getPlayer().getCSPoints(1) < 200) {
             cm.sendOk("#b你的Cash不夠哦");
             cm.dispose();
             return;
             }
             } else if (ds == 2){
             if (cm.getPlayer().getCSPoints(2) < 200) {
             cm.sendOk("#b你的楓葉點數不夠哦");
             cm.dispose();
             return;
             }
             }
             if (cm.canHoldByType(1, 3)) {
             cm.getPlayer().modifyCSPoints(ds, -200, true);
             cm.gainItem(1112176, 1);
             cm.gainItem(1112288, 1);
             cm.sendOk("領取成功!");
             cm.dispose();
             return;
             } else {
             cm.sendOk("前請確認裝備欄是否有空格!");
             cm.dispose();
             return;
             }
             cm.dispose();
             return;*/
        } else if (selection == 30) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112177, 1);
                cm.gainItem(1112289, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 31) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112178, 1);
                cm.gainItem(1112290, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 32) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112182, 1);
                cm.gainItem(1112295, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 33) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112184, 1);
                cm.gainItem(1115030, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 34) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112194, 1);
                cm.gainItem(1115007, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 35) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112195, 1);
                cm.gainItem(1115008, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 36) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112196, 1);
                cm.gainItem(1115009, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 37) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112197, 1);
                cm.gainItem(1115010, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 38) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1112198, 1);
                cm.gainItem(1115011, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 39) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1115103, 1);
                cm.gainItem(1115016, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 40) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1115108, 1);
                cm.gainItem(1115019, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 41) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1115109, 1);
                cm.gainItem(1115020, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 42) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1115110, 1);
                cm.gainItem(1115021, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 43) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1115116, 1);
                cm.gainItem(1115027, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 44) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 200) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 200) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 3)) {
                cm.getPlayer().modifyCSPoints(ds, -200, true);
                cm.gainItem(1115104, 1);
                cm.gainItem(1115017, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 45) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1112103, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 46) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1112136, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 47) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1112168, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 48) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1112264, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 49) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1112265, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 50) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1112271, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 51) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1112276, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 52) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1112279, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 53) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1112280, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 54) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1112281, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 55) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1112285, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 56) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1112294, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 57) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1112291, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 58) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1112296, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 59) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1115004, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 60) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1115005, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 61) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1115006, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 62) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1115022, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 63) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1115023, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 64) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1112025, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 65) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1115026, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        } else if (selection == 66) {
            if (ds == 1) {
                if (cm.getPlayer().getCSPoints(1) < 100) {
                    cm.sendOk("#b你的Cash不夠哦");
                    cm.dispose();
                    return;
                }
            } else if (ds == 2) {
                if (cm.getPlayer().getCSPoints(2) < 100) {
                    cm.sendOk("#b你的楓葉點數不夠哦");
                    cm.dispose();
                    return;
                }
            }
            if (cm.canHoldByType(1, 2)) {
                cm.getPlayer().modifyCSPoints(ds, -100, true);
                cm.gainItem(1115029, 1);
                cm.sendOk("領取成功!");
                cm.dispose();
                return;
            } else {
                cm.sendOk("前請確認裝備欄是否有空格!");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        }

    }
}
