/* 나이 계산기 */
const calculateAge = dateString => {

    const year = dateString.substring(0,4);
    const month = dateString.substring(4,6);
    const day = dateString.substring(6,8);
    const birthDate = new Date(year, month-1, day);
    const today = Date.now();
    const ageDifMs = today - birthDate.getTime();
    const ageDate = new Date(ageDifMs);

    const temp = ageDate.getUTCFullYear() - 1970;

    if(dateString.length < 8 || year > new Date().getFullYear() || month > 12 || day > new Date(year, month, 0).getDate() || temp < 0) {
        document.getElementById('ageCalc-result').textContent = '올바른 생년월일이 아닙니다.';
        return false;
    }

    let age;
    if(temp >= 12) age = '성인';
    else if(temp >= 2) age = '소아';
    else age = '유아';

    document.getElementById('ageCalc-result').textContent = `탑승일 ${getISODate(new Date())} 기준으로 입력한 생년월일은 ${age} 입니다.`;
};

const calculateAgeInit = () => {
    document.getElementById('btn-ageCalc').addEventListener('click', ()=> {
        document.getElementById('ageCalc-result').textContent = "";
        document.getElementById('txtBirthDate').value = "";
    });

    document.getElementById('btn-ageCalc-calc').addEventListener('click', () => calculateAge(document.getElementById('txtBirthDate').value));

    /* 나이 계산기 외 다른 부분 클릭시 */
    overlay.addEventListener('click', () => document.getElementById('btn-ageCalc-window').style.display = 'none' );
};

const getISODate = date => {
    const year = date.getFullYear();
    let month = (1 + date.getMonth());
    month = month >= 10 ? month : '0' + month;
    let day = date.getDate();
    day = day >= 10 ? day : '0' + day;
    return `${year}-${month}-${day}`;
};

/* 출발공항 도착공항 변경 */
const spotChange = () => {
    let spotChangeBtns = document.querySelectorAll('.spot-change');
    spotChangeBtns.forEach(elem => {
        elem.addEventListener('click', () => {
            const index = event.target.id.charAt(event.target.id.length-1);
            const temp = document.getElementById('airportFrom-' + index).value;
            document.getElementById('airportFrom-' + index).value = document.getElementById('airportTo-' + index).value;
            document.getElementById('airportTo-' + index).value = temp;
        })
    });
};

/* insertAfter */
const insertAfter = (referenceNode, newNode) => {
    if (!!referenceNode.nextSibling) referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);
    else referenceNode.parentNode.appendChild(newNode);
};

/* 조회버튼 클릭시 */
const input_check = () => {
    // 유효성검사
    let isValid = false;
    // 여정의 타입 저장 (왕복, 편도, 다구간)
    document.querySelector('input[name="flight-type"]').value = document.querySelector('input[type="radio"][name="trip-type"]:checked').value;
    // 출도착 공항 및 비행날짜 저장
    const airportFroms = Array.prototype.slice.call(document.querySelectorAll('input[id^=airportFrom]'));
    const airportTos = Array.prototype.slice.call(document.querySelectorAll('input[id^=airportTo]'));
    const flightDates = Array.prototype.slice.call(document.querySelectorAll('input[id^=flightDate]'));
    const airportFromInput = document.querySelector('input[name="airportFrom"]');
    const airportToInput = document.querySelector('input[name="airportTo"]');
    const flightDateInput = document.querySelector('input[name="flightDate"]');
    for (let i = 0; i<airportFroms.length; i++) {
        if (airportFroms[i].value !== "" && airportTos[i].value !== "" && flightDates[i].value !== "") {
            isValid = true;
            if (airportFromInput.value) {
                airportFromInput.value += "&" + airportFroms[i].value;
                airportToInput.value += "&" + airportTos[i].value;
                flightDateInput.value += "&" + flightDates[i].value;
            } else {
                airportFromInput.value += airportFroms[i].value;
                airportToInput.value += airportTos[i].value;
                flightDateInput.value += flightDates[i].value;
            }
        }
    }
    // 승객수 저장
    document.querySelector('input[name="numOfPassengers"]').value = strPassengers_generator();
    // session에 좌석타입 저장 (이코노미, 비즈니스, 퍼스트)
    document.querySelector('input[name="seat-type"]').value = document.querySelector('input[name="class-type"]:checked + label').innerText;


    if (!isValid) alert("출,도착 공항 및 탑승일을 선택하여 주십시오.\n- 다구간에서는 최소한 하나이상의 여정을 완성하여야 합니다.");
    else document.getElementById('nextEventFrm').submit();

    return isValid;
};



(() => {
    if(location.pathname.indexOf('booking1') !== -1) {
        /* 각종 안내 버튼 이벤트 */
        hintWindow('passenger-hint');
        hintWindow('class-hint');
        hintWindow('multi-way-hint', true, true, false);
        hintWindow('btn-ageCalc', true, true, true);

        /* 공항 picker */
        openPicker('.open-airport-picker');

        /* 캘린더 */
        cal_init();

        // overlay 클릭시 달력도 종료되도록
        overlay.addEventListener('click', () => cal_init(calendar_openedBy) );

        /* 여정/날짜 선택 */

        /* 체크박스 이벤트 */
        let checkboxElems = document.querySelectorAll('input[name="trip-type"]');
        checkboxElems.forEach(elem => elem.addEventListener('change', () => cal_init()));

        /* 컴포넌트 display 이벤트 */
        // 아니 왜 change 이벤트리스너 작동을 안하냐
        // Todo 코드 더러워서 나중에 다시 봐야겠다..
        document.querySelector('.type-radio-box').addEventListener('mouseup', e => {
            let temp = e.target.previousElementSibling;

            if (temp.id === "round-way")
                document.getElementById('note').classList.add('hidden');
            else
                document.getElementById('note').classList.remove('hidden');

            if (temp.id === "multi-way")
                document.getElementById('multi-way-btn-box').classList.remove('hidden');
            else {
                document.getElementById('multi-way-btn-box').classList.add('hidden');
                let btns = document.getElementById('multi-way-btn-box');
                let input_forms = document.querySelectorAll('.book-input-form');
                for(let i = 1; i<input_forms.length; i++)
                    btns.parentElement.removeChild( btns.previousElementSibling );
            }
        });

        /* 다구간섹션에서 여행선택폼 줄 추가/제거 이벤트 */
        document.querySelector('.add').addEventListener('click', () => {
            let input_forms = document.querySelectorAll('.book-input-form');
            let num_of_forms = input_forms.length;
            if(num_of_forms >= 6) {
                document.querySelector('#note>p').classList.remove('hidden');
                return false;
            }
            document.querySelector('#note>p').classList.add('hidden');

            let div = document.createElement('div');
            div.setAttribute('class','row clearfix book-input-form');
            let temp = input_forms[num_of_forms-1].innerHTML;
            input_forms[num_of_forms-1].parentElement.insertBefore(div, document.getElementById('multi-way-btn-box'));
            div.innerHTML = temp;

            //
            let btns = document.getElementById('multi-way-btn-box');
            let targetElem = btns.previousElementSibling;
            let str;


            for(let i = 0; i<targetElem.children.length; i++) {
                str = targetElem.children[i].firstElementChild.htmlFor;
                str = str.substr(0, str.length-1) + (num_of_forms + 1);

                targetElem.children[i].firstElementChild.htmlFor = str;
                targetElem.children[i].lastElementChild.id = str;
                targetElem.children[i].lastElementChild.name = str;

                if(i === 0) {
                    const strId = targetElem.children[i].children[1].id;
                    targetElem.children[i].children[1].id = strId.substr(0, strId.length-1) + (num_of_forms + 1);
                }

                if (str.indexOf('flightDate') !== -1)
                    calendar[num_of_forms] = cal_generator(str, document.getElementById('flightDate-'+num_of_forms).value);
            }
            openPicker('.open-airport-picker');

            document.getElementById('spot-change-' + (num_of_forms+1)).addEventListener('click', () => {
                const temp = document.getElementById('airportFrom-' + (num_of_forms+1)).value;
                document.getElementById('airportFrom-' + (num_of_forms+1)).value = document.getElementById('airportTo-' + (num_of_forms+1)).value;
                document.getElementById('airportTo-' + (num_of_forms+1)).value = temp;
            });
        });

        document.querySelector('.subtr').addEventListener('click', () => {
            let input_forms = document.querySelectorAll('.book-input-form');
            let num_of_forms = input_forms.length;
            if(num_of_forms <= 1) {
                document.querySelector('#note>p').classList.remove('hidden');
                return false;
            }
            document.querySelector('#note>p').classList.add('hidden');
            let btns = document.getElementById('multi-way-btn-box');
            btns.parentElement.removeChild( btns.previousElementSibling);
        });

        spotChange();

        /* 탑승인원 선택 */
        /* 나이 계산기 이벤트 */
        calculateAgeInit();

        /* 승객수 + - 버튼 이벤트 */
        changeNumOfPassengers();

    }

    else if (location.pathname.indexOf('booking2') !== -1) {

        // 메뉴 하단 선 제거
        menuBorderRemover();

        /* 팝업창 */
        hintWindow('num-of-passengers', true, true);
        hintWindow('btn-ageCalc', true, true, true);

        document.getElementById('num-of-passengers').addEventListener('click', e => {
            const _scrollTop = window.scrollY || document.documentElement.scrollTop;
            document.getElementById('num-of-passengers-window').style.top = _scrollTop + e.target.getBoundingClientRect().bottom + 15 + 'px';
        });

        //캘린더
        if (document.getElementById('trip-type').value === 'multi-way' ) {
            for (let i = 1; i <= document.querySelectorAll('div[id^=journey-').length; i++) {
                calendar[i - 1] = cal_generator('flightDate-' + i, document.getElementById('flightDate-' + i).value);
                calendar[i - 1].el.classList.add('centeredX');
            }
            overlay.addEventListener('click', () => cal_init(calendar_openedBy));
        } else
            cal_init();
        //공항picker
        openPicker('.open-airport-picker');

        /* 승객수 + - 버튼 이벤트 */
        changeNumOfPassengers();

        /* 탑승객 팝업에서 선택 클릭시 */
        const passengerNum = document.getElementById('num-of-passengers');

        document.querySelector('#selectBtn').addEventListener('click', () => {
            if(strPassengers_generator()) {
                passengerNum.value = strPassengers_generator();
                document.getElementById('num-of-passengers-window').style.display = 'none';
                overlay.style.display = 'none';
            }
        });

        /* 탑승객 팝업 오픈시 입력된 값 가져오기 */
        passengerNum.addEventListener('click', () => {
            const totalNum = passengerNum.value;

            let adultNum = totalNum.charAt(totalNum.indexOf('성인')+3);
            let childNum = totalNum.charAt(totalNum.indexOf('소아')+3);
            let infantNum = totalNum.charAt(totalNum.indexOf('유아')+3);
            if (totalNum.indexOf('성인') === -1) adultNum = 0;
            if (totalNum.indexOf('소아') === -1) childNum = 0;
            if (totalNum.indexOf('유아') === -1) infantNum = 0;
            document.getElementById('numOfAdult').value = adultNum;
            document.getElementById('numOfChild').value = childNum;
            document.getElementById('numOfInfant').value = infantNum;
        });

        /* 탑승객 팝업에서 다른 부분 클릭시 */
        overlay.addEventListener('click', () => document.getElementById('num-of-passengers-window').style.display = 'none' );

        /* 나이 계산기 이벤트 */
        calculateAgeInit();

        document.querySelector('#btn-ageCalc-window .btn-close').addEventListener('click', () => {
            overlay.style.display = 'block';
            document.getElementById('num-of-passengers-window').style.display = 'block';
        });

        /* 티켓 선택시 */
        const ticket_select = () => {
            document.querySelectorAll('.flight-ticket-table td>label').forEach(elem => {
                elem.addEventListener('click', e => {
                    const target_journey = e.target.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.id;
                    const selected = document.querySelector(`#${target_journey} .selected-ticket`);
                    if (selected) {
                        selected.classList.remove('selected-ticket');
                        selected.style.backgroundColor = '#eee';
                        selected.style.borderBottomColor = selected.parentElement.style.borderBottomColor = '#ddd';
                        selected.parentElement.parentElement.removeChild(selected.parentElement.nextElementSibling);
                    }
                    const parent = e.target.parentElement;
                    let price = e.target.nextElementSibling.lastElementChild.innerText;
                    while (price.indexOf(',') !== -1)
                        price = price.replace(',', '');

                    const totalNum = passengerNum.value;

                    let adultNum = totalNum.charAt(totalNum.indexOf('성인') + 3);
                    let childNum = totalNum.charAt(totalNum.indexOf('소아') + 3);
                    let infantNum = totalNum.charAt(totalNum.indexOf('유아') + 3);
                    //
                    let tableRow = document.createElement("TR");
                    tableRow.style.border = "1px solid";
                    let tempText = "<td colspan='7' class='price-div'><div class='col-two-fifth'><div class='col-two-fifth'>선택된 항공편 운임</div><div class='col-three-fifth'>";
                    const eventTarget = e.target.htmlFor.charAt(e.target.htmlFor.length - 1);
                    if (eventTarget === "1") {
                        tableRow.style.backgroundColor = tableRow.style.borderColor = parent.style.backgroundColor = parent.parentElement.style.borderBottomColor = "#9bf";
                        tempText += "60% 할인";
                    } else if (eventTarget === "2") {
                        tableRow.style.backgroundColor = tableRow.style.borderColor = parent.style.backgroundColor = parent.parentElement.style.borderBottomColor = "#69f";
                        tempText += "50% 할인";
                    } else if (eventTarget === "3") {
                        tableRow.style.backgroundColor = tableRow.style.borderColor = parent.style.backgroundColor = parent.parentElement.style.borderBottomColor = "#36f";
                        tempText += "정상 가격";
                    }
                    tempText += `</div></div><div class="col-three-fifth"><span class="people-type">(성인 ${adultNum})</span><span>${(price * adultNum).toLocaleString()}</span>`;
                    if (childNum !== " ") tempText += `<span>+</span><span class="people-type">(소아 ${childNum})</span><span>${(price / 200 * 100 * childNum).toLocaleString()}</span>`;
                    if (infantNum !== " ") tempText += `<span>+</span><span class="people-type">(유아 ${infantNum})</span><span>0</span>`;
                    tempText += `<span>=</span> KRW ${((price * adultNum) + (price / 200 * 100 * childNum)).toLocaleString()}</div></td>`;
                    tableRow.innerHTML = tempText;
                    insertAfter(parent.parentElement, tableRow);
                    e.target.parentElement.classList.add('selected-ticket');
                });
            });
        };
        ticket_select();

        /* 테이블정렬 */
        const ticketRowsArray = [
            Array.prototype.slice.call(document.querySelectorAll('#journey-1 tr')),
            Array.prototype.slice.call(document.querySelectorAll('#journey-2 tr')),
            Array.prototype.slice.call(document.querySelectorAll('#journey-3 tr')),
            Array.prototype.slice.call(document.querySelectorAll('#journey-4 tr')),
            Array.prototype.slice.call(document.querySelectorAll('#journey-5 tr')),
            Array.prototype.slice.call(document.querySelectorAll('#journey-6 tr')),
        ];
        let orderMultiplier = 1;
        document.querySelectorAll('input[id^=sort-by]').forEach(item => {
            item.addEventListener('click', ()=> {
                let sortedTicketRows;
                if (item.classList.contains('checked')) orderMultiplier *= -1;
                else orderMultiplier = 1;
                if (orderMultiplier === -1) item.classList.add('reverse-order');
                else item.classList.remove('reverse-order');
                const index = item.id.charAt(item.id.length-1);
                if (item.id.indexOf('departure') !== -1) {
                    sortedTicketRows = ticketRowsArray[index-1].sort( (a, b) => {
                            const tempA = a.children[0].innerText;
                            const tempB = b.children[0].innerText;
                            return tempA === tempB? 0 : tempA>tempB? orderMultiplier : -orderMultiplier;
                        }
                    );
                } else if (item.id.indexOf('arrival') !== -1) {
                    sortedTicketRows = ticketRowsArray[index-1].sort( (a, b) => {
                            const tempA = a.children[2].innerText;
                            const tempB = b.children[2].innerText;
                            return tempA === tempB? 0 : tempA>tempB? orderMultiplier : -orderMultiplier;
                        }
                    );
                } else if (item.id.indexOf('flight') !== -1) {
                    sortedTicketRows = ticketRowsArray[index-1].sort( (a, b) => {
                            let tempA = a.children[1].innerText;
                            let tempB = b.children[1].innerText;
                            if (tempA.length>tempB.length) return orderMultiplier;
                            if (tempB.length>tempA.length) return -orderMultiplier;
                            return tempA === tempB? 0 : tempA>tempB? orderMultiplier : -orderMultiplier;
                        }
                    );
                } else if (item.id.indexOf('price') !== -1) {
                    sortedTicketRows = ticketRowsArray[index - 1].sort((a, b) => {
                            const tempA = a.children[6].children[2].lastElementChild.innerText;
                            const tempB = b.children[6].children[2].lastElementChild.innerText;
                            return tempA === tempB ? 0 : tempA > tempB ? orderMultiplier : -orderMultiplier;
                        }
                    );
                }
                const targetBody = document.querySelector(`#journey-${index} tbody`);
                targetBody.innerHTML = "";
                sortedTicketRows.forEach(ticketRow => targetBody.innerHTML += ticketRow.outerHTML);

                document.querySelector(`#journey-${index} input.checked`).classList.remove('checked');
                item.classList.add('checked');
                const selected = document.querySelector(`#journey-${index} .selected-ticket`);
                if (selected) {
                    selected.classList.remove('selected-ticket');
                    selected.style.backgroundColor = '#eee';
                    selected.style.borderBottomColor = selected.parentElement.style.borderBottomColor = '#ddd';
                }
                ticket_select();
            });
        });

        // 날짜 선택시 ajax
        $(".price-of-day label").click(function() {
            var targetJourneyIndex = $(this).attr('for').charAt(8);
            var dateMod = $(this).attr('for').charAt(16)-4;
            var targetAirportFrom = $("#journey-airportFrom-"+targetJourneyIndex).val();
            var targetAirportTo = $("#journey-airportTo-"+targetJourneyIndex).val();
            var targetFlightDate = $("#journey-flightDate-"+targetJourneyIndex).val();
            $.ajax({
                url : "changeDate",
                type : "POST",
                data : `airportFrom=${targetAirportFrom}&airportTo=${targetAirportTo}&flightDate=${targetFlightDate}&dateMod=${dateMod}`,
                success : function(result){
                    if(result !== "") {
                        alert(result);
                    }else
                        alert("ss");
                },
                error : function(){
                    alert("비행기표검색에 실패했습니다.");
                }
            })
        });

        /* waypoint */
        // 상단바 변경
        $('.cur-route').waypoint(function(direction) {
            if (direction === "down") {
                $('.cur-route').addClass('sticky');
            } else {
                $('.cur-route').removeClass('sticky');
            }
        }, {
            offset: '10px;'
        });

        // 섹션별 여정 설명바
        for (let i = 1; i <= 6; i++) {
            $('#journey-'+i).waypoint(direction => {
                if (direction === "down") $(`#journey-${i} .journey-name-fixed`).removeClass('hidden');
                else $('.journey-name-fixed').addClass('hidden');
            }, {
                offset: '-1px;'
            });

            if ($('#journey-' + (i + 1)).length) {
                $('#journey-' + (i + 1)).waypoint(direction => {
                    if (direction === "up") $(`#journey-${i} .journey-name-fixed`).removeClass('hidden');
                    else $('.journey-name-fixed').addClass('hidden');
                }, {
                    offset: '200px;'
                });
            } else {
                $('.flex').waypoint(direction => {
                    if (direction === "down") $('.journey-name-fixed').addClass('hidden');
                    else $(`#journey-${i} .journey-name-fixed`).removeClass('hidden');
                }, {
                    offset: '200px;'
                });
                break;
            }
        }
    }

    else if (location.pathname.indexOf('booking3') !== -1) {
        hintWindow('membership-hint');
        birth_option_generator();

        const input = document.getElementById("phone");
        window.intlTelInput(input, {
            dropdownContainer: document.body,
            initialCountry: "kr",
            preferredCountries: ['kr', 'us', 'cn'],
            utilsScript: "../../../Vendor/intl-tel-input-16.0.0/build/js/utils.js",
        });
    }
})();