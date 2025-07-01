function addNewSmartPhone(event) {
    event.preventDefault();

    let producer = $('#producer').val();
    let model = $('#model').val();
    let price = $('#price').val();
    let newSmartphone = { producer, model, price };

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newSmartphone),
        url: "http://localhost:8080/api/smartphones",
        success: successHandler
    });
}

function updateSmartphone(event) {
    event.preventDefault();

    let id = $('#smartphone-id').val();
    let updatedSmartphone = {
        producer: $('#producer').val(),
        model: $('#model').val(),
        price: $('#price').val()
    };

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        url: `http://localhost:8080/api/smartphones/${id}`,
        data: JSON.stringify(updatedSmartphone),
        success: function () {
            successHandler();
            $('#submit-button').show();
            $('#update-button').hide();
            $('#smartphone-id').val('');
        }
    });
}

function editSmartphone(id) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/smartphones/${id}`,
        success: function (data) {
            console.log("Data received from API:", data);

            // ⚠ Gọi displayFormCreate() trước để reset form
            displayFormCreate();

            // ✅ Sau đó set dữ liệu vào
            $('#producer').val(data.producer);
            $('#model').val(data.model);
            $('#price').val(data.price);
            $('#smartphone-id').val(data.id);

            console.log("Form values after setting:");
            console.log("Producer:", $('#producer').val());
            console.log("Model:", $('#model').val());
            console.log("Price:", $('#price').val());

            $('#submit-button').hide();
            $('#update-button').show();
        },
        error: function (xhr) {
            console.error("Failed to fetch smartphone:", xhr);
        }
    });
}


function deleteSmartphone(id) {
    $.ajax({
        type: "DELETE",
        url: `http://localhost:8080/api/smartphones/${id}`,
        success: successHandler
    });
}

function successHandler() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/smartphones",
        success: function (data) {
            let content = '<table id="display-list" border="1"><tr>' +
                '<th>Producer</th><th>Model</th><th>Price</th><th>Actions</th></tr>';
            for (let i = 0; i < data.length; i++) {
                content += getSmartphone(data[i]);
            }
            content += "</table>";
            $('#smartphoneList').html(content).show();
            $('#add-smartphone').hide();
            $('#display-create').show();
            $('#title').show();
        }
    });
}

function getSmartphone(smartphone) {
    return `<tr>
                <td>${smartphone.producer}</td>
                <td>${smartphone.model}</td>
                <td>${smartphone.price}</td>
                <td>
                    <button onclick="editSmartphone(${smartphone.id})">Edit</button>
                    <button onclick="deleteSmartphone(${smartphone.id})">Delete</button>
                </td>
            </tr>`;
}

function displayFormCreate() {
    $('#smartphoneList').hide();
    $('#add-smartphone').show();
    $('#display-create').hide();
    $('#title').hide();

    // Reset form
    $('#producer').val('');
    $('#model').val('');
    $('#price').val('');
    $('#smartphone-id').val('');
    $('#submit-button').show();
    $('#update-button').hide();
}
