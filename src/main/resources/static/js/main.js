function addItemToChart(id, name) {
    var quantity = document.getElementById('quantity_'+id).value;
        if (quantity=="0.00"||quantity==undefined||quantity==""||quantity==0) {
            alert("Ошибка, уточните количество ткани");
        } else {
            var body = JSON.stringify(
                {
                    "tissueId":id,
                    "quantity":quantity,
                    "totalPrice":10000}
                    );
            var xmlreq = new XMLHttpRequest();
            xmlreq.open("POST","/order","false");
            xmlreq.setRequestHeader("Content-Type","application/json;UTF-8");
            xmlreq.send(body);
        }

}
