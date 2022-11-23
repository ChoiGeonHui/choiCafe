<%--
  Created by IntelliJ IDEA.
  User: adnstyle1153
  Date: 2022-11-03
  Time: 오전 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" charset="UTF-8">

    const autoHyphen = (target) => {
        target.value = target.value
            .replace(/[^0-9]/g,'').replace(/^(\d{3})(\d{3,4})(\d{4})$/g, "$1-$2-$3");
    }

    let seconds = 0; // 남은시간

    let inputCount = 0; //입력 횟수

    let countTime; //타이머 함수

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


    $(document).ready(function () {

        /** SMS 인증메일 전송 */
        $("#sendMessage").on('click', function () {

            let phone = $("#phone").val();

            //전화번호 정규식 확인 하기
            var regExp = /(01[016789])([1-9]{1}[0-9]{2,3})([0-9]{4})$/;

            if (!regExp.test(phone.replace(/-/g, ''))) {
                alert('전화번호를 다시 입력하세요.');
                return;
            }

            if (seconds > 60) {
                alert("1분이 지난 다음에 인증번호를 다시 받으세요.");
                return;
            }

            $.ajax({
                type: "POST",
                url: "https://sms-auth.adnstyle.com/sendMessage",
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
                        inputCount = 0;
                    } else {
                        alert('오류발생.');
                    }
                },
                error: function () {
                    alert('에러발생');
                }
            });

        });

        /** 인증번호 입력시 실행되는 함수 */
        $("#checkNumber").on('click', function () {

            let phone = $("#phone").val();
            let inputMessageNum = $("#inputMessageNum").val();

            if (seconds <= 0) { //시간초과 했을 경우
                alert('인증번호를 다시 받으세요.');
                return;
            }

            if (inputCount > 4) { // 입력횟수 초과
                alert('인증번호를 5회 틀리셨습니다. \n 인증번호를 재발급 받으세요.');
                seconds = 1;
                return;
            }

            $.ajax({
                type: "POST",
                url: "https://sms-auth.adnstyle.com/smsCheck/select",
                data: {'phone': phone, 'checkNumber': inputMessageNum},
                success: function (data) {
                    if (data.result == 'success') {
                        alert('인증되었습니다.');
                        $(".phoneInput").prop("readonly", true);
                        $(".smsCheck").prop("disabled", true);
                        $('.pwd').removeClass('d-none');
                        seconds = 0;
                    } else {
                        alert('불일치');
                        inputCount = inputCount + 1;
                    }
                },
                error: function () {
                    alert("오류발생!");
                }
            });

        });

    })

</script>
