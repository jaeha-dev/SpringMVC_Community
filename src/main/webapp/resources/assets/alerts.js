'use strict';

var alerts = document.querySelectorAll('.fa-times-circle');
var alertsFade = document.querySelectorAll('.alert-fade');

var options = { attributes: true };

var count = 0;

function getAlert(el) {
    if (el.classList.contains('alert')) return el;else return getAlert(el.parentNode);
}

function checkShowing(el) {
    if (el.classList.contains('alert_none')) return false;else return true;
}

alerts.forEach(function (el) {
    return el.addEventListener('click', function () {
        var father = getAlert(el);
        father.classList.toggle('alert_none');
    });
});

var onClassChange = new Event('classChange');

function createNewObserver() {
    var observer = new MutationObserver(function (mutationList) {
        var alert = mutationList[0].target;
        observer.disconnect();
        if (!alert.classList.contains('alert_none')) alert.dispatchEvent(onClassChange);
    });
    return observer;
}

alertsFade.forEach(function (el) {
    var mutation = createNewObserver();
    mutation.observe(el, options);
});

alertsFade.forEach(function (el) {
    return el.addEventListener('classChange', function (ev) {
        var alert = ev.target;
        if (!alert.classList.contains('alert_none')) {
            var time = 3000;
            setTimeout(function () {
                alert.classList.toggle('alert_none');
                var mutation = createNewObserver();
                mutation.observe(alert, options);
            }, time);
        }
    });
});

// const alerts = document.querySelectorAll('.fa-times-circle')
// const alertsFade = document.querySelectorAll('.alert-fade')
//
// const options = { attributes: true }
//
// let count = 0
//
// function getAlert(el) {
//     if (el.classList.contains('alert')) return el
//     else return getAlert(el.parentNode)
// }
//
// function checkShowing(el) {
//     if (el.classList.contains('alert_none')) return false
//     else return true
// }
//
// alerts.forEach(el => el.addEventListener('click', () => {
//     const father = getAlert(el)
//     father.classList.toggle('alert_none')
// }))
//
// const onClassChange = new Event(`classChange`)
//
// function createNewObserver() {
//     const observer = new MutationObserver(mutationList => {
//         const alert = mutationList[0].target
//         observer.disconnect()
//         if (!alert.classList.contains('alert_none'))
//             alert.dispatchEvent(onClassChange)
//     })
//     return observer
// }
//
// alertsFade.forEach(el => {
//     const mutation = createNewObserver()
//     mutation.observe(el, options)
// })
//
// alertsFade.forEach(el => el.addEventListener('classChange', ev => {
//     const alert = ev.target
//     if (!alert.classList.contains('alert_none')) {
//         // const time = parseInt(alert.getAttribute('data-fade-time')) * 1000
//         // setTimeout(() => {
//         //     alert.classList.toggle('alert_none')
//         //     const mutation = createNewObserver()
//         //     mutation.observe(alert, options)
//         // }, time)
//         const time = 3000
//         setTimeout(() => {
//             alert.classList.toggle('alert_none')
//             const mutation = createNewObserver()
//             mutation.observe(alert, options)
//             alert.fadeTo(500, 0).slideUp(500, function () {
//                 alert.remove()
//             })
//         }, time)
//     }
// }))

// Alert 자동 닫기
// window.setTimeout(function() {
//     $(".alert").fadeTo(500, 0).slideUp(500, function() {
//         $(this).remove();
//     });
// }, 3000);