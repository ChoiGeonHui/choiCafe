<%--
  Created by IntelliJ IDEA.
  User: adnstyle1153
  Date: 2022-09-22
  Time: 오후 1:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">

    //페이징, 일괄삭제 체크박스 및 버튼 스크립트

    let cookie = document.cookie;

    console.log('cookie: '+cookie);

    function selectAll(){

        const selectAll = document.querySelector("#selectAll");

        if (selectAll.checked){
            $('.selectChk').prop('checked',true);
        } else {
            $('.selectChk').prop('checked',false);
        }
    }

    function checkedAll() {
        const totalCheckBox = document.querySelectorAll(".selectChk"); // 전체 체크박스
        const totalCheckedBox = document.querySelectorAll(".selectChk:checked"); //선택한 체크박스
        const selectAll = document.querySelector("#selectAll"); //일괄 선택 체크박스

        if (totalCheckBox.length === totalCheckedBox.length) {
            selectAll.checked = true;
        } else {
            selectAll.checked = false;
        }
    }

    $(document).ready(function () {

        $('#selectDel').on('click', function () {
            let seq = [];

            $('.selectChk:checked').each(function () {
                seq.push($(this).data('checkbox'));
            })

            if (seq.length == 0) {
                alert('삭제할 항목이 없습니다.');
                return;
            }

            if (confirm('삭제 하시겠습니까? 삭제할 게시글의 번호는 ' + seq + ' 입니다.')) {

                $.ajax({
                    type: "POST",
                    url: "/board/delete",
                    data: {"seq" : seq},
                    success: function (data) {
                        if (data.result == 'success') {
                            alert('삭제를 완료하였습니다.');
                            location.reload();
                        } else {
                            alert('오류발생');
                        }
                    },
                    error: function () {
                        alert('에러발생!');
                    }
                })
            }
        })

        $('.btnPage').on('click', function (e) {
            e.preventDefault(); //href 이동 안함
            let pageNumber = $(this).data('page-number');
            let searchWord = $('#search').val();
            let searchName = $('#searchName').val();
            let boardHandle = $('#boardHandle').val();
            let category = $('#category').val();

            let hrefString = '/board/list/'+boardHandle+'?page=' + pageNumber;

            if (category != null && category != '') {
                hrefString = hrefString + '&category=' + category;
            }
            if (searchWord != null && searchWord != '') { //검색 값이 있을 경우
                hrefString = hrefString + '&searchWord=' + searchWord + '&searchName=' + searchName;
            }

            location.href = hrefString;

        });

        //검색 기능
        // $('#btnSearch').on('click', function () {
        //     let searchWord = $('#search').val();
        //     let searchName = $('#searchName').val();
        //
        //     if (searchWord == '' || searchWord == null) {
        //         alert('최소 1글자 이상 입력해주세요.');
        //         return;
        //     }
        //     location.href = '/board/list?searchWord=' + searchWord + '&searchName=' + searchName;
        // });


        // $('.btnDel').on('click', function () {
        //     let seq = [];
        //     seq.push($(this).data('seq'));
        //     if (confirm('삭제 하시겠습니까?')) {
        //         alert('seq : ' + seq);
        //         $.ajax({
        //             type: "POST",
        //             data: {'seq' : seq},
        //             url: "/board/delete",
        //             success: function (data) {
        //                 if (data.result == 'success') {
        //                     alert('삭제를 완료하였습니다.');
        //                     location.reload();
        //                 } else {
        //                     alert('오류발생');
        //                 }
        //             },
        //             error: function () {
        //                 alert('에러발생');
        //             }
        //         })
        //     }
        // });

        // $('#selectAll').on('click', function () {
        //     if ($(this).prop('checked')) {
        //         $("input[name=selectChk]").prop('checked', true);
        //     } else {
        //         $("input[name=selectChk]").prop('checked', false);
        //     }
        // })


    });

</script>
