//전화번호 입력시 '-' 자동생성
const autoHyphen = (target) => {
    target.value = target.value
        .replace(/[^0-9]/g,'').replace(/^(\d{3})(\d{3,4})(\d{4})$/g, "$1-$2-$3");
}

let seconds = 0;

let countTime;

/** sms 요청시 실행되는 타이머 함수 */
function count_down_time() {
    let min = parseInt((seconds) / 60);
    let sec = seconds % 60;
    let trueSec = sec < 10 ? '0' + sec : sec;
    $("#spanTime").html(min + " : " + trueSec);

    if (seconds == 0) {
        clearInterval(countTime);
    }
    seconds --;
}