let page = 1;

function getMore(count) {
    $.ajax({
        url: "/more/auction?size=4&page=" + page++,
        type: "GET",
        dataType: "json",
        success: function (data) {
            data.forEach(function (auction) {

                let d = new Date(auction['date'])

                let stringDate = ("0" + d.getDate()).slice(-2) + "." + ("0"+(d.getMonth()+1)).slice(-2) + "." +
                    d.getFullYear() + " " + ("0" + d.getHours()).slice(-2) + ":" + ("0" + d.getMinutes()).slice(-2) + ":00";


                $("#auction_block").append('<div class="col">' +
                    '                        <div class="card" style="width: 18rem;">' +
                    '                            <div class="card-body">' +
                    '                                <h5 class="card-title">' + auction['title'] + '</h5>' +
                    '                                <p class="card-text">' + stringDate + '</p>' +
                    '                                <a href="/auction/' + auction['id'] + '" class="btn btn-primary">подробнее</a>' +
                    '                            </div>' +
                    '                        </div>' +
                    '                    </div>')
            })
        }
    });

    if (page === count) {
        $("#getMoreButton").hide()
    }
}

if ($("#count").val() <= page) {
    $("#getMoreButton").hide()
}
