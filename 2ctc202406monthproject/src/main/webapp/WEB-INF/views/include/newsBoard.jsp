<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="news-layout" style="margin-top:100px">
<!-- �µ��ð� -->
<div class="navbar-clock-main">
    <span id="clock">
        <a class="time">1.5�� ��±���</a>
    </span>
</div>
    <div class="main">
    
    <div class="box1">
            <c:forEach var='boardlists' items="${list}" varStatus="idx">
            <c:choose>
            <c:when test="${board_list[idx.index].board_IDX == 3}">
            <div class="card shadow">
                <div class="card-body">
                    <h4 class="card-title">${board_list[idx.index].board_info_Nm}</h4>
                    <table class="table table-hover" id='board_list'>
                        <thead>
                            <tr>
                                <th class="text-center w-25">�۹�ȣ</th>
                                <th>����</th>
                                <th class="text-center w-25 d-none d-xl-table-cell">�ۼ���¥</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var='obj' items="${boardlists}">
                            <tr>
                                <td class="text-center">${obj.noticeID}</td>
                                <th><a href='${root}board/read?board_IDX=${board_list[idx.index].board_IDX}&noticeID=${obj.noticeID}&page=1' class="text-title">${obj.title}</a></th>
                                <td class="text-center d-none d-xl-table-cell">${obj.create_date}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <a href="${root}board/main?board_IDX=${board_list[idx.index].board_IDX}" class="btn btn-primary">������</a>
                </div>
            </div>
            </c:when>
            </c:choose>
            </c:forEach>
       </div> 
        <div class="box2">
            <c:forEach var='boardlists' items="${list}" varStatus="idx">
            <c:choose>
            <c:when test="${board_list[idx.index].board_IDX == 1}">
            <div class="card shadow">
                <div class="card-body">
                    <h4 class="card-title">${board_list[idx.index].board_info_Nm}</h4>
                    <table class="table table-hover" id='board_list'>
                        <thead>
                            <tr>
                                <th class="text-center w-25">�۹�ȣ</th>
                                <th>����</th>
                                <th class="text-center w-25 d-none d-xl-table-cell">�ۼ���¥</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var='obj' items="${boardlists}">
                            <tr>
                                <td class="text-center">${obj.noticeID}</td>
                                <th><a href='${root}board/read?board_IDX=${board_list[idx.index].board_IDX}&noticeID=${obj.noticeID}&page=1' class="text-title">${obj.title}</a></th>
                                <td class="text-center d-none d-xl-table-cell">${obj.create_date}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <a href="${root}board/main?board_IDX=${board_list[idx.index].board_IDX}" class="btn btn-primary">������</a>
                </div>
            </div>
            </c:when>
            </c:choose>
            </c:forEach>
        </div>
    </div>
</div>

<script>
document.addEventListener("DOMContentLoaded", function() {
    function updateDDay() {
       

        const targetDate = new Date('2030-04-18'); // ��ǥ ��¥�� ��Ȯ�ϰ� ���� (UTC �ð�)
       
        const now = new Date();

        const timeDiff = targetDate - now; // �и��� ������ �ð� ���� ���
      

        if (timeDiff < 0) {
            console.error("The target date is in the past Main");
            document.querySelector('.time').textContent = "1.5�� ��±��� 0��";
            document.querySelector('.date').textContent = "��¥�� �������ϴ�.";
            return;
        }

        const daysLeft = Math.ceil(timeDiff / (1000 * 60 * 60 * 24)); // �и��ʸ� �ϼ��� ��ȯ

        document.querySelector('.time').textContent = "1.5�� ��±��� " + daysLeft + "��";
    }

    setInterval(updateDDay, 1000);
    updateDDay(); // Initial call to display D-DAY immediately
});
</script>