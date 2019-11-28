function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}


(()=> {
    const methods = document.querySelectorAll('input[name=payment-method]');
    const method_windows = document.querySelectorAll('div.payment-section>form[id^=payment-method-]');
    methods.forEach(method => {
       method.addEventListener('click', () => {
            method_windows.forEach(method_window => method_window.classList.add('hidden') );

            const targetId = document.querySelector('input[name=payment-method]:checked').id;
            const targetWindow = document.getElementById('payment-method-' + targetId);
            targetWindow.classList.remove('hidden');
       });
    });

    const priceList = JSON.parse(sessionStorage.getItem('prices2'));
    let calcPriceList = JSON.parse(sessionStorage.getItem('prices'));
    let lastPrice = 0;
    for (let i = 0; i < priceList.length; i++) {
        while (priceList[i].indexOf(',') !== -1)
            priceList[i] = priceList[i].replace(',', '');
        document.querySelector('.price-'+(i+1)+'-1').innerText = numberWithCommas(Math.round(priceList[i] * 0.7));
        document.querySelector('.price-'+(i+1)+'-2').innerText = numberWithCommas(Math.round(priceList[i] * 0.1));
        document.querySelector('.price-'+(i+1)+'-3').innerText = numberWithCommas(Math.round(priceList[i] * 0.2));
        document.querySelector('.price-'+(i+1)+'-4').innerText = sessionStorage.getItem('numOfPassengers');
        document.querySelector('.price-'+(i+1)+'-5').innerText = calcPriceList[i];
        while(calcPriceList[i].indexOf(',') !== -1)
            calcPriceList[i] = calcPriceList[i].replace(',', '');
        lastPrice += parseInt(calcPriceList[i]);
    }

    const airportFromTxt = document.querySelector('input[name=airportFrom]');
    const airportToTxt = document.querySelector('input[name=airportTo]');
    const flightNumTxt = document.querySelector('input[name=flightNum]');
    const flightArr = JSON.parse(sessionStorage.getItem('flightArr'));
    for (let i =0; i<flightArr.length; i++ ) {
        if (i !== 0) {
            airportFromTxt.value += "#@!";
            airportToTxt.value += "#@!";
            flightNumTxt.value += "#@!";
        }
        airportFromTxt.value += flightArr[i][0];
        airportToTxt.value += flightArr[i][1];
        flightNumTxt.value += flightArr[i][2];
    }

    document.querySelector('.price-all-1').innerText = document.querySelector('.price-all-2').innerText =
        document.querySelector('.price-all-3').innerText = document.querySelector('.price-all-4').innerText = numberWithCommas(lastPrice);


    const btnPay = document.getElementById('btn-pay');
    btnPay.addEventListener('click', () => {
        const selectedMethod = document.querySelector('input[name=payment-method]:checked');
        const targetForm = document.getElementById('payment-method-' + selectedMethod.id);
        const inputTxts = document.querySelectorAll('#'+targetForm.id+" input" );
        // const selectBox = document.querySelector('#'+targetForm.id+" select");
        let isfilled = true;
        for (let i = 0; i < inputTxts.length; i++) {
            if (inputTxts[i].value === "") isfilled = false;
        }
        if (isfilled) targetForm.submit();
        else {
            alert('모든 항목을 입력해주십시오.');
            return false;
        }
    })
})();