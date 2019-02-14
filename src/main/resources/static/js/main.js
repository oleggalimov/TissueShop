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
