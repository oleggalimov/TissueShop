function addItemToChart(id, price) {
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
            xmlreq.open("POST","/order","false");
            xmlreq.setRequestHeader("Content-Type","application/json;UTF-8");
            xmlreq.send(body);
        }

}
function delItemFromChart (id) {
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
    xmlreq.open("DELETE","/order","false");
    xmlreq.setRequestHeader("Content-Type","application/json;UTF-8");
    xmlreq.send(body);
    window.location.reload();
}
function changeItemInChart (id) {
    var name = document.getElementById('name_'+id).textContent;
    var quantity = document.getElementById('quantity_'+id).value;
    var price = document.getElementById('price_'+id).textContent;
    var totalPrice = document.getElementById('totalPrice_'+id).textContent;
    var body = JSON.stringify(
                    {
                        "tissueId":id,
                        "name":name,
                        "price":price,
                        "quantity":quantity,
                        "totalPrice":price*quantity
                    }
    );
    var xmlreq = new XMLHttpRequest();
    xmlreq.open("PUT","/order","false");
    xmlreq.setRequestHeader("Content-Type","application/json;UTF-8");
    xmlreq.send(body);
    window.location.reload();
}