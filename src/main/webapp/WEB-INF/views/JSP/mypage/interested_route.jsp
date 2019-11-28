<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>관심노선</title>
        <link rel="stylesheet" href="<%=css %>/mypage.css">
        <script>window.onbeforeunload = () => window.scrollTo(0, 0)</script>
    </head>
    <body>
    <%@ include file="../common/nav.jspf" %>
        <section class="content">
            <h3>관심노선</h3>
            <div class="interest-route-btn-wrapper">
                <input type="checkbox" class="checkbox2" id="check-all">
                <label for="check-all">전체선택</label>
                <input type="button" value="전체삭제" id="delete-selected-btn" class="whiteBtn">
                <a href = "#">
                    <input type="checkbox" value="관심노선추가" id="interest-route-add-btn">
                    <label for="interest-route-add-btn" class="blueBtn">관심노선추가</label>
                </a>
            </div>
            
            <div class="interest-route-add-section">
                <div class="table-form">
                    <div class="row clearfix">
                        <div class="table-row-title">
                            여정
                        </div>
                        <div>
                            <input type="radio" name="trip" class="radio3" id="round-trip" checked>
                            <label for="round-trip"> 왕복</label>
                            <input type="radio" name="trip" class="radio3" id="oneway-trip">
                            <label for="oneway-trip"> 편도</label>
                        </div>
                    </div>
                    <div class="row clearfix">
                        <div class="table-row-title">
                            출발지
                        </div>
                        <div class="two-in-row">
                            <input type="text" placeholder="출발지를 입력해주십시오." id="airportFrom-1" class="open-airport-picker" readonly>
                        </div>
                        <div class="table-row-title">
                            도착지
                        </div>
                        <div class="two-in-row">
                            <input type="text" placeholder="도착지를 입력해주십시오." id="airportTo-1" class="open-airport-picker" readonly>
                        </div>
                    </div>
                </div>
                
                <div class="row clearfix">
                    <input type="button" class="blueBtn" value="저장" id="save">
                    <input type="button" class="whiteBtn" value="취소" id="cancel">
                </div>
            </div>
            
            <div class="interest-route-section">
                <div class="interest-route-title">
                    <input type="checkbox" class="checkbox2" id="check-1">
                    <label for="check-1"></label>
                    <span>서울 / 인천</span> <span>뉴욕 / 존 F 케네디</span>
                    <a href = "#">
                        <span class="regdate">2019.09.08</span>
                        <input type="checkbox" value="fold" id="fold-btn-1">
                        <label for="fold-btn-1"><img src="../../IMG/icon/underArrow.png" alt=""></label>
                    </a>
                </div>
                
                <div class="row clearfix interest-route">
                    <div class="col-three-fourth img-wrapper">
                        <img src="../../IMG/new_product_img/jeju.jpg">
                    </div>
                    <div class="col-fourth interest-route-detail">
                        <img src="../../IMG/icon/flight-table.svg" alt="">
                        <span>
                            <a href="#"><button class="btn-booking whiteBtn">수정</button></a>
                            <a href="#"><button class="btn-booking whiteBtn">삭제</button></a>
                        </span>
                        <p class="trip-type font-blue4"><i class="fas fa-plane-departure font-blue4"></i> 왕복</p>
                        <p class="airport1">서울 / 인천</p>
                        <p class="airport2">뉴욕 / 존 F 케네디</p>
                        <button class="btn-booking blueBtn" id="reserve-btn">예약하기</button>
                    </div>
                </div>
            </div>
            
            
            <div class="no-record">
                <p>등록한 관심노선이 없습니다.</p>
                관심노선을 등록하시려면 상단의 추가버튼을 클릭하여 주십시오. <br>
            </div>
        </section>
        <script src="<%=js %>/common.js"></script>
        <script src="<%=js %>/airportpicker.js"></script>
        <script src="<%=js %>/mypage.js"></script>
        <%@ include file="../common/footer.jspf" %>
    </body>
</html>