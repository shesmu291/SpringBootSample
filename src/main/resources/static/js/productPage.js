$(function () {
   // alert("loaded");

    $.ajax({
        url: '/api/product/all',
        type: 'get',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function (product) {
            for (let i = 0; i < product.length; i++) {
                $("#forAdd").before("<tr>" +
                    "<td>" + product[i].id + "</td>" +
                    "<td>" + product[i].name + "</td>" +
                    "<td>" + product[i].price + "</td>" +
                    "<td>" + product[i].amount + "</td>" +
                    "<td>" +
                    "<a class=\"btn btn-info\" onclick=''>Edit</a>" +
                    "<a class=\"btn btn-danger\" href=\"/products/buy?id=" + product[i].id+"\">Delete</a>" +
                    "</td>" +
                    "</tr>")
            }
        },
        error: function (response) {
            alert('error');
            console.log(response);
        }
    });


})


function addProduct() {
    //сгенерировать модальное окно
    var name=$("#name").val();
    var price=$("#price").val();
    var amount=$("#amount").val();

    var newProduct={
        'name':name,
        'price':price,
        'amount':amount
    }
    $.ajax({
        //запихиваем инфу в нужные поля
        url: '/api/product/add',
        method: 'post',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(newProduct),
        success: function () {
            window.location.replace("/products/all")
        },
        error: function (error) {

        }

    });
       
}
