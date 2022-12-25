function ymd(date) {
    return date.year + "/" + date.month + "/" + date.day;
}
function ymdh(date) {
    return ymd(date) + " " + date.hour;
}
function mdh(date) {
    return date.month + "/" + date.day + " " + thm(date);
}
function hm(date, isZh) {
    if(isZh) {
        return "昨天 " + thm(date);
    }
    return "yesterday " + thm(date);
}
function thm(date) {
    return date.hour + ":" + date.min;
}

function format(date, isZh) {
    let d = new Date();
    //console.log(date);
    if(date.year == d.getFullYear()) {
        if(date.month == (d.getMonth() + 1)) {
            let dated =  d.getDate() - date.day;
            if(dated <= 7) {
                if(date.day == d.getDate()) {
                    return thm(date);
                }
                if(dated == 1 && date.hour > d.getHours()) {
                    return hm(date, isZh);
                }
                return mdh(date);
            }
        } else {
            let dated = 31 - date.day + d.getDate();
            if(dated <= 7) {
                if(dated == 1 && date.hour > d.getHours()) {
                    return hm(date, isZh);
                }
                return mdh(date);
            }
        }
    } else {
        if(date.month == 12 && d.getMonth() == 0) {
            let dated = 31 - date.day + d.getDate();
            if(dated <= 7) {
                if(date.day == d.getDate()) {
                    return thm(date);
                }
                if(dated == 1 && date.hour > d.getHours()) {
                    return hm(date, isZh);
                }
                return ymdh(date);
            }
        }
    }
    return ymd(date);
}
function now() {
    let d = new Date();
    return "just now!";
}

function test() {
    //'2022-12-31T12:20:00'
    testSet1 = [
        {year: 2022, month: 12, day: 30, hour: 15, min: 15},
        {year: 2022, month: 12, day: 31, hour: 8, min: 15},
        {year: 2022, month: 12, day: 5, hour: 15, min: 15},
        {year: 2023, month: 1, day: 1, hour: 15, min: 15},
    ];
    //'2022-12-01T12:20:00'
    testSet2 = [
        {year: 2022, month: 11, day: 30, hour: 15, min: 15},
        {year: 2022, month: 11, day: 30, hour: 8, min: 15},
        {year: 2022, month: 11, day: 25, hour: 15, min: 15},
    ];
    //'2023-01-01T12:20:00'
    testSet3 = [
        {year: 2022, month: 12, day: 31, hour: 15, min: 15},
        {year: 2022, month: 11, day: 31, hour: 8, min: 15},
    ];
    for(const v of testSet3) {
        console.log(v);
        console.log(format(v));
        console.log('------------');
    }
}

export {format, now};