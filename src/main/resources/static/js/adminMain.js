function cancelOrder () {
    var id = document.getElementById("identifier").textContent;
    if (confirm ("Вы действительно хотите отменить заказ № "+id+"?")) {
        var xmlreq = new XMLHttpRequest();
         xmlreq.open("GET","/admin/order/cansel/"+id,"false");
         xmlreq.send();
         alert("Заказ отменен!");
         window.location.reload();
    }
}

function confirmOrder () {
    var id = document.getElementById("identifier").textContent;
    if (confirm ("Вы действительно хотите подтвердить заказ № "+id+"?")) {
        var xmlreq = new XMLHttpRequest();
         xmlreq.open("GET","/admin/order/confirm/"+id,"false");
         xmlreq.send();
         alert("Заказ подтвержден!");
         window.location.reload();
    }
}

function addItemToOrder (id, price) {
                            var name = document.getElementById('name_'+id).textContent;
                            var quantity = document.getElementById('quantity_'+id).value;
                                if (quantity=="0.00"||quantity==undefined||quantity==""||quantity==0) {
                                    alert("Ошибка, уточните количество ткани");
                                } else if (name==""||name==undefined){
                                    alert("Ошибка, не указано имя");
                                }
                                else {
                                    var body = JSON.stringify(
                                        {
                                            "tissueId":id,
                                            "name":name,
                                            "price":price,
                                            "quantity":quantity,
                                            "totalPrice":price*quantity}
                                            );
                                    var xmlreq = new XMLHttpRequest();
                                    xmlreq.open("POST","/admin/order/additem","false");
                                    xmlreq.setRequestHeader("Content-Type","application/json;UTF-8");
                                    xmlreq.send(body);
                                }

                        }

function delItemFromOrder (id) {
    var name = document.getElementById('name_'+id).textContent;
    var quantity = document.getElementById('quantity_'+id).textContent;
    var price = document.getElementById('price_'+id).textContent;
    var totalPrice = document.getElementById('totalPrice_'+id).textContent;
    var body = JSON.stringify(
        {
            "tissueId":id,
            "name":name,
            "price":price,
            "quantity":quantity,
            "totalPrice":totalPrice
        }
    );
    var xmlreq = new XMLHttpRequest();
    xmlreq.open("DELETE","/admin/order/deleteitem","false");
    xmlreq.setRequestHeader("Content-Type","application/json;UTF-8");
    xmlreq.send(body);
    window.location.reload();
}
