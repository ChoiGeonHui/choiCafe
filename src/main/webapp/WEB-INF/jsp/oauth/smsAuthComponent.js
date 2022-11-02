/** SMS 인증메일 전송 */
$("#sendMessage").on('click', function() {

    let phone = $("#phone").val();

    //전화번호 정규식 확인 하기
    var regExp =/(01[016789])([1-9]{1}[0-9]{2,3})([0-9]{4})$/;

    if (!regExp.test(phone.replace(/-/g,''))) {
        alert('전화번호를 다시 입력하세요.');
        return;
    }

    $.ajax({
        type: "POST",
        url: "http://192.168.3.116:8880/sendMessage",
        data: {'trPhone': phone},
        success: function (data) {
            if (data.result == 'success') {
                alert('해당번호로 인증메일을 전송하였습니다. \n제한시간 안에 인증번호를 입력하세요.');
                if (seconds > 0) {
                    seconds = 120;
                } else {
                    seconds = 120;
                    countTime = setInterval(count_down_time, 1000);
                }
            } else {
                alert('오류발생.');
            }
        },
        error: function () {
            alert('에러발생');
        }
    });

    // new Promise((succ, fail)=> {
    //     $.ajax({
    //         type: "POST",
    //         url: "http://localhost:8880/sendMessage",
    //         data: {'trPhone': phone},
    //         success: function (data) {
    //             succ(data);
    //         },
    //         error: function () {
    //             alert('에러발생');
    //         }
    //     });
    //
    // }).then((arg) => {
    //     $.ajax({
    //         type: "POST",
    //         url: "/smsCheck/insert",
    //         data: {'phone': phone, 'checkNumber' : arg.checkNum},
    //         success : function (data2) {
    //             if (data2.result == 'success') {
    //                 alert('해당번호로 인증메일을 전송하였습니다. \n제한시간 안에 인증번호를 입력하세요.');
    //                 seconds = 90;
    //                 countTime = setInterval(count_down_time, 1000);
    //             }
    //         },
    //         error : function () {
    //             alert("에러발생2");
    //         }
    //     })
    // })
});

/** 인증번호 입력시 실행되는 함수 */
$("#checkNumber").on('click', function () {

    let phone = $("#phone").val();
    let inputMessageNum = $("#inputMessageNum").val();

    if (seconds <= 0) {
        alert('인증번호를 다시 받으세요.');
        return;
    }

    $.ajax({
        type: "POST",
        url: "http://192.168.3.116:8880/smsCheck/select",
        data: {'phone' : phone, 'checkNumber' : inputMessageNum},
        success : function (data) {
            if (data.result == 'success') {
                alert('인증되었습니다.');
                $(".phoneInput").prop("readonly", true);
                $(".smsCheck").prop("disabled", true);
                seconds = 0;
            } else {
                alert('불일치');
            }
        },
        error : function () {
            alert("오류발생!");
        }
    });

});