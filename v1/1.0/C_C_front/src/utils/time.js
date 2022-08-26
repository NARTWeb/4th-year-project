function ymd(date) {
    return hm(date, true);
    return date.year + "/" + date.month + "/" + date.day;
}
function ymdh(date) {
    return ymd(date) + " " + date.hour;
}
function mdh(date) {
    return date.month + "/" + date.day + " " + date.hour;
}
function hm(date, isZh) {
    if(isZh) {
        return "昨天 " + date.hour + ":" + date.min;
    }
    return "yesterday " + date.hour + ":" + date.min;
}


export function format(date, isZh) {
    let d = new Date();
    if(date.year == d.getFullYear()) {
        if(date.month == (d.getMonth() + 1)) {
            let dated = 31 - date.day + d.getDate;
            if(dated <= 7) {
                if(dated == 1 && date.hour > d.getHours()) {
                    return hm(date, isZh);
                }
                return mdh(date);
            }
        } else {
            let dated = 31 - date.day + d.getDate;
            if(dated <= 7) {
                if(dated == 1 && date.hour > d.getHours()) {
                    return hm(date, isZh);
                }
                return mdh(date);
            }
        }
    } else {
        if(date.month == 12 && d.getMonth == 0) {
            let dated = 31 - date.day + d.getDate;
            if(dated <= 7) {
                if(dated == 1 && date.hour > d.getHours()) {
                    return hm(date, isZh);
                }
                return ymdh(date);
            }
        }
    }
    return ymd(date);
}